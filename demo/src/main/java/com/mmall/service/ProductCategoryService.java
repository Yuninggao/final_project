package com.mmall.service;

import com.mmall.Vo.ProductCategoryVo;
import com.mmall.entity.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 *
 *
 * @author Yuning
 * @since 2023-11-07
 */
public interface ProductCategoryService extends IService<ProductCategory> {
    public List<ProductCategoryVo> buildProductCategoryMenue();
    public List<ProductCategoryVo> findAllProductByCategoryLevelOne();

}
