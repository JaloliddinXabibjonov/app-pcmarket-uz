package uz.pcmarket.apppcmarketuz.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pcmarket.apppcmarketuz.projection.CustomCategory;
import uz.pcmarket.apppcmarketuz.projection.CustomCharacter;

@RepositoryRestResource(path= "character" , excerptProjection = CustomCharacter.class)
public interface CharacterRepository {
}
