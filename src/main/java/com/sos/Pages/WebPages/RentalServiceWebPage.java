package com.sos.Pages.WebPages;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.sos.CommonFactory.CommonSelenium;

public class RentalServiceWebPage extends CommonSelenium {
	WebDriver driver;

	public RentalServiceWebPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	// LOGIN-SIGNUP
	private By loginLinkBtn = By.xpath("//div[contains(@class,'nav-item-content') and text()='Log in']");
	private By mobileInput = By.xpath("//input[starts-with(@placeholder,'Enter') and @maxlength='10']");
	private By otpField = By.xpath("//div[@class='otp-wrapper']//input");
	private By loginsubmit = By.id("signUpSubmit");
	private By usernameField = By.id("login-signup-form__name-input");
	private By emailField = By.id("login-signup-form__email-input");
	private By signUpsubmit = By.xpath("//button[@type='submit']");
	//
	private By Other = By.xpath("//div[@class='css-151xaom-placeholder nb-select__placeholder' or text()='Other']");
	private By date = By.xpath("(//div[@aria-label='day-1'])[1]");
	private By oneDayDelivery = By.xpath(
			"//div[@class='addon-card' and div[@class='addon-card-left']/div[@class='addon-title' and text()='Get One Day Delivery']]//div[@class='add-button ']");
	private By proceedBtn = By.xpath("//button[contains(text(),'Proceed')]");
	private By SaveAndConLocBtn = By.xpath("//*[contains(text(),'Save and Continue')]");
	private By priviewBtn = By.xpath("//button[@class='btn btn-default'] [text()='Preview']");
	private By esigns = By.xpath("//div[contains(@class, 'addon-card')]//div[contains(@class, 'esign-counter')]/div");
	private By amountInput = By.xpath("//input[@id='ls-documentforms-amount-nbInput']");
	private By alertMsg = By.id("alertMessageBox");
	private By packageTitle = By.xpath("(//div[@class='description-title'])[2]");
	private By esignAddBtn = By.xpath("//div[@class='esign-counter ']");
	private By oneDayDeliveryAddBtn = By
			.xpath("//div[text()='Get One Day Delivery']//following::div[@class='add-button ']");
	private By notaryAddBtn = By
			.xpath("(//div[text()='Notarised Agreement']//following::div[@class='add-button '])[1]");
	private By landlordShare = By.xpath("//img[@src='/hs-new/public/LegalServices/upAero.svg']");
	private String genericSelectField = "(//label[contains(@for,'nbInput') and text()='labelName']|//label/div[text()='labelName'])/ancestor::div[starts-with(@id,'ls-')]//input/..";
	private String nbSelectValXpath = "//div[contains(@class,'nb-select') and contains(text(),'labelName')]";
	private String genericTxtField = "(//label[contains(@for,'nbInput') and text()='labelName']|//label/div[text()='labelName'])/ancestor::div[starts-with(@id,'ls-')]//input";
	private String editSummartBtn = "//div[.//div[@class='font-semibold text-18' and text()='editTitle']]/div[@class='abs-pos']//div[text()='Edit']";
	private By coninueAgreement = By.xpath("// div[contains(@class,'continue-agreement')]");
	// GENERATE ESTIMATE
	private By checkPriceBtn = By.cssSelector("div[class='action-button___3W21B'] button[type='button']");
	private By stampMenuList = By.xpath("//div[@class='css-q4imyt nb-select__menu-list']//div");
	private By calendar = By.xpath("//*[text()='Select Day'] | //*[@placeholder='Select Day']");
	private By currentMonthSelector = By.cssSelector("div[class*='current-month']");
	private By previousBtn = By.xpath("//button[contains(@class,'navigation--previous')]");
	private String sameDayId = "631834694edffcea583fb780";
	private String nextDayId = "6389be879d1a9cc5d788f8d6";
	private By discountLocator = By.xpath(
			"(//div[contains(@class, 'lineitemtitle') and contains(text(), 'Discount')] //following::div[@class='lineitemprice' ])[1]");
	private By totalAmt = By.xpath(
			"//div[contains(@class, 'lineitemtitle') and (text()='Total Amount')] //following::div[@class='lineitemprice' ]");
	private By raSummary = By.xpath("//div[contains(@class,'nbselected')]");

	private By extraTwoCopy = By.xpath("(//div[contains(@class,'my-3')]//child::div[@class='flex gap-1'])[1]");
	private By extraOneCopy = By.xpath("//div[contains(@class,'my-3')]//following::div[@class='flex gap-1']");
	// TV
	private By getStartedLocater = By.xpath("//div[contains(@class,'3W21B')]//*[@type='button'][text()='Get Started']");
	private By moreLocater = By.xpath("//div[contains(text(),'more')]");
	private By legalServiceloc = By.xpath("//div[text()='Legal Services']");
	private String leagalServices = "//div[@class='item-label' and text()='service']";
	private String selectTvPackage = "(//div[@class='title' and text()='package']/ancestor::div[@class='item']//button[contains(@class,'select-package')])[1]";
	private By selectIdCard = By.xpath("//div[contains(@class,'select') and text()='Select ID card']");
	private String idTypesLocator = "//div[contains(@class,'select__menu-list')]//div[text()='idType']";
	private By idNumberLoc = By.id("cardNumber");
	private By tenantNameloc = By.name("name");
	private By tenantPhoneloc = By.name("mobileNumber");
	private By dobLoc = By.xpath("//input[@class='dob-field']");
	private By tvSummaryTitle = By
			.xpath("//div[@class='ls-summary-packagedescription desktop-mode']//div[@class='description-container']");
	private By tvTotal = By.xpath("//div[@class='ls-summary-lineitem total']");
	private By tenantGenderLoc = By.xpath("//div[text()='Select Gender']");

	private By helpPopIframe = By.xpath("//iframe[@title='Zendesk Chat widget window']");
	private By helpMinimise = By.xpath("//div[@title='Minimize']");
	private By basicDetailsLabel = By.xpath("//span[contains(text(),'Basic Details')]");

