package com.zachary.utli.BaseBean;

import org.litepal.crud.DataSupport;

/**
 * Created by Zachary on 2018-04-18.
 */
public class OrderInfoLitePal extends DataSupport {


    private int     id;
    private String  nu;
    private String  com;
    private String  state;
    private String  time;
    private boolean isselect;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public boolean isselect() {
        return isselect;
    }

    public void setIsselect(boolean isselect) {
        this.isselect = isselect;
    }


    public String getNu() {
        return nu;
    }

    public void setNu(String nu) {
        this.nu = nu;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
