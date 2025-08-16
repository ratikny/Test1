import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Temp {

    public static void main (String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://demoqa.com/");
        driver.manage().window().maximize();

        driver.findElement(By.xpath("(//*[@class='card-up'])[1]")).click(); // click button Elements
        System.out.println("stage 1");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.xpath("(//*[@id='item-2'])[1]\n")).click(); // click to button "Radio Butoon"
        System.out.println("stage 2");


        WebElement radio = driver.findElement(By.id("yesRadio"));
        JavascriptExecutor js = (JavascriptExecutor) driver;   // click radio butoon " yes '
        js.executeScript("arguments[0].click();", radio);
        System.out.println("stage 3");



        // Ожидаем и кликаем кнопку "Text Box" (новый локатор)
        WebElement textBoxBtn = new WebDriverWait(driver, Duration.ofSeconds(10))   // click "Text box"
                .until(ExpectedConditions.elementToBeClickable(
                        // Ищем по комбинации класса и текста
                        By.xpath("//li[contains(@class, 'btn-light') and contains(., 'Text Box')]")
                ));
        textBoxBtn.click();
        System.out.println("Кнопка 'Text Box' успешно найдена и кликнута");
        System.out.println("stage 4");

        //driver.findElement(By.xpath("(//*[@class='mr-sm-2 form-control'])[1]")).click(); // click text box "Full Name"

        driver.findElement(By.xpath("(//*[@class='mr-sm-2 form-control'])[1]")).sendKeys("Jordan Brown"); // write full name
        System.out.println("stage 5");
        driver.findElement(By.xpath("//*[@placeholder='name@example.com']")).sendKeys("example@mail.com"); // write email
        System.out.println("stage 6");

        Thread.sleep(2000);
        driver.quit();

    }

}
