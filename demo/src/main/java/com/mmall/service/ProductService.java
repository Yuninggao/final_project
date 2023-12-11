package com.mmall.service;

import com.mmall.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**

 *
 * @author Yuning
 * @since 2023-11-07
 */
public interface ProductService extends IService<Product> {
    public List<Product> findByTypeAndProductCategoryId(Integer Type, Integer Id);


}
