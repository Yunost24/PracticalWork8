import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WEBFormTest {

    private WebDriver driver;

    @BeforeAll

    static void setupAll(){
        WebDriverManager.chromedriver().setup();
    } //Сообщаем что работаем с хромом

    @BeforeEach ()
    void setAndGetPage(){
        //Запустить браузер
        //Открыть нужную страницу get

        driver = new ChromeDriver();
        driver.get("http://localhost:9999/");//открываем страницу по адресу
    }

    @AfterEach()

    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void checkHappyPath () throws InterruptedException {
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        driver.findElement(By.cssSelector("#root > div > form > div:nth-child(1) input")).sendKeys("Коля Стукин");
        driver.findElement(By.cssSelector("#root > div > form > div:nth-child(2) input")).sendKeys("+79999999999");
        //        driver.findElement(By.cssSelector("#root > div > form > div:nth-child(1) > span")).sendKeys("Коля Стукин");
        //driver.findElement(By.cssSelector("#root > div > form > div:nth-child(2) > span")).sendKeys("+79999999999");
        driver.findElement(By.cssSelector("#root > div > form > div:nth-child(3) > label")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("#root > div > form > div:nth-child(4) > button")).click();
        Thread.sleep(3000);
        String actual = driver.findElement(By.cssSelector("#root > div > div > p")).getText().trim();

        Assertions.assertEquals(expected,actual);

        //иннициализируем ожидаемое значение
        //найти поле куда вводиться имя и фамилия
        //Ввести
        //Найти поле куда вводиться телефон
        //Ввести
        // Найти чек-бокс
        // кликнуть
        // Найти кнопку и кликнуть
        // Дождаться появления успешного сообщения достать его и сравнить с ожидаемым
    }

    //Хорошая практика (поиск по id) Чтобы упрощалость тестирование
    @Test
    void checkHappyPathGoodPractice () throws InterruptedException {
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Коля ПереСтукин");
        driver.findElement(By.cssSelector("#root > div > form > div:nth-child(2) input")).sendKeys("+79999999999");
        driver.findElement(By.cssSelector("#root > div > form > div:nth-child(3) > label")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("#root > div > form > div:nth-child(4) > button")).click();
        Thread.sleep(3000);
        String actual = driver.findElement(By.cssSelector("#root > div > div > p")).getText().trim();

        Assertions.assertEquals(expected,actual);

        //иннициализируем ожидаемое значение
        //найти поле куда вводиться имя и фамилия
        //Ввести
        //Найти поле куда вводиться телефон
        //Ввести
        // Найти чек-бокс
        // кликнуть
        // Найти кнопку и кликнуть
        // Дождаться появления успешного сообщения достать его и сравнить с ожидаемым
    }


}
