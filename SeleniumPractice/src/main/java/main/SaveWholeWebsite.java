package main;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class SaveWholeWebsite {
    public static void main(String[] args) {
        // Set the path to your EdgeDriver executable
        System.setProperty("webdriver.edge.driver", "D:\\Drivers\\edgedriver_win64\\msedgedriver.exe");

        // Configure Edge options
        EdgeOptions options = new EdgeOptions();
        options.setCapability("ms:edgeChromium", true);

        // Initialize EdgeDriver with the configured options
        WebDriver driver = new EdgeDriver(options);

        // Navigate to the website
        driver.get("https://mvnrepository.com/"); // Replace with your website URL

        try {
            Thread.sleep(5000); // Adjust the wait time as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Download and save the whole website
        saveWholeWebsite(driver, "C:\\Users\\subas.BOSE-K\\OneDrive\\Desktop\\website.html");

        // Close the browser
       
    }

    private static void saveWholeWebsite(WebDriver driver, String filePath) {
        // Download and embed images in the HTML content
        embedImages(driver);

        // Save the HTML content to a file
        String htmlContent = driver.getPageSource();
        saveHtmlContent(htmlContent, filePath);
    }

    private static void embedImages(WebDriver driver) {
        // Find all image elements on the page
        List<WebElement> imageElements = driver.findElements(By.tagName("img"));

        // Iterate through each image element
        for (WebElement imageElement : imageElements) {
            // Get the source URL of the image
            String imageUrl = imageElement.getAttribute("src");

            try {
                // Create a temporary file to download the image
                File tempImageFile = File.createTempFile("tempImage", ".jpg");

                // Download the image from the source URL
                ((RemoteWebElement) imageElement).setFileDetector(new LocalFileDetector());
                File downloadedImageFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                URL imageUrlObj = new URL(imageUrl);
                FileUtils.copyURLToFile(imageUrlObj, tempImageFile);

                // Update the "src" attribute of the image element with the local file path
                String localFilePath = tempImageFile.getAbsolutePath();
                ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('src', arguments[1]);",
                        imageElement, localFilePath);

                // Delete the temporary image file
                tempImageFile.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void saveHtmlContent(String htmlContent, String filePath) {
        try {
            File file = new File(filePath);
            FileUtils.writeStringToFile(file, htmlContent, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
