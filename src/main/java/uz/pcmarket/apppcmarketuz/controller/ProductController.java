package uz.pcmarket.apppcmarketuz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pcmarket.apppcmarketuz.entity.Product;
import uz.pcmarket.apppcmarketuz.payload.ProductDto;
import uz.pcmarket.apppcmarketuz.payload.template.Result;
import uz.pcmarket.apppcmarketuz.service.ProductService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    /**
     * ADD NEW PRODUCT
     * @param productDto
     * @return RequestEntity<Result>
     */
    @PostMapping
    public ResponseEntity<Result> add(@Valid @RequestBody ProductDto productDto){
        Result result = productService.addProduct(productDto);
        return ResponseEntity.status(result.isSucces()?201:409).body(result);
    }

    /**
     * GET ALL PRODUCT
     * @return ResponseEntity<Page<Product>>
     */
    @GetMapping
    public  ResponseEntity<Page<Product>> getAll(){
        Page<Product> allProduct = productService.getAllProduct();
        return ResponseEntity.ok(allProduct);
    }

    /**
     * GET PRODUCT BY ID
     * @param id
     * @return ResponseEntity<Product>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Integer id){
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    /**
     * EDIT PRODUCT
     * @param id
     * @param productDto
     * @return ResponseEntity<Result>
     */
    @PutMapping("/{id}")
    public ResponseEntity<Result> edit(@PathVariable Integer id, @Valid @RequestBody ProductDto productDto){
        Result result = productService.editProduct(id, productDto);
        return ResponseEntity.status(result.isSucces()? 202:409).body(result);
    }

    /**
     * DELETE PRODUCT BY ID
     * @param id
     * @return ResponseEntity<Result>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable Integer id){
        Result result = productService.delete(id);
        return ResponseEntity.status(result.isSucces()?200:409).body(result);
    }
}
