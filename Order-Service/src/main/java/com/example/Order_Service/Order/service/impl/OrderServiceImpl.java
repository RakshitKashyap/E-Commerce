package com.example.Order_Service.Order.service.impl;

import com.example.Order_Service.Order.exceptions.CustomException;
import com.example.Order_Service.Order.models.Dto.RequestDto.OrderItemsRequestDto;
import com.example.Order_Service.Order.models.Dto.RequestDto.OrderRequestDto;
import com.example.Order_Service.Order.models.Dto.ResponseDto.ResponseData;
import com.example.Order_Service.Order.models.entity.OrderItems;
import com.example.Order_Service.Order.models.entity.Orders;
import com.example.Order_Service.Order.repository.OrderRepository;
import com.example.Order_Service.Order.service.OrderItemService;
import com.example.Order_Service.Order.service.OrderService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.example.Order_Service.Order.utility.Enum.CheckedException;
import com.example.Order_Service.Order.utility.Enum.OrderStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemService itemService;

    ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public ResponseData getAllOrders() {
        List<Orders> ordersList = orderRepository.findAll();
        return new ResponseData(200, ordersList.stream().filter(order -> order.isStatus()).collect(Collectors.toList()));
    }

    @Override
    public ResponseData findOrderByOrderId(String orderId) {
        if(StringUtils.isEmpty(orderId)){
            return new ResponseData(404, null);
        }
        Orders orders = orderRepository.findByIdAndStatus(Long.parseLong(orderId.trim()), Boolean.TRUE);
        if(orders ==null){
            return new ResponseData(404, null);
        }
        return new ResponseData(200, orders) ;
    }

    @Override
    public ResponseData findOrderByStatusId(int orderStatusId) {

        List<Orders> ordersList = orderRepository.findAll();
    return new ResponseData(
        200,
        ordersList.stream()
            .filter(order -> order.getOrderStatus().getId() == orderStatusId)
            .collect(Collectors.toList()));
    }

    @Override
    public ResponseData createNewOrder(Object requestBody) {
        OrderRequestDto dto  = objectMapper.convertValue(requestBody, OrderRequestDto.class);
        Orders order = new Orders();
        List<OrderItems> itemsList = new ArrayList<>();
        Double amount = Double.valueOf(0);
        for (OrderItemsRequestDto itemsDto:dto.getOrderItems()){
            OrderItems items = itemService.fetchItemByItemId(itemsDto.getItemId());
            amount+=(items.getBasePrice()*items.getQuantity());
            itemsList.add(items);
        }
        order.setOrderItems(itemsList);

        order.setOrderedBy("user");
        order.setOrderUUID("user");
        order.setOrderDateTime(LocalDateTime.now());
        order.setTotalAmount(amount);
        order.setOrderStatus(OrderStatus.INITIATED);
        order.setPaymentId(null);
        order.setStatus(Boolean.TRUE);
        order.setCreatedBy("user");
        order.setModifiedBy("user");
        order.setCreatedOn(LocalDateTime.now());
        order.setModifiedOn(LocalDateTime.now());
        order =  orderRepository.save(order);
        return new ResponseData(201, order);
    }

    @Override
    public ResponseData updateOrder(String orderId, Orders orders) {
        Orders orders1 = objectMapper.convertValue(findOrderByOrderId(orderId).getData(), Orders.class);
        orders1.setOrderedBy("user");
        orders1.setOrderUUID(UUID.randomUUID().toString());
        orders1.setPaymentId(orders.getPaymentId());
        orders1.setOrderStatus(orders.getOrderStatus());
        orders1.setOrderDateTime(orders.getOrderDateTime());
        orders1.setTotalAmount(orders.getTotalAmount());
        orders1.setModifiedBy("user");
        orders1.setModifiedOn(LocalDateTime.now());
        orderRepository.save(orders1);
        return new ResponseData(200, orders1);
    }

    @Override
    public ResponseData deleteOrder(String orderId) {
        // :TODO go through this

        Orders orders = objectMapper.convertValue(findOrderByOrderId(orderId).getData(), Orders.class);
        if(orders ==null)
            return null;
        orders.setStatus(false);
        orderRepository.save(orders);
        return null;
    }

    @Override
    public Orders saveOrderDetail(Orders orders) {
        try{
           orders = orderRepository.save(orders);
        }catch (Exception e){
            throw new CustomException(CheckedException.DATA_NOT_SAVED);
        }
        return orders;
    }
}
