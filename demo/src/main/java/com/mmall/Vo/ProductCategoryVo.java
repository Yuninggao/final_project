package com.mmall.Vo;
import com.mmall.entity.Product;
import com.mmall.entity.ProductCategory;
import lombok.Data;

import java.util.List;

@Data

public class ProductCategoryVo {
    private Integer id;
    private String name;
    private Integer parentId;
    private List<ProductCategoryVo> children;
    private List<Product>productList;

    public ProductCategoryVo(ProductCategory productCategory){
        this.id = productCategory.getId();
        this.name = productCategory.getName();
        this.parentId = productCategory.getParentId();
    }



}
