package org.example.springbootdemo2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.springbootdemo2.entity.Users;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ASUS
 * @since 2026-06-01
 */
@Mapper
public interface UsersMapper extends BaseMapper<Users> {

}