	public void SaveAndContinueBtn() throws InterruptedException {
		if (isElementPresent(driver, SaveAndConLocBtn)) {
			click(SaveAndConLocBtn);
			waitFor(1);
		} else {
			click(SaveAndConLocBtn);
			waitFor(2);
		}
	}

	public void closingChatHelp() {
		if (isElementPresent(driver, helpPopIframe)) {
			driver.switchTo().frame(driver.findElement(helpPopIframe));
			click(helpMinimise);
			driver.switchTo().defaultContent();
		} else {
			System.out.println("Chat Help PopUp Not Appears");
		}
	}

	public void tenantBasicVerificationDetails(String idType, String idNumber, String name, String phone, String dob,
			String fatherName) throws InterruptedException {
		driver.navigate().refresh();
		Thread.sleep(5000);
		click(selectIdCard);
		click(driver.findElement(By.xpath(idTypesLocator.replace("idType", idType))));
		sendKeys(idNumberLoc, idNumber);
		sendKeys(tenantNameloc, name);
		sendKeys(tenantPhoneloc, phone);
		sendKeys(dobLoc, dob);
		waitFor(1);
		scrollUpto(tenantGenderLoc);
		click(tenantGenderLoc);
		waitFor(1);
		click(By.xpath("//div[@class='css-11unzgr nb-select__menu-list']//div[text()='Male']"));
		sendKeys(By.name("fatherName"), fatherName);
		click(SaveAndConLocBtn);
		waitFor(2);
	}

	public void verificationAddress(String flatNo, String locality, String pincode) {
		sendKeys(By.name("line1"), flatNo);
		sendKeys(By.name("line2"), locality);
		sendKeys(By.name("pincode"), pincode);
		click(SaveAndConLocBtn);
	}

	public void referenceName(String refName, String refMobNumber, String refEmail) {
		sendKeys(By.name("refName"), refName);
		sendKeys(By.name("refMobileNumber"), refMobNumber);
		sendKeys(By.name("refEmail"), refEmail);
		click(SaveAndConLocBtn);
	}

	public String getTvPackageTitle() {
		String packageTitle = driver.findElement(tvSummaryTitle).getText();
		return packageTitle;
	}

	public int getTvSummaryTotalAmount() {
		String tvtotalText = driver.findElement(tvTotal).getText();
		String[] amtTExt = tvtotalText.split("₹");
		int Amount = Integer.parseInt(amtTExt[1]);
		System.out.println("Total Amount : " + Amount);
		return Amount;
	}

	public void selectTenantVerification(String service) {
		waitForElementDisplay(driver, moreLocater, 5);
		click(moreLocater);
		waitFor(1);
		click(legalServiceloc);
		if (isElementPresent(driver, By.xpath(leagalServices.replace("service", service)))) {
			click(By.xpath(leagalServices.replace("service", service)));
		} else {
			waitForElementToBeClickable(driver,
					By.xpath("//div[@class='item-label' and text()='service']".replace("service", service)), 10);
			click(By.xpath("//div[@class='item-label' and text()='service']".replace("service", service)));
		}

		waitFor(3);
	}

	public void selectTenantVerificationPackage(String tvpackage) {
		if (driver.findElement(getStartedLocater).isDisplayed()) {
			click(getStartedLocater);
		} else {
			waitForElementDisplay(driver, getStartedLocater, 5);
			click(getStartedLocater);
		}
		driver.navigate().refresh();
		waitFor(2);
		// click(driver.findElement(By.xpath(selectTvPackage.replace("package",
		// tvpackage))));
		click(driver.findElement(By.xpath("(//div[@class='title' and text()='" + tvpackage
				+ "']/ancestor::div[@class='item']//button[contains(@class,'select-package')])[1]")));
	}

	public void tenantVerificationDetails(String idType, String idNumber, String name, String dob) throws InterruptedException {
		Thread.sleep(2000);
		click(selectIdCard);
		click(driver.findElement(By.xpath(idTypesLocator.replace("idType", idType))));
		sendKeys(idNumberLoc, idNumber);
		sendKeys(tenantNameloc, name);
		sendKeys(dobLoc, dob);
	}

	public int getExtraTwoCopyPrice() {
		int extraTwoCopyPrice = 0;
		String extraTwoCopyText = driver.findElement(extraTwoCopy).getText();
		String[] parts = extraTwoCopyText.split("\\₹", 3);
		if (parts.length >= 3) {
			String result = parts[2].trim();
			extraTwoCopyPrice = Integer.parseInt(result);
			System.out.println("extraTwoCopyPrice :" + extraTwoCopyPrice);
		}
		return extraTwoCopyPrice;
	}

	public int getExtraOneCopyPrice() {
		int extraOneCopyPrice = 0;
		String extraOneCopyText = driver.findElement(extraOneCopy).getText();
		String[] parts = extraOneCopyText.split("\\₹", 3);
		if (parts.length >= 3) {
			String result = parts[2].trim();
			extraOneCopyPrice = Integer.parseInt(result);
			System.out.println("extraTwoCopyPrice :" + extraOneCopyPrice);
		}
		return extraOneCopyPrice;
	}

	public void loginSignUpMethod(String mobileNumber, String otp, String username, String email) {
		waitFor(5);
		click(loginLinkBtn);
		waitForElementDisplay(driver, mobileInput, 2);
		sendKeys(mobileInput, mobileNumber);

		if (isElementPresent(driver, otpField)) {
			sendKeys(otpField, otp);
			click(loginsubmit);

		} else {
			waitForElementDisplay(driver, usernameField, 1);
			sendKeys(usernameField, username);

			waitForElementDisplay(driver, emailField, 1);
			sendKeys(emailField, email);

			waitForElementToBeClickable(driver, signUpsubmit, 1);
			click(signUpsubmit);
		}
	}

	public String getLoginSignUpSuccesstext() {
		String signedUptext = driver.findElement(alertMsg).getText();
		if (isTextPresent(driver, signedUptext)) {
			return signedUptext;
		} else {
			return "Signed in Successfully";
		}
	}

