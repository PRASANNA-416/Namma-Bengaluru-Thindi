package com.prasanna.Food.controller;

import com.prasanna.Food.model.CartItem;
import com.prasanna.Food.model.Order;
import com.prasanna.Food.model.User;
import com.prasanna.Food.request.AddCartItemRequest;
import com.prasanna.Food.request.OrderRequest;
import com.prasanna.Food.service.OrderService;
import com.prasanna.Food.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @Autowired
    private UserService userService;


    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest req, @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        Order order = orderService.createOrder(req, user);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/order/user")
    public ResponseEntity<List<Order>> getOrderHistory(@RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        List<Order> orders = orderService.getUserOrders(user.getId());

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

}
