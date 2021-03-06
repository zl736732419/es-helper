package com.zheng.es.model;

import com.zheng.es.enums.EnumExceptionCode;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *  查询响应结果
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年03月19日			zhenglian			    Initial.
 *
 * </pre>
 */
public class Response {
    /**
     * 网站域
     */
    private String domain;
    /**
     * 索引类型
     */
    private String type;
    /**
     * 分页页码
     */
    private Integer pageNo;
    /**
     * 分页大小
     */
    private Integer pageSize;
    /**
     * 召回记录总数
     */
    private Integer total;
    /**
     * 召回记录列表
     */
    private List<Map<String, Object>> data;
    /**
     * 响应码
     */
    private Integer code;
    /**
     * 响应消息
     */
    private String msg;
    /**
     * 日志
     */
    private Object log;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Map<String, Object>> getData() {
        return data;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public boolean isSuccess() {
        return Objects.equals(code, EnumExceptionCode.SUCCESS.getKey());
    }

    public Object getLog() {
        return log;
    }

    public void setLog(Object log) {
        this.log = log;
    }

    public static class Builder {
        private Response response;
        public Builder() {
            response = new Response();
        }
        
        public Builder pageNo(Integer pageNo) {
            response.setPageNo(pageNo);
            return this;
        }

        public Builder pageSize(Integer pageSize) {
            response.setPageSize(pageSize);
            return this;
        }

        public Builder total(Integer total) {
            response.setTotal(total);
            return this;
        }

        public Builder domain(String domain) {
            response.setDomain(domain);
            return this;
        }

        public Builder type(String type) {
            response.setType(type);
            return this;
        }
        
        public Builder data(List<Map<String, Object>> data) {
            response.setData(data);
            return this;
        }

        public Builder code(Integer code) {
            response.setCode(code);
            return this;
        }

        public Builder msg(String msg) {
            response.setMsg(msg);
            return this;
        }
        
        public Builder log(Object log) {
            response.setLog(log);
            return this;
        }

        public Response build() {
            return response;
        }
    }
    
    public static Response buildEmptyResponse() {
        return buildResponse(EnumExceptionCode.SUCCESS.getKey(), EnumExceptionCode.SUCCESS.getValue());
    }

    public static Response buildFailedResponse() {
        return buildResponse(EnumExceptionCode.SEARCH_ERROR.getKey(), EnumExceptionCode.SEARCH_ERROR.getValue());
    }
    
    private static Response buildResponse(Integer code, String msg) {
        Response response = new Response();
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }

    @Override
    public String toString() {
        return "Response{" +
                "domain='" + domain + '\'' +
                ", type='" + type + '\'' +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", total=" + total +
                ", data=" + data +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
