package com.lkf.common.page;

import java.io.Serializable;

/**
 * Created by kaifeng on 2017/5/7.
 */
public class PageOut<T> implements Serializable {
	private Integer total;
	private T detail;

	PageOut() {
	}

	PageOut(int total, T detail) {
		this.setTotal(total);
		this.setDetail(detail);
	}

	public static PageOut result(int total, Object detail) {
		return new PageOut(total, detail);
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public T getDetail() {
		return detail;
	}

	public void setDetail(T detail) {
		this.detail = detail;
	}
}
