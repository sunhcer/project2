package com.cskaoyan.mall.bean;

import java.util.Arrays;

public class Stat<T> {
    private String columns[];

    private T rows[];

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public T[] getRows() {
        return rows;
    }

    public void setRows(T[] rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "Stat{" +
                "columns=" + Arrays.toString(columns) +
                ", rows=" + Arrays.toString(rows) +
                '}';
    }
}
