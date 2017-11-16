package com.minstone.mobile.mp.common;

import com.github.pagehelper.PageInfo;

/**
 * Created by huangyg on 2017/8/7.
 */
public class CommonResult<T> {
    //    描述
    private String desc;
    //    状态码
    private Integer status;
    //    服务器响应时间
    private String time;
    //    返回数据
    private T data;
    //    分页信息
    private CommonPage pager;



    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public CommonPage getPager() {
        return pager;
    }

    public void setPager(CommonPage pager) {
        this.pager = pager;
    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "desc='" + desc + '\'' +
                ", status=" + status +
                ", time='" + time + '\'' +
                ", data=" + data +
                ", pager=" + pager +
                '}';
    }

    //    todo 分页实体
    public static class CommonPage {

        private Integer pageIndex;
        private Integer pageSize;
        private Integer pageCount;
        private Long itemCount;

        public CommonPage(PageInfo pageInfo) {
            this.setPageIndex(pageInfo.getPageNum());
            this.setPageSize(pageInfo.getPageSize());
            this.setPageCount(pageInfo.getPages());
            this.setItemCount(Long.valueOf(pageInfo.getTotal()));
        }
        public Integer getPageIndex() {
            return pageIndex;
        }

        public void setPageIndex(Integer pageIndex) {
            this.pageIndex = pageIndex;
        }

        public Integer getPageSize() {
            return pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }

        public Integer getPageCount() {
            return pageCount;
        }

        public void setPageCount(Integer pageCount) {
            this.pageCount = pageCount;
        }

        public Long getItemCount() {
            return itemCount;
        }

        public void setItemCount(Long itemCount) {
            this.itemCount = itemCount;
        }


        @Override
        public String toString() {
            return "CommonPage{" +
                    "pageIndex=" + pageIndex +
                    ", pageSize=" + pageSize +
                    ", pageCount=" + pageCount +
                    ", itemCount=" + itemCount +
                    '}';
        }
    }
}
