package by.bsu.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_color")
public class UserColor {
    public final static String DEFAULT_COLOR = "default";

    @Id
    @Column(name = "pk_id", nullable = false)
    @JsonIgnore
    private Byte pkId;

    @Column(name = "color", nullable = false, unique = true)
    private Integer color;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public Byte getPkId() {
        return pkId;
    }

    @JsonIgnore
    public void setPkId(Byte pkId) {
        this.pkId = pkId;
    }

    public Integer getColor() {
        return color;
    }

    @JsonIgnore
    public void setColor(Integer color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserColor color1 = (UserColor) o;

        if (pkId != null ? !pkId.equals(color1.pkId) : color1.pkId != null) return false;
        if (color != null ? !color.equals(color1.color) : color1.color != null) return false;
        return name != null ? name.equals(color1.name) : color1.name == null;

    }

    @Override
    public int hashCode() {
        int result = pkId != null ? pkId.hashCode() : 0;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
