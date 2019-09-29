package com.yb.medical_online.item.bean;

public class BaseForm{
    private Integer currentPage=1;
    private Integer pageSize=10;

    /**
     * pageStart
     */
    private Integer ps=0;


    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    public Integer getPs() {
        return  this.pageSize*(this.currentPage-1);//this.currentPage*this.pageSize-this.pageSize;
    }

    public void setPs(Integer ps) {
        this.ps = ps;
    }
}
