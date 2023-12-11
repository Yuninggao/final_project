package com.mmall.mapper;

import com.mmall.entity.UserAddress;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Yuning
 * @since 2023-11-07
 */
public interface UserAddressMapper extends BaseMapper<UserAddress> {
    public int setDefault();

}
