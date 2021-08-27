package uz.pcmarket.apppcmarketuz.payload;

import lombok.Data;

import java.util.Set;


@Data
public class BasketProductDto {


    private int amount;
    private Integer productId;
    private Integer basketId;
}
