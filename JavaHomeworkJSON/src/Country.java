import com.fasterxml.jackson.annotation.JsonProperty;

public class Country {
    @JsonProperty("id")
    public int id;
    @JsonProperty("code")
    public String code;
    @JsonProperty("name")
    public String name;
}
