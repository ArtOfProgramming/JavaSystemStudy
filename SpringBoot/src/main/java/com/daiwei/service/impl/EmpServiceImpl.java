package com.daiwei.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daiwei.bean.Emp;
import com.daiwei.mapper.EmpMapper;
import com.daiwei.service.EmpService;
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
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements EmpService {

}
