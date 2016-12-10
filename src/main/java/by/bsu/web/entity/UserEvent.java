package by.bsu.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Calendar;

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
    private EventType fkEventTypeEntity;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "event_datetime", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar eventDateTime;

    @Column(name = "event_coordinate_x")
    private Double eventCoordinateX;

    @Column(name = "event_coordinate_y")
    private Double eventCoordinateY;

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

    public EventType getFkEventTypeEntity() {
        return fkEventTypeEntity;
    }

    public void setFkEventTypeEntity(EventType fkEventTypeEntity) {
        this.fkEventTypeEntity = fkEventTypeEntity;
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
}
