package uz.pcmarket.apppcmarketuz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pcmarket.apppcmarketuz.entity.Basket;
import uz.pcmarket.apppcmarketuz.payload.BasketDto;
import uz.pcmarket.apppcmarketuz.payload.template.Result;
import uz.pcmarket.apppcmarketuz.service.BasketService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/basket")
public class BasketController {
    @Autowired
    BasketService basketService;

    @PostMapping
    public ResponseEntity<Result> add(@Valid @RequestBody BasketDto basketDto){
        Result result = basketService.addBasket(basketDto);
        return ResponseEntity.status(result.isSucces()?201:409).body(result);
    }

    @GetMapping
    public ResponseEntity<Page<Basket>> getAll(){
        return ResponseEntity.ok(basketService.getAllBasket());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Basket> getById(@PathVariable Integer id){
        return ResponseEntity.ok(basketService.getBasketById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> edit(@PathVariable Integer id, @Valid @RequestBody BasketDto basketDto){
        Result result = basketService.editBasket(id, basketDto);
        return ResponseEntity.status(result.isSucces()?202:409).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable Integer id){
        Result result = basketService.deleteBasket(id);
        return ResponseEntity.status(result.isSucces()?200:409).body(result);
    }

}
