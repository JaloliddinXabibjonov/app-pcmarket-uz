package uz.pcmarket.apppcmarketuz.payload;

import lombok.Data;


@Data
public class BasketProductDto {


    private int amount;
    private double subtotal;
    private Integer basketId;
}
