### 注解完成java对象的创建，属性，代替xml文件
---
##### 实现步骤
1. 加入依赖 必须要有 AOP、扫描注解的依赖，context隐式的导入了这些依赖
> org.springframework:spring-expression:5.2.5.RELEASE
> org.springframework:spring-aop:5.2.5.RELEASE
2. 创建类，在类中加入注解
3. 创建spring的配置文件，声明组件的标签，指名注解在项目中的位置。
4. 使用注解创建对象，创建容器ApplicationContext