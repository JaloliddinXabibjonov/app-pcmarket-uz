package uz.pcmarket.apppcmarketuz.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BasketDto {
    @NotNull(message = "Checkout should not empty")
    private int checkoutId;
    @NotNull(message = "User should not empty")
    private int userId;

}
