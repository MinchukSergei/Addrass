package by.bsu.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
@Table(name = "contact_list")
public class ContactList {
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long pkId;

    @JsonIgnore
    @Column(name = "fk_user_main", nullable = false)
    private Long fkUserMain;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user_friend", nullable = false)
    private UserData fkUserFriend;

    @Column(name = "fk_user_color")
    private Byte fkUserColor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user_color", insertable = false, updatable = false)
    private UserColor fkUserColorEntity;

    public Long getPkId() {
        return pkId;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    public Long getFkUserMain() {
        return fkUserMain;
    }

    public void setFkUserMain(Long fkUserMain) {
        this.fkUserMain = fkUserMain;
    }

    public UserData getFkUserFriend() {
        return fkUserFriend;
    }

    public void setFkUserFriend(UserData fkUserFriend) {
        this.fkUserFriend = fkUserFriend;
    }

    @JsonIgnore
    public Byte getFkUserColor() {
        return fkUserColor;
    }

    @JsonProperty
    public void setFkUserColor(Byte fkUserColor) {
        this.fkUserColor = fkUserColor;
    }

    public UserColor getFkUserColorEntity() {
        return fkUserColorEntity;
    }

    public void setFkUserColorEntity(UserColor fkUserColorEntity) {
        this.fkUserColorEntity = fkUserColorEntity;
    }
}
