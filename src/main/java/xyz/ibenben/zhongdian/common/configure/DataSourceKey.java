package xyz.ibenben.zhongdian.common.configure;

/**
 * @author ywt start
 * @create 2022-06-11 13:33
 */
public enum DataSourceKey {
    dataSource("dataSource","dataSource"),
    y2021("key","y2021DataSource");

    public String key;
    public String dataSourceName;
    DataSourceKey(String key, String dataSourceName){
        this.key = key;
        this.dataSourceName = dataSourceName;
    }
}

