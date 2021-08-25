package uz.pcmarket.apppcmarketuz.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pcmarket.apppcmarketuz.entity.Property;

@Projection(types = Property.class)
public interface CustomProperty {
    Integer getId();
    String getName();
    int getAmount();
    Character getCharacter();
}
