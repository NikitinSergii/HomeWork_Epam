package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
//        hook = {"src/test/java/runner"},
        glue = "features",
        plugin = {"json:target/cucumber-reports/Cucumber.json", "pretty"},
        tags = "@RunMe"
)

public class TestRunner{

}




