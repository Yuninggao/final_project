package com.mmall.Vo;

import lombok.Data;

@Data
public class OrdersDetailVo {
    private Integer id;

    private String name;
    private Float price;
    private String fileName;
    private Integer quantity;
    private  Float cost;

}
