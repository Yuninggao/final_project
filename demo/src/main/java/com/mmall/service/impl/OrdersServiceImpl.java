package com.mmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mmall.Vo.OrdersDetailVo;
import com.mmall.Vo.OrdersVo;
import com.mmall.entity.OrderDetail;
import com.mmall.entity.Orders;
import com.mmall.entity.Product;
import com.mmall.mapper.OrderDetailMapper;
import com.mmall.mapper.OrdersMapper;
import com.mmall.mapper.ProductMapper;
import com.mmall.service.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yuning
 * @since 2023-11-07
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private ProductMapper productMapper;
    @Override
    public List<OrdersVo> findAllByUserId(Integer id){
        QueryWrapper<Orders>queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",id);
        List<Orders> ordersList = this.ordersMapper.selectList(queryWrapper);
        List<OrdersVo> ordersVoList = new ArrayList<>();
        for(Orders orders : ordersList){
            OrdersVo ordersVo = new OrdersVo();
            BeanUtils.copyProperties(orders,ordersVo);

            QueryWrapper<OrderDetail> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("order_id",orders.getId());
            List<OrderDetail> orderDetailList = this.orderDetailMapper.selectList((queryWrapper1));
            List<OrdersDetailVo> ordersDetailVoList = new ArrayList<>();
            for(OrderDetail ordersDetail : orderDetailList){
                OrdersDetailVo orderDetailVo = new OrdersDetailVo();
                BeanUtils.copyProperties(ordersDetail,orderDetailVo);
                Product product = this.productMapper.selectById(ordersDetail.getProductId());
                BeanUtils.copyProperties(product,orderDetailVo);
                ordersDetailVoList.add(orderDetailVo);
            }
            ordersVo.setOrdersDetailList(ordersDetailVoList);
            ordersVoList.add(ordersVo);

        }

        return ordersVoList;

    }

}
