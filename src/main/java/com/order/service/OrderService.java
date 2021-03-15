package com.order.service;

import com.order.entity.Order;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: liuweijie
 * @CreateDate: 2021/2/26 16:41
 * @Version: 1.0
 * @Description:
 */
@Service
@Transactional
public class OrderService {
    @Autowired
    private SqlSession sqlSession;
    public static final String SQLNAME_SEPARATOR = ".";

    public Order queryOne(String id) {

        Map<String,Object> param = new HashMap<>();
        param.put("id",id);
        Order order = sqlSession.selectOne(Order.class.getName()+SQLNAME_SEPARATOR+"findById",param);
        return order;
    }
}
