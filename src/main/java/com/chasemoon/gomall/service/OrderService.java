package com.chasemoon.gomall.service;

import com.chasemoon.gomall.common.Constants;
import com.chasemoon.gomall.pojo.dto.cart.EmptyCartRequest;
import com.chasemoon.gomall.pojo.dto.order.*;
import com.chasemoon.gomall.pojo.entity.*;
import com.chasemoon.gomall.repository.jpa.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartService cartService;
    @Autowired
    private AddressRepository addressRepository;

    public PlaceOrderResponse placeOrder(PlaceOrderRequest placeOrderRequest,int userId) {
        Order order = new Order();
        //获取设置order必要的信息
        Date currentDate = new Date();
        List<CartItem> items=cartRepository.findByUserId(userId).getItems();
        List<OrderItem>orderItems=new ArrayList<OrderItem>();
        for(CartItem cartItem:items){
            OrderItem orderItem = FromCartItemToOrderItem(cartItem,order);
            orderItems.add(orderItem);
        }
        Address address=addressRepository.getByAddressId(placeOrderRequest.getAddressId());
        OrderAddress orderAddress=new OrderAddress();
        BeanUtils.copyProperties(address,orderAddress);

        //设置对应的字段
        order.setOrderId(UUID.randomUUID().toString().replace("-",""));

        order.setCreated_at(currentDate);

        order.setEmail(userRepository.getUsersByUserId(userId).getEmail());

        order.setOrderItems(orderItems);

        order.setAddress(orderAddress);

        order.setOrderStatus(Order.OrderStatus.PENDING_PAYMENT);

        //将购物车清空
        cartService.emptyCart(new EmptyCartRequest(userId));
        orderRepository.save(order);

        PlaceOrderResponse placeOrderResponse=new PlaceOrderResponse();
        placeOrderResponse.setOrder(order);
        return placeOrderResponse;
    }
    public OrderItem FromCartItemToOrderItem(CartItem cartItem,Order order) {
        OrderItem orderItem = new OrderItem();

        orderItem.setOrder(order);
        orderItem.setQuantity(cartItem.getQuantity());
        orderItem.setProductId(cartItem.getProductId());
        orderItem.setCost(productRepository.getProductByProductId(cartItem.getProductId()).getPrice());
        return orderItem;
    }
    //标记订单为已支付
    public MarkOrderPaidResponse markOrderPaid(MarkOrderPaidRequest markOrderPaidRequest) {
        log.info("markOrderPaidRequest:{}",markOrderPaidRequest);

        User user=userRepository.getUsersByUserId(markOrderPaidRequest.getUserId());
        Order order=orderRepository.getOrderByOrderId(markOrderPaidRequest.getOrderId());
        try {

            if (user != null && order != null && user.getEmail().equals(order.getEmail())) {
                order.setOrderStatus(Order.OrderStatus.PAID);
                orderRepository.save(order);
            } else {
                throw new RuntimeException(Constants.MARK_ORDER_PAIED_FAILED);
            }
        }catch(Exception e){
            throw new RuntimeException("markOrderPaidFail:"+e.getMessage());
        }
        return null;
    }
    public ListOrderResponse listOrder(ListOrderRequest listOrderRequest) {
        User user=userRepository.getUsersByUserId(listOrderRequest.getUserId());
        List<Order>orders=orderRepository.getAllByEmail(user.getEmail());
        ListOrderResponse listOrderResponse=new ListOrderResponse();
        listOrderResponse.setOrders(orders);
        return listOrderResponse;
    }
}
