import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Company {
	@JsonProperty("id")
	private int id;
	@JsonProperty("code")
	private String code;
	@JsonProperty("name_full")
	private String nameFull;
	@JsonProperty("name_short")
	private String nameShort;
	@JsonProperty("inn")
	private String inn;
	@JsonProperty("company_type")
	private CompanyType companyType;
	@JsonProperty("ogrn")
	private String ogrn;
	@JsonProperty("egrul_date")
	private Date eqrulDate;
	@JsonProperty("country")
	private Country country;
	@JsonProperty("fio_head")
	private String fioHead;
	@JsonProperty("address")
	private String address;
	@JsonProperty("phone")
	private String phone;
	@JsonProperty("e_mail")
	private String eMail;
	@JsonProperty("www")
	private String www;
	@JsonProperty("is_resident")
	private boolean isResident;
	@JsonProperty("securities")
	private List<Security> securities;

	public void setSecurities(List<Security> securities) {
		this.securities = securities;
	}

	public void printInfo() {
		System.out.println("Краткое название: " + nameShort + "\nДата основания: " + new SimpleDateFormat("dd/mm/yyyy")
				.format(eqrulDate));
	}

	public void printFinishedSecurities() {
		securities.forEach(security -> {
			if (security.isExpired()) {
				security.printInfo();
			}
		});
	}

	public int getFinishedSecuritiesCount() {
		return (int) securities.stream().filter(Security::isExpired).count();
	}

	public boolean hasCreatedAfterDate(Date date) {
		return eqrulDate.after(date);
	}

	public void printInfoByCode(String code) {
		securities.stream().filter(security -> security.getCurrencyCode().equals(code))
				  .forEach(Security::printSecurityInfo);
	}
}
