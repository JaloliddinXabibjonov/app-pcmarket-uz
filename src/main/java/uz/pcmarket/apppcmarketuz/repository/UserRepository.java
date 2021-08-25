package uz.pcmarket.apppcmarketuz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pcmarket.apppcmarketuz.entity.User;
import uz.pcmarket.apppcmarketuz.projection.CustomUser;

@RepositoryRestResource(path = "user" , excerptProjection = CustomUser.class)
public interface UserRepository extends JpaRepository<User, Integer> {
}
