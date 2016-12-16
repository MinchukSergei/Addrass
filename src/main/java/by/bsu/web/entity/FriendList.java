package by.bsu.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "friend_list")
public class FriendList {
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn (name = "fk_user_main")
    private UserData fkUserMain;

    @ManyToOne
    @JoinColumn(name = "fk_user_friend")
    private UserData fkUserFriend;

    @ManyToOne
    @JoinColumn(name = "fk_user_color")
    private UserColor fkUserColor;

    @ManyToOne
    @JoinColumn(name = "fk_user_group")
    private UserGroup fkUserGroup;

    @Column(name = "black_list", nullable = false, columnDefinition = "BIT", length = 1)
    private Boolean blackList;

    public Long getPkId() {
        return pkId;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    public UserData getFkUserMain() {
        return fkUserMain;
    }

    public void setFkUserMain(UserData fkUserMain) {
        this.fkUserMain = fkUserMain;
    }

    public UserData getFkUserFriend() {
        return fkUserFriend;
    }

    public void setFkUserFriend(UserData fkUserFriend) {
        this.fkUserFriend = fkUserFriend;
    }

    public UserColor getFkUserColor() {
        return fkUserColor;
    }

    public void setFkUserColor(UserColor fkUserColor) {
        this.fkUserColor = fkUserColor;
    }

    public UserGroup getFkUserGroup() {
        return fkUserGroup;
    }

    public void setFkUserGroup(UserGroup fkUserGroup) {
        this.fkUserGroup = fkUserGroup;
    }

    public Boolean getBlackList() {
        return blackList;
    }

    public void setBlackList(Boolean blackList) {
        this.blackList = blackList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FriendList that = (FriendList) o;

        if (pkId != null ? !pkId.equals(that.pkId) : that.pkId != null) return false;
        if (fkUserMain != null ? !fkUserMain.equals(that.fkUserMain) : that.fkUserMain != null) return false;
        if (fkUserFriend != null ? !fkUserFriend.equals(that.fkUserFriend) : that.fkUserFriend != null) return false;
        if (fkUserColor != null ? !fkUserColor.equals(that.fkUserColor) : that.fkUserColor != null) return false;
        if (fkUserGroup != null ? !fkUserGroup.equals(that.fkUserGroup) : that.fkUserGroup != null) return false;
        return blackList != null ? blackList.equals(that.blackList) : that.blackList == null;

    }

    @Override
    public int hashCode() {
        int result = pkId != null ? pkId.hashCode() : 0;
        result = 31 * result + (fkUserMain != null ? fkUserMain.hashCode() : 0);
        result = 31 * result + (fkUserFriend != null ? fkUserFriend.hashCode() : 0);
        result = 31 * result + (fkUserColor != null ? fkUserColor.hashCode() : 0);
        result = 31 * result + (fkUserGroup != null ? fkUserGroup.hashCode() : 0);
        result = 31 * result + (blackList != null ? blackList.hashCode() : 0);
        return result;
    }
}
