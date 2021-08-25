package uz.pcmarket.apppcmarketuz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pcmarket.apppcmarketuz.entity.Basket;
import uz.pcmarket.apppcmarketuz.entity.Checkout;
import uz.pcmarket.apppcmarketuz.entity.User;
import uz.pcmarket.apppcmarketuz.payload.BasketDto;
import uz.pcmarket.apppcmarketuz.payload.template.Result;
import uz.pcmarket.apppcmarketuz.repository.BasketRepository;
import uz.pcmarket.apppcmarketuz.repository.CheckoutRepository;
import uz.pcmarket.apppcmarketuz.repository.UserRepository;

import java.util.Optional;

@Service
public class BasketService {
    @Autowired
    BasketRepository basketRepository;
    @Autowired
    CheckoutRepository checkoutRepository;
    @Autowired
    UserRepository userRepository;

    /**
     * ADD NEW BASKET
     * @param basketDto
     * @return Result
     */
    public Result addBasket(BasketDto basketDto){
        Optional<Checkout> optionalCheckout = checkoutRepository.findById(basketDto.getCheckoutId());
        if (!optionalCheckout.isPresent())
            return new Result("Checkout not found", false);
        Optional<User> optionalUser = userRepository.findById(basketDto.getUserId());
        if (!optionalUser.isPresent())
            return new Result("User not found", false);
        Basket basket=new Basket(optionalCheckout.get(), optionalUser.get());
        basketRepository.save(basket);
        return new Result("Basket successfully added", true);
    }

    /**
     * get each basket on the page
     * @return Page<Basket>
     */
    public Page<Basket> getAllBasket(){
        Pageable pageable= PageRequest.of(0,10);
        return basketRepository.findAll(pageable);
    }

    /**
     * GET BASKET BY ID
     * @param id
     * @return Basket
     */
    public Basket getBasketById(Integer id){
        Optional<Basket> optionalBasket = basketRepository.findById(id);
        if (optionalBasket.isPresent())
            return optionalBasket.get();
        return null;
    }

    /**
     * EDIT BASKET
     * @param id
     * @param basketDto
     * @return Result
     */
    public Result editBasket(Integer id, BasketDto basketDto){
        Optional<Basket> optionalBasket = basketRepository.findById(id);
        if (!optionalBasket.isPresent())
            return new Result("Basket not found", false);
        Basket basket = optionalBasket.get();
        Optional<Checkout> optionalCheckout = checkoutRepository.findById(basketDto.getCheckoutId());
        if (!optionalCheckout.isPresent())
            return new Result("Checkout not found", false);
        Optional<User> optionalUser = userRepository.findById(basketDto.getUserId());
        if (!optionalUser.isPresent())
            return new Result("User not found", false);
        basket.setCheckout(optionalCheckout.get());
        basket.setUser(optionalUser.get());
        basketRepository.save(basket);
        return new Result("Basket successfully edited", true);

    }

    /**
     * DELETE BASKET BY ID
     * @param id
     * @return Result
     */
    public Result deleteBasket(Integer id){
        Optional<Basket> optionalBasket = basketRepository.findById(id);
        if (!optionalBasket.isPresent())
            return new Result("Basket not found", false);
        basketRepository.deleteById(id);
        return  new Result("Basket deleted", true);
    }
}
