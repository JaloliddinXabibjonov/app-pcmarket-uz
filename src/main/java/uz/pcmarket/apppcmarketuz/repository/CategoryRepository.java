package uz.pcmarket.apppcmarketuz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pcmarket.apppcmarketuz.entity.Category;
import uz.pcmarket.apppcmarketuz.projection.CustomCategory;

@RepositoryRestResource(path = "category" , excerptProjection = CustomCategory.class)
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
