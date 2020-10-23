package com.daiwei.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daiwei.bean.Dept;
import com.daiwei.mapper.DeptMapper;
import com.daiwei.service.DeptService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author daiwei
 * @since 2020-10-21
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

}
