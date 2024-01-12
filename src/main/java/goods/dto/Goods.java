package goods.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "goods")
public class Goods {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goods_id")
    Integer googsId;

    @Column(name = "name")
    String name;

    @Column(name = "cr_user")
    Integer crUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "cr_datetme", insertable = false)
    LocalDateTime crDatetime;

    @Column(name = "up_user")
    Integer upUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "up_datetime", insertable = false)
    LocalDateTime upDatetime;

    public Integer getGoogsId() {
        return googsId;
    }

    public void setGoogsId(Integer googsId) {
        this.googsId = googsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCrUser() {
        return crUser;
    }

    public void setCrUser(Integer crUser) {
        this.crUser = crUser;
    }

    public LocalDateTime getCrDatetime() {
        return crDatetime;
    }

    public void setCrDatetime(LocalDateTime crDatetime) {
        this.crDatetime = crDatetime;
    }

    public Integer getUpUser() {
        return upUser;
    }

    public void setUpUser(Integer upUser) {
        this.upUser = upUser;
    }

    public LocalDateTime getUpDatetime() {
        return upDatetime;
    }

    public void setUpDatetime(LocalDateTime upDatetime) {
        this.upDatetime = upDatetime;
    }
}
