package com.order.product;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: liuweijie
 * @CreateDate: 2021/2/24 17:44
 * @Version: 1.0
 * @Description:
 */
@Data
public class RedisObject implements Serializable {
    private static final long serialVersionUID = 6867080149102516470L;
    private String name;
    private String sex;
    private int age;
}
