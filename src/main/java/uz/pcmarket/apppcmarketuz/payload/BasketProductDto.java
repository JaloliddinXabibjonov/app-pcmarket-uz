package uz.pcmarket.apppcmarketuz.payload;

import lombok.Data;

import java.util.Set;


@Data
public class BasketProductDto {


    private int amount;
    private double subtotal;
    private Set<Integer> productSet;
    private Integer basketId;
}
