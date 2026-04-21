package com.example.Order_Service.Order.service;


import com.example.Order_Service.Order.models.Dto.ResponseDto.ResponseData;
import com.example.Order_Service.Order.models.entity.OrderItems;

public interface OrderItemService {
    ResponseData addNewItemToCart(Object requestDto, String CartId);

    ResponseData fetchAllItemsForCart(String CartId);

    ResponseData fetchItemByCartIdAndItemId(String CartId, String itemId);

    ResponseData updateItem(String CartId, String itemId, Object requestBody);

    ResponseData removeItemFromCart(String CartId, String itemId);

    OrderItems fetchItemByItemId(Long itemId);
}

// 2:19:35