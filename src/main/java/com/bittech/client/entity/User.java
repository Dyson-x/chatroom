package com.bittech.client.entity;

import lombok.Data;

/**
 * @author Dyson
 * @date 2019/8/18 8:29
 */
@Data
public class User {
    private Integer id;
    private String userName;
    private String password;
    private String brief;
}
