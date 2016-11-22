package by.bsu.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_color")
public class UserColor {
    @Id
    @Column(name = "pk_id", nullable = false)
    @JsonIgnore
    private Byte pkId;

    @Column(name = "color", nullable = false)
    private int color;

    public Byte getPkId() {
        return pkId;
    }

    public void setPkId(Byte pkId) {
        this.pkId = pkId;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
