package com.bittech.client.service;

import com.bittech.util.CommUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Properties;

/**
 * @author Dyson
 * @date 2019/8/19 9:39
 * 封装客户端与服务端建立得连接以及输入，输出流
 */
public class ConnectServer {
    private static final int PORT;
    private static final String IP;
    private Socket client;
    private InputStream in;
    private OutputStream out;
    //建立连接的两个参数
    static {
        Properties pros = CommUtil.loadProperties("socket.properties");
        PORT = Integer.parseInt(pros.getProperty("PORT"));
        IP = pros.getProperty("IP");
    }

    public ConnectServer() {
        // 与服务器建立连接
        try {
            client = new Socket(IP,PORT);
            this.in = client.getInputStream();
            this.out = client.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public InputStream getIn() {
        return in;
    }

    public OutputStream getOut() {
        return out;
    }
}
