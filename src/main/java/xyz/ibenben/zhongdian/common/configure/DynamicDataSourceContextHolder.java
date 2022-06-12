package xyz.ibenben.zhongdian.common.configure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ywt start
 * @create 2022-06-11 13:33
 */
public class DynamicDataSourceContextHolder {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * Maintain variable for every thread, to avoid effect other thread
     */
    private static ThreadLocal<String> CONTEXT_HOLDER = ThreadLocal.withInitial(DataSourceKey.dataSource::name);;
    /**
     * All DataSource List
     */
    public static List<Object> dataSourceKeys = new ArrayList<>();
    /**
     * Get current DataSource
     * @return data source key
     */
    public static String getDataSourceKey() {
        return CONTEXT_HOLDER.get();
    }
    public static void setDataSourceKey(String key) {
        CONTEXT_HOLDER.set(key);
    }

    public static void clearDataSourceKey() {
        CONTEXT_HOLDER.remove();
    }


    public static boolean containDataSourceKey(String key) {
        return dataSourceKeys.contains(key);
    }

    public static void useSlaveDataSource(String ds) {
        DataSourceKey dataSourceKey = DataSourceKey.valueOf(ds);
        //if there is no suitable enum,then use default dataSource
        if(dataSourceKey == null){
            setDataSourceKey(DataSourceKey.dataSource.dataSourceName);
        }
        setDataSourceKey(dataSourceKey.dataSourceName);
    }

    public static void useMasterDataSource() {
        CONTEXT_HOLDER.set(DataSourceKey.dataSource.dataSourceName);
    }

}

