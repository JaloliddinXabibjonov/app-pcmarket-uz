package uz.pcmarket.apppcmarketuz.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pcmarket.apppcmarketuz.entity.User;

@Projection(types = User.class)
public interface CustomUser {
    Integer getId();
    String getEmail();
    String getPassword();

}
