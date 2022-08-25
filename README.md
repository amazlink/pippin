<!-- TOC -->

- [1. 概述](#1-概述)
- [2. 友情关联](#2-友情关联)
- [3. 目录结构](#3-目录结构)

<!-- /TOC -->

![gradle](https://abram.oss-cn-shanghai.aliyuncs.com/blog/sctel/gradle.png)

## 1. 概述

`Pippin`, was a `Hobbit` of the Shire, and one of `Frodo Baggins'` youngest, but closest friends. He was a member of the Fellowship of the Ring and later became the thirty-second Thain of the Shire。 

`Gradle` 是一种开源构建自动化工具，其设计足够灵活，几乎可以构建任何类型的软件，是一个基于`Apache Ant`和 `Apache Maven`概念的项目自动化构建工具，它使用 `Groovy` 语言来声明项目设置，抛弃了 `XML` 的各类繁琐配置。以面向 `Java` 应用为主，当前支持的语言有除 `Java` 外，还有 `Groovy`、`Kotlin`、`Scala`、`C++`、`Swift`、`JavaScript`，计划未来支持更多的语言。

`Gradle` 具有以下特性：

- 高性能：`Gradle` 通过仅运行需要运行的任务来避免不必要的工作，因为它们的输入或输出已更改，还可以使用构建缓存来重用先前运行或来自不同机器（具有共享构建缓存）的任务输出。
- JVM基础：`Gradle` 在 `JVM` 上运行，必须安装 `Java Development Kit (JDK)` 才能使用它，这对于熟悉 `Java` 平台的用户来说是一个好处，可以在构建逻辑中使用标准的 `Java API`，例如自定义任务类型和插件，它还可以轻松地在不同平台上运行 `Gradle` 。
- 约定： `Gradle` 借鉴 `Maven` ，通过实现约定使常见类型的项目易于构建，应用适当的插件可以轻松地为许多项目创建精简的构建脚本，但是这些约定并不会限制，你可以覆盖它们，添加自己的任务，进行其他自定义行为。
- 可扩展性：可以扩展 `Gradle` 以提供您自己的任务类型甚至构建模型。
- IDE支持：支持主流 IDE 构建并与之交互，如 `Android Studio`、`IntelliJ IDEA`、 `Eclipse`、 `NetBeans`，`Gradle` 还支持生成将项目加载到 `Visual Studio` 所需的解决方案。
- 可分析：可在构建扫描过程中提供有关构建运行的大量信息，基于这些信息来识别构建性能问题，还可以与他人共享构建扫描，这在解决构建问题时特别有用。

 本工程主要是针对 `Gradle`的学习入门类项目，也是借助于托尔金指环王的人物命名的工程，在学习本工程之前，对学习者有如下要求：

- 面向`Java`语言的项目开发者，至少一年半以上开发类的经验，如果会 `Python` 更好， `Groovy`语言很多都是参考 `Python`。
- 掌握 `Maven`在项目开发中的基本运用，具体如下：
    - 熟练掌握 `Nexus` 私服，创建和维护私服
    - 掌握 `Maven`中环境构建、依赖管理、编译、打包、部署，并且可以基本运用
    - 了解 `Maven`中对插件的运用，如`maven-compiler-plugin`、`maven-resources-plugin`等
    - 了解 `Maven`中各类仓库的概念，中央仓库、本地仓库
- 了解项目中常用的版本命名规则

如果不满足上述要求，建议先去补充 `Maven`有关的知识，因为 `Gradle`和 `Maven`还是有很多类似的地方。

通过本工程的学习，会掌握 `Gradle`常见用法以及利用 `Groovy`编写任务来完成自身项目开发需要，当然本工程最后一段项目案例以集成 `SpringBoot` 完成项目模块的构建，也可以将此用来当作现实中用来改造既有项目为 `Gradle`项目的模板来使用。

## 2. 友情关联

本工程目前已经开源，源码目录分别在 `Gitee` 和 `Github` 中都有链接，希望对大家有帮助，最后别忘给 `Star`。

[Gitee链接：https://gitee.com/rothschil/pippin](https://gitee.com/rothschil/pippin)

[Github链接：https://github.com/rothschil/pippin.git](https://github.com/rothschil/pippin.git)

## 3. 目录结构


~~~

|-- Doc                 ------------------文档
|-- README.md
|-- build
|   `-- bootJarMainClassName
|-- build.gradle        ------------------Gradle配置文件
|-- gradle
|   `-- wrapper
|       |-- gradle-wrapper.jar  -gradle-wrapper 主题功能
|       `-- gradle-wrapper.properties   --Gradle当前配置的版本，以及从哪里获取
|-- gradle.properties       ------------------根目录的属性文件，这是默认命名
|-- gradlew
|-- gradlew.bat
|-- settings.gradle         ------------------Gradle模块 配置文件
`-- src
    |-- main
    |   |-- java
    |   |   `-- io
    |   |       `-- github
    |   |           `-- rothschil
    |   |               |-- PippinApplication.java
    |   |               `-- web
    |   |                   `-- IndexController.java
    |   `-- resources
    |       |-- application.properties
    |       |-- favicon.ico
    |       |-- logback.xml
    |       |-- static
    |       |   `-- schema
    |       |       |-- schema-book.graphql
    |       |       |-- schema-enum.graphql
    |       |       |-- schema-user.graphql
    |       |       `-- schema.graphql
    |       `-- templates
    `-- test
        |-- java
        |   `-- io
        |       `-- github
        |           `-- rothschil
        |               `-- base
        `-- resources


~~~