package com.example.whr.photoalbum.info;

/**
 * Created by whr on 17-2-4.
 */

public class ParentInfo {
    private String name;
    private String childPath;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChildPath() {
        return childPath;
    }

    public void setChildPath(String childPath) {
        this.childPath = childPath;
    }

    public ParentInfo(String name, String childPath) {

        this.name = name;
        this.childPath = childPath;
    }
}
