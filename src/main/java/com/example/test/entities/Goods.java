package com.example.test.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "goods")
public class Goods {
    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "goods_id")
    UUID goodsId;

    @Column(name = "name")
    String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "cr_user",
            referencedColumnName = "user_id",
            nullable = false
    )
    SystemUser crUser;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    @Column(name = "cr_datetime", insertable = false)
    LocalDateTime crDatetime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "up_user",
            referencedColumnName = "user_id"
    )
    SystemUser upUser;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @UpdateTimestamp
    @Column(name = "up_datetime")
    LocalDateTime upDatetime;

    public UUID getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(UUID goodsId) {
        this.goodsId = goodsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public SystemUser getCrUser() {
        return crUser;
    }

    public void setCrUser(SystemUser crUser) {
        this.crUser = crUser;
    }

    public LocalDateTime getCrDatetime() {
        return crDatetime;
    }

    public void setCrDatetime(LocalDateTime crDatetime) {
        this.crDatetime = crDatetime;
    }

    public SystemUser getUpUser() {
        return upUser;
    }

    public void setUpUser(SystemUser upUser) {
        this.upUser = upUser;
    }

    public LocalDateTime getUpDatetime() {
        return upDatetime;
    }

    public void setUpDatetime(LocalDateTime upDatetime) {
        this.upDatetime = upDatetime;
    }
}
