package bean;

import java.util.Date;

public class Message {
    private int mid;//文章id
    private String name;//文章名称
    private String autho;//作者
    private String diff;//文章的难度
    private String message;//文章信息
    private Date rel_time;//注册时间
    private Date upd_time;//更新时间

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAutho() {
        return autho;
    }

    public void setAutho(String autho) {
        this.autho = autho;
    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getRel_time() {
        return rel_time;
    }

    public void setRel_time(Date rel_time) {
        this.rel_time = rel_time;
    }

    public Date getUpd_time() {
        return upd_time;
    }

    public void setUpd_time(Date upd_time) {
        this.upd_time = upd_time;
    }
}
