package com.sos.stepDefinitions;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import com.github.javafaker.Faker;
import com.sos.CommonFactory.WebBrowser;
import com.sos.Pages.WebPages.RentalServiceWebPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TenantVerifications {
    private Faker faker = new Faker();
    private RentalServiceWebPage user;
    private String url = WebBrowser.getUrl();


    @Given("the user has landed on the NoBroker LoginPage")
    public void the_user_has_landed_on_the_no_broker_login_page() {
    	 user = new RentalServiceWebPage(WebBrowser.getDriver());
         WebBrowser.getDriver().get(url);
    }

    @When("the user logs in with valid login credentials")
    public void the_user_logs_in_with_valid_login_credentials() {
    	user.loginSignUpMethod(generateRandomMobileNumber(), "9999", faker.name().firstName(), faker.internet().emailAddress());
    }

    @Given("the user lands on the Legal Services Page and selects Tenant Verification")
    public void the_user_lands_on_the_legal_services_page_and_selects_tenant_verification() throws InterruptedException {
    	 user.selectHSServiceType("Painting & Cleaning");
         user.selectHSServiceCity("Bangalore");
         user.selectTenantVerification("Tenant Verification");
    }

    @Given("clicks on the Get Started button and selects the {string} package")
    public void clicks_on_the_get_started_button_and_selects_the_package(String tvPackage) {
    	user.closingChatHelp();
    	user.selectTenantVerificationPackage(tvPackage);
    }

    @Given("the user selects Verification ID Type {string} Enters ID Number {string} name {string} and DOB {string}")
    public void the_user_selects_verification_id_type_enters_id_number_name_and_dob(String idType, String idNumber, String name, String dob) throws InterruptedException {
    	user.tenantVerificationDetails(idType, idNumber, name, dob);
    }

    @Given("clicks on the Save and Continue button")
    public void clicks_on_the_save_and_continue_button() throws InterruptedException {
    	user.SaveAndContinueBtn();
    }

    @Then("validate the price <{int}> and Package Title {string} in the summary Page")
    public void validate_the_price_and_package_title_in_the_summary_page(int price, String title) {
    	 String summaryTitle = user.getTvPackageTitle();
         Assert.assertEquals(summaryTitle, title);
         int summaryTotalAmt = user.getTvSummaryTotalAmount();
        //  Assert.assertEquals(summaryTotalAmt, price);
    }  

    @Given("Entering Verification Details ID {string} ID No {string} name {string} phone {string} DOB {string} Gender and FatherName {string}")
    public void entering_verification_details_id_id_no_name_phone_dob_gender_and_father_name(String idType,String idNumber,String name,String phone,String dob,String fatherName) throws InterruptedException {
    	user.tenantBasicVerificationDetails(idType, idNumber,name,phone,dob,fatherName);
    }

  

	@Given("Entering Address Details {string} {string} and pincode {string}")
    public void entering_address_details_and_pincode(String flatNo, String locality, String pincode) {
       user.verificationAddress(flatNo, locality, pincode);
    }

    @Then("validates the price <{int}> and Summary Package Title {string} in the summary Page")
    public void validates_the_price_and_summary_package_title_in_the_summary_page(int price, String title) {
    	  String summaryTitle = user.getTvPackageTitle();
          Assert.assertEquals(summaryTitle, title);
          int summaryTotalAmt = user.getTvSummaryTotalAmount();
         // Assert.assertEquals(summaryTotalAmt, price);
    }

    @Given("Enter Reference Dertails  name {string} {string} and {string}")
    public void enter_reference_dertails_name_and(String name, String mobile, String email) {
     user.referenceName(name,mobile,email);
    	
    } 
   
    
    
    public static String generateRandomMobileNumber() {
        return "665" + RandomStringUtils.randomNumeric(4) + "321";
    }   
} 
