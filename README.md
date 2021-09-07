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

> 如果spring boot版本小于2.3.x, spring-boot-starter-web自带spring-boot-starter-validation依赖
> 2.3.x以后的版本, 需要手动引入

依赖层级

* spring-boot-starter-validation 二次封装
  * hibernate-validator  api实现
    * jakata.validation-api  api定义

## 校验用注解有哪些?

### 标注需要校验的参数

除了需要进行分组校验或嵌套校验, 其他情况下@Valid和@Validated的使用是等价的, 推荐默认使用@Validated

|          | @Valid                                          | @Validated                                                   |
| -------- | ----------------------------------------------- | ------------------------------------------------------------ |
| 来源     | JSR-303规范 <br />javax.validation.Valid        | SpringFramework <br />org.springframework.validation.annotation.Validated |
| 可用范围 | METHOD, FIELD, CONSTRUCTOR, PARAMETER, TYPE_USE | TYPE, METHOD, PARAMETER                                      |
| 支持分组 | 否                                              | 是                                                           |
| 嵌套校验 | 可用于标记`需要进行嵌套校验的字段`              | 不可用于标记`需要进行嵌套校验的字段`(不支持FIELD作用域)      |

### 标注参数校验逻辑

每个注解的类注释上, 都写明了功能, 支持对象, 校验逻辑, 使用之前如果不确定, 建议参考一下注释

|                                     | 注解         | 功能                                                         |
| ----------------------------------- | ------------ | ------------------------------------------------------------ |
| javax.validation                    | @Null        | 被注释元素必须是null                                         |
|                                     | @NotNull     | 被注释元素必须不是null                                       |
|                                     | @NotEmpty    | 被注释元素必须不是null且不为空, 支持CharSequence, Collection, Map, Array. 一般用于注释集合 |
|                                     | @NotBlank    | 被注释元素必须不是null且长度大于0, 支持CharSequence, 一般用于注释String |
|                                     | @AssertTrue  | 被注释元素必须是true                                         |
|                                     | @AssertFalse | 被注释元素必须是false                                        |
|                                     | @Max(long)   | 被注释元素必须小于等于指定值, 支持BigDecimal, BigInteger, byte, short, int, long以及包装类 |
|                                     | @Min(long)   | 被注释元素必须大于等于指定值                                 |
|                                     | @DecimalMax  | 被注释元素必须小于等于指定值, 支持BigDecimal, BigInteger, CharSequence, byte, short, int, long |
|                                     | @DecimalMin  | 被注释元素必须大于等于指定值                                 |
|                                     | @Size        | 被注释元素必须在指定范围内, [min, max], 支持CharSequence, Collection, Map, Array |
|                                     | @Digits      | 被注释元素必须是数字, 并且在指定范围内                       |
|                                     | @Past        | 被注释元素必须是当前时间之前的时间                           |
|                                     | @Future      | 被注释元素必须是当前时间之后的时间                           |
|                                     | @Pattern     | 被注释元素必须符合正则表达式                                 |
|                                     | @Email       | 被注释元素必须是一个符合规则的邮箱地址                       |
| org.hibernate.validator.constraints | @Length      | 被注释元素必须是String且长度在指定范围内, [min, max]         |
|                                     | @Range       | 被注释元素必须在指定范围内, 支持数字和字符串数字             |
|                                     | @ISBN        | 必须是ISBN格式的编号                                         |
|                                     | @URL         | 必须是合法的URL格式                                          |
|                                     | 淘汰的注解   | @NotBlank, @NotEmpty                                         |

## 基本的使用方法和异常处理

### 使用类作为参数

需要在需要校验的参数类前添加@Validated, 参数类内需要校验的字段上增加校验注解

```java
/**
 * 校验失败抛出BindException
 */
@GetMapping("/user")
public PageParam<User> getUser(@Validated UserQuery userQuery) {
    // do some thing
}
/**
 * 校验失败抛出MethodArgumentNotValidException
 */
@PostMapping("/user")
public void saveUser(@Validated @RequestBody User user) {
    // do some thing
}
```

```java
public class UserQuery {
    @NotBlank
    private String userId;
    @NotBlank
    private String userName;
}
```

### 使用@PathVariable和@RequestParam作为参数

需要在Controller上增加@Validated

```java
@RestController
@RequestMapping("/user1")
@Validated
public class UserAllValidatedController {
    /**
     * 校验失败抛出ConstraintViolationException
     */
    @GetMapping("/getUser2/{sex}")
    public PageParam<User> getUser2(@PathVariable @Min(0) @Max(1) Integer sex) {
        return new PageParam<>();
    }

    /**
     * 校验失败抛出ConstraintViolationException
     */
    @GetMapping("/getUser3")
    public PageParam<User> getUser3(@RequestParam @Min(0) @Max(1) Integer sex) {
        return new PageParam<>();
    }
}
```

