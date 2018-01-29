package com.minstone.mobile.mp.utils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import me.chanjar.weixin.mp.bean.kefu.result.WxMpKfMsgRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangyg
 * @description
 * @since 2018/1/17
 */
public class PagerUtil<T> {

    public static<T> PageInfo lowPager(int currentPage,int pageSize,List result){
        // 手动分页处理
        PageHelper.startPage(currentPage,pageSize);
        PageInfo<T> page = new PageInfo<>(result);
        int totalPages = (int) Math.ceil((double) page.getSize() / pageSize);
        if (currentPage > totalPages) {
            currentPage = totalPages;
        }

        if (pageSize > page.getSize()) {
            currentPage = 1;
            pageSize = page.getSize();
        }

        page.setPageNum(currentPage); // 设置当前为第几页
        page.setPages(totalPages);    // 设置总的页数

        int endIndex = (currentPage * pageSize) < page.getSize() ? (currentPage * pageSize) : page.getSize(); // 某一页最后一条占总数的下标
        // 如果当前页为最后一页，如果当前页数*每页显示数量比实际页数大,则最后一页的实际数量为如下
        int currentPageSize = currentPage * pageSize > page.getSize() ? (page.getSize() - (currentPage-1) * pageSize) : pageSize;
        page.setPageSize(currentPageSize);  // 设置每一页显示的数量

        page.setList(result.subList((currentPage - 1) * pageSize, endIndex));

        return page;
    }

}
