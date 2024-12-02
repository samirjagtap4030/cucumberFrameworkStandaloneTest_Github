package cucumberOptions;

import io.cucumber.testng.AbstractTestNGCucumberTests; 
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/features",glue ="stepDefinitions"
,monochrome=true)
public class TestNGTestRunner1 extends AbstractTestNGCucumberTests{

	// dryRun=true - is used to check stepDefinition is missing (before execute all tests)
	// when you execute all tests,remove dryRun=true 
}