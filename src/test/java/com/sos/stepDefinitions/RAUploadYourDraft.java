package com.sos.stepDefinitions;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;

import com.github.javafaker.Faker;
import com.sos.CommonFactory.WebBrowser;
import com.sos.Pages.WebPages.RentalServiceWebPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.time.LocalTime;

public class RAUploadYourDraft {
	private Faker faker = new Faker();
	private LocalTime currentTime = LocalTime.now();
	private boolean isBefore3PM = currentTime.isBefore(LocalTime.of(15, 0));
	private RentalServiceWebPage user;
	private String url = WebBrowser.getUrl();
	private String name = faker.name().firstName();
	private String tenantName = faker.name().firstName();
	private String phoneNumber = generateRandomMobileNumber(); // faker.phoneNumber().cellPhone();
	private String email = faker.internet().emailAddress();
	private String ra = "Rental Agreement", city = "Bangalore", imType = "Owner";
	private String stampDuty = "Govt Stamp Duty", ConvenienceCharges = "Convenience Charges",
			twoExtraCopy = "Two extra original copies ";
	private String notarizedAddon = "Notarised Agreement", esignAddon = "E-Sign Agreement";
	private int stampDutyAmt = 100, ConvenienceAmt = 379, notarizedAddonAmt = 300, esignAddonAmt = 100,
			twoExtraCopyAmt = 883;
	private int nextDayDeliveryTotal = 1029, nextDayDeliveryWithExtraCopyTotal = 1872;
	private int sameDayDeliveryTotal = 1079, sameDayDeliveryWithExtraCopyTotal = 1962;int deliveryPrice;
	private int PayebleTotal;

	@Given("User Landed on HomePage")
	public void user_landed_on_home_page() {
		user = new RentalServiceWebPage(WebBrowser.getDriver());
		WebBrowser.getDriver().get(url);
	}

	@When("User Enters valid PhoneNumber, Name, and email")
	public void user_enters_valid_phone_number_name_and_email() throws InterruptedException {
		user.clickLogin();
		user.enterMobile(phoneNumber);
		user.enterUsername(name);
		user.enterSignupEmail(email);
	}

	@When("User clicks on login continue button")
	public void user_clicks_on_login_continue_button() {
		user.clickLoginContinue();
	}

	@Then("validate the outcome loggedIn or SignedUp {string}")
	public void validate_the_outcomes(String expected) {
		String actualText = user.getLoginSignUpSuccesstext();
		Assert.assertTrue(actualText.contains(expected));
	}

	@Given("User is on the Legal Services Page")
	public void user_is_on_the_legal_services_page() throws InterruptedException {
		user.selectHSServiceType(ra);
	}

	@When("User selects a city from the available options")
	public void user_selects_a_city_from_the_available_options() throws InterruptedException {
		user.selectCityOrFinishYourAgreement(city);
	}

	@When("User clicks on the Check Price button")
	public void user_clicks_on_the_check_price_button() {
		user.checkPrices();
	}

	@When("User selects the {string} package")
	public void user_selects_the_package(String packageTitle) {
		user.selectPackage(packageTitle);
	}

	@When("User uploads the Old or Existing Rent Agreement draft")
	public void user_uploads_the_old_or_existing_rent_agreement_draft() {
		user.uploadExistingDraft();
	}

	@When("User selects Stamp Paper option")
	public void user_selects_stamp_paper_option() {
		user.selectStampaper("₹ 100 Stamp");
	}

	@When("User selects Rent Agreement Start Date")
	public void user_selects_rent_agreement_start_date() throws InterruptedException {
		user.selectAgreementStartDate();
	}

	@When("User enters LandlordName and TenantName")
	public void user_enters_landlord_name_and_tenant_name() {
		user.selectIam(imType);
		user.enterLandloardTenantName(name, tenantName);

	}

	@When("User selects Notarized Agreement Addon, eSign Addon, delivery Addon, and clicks on Save and Continue button")
	public void user_selects_addons_clicks_on_save_and_continue_button() throws InterruptedException {
		user.selectNotarizedAgreementAddon();
		user.selectEsignAddon(1);
		user.selectDeliveryAddonOption();
		user.SaveAndContinueBtn();
	}

