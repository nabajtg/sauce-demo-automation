package com.swaglab.enums;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum SortOptions{
    ASCENDING_BY_NAME("ascendingByName", "az"), 
    DESCENDING_BY_NAME("descendingByName", "za"), 
    ASCENDING_BY_PRICE("ascendingByPrice", "lohi"), 
    DESCENDING_BY_PRICE("descendingByPrice", "hilo");

    private @Getter String testDataValue;
    private @Getter String selectValue;

    public static SortOptions get(String testDataValue){
        return Arrays.asList(SortOptions.values()).stream()
            .filter(option-> option.getTestDataValue().equals(testDataValue))
            .findFirst().get();
    }
}
