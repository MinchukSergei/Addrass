package by.bsu.web.controller.util;


public class EventCount {
    private Integer dayOfMonth;
    private Long eventCount;
    private Boolean isOwner;

    public EventCount(Integer dayOfMonth, Long eventCount, Boolean isOwner) {
        this.dayOfMonth = dayOfMonth;
        this.eventCount = eventCount;
        this.isOwner = isOwner;
    }

    public Integer getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(Integer dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public Long getEventCount() {
        return eventCount;
    }

    public void setEventCount(Long eventCount) {
        this.eventCount = eventCount;
    }

    public Boolean getOwner() {
        return isOwner;
    }

    public void setOwner(Boolean owner) {
        isOwner = owner;
    }
}
