import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class Main {

    public static void main(String[] args) {
        System.setProperty("Webdriver.chrome.driver", "D:\\Selenium\\chromedriver-win32");
        WebDriver driver=new ChromeDriver();
        driver.get("https://www.javatpoint.com/object-cloning");
    }

}
