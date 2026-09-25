package Day5.SegregationFile;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginDataProvider {
    @DataProvider(name = "loginCredentials")
    public static Object[][] getLoginData() {
        List<Object[]> data = new ArrayList<>();
        String csvFile = "C:\\Users\\ccst\\Desktop\\Selenium\\SeleniumMaterial\\loginData.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] values = line.split(",");
                if (values.length >= 2) {
                    data.add(new Object[]{values[0].trim(), values[1].trim()});
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read CSV file at: " + csvFile, e);
        }

        return data.toArray(new Object[0][]);
    }
}