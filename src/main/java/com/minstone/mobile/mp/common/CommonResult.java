package com.minstone.mobile.mp.common;

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
    private T pager;


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

    public T getPager() {
        return pager;
    }

    public void setPager(T pager) {
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
}
