package uz.pcmarket.apppcmarketuz.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pcmarket.apppcmarketuz.entity.Category;

@Projection(types = Category.class)
public interface CustomCategory {
    Integer getId();
    String getName();
}
