package cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "cucumber.definitions",
        plugin = {
                "pretty",
                "json:target/cucumber-report/cucumber.json",
                "junit:target/cucumber.xml"
        }
)
public class Runner {

}
