package kys24.goods.dto;

import java.util.ArrayList;

/**
 * @author Duolaimon
 *         17-5-5 上午11:41
 */
public class Page<T> implements PageResult<T> {
    private int pageNumber;

    private java.util.List dataList = new ArrayList<>();

    Page(int currentPage) {
        this.pageNumber = currentPage;
    }

    public int getPageNumber() {
        return pageNumber;
    }


    public java.util.List getDataList() {
        return dataList;
    }

    void setDataList(java.util.List dataList) {
        this.dataList = dataList;
    }

}
