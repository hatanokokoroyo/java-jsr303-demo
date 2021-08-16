[toc]

# JSR 303

`Java API`规范(`JSR303`)定义了`Bean`校验的标准`validation-api`，但没有提供实现。

`hibernate validation`是对这个规范的实现，并增加了校验注解如`@Email`、`@Length`等。`Spring Validation`是对`hibernate validation`的二次封装，用于支持`spring mvc`参数自动校验。

## 如何添加依赖

在spring boot项目中, 添加

```xml
 <dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

依赖层级

* spring-boot-starter-validation
  * hibernate-validator
    * jakata.validation-api

## 校验用注解有哪些?

## 基本的使用方法

## 异常处理

需要处理的异常有三种

* BindException

  Get请求中 使用@Valid 验证路径中请求实体校验失败后抛出的异常

* MethodArgumentNotValidException

  @RequestBody上validate失败后抛出的异常

* ConstraintViolationException

  @PathVariable或@RequestParam参数校验失败后抛出的异常

> 注意, 在spring framework 5.3.x及以后的版本 (spring boot 2.4.x), MethodArgumentNotValidException从extends Exception改成了extends BindException
> 对BindException进行捕获处理, 就能兼容MethodArgumentNotValidException

## 进阶应用

### 组合注解

### 自定义校验器

### 注解数组

### 嵌套校验

### Controller以外

注入validator

封装工具类

## 基本的使用



## @Valid和@Validate的区别

* 包的位置, 来源
* 分组
* 嵌套校验