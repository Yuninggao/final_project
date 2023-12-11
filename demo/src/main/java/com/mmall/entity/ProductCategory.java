package com.mmall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Yuning
 * @since 2023-11-07
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class ProductCategory implements Serializable {

    private static final long serialVersionUID=1L;


        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;


      private String name;


      private Integer parentId;


      private Integer type;


}
