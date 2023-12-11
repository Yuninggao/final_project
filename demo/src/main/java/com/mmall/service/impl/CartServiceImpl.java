package com.mmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mmall.Exception.mmallException;
import com.mmall.Result.ResponseEnum;
import com.mmall.Vo.CartVo;
import com.mmall.entity.*;
import com.mmall.mapper.*;
import com.mmall.service.CartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 *
 * @author Yuning
 * @since 2023-11-07
 */
@Service
@Slf4j

public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private UserAddressMapper userAddressMapper;




    @Override
    @Transactional

    public Boolean addCart(Cart cart){
        int insert = this.cartMapper.insert(cart);
        if(insert != 1){
            throw new mmallException(ResponseEnum.CART_ADD_ERROR);
        }

        Integer stock = this.productMapper.getStockById(cart.getProductId());
        if(stock == null){
            log.info("product not exist");
            throw new mmallException(ResponseEnum.PRODUCT_NOT_EXIST);
        }
        if(stock == 0){
            log.info("stock error");
            throw new mmallException(ResponseEnum.STOCK_ERROR);
        }
        Integer newStock = stock - cart.getQuantity();
        if(newStock < 0){
            log.info("stock error");
            throw new mmallException(ResponseEnum.STOCK_ERROR);
        }
        this.productMapper.updateStockById(cart.getProductId(),newStock);
        return true;


    }

    @Override
    public List<CartVo> findVoListByUserId(Integer userId){
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<Cart> CartList = this.cartMapper.selectList(queryWrapper);
        List<CartVo> CartListVo = new ArrayList<>();

        for(Cart cart : CartList){
            Product product = this.productMapper.selectById(cart.getProductId());
            CartVo cartVo = new CartVo();
            BeanUtils.copyProperties(product,cartVo);
            BeanUtils.copyProperties(cart,cartVo);
            CartListVo.add(cartVo);
        }
        return CartListVo;



    }
    @Override
    @Transactional
    public Boolean update(Integer id,Integer quantity,Float cost){
        Cart cart = this.cartMapper.selectById(id);
        Integer oldQuantity = cart.getQuantity();
        if(quantity.equals(oldQuantity)){
            log.info("Update stock error");
            throw new mmallException(ResponseEnum.UPDATE_STOCK_ERROR);
        }
        Integer stock = this.productMapper.getStockById(cart.getProductId());
        Integer newStock = stock - (quantity - oldQuantity);
        if(newStock < 0 ){
            log.info("stock error");
            throw new mmallException(ResponseEnum.STOCK_ERROR);
        }

        Integer integer = this.productMapper.updateStockById(cart.getProductId(), stock);
        if( integer != 1){
            log.info("update stock error");
            throw new mmallException(ResponseEnum.UPDATE_STOCK_ERROR);

        }


        int update = this.cartMapper.update(id, quantity, cost);
        if( update!= 1){
            log.info("update error");
            throw new mmallException(ResponseEnum.UPDATE_CART_ERROR);

        }


        return true;
    }
    @Override
    @Transactional
    public Boolean delete(Integer id){
        Cart cart = this.cartMapper.selectById(id);
        Integer stock = this.productMapper.getStockById(cart.getProductId());
        Integer newStock = stock + (cart.getQuantity());
        Integer integer = this.productMapper.updateStockById(cart.getProductId(),newStock);
        if(integer != 1){
            log.info("stock delete error");
            throw new mmallException(ResponseEnum.UPDATE_STOCK_ERROR);

        }
        int i = this.cartMapper.deleteById(id);
        if(i != 1){
            log.info("stock delete error");
            throw new mmallException(ResponseEnum.DELETE_STOCK_ERROR);

        }
        return true;

    }
    @Override
    @Transactional
    public Orders commit(String user_address,String address, String remark, User user){
        if(!user_address.equals("newAddress")){
            address = user_address;
        }else {
            int i = this.userAddressMapper.setDefault();
            if( i == 0){
                log.info("isDefault error");
                throw new mmallException(ResponseEnum.USER_ADDRESS_SET_DEFAULT_ERROR);
            }
            UserAddress userAddress1 = new UserAddress();
            userAddress1.setIsdefault(1);
            userAddress1.setUserId(user.getId());
            userAddress1.setAddress(address);
            userAddress1.setRemark(remark);
            int insert = this.userAddressMapper.insert(userAddress1);
            if(insert == 0){
                log.info("userAddress error");
                throw new mmallException(ResponseEnum.USER_ADDRESS_ADD_ERROR);
            }

        }

        Orders orders = new Orders();
        orders.setUserId(user.getId());
        orders.setLoginName(user.getLoginName());
        orders.setUserAddress(address);
        orders.setCost(this.cartMapper.getCostByUserId(user.getId()));

        String seriaNumber = null;

        try {
            StringBuffer result = new StringBuffer();
            for(int i=0;i<32;i++) {
                result.append(Integer.toHexString(new Random().nextInt(16)));
            }
            seriaNumber =  result.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        orders.setSerialnumber(seriaNumber);
        int insert = this.ordersMapper.insert(orders);
        if(insert != 1){
            log.info("checkout error");
            throw new mmallException(ResponseEnum.CHECK_OUT_ERROR);
        }

        QueryWrapper <Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",user.getId());
        List<Cart> carts = this.cartMapper.selectList(queryWrapper);
        for(Cart cart:carts) {
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(cart, orderDetail);
            orderDetail.setOrderId(orders.getId());
            int insert1 = this.orderDetailMapper.insert(orderDetail);
            if (insert1 == 0) {
                log.info("checkout error");
                throw new mmallException(ResponseEnum.CHECK_OUT_ERROR);

            }
        }

        QueryWrapper<Cart>queryWrapper1 = new QueryWrapper<>();
        queryWrapper.eq("user_id",user.getId());
        int delete = this.cartMapper.delete(queryWrapper1);
        if(delete == 0){
            log.info("delete error");
            throw new mmallException(ResponseEnum.DELETE_ERROR);
        }

        return orders;
    }


}
