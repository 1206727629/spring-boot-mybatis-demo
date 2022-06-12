package xyz.ibenben.zhongdian.common.configure;

/**
 * @author ywt start
 * @create 2022-06-11 13:32
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Multiple DataSource Configurer
 */
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected Object determineCurrentLookupKey() {
        logger.info("Current DataSource is {}",DynamicDataSourceContextHolder.getDataSourceKey());
        //从动态数据源上下文持有者里面获取
        return DynamicDataSourceContextHolder.getDataSourceKey();
    }
}
