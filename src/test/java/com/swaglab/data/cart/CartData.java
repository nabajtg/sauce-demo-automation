package com.swaglab.data.cart;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data @ToString
public class CartData {

    private String user;
    private List<String> itemsToAdd;
    private List<String> itemsToRemove;

}
