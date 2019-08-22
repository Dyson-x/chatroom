package com.bittech.client.service;

import com.bittech.client.dao.AccountDao;
import com.bittech.client.entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Dyson
 * @date 2019/8/18 10:20
 */
public class UserReg {
    private JPanel regPanel;
    private JTextField userNameText;
    private JTextField passwordText;
    private JTextField briefText;
    private JButton confimBtn;

    private AccountDao accountDao = new AccountDao();

    public UserReg() {
        final JFrame frame = new JFrame("用户注册");
        frame.setContentPane(regPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //居中显示页面
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        //点击提交按钮触发方法
        confimBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //1.获取界面上三个控件内容
                String userName = userNameText.getText();
                String password = passwordText.getText();
                String brief = briefText.getText();
                //2.调用dao层方法将信息持久化到数据库
                User user = new User();
                user.setUserName(userName);
                user.setPassword(password);
                user.setBrief(brief);
                System.out.println(user);
                if(accountDao.userReg(user)){
                    //弹出提示框提示用户信息注册成功
                    //返回登录界面
                    JOptionPane.showMessageDialog(null,
                            "注册成功","成功信息",JOptionPane.INFORMATION_MESSAGE);
                    //关闭注册框
                    frame.setVisible(false);
                }else {
                    //弹出提示框告知用户注册失败
                    //保留当前注册页面
                    JOptionPane.showMessageDialog(null,
                            "注册失败","错误信息",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
