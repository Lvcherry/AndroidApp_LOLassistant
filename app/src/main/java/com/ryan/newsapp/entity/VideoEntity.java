package com.ryan.newsapp.entity;

import java.util.List;

/*
    视频实体类
 */
public class VideoEntity {

    private int totalPage;
    private String currentPage;
    private String type;
    private List<TypesEntity> types;
    private List<ItemEntity> item;

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTypes(List<TypesEntity> types) {
        this.types = types;
    }

    public void setItem(List<ItemEntity> item) {
        this.item = item;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public String getType() {
        return type;
    }

    public List<TypesEntity> getTypes() {
        return types;
    }

    public List<ItemEntity> getItem() {
        return item;
    }

    public static class TypesEntity {
        /**
         * id : clientvideo_9
         * name : 娱乐
         * chType : video
         * position : down
         */

        private String id;
        private String name;
        private String chType;
        private String position;

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setChType(String chType) {
            this.chType = chType;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getChType() {
            return chType;
        }

        public String getPosition() {
            return position;
        }
    }

    public static class ItemEntity {

        private String praise;
        private String tread;
        private String playTime;
        private String documentId;
        private String title;
        private String image;
        private String thumbnail;
        private String guid;
        private String commentsall;
        private String duration;
        private String shareUrl;
        private String commentsUrl;
        private String video_url;
        private int video_size;

        public void setPraise(String praise) {
            this.praise = praise;
        }

        public void setTread(String tread) {
            this.tread = tread;
        }

        public void setPlayTime(String playTime) {
            this.playTime = playTime;
        }

        public void setDocumentId(String documentId) {
            this.documentId = documentId;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public void setCommentsall(String commentsall) {
            this.commentsall = commentsall;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public void setShareUrl(String shareUrl) {
            this.shareUrl = shareUrl;
        }

        public void setCommentsUrl(String commentsUrl) {
            this.commentsUrl = commentsUrl;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public void setVideo_size(int video_size) {
            this.video_size = video_size;
        }

        public String getPraise() {
            return praise;
        }

        public String getTread() {
            return tread;
        }

        public String getPlayTime() {
            return playTime;
        }

        public String getDocumentId() {
            return documentId;
        }

        public String getTitle() {
            return title;
        }

        public String getImage() {
            return image;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public String getGuid() {
            return guid;
        }

        public String getCommentsall() {
            return commentsall;
        }

        public String getDuration() {
            return duration;
        }

        public String getShareUrl() {
            return shareUrl;
        }

        public String getCommentsUrl() {
            return commentsUrl;
        }

        public String getVideo_url() {
            return video_url;
        }

        public int getVideo_size() {
            return video_size;
        }
    }
}