package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class LandingPage {

    @FindBy(how= How.XPATH, xpath = "/html/body/div/div/form/input[@ng-model='first']")
    public WebElement firstInput;

    @FindBy(how= How.XPATH, xpath = "/html/body/div/div/form/input[@ng-model='second']")
    public WebElement secondInput;

    @FindBy(how = How.XPATH, xpath = "/html/body/div/div/form/select[@ng-model='operator']")
    public WebElement operator;

    @FindBy(how = How.ID, id = "gobutton")
    public WebElement goButton;

    @FindBy(how = How.CLASS_NAME, className = "ng-binding")
    public WebElement resultField;

    @FindBy(how = How.XPATH, xpath = "//table[@class='table']/tbody/tr")
    public List<WebElement> expressions;
}
