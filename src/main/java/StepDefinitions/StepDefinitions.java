package StepDefinitions;

import cucumber.api.java.en.Given;		
import cucumber.api.java.en.Then;		
import cucumber.api.java.en.When;

public class StepDefinitions {

	@Given("^Open the Chrome and launch the timer website$")				
    public void open_the_Firefox_and_launch_the_application() throws Throwable							
    {		
        System.out.println("This Step open the Firefox and launch the application.");					
    }	
}
