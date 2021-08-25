package uz.pcmarket.apppcmarketuz.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pcmarket.apppcmarketuz.projection.CustomCategory;
import uz.pcmarket.apppcmarketuz.projection.CustomProperty;

@RepositoryRestResource(path = "property" , excerptProjection = CustomProperty.class)
public interface PropertyRepository {
}
