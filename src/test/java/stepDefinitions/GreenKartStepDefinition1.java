// first we write standAloneTest then we optimize it for framework
/*
searchProduct.feature

Feature: Search and Place the order for Products

Scenario: Search Experience for product search in both home and Offers page

Given User is on GreenCart Landing page
When user searched with Shortname "Tom" and extracted actual name of product
Then user searched for "Tom" shortname in offers page
And validate product name in offers page matches with Landing Page

*/
package stepDefinitions;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GreenKartStepDefinition1 {

	public WebDriver driver;
	public static String landingPageProductName;
	public String offerPageProductName;

	@Given("User is on GreenCart Landing page")
	public void user_is_on_green_cart_landing_page() {

		driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
	}

	@When("user searched with Shortname {string} and extracted actual name of product")
	public void user_searched_with_shortname_and_extracted_actual_name_of_product(String shortName) throws InterruptedException {
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys(shortName);
		Thread.sleep(2000);
		String landingPageProductName = driver.findElement(By.cssSelector("h4.product-name")).getText().split("-")[0].trim(); // Tomato - 1 Kg -> Tomato
		System.out.println(landingPageProductName + " is extracted from Home page");
	}

	@Then("user searched for {string} shortname in offers page")
	public void user_searched_for_same_shortname_in_offers_page(String shortName) throws InterruptedException {
		// offer pafe-enter grab text
		driver.findElement(By.linkText("Top Deals")).click();
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> il = s1.iterator();

		String parentWindow = il.next();
		String childWindow = il.next();

		driver.switchTo().window(childWindow);
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys(shortName);
		Thread.sleep(2000);
		
		offerPageProductName = driver.findElement(By.cssSelector("tr td:nth-child(1)")).getText();
		System.out.println(offerPageProductName);
	}

	@Then("validate product name in offers page matches with Landing Page")
	public void validate_product_name_in_Offers_page() {
		
		System.out.println(landingPageProductName);// null
		System.out.println(offerPageProductName);// Tomato
		Assert.assertEquals(offerPageProductName,landingPageProductName);// Tomato, null   
        driver.quit();
	}
}