	public void genrateEstimate(String stamPaper, String agrMonth, int esignNum) throws InterruptedException {
		click(Other);
		List<WebElement> stampList = driver.findElements(stampMenuList);
		boolean foundStamp = false;
		for (WebElement stamp : stampList) {
			if (stamp.getText().equals(stamPaper)) {
				try {
					stamp.click();
					break;
				} catch (StaleElementReferenceException e) {
					stamp.click();
				}
			}
		}
		if (!foundStamp) {
			click(By.xpath(" //*[text()='₹ 100 Stamp']"));
		}

		waitForElementDisplay(driver, calendar, 3);
		click(calendar);
		waitForElementDisplay(driver, currentMonthSelector, 3);
		WebElement currentMonth = driver.findElement(currentMonthSelector);
		while (true) {
			if (currentMonth.getText().equals(agrMonth)) {
				break;
			} else {
				click(previousBtn);
				Thread.sleep(1000);
			}
		}
		click(date);
		click(notaryAddBtn);
		click(esignAddBtn);
		waitFor(1);

		for (int i = 0; i < esignNum; i++) {
			String selected = driver.findElement(By.xpath("//div[text()='1']")).getText();
			int selectedEsign = Integer.parseInt(selected);

			if (selectedEsign <= esignNum) {
				click(By.xpath("//div[text()='+']"));
			} else {
				break; // Exit the loop when selected Esign becomes equal to or greater than esignNum
			}
		}

		click(oneDayDeliveryAddBtn);
		if (Integer.valueOf(new SimpleDateFormat("kk").format(new Date())) < 15) {
			click(By.id(sameDayId)); // SameDay
		} else {
			click(By.id(nextDayId)); // NextDay
		}
		click(proceedBtn);
	}

	public String getPropertyDetails() {
		List<WebElement> raDetails = driver.findElements(raSummary);
		if (!raDetails.isEmpty()) {
			String propDetailText = raDetails.get(0).getText();
			return propDetailText.replace(",", "");
		} else {
			System.out.println("No Property Details found.");
			return "";
		}
	}

	public String getlandlordDetail() {
		List<WebElement> raDetails = driver.findElements(raSummary);
		if (!raDetails.isEmpty()) {
			String landlordDetailText = raDetails.get(1).getText();
			return landlordDetailText.replace(",", "");
		} else {
			System.out.println("Landlord Details Not found.");
			return "";
		}
	}

	public String getTenatDetails() {
		List<WebElement> raDetails = driver.findElements(raSummary);
		if (!raDetails.isEmpty()) {
			String tenatDetailText = raDetails.get(2).getText();
			return tenatDetailText.replace(",", "");
		} else {
			System.out.println("Tenant Details Not found.");
			return "";
		}
	}

	public String getcontractDetail() {
		List<WebElement> raDetails = driver.findElements(raSummary);
		if (!raDetails.isEmpty()) {
			String contractDetailText = raDetails.get(2).getText();
			return contractDetailText.replace(",", "");
		} else {
			System.out.println("Contract Details Not found.");
			return "";
		}
	}

	public void clickingOnPage(String pageTitle) {
		click(By.xpath("//div[contains(text(),'pageTitle')]".replace("pageTitle", pageTitle)));
	}

	public void finishExistingAgreement() {
		waitForElementToBeClickable(driver, coninueAgreement, 3);
		click(coninueAgreement);
	}

	public int sumLineItemAmounts() {
		List<WebElement> listEle = driver.findElements(By.xpath("//div[@class='ls-summary-lineitem']"));
		int totalAmt = 0, discountAmt = 0;
		String discountText = "";

		List<WebElement> discountElements = driver
				.findElements(By.xpath("//div[contains(@class,'lineitem discount')]"));
		if (!discountElements.isEmpty()) {
			discountText = discountElements.get(0).getText();
			if (!discountText.isEmpty()) {
				String[] discPart = discountText.split("₹");
				String discount = discPart[discPart.length - 1].trim();
				discountAmt = Integer.parseInt(discount);
			}
		}
		for (int i = 0; i < listEle.size(); i++) {
			String lineItemText = listEle.get(i).getText();
			String[] parts = lineItemText.split("₹");

			if (parts.length > 1) {
				String charge = parts[parts.length - 1].trim();
				int chargeAmt = Integer.parseInt(charge);
				totalAmt += chargeAmt;
			}
		}
		if (!discountText.isEmpty()) {
			return totalAmt - discountAmt;
		} else {
			return totalAmt;
		}
	}

	public void enterSelectLanlordDetails(Map<String, String> selectMap) throws InterruptedException {
		click(landlordShare);
		waitFor(1);
		String value;
		for (String labelName : selectMap.keySet()) {
			value = selectMap.get(labelName);
			waitForElementToBeClickable(driver, getFieldXpathLocator(genericSelectField, labelName), 3);
			driver.findElement(getFieldXpathLocator(genericSelectField, labelName)).click();
			waitFor(1);
			waitForElementToBeClickable(driver, getFieldXpathLocator(nbSelectValXpath, value), 3);
			driver.findElement(getFieldXpathLocator(nbSelectValXpath, value)).click();
			waitFor(1);
		}
	}

	public void enterLandlordText(Map<String, String> txtFieldMapr) throws InterruptedException {
		String value;
		for (String labelName : txtFieldMapr.keySet()) {
			value = txtFieldMapr.get(labelName);
			waitForElementDisplay(driver, getFieldXpathLocator(genericTxtField, labelName), 5);
			sendKeys(getFieldXpathLocator(genericTxtField, labelName), value);
		}
		waitFor(1);
	}

	public void enterSelectTenantDetails(Map<String, String> selectMap) throws InterruptedException {
		click(landlordShare);
		waitFor(1);
		String value;
		for (String labelName : selectMap.keySet()) {
			value = selectMap.get(labelName);
			waitForElementToBeClickable(driver, getFieldXpathLocator(genericSelectField, labelName), 2);
			driver.findElement(getFieldXpathLocator(genericSelectField, labelName)).click();
			waitFor(1);
			waitForElementToBeClickable(driver, getFieldXpathLocator(nbSelectValXpath, value), 2);
			driver.findElement(getFieldXpathLocator(nbSelectValXpath, value)).click();
			waitFor(1);
		}
	}

