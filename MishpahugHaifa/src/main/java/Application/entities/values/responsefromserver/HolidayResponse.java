package Application.entities.values.responsefromserver;

public class HolidayResponse {
    private MetaValue meta;
    private ResponseValue response;

    public HolidayResponse(MetaValue meta, ResponseValue response) {
        this.meta = meta;
        this.response = response;
    }

    public HolidayResponse() {
    }

    public MetaValue getMeta() {
        return meta;
    }

    public void setMeta(MetaValue meta) {
        this.meta = meta;
    }

    public ResponseValue getResponse() {
        return response;
    }

    public void setResponse(ResponseValue response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "HolidayResponse{" +
                "meta=" + meta +
                ", response=" + response +
                '}';
    }
}
