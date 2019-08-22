package com.bittech.client.service;

import com.bittech.client.dao.AccountDao;
import com.bittech.client.entity.User;
import com.bittech.util.CommUtil;
import com.bittech.vo.MessageVo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Dyson
 * @date 2019/8/18 10:00
 */
public class UserLogin {
    private JPanel loginPanel;
    private JPanel labelPanel;
    private JPanel userNamePanel;
    private JTextField userNameText;
    private JPanel passWordPanel;
    private JPasswordField passWordText;
    private JButton regButton;
    private JPanel btnPanel;
    private JButton loginButton;

    private AccountDao accountDao=new AccountDao();

    public UserLogin() {
        final JFrame frame = new JFrame("用户登录");
        frame.setContentPane(loginPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        //居中显示
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //点击注册按钮，弹出注册页面
        regButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserReg();
            }
        });
        //点击登录按钮，验证用户输入信息是否正确
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取用户的输入
                String userName = userNameText.getText();
                String password = String.valueOf(passWordText.getPassword());
                User user = accountDao.userLogin(userName,password);
                if(user!=null){
                    //登录成功
                    JOptionPane.showMessageDialog(null,
                            "登录成功","提示信息",JOptionPane.INFORMATION_MESSAGE);
                    //与服务器建立连接，将用户名注册到服务器缓存
                    ConnectServer connectServer = new ConnectServer();
                    //客户端
                    //1. 与服务器建立连接,将自己的用户名与Socket保存到服务端缓存
                    MessageVo messageVo = new MessageVo();
                    messageVo.setType(1);
                    messageVo.setContent(userName);
                    String msgJson = CommUtil.object2Json(messageVo);
                    try {
                        PrintStream out = new PrintStream(connectServer.getOut(),
                                true,"UTF-8");
                        out.println(msgJson);
                        //读取服务端发回的响应，读取到所有在线好友信息
                        Scanner in = new Scanner(connectServer.getIn());
                        if(in.hasNextLine()){
                            String jsonStr = in.nextLine();
                            MessageVo msgFromServer = (MessageVo) CommUtil.json2Object(jsonStr,
                                    MessageVo.class);
                            Set<String> names = (Set<String>) CommUtil.json2Object(msgFromServer.getContent(),
                                    Set.class);
                            System.out.println("在线好友为:"+names);
                            //加载好友列表界面，将登陆界面不可见
                            frame.setVisible(false);
                            //跳转到好友列表界面需要传递用户名，与服务器建立得连接，所有在线的好友信息
                            new FriendList(userName,connectServer,names);
                        }

                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    }
                    //2. 读取服务端的所有在线好友信息
                    //3. 新建一个后台线程不断读取服务器发来的信息

                }else {
                    JOptionPane.showMessageDialog(null,
                            "登录失败","错误信息",JOptionPane.ERROR_MESSAGE);
                    //保留当前登录界面
                }
            }
        });
    }

    public static void main(String[] args) {
        new UserLogin();
    }
}
