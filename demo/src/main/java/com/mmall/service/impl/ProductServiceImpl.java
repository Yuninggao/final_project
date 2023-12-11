package com.mmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mmall.entity.Product;
import com.mmall.mapper.ProductMapper;
import com.mmall.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**

 *
 * @author Yuning
 * @since 2023-11-07
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public List<Product> findByTypeAndProductCategoryId(Integer type, Integer id){

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        String column = null;
        switch (type){
            case 1 :
                column = "categorylevelone_id";
                break;
            case 2 :
                column = "categoryleveltwo_id";
                break;
            case 3 :
                column = "categorylevelthree_id";
                break;
        }
        queryWrapper.eq(column,id);
        List<Product>productList = this.productMapper.selectList(queryWrapper);
        return productList;


    }


}