	public void enterTenantText(Map<String, String> txtFieldMapr) throws InterruptedException {
		String value;
		for (String labelName : txtFieldMapr.keySet()) {
			value = txtFieldMapr.get(labelName);
			waitForElementDisplay(driver, getFieldXpathLocator(genericTxtField, labelName), 2);
			sendKeys(getFieldXpathLocator(genericTxtField, labelName), value);
		}
		waitFor(1);
	}

	public int getDiscountAmt() {
		String discountText = driver.findElement(discountLocator).getText();
		String a = discountText.replaceAll("[^0-9]", "");
		int discountAmt = Integer.parseInt(a);
		return discountAmt;
	}

	public int getTotalAmt() {
		String totalAmtText = driver.findElement(totalAmt).getText();
		String a = totalAmtText.replaceAll("[^0-9]", "");
		int totalAmt = Integer.parseInt(a);
		return totalAmt;
	}

	public void enterSelectContractDetails(Map<String, String> selectMap) throws InterruptedException {
		String value;
		for (String labelName : selectMap.keySet()) {
			value = selectMap.get(labelName);
			waitForElementToBeClickable(driver, getFieldXpathLocator(genericSelectField, labelName), 2);
			driver.findElement(getFieldXpathLocator(genericSelectField, labelName)).click();
			waitFor(3);
			waitForElementToBeClickable(driver, getFieldXpathLocator(nbSelectValXpath, value), 2);
			driver.findElement(getFieldXpathLocator(nbSelectValXpath, value)).click();
			waitFor(3);
		}
	}

	public void enterContractText(Map<String, String> txtFieldMapr) throws InterruptedException {
		String value;
		for (String labelName : txtFieldMapr.keySet()) {
			value = txtFieldMapr.get(labelName);
			waitForElementDisplay(driver, getFieldXpathLocator(genericTxtField, labelName), 2);
			sendKeys(getFieldXpathLocator(genericTxtField, labelName), value);
		}
		waitFor(1);
	}

