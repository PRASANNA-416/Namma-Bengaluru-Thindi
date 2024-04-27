package com.prasanna.Food.service;

import com.prasanna.Food.model.Cart;
import com.prasanna.Food.model.CartItem;
import com.prasanna.Food.model.Food;
import com.prasanna.Food.model.User;
import com.prasanna.Food.repository.CartItemRepository;
import com.prasanna.Food.repository.CartRepository;
import com.prasanna.Food.repository.FoodRepository;
import com.prasanna.Food.request.AddCartItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CartServiceImp implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemRepository cartItemRepository;


    @Autowired
    private FoodService foodService;


    @Override
    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.findFoodById(req.getFoodId());

        Cart cart = cartRepository.findByCustomerId(user.getId());
        for(CartItem item : cart.getItem()){

            if(item.getFood().equals(food)){
                int newQuantity = (int) (item.getQuantity() + req.getQuantity());
                return updateCartItemQuantity(item.getId(), newQuantity);
            }
        }

        CartItem newitem = new CartItem();
        newitem.setFood(food);
        newitem.setCart(cart);
        newitem.setQuantity(Math.toIntExact(req.getQuantity()));
        newitem.setIngredients(req.getIngredients());
        newitem.setTotalPrice(req.getQuantity() * food.getPrice());

        CartItem savedcardItem = cartItemRepository.save(newitem);
        cart.getItem().add(savedcardItem);


        return savedcardItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {

        Optional<CartItem> cartItemoptinal = cartItemRepository.findById(cartItemId);
        if(cartItemoptinal.isEmpty()){
            throw new Exception("Cart Item not found");
        }

        CartItem cartItem = cartItemoptinal.get();
        cartItem.setQuantity(quantity);
        cartItem.setTotalPrice(cartItem.getFood().getPrice() * quantity);

        return cartItemRepository.save(cartItem);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartRepository.findByCustomerId(user.getId());

        Optional<CartItem> cartItemoptinal = cartItemRepository.findById(cartItemId);
        if(cartItemoptinal.isEmpty()){
            throw new Exception("Cart Item not found");
        }

        CartItem cartItem = cartItemoptinal.get();
        cart.getItem().remove(cartItem);

        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotals(Cart cart) throws Exception {

        Long total = 0L;
        for(CartItem item : cart.getItem()){
            total += item.getFood().getPrice() * item.getQuantity();
        }

        return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {

        Optional<Cart> cartoptinal = cartRepository.findById(id);
        if(cartoptinal.isEmpty()){
            throw new Exception("Cart not found with id: " + id);
        }
        return cartoptinal.get();
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {

       // User user = userService.findUserByJwtToken(jwt);

        Cart cart =  cartRepository.findByCustomerId(userId);
        cart.setTotal(calculateCartTotals(cart));

        return cart;
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {


        //User user = userService.findUserByJwtToken(user);
        Cart cart = findCartByUserId(userId);
        cart.getItem().clear();
        return cartRepository.save(cart);
    }
}
