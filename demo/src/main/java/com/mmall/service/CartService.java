package com.mmall.service;

import com.mmall.Vo.CartVo;
import com.mmall.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mmall.entity.Orders;
import com.mmall.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**

 *
 * @author Yuning
 * @since 2023-11-07
 */
public interface CartService extends IService<Cart> {

    public Boolean addCart(Cart cart);
    public List<CartVo> findVoListByUserId(Integer userId);
    public Boolean update(Integer id,Integer quantity,Float cost);
    public Boolean delete(Integer id);
    public Orders commit(String user_address, String address, String remark, User user);







}
