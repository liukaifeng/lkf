package com.lkf.rwdb.mybatis;


public final class DynamicDataSourceHolder {

    private static final ThreadLocal<DynamicDataSourceGlobal> holder = new ThreadLocal<DynamicDataSourceGlobal>();

    private DynamicDataSourceHolder() {
        //
    }
/*设置数据源类型*/
    public static void setDataSource(DynamicDataSourceGlobal dataSource){
        holder.set(dataSource);
    }

    /*获取数据源类型*/
    public static DynamicDataSourceGlobal getDataSource(){
        return holder.get();
    }

    /*清空数据源类型*/
    public static void clearDataSource() {
        holder.remove();
    }

}
