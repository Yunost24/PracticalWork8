import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WEBFormTest {

    private WebDriver driver;

    @BeforeAll

    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    } //Сообщаем что работаем с хромом

    @BeforeEach()
    void setAndGetPage() {
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

    //Плохая практика написания тестирования без id элемента
    @Test
    void checkHappyPathBadPractice() throws InterruptedException {
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        driver.findElement(By.cssSelector("#root > div > form > div:nth-child(1) input")).sendKeys("Александр Панкратов");
        driver.findElement(By.cssSelector("#root > div > form > div:nth-child(2) input")).sendKeys("+79999999999");
        driver.findElement(By.cssSelector("#root > div > form > div:nth-child(3) > label")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("#root > div > form > div:nth-child(4) > button")).click();
        Thread.sleep(3000);
        String actual = driver.findElement(By.cssSelector("#root > div > div > p")).getText().trim();

        Assertions.assertEquals(expected, actual);

        //Инициализация ожидаемого значения
        //Найти поле куда вводиться имя и фамилия
        //Ввести Имя Фамилию
        //Найти поле куда вводиться телефон
        //Ввести в формате +7..
        //Найти чек-бокс
        //Кликнуть
        //Найти кнопку и кликнуть
        //Дождаться появления успешного сообщения достать его и сравнить с ожидаемым
    }

    //Хорошая практика (поиск по id) Чтобы упростить тестирование
    @Test
    void checkHappyPathGoodPractice() throws InterruptedException {
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Александр ПанкратовЧерный");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79999999999");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("[class=button__content]")).click();
        Thread.sleep(3000);
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();

        Assertions.assertEquals(expected, actual);

        //Инициализация ожидаемое значение
        //Найти поле куда вводиться имя и фамилия
        //Ввести
        //Найти поле куда вводиться телефон
        //Ввести в формате +7...
        //Найти чек-бокс
        //Кликнуть
        //Найти кнопку и кликнуть
        //Дождаться появления успешного сообщения достать его и сравнить с ожидаемым
    }

    //Пользователь не ввел данные и не нажал на чек-бокс
    @Test
    void checkNoDataPathGoodPractice() throws InterruptedException {
        String expected = "Поле обязательно для заполнения";
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("");
        driver.findElement(By.cssSelector("[class=button__content]")).click();
        Thread.sleep(3000);
        String actual = driver.findElement(By.cssSelector("[class=input__sub]")).getText().trim();

        Assertions.assertEquals(expected, actual);

        //Инициализируем ожидаемое значение
        //Найти поле куда вводиться имя и фамилия
        //Оставить пустым
        //Найти поле куда вводиться телефон
        //Оставить пустым
        //Найти кнопку и кликнуть
        //Дождаться появления сообщения под полем ФИ достать его и сравнить с ожидаемым
    }

    //Пользователь не ввел номер телефона и не нажал на чек-бокс
    @Test
    void checkNoPhonePathGoodPractice() throws InterruptedException {
        String expected = "Поле обязательно для заполнения";
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Остап Бендер");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("");
        driver.findElement(By.cssSelector("[class=button__content]")).click();
        Thread.sleep(3000);
        String actual = driver.findElement(By.cssSelector("#root > div > form > div:nth-child(2) > span > span > span.input__sub")).getText().trim();

        Assertions.assertEquals(expected, actual);

        //Инициализируем ожидаемое значение
        //Найти поле куда вводиться имя и фамилия
        //Ввести
        //Найти поле куда вводиться телефон
        //Оставить пустым
        //Найти кнопку и кликнуть
        //Дождаться появления сообщения под полем ФИ достать его и сравнить с ожидаемым
    }

    //Пользователь ввёл данные, но не нажал на чек-бокс
    @Test
    void checkNoCheckBoxPathGoodPractice() throws InterruptedException {
        String expectedColor = "rgba(255, 92, 92, 1)";
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Остап Бендер");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79111111111");
        driver.findElement(By.cssSelector("[class=button__content]")).click();
        Thread.sleep(3000);
        String actualColor = driver.findElement(By.cssSelector("#root > div > form > div:nth-child(3) > label > span.checkbox__text")).getCssValue("color");

        Assertions.assertEquals(expectedColor, actualColor);

        //Инициализируем ожидаемое значение
        //Найти поле куда вводиться имя и фамилия
        //Ввести
        //Найти поле куда вводиться телефон
        //Ввести в формате +7...
        //Найти кнопку и кликнуть
        //Найти чек-бокс
        //Проигнорировать
        //Дождаться появления сообщения под полем ФИ достать его и сравнить с ожидаемым

    }
}
