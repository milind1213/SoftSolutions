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

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RAEstampAgreementFlow {
	private Faker faker = new Faker();
	private LocalTime currentTime = LocalTime.now();
	private boolean isBefore3PM = currentTime.isBefore(LocalTime.of(15, 0));
	private RentalServiceWebPage user;
	private String url = WebBrowser.getUrl();
	private String name = faker.name().firstName();
	private String phoneNumber = generateRandomMobileNumber(); // faker.phoneNumber().cellPhone();
	private String email = faker.internet().emailAddress();
	private String rentalAgreement = "Rental Agreement";
	private String floorNumber, chooseBHK, typeOfProperty, flatNumber, buildingName, locality, pincode;
	private String fullName, Age, phone, parmanentAddress, panNumber, ownerEmail, partyType, gender, tenantEmail;
	private String agreementDuration, rentAmount, refundableDeposit, minLocking;
	private int PayebleTotal;
	private int extraTwoCopyPrice, extraOneCopyPrice, deliveryPrice;

	@Given("User landed on the on the NoBroker Login HomePage")
	public void landed_NoBroker_Login_Page() {
		user = new RentalServiceWebPage(WebBrowser.getDriver());
		WebBrowser.getDriver().get(url);
	}

	@When("User login  with valid Login Credentials")
	public void user_logs_in_with_valid_login_credentials() {
		user.loginSignUpMethod(phoneNumber, "9999", name, email);
	}

	@Given("User Navigates to the Legal Services Page")
	public void user_navigates_to_the_legal_services_page() throws InterruptedException {
		user.selectHSServiceType(rentalAgreement);
	}

	@When("User Clicks on the Check Price button and Selects the {string} Package")
	public void user_clicks_on_the_check_price_button_and_selects_the_package(String packageTitle) {
		user.selectCityOrFinishYourAgreement("Bangalore");
		user.checkPrices();
		user.selectPackage(packageTitle);
	}

	@When("User Selects Stamp {string}, Agreement Duration {string}, and Notarised Agreement, E-Sign {int} and Delivery addons")
	public void user_selects_stamp_agreement_duration_and_notarised_agreement_e_sign_and_delivery_addons(String stamp,
			String agrMonth, int esignNum) throws InterruptedException {
		user.genrateEstimate(stamp, agrMonth, esignNum);
	}

	@When("Clicks on Save and Continue button")
	public void clicks_on_save_and_continue_button() throws InterruptedException {
		user.SaveAndContinueBtn();
		user.refreshPage();
	}

	@When("Lands on Property Detail Page and Enters Property Details:")
	public void lands_on_property_detail_page_and_enters_property_details(io.cucumber.datatable.DataTable dataTable)
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

	@When("Click and Add Number of <{int}> Bathrooms")
	public void click_and_add_number_of_bathrooms(Integer numberOfBathrooms) {
		user.selectNumberOfBathrooms(numberOfBathrooms);
	}

	@Then("Lands on Landlord Detail Page and Enters Landlord Details:")
	public void lands_on_landlord_detail_page_and_enters_landlord_details(io.cucumber.datatable.DataTable dataTable)
			throws InterruptedException {
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

	@Then("Lands on Tenant Detail Page and Enters Tenant Details:")
	public void lands_on_tenant_detail_page_and_enters_tenant_details(io.cucumber.datatable.DataTable dataTable)
			throws InterruptedException {
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

	@Then("Lands on Contract Details Page and Enters Contract Details:")
	public void lands_on_contract_details_page_and_enters_contract_details(io.cucumber.datatable.DataTable dataTable)
			throws InterruptedException {
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

	@Then("User Enters Deposit Payment Details,")
	public void user_enters_deposit_payment_details() throws InterruptedException {
		user.rentNotFixed("15");
		user.addPaymentDetails(refundableDeposit);
	}

	@Then("User Selects Rent Day, Notice Period, and Amenities")
	public void user_selects_rent_day_notice_period_and_amenities() throws InterruptedException {
		user.selectRentDay();
		user.selecNoticePeriod("2");
		user.selectAmenities("Number of Beds");
	}

	@When("Clicks on Save and Continue button and Navigate to SummaryPage")
	public void clickSaveAndContinueAndNavigateToSummaryPage() throws InterruptedException {
		user.SaveAndContinueBtn();
	}

	@Then("Clicking on Add New button and submitting delivery Address {string} and {string}")
	public void clicking_on_add_new_button_and_submitting_delivery_address_and(String address, String pincode) {
		user.clickOnAddNewAddress();
		user.addDeliveryAddress(address, pincode);

	}

	@Then("Clicking on Number of Extra copy {string} button.")
	public void clicking_on_number_of_extra_copy_button(String NumberOfCopy) {
		extraTwoCopyPrice = user.getExtraTwoCopyPrice();
		extraOneCopyPrice = user.getExtraOneCopyPrice();
		user.selectExtraCopy(NumberOfCopy);
	}

	@Then("Validate Agreement and Quotation Pricing Details in Summary Page")
	public void validate_agreement_and_quotation_pricing_details_in_summary_page() {

		int conveninceAmt = user.getLIneItemsPrice("Convenience Charges");
		Assert.assertEquals(conveninceAmt, 379);
		System.out.println("Convenince Charge :" + conveninceAmt);

		int stampDuty = user.getLIneItemsPrice("Govt Stamp Duty");
		Assert.assertEquals(stampDuty, 100);
		System.out.println("Stamp Duty Amount :" + stampDuty);

		int priceTwoExtraCopyAmt = user.getLIneItemsPrice("Two extra original copies ");
		int actualPrice = user.getExtraTwoCopyPrice();
		int tolerance = 1;
		// Assert.assertEquals(priceTwoExtraCopyAmt, extraTwoCopyPrice);
		Assert.assertTrue(Math.abs(actualPrice - priceTwoExtraCopyAmt) <= tolerance);
		System.out.println("Two Extra Copy Amount :" + priceTwoExtraCopyAmt);

		if (isBefore3PM) {
			deliveryPrice = user.getLIneItemsPrice("Same Day Delivery");
		} else {
			deliveryPrice = user.getLIneItemsPrice("Next Day Delivery");
		}
		System.out.println(" Delivery Amount :" + deliveryPrice);

		int eSignAgreement = user.getLIneItemsPrice("E-Sign Agreement");
		Assert.assertEquals(eSignAgreement, 150);
		System.out.println("E-Sign Agreement Amount :" + eSignAgreement);

		int notarisedAgreement = user.getLIneItemsPrice("Notarised Agreement");
		Assert.assertEquals(notarisedAgreement, 300);
		System.out.println("Notarised Agreement  Amount :" + notarisedAgreement);

		int tenantVerification = user.getLIneItemsPrice("Tenant Verification");
		Assert.assertEquals(tenantVerification, 99);
		System.out.println("Tenant Verification Amount :" + tenantVerification);

		int discountAmt = user.getDiscountAmt();
		System.out.println("Discount Amount :" + discountAmt);

		int sumTotal = user.sumLineItemAmounts();
		System.out.println("Sum of LineItem Amount :" + sumTotal);

		PayebleTotal = user.getTotalAmt();
		System.out.println("Total Payeble Amount :" + PayebleTotal);

		Assert.assertEquals(sumTotal, PayebleTotal);

		String expPropertyDetails = flatNumber + " " + floorNumber + " " + buildingName + " " + locality
				+ " Bangalore Karnataka " + pincode;
		String expLandlordDetails = fullName + " " + Age + " " + gender + " " + phone + " " + ownerEmail + " "
				+ panNumber + "\n" + parmanentAddress + "\n" + pincode;
		String expTenantDetails = "Sandhya R 25 Female 9503333701 gm@gmail.com PAN: ABCDE4321G\nB-111 Bangalore\n560035";

		String propDetails = user.getPropertyDetails();
		Assert.assertEquals(propDetails, expPropertyDetails);

		String landlordDetails = user.getlandlordDetail();
		// Assert.assertEquals(landlordDetails, expLandlordDetails);

		String tenantDetails = user.getTenatDetails();
		// Assert.assertEquals(tenantDetails, expTenantDetails);
	}

	@When("Clicking on Pay button and Navigate to Payment Dashboard")
	public void clicking_on_pay_button_and_navigate_to_payment_dashboard() {
		user.clickingOnPayBtn();
	}

	@When("User entering the Payment Details {string} {string} {string}  {string} and clicks on Make Payment Btn")
	public void user_entering_the_payment_details_and_clicks_on_make_payment_btn(String cardNumber, String expiryDate,
			String cvv, String name) {
		user.enteringCardDetails(cardNumber, expiryDate, cvv, name);
	}

	@When("User Entering OTP {string} and click on SubmitBtn")
	public void user_entering_otp_and_click_on_submit_btn(String otp) {
		user.enteringAndSubmittingOtp(otp);
	}

	@Then("Validating the Payment and Order Confirmation status in MyBooking")
	public void validating_the_payment_and_order_confirmation_status_in_my_booking() {
		String myBookingStatus = user.getOrderStatus();
		Assert.assertEquals(myBookingStatus, "Order Confirmed");
		int paidAmt = user.getPaidAmt();
		int tolerance2 = 4;
		if (isBefore3PM) {
			Assert.assertTrue(Math.abs(paidAmt - PayebleTotal) <= tolerance2);
		} else {
			Assert.assertTrue(Math.abs(paidAmt - PayebleTotal) <= tolerance2);
		}
	}

	public static String generateRandomMobileNumber() {
		return "76" + RandomStringUtils.randomNumeric(6) + "21";
	}
}
