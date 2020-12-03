package com.zhj.connect;

import java.util.List;

/**
 * @author ：
 * @date ：Created in 2020/11/23 下午 01:50
 */

public class Page {
    // 每一页显示的最大值
    private Integer max;

    private Integer page;

    private Integer size;

    private List<?> list;
    public Page() {
    }

    public Page(Integer max, Integer page, Integer size, List<?> list) {
        this.max = max;
        this.page = page;
        this.size = size;
        this.list = list;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Page{" +
                "max=" + max +
                ", page=" + page +
                ", size=" + size +
                ", list=" + list +
                '}';
    }
}
