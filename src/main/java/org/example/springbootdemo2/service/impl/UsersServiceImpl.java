package org.example.springbootdemo2.service.impl;

import org.example.springbootdemo2.entity.Users;
import org.example.springbootdemo2.mapper.UsersMapper;
import org.example.springbootdemo2.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ASUS
 * @since 2026-06-01
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

}
