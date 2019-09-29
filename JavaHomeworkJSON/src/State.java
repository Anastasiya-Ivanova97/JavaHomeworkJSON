import com.fasterxml.jackson.annotation.JsonProperty;

public class State {
    @JsonProperty("id")
    public int id;
    @JsonProperty("name")
    public String name;
}
