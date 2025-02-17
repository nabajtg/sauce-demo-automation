package com.swaglab.data.cart;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.ToString;

@Data @ToString
public class CartData {

    private String user;
    private List<String> itemsToAdd;
    private List<String> itemsToRemove;

}
