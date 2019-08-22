package com.bittech.vo;


import lombok.Data;

/**
 * @author Dyson
 * @date 2019/8/19 12:19
 * 服务端与客户端通信载体,规定的格式
 */
@Data
public class MessageVo {
    /**
     * 告知服务端要进行的操作，eg：1表示新用户注册，2表示私聊，3表示群聊等
     */
    private Integer type;

    /**
     * 服务端与客户端聊天具体内容
     */
    private  String content;

    /**
     * 聊天信息发送的目标客户端名称
     */
    private String to;
}
