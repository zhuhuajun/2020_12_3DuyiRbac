package com.zhj.domain;

/**
 * @author ：
 * @date ：Created in 2020/12/1 下午 01:46
 */

public class Role {
    private Integer rid;
    private String rname ;
    private String yul1;
    private String yul2;

    public Role() {
    }

    public Role(Integer rid, String name, String yul1, String yul2) {
        this.rid = rid;
        this.rname = name;
        this.yul1 = yul1;
        this.yul2 = yul2;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String name) {
        this.rname = name;
    }

    public String getYul1() {
        return yul1;
    }

    public void setYul1(String yul1) {
        this.yul1 = yul1;
    }

    public String getYul2() {
        return yul2;
    }

    public void setYul2(String yul2) {
        this.yul2 = yul2;
    }
}
