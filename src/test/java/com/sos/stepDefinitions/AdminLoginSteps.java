package com.sos.stepDefinitions;

import org.testng.Assert;

import com.sos.CommonFactory.WebBrowser;
import com.sos.Pages.WebPages.DashboardPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AdminLoginSteps {

	private DashboardPage admin = new DashboardPage(WebBrowser.getDriver());
	private String url = WebBrowser.getUrl();

	@Given("the User Navigates to the Landing Page")
	public void the_user_navigates_to_the_landing_page() {
		WebBrowser.getDriver().get(url);
	}  

	@When("the User logs in with Valid Email {string} and Password {string}")
	public void the_user_logs_in_with_valid_email_and_password(String email, String password) {
		admin.societyAdminLogin(email, password);
	}

	@Then("the Username should be displayed as {string}")
	public void the_username_should_be_displayed_as(String userName) {
		String actualUserNameText = admin.getUserNameText();
		Assert.assertEquals(userName, actualUserNameText);
	}

	@Then("the Admin Dashboard Page title should be {string}")
	 public void the_admin_dashboard_page_title_should_be(String expecteSocityName) {
		String actulSocityName = admin.getPageTitle();
		Assert.assertEquals(actulSocityName, expecteSocityName);
	}
	
	@Then("an error message should be displayed\"Bad credentials\"")
	 public void an_error_message_should_be_displayed_bad_credentials() {
	   String actualerror = admin.getLoginErrortext();
	   Assert.assertEquals(actualerror, "Bad credentials");
	}

}