import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Google extends DevopsBase{

   public Google() {
       wait = new WebDriverWait(driver, Duration.ofSeconds(15));
   }

    @Test
    public void Test_GoogleTitle(){
        driver.get(prop.getProperty("url"));
        wait.until(ExpectedConditions.titleIs("Google"));
        String title = driver.getTitle();
        Assert.assertEquals(title,prop.getProperty("title"));
        System.out.println("Title : "+title);

    }
}
