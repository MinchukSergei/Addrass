package by.bsu.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "event_member")
public class EventMember {
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long pkId;

    @ManyToOne
    @JoinColumn(name = "fk_event_id")
    private UserEvent fkEventId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user_id", nullable = false)
    private UserData fkUserId;

    public Long getPkId() {
        return pkId;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    public UserEvent getFkEventId() {
        return fkEventId;
    }

    public void setFkEventId(UserEvent fkEventId) {
        this.fkEventId = fkEventId;
    }

    public UserData getFkUserId() {
        return fkUserId;
    }

    public void setFkUserId(UserData fkUserId) {
        this.fkUserId = fkUserId;
    }
}
