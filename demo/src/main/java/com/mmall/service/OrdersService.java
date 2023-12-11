package com.mmall.service;

import com.mmall.Vo.OrdersVo;
import com.mmall.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 *
 *
 * @author Yuning
 * @since 2023-11-07
 */
public interface OrdersService extends IService<Orders> {
    public List <OrdersVo> findAllByUserId(Integer id);

}
