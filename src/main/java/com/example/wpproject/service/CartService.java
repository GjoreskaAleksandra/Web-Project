package com.example.wpproject.service;

import com.example.wpproject.model.Drug;
import com.example.wpproject.model.Cart;

import java.util.List;

public interface CartService {

    List<Drug> listAllDrugsInCart(Long id);
    Cart getActiveCart(String username);
    Cart addDrugToCart(String username, Long id);
    void deleteById(Long id);

}
