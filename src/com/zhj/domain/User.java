package com.zhj.domain;

/**
 * @author ：
 * @date ：Created in 2020/11/20 下午 03:13
 */

public class User {
    private Integer uid;
    private String uName ;
    private String uPass ;
    private Integer age ;
    private String sex ;
    private String uRueName;
    private String yul1 ;
    private String yul2 ;

    public User() {
    }

    public User(Integer uid, String uName, String uPass, Integer age, String sex, String uRueName, String yul1, String yul2) {
        this.uid = uid;
        this.uName = uName;
        this.uPass = uPass;
        this.age = age;
        this.sex = sex;
        this.uRueName = uRueName;
        this.yul1 = yul1;
        this.yul2 = yul2;
    }


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUName() {
        return uName;
    }

    public void setUName(String uName) {
        this.uName = uName;
    }

    public String getUPass() {
        return uPass;
    }

    public void setUPass(String uPass) {
        this.uPass = uPass;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getURueName() {
        return uRueName;
    }

    public void setURueName(String uRueName) {
        this.uRueName = uRueName;
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

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uName='" + uName + '\'' +
                ", uPass='" + uPass + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", uRueName='" + uRueName + '\'' +
                ", yul1='" + yul1 + '\'' +
                ", yul2='" + yul2 + '\'' +
                '}';
    }
}

