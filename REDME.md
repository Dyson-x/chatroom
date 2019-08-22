

聊天室dao层开发与聊天室登录与注册按钮
-
- dao：数据库
- service：处理业务



注册用户：服务器端实现
由于是在本地，服务器与客户端共享数据库，将注册用户放在客户端

JDBC：基于数据源
1. 加载数据源
2. 获取连接 Connection
3. 执行SQL  
    select :executeQuery(PreparedStatement，ResultSet)
    insert/update/delete:executeUpdate(PreparedStatement)
4. 关闭资源

加载数据源(已经创建好的数据库连接，类似于线程池，可以将创建好的链接重复使用)

问题：
- 所有数据库的实体一律用包装类
- 将数据源的公共操作提取到一个父接口中



- JPanel - 存放各类组件的基础容器
- JLabel - 标签
- JTextField - 输入框
- JButton - 按钮
- JOptionPanel - 提示框（静态方法） 成功，失败


聊天室好友列表开发与通信模块
-
ServerSocket：accept() 返回客户端Socket

Socket


建立连接登录之后服务器端与客户顿通信过程

服务端
1. 保存新用户上线信息  request
2. 将当前所有在线用户发回给新用户 response
3. 将新用户上线信息发给所有其他用户(上线提醒)

客户端
1. 与服务器建立连接,将自己的用户名与Socket保存到服务端缓存
2. 读取服务端的所有在线好友信息
3. 新建一个后台线程不断读取服务器发来的信息

json字符串 序列化
前后端分离
{"key","value"}

str -> Object：json序列化
Object -> str：json反序列化

transient关键字，禁止序列化


- Message (client)
   
    - type:1
    - content:userName
    - to:(私聊，群聊)

server:

  - type:1
  - content:[在线用户信息] 
   
   
   
  私聊实现
  ---
  客户端：点击用户列表标签，进入到私聊界面，在私聊界面中按照的指定协议去，
  向服务端发送信息，服务端判断发送来的协议，取出定的私聊用户对象的Socket，
  然后将信息进行转发。服务器中接受端被动接受服务器端所发送来的信息，先判断
  缓存中是否存在私聊界面，如果有直接将私聊界面的frame.setVisible开启，没
  有则创建即可，然后按照指定的协议去读取所发送来的msg(判断发送过来的格式是否
  为Json格式，然后将Json格式转化为字符串)，然后将msg进行切分并将其进行输出。
  
  
  
  群聊注册实现
  --
  当点击注册之后，
  
  客户端将所有的在线用户创建一个JPanel，将所有的在线用户名称加一个JConboBox标签，
  当点击群聊用户名单时，将所有群聊用户名称加入到一个set集合中，并且指定协议，
  将set(转化为Json格式)，type，content，发送到服务端，服务端解析发送的协议，
  
  
  
  难点：群聊实现
  
   