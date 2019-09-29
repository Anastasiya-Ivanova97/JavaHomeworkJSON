import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

class App {
	private static final SimpleDateFormat[] DATE_FORMAT = {
			new SimpleDateFormat("dd.MM.yy"),
			new SimpleDateFormat("dd/MM/yy"),
			new SimpleDateFormat("yyyy-MM-dd"),
			new SimpleDateFormat("dd.MM,yy")
	};

    private static final String[] stepsDescription = {
			"Введите путь до json-файла.",
			"Введите дату",
			"Введите валюту"
	};

	private int step = 0;
	private List<Company> all = new ArrayList<>();

    void run() {
        while(true) {
			System.out.println();
			System.out.println("--- " + stepsDescription[step]);
			System.out.println("Или напишите quit, чтобы закончить");
			String enterCommand = new Scanner(System.in).next();

			if ("quit".equalsIgnoreCase(enterCommand)) {
				return;
			}

			boolean nextStep = false;
			switch (step) {
				case 0: {
					nextStep = stepEnterFilename(enterCommand);
					break;
				}
				case 1: {
					nextStep = stepEnterDate(enterCommand);
					break;
				}
				case 2: {
					nextStep = stepEnterCurrency(enterCommand);
					break;
				}
			}

			if (nextStep) {
				step = step >= 2 ? 1 : step + 1;
			}
		}
    }

    private boolean stepEnterFilename(String filename) {
		File file = new File(filename);
		if (!file.exists()) {
			System.out.println("Такого файла не сущетсвует.");
			return false;
		}
		parse(file);
		printInfo();
		printFinishedSecurities();
		return true;
	}

    private boolean stepEnterDate(String enteredDate) {
		Date date;

		try {
			date = parseDate(enteredDate);
			System.out.println("// Получено время: " + date);
		} catch (Exception e) {
			System.out.println("Unknown format date");
			e.printStackTrace();
			return false;
		}

		all.stream()
		   .filter(company -> company.hasCreatedAfterDate(date))
		   .forEach(Company::printInfo);
		return true;
	}

	private boolean stepEnterCurrency(String enterCurrency) {
		printSecuritiesByCurrency(enterCurrency);
		return true;
	}

    private void printSecuritiesByCurrency(String currency) {
        all.forEach(company -> company.printInfoByCode(currency));
    }

	private void printInfo() {
		all.forEach(Company::printInfo);
	}

	private void printFinishedSecurities() {
		all.forEach(Company::printFinishedSecurities);
		int count = all.stream().mapToInt(Company::getFinishedSecuritiesCount).sum();
		System.out.println("Количество: " + count);
	}

    private void parse(File file) {
		ObjectMapper mapper = new ObjectMapper();
		JavaType javaType = mapper.getTypeFactory().constructCollectionType(List.class, Company.class);
		try {
			all = mapper.readValue(file, javaType);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Date parseDate(String input) throws Exception {
		for (SimpleDateFormat format : DATE_FORMAT) {
			try {
				return format.parse(input);
			} catch (Exception e) {}
		}
		throw new Exception("Unknown format");
	}
}
