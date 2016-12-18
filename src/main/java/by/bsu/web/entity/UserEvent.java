package by.bsu.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Set;

@Entity
@Table(name = "user_event")
public class UserEvent {
    @Id
    @Column(name = "pk_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkId;

    @Column(name = "fk_event_owner", nullable = false)
    @JsonIgnore
    private Long fkEventOwner;

    @ManyToOne
    @JoinColumn(name = "fk_event_type")
    private EventType eventType;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "event_datetime", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar eventDateTime;

    @Column(name = "event_coordinate_x")
    private Double eventCoordinateX;

    @Column(name = "event_coordinate_y")
    private Double eventCoordinateY;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fkEventId", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EventMember> members;

    public Set<EventMember> getMembers() {
        return members;
    }

    public void setMembers(Set<EventMember> members) {
        this.members = members;
    }

    public Long getPkId() {
        return pkId;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    public Long getFkEventOwner() {
        return fkEventOwner;
    }

    public void setFkEventOwner(Long fkEventOwner) {
        this.fkEventOwner = fkEventOwner;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(Calendar eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    public Double getEventCoordinateX() {
        return eventCoordinateX;
    }

    public void setEventCoordinateX(Double eventCoordinateX) {
        this.eventCoordinateX = eventCoordinateX;
    }

    public Double getEventCoordinateY() {
        return eventCoordinateY;
    }

    public void setEventCoordinateY(Double eventCoordinateY) {
        this.eventCoordinateY = eventCoordinateY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEvent userEvent = (UserEvent) o;

        if (pkId != null ? !pkId.equals(userEvent.pkId) : userEvent.pkId != null) return false;
        if (fkEventOwner != null ? !fkEventOwner.equals(userEvent.fkEventOwner) : userEvent.fkEventOwner != null)
            return false;
        if (eventType != null ? !eventType.equals(userEvent.eventType) : userEvent.eventType != null) return false;
        if (name != null ? !name.equals(userEvent.name) : userEvent.name != null) return false;
        if (eventDateTime != null ? !eventDateTime.equals(userEvent.eventDateTime) : userEvent.eventDateTime != null)
            return false;
        if (eventCoordinateX != null ? !eventCoordinateX.equals(userEvent.eventCoordinateX) : userEvent.eventCoordinateX != null)
            return false;
        return eventCoordinateY != null ? eventCoordinateY.equals(userEvent.eventCoordinateY) : userEvent.eventCoordinateY == null;

    }

    @Override
    public int hashCode() {
        int result = pkId != null ? pkId.hashCode() : 0;
        result = 31 * result + (fkEventOwner != null ? fkEventOwner.hashCode() : 0);
        result = 31 * result + (eventType != null ? eventType.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (eventDateTime != null ? eventDateTime.hashCode() : 0);
        result = 31 * result + (eventCoordinateX != null ? eventCoordinateX.hashCode() : 0);
        result = 31 * result + (eventCoordinateY != null ? eventCoordinateY.hashCode() : 0);
        return result;
    }
}
