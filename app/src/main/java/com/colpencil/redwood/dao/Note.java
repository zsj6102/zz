package com.colpencil.redwood.dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table NOTE.
 */
public class Note {

    private Long id;
    private Integer ote_id;
    private String ote_title;
    private String ote_content;
    private Long createtime;
    private Long systemTime;
    private String url;
    private String nickname;
    private String face;
    private Integer com_count;
    private Integer like_count;
    private Integer sec_id;

    public Note() {
    }

    public Note(Long id) {
        this.id = id;
    }

    public Note(Long id, Integer ote_id, String ote_title, String ote_content, Long createtime, Long systemTime, String url, String nickname, String face, Integer com_count, Integer like_count, Integer sec_id) {
        this.id = id;
        this.ote_id = ote_id;
        this.ote_title = ote_title;
        this.ote_content = ote_content;
        this.createtime = createtime;
        this.systemTime = systemTime;
        this.url = url;
        this.nickname = nickname;
        this.face = face;
        this.com_count = com_count;
        this.like_count = like_count;
        this.sec_id = sec_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOte_id() {
        return ote_id;
    }

    public void setOte_id(Integer ote_id) {
        this.ote_id = ote_id;
    }

    public String getOte_title() {
        return ote_title;
    }

    public void setOte_title(String ote_title) {
        this.ote_title = ote_title;
    }

    public String getOte_content() {
        return ote_content;
    }

    public void setOte_content(String ote_content) {
        this.ote_content = ote_content;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public Long getSystemTime() {
        return systemTime;
    }

    public void setSystemTime(Long systemTime) {
        this.systemTime = systemTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public Integer getCom_count() {
        return com_count;
    }

    public void setCom_count(Integer com_count) {
        this.com_count = com_count;
    }

    public Integer getLike_count() {
        return like_count;
    }

    public void setLike_count(Integer like_count) {
        this.like_count = like_count;
    }

    public Integer getSec_id() {
        return sec_id;
    }

    public void setSec_id(Integer sec_id) {
        this.sec_id = sec_id;
    }

}
