package com.zheng.es.model;

import com.zheng.es.utils.StringUtil;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *  TODO
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年04月04日			zhenglian			    Initial.
 *
 * </pre>
 */
public class EsPage {

    /**
     * 分页页码
     */
    private Integer pageNo = 1;
    /**
     * 分页大小
     */
    private Integer size = 36;

    /**
     * 当前页开始下标
     */
    private Integer from;

    private EsPage() {}
    
    private void init() {
        from = (pageNo - 1) * size;
    }

    public Integer getSize() {
        return size;
    }

    public Integer getFrom() {
        return from;
    }

    public static class Builder {
        private EsPage page;
        public Builder() {
            page = new EsPage();
        }
        
        public Builder pageNo(Integer pageNo) {
            if (StringUtil.isNotEmpty(pageNo)) {
                page.pageNo = pageNo;
            }
            return this;
        }

        public Builder size(Integer size) {
            if (StringUtil.isNotEmpty(size)) {
                page.size = size;
            }
            return this;
        }
        
        public EsPage build() {
            page.init();
            return page;
        }
    }
}
