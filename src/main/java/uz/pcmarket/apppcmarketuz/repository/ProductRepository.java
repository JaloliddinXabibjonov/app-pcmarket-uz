package uz.pcmarket.apppcmarketuz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pcmarket.apppcmarketuz.entity.Product;


public interface ProductRepository extends JpaRepository<Product, Integer> {

}
