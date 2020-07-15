package com.jameni.jamenipagerlib.model;

public class ImageModel {

    private String imgUrl;
    private String imgId;
    private int imgType;//图片类型，0 网络图片，1本地图片 2本地资源id
    private int imgResId;

    public ImageModel(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public ImageModel(String imgUrl, String imgId) {
        this.imgUrl = imgUrl;
        this.imgId = imgId;
    }

    public ImageModel(String imgUrl, int imgType, int imgResId) {
        this.imgUrl = imgUrl;
        this.imgType = imgType;
        this.imgResId = imgResId;
    }

    public ImageModel(String imgUrl, String imgId, int imgType, int imgResId) {
        this.imgUrl = imgUrl;
        this.imgId = imgId;
        this.imgType = imgType;
        this.imgResId = imgResId;
    }

    public ImageModel(String imgUrl, int imgType) {

        this.imgUrl = imgUrl;
        this.imgType = imgType;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public int getImgType() {
        return imgType;
    }

    public void setImgType(int imgType) {
        this.imgType = imgType;
    }

    public int getImgResId() {
        return imgResId;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }
}
