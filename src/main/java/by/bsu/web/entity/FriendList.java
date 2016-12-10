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
    @JsonIgnore
    private Long pkId;

    @JsonIgnore
    @Column(name = "fk_user_main", nullable = false)
    private Long fkUserMain;

    @ManyToOne
    @JoinColumn(name = "fk_user_friend")
    private UserData fkUserFriendEntity;

    @ManyToOne
    @JoinColumn(name = "fk_user_color")
    private UserColor fkUserColorEntity;

    @ManyToOne
    @JoinColumn(name = "fk_user_group")
    private UserGroup fkUserGroupEntity;

    @Column(name = "black_list", nullable = false, columnDefinition = "BIT", length = 1)
    private Boolean blackList;

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

    public UserData getFkUserFriendEntity() {
        return fkUserFriendEntity;
    }

    public void setFkUserFriendEntity(UserData fkUserFriendEntity) {
        this.fkUserFriendEntity = fkUserFriendEntity;
    }

    public UserColor getFkUserColorEntity() {
        return fkUserColorEntity;
    }

    public void setFkUserColorEntity(UserColor fkUserColorEntity) {
        this.fkUserColorEntity = fkUserColorEntity;
    }

    public UserGroup getFkUserGroupEntity() {
        return fkUserGroupEntity;
    }

    public void setFkUserGroupEntity(UserGroup fkUserGroupEntity) {
        this.fkUserGroupEntity = fkUserGroupEntity;
    }

    public Boolean getBlackList() {
        return blackList;
    }

    public void setBlackList(Boolean blackList) {
        this.blackList = blackList;
    }
}
