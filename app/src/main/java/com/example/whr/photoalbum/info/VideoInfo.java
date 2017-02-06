package com.example.whr.photoalbum.info;

/**
 * Created by whr on 17-2-6.
 */

public class VideoInfo {
    private String videoName;
    private String videoPath;
    private long videoDuration;

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public long getVideoDuration() {
        return videoDuration;
    }

    public void setVideoDuration(long videoDuration) {
        this.videoDuration = videoDuration;
    }

    public VideoInfo(String videoName, String videoPath, long videoDuration) {

        this.videoName = videoName;
        this.videoPath = videoPath;
        this.videoDuration = videoDuration;
    }
}
