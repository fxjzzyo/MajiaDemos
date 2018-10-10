package com.fxj.earnmoney.javabean;

import java.io.Serializable;

/**
 * Created by v_fanlulin on 2018/10/9.
 */

public class Info implements Serializable {
    private int id;
    private String title;
    private String salary;
    private String time;//招录时间段
    private String personCount;
    private String type;//短期/长期
    private String detail;//职位描述
    private String address;//地址
    private int picture;

    public Info(){

    }

    public Info(int id, String title, String salary, String time, String personCount, String type, String detail, String address, int picture) {
        this.id = id;
        this.title = title;
        this.salary = salary;
        this.time = time;
        this.personCount = personCount;
        this.type = type;
        this.detail = detail;
        this.address = address;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getPersonCount() {
        return personCount;
    }

    public void setPersonCount(String personCount) {
        this.personCount = personCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Info{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", salary='" + salary + '\'' +
                ", time='" + time + '\'' +
                ", personCount='" + personCount + '\'' +
                ", type='" + type + '\'' +
                ", detail='" + detail + '\'' +
                ", address='" + address + '\'' +
                ", picture=" + picture +
                '}';
    }
}
