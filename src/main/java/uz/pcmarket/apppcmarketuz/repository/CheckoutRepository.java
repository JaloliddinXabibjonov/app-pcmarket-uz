package uz.pcmarket.apppcmarketuz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pcmarket.apppcmarketuz.entity.Checkout;
import uz.pcmarket.apppcmarketuz.projection.CustomCategory;
import uz.pcmarket.apppcmarketuz.projection.CustomCheckout;

@RepositoryRestResource(path = "checkout" , excerptProjection = CustomCheckout.class)
public interface CheckoutRepository extends JpaRepository<Checkout, Integer> {
}
