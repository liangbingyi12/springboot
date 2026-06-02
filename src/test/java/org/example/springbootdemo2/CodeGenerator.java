package org.example.springbootdemo2; // 声明当前生成器所在的包名

import com.baomidou.mybatisplus.generator.FastAutoGenerator; // 导入 MyBatis-Plus 快速代码生成器
import com.baomidou.mybatisplus.generator.config.OutputFile; // 导入输出文件类型枚举
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine; // 导入 Freemarker 模板引擎
import org.apache.ibatis.annotations.Mapper; // 导入 Mapper 注解类型

import java.nio.file.Path; // 导入跨平台路径工具
import java.util.Arrays; // 导入数组工具类
import java.util.Collections; // 导入集合工具类
import java.util.List; // 导入列表类型

public class CodeGenerator { // 定义 MyBatis-Plus 代码生成器类

    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/testdb" // 定义默认数据库连接地址前半部分
            + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai"; // 拼接默认数据库连接参数
    private static final String DEFAULT_USERNAME = "root"; // 定义默认数据库用户名
    private static final String DEFAULT_PASSWORD = "root"; // 定义默认数据库密码
    private static final String DEFAULT_PARENT_PACKAGE = "org.example.springbootdemo2"; // 定义默认父包名
    private static final String GENERATE_TABLES = "users"; // 指定要生成的数据表，多个表用英文逗号分隔，留空则生成全部表
    private static final String TABLE_PREFIX = ""; // 指定需要去掉的数据表前缀，多个前缀用英文逗号分隔

    public static void main(String[] args) { // 定义生成器程序入口
        GeneratorProperties properties = GeneratorProperties.fromSystemProperties(); // 从 JVM 参数中读取生成器配置

        FastAutoGenerator.create(properties.url(), properties.username(), properties.password()) // 创建 MyBatis-Plus 快速生成器
                .globalConfig(builder -> builder // 开始配置全局生成参数
                        .author(properties.author()) // 设置生成代码中的作者
                        .outputDir(properties.javaOutputDir()) // 设置 Java 文件输出目录
                        .commentDate("yyyy-MM-dd") // 设置注释中的日期格式
                        .disableOpenDir()) // 生成完成后不自动打开输出目录
                .packageConfig(builder -> builder // 开始配置包路径
                        .parent(properties.parentPackage()) // 设置父包名
                        .entity("entity") // 设置实体类包名
                        .mapper("mapper") // 设置 Mapper 接口包名
                        .service("service") // 设置 Service 接口包名
                        .serviceImpl("service.impl") // 设置 Service 实现类包名
                        .controller("controller") // 设置 Controller 包名
                        .pathInfo(Collections.singletonMap(OutputFile.xml, properties.xmlOutputDir()))) // 设置 Mapper XML 输出目录
                .strategyConfig(builder -> { // 开始配置生成策略
                    if (!properties.tables().isEmpty()) { // 判断是否指定了需要生成的数据表
                        builder.addInclude(properties.tables()); // 指定只生成这些数据表
                    } // 结束指定数据表判断
                    builder.addTablePrefix(properties.tablePrefixes()) // 设置生成实体类时需要去掉的数据表前缀
                            .entityBuilder() // 进入实体类生成策略配置
                            .enableTableFieldAnnotation() // 在实体字段上生成表字段注解
                            .mapperBuilder() // 进入 Mapper 生成策略配置
                            .mapperAnnotation(Mapper.class) // 在 Mapper 接口上添加 @Mapper 注解
                            .enableBaseResultMap() // 在 XML 中生成 BaseResultMap
                            .enableBaseColumnList() // 在 XML 中生成 BaseColumnList
                            .serviceBuilder() // 进入 Service 生成策略配置
                            .formatServiceFileName("%sService") // 设置 Service 接口命名规则
                            .controllerBuilder() // 进入 Controller 生成策略配置
                            .enableRestStyle(); // 生成 REST 风格 Controller
                }) // 结束生成策略配置
                .templateEngine(new FreemarkerTemplateEngine()) // 使用 Freemarker 模板引擎生成代码
                .execute(); // 执行代码生成
    } // 结束 main 方法

    private record GeneratorProperties( // 定义用于承载生成器配置的不可变记录类型
            String url, // 数据库连接地址
            String username, // 数据库用户名
            String password, // 数据库密码
            String author, // 生成代码作者
            String parentPackage, // 生成代码父包名
            List<String> tables, // 需要生成代码的数据表列表
            List<String> tablePrefixes, // 需要去掉的数据表前缀列表
            String javaOutputDir, // Java 文件输出目录
            String xmlOutputDir) { // Mapper XML 文件输出目录

        private static GeneratorProperties fromSystemProperties() { // 从系统属性创建生成器配置
            String projectDir = System.getProperty("user.dir"); // 获取当前 Maven 项目根目录
            return new GeneratorProperties( // 返回组装好的生成器配置
                    getProperty("generator.url", DEFAULT_URL), // 读取数据库地址，未提供时使用默认值
                    getProperty("generator.username", DEFAULT_USERNAME), // 读取数据库用户名，未提供时使用默认值
                    getProperty("generator.password", DEFAULT_PASSWORD), // 读取数据库密码，未提供时使用默认值
                    getProperty("generator.author", System.getProperty("user.name", "code-generator")), // 读取作者，未提供时使用当前系统用户名
                    getProperty("generator.parentPackage", DEFAULT_PARENT_PACKAGE), // 读取父包名，未提供时使用默认父包名
                    splitOptional("generator.tables", GENERATE_TABLES), // 优先读取 VM 指定表，否则读取代码指定表，两者都为空时生成全部表
                    splitOptional("generator.tablePrefix", TABLE_PREFIX), // 优先读取 VM 表前缀，否则读取代码指定表前缀
                    Path.of(projectDir, "src", "main", "java").toString(), // 计算 Java 文件输出目录
                    Path.of(projectDir, "src", "main", "resources", "mapper").toString() // 计算 Mapper XML 输出目录
            ); // 结束配置对象创建
        } // 结束 fromSystemProperties 方法

        private static String getProperty(String key, String defaultValue) { // 定义读取单个系统属性的方法
            String value = System.getProperty(key); // 按属性名读取 JVM 系统属性
            return value == null || value.isBlank() ? defaultValue : value.trim(); // 为空时返回默认值，否则返回去除首尾空格的值
        } // 结束 getProperty 方法

        private static List<String> splitOptional(String key, String defaultValue) { // 定义读取可选逗号分隔参数的方法
            String value = System.getProperty(key, defaultValue); // 读取系统属性，未提供时使用默认值
            if (value.isBlank()) { // 判断参数值是否为空白
                return Collections.emptyList(); // 空白时返回空列表
            } // 结束空白判断
            return Arrays.stream(value.split(",")) // 按逗号拆分字符串并创建流
                    .map(String::trim) // 去除每个参数项的首尾空格
                    .filter(item -> !item.isEmpty()) // 过滤掉空参数项
                    .toList(); // 收集为不可变列表
        } // 结束 splitOptional 方法
    } // 结束 GeneratorProperties 记录类型
} // 结束 CodeGenerator 类
