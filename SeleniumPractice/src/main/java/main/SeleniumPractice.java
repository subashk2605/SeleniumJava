package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;


public class SeleniumPractice {
	public static void main(String[] args) throws IOException {

		System.setProperty("webdriver.edge.driver", "D:\\Drivers\\edgedriver_win64\\msedgedriver.exe");

		EdgeOptions options = new EdgeOptions();

		options.addArguments("--disable-notifications");
		WebDriver driver = new EdgeDriver(options);
		driver.get("https://www.saucedemo.com/");
		WebElement login = driver.findElement(By.name("user-name"));
		login.sendKeys("standard_user");
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("secret_sauce");
		WebElement loginClick = driver.findElement(By.id("login-button"));
		loginClick.click();

		String filePath = "C:\\\\Users\\\\subas.BOSE-K\\\\OneDrive\\\\Desktop\\\\FileReadJava.txt";
		FileReader reader = new FileReader(filePath);
		 BufferedReader br = new BufferedReader(reader); 
	            String line;
	           
	            while ((line = br.readLine()) != null) {
	                System.out.println(line);
	            }
	}
		
}
