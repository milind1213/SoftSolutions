package com.sos.testCases;

//import static io.restassured.RestAssured.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sos.Pages.RestPages.NonMHRentalService;
import com.sos.Utilities.TestUtils;

import io.restassured.response.Response;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerFlow extends TestUtils {
	NonMHRentalService customer = new NonMHRentalService();
	Response res;
	String authId = "8882d7b88a0c061ada3d786c5d1c6c1e";
	String leadId, packageId, agreementId, quotationId, paymentLink, packageKey = "RA";

	@Test(priority = 1)
	public void runTest() {
		String leadName = "Milind G", leadPhone = "7454759859", leadEmail = "milind12@gmail.com", city = "Bangalore";
		String tenantName = "Prasad Kumar", tenantPhone = "9363634321", tenantEmail = "tenant@g.com";
		String lanlordName = "Akash", landlordphone = "7656765675", landlordEmail = "landlord@g.com";
		String refundableDeposit = "100000", amount = "120000",noticePeriod = "2",licencePeriod = "12",lockingPeriod = "3" ;

		log("Creating lead With Post Request ");
		res = customer.addLead(leadName, leadPhone, leadEmail, authId);
		String response = res.jsonPath().getString("data");
		String msg = res.jsonPath().getString("msg");
		Pattern pattern = Pattern.compile("\\[([a-fA-F0-9]+):");
		Matcher matcher = pattern.matcher(response);
		if (matcher.find()) {
			leadId = matcher.group(1);
			System.out.println("Lead ID :" + leadId);
		}
		Assert.assertEquals(res.statusCode(), 200);
		Assert.assertEquals(msg, "Lead added successfully");

		log(" Updating Package Details Using POST request ");
		res = customer.updatePackage(leadName, leadPhone, city, leadEmail, leadId, packageKey, authId);
		String agreementid = res.jsonPath().getString("data." + leadId + ".agreementId");
		packageId = res.jsonPath().getString("data." + leadId + ".servicePackages[0].id");
		agreementId = agreementid;
		System.out.println("AgreementId :" + agreementId);
		System.out.println("PackageId :" + packageId);
		Assert.assertEquals(res.statusCode(), 200);
		Assert.assertEquals(res.jsonPath().getString("msg"), "Lead added successfully");

		log(" Genrating Quotation using POST request ");
		res = customer.genrateQuotation(agreementId, authId);
		quotationId = res.jsonPath().getString("data.id");
		System.out.println("Quotation Id :" + quotationId);
		Assert.assertEquals(res.statusCode(), 200);
		Assert.assertEquals(res.jsonPath().getString("msg"), "Quotation generated successfully");

		log("Updating Property Details using PUT request");
		res = customer.updatePropertyDetails(agreementId, authId);
		String locality = res.jsonPath().getString("data.property.address.locality");
		System.out.print("Updated Locality :" + locality);
		Assert.assertEquals(locality, "KORMANGLA");
		Assert.assertEquals(res.statusCode(), 200);
		Assert.assertEquals(res.jsonPath().getString("msg"), "Document details updated successfully");

		log("Updating Tenanat Details using PUT request");
		res = customer.updateTenantDetails(tenantName, tenantPhone, tenantEmail, agreementId, authId);
		Assert.assertEquals(res.statusCode(), 200);
		Assert.assertEquals(res.jsonPath().getString("msg"), "Document details updated successfully");

		log("Updating Lanlord Details using PUT request");
		res = customer.updateownersDetails(lanlordName, landlordphone, landlordEmail, agreementId, authId);
		Assert.assertEquals(res.statusCode(), 200);
		Assert.assertEquals(res.jsonPath().getString("msg"), "Document details updated successfully");

		log("Updating Term Details using PUT request");
		res = customer.updatetermDetails(licencePeriod, refundableDeposit, lockingPeriod, amount, noticePeriod,
				agreementId, authId);
		Assert.assertEquals(res.statusCode(), 200);
		Assert.assertEquals(res.jsonPath().getString("msg"), "Document details updated successfully");

		log("Updating Delivery Details using PUT request ");
		res = customer.addDeliveryDetails(lanlordName, landlordphone, landlordEmail, agreementId, authId);
		Assert.assertEquals(res.statusCode(), 200);
		Assert.assertEquals(res.jsonPath().getString("msg"), "Document details updated successfully");

		log("Genrating Payment link using POST request");
		res = customer.genratePaymentLink(quotationId, packageId, authId);
		paymentLink = res.jsonPath().getString("data.paymentLink");
		System.out.println("Payment Link :" + paymentLink);
		Assert.assertEquals(res.statusCode(), 200);

		log("Making Payment using POST request");
		res = customer.makePayment(leadId, quotationId, authId);
		Assert.assertEquals(res.statusCode(), 200);
	}

	@Test(priority = 2)
	public void getCitiesList() {

		res = customer.getCitiesList(authId);
		String msg = res.jsonPath().getString("msg");
		List<String> nonMhCities = res.jsonPath().getList("data");
		System.out.println("Total Number Serviceble Non-MH Cities :" + nonMhCities.size());
		for (int i = 0; i < nonMhCities.size(); i++) {
			if (nonMhCities.get(i).equalsIgnoreCase("Bangalore")) {
				System.out.println("Banglore :" + nonMhCities.get(i));
			}
		}
		Assert.assertEquals(res.statusCode(), 200);
		Assert.assertEquals(msg, "success");
	}

}
