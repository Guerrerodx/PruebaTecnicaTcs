package cucumber.driver;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class DriverFactory {
    private static ThreadLocal<WebDriver> drivers = new ThreadLocal<>();

    private static List<WebDriver> storedDrivers = new ArrayList<>();

    /**
     * Este metodo crea un hooks destinado a eliminar los drivers que no fueron eliminados durante la ejecución.
     */
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> storedDrivers.forEach(WebDriver::quit)));
    }

    private DriverFactory() {

    }

    public static WebDriver getDriver() {
        return drivers.get();
    }

    public static void addDriver(WebDriver driver) {
        storedDrivers.add(driver);
        drivers.set(driver);
    }

    public static void removeDriver() {
        storedDrivers.remove(drivers.get());
        drivers.remove();
    }

    public static void quitDriver(){
        if (null == drivers.get()) return;
        drivers.get().quit();
        removeDriver();
    }
}
