# !!!重要!!!

运行项目前请先部署好环境,步骤如下

在src/main/resources文件夹下新建application-dev.yml文件,按照以下格式填写MySQL和Redis地址:

```yaml
spring: #没有写注释的地方请勿更改
  profiles: dev

  redis:
    host: #这里写你的Redis的地址
    port: 6379
    database: 0
    password: #这里写你的Redis的密码
  datasource:
    druid:
      driver-class-name: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://${YOUR_ADDRESS}:3306/aladdin?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true #这里写你MySQL的驱动,请先把${YOUR_ADDRESS}更改为你自己的MySQL数据库地址
      username: YOUR_USERNAME #这里写你的MySQL的用户名
      password: YOUR_PASSWORD #这里写你的MySQL的密码

```

# 项目入口

可能会有变更

## 用户端(需开启手机模式)

```http
localhost:8080/front/page/login.html
```

## 管理端

```http
localhost:8080/backend/page/login/login.html
```

# git使用指南

## git clone

用于克隆仓库

用法:

```cmd
git clone + 项目地址
```

## git pull

用于拉取代码和变更分支等

就是 **git fetch** 和 **git merge FETCH_HEAD** 的简写

用法:

```cmd
git pull
```

或

```cmd
git pull origin master:master
```

