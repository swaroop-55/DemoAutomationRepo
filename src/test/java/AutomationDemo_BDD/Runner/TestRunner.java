package AutomationDemo_BDD.Runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions (
		features="classpath:features",
		glue="AutomationDemo_BDD.StepDef",
		tags= "@DisplayLogo",
		plugin= {"pretty","html:target/html/htmlreport.html",
					"json:target/json/file.json",
				},
		publish=true,
		monochrome=true,
		dryRun=false
		)

public class TestRunner {

}
