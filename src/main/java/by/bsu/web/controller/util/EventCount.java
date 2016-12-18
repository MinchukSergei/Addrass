package by.bsu.web.controller.util;


public class EventCount {
    private Integer dayOfMonth;
    private Integer eventCount;
    private Boolean isOwner;

    public EventCount(Integer dayOfMonth, Integer eventCount, Boolean isOwner) {
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

    public Integer getEventCount() {
        return eventCount;
    }

    public void setEventCount(Integer eventCount) {
        this.eventCount = eventCount;
    }

    public Boolean getOwner() {
        return isOwner;
    }

    public void setOwner(Boolean owner) {
        isOwner = owner;
    }
}
