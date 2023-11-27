package com.sos.stepDefinitions;

import com.sos.CommonFactory.WebBrowser;
import com.sos.Pages.WebPages.DashboardPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class NBHCurrentStaffAndSecuritySteps {
	private DashboardPage admin = new DashboardPage(WebBrowser.getDriver());
	private String url = WebBrowser.getUrl();

	@Given("the user is on the landing page")
	public void the_user_is_on_the_landing_page() {
		WebBrowser.getDriver().get(url);
	}

	@Given("the user is logged in with email {string} and password {string}")
	public void the_user_is_logged_in_with_email_and_password(String email, String password) {
		admin.societyAdminLogin(email, password);
	}

	@When("the user clicks on {string} and selects {string}")
	public void the_user_clicks_on_and_selects(String gateVisitormgmt, String manageStafSecurity) {
		admin.clickDashbpardElement(gateVisitormgmt, manageStafSecurity);
	}

	@Then("the user verifies the number of staff")
	public void the_user_verifies_the_number_of_staff() {
		admin.getSocietystafandSecurityDetails();
	}

}
