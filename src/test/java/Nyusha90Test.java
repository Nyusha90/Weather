import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class Nyusha90Test {

    //    TC_1_1  - Тест кейс:
    //    //1. Открыть страницу https://openweathermap.org/
    //    //2. Набрать в строке поиска город Paris
    //    //3. Нажать пункт меню Search
    //    //4. Из выпадающего списка выбрать Paris, FR
    //    //5. Подтвердить, что заголовок изменился на "Paris, FR"

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Chrome_Drivers\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(url);
        Thread.sleep(5000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city' ]")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = driver.findElement(
                By.xpath("//button[@type = 'submit']")
        );
        searchButton.click();

        Thread.sleep(3000);

        WebElement parisFRChoiceInDropdownMenu = driver.findElement(
                By.xpath("//ul[@class ='search-dropdown-menu']/li/span[text() ='Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = driver.findElement(
                By.xpath("//div[@id ='weather-widget']//h2")
        );

        Thread.sleep(2000);

        // Act
        String actualResult = h2CityCountryHeader.getText();

        //Assert
        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();

    }

    //TC_11_01
    //1.  Открыть базовую ссылку
    //2.  Нажать на пункт меню Guide
    //3.  Подтвердить, что вы перешли на страницу со ссылкой
    // https://openweathermap.org/guide и что title этой страницы OpenWeatherMap API guide - OpenWeatherMap

    @Test
    public void testGuiderUrlAndHeader() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Chrome_Drivers\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org";
        String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";
        String expectedResultUrl = "https://openweathermap.org/guide";

        driver.get(url);
        Thread.sleep(2000);

        WebElement guideElementInMenu = driver.findElement(
                By.xpath("//a[@href = '/guide']")
        );

        guideElementInMenu.click();

        String actualResultUrl = driver.getCurrentUrl();
        String actualResultTitle = driver.getTitle();

        Assert.assertEquals(actualResultTitle, expectedResultTitle);
        Assert.assertEquals(actualResultUrl, expectedResultUrl);

        driver.quit();
    }

    //TC_11_02
    //1.  Открыть базовую ссылку
    //2.  Нажать на единицы измерения Imperial: °F, mph
    //3.  Подтвердить, что температура для города показана в Фарингейтах

    @Test
    public void testTemperatureOfTheCityInF() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Chrome_Drivers\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org";
        String expectedResult = "°F";
        String fTempSymbol = "°F";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement menuEmperial = driver.findElement(By.xpath
                ("//div[@class ='switch-container']/div[@class = 'option']/following-sibling::div")
        );

        menuEmperial.click();

        WebElement tempF = driver.findElement(
                By.xpath("//div[@class ='current-temp']/span")
        );

        Boolean isTemperatureInFatherenheit = tempF.getText().contains("°F");


        String tempInF = tempF.getText();

        String actualResult = tempInF.substring((tempInF.length() - 2)); //метод ктрый выводит
        // кусочек желаемого текста 85°F

        Assert.assertTrue(tempF.getText().contains(fTempSymbol));

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    //TC_11_03
    //1.  Открыть базовую ссылку
    //2. Подтвердить, что внизу страницы есть панель с текстом “We use cookies which are essential for the site
    // to work. We also use non-essential cookies to help us improve our services. Any data collected
    // is anonymised. You can allow all cookies or manage them individually.”
    //3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”

    @Test
    public void testTApproveTwoButtonsInPanel() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Chrome_Drivers\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org";
        String expectedResultText = "We use cookies which are essential for the site to work. "
                + "We also use non-essential cookies to help us improve our services. "
                + "Any data collected is anonymised. You can allow all cookies or manage them individually.";


        driver.get(url);
        Thread.sleep(5000);

        WebElement panelWithText = driver.findElement(By.className("stick-footer-panel__description"));
        WebElement button1 = driver.findElement(By.xpath("//button[@type ='button']"));
        WebElement button2 = driver.findElement(By.xpath("//a[@href='/cookies-settings']"));

        String actualResultText = panelWithText.getText();

        Assert.assertEquals(button1.getText(), "Allow all");
        Assert.assertEquals(button2.getText(), "Manage cookies");
        Assert.assertEquals(actualResultText, expectedResultText);

        driver.quit();
    }

    //TC_11_04
    //1.  Открыть базовую ссылку
    //2.  Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”, “How to start” и “Ask a question”

    @Test
    public void testTApproveThreeSubMenu() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Chrome_Drivers\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org";
        String expectingResultFAQ = "FAQ";
        String expectingResultHowToStart = "How to start";
        String expectingResultAskAQuestion = "Ask a question";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement menuSupport = driver.findElement(By.xpath("//div[@id ='support-dropdown']"));
        menuSupport.click();
        Thread.sleep(2000);

        WebElement checkIfTextFAQ = driver.findElement(By.xpath
                ("//ul[@id='support-dropdown-menu']//a[@href='/faq']"));
        String actualResultTextFAQ = checkIfTextFAQ.getText();

        WebElement checkIfTextHowToStart = driver.findElement(By.xpath
                ("//ul[@id='support-dropdown-menu']//a[@href='/appid']"));
        String actualResultTHowTostart = checkIfTextHowToStart.getText();

        WebElement checkIfTextAskAQuestion = driver.findElement(By.xpath
                ("//ul[@id='support-dropdown-menu']//a[@href='https://home.openweathermap.org/questions']")
        );
        String actualResultAskAQuestion = checkIfTextAskAQuestion.getText();

        Assert.assertEquals(actualResultTextFAQ, expectingResultFAQ);
        Assert.assertEquals(actualResultTHowTostart, expectingResultHowToStart);
        Assert.assertEquals(actualResultAskAQuestion, expectingResultAskAQuestion);

        driver.quit();
    }

    //TC_11_05
    //1. Открыть базовую ссылку
    //2. Нажать пункт меню Support → Ask a question
    //3. Заполнить поля Email, Subject, Message
    //4. Не подтвердив CAPTCHA, нажать кнопку Submit
    //5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification failed, please try again.”

    @Test
    public void testCaptcha() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Chrome_Drivers\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org";
        String expectedResult = "reCAPTCHA verification failed, please try again.";
        String email = "google@google.com";
        String message = "Test message";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement menuSupport = driver.findElement(By.xpath("//div[@id ='support-dropdown']"));
        menuSupport.click();
        Thread.sleep(2000);

        WebElement pressAskAQuestion = driver.findElement(By.xpath
                ("//ul[@id='support-dropdown-menu']//a[@href='https://home.openweathermap.org/questions']")
        );
        Thread.sleep(2000);
        pressAskAQuestion.click();

        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));

        WebElement emailField = driver.findElement(By.xpath
                ("//input[@class='form-control string email required']"));

        emailField.sendKeys(email);

        WebElement selectSubject = driver.findElement(By.xpath
                ("//select[@class ='form-control select required']"));
        Thread.sleep(2000);
        selectSubject.click();

        WebElement selectSubjectChoice = driver.findElement(By.xpath("//option[@value='Sales']"));
        Thread.sleep(2000);
        selectSubjectChoice.click();

        WebElement messageField = driver.findElement(By.xpath
                ("//textarea[@class='form-control text required']"));
        messageField.click();

        messageField.sendKeys(message);

        WebElement submitButton = driver.findElement(By.xpath
                ("//input[@data-disable-with=\"Create Question form\"]"));

        submitButton.click();
        Thread.sleep(2000);

        WebElement reCaptchaText = driver.findElement(By.xpath("//div[@class='help-block']"));

        String actualResult = reCaptchaText.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

}
