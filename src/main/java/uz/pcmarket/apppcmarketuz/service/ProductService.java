package uz.pcmarket.apppcmarketuz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pcmarket.apppcmarketuz.entity.Attachment;
import uz.pcmarket.apppcmarketuz.entity.BasketProduct;
import uz.pcmarket.apppcmarketuz.entity.Category;
import uz.pcmarket.apppcmarketuz.entity.Product;
import uz.pcmarket.apppcmarketuz.payload.ProductDto;
import uz.pcmarket.apppcmarketuz.payload.template.Result;
import uz.pcmarket.apppcmarketuz.repository.AttachmentRepository;
import uz.pcmarket.apppcmarketuz.repository.BasketProductRepository;
import uz.pcmarket.apppcmarketuz.repository.CategoryRepository;
import uz.pcmarket.apppcmarketuz.repository.ProductRepository;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BasketProductRepository basketProductRepository;
    /**
     * add new product
     * @param productDto
     * @return Result
     */
    public Result addProduct(ProductDto productDto){
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent())
            return new Result("File not found",false);
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new Result("Category not found", false);
        Optional<BasketProduct> optionalBasketProduct = basketProductRepository.findById(productDto.getBasketProductId());
        if (!optionalBasketProduct.isPresent())
            return new Result("BasketProduct not found", false);
        Product product=new Product(productDto.getName(), productDto.getPrice(), optionalCategory.get(), optionalAttachment.get(), optionalBasketProduct.get());
        productRepository.save(product);
        return new Result("Product saved", true);
    }

    /**
     * GET ALL PRODUCTS BY PAGE
     * @return Page<Product>
     */
    public Page<Product> getAllProduct(){
        Pageable pageable= PageRequest.of(0,10);
        return productRepository.findAll(pageable);
    }

    /**
     * GET PRODUCT BY ID
     * @param id
     * @return Product
     */
    public Product getProductById(Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent())
            return optionalProduct.get();
        return null;
    }

    /**
     * EDIT PRODUCT
     * @param id
     * @param productDto
     * @return Result
     */
    public Result editProduct(Integer id, ProductDto productDto){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent())
            return new Result("Product not found", false);
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent())
            return new Result("File not found", false);
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new Result("Category not found", false);
        Optional<BasketProduct> optionalBasketProduct = basketProductRepository.findById(productDto.getBasketProductId());
        if (!optionalBasketProduct.isPresent())
            return new Result("BasketProduct not found", false);
        Product product = optionalProduct.get();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setAttachment(optionalAttachment.get());
        product.setCategory(optionalCategory.get());
        product.setBasketProduct(optionalBasketProduct.get());
        productRepository.save(product);
        return new Result("Product edited", true);
    }

    /**
     * DELETE PRODUCT
     * @param id
     * @return Result
     */
    public Result delete(Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent())
            return new Result("Product not found", false);
        productRepository.deleteById(id);
        return new Result("Product deleted", true);
    }
}
