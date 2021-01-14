下载一个gradle的版本  根据 gradle-wrapper.properties 这个文件的下载地址去下载
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
distributionUrl=https\://services.gradle.org/distributions/gradle-6.7.1-bin.zip
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists

执行命令：gradle build 生成后地址在C:\Users\Administrator\.gradle文件下
3、将下载后的gradle的zip包放在C:\Users\Administrator\.gradle\wrapper\dists\gradle-6.7.1-bin\bwlcbys1h7rz3272sye1xwiv6下边

在这个过程去构建工程，发现一直在在下载Gradle的zip包，很奇怪说要用阿里云的地址等等...
由于本地已经下了zip包，后来发现在idea 工具中配置就好了
4、打开idea 在setting中Build,Execution，Deployment 下的Gradle 的Gradle user home 配置自己下载好的路径

5、关掉idea 重新打开即可自动编译项目，构建成功
