package uz.pcmarket.apppcmarketuz.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductDto {

    private Integer id;
    @NotNull(message = "The product name must not be empty")
    private String name;
    @NotNull(message = "The price of the product should not be empty")
    private double price;
    @NotNull(message = "The product category should not be empty")
    private Integer categoryId;
    @NotNull(message = "The product photo should not be empty")
    private Integer photoId;

    public ProductDto(String name, double price, Integer categoryId, Integer photoId) {
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.photoId = photoId;

    }
}
