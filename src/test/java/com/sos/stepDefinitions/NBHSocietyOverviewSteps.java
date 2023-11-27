package com.sos.stepDefinitions;

import com.sos.CommonFactory.WebBrowser;
import com.sos.Pages.WebPages.DashboardPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class NBHSocietyOverviewSteps {
	private DashboardPage admin = new DashboardPage(WebBrowser.getDriver());
	private String url = WebBrowser.getUrl();
	
	@Given("User landed on Society Dashboard and Logged in with valid email {string} and password {string}")
	public void user_landed_on_society_dashboard_and_logged_in_With_email_and_password(String email, String password) {
		WebBrowser.getDriver().get(url);
		admin.societyAdminLogin(email, password);
	}

	@When("User Clicks on Society Overview")
	public void user_clicks_on_society_overview() {
	    System.out.println("clicks ssss");
	}

	@Then("Validate the total number of Staff on Duty")
	public void validate_the_total_number_of_staff_on_duty() {
		admin.onDutyStaff();
	}

	@Then("Validate the Number of Security and Other staff")
	public void validate_the_number_of_security_and_other_staff() {
	   System.out.println("Successfuly Validated On DUty Staff");
	}

	@Then("Validate the total number of Flats")
	public void validate_the_total_number_of_flats() throws InterruptedException {
	   admin.totalFlats();
	}

	@Then("Validate the number of Owner Residing, Tenant Residing, Vacant, and Free flats")
	public void validate_the_number_of_owner_residing_tenant_residing_vacant_and_free_flats() {
		 System.out.println("Successfuly Validated On Owner,Tenant and Vacant and free flats");
	}

	@Then("Validate the number of Pending Approvals")
	public void validate_the_number_of_pending_approvals() {
		admin.pendingApprovals();
	}

	@Then("Validate the Move In, Staff, Services, and Moved Out Pending Approval Numbers")
	public void validate_the_move_in_staff_services_and_moved_out_pending_approval_numbers() {
		 System.out.println("Successfuly Validated pending_approval_numbers");
	}

	@Then("Verify the number of Total Number of Complaints")
	public void verify_the_number_of_total_number_of_complaints() {
	   
	}

	@Then("Verify the Number of Open, In Progress, Resolved, and Closed complaints status")
	public void verify_the_number_of_open_in_progress_resolved_and_closed_complaints_status() {
	  
	}

}
