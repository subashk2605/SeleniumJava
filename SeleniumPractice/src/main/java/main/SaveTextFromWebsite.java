package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SaveTextFromWebsite {

	public static void main(String[] args) {
		WebDriver driver = new EdgeDriver();

		// Navigate to the website
		driver.get(
				"https://mvnrepository.com/"); // Replace
																																																			// with
		// Locate the paragraph element
		 WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10)); // Adjust the timeout as needed
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("p")));

	        // Locate all the paragraph elements
	        List<WebElement> paragraphElements = driver.findElements(By.tagName("p")); // Replace with the appropriate locator

	        // Create a StringBuilder to hold the combined text of all paragraphs
	        StringBuilder combinedText = new StringBuilder();

	        // Extract the text of each paragraph and append it to the StringBuilder
	        for (WebElement paragraphElement : paragraphElements) {
	            String paragraphText = paragraphElement.getText();
	            combinedText.append(paragraphText).append("\n");
	        }

	        // Specify the path to save the file
	        String filePath = "C:\\Users\\subas.BOSE-K\\OneDrive\\Desktop\\paragraph.txt";

	        // Save the combined text to a text file
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
	            writer.write(combinedText.toString());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }


	        System.out.println("Paragraphs extracted and saved to: " + filePath);
	    }

	}


