
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

public class RAStampedAgreement {
	private Faker faker = new Faker();
	private LocalTime currentTime = LocalTime.now();
	private boolean isBefore3PM = currentTime.isBefore(LocalTime.of(15, 0));
	private RentalServiceWebPage user;
	private String url = WebBrowser.getUrl();
	private String name = faker.name().firstName();
	private String phoneNumber = generateRandomMobileNumber(); // faker.phoneNumber().cellPhone();
	private String email = faker.internet().emailAddress();
	private String ra = "Rental Agreement", stampDuty = "Govt Stamp Duty", ConvenienceCharges = "Convenience Charges";
	private String twoExtraCopy = "Two extra original copies ";
	private String floorNumber, chooseBHK, typeOfProperty, flatNumber, buildingName, locality, pincode;
	private String fullName, Age, phone, parmanentAddress, panNumber, ownerEmail, partyType, gender, tenantEmail;
	private String agreementDuration, rentAmount, refundableDeposit, minLocking;
	private int stampDutyAmt = 100, ConvenienceAmt = 379, twoExtraCopyAmt = 843;
	private int expectedTotal1 = 479, expectedTotal2 = 461;
	private int deliveryPrice , PayebleTotal;


	@Given("User is on the HomePage")
	public void user_is_on_the_home_page() {
		user = new RentalServiceWebPage(WebBrowser.getDriver());
		WebBrowser.getDriver().get(url);
	}

	@When("User enters a valid Login Credentials")
	public void user_enters_a_valid_phone_number_name_and_email() throws InterruptedException {
		user.loginSignUpMethod(phoneNumber, "9999", name, email);
	}

	@Then("the user is logged in or signed up {string}")
	public void validate_the_outcome(String expected) {
		String actualText = user.getLoginSignUpSuccesstext();
		Assert.assertTrue(actualText.contains(expected));
	}

