package uz.pcmarket.apppcmarketuz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pcmarket.apppcmarketuz.entity.Basket;
import uz.pcmarket.apppcmarketuz.entity.BasketProduct;
import uz.pcmarket.apppcmarketuz.entity.Product;
import uz.pcmarket.apppcmarketuz.payload.BasketProductDto;
import uz.pcmarket.apppcmarketuz.payload.template.Result;
import uz.pcmarket.apppcmarketuz.repository.BasketProductRepository;
import uz.pcmarket.apppcmarketuz.repository.BasketRepository;
import uz.pcmarket.apppcmarketuz.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BasketProductService {
    @Autowired
    BasketProductRepository basketProductRepository;

    @Autowired
    BasketRepository basketRepository;
    @Autowired
    ProductRepository productRepository;

    /**
     * ADD BASKET PRODUCT
     * @param basketProductDto
     * @return Result
     */
    public Result add(BasketProductDto basketProductDto){
        BasketProduct basketProduct=new BasketProduct();
        basketProduct.setAmount(basketProductDto.getAmount());
        Optional<Product> optionalProduct = productRepository.findById(basketProductDto.getProductId());
        //CHECK PRODUCT BY ID
        if (!optionalProduct.isPresent()){
            return new Result("Product not found", false);
        }
        basketProduct.setSubtotal(basketProduct.getAmount()*optionalProduct.get().getPrice());
        //CHECK BASKET BY ID
        Optional<Basket> optionalBasket = basketRepository.findById(basketProductDto.getBasketId());
        if (!optionalBasket.isPresent())
            return new Result("Basket not found", false);
        basketProduct.setBasket(optionalBasket.get());
        basketProductRepository.save(basketProduct);
        return new Result("Basket product saved", true);
    }

    /**
     * GET ALL BASKETPRODUCT BY PAGE
     * @return Page<BasketProduct>
     */
    public Page<BasketProduct> getAll(){
        Pageable pageable= PageRequest.of(0,10);
        return basketProductRepository.findAll(pageable);
    }

    /**
     * GET BASKETPRODUCT BY ID
     * @param id
     * @return BasketProduct
     */
    public BasketProduct getById(Integer id){
        Optional<BasketProduct> optionalBasketProduct = basketProductRepository.findById(id);
        if (optionalBasketProduct.isPresent())
            return optionalBasketProduct.get();
        return null;
    }

    /**
     * EDIT BASKETPRODUCT BY ID
     * @param id
     * @param basketProductDto
     * @return Result
     */
    public Result edit(Integer id, BasketProductDto basketProductDto){

        //CHECK BASKET PRODUCT  BY ID
        Optional<BasketProduct> optionalBasketProduct = basketProductRepository.findById(id);
        if (!optionalBasketProduct.isPresent())
            return new Result("Basket product not found", false);

        BasketProduct basketProduct = optionalBasketProduct.get();
        basketProduct.setAmount(basketProductDto.getAmount());

        //CHECK PRODUCT BY ID
        Optional<Product> optionalProduct = productRepository.findById(basketProductDto.getProductId());
        if (!optionalProduct.isPresent()){
            return new Result("Product not found", false);
        }

        //CHECK BASKET BY ID
        basketProduct.setSubtotal(basketProduct.getAmount()*optionalProduct.get().getPrice());
        Optional<Basket> optionalBasket = basketRepository.findById(basketProductDto.getBasketId());
        if (!optionalBasket.isPresent())
            return new Result("Basket not found", false);
        basketProduct.setBasket(optionalBasket.get());
        basketProductRepository.save(basketProduct);
        return new Result("Basket product edited", true);
    }

    /**
     * DELETE
     * @param id
     * @return Result
     */
    public Result delete(Integer id){
        Optional<BasketProduct> optionalBasketProduct = basketProductRepository.findById(id);
        if (!optionalBasketProduct.isPresent())
            return new Result("Basket Product not found", false);
        basketProductRepository.deleteById(id);
        return new Result("Basket product deleted", true);
    }
}
