package com.mmall.mapper;

import com.mmall.entity.Cart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Yuning
 * @since 2023-11-07
 */
public interface CartMapper extends BaseMapper<Cart> {

    public int update(Integer id,Integer quantity,Float cost);
    public Float getCostByUserId(Integer id);

}
