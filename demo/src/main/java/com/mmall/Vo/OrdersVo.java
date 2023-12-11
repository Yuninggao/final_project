package com.mmall.Vo;

import com.mmall.entity.OrderDetail;
import lombok.Data;
import java.util.List;

@Data
public class OrdersVo {
    private Integer id;

    private String loginName;
    private Float cost;
    private String userAddress;
    private Integer serialnumber;
    List<OrdersDetailVo> ordersDetailList;


}
