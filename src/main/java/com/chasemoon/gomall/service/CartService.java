package com.chasemoon.gomall.service;

import com.chasemoon.gomall.pojo.dto.cart.*;
import com.chasemoon.gomall.pojo.entity.Cart;
import com.chasemoon.gomall.pojo.entity.CartItem;
import com.chasemoon.gomall.repository.CartRepository;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    //购物车在用户初次将商品添加到购物车时才在数据库中添加
    public AddItemResponse addItem(int userId, AddItemRequest addItemRequest) {
        try{
            Cart cart=cartRepository.findByUserId(userId);
            //若找不到用户ID对应的购物车，则在数据库中创建对应的购物车
            if(cart==null){
                cart=new Cart();
                cart.setUserId(userId);
                List<CartItem>items=new ArrayList<>();
                cart.setItems(items);
                cartRepository.save(cart);
            }

            CartItem cartItem=new CartItem();
            cartItem.setCart(cart);
            cartItem.setQuantity(addItemRequest.getQuantity());
            cartItem.setProductId(addItemRequest.getProductId());

            //将商品添加到对应的购物车
            cart.getItems().add(cartItem);
            cartRepository.save(cart);
        }catch(Exception e){
            throw new RuntimeException(e);
        }

        return new AddItemResponse(addItemRequest.getProductId(), addItemRequest.getQuantity());
    }
    public GetCartResponse getCart(GetCartRequest getCartRequest) {
        return null;
    }
    public EmptyCartResponse emptyCart(EmptyCartRequest emptyCartRequest) {
        return null;
    }
}
