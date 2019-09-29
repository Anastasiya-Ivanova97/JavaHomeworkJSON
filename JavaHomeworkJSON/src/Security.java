import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Security {
	@JsonProperty("id")
    private int id;
	@JsonProperty("code")
    private String code;
	@JsonProperty("name_full")
    private String nameFull;
	@JsonProperty("cfi")
    private String cfi;
	@JsonProperty("date_to")
    private Date dateTo;
	@JsonProperty("state_reg_date")
    private Date stateRegDate;
	@JsonProperty("state")
    private State state;
	@JsonProperty("currency")
    private Currency currency;

    @Override
    public String toString() {
        return id + code + nameFull + cfi + dateTo + stateRegDate;
    }

    boolean isExpired() {
        return new Date(System.currentTimeMillis()).after(dateTo);
    }

    void printInfo() {
        System.out.println("Код: " + code);
        System.out.println("Дата истечения: " + new SimpleDateFormat("dd/MM/yy").format(dateTo));
        System.out.println("Название: " + nameFull);
    }

    String getCurrencyCode() {
        return currency.getCode();
    }

    void printSecurityInfo() {
        System.out.println("id: " + id);
        System.out.println("code: " + code);
    }
}
