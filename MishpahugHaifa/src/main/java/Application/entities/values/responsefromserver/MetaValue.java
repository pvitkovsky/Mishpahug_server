package Application.entities.values.responsefromserver;

public class MetaValue {
    private String code;

    public MetaValue(String code) {
        this.code = code;
    }

    public MetaValue() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "MetaValue{" +
                "code='" + code + '\'' +
                '}';
    }
}
