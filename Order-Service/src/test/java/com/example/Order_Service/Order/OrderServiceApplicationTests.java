//package com.example.Order_Service.Order;
//
//import com.example.Order_Service.Order.models.Dto.RequestDto.OrderItemsRequestDto;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//
//class NormalTest {
//  String json =
//      "{\n"
//          + "    \"productId\": 1,\n"
//          + "    \"quantity\": 2,\n"
//          + "    \"basePrice\": 150000\n"
//          + "}";
//
//  ObjectMapper mapper = new ObjectMapper();
//	@Test
//	void contextLoads() {
//        System.out.println(json);
//        OrderItemsRequestDto dto = mapper.convertValue(json, OrderItemsRequestDto.class);
//
//        System.out.println(dto.toString());
//	}
//
//}