	@Then("Verify the Title of the selected {string} Package is displayed on the Summary Page")
	public void verify_the_title_of_the_selected_package_is_displayed_on_the_summary_page(String Expectedtitle) {
		String actualTitle = user.getQuatationTitle();
		Assert.assertEquals(actualTitle, Expectedtitle);
	}

	@Then("validate the Quotation Price Details")
	public void validate_the_quotation_price_details() {
		int conveninceAmt = user.getLIneItemsPrice("Convenience Charges");
		Assert.assertEquals(conveninceAmt, 379);
		System.out.println("Convenince Charge :" + conveninceAmt);

		int stampDuty = user.getLIneItemsPrice("Govt Stamp Duty");
		Assert.assertEquals(stampDuty, 100);
		System.out.println("Stamp Duty Amount :" + stampDuty);

		/*int priceTwoExtraCopyAmt = user.getLIneItemsPrice("Two extra original copies");
		int actualPrice = user.getExtraTwoCopyPrice();
		int tolerance = 1;
		// Assert.assertEquals(priceTwoExtraCopyAmt, extraTwoCopyPrice);
		Assert.assertTrue(Math.abs(actualPrice - priceTwoExtraCopyAmt) <= tolerance);
		System.out.println("Two Extra Copy Amount :" + priceTwoExtraCopyAmt);
        */
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
	}

	@When("User clicks on Add New button in Summary")
	public void user_clicks_on_add_new_button_in_summary() {
		user.clickOnAddNewAddress();
	}

	@When("User submits delivery details name , email , phone  {string} and {string}")
	public void user_submits_delivery_details_and(String address, String pincode) {
		user.addDeliveryAddress(address, pincode);
	}

	@Then("Validate the checked icon and delivery Receiver name in summary")
	public void validate_the_checked_icon_and_delivery_receiver_name_in_summary() {
		String actualReceiversName = user.getDeliveryRecversName();
		Assert.assertTrue(actualReceiversName.contains(name), "Receiver name not found in summary");
	}

	@When("User submits delivery details name, email, phone {string} and {string}")
	public void user_submits_delivery_details_name_email_phone_and(String address, String pincode) {
		user.addDeliveryAddress(address, pincode);
	}

	@When("User clicks on Number of Extracopy {string} button.")
	public void user_clicks_on_number_of_extracopy_button(String NumberOfCopy) {
		user.selectExtraCopy(NumberOfCopy);
	}

	@Then("validate the Quotation Price Details After adding extracopy")
	public void validate_the_quotation_price_detailss() {
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
	}

	@Then("User clicking on  PayButton")
	public void user_clicking_on_pay_button() {
		user.clickingOnPayBtn();
	}

	@Then("User entered {string} {string} {string}  {string} and clicks on Make Payment Btn")
	public void user_entered_and_clicks_on_make_payment_btn(String cardNumber, String expiryDate, String cvv,
			String name) {
		user.enteringCardDetails(cardNumber, expiryDate, cvv, name);
	}

	@Then("User Enter {string} and click on SubmitButton")
	public void user_enter_and_click_on_submit_button(String otp) {
		user.enteringAndSubmittingOtp(otp);
	}

	@Then("Validating the Order Confirmation in  MyBookings")
	public void validating_the_order_confirmation_in_my_bookings() {
		String myBookingStatus = user.getOrderStatus();
		Assert.assertEquals(myBookingStatus, "Order Confirmed");
		int paidAmt = user.getPaidAmt();
		int tolerance2 = 4;
		if (isBefore3PM) {
			Assert.assertTrue(Math.abs(paidAmt - PayebleTotal) <= tolerance2);
		} else {
			// Assert.assertTrue(Math.abs(paidAmt - PayebleTotal) <= tolerance2);
			System.out.println("Ignoring MyBooking Calculation");
		}
	}

	public static String generateRandomMobileNumber() {
		return "765" + RandomStringUtils.randomNumeric(4) + "321";
	}
}
