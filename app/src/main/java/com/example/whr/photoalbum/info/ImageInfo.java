package com.example.whr.photoalbum.info;

/**
 * Created by whr on 16-12-26.
 */

public class ImageInfo {
    private String imageName;
    private String imagePath;
    private boolean checkState;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public boolean isCheckState() {
        return checkState;
    }

    public void setCheckState(boolean checkState) {
        this.checkState = checkState;
    }

    public ImageInfo(String imageName, String imagePath, boolean checkState) {
        this.imageName = imageName;
        this.imagePath = imagePath;
        this.checkState = checkState;
    }
}
