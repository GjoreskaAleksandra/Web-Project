package com.example.wpproject.service.impl;

import com.example.wpproject.model.Drug;
import com.example.wpproject.model.Cart;
import com.example.wpproject.model.User;
import com.example.wpproject.model.enumerations.CartStatus;
import com.example.wpproject.model.exceptions.DrugAlreadyInCartException;
import com.example.wpproject.model.exceptions.DrugNotFoundException;
import com.example.wpproject.model.exceptions.CartNotFoundException;
import com.example.wpproject.model.exceptions.UserNotFoundException;
import com.example.wpproject.repository.DrugRepository;
import com.example.wpproject.repository.CartRepository;
import com.example.wpproject.repository.UserRepository;
import com.example.wpproject.service.CartService;
import org.springframework.stereotype.Service;
import java.lang.Long;
import java.util.List;


@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final DrugRepository drugRepository;
    private final UserRepository userRepository;

    public CartServiceImpl(CartRepository cartRepository, DrugRepository drugRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.drugRepository = drugRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Drug> listAllDrugsInCart(Long id) {
        if(!this.cartRepository.findById(id).isPresent())
            throw new CartNotFoundException(id);
        return this.cartRepository.findById(id).get().getDrugs();
    }

    @Override
    public Cart getActiveCart(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return this.cartRepository.findByUserAndStatus(user, CartStatus.CREATED)
                .orElseGet(() -> {
                    Cart cart = new Cart(user);
                return this.cartRepository.save(cart);
                });
    }

    @Override
    public Cart addDrugToCart(String username, Long id) {

        Cart cart = this.getActiveCart(username);

        Drug drug = this.drugRepository.findById(id)
                .orElseThrow(() -> new DrugNotFoundException(id));

        if(cart.getDrugs()
                .stream().filter(i -> i.getId() == id).count() > 0)
            throw new DrugAlreadyInCartException(id, username);

        cart.getDrugs().add(drug);

        return this.cartRepository.save(cart);

    }

    @Override
    public void deleteById(Long id) {
        this.cartRepository.deleteById(id);
    }
}
