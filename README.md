## 搭建 bronze 环境：
工程构建依托 [gradle](http://www.gradle.org) 构建工具，需要手动下载好对应的发布包：
~~~~
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
distributionUrl=https\://services.gradle.org/distributions/gradle-6.7.1-bin.zip
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists
~~~~
- 按照 `gradle-wrapper.properties` 文件中 `distributionUrl` 路径，将下载好的压缩包放到 wrapper 自动创建的路径下（如：~/.gradle/wrapper/dists/gradle-6.7.1-bin/bwlcbys1h7rz3272sye1xwiv6）。
- 执行 gradle 命令
- 如果发现在一直下载 Gradle 的 Zip 包，在 开发工具中配置指定版本的 Gradle 的路径，以 IDEA 配置为例：File -> Settings -> Build,Execution，Deployment -> Gradle -> Gradle user home
- 关掉idea 重新打开即可自动编译项目，构建成功


## 敏感数据处理

基于 MyBatis 拦截器（`org.apache.ibatis.plugin.Interceptor`）提供了一个敏感数据处理机制，可以针对敏感数据，从 `写入数据库` 和 `数据库中读取` 两个环节对敏感数据进行处理，如：

1. 写入时加密，读取时解密：涉密数据希望在 DB 中以密文形式存储，系统中以明文形式展示
2. 写入时加密，读取时不处理：密码希望以不可逆密文存储在数据库，读取使用时也是以密文形式使用
3. 写入时不处理，读取时加密：数据使用时希望遮挡部分内容脱敏显示

### 配置

配置参数以 `com.amber.common.sensitive` 为前缀，后面配置格式为 `key` : `value`，其中：

- `key` 为 MyBatis Mapper 实体类全名及要处理敏感数据的属性，如 `UserDO` 的 `phone` 属性设置为：`com.amber.common.sensitive.entity.UserDO.phone`。目前仅支持字符串类型的属性。
- `value` 为敏感数据处理类 bean name 的后缀，加上 `dataSensitiveHandler-` 前缀组成完整 bean name。

```yml
com:
  amber:
    common:
      sensitive:
        com.amber.common.sensitive.entity.UserDO.phone: abb
        com.amber.common.sensitive.entity.UserDO.idCard: sm4
```

```properties
com.amber.common.sensitive.com.amber.common.sensitive.entity.UserDO.password=md5
```

### 内置敏感数据处理器

目前内置三种敏感数据处理器：

1. `abb`：对字符串中间部分使用 `*` 遮挡，仅对读取到的数据执行操作
2. `md5`：对字符串进行 md5 摘要，仅在写入数据时执行操作
3. `sm4`：使用国密 SM4 算法进行对称加解密，在写入及读取时均执行

### 自定义敏感数据处理器

实现 `DataSensitiveHandler` 接口，接口中包括两个方法：

1. 需要在写入数据时对数据做的处理，在 `encrypt` 方法中实现；
2. 需要在读取数据时对数据做的处理，在 `decrypt` 方法中实现。

自定义处理器注册 bean 时，需要使用固定前缀 `dataSensitiveHandler-` 加自定义后缀的命名方式，如：

```java
@Component("dataSensitiveHandler-cus")
```

此时可在配置文件中设置 `com.amber.common.sensitive.package.entity.property=cus`

### 注意

- 通过 MyBatis 新增或保存实体时，传入的实体在方法调用后，配置为敏感数据的属性会变成应用了敏感处理器 `encrypt` 方法之后的值
- 通过 MyBatis 查询实体时，检索出的实体对象中，配置了敏感数据的属性会变成应用了敏感处理器 `decrypt` 方法之后的值
- 不通过 MyBatis 操作的数据，不会应用敏感数据处理器处理数据
- **存入数据库中的数据在执行了敏感处理后将丧失按照处理前的数据进行查询的能力，只能按照处理后的数据进行查询**



