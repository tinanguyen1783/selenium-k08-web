package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import url.Urls;

public class JSExecutor implements Urls {

    public static void main(String[] args) {

        WebDriver driverFactory = DriverFactory.getChromeDriver();

        try {
            //Navigate to target page
            driverFactory.get(Urls.baseUrl.concat(Urls.floatingSlug));

            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driverFactory;
            javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);\n");
            Thread.sleep(2000);
            javascriptExecutor.executeScript("window.scrollTo(document.body.scrollHeight,0);\n");

Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        driverFactory.quit();
    }
}
