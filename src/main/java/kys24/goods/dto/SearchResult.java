package kys24.goods.dto;

/**
 * @author Duolaimon
 *         17-5-12 下午7:42
 */
public class SearchResult {
    private int num;
    private PageResult pageResult;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public PageResult getPageResult() {
        return pageResult;
    }

    public void setPageResult(PageResult pageResult) {
        this.pageResult = pageResult;
    }

    public SearchResult(int num, PageResult pageResult) {

        this.num = num;
        this.pageResult = pageResult;
    }
}
