package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class LoginSteps {


    //WebDriver driver = new ChromeDriver();
    private WebDriver driver;
    private WebDriverWait wait;

    @Given("I opened main page of website")
    public void i_opened_main_page_of_website() {
        //WebDriverManager.chromedriver().setup();

        // --- НАЧАЛО ИЗМЕНЕНИЙ ---

        // Создаём опции для Chrome
        ChromeOptions options = new ChromeOptions();

        // Включаем headless-режим. Браузер будет работать в фоне.
        options.addArguments("--headless");

        // Эти опции часто нужны для стабильной работы в Docker/Linux
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080"); // Задаём размер окна

        // Создаём драйвер с новыми опциями
        driver = new ChromeDriver(options);

        // --- КОНЕЦ ИЗМЕНЕНИЙ ---

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // driver.manage().window().maximize(); // ЭТА СТРОКА УДАЛЕНА

        driver.get("https://demoqa.com/");

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//img[@class='banner-image']")
        ));

        System.out.println("Stage 1");
    }

    @When("I click {string} button")
    public void i_click_button(String name) {
        // Скрываем баннер, если есть
        try {
            WebElement banner = driver.findElement(By.id("fixedban"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none'", banner);
            System.out.println("✅ Баннер скрыт.");
        } catch (Exception e) {
            System.out.println("⚠️ Баннер не найден, пропускаем.");
        }

        // Кликаем по карточке (например, Elements)
        WebElement elementsCard = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//h5[text()='" + name + "']/ancestor::div[@class='card mt-4 top-card']")));
        elementsCard.click();
        System.out.println("✅ Клик по '" + name + "'");
    }

    @And("I click {string} section")
    public void i_click_section(String sectionName) {
        WebElement menuItem = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='" + sectionName + "']")));
        menuItem.click();
        System.out.println("✅ Клик по разделу '" + sectionName + "'");
    }

    @And("I choose {string} radio button")
    public void i_choose_radio_button(String option) {
        WebElement radio = driver.findElement(By.id("yesRadio"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", radio);
        System.out.println("✅ Выбран radio: " + option);
    }

    @And("I fill {string} text box")
    public void i_fill_text_box(String fieldName) {
        if (fieldName.equalsIgnoreCase("Full Name")) {
            driver.findElement(By.id("userName")).sendKeys("Jordan Brown");
            System.out.println("✅ Ввёл Full Name");
        } else if (fieldName.equalsIgnoreCase("Email")) {
            driver.findElement(By.id("userEmail")).sendKeys("example@mail.com");
            System.out.println("✅ Ввёл Email");
        }
    }

    @Then("I close browser")
    public void i_close_browser() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }
}





//    @Given("I opened main page of website")
//    public void i_opened_main_page_of_website() {
//
//
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        wait = new WebDriverWait(driver,Duration.ofSeconds(15));
//
//        driver.manage().window().maximize();
//        driver.get("https://demoqa.com/");
//        //driver.manage().window().maximize();
//        wait.until(ExpectedConditions.presenceOfElementLocated(
//                By.xpath("//img[@class='banner-image']")
//        ));
//        System.out.println("Stage 1 ");
//
//    }
//
//    @When("I click {string} button")
//    public void i_click_button(String name)  {
////        driver.findElement(By.xpath("(//*[@class='card-up'])[1]")).click(); // click button Elements
////        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
////        driver.findElement(By.xpath("(//*[@id='item-2'])[1]")).click(); // click to button "Radio Butoon"
//
//        // Скрываем баннер, если есть
//        try {
//            WebElement banner = driver.findElement(By.id("fixedban"));
//            ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none'", banner);
//            System.out.println("✅ Баннер скрыт.");
//        } catch (Exception e) {
//            System.out.println("⚠️ Баннер не найден, пропускаем.");
//        }
//
//        // Нажимаем на карточку Elements
//        WebElement elementsCard = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//h5[text()='Elements']/ancestor::div[@class='card mt-4 top-card']")));
//        elementsCard.click();
//        System.out.println("✅ Клик по 'Elements'");
//
//        // Переход к Radio Button
//        WebElement radioButton = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//span[text()='Radio Button']")));
//        radioButton.click();
//        System.out.println("✅ Клик по 'Radio Button'");
//
//        System.out.println("stage 2");
//
//    }
//
//    @And("I choose {string} radio button section")
//    public void i_choose_radio_button_section(String string) throws InterruptedException {
//       // Thread.sleep(2000);
//        WebElement radio = driver.findElement(By.id("yesRadio"));
//        JavascriptExecutor js = (JavascriptExecutor) driver;   // click radio butoon " yes '
//        js.executeScript("arguments[0].click();", radio);
//        System.out.println("stage 3");
//    }
//
//
//    @And("i click {string} text box and write Full Name")
//    public void i_click_text_box_and_write_full_name(String string) {
//        WebElement textBoxBtn = new WebDriverWait(driver, Duration.ofSeconds(10))   // click "Text box"
//                .until(ExpectedConditions.elementToBeClickable(
//                        // Ищем по комбинации класса и текста
//                        By.xpath("//li[contains(@class, 'btn-light') and contains(., 'Text Box')]")
//                ));
//        textBoxBtn.click();
//        System.out.println("Кнопка 'Text Box' успешно найдена и кликнута");
//        System.out.println("stage 4");
//
//        //driver.findElement(By.xpath("(//*[@class='mr-sm-2 form-control'])[1]")).click(); // click text box "Full Name"
//
//        driver.findElement(By.xpath("(//*[@class='mr-sm-2 form-control'])[1]")).sendKeys("Jordan Brown"); // write full name
//
//    }
//
//    @And("I click {string} text box and write email address")
//    public void i_click_text_box_and_write_email_address(String string) {
//        driver.findElement(By.xpath("//*[@placeholder='name@example.com']")).sendKeys("example@mail.com"); // write email
//
//    }
//
//    @Then("I close browser")
//    public void i_close_browser() throws InterruptedException {
//        Thread.sleep(2000);
//       driver.quit();
//    }

//    @Given("I opened main page of website")
//    public void i_opened_main_page_of_website() {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//
//        driver.manage().window().maximize();
//        driver.get("https://demoqa.com/");
//
//        wait.until(ExpectedConditions.presenceOfElementLocated(
//                By.xpath("//img[@class='banner-image']")
//        ));
//
//        System.out.println("Stage 1");
//    }
//
//    @When("I click {string} button")
//    public void i_click_button(String name) {
//        // Скрываем баннер, если есть
//        try {
//            WebElement banner = driver.findElement(By.id("fixedban"));
//            ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none'", banner);
//            System.out.println("✅ Баннер скрыт.");
//        } catch (Exception e) {
//            System.out.println("⚠️ Баннер не найден, пропускаем.");
//        }
//
//        // Кликаем по карточке (например, Elements)
//        WebElement elementsCard = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//h5[text()='" + name + "']/ancestor::div[@class='card mt-4 top-card']")));
//        elementsCard.click();
//        System.out.println("✅ Клик по '" + name + "'");
//    }
//
//    @And("I click {string} section")
//    public void i_click_section(String sectionName) {
//        WebElement menuItem = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//span[text()='" + sectionName + "']")));
//        menuItem.click();
//        System.out.println("✅ Клик по разделу '" + sectionName + "'");
//    }
//
//    @And("I choose {string} radio button")
//    public void i_choose_radio_button(String option) {
//        WebElement radio = driver.findElement(By.id("yesRadio"));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", radio);
//        System.out.println("✅ Выбран radio: " + option);
//    }
//
//    @And("I fill {string} text box")
//    public void i_fill_text_box(String fieldName) {
//        if (fieldName.equalsIgnoreCase("Full Name")) {
//            driver.findElement(By.id("userName")).sendKeys("Jordan Brown");
//            System.out.println("✅ Ввёл Full Name");
//        } else if (fieldName.equalsIgnoreCase("Email")) {
//            driver.findElement(By.id("userEmail")).sendKeys("example@mail.com");
//            System.out.println("✅ Ввёл Email");
//        }
//    }
//
//    @Then("I close browser")
//    public void i_close_browser() throws InterruptedException {
//        Thread.sleep(2000);
//        driver.quit();
//    }
//}
//
//