	public void rentNotFixed(String percentage) {
		waitFor(1);
		try {
			click(By.xpath("//input[@id='rentNotFixed']"));
			waitFor(1);
			sendKeys(By.xpath("//*[@name='incrementPercentage']"), percentage);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void enterPropertyDetail(Map<String, String> selectMap, Map<String, String> txtFieldMap)
			throws InterruptedException {
		String value;
		for (String labelName : selectMap.keySet()) {
			value = selectMap.get(labelName);
			waitForElementDisplay(driver, getFieldXpathLocator(genericSelectField, labelName), 3);
			driver.findElement(getFieldXpathLocator(genericSelectField, labelName)).click();
			waitFor(1);
			waitForElementDisplay(driver, getFieldXpathLocator(nbSelectValXpath, value), 3);
			driver.findElement(getFieldXpathLocator(nbSelectValXpath, value)).click();
			waitFor(1);
		}
		for (String labelName : txtFieldMap.keySet()) {
			value = txtFieldMap.get(labelName);
			sendKeys(getFieldXpathLocator(genericTxtField, labelName), value);
		}
	}

	public void selecNoticePeriod(String month) {
		click(By.xpath("//div[@class='item  w-20pe p-1p' and text()='" + month + "']"));
	}

	public By getFieldXpathLocator(String genericXpath, String labelName) {
		return By.xpath(genericXpath.replace("labelName", labelName));
	}

	public void editSummaryDetails(String editTitle) {
		click(By.xpath(editSummartBtn.replace("editTitle", editTitle)));
	}

	public void selectNumberOfBathrooms(int numberOfBathrooms) {
		if (!isElementPresent(driver, By.id("-add"))) {
			waitForElementToBeClickable(driver, By.id("-add"), 3);
		}
		click(By.id("-add"));
		for (int i = 1; i < numberOfBathrooms; i++) {
			waitForElementToBeClickable(driver, By.xpath("//button[@id='1-plus' or text()='+']"), 3);
			click(By.xpath("//button[@id='1-plus' or text()='+']"));
			waitFor(1);
		}
	}

	public void clickOnSummary() {
		click(By.xpath("//*[@class='stepcontent' and text()='Summary']"));
	}

	public void selectEsignAddon(int esignNum) {
		click(esignAddBtn);
		waitFor(1);

		for (int i = 0; i < esignNum; i++) {
			String selected = driver.findElement(By.xpath("//div[text()='1']")).getText();
			int selectedEsign = Integer.parseInt(selected);

			if (selectedEsign <= esignNum) {
				click(By.xpath("//div[text()='+']"));
			} else {
				break; // Exit the loop when selected Esign becomes equal to or greater than esignNum
			}
		}
	}

	public void clickingOnPayBtn() {
		click(By.xpath("//button[contains(@class,'paynow')]"));
	}

	public String getOrderStatus() {
		waitForElementDisplay(driver, By.xpath("//div[@class='status']"), 5);
		String orderStatus = driver.findElement(By.xpath("//div[@class='status']")).getText();
		return orderStatus;
	}

	public int getPaidAmt() {
		String paidAmtText = driver.findElement(By.xpath("//span[@class='price']")).getText();
		String cleanPaidAmt = paidAmtText.replaceAll("[₹,]", "").trim();
		int paidAmount = Integer.parseInt(cleanPaidAmt);
		return paidAmount;
	}

	public void enteringCardDetails(String cardNumber, String expiryDate, String cvv, String name) {
		waitForElementDisplay(driver, By.id("cardNumber"), 10);
		sendKeys(By.id("cardNumber"), cardNumber);
		sendKeys(By.id("expiryDate"), expiryDate);
		sendKeys(By.id("cvv"), cvv);
		sendKeys(By.id("name"), name);
		click(By.xpath("//button[@type='submit' and  text()='Make Payment']"));
	}

	public void enteringAndSubmittingOtp(String otp) {
		waitForElementDisplay(driver, By.xpath("//*[@placeholder='OTP']"), 5);
		sendKeys(By.xpath("//*[@placeholder='OTP']"), otp);
		click(By.id("submit-action"));
		waitFor(3);
	}

	public void selectNotarizedAgreementAddon() {
		click(notaryAddBtn);
	}

	public void selectDeliveryAddonOption() {
		click(oneDayDeliveryAddBtn);
		if (Integer.valueOf(new SimpleDateFormat("kk").format(new Date())) < 15) {
			click(By.id("631834694edffcea583fb780")); // SameDay
		} else {
			click(By.id("6389be879d1a9cc5d788f8d6")); // NextDay
		}
		click(proceedBtn);
	}

	public String getQuatationTitle() {
		String summaryTitleText = driver.findElement(packageTitle).getText();
		return summaryTitleText;
	}

	public int getLIneItemsPrice(String items) {
		By priceLoc = By.xpath(
				"//div[@class='ls-summary-lineitem' and .//div[@class='lineitemtitle' and text()='items']]//span[@class='price']"
						.replace("items", items));
		String priceText = driver.findElement(priceLoc).getText();
		int lineItemPrice = Integer.parseInt(priceText);
		return lineItemPrice;
	}

	public void clickOnAddNewAddress() {
		click(By.xpath("//div[@class='add-button'] | //span[text()=' Add New']"));
	}

	public String getDeliveryRecversName() {

		String reciversName = driver.findElement(By.xpath("//div[@class='address-info']")).getText();

		return reciversName;
	}

	public void selectExtraCopy(String extraCopy) {
		if ("1".equals(extraCopy)) {
			click(By.id("64ccac012f5b910001337302")); // Extra one copy
		} else if ("2".equals(extraCopy)) {
			click(By.id("652ce3c3ce221b0001d6c659")); // Extra two copy
		} else {
			System.out.println("Invalid argument: " + extraCopy);
		}
	}

	public void addDeliveryAddress(String Address, String pincode) {
		/*
		 * sendKeys(By.id("fullName-fullName-nbInput"), name);
		 * sendKeys(By.id("email-email-nbInput"), email);
		 * sendKeys(By.id("phone-phone-nbInput"), phone);
		 */
		sendKeys(By.id("street-street-nbInput"), Address);
		sendKeys(By.id("pincode-pincode-nbInput"), pincode);
		click(By.xpath("//button[contains(text(),'Submit')]"));
	}

	public void clickLogin() throws InterruptedException {
		By loginLinkBtn = By.xpath("//div[contains(@class,'nav-item-content') and text()='Log in']");
		click(loginLinkBtn);
		waitFor(2);
	}

	public void enterMobile(String mobile) {
		sendKeys(By.xpath("//input[starts-with(@placeholder,'Enter') and @maxlength='10']"), mobile);
		waitFor(2);
	}

	public void enterUsername(String username) {
		By usernameField = By.id("login-signup-form__name-input");
		waitForElementDisplay(driver, usernameField, 10);
		sendKeys(usernameField, username);
		waitFor(2);
	}

	public void enterSignupEmail(String email) throws InterruptedException {
		By emailField = By.id("login-signup-form__email-input");
		waitForElementDisplay(driver, emailField, 10);
		sendKeys(emailField, email);
	}

	public void clickLoginContinue() {
		By locator = By.xpath("//button[@type='submit']");
		waitForElementToBeClickable(driver, locator, 5);
		click(locator);
	}

	public void selectPackageNonMH(String packages) {
		String xpathExpression = "//div[@class='title' and text()='" + packages
				+ "']/parent::div/following-sibling::button[text()='Select']";
		By xpath = By.xpath(xpathExpression);
		click(xpath);
	}

	public void addBtn() {
		click(By.xpath("//*[text()='Add']"));
	}

	public void selectIam(String imType) {
		click(By.xpath("//div[text()='Owner']".replace("Owner", imType)));
	}

	public void enterLandloardTenantName(String lanlordName, String tenantName) {
		By lnameField = By.xpath("//input[@placeholder='Enter landlord name']");
		By tnameField = By.xpath("//input[@placeholder='Enter tenant name']");
		sendKeys(lnameField, lanlordName);
		sendKeys(tnameField, tenantName);
	}

	public void refreshPage() {
		driver.navigate().refresh();
		waitFor(2);
	}

	public void previewBtn() {
		waitForElementToBeClickable(driver, priviewBtn, 10);
		scrollUpto(priviewBtn);
		Actions actions = new Actions(driver);
		WebElement ele = driver.findElement(priviewBtn);
		actions.moveToElement(ele).click().build().perform();

		By dateLocator = By.xpath("//div[@class='text-justify']");
		WebElement dateElement = driver.findElement(dateLocator);
		String dataText = dateElement.getText();
		System.out.println(dataText);

	}

	public void hsToggle() {
		click(By.xpath("//*[contains(text(),'Home Services')]"));
	}

	public void esignAgreement() {
		waitForElementToBeClickable(driver, esigns, 10);
		scrollUpto(esigns);
		Actions actions = new Actions(driver);
		WebElement ele = driver.findElement(esigns);
		actions.moveToElement(ele).click().build().perform();
	}

	public void notarisedAgreement() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		By locator = By.xpath(
				"//div[@class='addon-card']//div[@class='addon-title' and text()='Notarised Agreement']/ancestor::div[@class='addon-card']/div[@class='addon-card-right']/div[@class='add-button ']");
		WebElement addButton = driver.findElement(locator);
		executor.executeScript("arguments[0].scrollIntoView(true);", addButton);
		addButton.click();
	}

	public void paymentSummaryDetails() {
		By totalAmt = By.xpath(
				"//div[contains(@class, 'ls-summary-lineitem') and contains(., 'Total Amount')]//span[@class='price']");
		WebElement totalAmountElement = driver.findElement(totalAmt);
		int totalAmount = Integer.parseInt(totalAmountElement.getText());
		Map<String, String> paymentSummary = new LinkedHashMap<>();
		List<WebElement> lineItems = driver
				.findElements(By.xpath("//div[@class='ls-summary-lineitem']//div[@class='lineitemtitle']"));
		List<WebElement> prices = driver.findElements(
				By.xpath("//div[@class='ls-summary-lineitem']//div[@class='lineitemprice']//span[@class='price']"));
		System.out.println("Legal Quotation Details :");
		for (int i = 0; i < lineItems.size(); i++) {
			String lineItem = lineItems.get(i).getText();
			String price = prices.get(i).getText();
			System.out.print(lineItem + "-" + price);
			paymentSummary.put(lineItem, price);
		}
		int totalPrice = 0;
		for (WebElement priceElement : prices) {
			String priceText = priceElement.getText().replaceAll("[^0-9]", ""); // Remove non-numeric characters
			int price = Integer.parseInt(priceText);
			totalPrice += price;
		}
		log("Validating [Sum of LineItem Prices :" + totalPrice + "-" + "Total Payeble Amount:" + totalAmount + "]");
		Assert.assertEquals(totalPrice, totalAmount);
	}

	public void sharelandlordTenantshareform() {
		By elementLocator = By.xpath("//img[contains(@src,'/hs-new/public/LegalServices/upAero.svg')]");
		waitForElementToBeClickable(driver, elementLocator, 5);
		click(elementLocator);
	}

	public void standardDeliveryOption() {
		By stanDard = By.id("6389be7c9d1a9cc5d788f8d5");
		log("CLicking on [Get One Day Delivery]");
		scrollClick(oneDayDelivery);
		waitFor(1);
		log("Selecting [Standard Delivery]");
		click(stanDard);
	}

	public void yesTick() {
		scrollClick(By.xpath("(//input[@id='yes-Yes'])[1]"));
	}

	public void selectAmenities(String amenityName) {
		By amenityxpath = By.xpath("//div[contains(@class, 'counter-label') and text()='" + amenityName
				+ "']/following-sibling::button//div[@id='-add']");
		click(By.xpath("//button[contains(text(),'Add Amenities')]"));
		click(amenityxpath);
		scrollClick(By.xpath("//button[contains(text(),'Save & Continue')]"));
	}

	public void selectRentDay() {
		scrollIntoView(driver, By.xpath(
				"//label[contains(@for,'rentDay')]//following::div[contains(@id,'rentDay')]//input[contains(@placeholder,'Select Day')]"));
		click(By.xpath(
				"//label[contains(@for,'rentDay')]//following::div[contains(@id,'rentDay')]//input[contains(@placeholder,'Select Day')]"));
		click(By.xpath("//div[contains(@class,'grid grid-cols-7 ls-number-modal-container')]//div[1]"));
	}

	public void selectNoticePeriod(String noticePeriod) {
		log("Selecting Notice Period [" + noticePeriod + "]");
		String xpath = "//div[@id='ls-documentforms-noticePeriod-nbInput-container']//div[text()='" + noticePeriod
				+ "']";
		click(By.xpath(xpath));
	}

	public void addPaymentDetails(String depositAmount) {
		click(By.xpath("//button[contains(.,'Add Payment Details')]"));
		By locator = By.xpath("(//*[name()='svg'][contains(@class,'css-19bqh2r')])[3]");
		waitForElementDisplay(driver, locator, 5);
		click(By.xpath("(//*[name()='svg'][contains(@class,'css-19bqh2r')])[3]"));
		List<WebElement> paymentModeList = driver
				.findElements(By.xpath("//div[@class='css-11unzgr nb-select__menu-list']//div"));
		WebElement cash = null;
		for (WebElement paymentMode : paymentModeList) {
			if (paymentMode.getText().equals("Cash")) {
				cash = paymentMode;
				break;
			}
		}
		if (cash != null) {
			cash.click();
		}

		waitForElementDisplay(driver, amountInput, 5);
		clearElement(driver, amountInput);
		sendKeys(amountInput, depositAmount);

		click(By.xpath("//button[contains(text(),'Save & Continue')]"));
	}

	public void selectMinimumLokinPeriod(String month) {
		String lockInPeriodXPath = String.format("//div[contains(@id,'lockinPeriod')]//div[contains(text(),'%s')]",
				month);
		if (Integer.parseInt(month) <= 3) {
			log("Selecting Minimum Lokin Period [" + month + "]");
			scrollClick(By.xpath(lockInPeriodXPath));
		} else {
			log("Selecting Minimum Lokin Period [" + month + "]");
			By elementLocator = By.xpath("(//*[text()='Other'])[last()]");
			waitForElementToBeClickable(driver, elementLocator, 5);
			scrollUpto(elementLocator);
			Actions actions = new Actions(driver);
			actions.moveToElement(driver.findElement(elementLocator)).click().build().perform();
		}
		List<WebElement> otherDrp = driver
				.findElements(By.xpath("//div[contains(@class,'menu-list')]//div[contains(text()," + month + ")]"));
		for (WebElement dropdownMonth : otherDrp) {
			if (dropdownMonth.getText().equals(month)) {
				try {
					dropdownMonth.click();
					break;
				} catch (StaleElementReferenceException e) {
					dropdownMonth.click();
				}
			}
		}
	}

	public void verifyPreview(String exclusions, Map<String, String>... maps) {
		log("Verifying preview :");
		int mapCount = 1;
		By previewContent = By.xpath("//button[@class='btn btn-default'][text()='Preview']");
		waitForElementToBeClickable(driver, previewContent, 5);
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(previewContent)).click().build().perform();
		String preview = driver.findElement(previewContent).getText();
		log(preview);
		String value;
		for (Map<String, String> m : maps) {
			System.out.println("Preview Map :" + (mapCount++));
			for (String field : m.keySet()) {
				if (!exclusions.contains(field.split(" ")[0])) // exclusions
				{
					value = m.get(field);
					if (!preview.toLowerCase().contains(value.toLowerCase()))
						log("Field:Value [" + field + ':' + value + "] not found in preview");
					Assert.assertTrue(
							preview.contains(value.toLowerCase()) || preview.equalsIgnoreCase(value.toLowerCase()));
				}
			}
		}
	}

