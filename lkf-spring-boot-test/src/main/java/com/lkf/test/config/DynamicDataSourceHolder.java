package com.lkf.test.config;

/*本地线程全局变量，用来存放当前操作是读还是写*/
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
