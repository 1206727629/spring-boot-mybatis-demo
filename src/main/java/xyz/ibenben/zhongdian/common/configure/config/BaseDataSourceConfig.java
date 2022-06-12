package xyz.ibenben.zhongdian.common.configure.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import xyz.ibenben.zhongdian.common.configure.DynamicRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * @author ywt start
 * @create 2022-06-11 13:31
 */
@Configuration
public class BaseDataSourceConfig {

    /**
     * 配置默认数据源，并设置高优先级
     * @return
     */
    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    /**
     * 配置多数据源
     * @return
     */
    @Bean("y2021DataSource")
    @ConfigurationProperties(prefix = "y2021.spring.datasource")
    public DataSource y2021DataSource(){
        return DataSourceBuilder.create().build();
    }

    /**
     * 设置多数据源，配置动态路由数据源
     * @return
     */
    @Bean("dynamincDataSource")
    public DataSource dynamincDataSource(){
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        HashMap<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put("dataSource",dataSource());
        dataSourceMap.put("y2021DataSource",y2021DataSource());
        //配置默认目标数据源
        dynamicRoutingDataSource.setDefaultTargetDataSource(dataSource());
        //设置目标数据源集合，供路由选择
        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);
        return dynamicRoutingDataSource;
    }

    /**
     * 设置会话工厂
     * @return
     * @throws Exception
     */
    @Bean(name="sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        //配置数据源为多数据源
        factoryBean.setDataSource(dynamincDataSource());
        //设置mybaits的xml文件路径
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/*.xml"));
        return factoryBean.getObject();
    }

    /**
     * 配置事务管理器
     * @return
     */
    @Bean(name="transactionManager")
    public PlatformTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dynamincDataSource());
    }

}

