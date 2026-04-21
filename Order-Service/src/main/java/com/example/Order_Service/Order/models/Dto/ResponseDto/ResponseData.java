package com.example.Order_Service.Order.models.Dto.ResponseDto;

import com.example.Order_Service.Order.models.entity.Orders;
import lombok.Data;

import java.util.List;

@Data
public class ResponseData {
    private int status;
    private Object data;

    public ResponseData(int status, Object data) {
        this.status = status;
        this.data = data;
    }


}
