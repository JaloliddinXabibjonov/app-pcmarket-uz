package uz.pcmarket.apppcmarketuz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pcmarket.apppcmarketuz.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "Select * from product p \n" +
            "    join \n" +
            "        character ch on p.id=ch.product_id\n" +
            "    join\n" +
            "    property pr on ch.id=pr.character_id where pr.id in :property_list;",nativeQuery = true)
    Page<Product> findAllByPropertyIdes(List<Integer> property_list,Pageable pageable);
}