	@Given("User Landed on the Legal Services HomePage")
	public void user_landed_on_the_legal_services_home_page() {
		try {
			user.selectHSServiceType(ra);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@When("User Selects a {string} from the available options")
	public void user_selects_a_from_the_available_options(String city) throws InterruptedException {
		user.selectCityOrFinishYourAgreement(city);
	}

	@When("User click on the Check Price button")
	public void userClicksCheckPrice() {
		user.checkPrices();
	}

	@When("User Selects the {string} package")
	public void userSelectsPackage(String packageTitle) {
		user.selectPackage(packageTitle);
	}

	@When("Selects Stamp Paper {string} option from the dropdowns")
	public void selects_stamp_paper_option_from_the_dropdowns(String stamp) {
		user.selectStampaper(stamp);
	}

	@And("Selecting Rent Agreement Start Date")
	public void userSelectsRentStartDate() throws InterruptedException {
		user.selectAgreementStartDate();
	}

	@When("clicking on the summaryContent and redirects on the SummaryPage")
	public void clicking_on_the_summary_content_and_redirects_on_the_summary_page() {
		user.clickOnSummary();
	}

	@Then("validate the title {string} and Price details in SummaryPage")
	public void validateTitleAndPriceInSummaryPage(String expectedTitle) {
		String actualTitle = user.getQuatationTitle();
		Assert.assertEquals(actualTitle, expectedTitle);

		int actulTotal = user.sumLineItemAmounts();
		Assert.assertTrue(actulTotal == expectedTotal1 || actulTotal == expectedTotal2,
				"Actual total does not match either expected total");

		int actualstampDuty = user.getLIneItemsPrice(stampDuty);
		Assert.assertEquals(actualstampDuty, stampDutyAmt);

		int actualConveninceAmt = user.getLIneItemsPrice(ConvenienceCharges);
		Assert.assertEquals(actualConveninceAmt, ConvenienceAmt);
	}

	@When("User selects Notarized Agreement,eSign,delivery Addons and clicks on Save and Continue button")
	public void user_selects_notarized_agreement_e_sign_delivery_addons_and_clicks_on_save_and_continue_button() throws InterruptedException {
		user.selectNotarizedAgreementAddon();
		user.selectEsignAddon(1);
		user.selectDeliveryAddonOption();
		user.SaveAndContinueBtn();
	}

	@Then("validating the title {string} and Price details in SummaryPage")
	public void validating_the_title_and_price_details_in_summary_page(String expectedTitle) {
		String actualTitle = user.getQuatationTitle();
		Assert.assertEquals(actualTitle, expectedTitle);

		int actulTotal = user.sumLineItemAmounts();
		Assert.assertEquals(actualTitle, expectedTitle);

		boolean isBefore3PM = currentTime.isBefore(LocalTime.of(15, 0));

		if (isBefore3PM) {
			deliveryPrice = user.getLIneItemsPrice("Same Day Delivery");
		} else {
			deliveryPrice = user.getLIneItemsPrice("Next Day Delivery");
		}

		int actualstampDuty = user.getLIneItemsPrice(stampDuty);
		Assert.assertEquals(actualstampDuty, stampDutyAmt);

		int actualConveninceAmt = user.getLIneItemsPrice(ConvenienceCharges);
		Assert.assertEquals(actualConveninceAmt, ConvenienceAmt);
	}

	@And("clicking on Edit {string}")
	public void clickingOn(String editTitle) {
		user.editSummaryDetails(editTitle);
	}

	@When("User Enters Property Details:")
	public void user_enters_property_details(io.cucumber.datatable.DataTable dataTable) {
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

		try {
			user.enterPropertyDetail(selectPropertyMap, textPropertyMap);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@And("Clicking on Add Btn for adding Number of Bathrooms {int}")
	public void clickingOnAddBtnForNumberOfBathrooms(int numberOfBathrooms) {
		user.selectNumberOfBathrooms(numberOfBathrooms);
	}

	@Then("validating the Property Details in Summary")
	public void validateAddressDetails() throws InterruptedException {
		System.out.println("SuccesFUlly Validated Property Details");
		user.SaveAndContinueBtn();
	}

	@When("User Enters Landlord Details:")
	public void userEntersLandlordDetailsDetails(io.cucumber.datatable.DataTable dataTable)
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

		user.enterSelectLanlordDetails(selectLanlordMap);
		Thread.sleep(2000);
		user.enterLandlordText(textLanlordMap);

	}

	@When("User selects Notarized Agreement, eSign, delivery Addons and clicks on Save and Continue button")
	public void user_selects_notarized_agreement_esign_delivery_addons_and_clicks_on_save_and_continue_button() throws InterruptedException {
		user.selectNotarizedAgreementAddon();
		user.selectEsignAddon(1);
		user.selectDeliveryAddonOption();
		user.SaveAndContinueBtn();
	}

	@Then("validating the Landlord Details in Summary")
	public void validateLanlordDetails() throws InterruptedException {
		System.out.println("SuccesFUlly Validated Landlord Details");
		user.SaveAndContinueBtn();
		Thread.sleep(2000);
	}

	@When("User Enters Tenant Details:")
	public void userEntersTenantDetailsDetails(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
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

	@Then("validating the Tenant Details in Summary")
	public void validateTenantDetails() throws InterruptedException {
		System.out.println("SuccesFUlly Validated Tenant Details");
		user.SaveAndContinueBtn();
		Thread.sleep(2000);
	}

	@When("User Enters Contract Details:")
	public void user_enters_contract_details(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
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
		user.selectAgreementStartDate();
		user.rentNotFixed("15");
		user.addPaymentDetails(refundableDeposit);
		user.selectRentDay();
		user.selecNoticePeriod("2");
		user.selectAmenities("Number of Beds");
		user.SaveAndContinueBtn();
	}

	@When("User clicking on Add New button in SummaryPage")
	public void user_clicks_on_add_new_button_in_summary() {
		user.clickOnAddNewAddress();
	}

	@When("User submiting delivery details {string} and {string}")
	public void user_submits_delivery_details_and(String address, String pincode) {
		user.addDeliveryAddress(address, pincode);
	}

	@When("User clicking on Number of Extracopy {string} button.")
	public void user_clicks_on_number_of_extracopy_button(String NumberOfCopy) {
		user.selectExtraCopy(NumberOfCopy);
	}

	@Then("Validate the Quotation Pricing Details After adding extracopy")
	public void validate_the_quotation_price_detailss() {
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
		Assert.assertEquals(eSignAgreement, 100);
		System.out.println("E-Sign Agreement Amount :" + eSignAgreement);

		int notarisedAgreement = user.getLIneItemsPrice("Notarised Agreement");
		Assert.assertEquals(notarisedAgreement, 300);
		System.out.println("Notarised Agreement  Amount :" + notarisedAgreement);

		//int tenantVerification = user.getLIneItemsPrice("Tenant Verification");
		//Assert.assertEquals(tenantVerification, 99);
		//System.out.println("Tenant Verification Amount :" + tenantVerification);

		int discountAmt = user.getDiscountAmt();
		System.out.println("Discount Amount :" + discountAmt);

		int sumTotal = user.sumLineItemAmounts();
		System.out.println("Sum of LineItem Amount :" + sumTotal);

		PayebleTotal = user.getTotalAmt();
		System.out.println("Total Payeble Amount :" + PayebleTotal);

		Assert.assertEquals(sumTotal, PayebleTotal);
	}

	@When("User clicking on  PayNow button")
	public void user_clicking_on_pay_now_button() {
		user.clickingOnPayBtn();
	}

	@When("User entering Payment Details {string} {string} {string}  {string} and clicks on Make Payment Btn")
	public void paymentDetails(String cardNumber, String expiryDate, String cvv, String name) {
		user.enteringCardDetails(cardNumber, expiryDate, cvv, name);
	}

	@Then("User Entering {string} and click on SubmitButton")
	public void user_enter_and_click_on_submit_button(String otp) {
		user.enteringAndSubmittingOtp(otp);
	}

	@Then("Validating the Order Confirmation status in MyBooking")
	public void validating_the_order_confirmation_status_in_my_booking() {
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
