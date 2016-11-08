package by.bsu.web.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_data")
public class UserData {
    @Id
    @Column(name = "pk_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pkId;

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

    @Column(name = "fk_user_photo")
    private long userPhoto;

    public UserData() {
    }

    public long getPkId() {
        return pkId;
    }

    public void setPkId(long pkId) {
        this.pkId = pkId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

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

    public long getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(long userPhoto) {
        this.userPhoto = userPhoto;
    }
}
