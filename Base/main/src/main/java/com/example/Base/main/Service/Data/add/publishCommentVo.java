package com.example.Base.main.Service.Data.add;

public class publishCommentVo {
   private String publish_content;
   private String video_id;

    public publishCommentVo(String publish_content, String video_id) {
        this.publish_content = publish_content;
        this.video_id = video_id;
    }

    public String getPublish_content() {
        return publish_content;
    }

    public void setPublish_content(String publish_content) {
        this.publish_content = publish_content;
    }

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }
}
