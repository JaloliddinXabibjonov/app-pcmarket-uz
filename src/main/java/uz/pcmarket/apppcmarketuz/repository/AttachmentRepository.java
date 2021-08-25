package uz.pcmarket.apppcmarketuz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pcmarket.apppcmarketuz.entity.Attachment;
import uz.pcmarket.apppcmarketuz.projection.CustomCategory;


public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {

}
