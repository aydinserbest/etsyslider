package slider;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class SlidingTest {
    private WebDriver driver;

    @Before
    public void setup() {
        driver = new ChromeDriver();
    }

    @Test
    public void test_slow_human_ike() {

        // Navigate to Etsy site
        driver.get("https://www.etsy.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        // Switch to the main iframe
        WebElement mainIframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("iframe[src*='captcha-delivery']")));
        driver.switchTo().frame(mainIframe);

        // Check the slider puzzle text
        String element = driver.findElement(By.xpath("//p[contains(text(),'Slide right to complete the puzzle.')]")).getText();
        System.out.println(element);


        // Find the slider icon
        WebElement slider = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sliderIcon")));

        // Perform the sliding action (human-like)
        Actions action = new Actions(driver);
        action.clickAndHold(slider);

        // Slide with random pauses and movements to simulate human behavior
        Random random = new Random();
        int totalOffset = 150;
        int currentOffset = 0;
        while (currentOffset < totalOffset) {
            int moveBy = random.nextInt(10) + 5; // Move by 5 to 15 pixels
            action.moveByOffset(moveBy, 0);
            currentOffset += moveBy;
            try {
                Thread.sleep(random.nextInt(100) + 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        action.release().perform();

        // Switch back to default content
        driver.switchTo().defaultContent();
    }

    @Test
    public void test() {

        // Navigate to Etsy site
        driver.get("https://www.etsy.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        // Switch to the main iframe
        WebElement mainIframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("iframe[src*='captcha-delivery']")));
        driver.switchTo().frame(mainIframe);

        // Check the slider puzzle text
        String element = driver.findElement(By.xpath("//p[contains(text(),'Slide right to complete the puzzle.')]")).getText();
        System.out.println(element);

        // Find the slider icon
        WebElement slider = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sliderIcon")));

        // Perform the sliding action
        Actions action = new Actions(driver);
        action.clickAndHold(slider)
                .moveByOffset(150, 0) // Adjust the sliding distance if needed
                .release()
                .perform();


    }

    @Test
    public void test_with_sliding_distance() {
        // Navigate to Etsy site
        driver.get("https://www.etsy.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        // Switch to the main iframe
        WebElement mainIframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("iframe[src*='captcha-delivery']")));
        driver.switchTo().frame(mainIframe);

        // Check the slider puzzle text
        String element = driver.findElement(By.xpath("//p[contains(text(),'Slide right to complete the puzzle.')]")).getText();
        System.out.println(element);

        // Find the slider icon
        WebElement slider = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sliderIcon")));

        // Find the slider icon
        WebElement sliderIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sliderIcon")));

        // Use JavaScript to get the bounding rectangles for the slider piece and target slot
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement sliderPiece = driver.findElement(By.cssSelector(".sliderIcon"));
        WebElement targetSlot = driver.findElement(By.cssSelector(".sliderContainer .sliderbg"));

        // Get the bounding rectangles for the elements
        Double sliderPieceX = (Double) js.executeScript("return arguments[0].getBoundingClientRect().left;", sliderPiece);
        Double targetSlotX = (Double) js.executeScript("return arguments[0].getBoundingClientRect().left;", targetSlot);

        System.out.println("Slider Piece X: " + sliderPieceX);
        System.out.println("Target Slot X: " + targetSlotX);

        // Calculate the sliding distance
        int offset = (int) Math.round(targetSlotX - sliderPieceX);

        System.out.println("Offset: " + offset);

        // Perform the sliding action
        Actions action = new Actions(driver);
        action.moveToElement(slider)
                .clickAndHold()
                .moveByOffset(offset, 0) // Adjust the sliding distance if needed
                .release()
                .perform();

        // Optional: Switch back to default content
        driver.switchTo().defaultContent();


    }
    /*
    @Test
    public void test_with_sliding_distance() {
        // Navigate to Etsy site
        driver.get("https://www.etsy.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        // Switch to the main iframe
        WebElement mainIframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("iframe[src*='captcha-delivery']")));
        driver.switchTo().frame(mainIframe);

        // Check the slider puzzle text
        String element = driver.findElement(By.xpath("//p[contains(text(),'Slide right to complete the puzzle.')]")).getText();
        System.out.println(element);

        // Find the slider icon
        WebElement slider = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sliderIcon")));
        WebElement targetSlot = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#captcha__puzzle canvas.block")));
        Point targetLocation = targetSlot.getLocation();
        int targetX = targetLocation.getX();

        System.out.println(targetX);

        WebElement sliderPiece = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#captcha__puzzle canvas:first-child")));
        Point sliderLocation = sliderPiece.getLocation();
        int sliderX = sliderLocation.getX();

        System.out.println(sliderX);

        // Calculate the sliding distance
        int offset = targetX - sliderX;

        System.out.println(offset);

        // Perform the sliding action
        Actions action = new Actions(driver);
        action.moveToElement(slider)
                .clickAndHold()
                .moveByOffset(offset, 0) // Adjust the sliding distance if needed
                .release()
                .perform();
    }

     */
}
