package kys24.goods.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Duolaimon
 *         17-5-5 上午11:41
 */
public class Page<T> implements PageResult<T>{
    private int pageNumber;

    private List<T> dataList = new ArrayList<>();

    Page(int currentPage) {
        this.pageNumber = currentPage;
    }

    public int getPageNumber() {
        return pageNumber;
    }


    public List<T> getDataList() {
        return dataList;
    }

    void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

}
