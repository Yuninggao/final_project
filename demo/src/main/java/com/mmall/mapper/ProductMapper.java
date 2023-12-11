package com.mmall.mapper;

import com.mmall.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Yuning
 * @since 2023-11-07
 */
public interface ProductMapper extends BaseMapper<Product> {

    public Integer updateStockById(Integer id, Integer stock);
    public Integer getStockById(Integer id);

}
