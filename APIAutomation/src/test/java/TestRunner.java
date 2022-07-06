import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/featureFiles/",
        glue={"stepDefs"},
//        tags = "@AddPayee"
        plugin = { "pretty", "html:target/cucumber-report/Report.html"},
        monochrome = true
)

public class TestRunner {

}

