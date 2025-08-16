package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/main/resources/features",
        glue = "stepDefinitions",
        plugin = {
            "pretty",
        "html:target/cucumber-html-report.html",
        "json:target/cucumber-report.json"
},
        monochrome = true


        )


public class TestRunner extends AbstractTestNGCucumberTests {


}
