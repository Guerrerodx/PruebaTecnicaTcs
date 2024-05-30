package cucumber.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;


public class ComparaLaptopsPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(className = "D0yIt")
    List<WebElement> selectLaptops;
    @FindBy(xpath = "//input[@type='text' and @placeholder='Search Laptops']")
    WebElement inputLaptops;
    @FindBy(xpath = "//button[@type='button' and contains(@class, 'theme_button') and text()='compare']")
    WebElement compareButton;
    @FindBy(xpath = "//table")
    WebElement tabla;

    public ComparaLaptopsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 30);
        PageFactory.initElements(driver, this);
    }
    public void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void clickPrimerElemento(String string) throws InterruptedException {
        waitForElementToBeClickable(selectLaptops.get(0));
        WebElement primerElemento = selectLaptops.get(0);
        primerElemento.click();
        waitForElementToBeClickable(inputLaptops);
        inputLaptops.sendKeys(string);
        Thread.sleep(2000);//se justifica el uso de Thread.sleep solo porque para no repetir el metodo es mas util buscar el elemento dentro de la funcion
        WebElement laptop = driver.findElement(By.xpath("//mark[contains(text(),'"+string+"')]"));
        waitForElementToBeClickable(laptop);
        laptop.click();
    }

    public void clickCompareButton(){
        waitForElementToBeClickable(compareButton);
        compareButton.click();
    }

    public String tablaText(){
        return tabla.getText();
    }

    public WebElement tabla(){
        return tabla;
    }

}
