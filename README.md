# spring-cloud-mongodb-example
这是一个针对mongodb数据库的操作示例程序

#安装mongodb
中文社区[http://mongoing.com/]  
官网[https://www.mongodb.com/]  
数据库查看工具比较好用的
[https://www.mongodbmanager.com/download]
[https://www.navicat.com.cn/]  
根据你的需要下载版本进行安装，这里就不介绍了。
#简单介绍
mongodb 默认安装没有密码;使用mdb时必须熟悉它的概念以及它的数据库操作各个函数的功能，这样才能顺利的使用。
实际业务场景下多数用于文档的存储，不复杂的业务系统存储，文档的检索，若是海量数据时需要搭配ES进行搜索这里也不过多介绍。
#启动方式centOS
修改工程的.yml配置的ip端口要是改了就改没改就用默认的
a：远程到数据库所在的服务器 
b：进入目录： cd /opt/mongodb/bin/  
c：启动：./mongod --config /opt/mongodb/mongodb.conf 


