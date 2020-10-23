package com.daiwei;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import java.util.Scanner;

public class MybatisPlusGeneratorTest {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        AutoGenerator autoGenerator = new AutoGenerator();
        String projectPath = System.getProperty("user.dir");
        //全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setAuthor("daiwei")//设置作者
            .setOutputDir(projectPath + "/mybatis-plus-generator/src/main/java")//设置输出路径
            .setFileOverride(true)//设置文件覆盖
            .setIdType(IdType.AUTO)//设置主键生成策略
            .setServiceName("%sService")//service接口的名称
            .setBaseResultMap(true)//基本结果集合
            .setBaseColumnList(true)//设置基本的列
            .setControllerName("%sController")
            .setSwagger2(true);

        //配置数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver").
            setUrl("jdbc:mysql://localhost:3306/memb?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai")
            .setUsername("root").setPassword("parking");

        //策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setInclude("dept", "emp")//设置要包含的表
//            .setTablePrefix("tbl_")//设置表名的前缀
            .setNaming(NamingStrategy.underline_to_camel);//映射实体类的时候命名的策略

        //包名配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.daiwei").setMapper("mapper")
            .setService("service").setController("controller")
            .setEntity("bean").setXml("mapper");

        autoGenerator.setGlobalConfig(globalConfig).setDataSource(dataSourceConfig)
            .setStrategy(strategyConfig).setPackageInfo(packageConfig);

        autoGenerator.execute();
    }

}
