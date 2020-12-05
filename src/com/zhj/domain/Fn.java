package com.zhj.domain;

import java.util.List;

/**
 * @author     ：
 * @date       ：Created in 2020/12/5 上午 08:47
 */

public class Fn {
    private Integer fid ;
    private String fname ;
    private String fhref ;
    private Integer flag ;
    private Integer pid ;
    private String yul1;
    private String yul2;

    private List<Fn> carte;

    public Fn() {
    }

    public Fn(Integer fid, String fname, String fhref, Integer flag, Integer pid, String yul1, String yul2, List<Fn> carte) {
        this.fid = fid;
        this.fname = fname;
        this.fhref = fhref;
        this.flag = flag;
        this.pid = pid;
        this.yul1 = yul1;
        this.yul2 = yul2;
        this.carte = carte;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFhref() {
        return fhref;
    }

    public void setFhref(String fhref) {
        this.fhref = fhref;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
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

    public List<Fn> getCarte() {
        return carte;
    }

    public void setCarte(List<Fn> carte) {
        this.carte = carte;
    }
}
