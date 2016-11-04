package by.bsu.web.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_data")
public class UserData {
    @Id
    @Column(name = "user_login", nullable = false)
    private String userLogin;

    @Column(name = "user_password", nullable = false)
    private String userPassword;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_phone_field", nullable = false)
    private String userPhoneField;

    @Column(name = "user_email_field", nullable = false)
    private String userEmailField;

    @Column(name = "user_organization_field", nullable = false)
    private String userOrganizationField;

    @Column(name = "user_address_field", nullable = false)
    private String userAddressField;

    @Column(name = "user_notes_field", nullable = false)
    private String userNotesField;

    @Column(name = "user_photo", nullable = false)
    private byte[] userPhoto;

    public UserData() {
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

    public byte[] getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(byte[] userPhoto) {
        this.userPhoto = userPhoto;
    }

}
