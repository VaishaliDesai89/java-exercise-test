package selenium.test.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import selenium.test.pages.HomePage;

import org.openqa.selenium.remote.RemoteWebDriver;

import static selenium.test.DriverSetup.driverSetup;

public class MyStepdefs {
    public RemoteWebDriver driver;

    @Before
    public void init() throws Throwable {
    	System.out.println("First Step def");
        driver = driverSetup();
    }

    @And("^I navigate to \"([^\"]*)\"$")
    public void iNavigateTo(String url) throws Throwable {
        new HomePage(driver).goToPage(url);
    }
    
    @When("^I click on \"([^\"]*)\" button$")
    public void iClickOnButton(String buttonText) throws Throwable {
        new HomePage(driver).clickOnButton(buttonText);
    }
   
    @Then("^I get a page to enter vehicle registration number$")
    public void iGetPageToInputVehicleRegistrationNumber() throws Throwable {
    		new HomePage(driver).checkCurrentPageUrl("https://vehicleenquiry.service.gov.uk/");
    }
    
    @When("^I search for an item \"([^\"]*)\"$")
    public void iSearchForAnItem(String searchTerm) throws Throwable {
        new HomePage(driver).searchForAnItem(searchTerm);
    } 
  
    @Then("^I get a page with vehicle details showing colour and make$")
    public void iGetAPageWithVehicleDetails() throws Throwable {
    	 	new HomePage(driver).checkVehicleDetails("Mini","Orange");
    	 	new HomePage(driver).readFromCSV("vehicle-data.xls","www.gov.uk/get-vehicle-information-from-dvla");
    }
    
    @After
    public void cleanUp(){
        driver.quit();
    }
}
