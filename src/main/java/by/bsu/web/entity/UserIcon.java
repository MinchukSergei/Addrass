package by.bsu.web.entity;


import javax.persistence.*;

@Entity
@Table(name = "user_icon")
public class UserIcon {

    @Id
    @Column(name = "pk_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pkId;

    @Lob
    @Column(name = "icon_bytes", nullable = false, columnDefinition = "LONGBLOB")
    private byte[] iconBytes;

    public UserIcon() {
    }

    public long getPkId() {
        return pkId;
    }

    public void setPkId(long pkId) {
        this.pkId = pkId;
    }

    public byte[] getIconBytes() {
        return iconBytes;
    }

    public void setIconBytes(byte[] iconBytes) {
        this.iconBytes = iconBytes;
    }
}