### 异常处理

需要处理的异常有三种

* BindException

  Get请求中 使用@Valid 验证路径中请求实体校验失败后抛出的异常

* MethodArgumentNotValidException

  @RequestBody上validate失败后抛出的异常

* ConstraintViolationException

  @PathVariable或@RequestParam参数校验失败后抛出的异常

> 注意, 在spring framework 5.3.x及以后的版本 (spring boot 2.4.x), MethodArgumentNotValidException从extends Exception改成了extends BindException
> 对BindException进行捕获处理, 就能兼容MethodArgumentNotValidException

全局异常处理

```java
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        System.out.println("捕获到MethodArgumentNotValidException");
        StringBuilder builder = new StringBuilder();
        // 获取所有的错误参数校验结果, 并将message进行拼接
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        for (ObjectError error : allErrors) {
            builder.append(error.getDefaultMessage()).append(";");
        }
        return ResponseEntity.badRequest().body(builder.toString());
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<String> bindExceptionHandler(BindException e) {
        System.out.println("捕获到BindException");
        StringBuilder builder = new StringBuilder();
        // 获取所有的错误参数校验结果, 并将message进行拼接
        List<ObjectError> allErrors = e.getAllErrors();
        for (ObjectError error : allErrors) {
            builder.append(error.getDefaultMessage()).append(";");
        }
        return ResponseEntity.badRequest().body(builder.toString());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> constraintViolationException(ConstraintViolationException e) {
        System.out.println("捕获到ConstraintViolationException");
        StringBuilder builder = new StringBuilder();
        // 获取所有的错误参数校验结果, 并将message进行拼接
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            builder.append(constraintViolation.getMessage()).append(";");
        }
        return ResponseEntity.badRequest().body(builder.toString());
    }
}
```



## 进阶应用

### 分组校验

多个api可能会复用同一个类作为入参类, 但是不同的api, 对于字段的校验逻辑不一样, 需要进行区分

> 注意, 只有使用@Validated时, 注解的分组功能才会生效

```java
// 使用Save分组
@PostMapping("/save0")
public void save0(@Validated(UserNormalValidDto.Save.class) @RequestBody UserNormalValidDto dto) {
    // 校验通过, 执行保存操作
    userMapper.saveUser(dto);
}
// 使用UpdateName分组
@PutMapping("/updateName")
public void updateName(@Validated(UserNormalValidDto.UpdateName.class) @RequestBody UserNormalValidDto dto) {
    userMapper.updateUserNameById(dto);
}
// 使用UpdatePhone分组
@PutMapping("/updatePhone")
public void updatePhone(@Validated(UserNormalValidDto.UpdatePhone.class) @RequestBody UserNormalValidDto dto) {
    userMapper.updateUserPhoneById(dto);
}
```

```java
public class UserNormalValidDto {
    // 仅当分组为UpdateName, UpdatePhone时生效
    @NotBlank(message = "userId不能为空", groups = {UpdateName.class, UpdatePhone.class})
    private String userId;
    
    // 仅当分组为Save, UpdateName时生效
    @NotNull(message = "name不能为空", groups = {Save.class, UpdateName.class})
    @Length(min = 5, max = 20, message = "名称长度必须在5-20", groups = {Save.class, UpdateName.class})
    private String name;

    // 仅当分组为Save时生效
    @NotNull(message = "age不能为空", groups = {Save.class})
    @Min(value = 1, message = "age必须大于等于1", groups = {Save.class})
    private Integer age;
    
    // 参数类内部定义的分组
    public interface Save {
    }

    public interface UpdateName {
    }

    public interface UpdatePhone {
    }
}
```

### 组合注解

对已经存在的校验注解进行组合封装, 一个注解能够达到多个注解的效果

```java
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {}) // 不需要指定校验器
// 封装Min和Max两个注解, 不过这个例子不太恰当, @Size和@Length可以达到同样的效果
@Min(0)
@Max(1)
public @interface Sex {
    /**
     * 校验不通过时的错误信息
     */
    String message() default "性别必须是0或1";

    /**
     * 分组用
     */
    Class<?>[] groups() default {};

    /**
     * 扩展用
     */
    Class<? extends Payload>[] payload() default {};
  
}
```

### 自定义校验器

如何自定义校验注解和校验器, 参考:

* javax.validation.Constraint: 类注释上有说明和举例

* javax.validation.ConstraintValidator

```java
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
// PhoneValidator是自定义的校验器
@Constraint(validatedBy = PhoneValidator.class)
public @interface Phone {

    /**
     * 校验不通过时的错误信息
     */
    String message() default "手机号校验失败";

    /**
     * 分组用
     */
    Class<?>[] groups() default {};

    /**
     * 扩展用
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * 自定义属性, 手机号正则检测规则
     */
    String regex() default "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    /**
     * 自定义属性, 是否允许手机号为null
     */
    boolean allowNull() default true;
}
```

