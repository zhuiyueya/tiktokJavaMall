package com.chasemoon.gomall.service;

import com.chasemoon.gomall.pojo.dto.cart.*;
import com.chasemoon.gomall.pojo.dto.product.GetProductRequest;
import com.chasemoon.gomall.pojo.dto.product.GetProductResponse;
import com.chasemoon.gomall.pojo.entity.*;
import com.chasemoon.gomall.repository.jpa.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductService productService;
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


        return new GetCartResponse(cartRepository.findByUserId(getCartRequest.getUserId()));
    }
    @Transactional//有写入/删除操作时需要加上这个注解，确保方法在事务中执行，为什么这个函数去掉此注解也能正常运行？save函数自带事务
    public EmptyCartResponse emptyCart(EmptyCartRequest emptyCartRequest) {
        //cartRepository.deleteByUserId(emptyCartRequest.getUserId());
        Cart cart=cartRepository.findByUserId(emptyCartRequest.getUserId());
        cart.getItems().clear();
        cartRepository.save(cart);
        return null;
    }

    /*
     * @Description:  获取用户ID对应的购物车的具体商品信息
     * @param userID 用户表中对应的用户id
     * @return: List<OrderProduct>
     * @Author:  34362
     * @date:  2025/2/23 15:21
     */

    public List<OrderProduct>getOrderProductsByUserId(int userId){
        GetCartRequest getCartRequest=new GetCartRequest();
        getCartRequest.setUserId(userId);
        GetCartResponse getCartResponse=getCart(getCartRequest);
        Cart cart=getCartResponse.getCart();
        //商品
        List<OrderProduct>products=new ArrayList<>();
        for(CartItem cartItem:cart.getItems()){
            //获取对应商品ID的商品
            GetProductRequest getProductRequest=new GetProductRequest();
            getProductRequest.setProductId(cartItem.getProductId());
            GetProductResponse getProductResponse=productService.getProduct(getProductRequest);

            //将商品的属性和购买数量拷贝到下单商品信息类中
            OrderProduct orderProduct=new OrderProduct();
            BeanUtils.copyProperties(getProductResponse.getProduct(),orderProduct);
            orderProduct.setQuantity(cartItem.getQuantity());

            products.add(orderProduct);
        }
        return products;
    }
}
