package com.example.Order_Service.Order.service.impl;

import com.example.Order_Service.Order.exceptions.CustomException;
import com.example.Order_Service.Order.models.Dto.RequestDto.OrderItemsRequestDto;
import com.example.Order_Service.Order.models.Dto.RequestDto.OrderRequestDto;
import com.example.Order_Service.Order.models.Dto.ResponseDto.ResponseData;
import com.example.Order_Service.Order.models.entity.OrderItems;
import com.example.Order_Service.Order.models.entity.Orders;
import com.example.Order_Service.Order.repository.OrderItemsRepository;
import com.example.Order_Service.Order.service.OrderItemService;
import com.example.Order_Service.Order.utility.Enum.CheckedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public ResponseData addNewItemToCart(Object requestDto, String cartId) {
        if(StringUtils.isEmpty(cartId)){

        }
        return null;
    }

    @Override
    public ResponseData fetchAllItemsForCart(String CartId) {
        return null;
    }

    @Override
    public ResponseData fetchItemByCartIdAndItemId(String CartId, String itemId) {
        return null;
    }

    @Override
    public ResponseData updateItem(String CartId, String itemId, Object requestBody) {
        return null;
    }

    @Override
    public ResponseData removeItemFromCart(String CartId, String itemId) {
        return null;
    }

    @Override
    public OrderItems fetchItemByItemId(Long itemId) {
        return null;
    }
}
