import com.fasterxml.jackson.annotation.JsonProperty;

public class CompanyType {
    @JsonProperty("id")
    public int id;
    @JsonProperty("name_short")
    public String nameShort;
    @JsonProperty("name_full")
    public String nameFull;
}
