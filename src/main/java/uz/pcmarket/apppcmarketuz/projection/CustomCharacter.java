package uz.pcmarket.apppcmarketuz.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pcmarket.apppcmarketuz.entity.Character;
import uz.pcmarket.apppcmarketuz.entity.Product;

@Projection(types = Character.class)
public interface CustomCharacter {
    Integer getId();
    String getName();
    Product getProduct();
}
