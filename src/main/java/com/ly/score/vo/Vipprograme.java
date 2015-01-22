package com.ly.score.vo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("vipprograme")
public class Vipprograme {

    @Id
    @Column
    private Long id;

    @Column
    private Long vipid;

    @Column
    private Long programeid;

    @Column
    private Long num;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVipid() {
        return vipid;
    }

    public void setVipid(Long vipid) {
        this.vipid = vipid;
    }

    public Long getProgrameid() {
        return programeid;
    }

    public void setProgrameid(Long programeid) {
        this.programeid = programeid;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }
}
