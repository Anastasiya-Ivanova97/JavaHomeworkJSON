import com.fasterxml.jackson.annotation.JsonProperty;

public class Currency {
    @JsonProperty("id")
    public int id;
    @JsonProperty("code")
    public String code;
    @JsonProperty("name_short")
    public String nameShort;
    @JsonProperty("name_full")
    public String nameFull;

    String getCode() {
        return code;
    }
}
