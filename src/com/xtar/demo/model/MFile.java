package com.xtar.demo.model;

public class MFile {
    private int id;
    public String name;
    public int len;

    public MFile() {
        id = 0;
        name = "";
        len = 0;
    }

    public MFile(int id) {
        this();
        this.id = id;
    }

    @Override
    public String toString() {
        return "[MFile: " + id + "," + name + "," + len + "]";
    }

}
