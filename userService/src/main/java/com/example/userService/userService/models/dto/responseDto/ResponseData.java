package com.example.userService.userService.models.dto.responseDto;

import lombok.Data;

@Data
public class ResponseData {
    private int status;
    private Object data;

    public ResponseData(int status, Object data) {
        this.status = status;
        this.data = data;
    }


}
