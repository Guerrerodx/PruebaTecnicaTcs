package cucumber.definitions;

import cucumber.driver.DriverFactory;
import cucumber.driver.SharedDriver;
import cucumber.pages.ComparaLaptopsPage;
import cucumber.pages.ExcelGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class ComparaLaptopsDef {

    public ComparaLaptopsPage comparaLaptopsPage;
    public ExcelGenerator excelGenerator;
    private WebDriver driver;
    private String ulrLaptops = "https://www.gadgetsnow.com/compare-laptops";

    public ComparaLaptopsDef(SharedDriver driver) {
        this.driver = DriverFactory.getDriver();
        comparaLaptopsPage = new ComparaLaptopsPage(this.driver);
        excelGenerator = new ExcelGenerator();
    }
    @Given("abro la pagina de comparacion")
    public void abro_la_pagina_de_comparacion() throws InterruptedException {
        driver.get(ulrLaptops);
        Thread.sleep(2000);//se justifica uso de Thread.sleep porque la pagina tarda aprox 2 segundos en cargar sus elementos
    }
    @When("busco {string} y lo anado a la comparacion")
    public void busco_y_lo_anado_a_la_comparacion(String string) throws InterruptedException {
        comparaLaptopsPage.clickPrimerElemento(string);
        comparaLaptopsPage.clickCompareButton();
    }
    @Then("extraigo los detalles del resumen a un archivo Excel")
    public void extraigo_los_detalles_del_resumen_a_un_archivo_excel() {
        excelGenerator.generateExcelFromTable(comparaLaptopsPage.tabla());
    }


}
