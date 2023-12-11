package com.mmall.Vo;

import lombok.Data;

@Data
public class CartVo {
    private Integer id;
    private Integer productId;
    private Integer quantity;
    private Float cost;
    private Float price;
    private String name;
    private String fileName;
    private Integer stock;



}