```java
// 实现ConstraintValidator接口, 两个泛型参数, 第一个是对应的校验注解, 第二个是要校验的参数的类型, 对应方法isValid的第一个参数的类型
public class PhoneValidator implements ConstraintValidator<Phone, String> {
    // 自定义变量
    private String regex;
    private boolean allowNull;
    
	// 初始化方法, 在这里根据@Phone中的参数来对校验器进行初始化
    @Override
    public void initialize(Phone phone) {
        regex = phone.regex();
        allowNull = phone.allowNull();
    }
	
    // 校验方法, 在这里对被注释对象进行校验
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.length() == 0) {
            return allowNull;
        }
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(value);
        return m.matches();
    }
}
```

### 数组注解

在使用分组注解时, 同一个参数的同一个注解, 可能会希望在不同的组别中, 有不同的校验逻辑, 但是一个对象上默认同一注解只能存在一个, 需要定义数组注解, 让注解实现在一个对象上可重复

```java
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PhoneValidator.class)
// 标注@Phone可以在指定注解@Phone.List中重复添加
@Repeatable(value = Phone.List.class)
public @interface Phone {

    /**
     * 校验不通过时的错误信息
     */
    String message() default "手机号校验失败";

    /**
     * 分组用
     */
    Class<?>[] groups() default {};

    /**
     * 扩展用
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * 自定义属性, 手机号正则检测规则
     */
    String regex() default "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    /**
     * 自定义属性, 是否允许手机号为null
     */
    boolean allowNull() default true;

    // 新增Phone的数组注解, (可以在外部定义, 但是在内部定义会更方便一些)
    @Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        Phone[] value();
    }
}
```

使用方式

```java
public class UserNormalValidDto {
    // 普通写法
    @Phone.List(
    	@Phone(message = "请输入正确的手机号", groups = {Save.class})
		@Phone(message = "手机号格式错误, 请重新检查", allowNull = false, groups = {UpdatePhone.class})
    )
    private String phone;
    
    // 语法糖写法, 只要注解有对应的数组注解, 就可以连续添加注解, 编译器会自动转换为上面的效果
    // 
    @Phone(message = "请输入正确的手机号", groups = {Save.class})
	@Phone(message = "手机号格式错误, 请重新检查", allowNull = false, groups = {UpdatePhone.class})
    private String phone;
}
```

### 嵌套校验

```java
@PostMapping("/userPayInfo")
public void saveUserPayInfo(@Validated @RequestBody UserSavePayInfoValidDto dto) {
    userMapper.saveUserPayInfo(dto);
}
```

```java
public class UserSavePayInfoValidDto {
    @NotNull(message = "userId不能为空")
    private String userId;
    
	// 在嵌套类上使用@Valid, 表示这个字段需要进行嵌套校验, 如果没有, 嵌套类内部的校验注解不会生效
    @Valid
    @NotEmpty(message = "必须输入支付信息")
    private List<UserPayInfoDto> payInfoList;
}
```

```java
public class UserPayInfoDto {
    @NotBlank(message = "cardNo不能为空")
    private String cardNo;
    @NotBlank(message = "cardType不能为空")
    private String cardType;
}
```

### 在Controller接口之外的地方进行校验

```java
@Service
public class UserServiceImpl implement UserService{
    @Autowired
    private javax.validation.Validator validator;
    
    public void save(UserNormalValidDto dto) throws BusinessException {
        Set<ConstraintViolation<UserNormalValidDto>> validateResult = validator.validate(dto, UserNormalValidDto.Save.class);
        if (!validateResult.isEmpty()) {
            // 对参数进行手动补救, 或组合错误信息后, 抛出BusinessException
        }
        // 继续执行后续逻辑
	}
}
```

可以对校验器进行封装

```java
@Component
public class ValidationUtil {
    private final Validator validator;

    public ValidationUtil(Validator validator) {
        this.validator = validator;
    }

    public void validate(Object o, Class<?>... groups) throws BusinessException {
        Set<ConstraintViolation<Object>> result = validator.validate(o, groups);
        if (result.isEmpty()) {
            return;
        }
        StringBuilder builder = new StringBuilder();
        for (ConstraintViolation<Object> objectConstraintViolation : result) {
            String message = objectConstraintViolation.getMessage();
            builder.append(message).append(";");
        }
        throw new BusinessException("000001", builder.toString());
    }
  
  
}
```

```java
@Service
public class UserServiceImpl implement UserService{
    @Autowired
    private ValidationUtil validationUtil;
    
    public void save2(@RequestBody UserNormalValidDto dto) throws BusinessException {
        validationUtil.validate(dto, UserNormalValidDto.Save.class);
        userMapper.saveUser(dto);
    }
}
```



[参考文章1](https://segmentfault.com/a/1190000023471742)

[参考文章2](https://www.chkui.com/article/java/java_bean_validation)

