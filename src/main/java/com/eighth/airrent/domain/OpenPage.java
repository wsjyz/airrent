package com.eighth.airrent.domain;

import java.util.ArrayList;
import java.util.List;


public class OpenPage<T> {

    public final static int DEFAULT_PAGE_SIZE = 10;

    protected int pageNo = 1;

    protected int pageSize = DEFAULT_PAGE_SIZE;
    protected boolean autoCount = true;
    protected boolean autoPaging = true;

    protected List<T> rows = new ArrayList<T>();

    protected long total = 0;


    public OpenPage() {
    }

    public OpenPage(int pageSize) {
        this.pageSize = pageSize;
    }


    public int getPageNo() {
        return pageNo;
    }


    public void setPageNo(final int pageNo) {
        this.pageNo = pageNo;

        if (pageNo < 1) {
            this.pageNo = 1;
        }
    }


    public int getPageSize() {
        return pageSize;
    }


    public void setPageSize(final int pageSize) {
        this.pageSize = pageSize;
    }


    public int getFirst() {
        return ((pageNo - 1) * pageSize) + 1;
    }


    public boolean isAutoCount() {
        return autoCount;
    }


    public void setAutoCount(final boolean autoCount) {
        this.autoCount = autoCount;
    }


    public List<T> getRows() {
        return rows;
    }


    public void setRows(final List<T> result) {
        this.rows = result;
    }


    public long getTotal() {
        return total;
    }


    public void setTotal(final long totalCount) {
        this.total = totalCount;
    }


    public long getTotalPages() {
        long count = total / pageSize;
        if (total % pageSize > 0) {
            count++;
        }
        return count;
    }


    public boolean isHasNext() {
        return (pageNo + 1 <= getTotalPages());
    }

    public int getNextPage() {
        if (isHasNext()) {
            return pageNo + 1;
        } else {
            return pageNo;
        }
    }


    public boolean isHasPre() {
        return (pageNo - 1 >= 1);
    }


    public int getPrePage() {
        if (isHasPre()) {
            return pageNo - 1;
        } else {
            return pageNo;
        }
    }


    public boolean isAutoPaging() {
        return autoPaging;
    }

    public void setAutoPaging(boolean autoPaging) {
        this.autoPaging = autoPaging;
    }

    @Override
    public String toString() {
        return "Page{" +
                "total=" + total +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", autoCount=" + autoCount +
                '}';
    }
}
