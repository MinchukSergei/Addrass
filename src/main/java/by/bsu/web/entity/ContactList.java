package by.bsu.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "contact_list")
public class ContactList {
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkId;

    @ManyToOne
    @JoinColumn(name = "fk_user_friend")
    private UserData fkUserFriend;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_user_main")
    private UserData fkUserMain;

    @OneToOne
    @JoinColumn(name = "fk_user_color")
    private UserColor fkUserColor;

    public Long getPkId() {
        return pkId;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    public UserData getFkUserFriend() {
        return fkUserFriend;
    }

    public void setFkUserFriend(UserData fkUserFriend) {
        this.fkUserFriend = fkUserFriend;
    }

    public UserData getFkUserMain() {
        return fkUserMain;
    }

    public void setFkUserMain(UserData fkUserMain) {
        this.fkUserMain = fkUserMain;
    }

    public UserColor getFkUserColor() {
        return fkUserColor;
    }

    public void setFkUserColor(UserColor fkUserColor) {
        this.fkUserColor = fkUserColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactList that = (ContactList) o;

        if (pkId != null ? !pkId.equals(that.pkId) : that.pkId != null) return false;
        if (fkUserFriend != null ? !fkUserFriend.equals(that.fkUserFriend) : that.fkUserFriend != null) return false;
        if (fkUserMain != null ? !fkUserMain.equals(that.fkUserMain) : that.fkUserMain != null) return false;
        return fkUserColor != null ? fkUserColor.equals(that.fkUserColor) : that.fkUserColor == null;

    }

    @Override
    public int hashCode() {
        int result = pkId != null ? pkId.hashCode() : 0;
        result = 31 * result + (fkUserFriend != null ? fkUserFriend.hashCode() : 0);
        result = 31 * result + (fkUserMain != null ? fkUserMain.hashCode() : 0);
        result = 31 * result + (fkUserColor != null ? fkUserColor.hashCode() : 0);
        return result;
    }
}
