import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.File


/**
 * Created by Ender on 13/04/2017.
 */
fun main(args: Array<String>) {
    // GeckoDriver route is necessary for FirefoxDriver to work if it's not set in the path.
    System.setProperty("webdriver.gecko.driver", """F:\Descargas\selenium-java-3.3.1\geckodriver.exe""")

    // Creating Firefox driver object.
    val driver = FirefoxDriver()
    driver.get("https://chrono.gg/")

    // Log in button
    driver.findElementByClassName("cd-signin").click()

    // Explicit wait for email field to be visiable
    WebDriverWait(driver, 10)
            .until(ExpectedConditions.presenceOfElementLocated(By.id("signin-email")))
            .sendKeys("xavier1997@gmail.com")

    // Reads file with the password (main reason of this it's because public VCS)
    driver.findElementById("signin-password")
            .sendKeys(File("""F:\Proyects\chrono_kot\password.txt""").readText() + Keys.ENTER)

    // Finds current coins, xpath should be replaced with a less fragile expression.
    var currency = WebDriverWait(driver, 10)
            .until(ExpectedConditions.presenceOfElementLocated(By.xpath("""//*[@id="react"]/div/div[1]/header/div/nav[2]/ul/li[1]/a/span[2]""")))
    println("Coints: ${currency.text}")

    // Fins coin and checks the status
    var coin = driver.findElementById("reward-coin")
    var catt = coin.getAttribute("class")
    if (catt == "coin dead") {
        println("Coin dead, not clickable")
    }


    driver.quit()

}