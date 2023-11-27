package com.sos.stepDefinitions;

import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;

import com.github.javafaker.Faker;
import com.sos.CommonFactory.WebBrowser;
import com.sos.Pages.WebPages.RentalServiceWebPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RAEstampUpdationRentalAgreement {
	private Faker faker = new Faker();
	private LocalTime currentTime = LocalTime.now();
	private boolean isBefore3PM = currentTime.isBefore(LocalTime.of(15, 0));
	private RentalServiceWebPage user;
	private String url = WebBrowser.getUrl();
	private String name = faker.name().firstName();
	private String phoneNumber = "5262463636";// generateRandomMobileNumber(); // faker.phoneNumber().cellPhone();
	private String email = faker.internet().emailAddress();
	private String ra = "Rental Agreement", stampDuty = "Govt Stamp Duty", ConvenienceCharges = "Convenience Charges";
	private String twoExtraCopy = "Two extra original copies ";
	private String floorNumber, chooseBHK, typeOfProperty, flatNumber, buildingName, locality, pincode;
	private String fullName, Age, phone, parmanentAddress, panNumber, ownerEmail, partyType, gender, tenantEmail;
	private String agreementDuration, rentAmount, refundableDeposit, minLocking;
	private int stampDutyAmt = 100, ConvenienceAmt = 379, twoExtraCopyAmt = 843;
	private int expectedTotal1 = 479, expectedTotal2 = 461;
	private int nextDayDeliveryTotal = 1029, nextDayDeliveryWithExtraCopyTotal = 1872;
	private int sameDayDeliveryTotal = 1079, sameDayDeliveryWithExtraCopyTotal = 1962;

	@Given("User is on the Nobroker HomePage")
	public void user_is_on_the_nobroker_home_page() {
		user = new RentalServiceWebPage(WebBrowser.getDriver());
		WebBrowser.getDriver().get(url);
	}

	@When("User entering valid Login Credentials")
	public void user_entering_valid_login_credentials() {
		user.loginSignUpMethod(phoneNumber, "9999", name, email);
	}

	@Then("the user is logged In {string}")
	public void the_user_is_logged_in(String expected) {
		String actualText = user.getLoginSignUpSuccesstext();
		Assert.assertTrue(actualText.contains(expected));
	}

	@Given("User Lands on Legal Services HomePage")
	public void user_lands_on_legal_services_home_page() {
		try {
			user.selectHSServiceType(ra);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@When("User clicking on Finish your Agreement DropUp")
	public void user_clicking_on_finish_your_agreement_drop_up() {
		user.selectCityOrFinishYourAgreement("Bangalore");
	}

	@When("User clicks on Property Detail and Entering Property Details:")
	public void user_clicks_on_property_detail_and_entering_property_details(io.cucumber.datatable.DataTable dataTable)
			throws InterruptedException {
		List<List<String>> data = dataTable.asLists();
		this.floorNumber = data.get(0).get(1);
		this.chooseBHK = data.get(1).get(1);
		this.typeOfProperty = data.get(2).get(1);
		this.flatNumber = data.get(3).get(1);
		this.buildingName = data.get(4).get(1);
		this.locality = data.get(5).get(1);
		this.pincode = data.get(6).get(1);

		Map<String, String> selectPropertyMap = new LinkedHashMap<>();
		selectPropertyMap.put("Floor Number", floorNumber);
		selectPropertyMap.put("Choose BHK", chooseBHK);
		selectPropertyMap.put("Type of Property", typeOfProperty);

		Map<String, String> textPropertyMap = new LinkedHashMap<>();
		textPropertyMap.put("Shop Number", flatNumber);
		textPropertyMap.put("Building Name", buildingName);
		textPropertyMap.put("Locality", locality);
		textPropertyMap.put("Pincode", pincode);
		user.enterPropertyDetail(selectPropertyMap, textPropertyMap);
	}

	@When("Clicking and Adding Number of Bathrooms <{int}>")
	public void clicking_and_adding_number_of_bathrooms(int numberOfBathrooms) {
		user.selectNumberOfBathrooms(numberOfBathrooms);
	}

	@When("User Clicking on Save and Continue Btn")
	public void user_clicking_on_save_and_continue_btn() throws InterruptedException {
		user.SaveAndContinueBtn();
		user.refreshPage();
	}

	@When("User Entering Landlord Details:")
	public void user_entering_landlord_details(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
		List<List<String>> data = dataTable.asLists();
		this.fullName = data.get(0).get(1);
		this.Age = data.get(1).get(1);
		this.phone = data.get(2).get(1);
		this.parmanentAddress = data.get(3).get(1);
		this.panNumber = data.get(4).get(1);
		this.ownerEmail = data.get(5).get(1);
		this.pincode = data.get(6).get(1);
		this.partyType = data.get(7).get(1);
		this.gender = data.get(8).get(1);
		Map<String, String> textLanlordMap = new LinkedHashMap<>();
		textLanlordMap.put("Full Name", fullName);
		textLanlordMap.put("Age", Age);
		textLanlordMap.put("Phone", phone);
		textLanlordMap.put("Permanent Address Full", parmanentAddress);
		textLanlordMap.put("PAN Number (ABCDE1234F)", panNumber);
		textLanlordMap.put("Email Address", ownerEmail);
		textLanlordMap.put("PIN Code", pincode);
		Map<String, String> selectLanlordMap = new LinkedHashMap<>();
		selectLanlordMap.put("Party Type", partyType);
		selectLanlordMap.put("Gender", gender);
		Thread.sleep(2000);
		user.enterSelectLanlordDetails(selectLanlordMap);
		Thread.sleep(2000);
		user.enterLandlordText(textLanlordMap);
	}

	@When("User Entering Tenant Details:")
	public void user_entering_tenant_details(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
		List<List<String>> data = dataTable.asLists();
		this.fullName = data.get(0).get(1);
		this.Age = data.get(1).get(1);
		this.phone = data.get(2).get(1);
		this.parmanentAddress = data.get(3).get(1);
		this.panNumber = data.get(4).get(1);
		this.tenantEmail = data.get(5).get(1);
		this.pincode = data.get(6).get(1);
		this.partyType = data.get(7).get(1);
		this.gender = data.get(8).get(1);
		Map<String, String> textTenantMap = new LinkedHashMap<>();
		textTenantMap.put("Full Name", fullName);
		textTenantMap.put("Age", Age);
		textTenantMap.put("Phone", phone);
		textTenantMap.put("Permanent Address Full", parmanentAddress);
		textTenantMap.put("PAN Number (ABCDE1234F)", panNumber);
		textTenantMap.put("Email Address", tenantEmail);
		textTenantMap.put("PIN Code", pincode);

		Map<String, String> selectTenantMap = new LinkedHashMap<>();
		selectTenantMap.put("Party Type", partyType);
		selectTenantMap.put("Gender", gender);
		user.enterSelectTenantDetails(selectTenantMap);
		Thread.sleep(2000);
		user.enterTenantText(textTenantMap);
	}

	@When("User Entering Contract Details:")
	public void user_entering_contract_details(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
		List<List<String>> data = dataTable.asLists();
		this.agreementDuration = data.get(0).get(1);
		this.rentAmount = data.get(1).get(1);
		this.refundableDeposit = data.get(2).get(1);
		this.minLocking = data.get(3).get(1);
		Map<String, String> selectContractMap = new LinkedHashMap<>();
		selectContractMap.put("Agreement Duration (In months)", agreementDuration);
		selectContractMap.put("Minimum Lockin Period (In months)", minLocking);
		Map<String, String> textContractMap = new LinkedHashMap<>();
		textContractMap.put("Rent Amount", rentAmount);
		textContractMap.put("Refundable Deposit", refundableDeposit);
		user.enterContractText(textContractMap);
		Thread.sleep(2000);
		user.enterSelectContractDetails(selectContractMap);
	}

	@When("User Enters Deposit Payment Details")
	public void user_enters_deposit_payment_details() throws InterruptedException {
		user.selectAgreementStartDate();
		user.rentNotFixed("15");
		user.addPaymentDetails(refundableDeposit);
	}

	@When("User Select Rent Day")
	public void user_select_rent_day() {
		user.selectRentDay();
	}

	@When("User select Notice Period, Amenities and clicking on Save and Continue Btn")
	public void user_select_notice_period_amenities_and_clicking_on_save_and_continue_btn()
			throws InterruptedException {
		user.selecNoticePeriod("2");
		user.selectAmenities("Number of Beds");
		user.SaveAndContinueBtn();
	}

	@When("User Clicks on SummaryPage")
	public void user_clicks_on_summary_page() {
		user.clickOnAddNewAddress();
	}

	@When("User submitting delivery details {string} and {string}")
	public void user_submits_delivery_details_and(String address, String pincode) {
		user.addDeliveryAddress(address, pincode);
	}

	@When("User  clicking on Number of Extra copy {string} button.")
	public void user_clicks_on_number_of_extracopy_button(String NumberOfCopy) {
		user.selectExtraCopy(NumberOfCopy);
	}

	@Then("Validate the Quotation Details in Summary.")
	public void validate_the_quotation_details_in_summary() {
		int actualPriceTwoExtraCopy = user.getLIneItemsPrice(twoExtraCopy);
		Assert.assertEquals(twoExtraCopyAmt, actualPriceTwoExtraCopy);
		int actulTotal = user.sumLineItemAmounts();

		if (isBefore3PM) {
			Assert.assertTrue(actulTotal == sameDayDeliveryWithExtraCopyTotal || actulTotal == 1904,
					"Actual total does not match");
		} else {
			Assert.assertTrue(actulTotal == nextDayDeliveryWithExtraCopyTotal || actulTotal == 1854,
					"Actual total does not match");
		}
	}

	@When("User clicking on  PayNow Btn")
	public void user_clicking_on_pay_now_btn() {
		user.clickingOnPayBtn();
	}

	@When("User enters Payment Details {string} {string} {string}  {string} and clicks on Make Payment Btn")
	public void paymentDetails(String cardNumber, String expiryDate, String cvv, String name) {
		user.enteringCardDetails(cardNumber, expiryDate, cvv, name);
	}

	@When("User Entering {string} and click on SubmitBtn")
	public void user_entering_and_click_on_submit_btn(String otp) {
		user.enteringAndSubmittingOtp(otp);
	}

	@Then("Validating the Pricing and Order Confirmation status in MyBooking")
	public void validating_the_pricing_and_order_confirmation_status_in_my_booking() {
		String myBookingStatus = user.getOrderStatus();
		Assert.assertEquals(myBookingStatus, "Order Confirmed");
		int paidAmt = user.getPaidAmt();
		if (isBefore3PM) {
			Assert.assertTrue(paidAmt == sameDayDeliveryWithExtraCopyTotal || paidAmt == 1904,
					"Actual total does not match");
		} else {
			Assert.assertTrue(paidAmt == nextDayDeliveryWithExtraCopyTotal || paidAmt == 1854,
					"Actual total does not match");
		}
	}
}
