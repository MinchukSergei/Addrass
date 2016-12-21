package by.bsu.web.controller.util;

/**
 * Created by USER on 03.12.2016.
 */
public class EventCountCriteria {
    private String type;
    private Integer value;

    public EventCountCriteria(String type, Integer value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