	public void doubleRPCopy() {
		click(By.xpath("(//div[@class='addon-card']//div[@class='add-button '])[4]"));
	}

	public void addBathrooms() {
		log("Clicking on [ Add Button]");
		scrollClick(By.xpath("//div[@id='-add']"));
		for (int i = 1; i < 4; i++) {
			log("Clicking on [ Plus Symbol]");
			click(By.xpath("//button[contains(@id,'plus')]"));
		}
	}

	public void addBedroomBathroom() throws InterruptedException {
		log("Clicking on [ Add Button]");
		scrollClick(By.xpath("(//*[text()='Add'])[1]"));
		for (int i = 1; i < 4; i++) {
			log("Clicking on [ Plus Symbol]");
			click(By.xpath("//button[contains(@id,'plus')]"));
		}
		wait(1);
		log("Clicking on [ Add Button]");
		scrollClick(By.xpath("(//*[text()='Add'])[last()]"));
		for (int i = 1; i < 4; i++) {
			log("Clicking on [ Plus Symbol]");
			click(By.xpath("//button[contains(@id,'plus')]"));
		}
	}

	public void selectHSServiceType(String service) throws InterruptedException {
		String serviceHSxpath = "//span[text()='service']".replace("service", service);
		if (driver.findElement(By.xpath(serviceHSxpath)).isDisplayed()) {
			click(By.xpath(serviceHSxpath));
		} else {
			Thread.sleep(3000);
			click(By.xpath(serviceHSxpath));
			waitFor(3);
		}
	}

