package com.swaglab.data.order;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data @ToString
public class OrderData {

    private String user;
    private List<String> items;
    private CustomerDetails customerDetails;
}
