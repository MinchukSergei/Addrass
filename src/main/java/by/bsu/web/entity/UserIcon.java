package by.bsu.web.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "user_icon")
public class UserIcon {
    public static final String DEFAULT_ICON_NAME = "default";

    @Id
    @Column(name = "pk_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long pkId;

    @Lob
    @Column(name = "icon_bytes", nullable = false, columnDefinition = "LONGBLOB")
    private byte[] iconBytes;

    @Column(name = "icon_name", nullable = false)
    private String iconName;

    public UserIcon() {
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public Long getPkId() {
        return pkId;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    @JsonIgnore
    public byte[] getIconBytes() {
        return iconBytes;
    }

    @JsonProperty
    public void setIconBytes(byte[] iconBytes) {
        this.iconBytes = iconBytes;
    }
}
