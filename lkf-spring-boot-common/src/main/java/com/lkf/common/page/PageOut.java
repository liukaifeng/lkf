package com.lkf.common.page;

import java.io.Serializable;

/**
 * Created by kaifeng on 2017/5/7.
 */
public class PageOut<T> implements Serializable {
	private Integer total;
	private T detail;
}
