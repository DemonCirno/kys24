package kys24.goods.dto;

import java.util.List;

/**
 * @author Duolaimon
 *         17-5-12 下午7:42
 */
public class SearchResult<T> {
    private int num;
    private List list;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public SearchResult(List list) {
        this.list = list;
        num = list.size();
    }
}
