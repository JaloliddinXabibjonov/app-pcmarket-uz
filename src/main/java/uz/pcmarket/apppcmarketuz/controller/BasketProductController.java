package uz.pcmarket.apppcmarketuz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pcmarket.apppcmarketuz.entity.BasketProduct;
import uz.pcmarket.apppcmarketuz.payload.BasketProductDto;
import uz.pcmarket.apppcmarketuz.payload.template.Result;
import uz.pcmarket.apppcmarketuz.service.BasketProductService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/basketProduct")
public class BasketProductController {
    @Autowired
    BasketProductService basketProductService;

    /**
     * ADD NEW BASKET PRODUCT
     * @param basketProductDto
     * @return ResponseEntity<Result>
     */
    @PostMapping
    public ResponseEntity<Result> add(@Valid @RequestBody BasketProductDto basketProductDto){
        Result result = basketProductService.add(basketProductDto);
        return ResponseEntity.status(result.isSucces()?201:409).body(result);
    }

    /**
     * GET ALL BASKET PRODUCT BY PAGE
     * @return ResponseEntity<Page<BasketProduct>>
     */
    @GetMapping
    public ResponseEntity<Page<BasketProduct>> getAllByPage(){
        return ResponseEntity.ok(basketProductService.getAll());
    }

    /**
     * GET BASKET PRODUCT BY ID
     * @param id
     * @return  ResponseEntity<BasketProduct>
     */
    @GetMapping("/{id}")
    public ResponseEntity<BasketProduct> getById(@PathVariable Integer id){
        BasketProduct basketProduct = basketProductService.getById(id);
        return ResponseEntity.ok(basketProduct);
    }

    /**
     * EDIT BASKET PRODUCT
     * @param id
     * @param basketProductDto
     * @return ResponseEntity<Result>
     */
    @PutMapping("/{id}")
    public ResponseEntity<Result> edit(@PathVariable Integer id, @Valid @RequestBody BasketProductDto basketProductDto){
        Result result = basketProductService.edit(id, basketProductDto);
        return ResponseEntity.status(result.isSucces()?202:409).body(result);
    }

    /**
     * DELETE BASKET PRODUCT BY ID
     * @param id
     * @return ResponseEntity<Result>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable Integer id){
        Result result = basketProductService.delete(id);
        return ResponseEntity.status(result.isSucces()?202:409).body(result);
    }
}
