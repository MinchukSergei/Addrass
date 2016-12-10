package by.bsu.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_group")
public class UserGroup {
    public static final String DEFAULT_GROUP = "other";

    @Id
    @Column(name = "pk_id", nullable = false)
    private Byte pkId;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public Byte getPkId() {
        return pkId;
    }

    @JsonIgnore
    public void setPkId(Byte pkId) {
        this.pkId = pkId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
