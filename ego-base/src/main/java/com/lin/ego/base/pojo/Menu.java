package com.lin.ego.base.pojo;

import java.util.List;

public class Menu {
    private List<?> data;

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public Menu(List<?> data) {
        this.data = data;
    }

    public Menu() {
    }
}
