package Application.entities.values.responsefromserver;

import java.util.Arrays;

public class ResponseValue {
    private HaliDayValue[] holidays;

    public HaliDayValue[] getHolidays() {
        return holidays;
    }

    public void setHolidays(HaliDayValue[] holidays) {
        this.holidays = holidays;
    }

    public ResponseValue(HaliDayValue[] holidays) {
        this.holidays = holidays;
    }

    @Override
    public String toString() {
        return "ResponseValue{" +
                "holidays=" + Arrays.toString(holidays) +
                '}';
    }

    public ResponseValue() {
    }
}
