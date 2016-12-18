package com.athome.webmall.product.entities;

import java.util.List;

public class Page<T> {
	// ��ǰ�ڼ�ҳ
	private int pageNo;

	// ��ǰҳ��list
	private List<T> list;

	// ÿҳ��ʾ��������¼
	private int pageSize;

	// �ܼ�¼��
	private long totalItemNumber;

	// �������ж�pageNo���г�ʼ��
	public Page(int pageNo) {
		super();
		this.pageNo = pageNo;
	}

	// ��ȡpageNo��ͬʱ�����޶�����ȷ��ȡֵ��Χ��
	public int getPageNo() {
		if (pageNo < 1) {
			pageNo = 1;
		} else if (pageNo > getTotalPageNumber()) {
			pageNo = getTotalPageNumber();
		}
		return pageNo;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public List<T> getList() {
		return list;
	}

	// �������ݿ��в�ѯ���Ķ��󼯺ϸ���list��Ա����
	public void setList(List<T> list) {
		this.list = list;
	}

	// �������ݿ��в�ѯ���ļ�¼��ȷ����ҳ����
	public int getTotalPageNumber() {
		// int totalPageNubmer = (int) Math.ceil(totalItemNumber % pageSize);
		// return totalPageNubmer;
		int totalPageNubmer = (int)totalItemNumber/pageSize;
		if((totalItemNumber % pageSize) != 0){
			totalPageNubmer++;
		}
		return totalPageNubmer;
	}

	public void setTotalItemNumber(long totalItemNumber) {
		this.totalItemNumber = totalItemNumber;
	}

	// �ж��Ƿ�����һҳ
	public boolean hasNextPage() {
		if (getPageNo() != getTotalPageNumber()) {
			return true;
		}
		return false;
	}

	// �ж��Ƿ���ǰһҳ
	public boolean hasPrevPage() {
		if (getPageNo() != 1) {
			return true;
		}
		return false;
	}

	public int getPrevPage() {
		if (hasPrevPage()) {
			return getPageNo() - 1;
		}
		return getPageNo();
	}

	public int getNextPage() {
		if (hasNextPage()) {
			return getPageNo() + 1;
		}
		return getPageNo();
	}
}
