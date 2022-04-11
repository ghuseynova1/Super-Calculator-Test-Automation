package modules;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LandingPage;

public class SuperCalculatorTest {
    private static final String URL = "http://juliemr.github.io/protractor-demo/";
    public WebDriver driver;
    LandingPage landingPage;

    @BeforeTest
    public void setup() {

        System.setProperty("webdriver.chrome.driver","D:\\Projects\\selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(URL);
        landingPage =  PageFactory.initElements(driver, LandingPage.class);
    }

    @AfterMethod
    public void afterMethod(){
        driver.navigate().refresh();
    }

    @Test
    public void test1(){

        String expectedTitle = "Super Calculator";
        Assert.assertEquals(driver.getTitle(), expectedTitle,"Title is different than Super Calculator");
    }

    @Test
    public void test2(){
        landingPage.firstInput.sendKeys("8");
        landingPage.secondInput.sendKeys("8");
        Select operators = new Select(landingPage.operator);
        operators.selectByValue("ADDITION");
        landingPage.goButton.click();

        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until((ExpectedCondition<Boolean>) d -> {

            return isNumeric(landingPage.resultField.getText());
        });
        Assert.assertEquals(landingPage.resultField.getText(),"16","The result is different than 16");
    }

    @Test
    public void test3(){
        landingPage.firstInput.sendKeys("16");
        landingPage.secondInput.sendKeys("4");
        Select operators = new Select(landingPage.operator);
        operators.selectByValue("DIVISION");
        landingPage.goButton.click();
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until((ExpectedCondition<Boolean>) d -> {
            return isNumeric(landingPage.resultField.getText());
        });
        Assert.assertEquals(landingPage.resultField.getText(),"4","The result is different than 4");

        landingPage.firstInput.sendKeys("4");
        landingPage.secondInput.sendKeys("4");
        operators.selectByValue("MULTIPLICATION");
        landingPage.goButton.click();
        wait.until((ExpectedCondition<Boolean>) d -> {

            return isNumeric(landingPage.resultField.getText());
        });
        Assert.assertEquals(landingPage.resultField.getText(),"16","The result is different than 16");

        Assert.assertEquals(landingPage.expressions.size(),2, "History session expressions size is not equal to 2");
    }



    @AfterClass
    public void end() {
        driver.quit();
    }

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
