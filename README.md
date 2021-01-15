# bronze

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
