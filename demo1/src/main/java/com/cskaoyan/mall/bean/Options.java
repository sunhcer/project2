package com.cskaoyan.mall.bean;

public class Options {
    private int value;

    private String label;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Options{" +
                "value=" + value +
                ", label='" + label + '\'' +
                '}';
    }
}
