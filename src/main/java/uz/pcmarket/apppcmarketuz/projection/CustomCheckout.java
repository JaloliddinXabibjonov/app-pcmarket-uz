package uz.pcmarket.apppcmarketuz.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pcmarket.apppcmarketuz.entity.Basket;
import uz.pcmarket.apppcmarketuz.entity.Checkout;

@Projection(types = Checkout.class)
public interface CustomCheckout {
    Integer getId();
    String getName();
    String getAddress();
    String getPhone();
    String getOrderNote();
    Basket getBasket();
}
