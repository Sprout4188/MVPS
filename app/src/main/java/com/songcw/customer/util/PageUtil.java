package com.songcw.customer.util;

/**
 * @name employee
 * @class name：com.songcw.customer.util
 * @class describe
 * @anthor wjb
 * @time 2018/9/20 17:26
 * @change
 * @chang time
 * @class describe
 */
public class PageUtil {
    /**
     * 是否还有更多
     *
     * @param total     总条数
     * @param pageSize  每页有多少条
     * @param pageIndex 当前页码（从第一页开始）
     * @return
     */
    public static boolean hasMorePage(int total, int pageSize, int pageIndex) {
        int maxPageSize = (int) (Math.ceil((total * 1d) / (pageSize * 1d)));
        return pageIndex < maxPageSize;
    }
}
