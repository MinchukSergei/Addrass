package by.bsu.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user_data")
public class UserData {
    @Id
    @Column(name = "pk_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkId;

    @Column(name = "user_login")
    private String userLogin;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_phone_field")
    private String userPhoneField;

    @Column(name = "user_email_field")
    private String userEmailField;

    @Column(name = "user_organization_field")
    private String userOrganizationField;

    @Column(name = "user_address_field")
    private String userAddressField;

    @Column(name = "user_notes_field")
    private String userNotesField;

    @OneToOne
    @JoinColumn(name = "fk_user_photo")
    private UserIcon fkUserPhoto;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fkUserMain", cascade = CascadeType.ALL)
    private Set<ContactList> contacts;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fkUserMain", cascade = CascadeType.ALL)
    private Set<FriendList> friends;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userOwner")
    private Set<UserEvent> ownEvents;

    public UserData() {
    }

    public Long getPkId() {
        return pkId;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @JsonIgnore
    public String getUserPassword() {
        return userPassword;
    }

    @JsonProperty
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhoneField() {
        return userPhoneField;
    }

    public void setUserPhoneField(String userPhoneField) {
        this.userPhoneField = userPhoneField;
    }

    public String getUserEmailField() {
        return userEmailField;
    }

    public void setUserEmailField(String userEmailField) {
        this.userEmailField = userEmailField;
    }

    public String getUserOrganizationField() {
        return userOrganizationField;
    }

    public void setUserOrganizationField(String userOrganizationField) {
        this.userOrganizationField = userOrganizationField;
    }

    public String getUserAddressField() {
        return userAddressField;
    }

    public void setUserAddressField(String userAddressField) {
        this.userAddressField = userAddressField;
    }

    public String getUserNotesField() {
        return userNotesField;
    }

    public void setUserNotesField(String userNotesField) {
        this.userNotesField = userNotesField;
    }

    public UserIcon getFkUserPhoto() {
        return fkUserPhoto;
    }

    public void setFkUserPhoto(UserIcon fkUserPhoto) {
        this.fkUserPhoto = fkUserPhoto;
    }

    public Set<ContactList> getContacts() {
        return contacts;
    }

    public void setContacts(Set<ContactList> contacts) {
        this.contacts = contacts;
    }

    public Set<FriendList> getFriends() {
        return friends;
    }

    public void setFriends(Set<FriendList> friends) {
        this.friends = friends;
    }

    public Set<UserEvent> getOwnEvents() {
        return ownEvents;
    }

    public void setOwnEvents(Set<UserEvent> ownEvents) {
        this.ownEvents = ownEvents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserData userData = (UserData) o;

        if (pkId != null ? !pkId.equals(userData.pkId) : userData.pkId != null) return false;
        if (userLogin != null ? !userLogin.equals(userData.userLogin) : userData.userLogin != null) return false;
        if (userPassword != null ? !userPassword.equals(userData.userPassword) : userData.userPassword != null)
            return false;
        if (userName != null ? !userName.equals(userData.userName) : userData.userName != null) return false;
        if (userPhoneField != null ? !userPhoneField.equals(userData.userPhoneField) : userData.userPhoneField != null)
            return false;
        if (userEmailField != null ? !userEmailField.equals(userData.userEmailField) : userData.userEmailField != null)
            return false;
        if (userOrganizationField != null ? !userOrganizationField.equals(userData.userOrganizationField) : userData.userOrganizationField != null)
            return false;
        if (userAddressField != null ? !userAddressField.equals(userData.userAddressField) : userData.userAddressField != null)
            return false;
        if (userNotesField != null ? !userNotesField.equals(userData.userNotesField) : userData.userNotesField != null)
            return false;
        return fkUserPhoto != null ? fkUserPhoto.equals(userData.fkUserPhoto) : userData.fkUserPhoto == null;

    }

    @Override
    public int hashCode() {
        int result = pkId != null ? pkId.hashCode() : 0;
        result = 31 * result + (userLogin != null ? userLogin.hashCode() : 0);
        result = 31 * result + (userPassword != null ? userPassword.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userPhoneField != null ? userPhoneField.hashCode() : 0);
        result = 31 * result + (userEmailField != null ? userEmailField.hashCode() : 0);
        result = 31 * result + (userOrganizationField != null ? userOrganizationField.hashCode() : 0);
        result = 31 * result + (userAddressField != null ? userAddressField.hashCode() : 0);
        result = 31 * result + (userNotesField != null ? userNotesField.hashCode() : 0);
        result = 31 * result + (fkUserPhoto != null ? fkUserPhoto.hashCode() : 0);
        return result;
    }
}