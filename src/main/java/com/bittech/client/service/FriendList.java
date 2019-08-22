package com.bittech.client.service;

import javax.swing.*;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Dyson
 * @date 2019/8/19 13:38
 */
public class FriendList {
    private JPanel friendListPanel;
    private JScrollPane friendPanel;
    private JButton createGroupBtn;
    private JScrollPane GroupPanel;
    private String myName;
    private ConnectServer connectServer;
    private Set<String> names;

    //后台监听线程,动态的监听好友上线信息
    private class DaemonTask implements Runnable{
        private Scanner scanner = new Scanner(connectServer.getIn());
        @Override
        public void run() {
            while(true){
                if(scanner.hasNextLine()){
                    String strFromServer = scanner.nextLine();
                    if(strFromServer.startsWith("newLogin:")){
                        //好友上线提醒
                        String newFriend = strFromServer.split(":")[1];
                        JOptionPane.showMessageDialog(null,
                                newFriend+"上线了","上线提醒",JOptionPane.INFORMATION_MESSAGE);
                        names.add(newFriend);
                        reloadFriendList();
                    }
                }
            }
        }
    }


    public FriendList(String myName,ConnectServer connectServer,Set<String> names){
        this.myName = myName;
        this.connectServer = connectServer;
        this.names = names;
        JFrame frame = new JFrame(myName);
        frame.setContentPane(friendListPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        reloadFriendList();
        //新启动一个后台进程不断监听服务器发来的信息
        Thread daemonThread = new Thread(new DaemonTask());
        //设置线程为后台线程
        daemonThread.setDaemon(true);
        daemonThread.start();
    }
    public void reloadFriendList(){
        JPanel friendLabelPanel = new JPanel();
        JLabel[] labels = new JLabel[names.size()];
        //迭代遍历set集合
        Iterator<String> iterator = names.iterator();
        //设置标签纵向对齐，纵向显示在线用户信息
        friendLabelPanel.setLayout(new BoxLayout(friendLabelPanel,BoxLayout.Y_AXIS));
        int i = 0;
        while(iterator.hasNext()){
            String labelName = iterator.next();
            labels[i] = new JLabel(labelName);
            //将标签添加到盘子中
            friendLabelPanel.add(labels[i]);
            i++;
        }
        //将盘子加到滚动条当中
        this.friendPanel.setViewportView(friendLabelPanel);
        //设置滚动条为垂直滚动条
        this.friendPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.friendPanel.revalidate();
    }
}