	public void checkPrices() {
		waitForElementToBeClickable(driver, checkPriceBtn, 5);
		click(checkPriceBtn);
	}

	public void selectPackage(String packageTitle) {
		By packageLocator = By.xpath("//div[@class='title'] [text()='packageTitle']//following::button[text()='Select']"
				.replace("packageTitle", packageTitle));
		click(packageLocator);
		waitFor(1);
	}

	public void selectMHpackages(String rentalAgreement) {
		String packageXpath = "//div[contains(@class, 'title') and text()='" + rentalAgreement
				+ "']/parent::div/parent::div/button[contains(@class, 'select-package')]";
		WebElement element = driver.findElement(By.xpath(packageXpath));
		click(element);
	}

	public void selectCityOrFinishYourAgreement(String city) {
		switchToChildWindow();
		if (isElementPresent(driver, coninueAgreement)) {
			click(coninueAgreement);
		} else {
			String cityXpath = "//img[@alt='city']".replace("city", city);
			waitForElementToBeClickable(driver, By.xpath(cityXpath), 5);
			waitFor(2);
			click(By.xpath(cityXpath));
		}
	}

	public void selectHSServiceCity(String city) {
		switchToChildWindow();
		String cityXpath = "//img[@alt='city']".replace("city", city);
		waitForElementToBeClickable(driver, By.xpath(cityXpath), 10);
		click(By.xpath(cityXpath));
	}

