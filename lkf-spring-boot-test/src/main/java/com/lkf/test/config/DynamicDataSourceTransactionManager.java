package com.lkf.test.config;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;

/**
 * Created by Administrator on 2017/8/16 0016.
 */
public class DynamicDataSourceTransactionManager extends DataSourceTransactionManager {
    private Logger logger = LogManager.getLogger(this.getClass());

    /**
     * 只读事务到读库，读写事务到写库
     *
     * @param transaction
     * @param definition
     */
    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) {
        //设置数据源
        boolean readOnly = definition.isReadOnly();
        if (readOnly) {
            logger.debug("读数据源");
            DynamicDataSourceHolder.setDataSource(DynamicDataSourceGlobal.READ);
        } else {
            logger.debug("写数据源");
            DynamicDataSourceHolder.setDataSource(DynamicDataSourceGlobal.WRITE);
        }
        super.doBegin(transaction, definition);
    }

    /**
     * 清理本地线程的数据源
     *
     * @param transaction
     */
    @Override
    protected void doCleanupAfterCompletion(Object transaction) {
        super.doCleanupAfterCompletion(transaction);
        DynamicDataSourceHolder.clearDataSource();
    }
}
