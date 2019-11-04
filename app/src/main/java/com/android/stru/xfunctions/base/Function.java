package com.android.stru.xfunctions.base;

/**
 * 方法的抽象
 */
public abstract class Function {
    /**
     * 方法名称
     */
    private String name;

    public Function(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
