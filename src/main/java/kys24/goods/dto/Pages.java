package kys24.goods.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Duolaimon
 *         17-5-5 上午11:33
 */
public class Pages<T> implements PageResult<T> {
    private final Logger logger = LoggerFactory.getLogger(Pages.class);
    private int pageSize;

    private List<Page<T>> pages;

    private Pages(int pageSize, List<T> list) {
        pages = new ArrayList<>();
        this.pageSize = pageSize;
        for (int i = 0; i < Math.ceil((double) list.size() / pageSize); i++) {

            Page<T> page = new Page<>(i + 1);
            page.setDataList(list.stream()
                    .skip((long) i * pageSize)
                    .limit(pageSize)
                    .collect(Collectors.toList()));
            pages.add(page);
        }
    }

    private Page<T> getPageByPageNumber(int pageNumber) {
        Page<T> page = new Page<>(pageNumber);
        Optional<Page<T>> optional = pages.stream()
                .filter(c -> c.getPageNumber() == pageNumber)
                .findAny();
        if (optional.isPresent()) {
            page = optional.get();
        }
        return page;
    }

    /**
     * 处理列表成页
     *
     * @param pageSize 每页容量
     * @param list     列表
     * @return 页结果
     */
    public static <T> Pages<T> getPageResultHandle(int pageSize, List<T> list) {
        return new Pages<>(pageSize, list);
    }

    public static <T> Page<T> getPageHandle(int pageSize, List<T> list, int pageNumber) {
        return getPageResultHandle(pageSize, list).getPageByPageNumber(pageNumber);
    }

    public int getPageSize() {
        return pageSize;
    }

    public List<Page<T>> getPages() {
        return pages;
    }
}