	public void enterAddharDetails(String aadharNumber) {
		try {
			click(By.id("aadhaarAdded"));
			sendKeys(By.xpath("//input[@id='ls-documentforms-adhaarNumber-nbInput']"), aadharNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getQuoteAmount() {
		log("Printing [Quotation Amount]");
		By quoteAmtLocator = By.xpath("//div/span[@class='rupee']/following-sibling::span[@class='price']");
		WebElement quoteAmtElement = driver.findElement(quoteAmtLocator);
		String quoteAmt = quoteAmtElement.getText();
		System.out.println("Estimated Quotation Price:" + quoteAmt);
	}

	public void enterTenantDetails(Map<String, String> selectMap, Map<String, String> txtFieldMapr)
			throws InterruptedException {
		String value;
		for (String labelName : selectMap.keySet()) {
			value = selectMap.get(labelName);
			log("Opening select for [" + labelName + "]");
			waitForElementToBeClickable(driver, getFieldXpathLocator(genericSelectField, labelName), 10);
			click(getFieldXpathLocator(genericSelectField, labelName));

			waitFor(1);
			log("Selecting value [" + value + "]");
			waitForElementToBeClickable(driver, getFieldXpathLocator(nbSelectValXpath, value), 10);
			click(getFieldXpathLocator(nbSelectValXpath, value));
			waitFor(1);
		}
		for (String labelName : txtFieldMapr.keySet()) {
			value = txtFieldMapr.get(labelName);
			log("Entering [" + value + "] for label [" + labelName + "]");
			By fieldLocator = getFieldXpathLocator(genericTxtField, labelName);
			waitForElementDisplay(driver, fieldLocator, 5);
			WebElement element = driver.findElement(fieldLocator);
			element.clear();
			element.sendKeys(value);
		}

	}

	public void enterLordTenantName(String leadName, String tenantName) throws InterruptedException {
		By landlord = By.xpath("//input[contains(@placeholder,'Enter landlord name')]");
		scrollUpto(landlord);
		sendKeys(By.xpath("//input[contains(@placeholder,'Enter landlord name')]"), leadName);
		click(By.xpath("//button[normalize-space()='Add more landlord']"));
		sendKeys(By.xpath("//input[contains(@placeholder,'Enter landlord name')]"), leadName);
		sendKeys(By.xpath("//input[contains(@placeholder,'Enter tenant name')]"), tenantName);
		click(By.xpath("//button[normalize-space()='Add more tenant']"));
		sendKeys(By.xpath("//input[contains(@placeholder,'Enter tenant name')]"), tenantName);
	}

	public void reenterLordTenantNames(String leadName, String tenantName) throws InterruptedException {
		sendKeys(By.xpath("(//*[@name='fullName'])[last()]"), leadName);
		sendKeys(By.xpath("(//*[@name='fullName'])[2]"), tenantName);
	}

	public void overirdeDrafTfield() {
		click(By.id("isManualEdit"));
	}

	public void enterContractDetails(Map<String, String> selectMap, Map<String, String> txtFieldMapr)
			throws InterruptedException {
		String value;

		for (String labelName : selectMap.keySet()) {

			value = selectMap.get(labelName);
			log("Opening select for [" + labelName + "]");
			waitForElementToBeClickable(driver, getFieldXpathLocator(genericSelectField, labelName), 10);
			click(getFieldXpathLocator(genericSelectField, labelName));
			waitFor(1);

			log("Selecting value [" + value + "]");
			waitForElementToBeClickable(driver, getFieldXpathLocator(nbSelectValXpath, value), 10);
			click(getFieldXpathLocator(nbSelectValXpath, value));
			waitFor(1);
		}

		for (String labelName : txtFieldMapr.keySet()) {
			value = txtFieldMapr.get(labelName);
			log("Entering [" + value + "] for label [" + labelName + "]");
			By fieldLocator = getFieldXpathLocator(genericTxtField, labelName);
			waitForElementDisplay(driver, fieldLocator, 10);
			WebElement element = driver.findElement(fieldLocator);
			element.sendKeys(value);
		}

	}

	public void selectStampaper(String stamPaper) {

		click(Other);
		List<WebElement> stampList = driver
				.findElements(By.xpath("//div[@class='css-q4imyt nb-select__menu-list']//div"));
		boolean foundStamp = false;
		for (WebElement stamp : stampList) {
			if (stamp.getText().equals(stamPaper)) {
				try {
					stamp.click();
					break;
				} catch (StaleElementReferenceException e) {
					stamp.click();
				}
			}
		}
		if (!foundStamp) {
			click(By.xpath(" //*[text()='₹ 100 Stamp']"));
		}
	}

	public void selctRentalagreemnt() {
		scrollClick(By.xpath("//div[contains(text(),'Rental Agreement')]"));
		waitFor(3);
	}

	public void selectAgreementStartDate() throws InterruptedException {
		String agrMonth = "October 2023";
		By calendar = By.xpath("//*[text()='Select Day'] | //*[@placeholder='Select Day']");
		By currentMonthSelector = By.cssSelector("div[class*='current-month']");
		By previousBtn = By.xpath("//button[contains(@class,'navigation--previous')]");
		waitForElementToBeClickable(driver, calendar, 10);
		click(calendar);
		waitForElementToBeClickable(driver, currentMonthSelector, 10);
		WebElement currentMonth = driver.findElement(currentMonthSelector);
		while (true) {
			if (currentMonth.getText().equals(agrMonth)) {
				break;
			} else {
				click(previousBtn);
				Thread.sleep(1000);
			}
		}
		click(date);
	}

	public void selectDateMH(String agrMonth) throws InterruptedException {
		By calendar = By.xpath("//*[text()='Select Date'] | //*[@placeholder='Select Date']");
		By currentMonthSelector = By.cssSelector("div[class*='current-month']");
		By previousBtn = By.xpath("//button[contains(@class,'navigation--previous')]");
		waitForElementDisplay(driver, calendar, 10);
		click(calendar);
		waitForElementDisplay(driver, currentMonthSelector, 15);
		Thread.sleep(5000);
		WebElement currentMonth = driver.findElement(currentMonthSelector);
		while (true) {
			if (currentMonth.getText().equals(agrMonth)) {
				break;
			} else {
				click(previousBtn);
				Thread.sleep(1000);
			}
		}

		click(date);
	}

	public void enterPropertyDetailMHTxtField(Map<String, String> txtFieldMap) {
		String value;
		for (String labelName : txtFieldMap.keySet()) {
			value = txtFieldMap.get(labelName);
			log("Entering [" + value + "]  for label [" + labelName + "]");
			sendKeys(getFieldXpathLocator(genericTxtField, labelName), value);
		}
	}

	public void enterPropertyDetailMHselectField() throws InterruptedException {
		String taluka = "Baramati";
		String district = "Pune";
		String village = "Akole";
		By districtDrp = By.xpath("//div[@id='ls-documentforms-district-nbInput']");
		By talukaDrp = By.xpath("//div[contains(text(),'Enter Taluka')]");
		waitForElementToBeClickable(driver, districtDrp, 10);
		scrollUpto(districtDrp);
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(districtDrp)).click().build().perform();
		WebElement dst = driver.findElement(By.xpath("//div[contains(@id,'react-select')][text()='" + district + "']"));
		boolean isClicked = false;
		int retries = 0;
		int maxRetries = 3;
		while (!isClicked && retries < maxRetries) {
			try {
				dst.click();
				isClicked = true;
			} catch (StaleElementReferenceException e) {
				retries++;
				Thread.sleep(1000);
			}
		}
		waitForElementToBeClickable(driver, talukaDrp, 10);
		actions.moveToElement(driver.findElement(talukaDrp)).click().build().perform();
	}

	public void uploadExistingDraft() {
		waitFor(2);
		driver.findElement(By.id("uploadAgreement"))
				.sendKeys(System.getProperty("user.dir") + "/src/test/resources/UploadData/RENTAL_AGREEMENT.pdf");
	}

	public void enteringPropertyType(String propertyType) throws InterruptedException {
		log("clicking on [Property Type]");
		click(By.xpath("//div[contains(text(),'Select Property')]"));
		log("Selecting value from Dropdown [" + propertyType + "]");

		boolean found = false;
		int attempts = 0;
		while (!found && attempts < 3) {
			try {
				List<WebElement> propertyList = driver
						.findElements(By.xpath("//div[@class='css-11unzgr nb-select__menu-list']//div"));
				for (WebElement source : propertyList) {
					if (source.getText().equals(propertyType)) {
						source.click();
						found = true;
						break;
					}
				}
			} catch (StaleElementReferenceException e) {
				attempts++;
				wait(5);
			}
		}
	}

	public void genrateEstimate(Map<String, String> selectMap, Map<String, String> txtFieldMapr)
			throws InterruptedException {
		String value;

		for (String labelName : selectMap.keySet()) {
			value = selectMap.get(labelName);
			log("Opening select for [" + labelName + "]");
			waitForElementToBeClickable(driver, getFieldXpathLocator(genericSelectField, labelName), 10);
			click(getFieldXpathLocator(genericSelectField, labelName));
			waitFor(3);

			log("Selecting value [" + value + "]");
			waitForElementToBeClickable(driver, getFieldXpathLocator(nbSelectValXpath, value), 10);
			click(getFieldXpathLocator(nbSelectValXpath, value));
			waitFor(2);
		}

		for (String labelName : txtFieldMapr.keySet()) {
			value = txtFieldMapr.get(labelName);
			By loc = getFieldXpathLocator(genericTxtField, labelName);
			log("Entering [" + value + "] for label [" + labelName + "]");
			waitForElementToBeClickable(driver, getFieldXpathLocator(genericTxtField, labelName), 10);
			WebElement element = driver.findElement(loc);
			element.sendKeys(value);
		}
	}

}
