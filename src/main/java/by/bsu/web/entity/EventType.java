package by.bsu.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "event_type")
public class EventType {
    public static final String DEFAULT_TYPE = "default";

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventType eventType = (EventType) o;

        if (pkId != null ? !pkId.equals(eventType.pkId) : eventType.pkId != null) return false;
        return name != null ? name.equals(eventType.name) : eventType.name == null;

    }

    @Override
    public int hashCode() {
        int result = pkId != null ? pkId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
