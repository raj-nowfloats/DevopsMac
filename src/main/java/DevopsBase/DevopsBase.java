package DevopsBase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class DevopsBase {

    public static WebDriver driver;
    public static Properties prop;
    public static WebDriverWait wait;

    public DevopsBase(){
        try {
            prop = new Properties();
            FileInputStream cp = new FileInputStream("src/test/resources/config.properties");
            prop.load(cp);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


    @BeforeClass
    public static void setup() throws IOException {
        Properties prop = new Properties();
        FileInputStream ip = new FileInputStream("src/test/resources/config.properties");
        prop.load(ip);
        String browserName = prop.getProperty("browser");
        if (browserName.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(ChromeOptions.CAPABILITY, options);
            options.merge(caps);
            driver = new ChromeDriver(options);
        }else{
            System.err.println("Browser should be chrome");
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(60));
    }

    @AfterClass
    public static void teardown(){;
        driver.quit();
    }
}
