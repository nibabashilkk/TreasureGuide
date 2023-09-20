# TreasureGuide
宝藏指北小程序，目前只有后端和小程序端，管理界面打算用个开源的还没来得及改。

## backend

小程序后端，使用`java17`+`mybatis-plus`+`redis`+`zincsearch`+`sa-token`来实现，目前由于数据量较小实现较为简单，分页什么的都没做。

使用的时候需要自己建数据库内的表，具体表类型看代码里实体类，自带的md文档好像不是最新的

部署时建议使用`docker-compose`来部署，操作较为简单。

下面是示例docker-compose.yml文件

```json
version: '3' #设置docker compose 版本
services: #设置services
  redis:
    image: redis:latest  #镜像名称
    container_name: redis #容器名称
    restart: always  #重启docker引擎后该容器也重启
    ports:
      - 6379:6379 #本地端口号与容器内部端口号
    volumes: #指定挂载目录
      - ./data/redis/redis.conf:/usr/local/etc/redis/redis.conf 
      - ./data/redis/data:/data
    command:
       --requirepass "secret"  ##初始化密码
    networks: 
      blog_network:
  postgres:
    image: postgres:latest
    container_name: postgres
    restart: always
    environment:
      POSTGRES_DB: dnname ##数据库名称
      POSTGRES_USER: user ##用户
      POSTGRES_PASSWORD: secret ##密码
    ports:
      - 5432:5432
    volumes:
      - ./data/postgres/data:/var/lib/postgresql/data
    networks:
      blog_network:
  zinc: ## zincsearch镜像
    image: public.ecr.aws/zinclabs/zinc:latest
    container_name: zinc
    environment:
      - ZINC_FIRST_ADMIN_USER=user
      - ZINC_FIRST_ADMIN_PASSWORD=secret
    ports:
      - "4080:4080"
    volumes:
      - ./data/zinc:/data
    restart: always
    networks:
      blog_network:
networks:  
      blog_network:
```

## miniprogram

小程序项目，直接导入到小程序开发者工具应该就能使用。

## miniprogram-manager

找的开源代码还没来得及改
