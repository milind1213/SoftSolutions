package com.sos.Pages.RestPages;

import java.io.File;
import java.util.Map;

import com.sos.CommonFactory.CommonREST;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

public class NonMHRentalService  extends CommonREST{
	 RequestSpecification spec;
     RequestSpecBuilder   builder;
     public static String BASE_URL = "https://star-preprod.homeservice-react.star.nobroker.in/";
     static final String  basePath = "blackbox-hsbackend/";
     // APIs
     public static String addlead = "api/v1/legal-documents/leads";
     public static String Package = "api/v1/legal-documents/package";
     public static String propertyDetails = "api/v1/legal-documents/document-details/PROPERTY_DETAIL";
     public static String tenanatDetail = "api/v1/legal-documents/document-details/TENANT_DETAIL";
     public static String ownerDetail = "api/v1/legal-documents/document-details/LANDLORD_DETAIL";
     public static String termDetail = "api/v1/legal-documents/document-details/TERM_DETAIL";
     public static String deliveryDetails = "api/v1/legal-documents/document-details/SUMMARY_DETAILS_UPDATE";
     public static String quotation = "api/v1/legal-documents/quotations";
     public static String listPackage = "api/v1/legal-documents/packageInfo/list";
     public static String makePayment = "api/v1/legal-documents/payment-link";
     public static String cities = "api/v1/legal-documents/cities";
     public static String uploadDraft ="api/v1/legal-documents/uploadDraft";
     public String completePayment = "api/test/test/status";
	
     
     public Response uploadYourDraft(String authId, String leadId) {
         builder = new RequestSpecBuilder();
         builder.setBaseUri(BASE_URL)
                 .setBasePath(basePath)
                 .addHeader("Accept", "*/*")
                 .addHeader("homeservices-auth-id",authId)
                 .setContentType("multipart/form-data;");
         spec = builder.build();
         return given(spec)
                 .multiPart("lead_id",leadId)
                 .multiPart("doc",new File("/home/milind/Desktop/NobrokerAutomation/resource/HS/Upload/RENTAL_AGREEMENT.pdf"))
                 .when().post(uploadDraft);
     }
     
     public Response getPackageList( String authId,String city ) {
         builder = new RequestSpecBuilder();
         builder.setBaseUri(BASE_URL)
                 .setBasePath(basePath)
                 .addHeader("Accept","application/json")
                 .addQueryParam("serviceType","RENTAL_AGREEMENT")
                 .addQueryParam("city",city)
                 .addHeader("homeservices-auth-id", authId);
         spec = builder.build();
         return get(spec, listPackage);
     }

     public Response getCitiesList( String authId ) {
         builder = new RequestSpecBuilder();
         builder.setBaseUri(BASE_URL)
                 .setBasePath(basePath)
                 .addHeader("Accept","application/json")
                 .addQueryParam("serviceType","RENTAL_AGREEMENT")
                 .addHeader("homeservices-auth-id", authId);
         spec = builder.build();
         return get(spec, cities);
     }

     public Response addLead(String leadName, String leadPhone, String leadEmail, String authId) {
         String payload = "{\n" +
                 "    \"userName\": \" " + leadName + " \",\n" +
                 "    \"userPhone\": \" " + leadPhone + "\",\n" +
                 "    \"serviceTypes\": [\n" +
                 "    \"RENTAL_AGREEMENT\"\n" +
                 "    ],\n" +
                 "    \"city\": \"BANGALORE\",\n" +
                 "    \"userEmail\": \" " + leadEmail + " \",\n" +
                 "    \"phoneCountryCode\": \"91\",\n" +
                 "    \"userType\": \"OWNER\",\n" +
                 "    \"source\": \"DETAILS_PAGE_FEEDBACK\"\n" +
                 "}";
         builder = new RequestSpecBuilder();
         builder.setBaseUri(BASE_URL)
                 .setBasePath(basePath)
                 .setContentType("application/json;charset=UTF-8")
                 .addHeader("homeservices-auth-id", authId)
                 .setBody(payload);
         spec = builder.build();
         return postNew(spec, addlead);
     }

     public Response updatePackage(String leadName, String leadPhone, String city, String leadEmail, String leadId,String packageKey, String authId) {
         String payload = "{\n" +
                 "    \"userName\": \"" + leadName + "\",\n" +
                 "    \"userPhone\": \"+91" + leadPhone + "\",\n" +
                 "    \"city\": \"" + city + "\",\n" +
                 "    \"userEmail\": \"" + leadEmail + "\",\n" +
                 "    \"phoneCountryCode\": \"91\",\n" +
                 "    \"source\": \"WEBSITE\",\n" +
                 "    \"packageKey\": \""+packageKey+"\",\n" +
                 "    \"leadIdMap\": {\n" +
                 "        \"RENTAL_AGREEMENT\": \"" + leadId + "\"\n" +
                 "    }\n" +
                 "}";
         builder = new RequestSpecBuilder();
         builder.setBaseUri(BASE_URL)
                 .setBasePath(basePath)
                 .setContentType("application/json;charset=UTF-8")
                 .addHeader("homeservices-auth-id", authId)
                 .setBody(payload);
         spec = builder.build();
         return postNew(spec, Package);
     }

     public Response genrateQuotation(String agreementId, String authId) {
         String payload = "{\n" +
                 "    \""+agreementId+"\": {\n" +
                 "        \"stampDuty\": \"500\",\n" +
                 "        \"additionalDiscount\": 0,\n" +
                 "        \"packageId\": null,\n" +
                 "        \"packageKey\": null,\n" +
                 "        \"addAssistantCharge\": false,\n" +
                 "        \"doc\": {\n" +
                 "            \"term\": {\n" +
                 "                \"leaseDuration\": {\n" +
                 "                    \"licencePeriod\": null,\n" +
                 "                    \"startDate\": \"2023-06-07T00:00:00+05:30\"\n" +
                 "                },\n" +
                 "                \"refundableDeposit\": null,\n" +
                 "                \"rentType\": \"FIXED\",\n" +
                 "                \"rentAmountList\": []\n" +
                 "            }\n" +
                 "        },\n" +
                 "        \"addonMap\": {\n" +
                 "            \"ESIGN\": {\n" +
                 "                \"catalogId\": \"62c6dddee771577b5113730e\",\n" +
                 "                \"catalogKey\": \"ESIGN\",\n" +
                 "                \"catalogTitle\": \"E-Sign Agreement\",\n" +
                 "                \"type\": \"ADONS\",\n" +
                 "                \"quantity\": 1\n" +
                 "            },\n" +
                 "            \"NOTARY\": {\n" +
                 "                \"catalogId\": \"5f3a9def6ed0974167ab1667\",\n" +
                 "                \"catalogKey\": \"NOTARY\",\n" +
                 "                \"catalogTitle\": \"Notarised Agreement\",\n" +
                 "                \"type\": \"BOOLEAN\"\n" +
                 "            },\n" +
                 "            \"NEXT_DAY_DELIVERY\": {\n" +
                 "                \"catalogId\": \"6389be879d1a9cc5d788f8d6\",\n" +
                 "                \"catalogKey\": \"NEXT_DAY_DELIVERY\",\n" +
                 "                \"catalogTitle\": \"Next Day Delivery\",\n" +
                 "                \"type\": \"BOOLEAN\"\n" +
                 "            }\n" +
                 "        }\n" +
                 "    }\n" +
                 "}";
         builder = new RequestSpecBuilder();
         builder.setBaseUri(BASE_URL)
                 .setBasePath(basePath)
                 .setContentType("application/json;charset=UTF-8")
                 .addHeader("homeservices-auth-id", authId)
                 .setBody(payload);
         spec = builder.build();
         return postNew(spec, quotation);
     }

    public Response updatePropertyDetails(String agreementId, String authId) {
         String PropertyDetails = "{\n" +
                 "    \"property\": {\n" +
                 "        \"address\": {\n" +
                 "            \"latitude\": null,\n" +
                 "            \"longitude\": null,\n" +
                 "            \"bhkType\": \"BHK1\",\n" +
                 "            \"buildingName\": \"DARSHAN HEIGHTS\",\n" +
                 "            \"flatNumber\": \"WERWWR  \",\n" +
                 "            \"floorNumber\": 23,\n" +
                 "            \"street\": \"TEW RWR\",\n" +
                 "            \"locality\": \"KORMANGLA\",\n" +
                 "            \"pincode\": 560035,\n" +
                 "            \"city\": \"Bnaglore\",\n" +
                 "            \"district\": \"Bangalore\",\n" +
                 "            \"taluka\": null,\n" +
                 "            \"village\": null,\n" +
                 "            \"state\": null\n" +
                 "        },\n" +
                 "        \"propertyType\": \"OFFICE\",\n" +
                 "        \"noOfBathroom\": 1\n" +
                 "    },\n" +
                 "    \"id\": \"" + agreementId + "\"\n" +
                 "}";
         builder = new RequestSpecBuilder();
         builder.setBaseUri(BASE_URL)
                 .setBasePath(basePath)
                 .setContentType("application/json;charset=UTF-8")
                 .addHeader("homeservices-auth-id", authId)
                 .setBody(PropertyDetails);
         spec = builder.build();
         return put(spec, propertyDetails);
     }
     public Response updateTenantDetails(String tenantName, String tenantPhone, String tenantEmail, String agreementId, String authId) {
         String tenantDetails = "{\n" +
                 "         \"tenants\": [\n" +
                 "          {\n" +
                 "            \"personType\": \"TENANT\",\n" +
                 "            \"fullName\": \"" + tenantName + "\",\n" +
                 "            \"age\": 22,\n" +
                 "            \"gender\": \"MALE\",\n" +
                 "            \"phone\": " + tenantPhone + ",\n" +
                 "            \"address\": {\n" +
                 "                \"latitude\": null,\n" +
                 "                \"longitude\": null,\n" +
                 "                \"bhkType\": null,\n" +
                 "                \"buildingName\": null,\n" +
                 "                \"flatNumber\": null,\n" +
                 "                \"floorNumber\": null,\n" +
                 "                \"street\": \"cccccc\",\n" +
                 "                \"locality\": null,\n" +
                 "                \"pincode\": 555555,\n" +
                 "                \"city\": null,\n" +
                 "                \"district\": null,\n" +
                 "                \"taluka\": null,\n" +
                 "                \"village\": null,\n" +
                 "                \"state\": null\n" +
                 "               },\n" +
                 "            \"email\": \"" + tenantEmail + "\"\n" +
                 "        }\n" +
                 "    ],\n" +
                 "    \"id\": \"" + agreementId + "\"\n" +
                 "}";
         builder = new RequestSpecBuilder();
         builder.setBaseUri(BASE_URL)
                 .setBasePath(basePath)
                 .setContentType("application/json;charset=UTF-8")
                 .addHeader("homeservices-auth-id", authId)
                 .setBody(tenantDetails);
         spec = builder.build();
         return put(spec, tenanatDetail);
     }
     public Response updateownersDetails(String name, String phone, String email, String agreementId, String authId) {
         String ownersDetails = "{\n" +
                 "    \"landlords\": [\n" +
                 "        {\n" +
                 "            \"personType\": \"OWNER\",\n" +
                 "            \"firstName\": \"Name\",\n" +
                 "            \"fullName\": \"" + name + " \",\n" +
                 "            \"age\": 23,\n" +
                 "            \"gender\": \"MALE\",\n" +
                 "            \"adhaarNumber\": 124151413414,\n" +
                 "            \"panNumber\": \"ABCDE1234F\",\n" +
                 "            \"phone\": \"91" + phone + "\",\n" +
                 "            \"email\": \"" + email + "\",\n" +
                 "            \"address\": {\n" +
                 "                \"latitude\": null,\n" +
                 "                \"longitude\": null,\n" +
                 "                \"bhkType\": null,\n" +
                 "                \"buildingName\": null,\n" +
                 "                \"flatNumber\": null,\n" +
                 "                \"floorNumber\": null,\n" +
                 "                \"street\": \"asdaafas\",\n" +
                 "                \"locality\": null,\n" +
                 "                \"pincode\": 560035,\n" +
                 "                \"district\": null,\n" +
                 "                \"taluka\": null,\n" +
                 "                \"village\": null,\n" +
                 "                \"state\": null,\n" +
                 "                \"city\": null\n" +
                 "            }\n" +
                 "        }\n" +
                 "    ],\n" +
                 "    \"id\": \"" + agreementId + "\"\n" +
                 "}";
         builder = new RequestSpecBuilder();
         builder.setBaseUri(BASE_URL)
                 .setBasePath(basePath)
                 .setContentType("application/json;charset=UTF-8")
                 .addHeader("homeservices-auth-id", authId)
                 .setBody(ownersDetails);
         spec = builder.build();
         return put(spec, ownerDetail);
     }
     public Response updatetermDetails(String licencePeriod, String refundableDeposit, String lockinPeriod, String amount, String noticePeriod, String agreementId, String authId) {
         String ownersDetails = " {\n" +
                 "        \"term\": {\n" +
                 "        \"leaseDuration\": {\n" +
                 "            \"licencePeriod\": " + licencePeriod + ",\n" +
                 "                    \"startDate\": \"2023-06-07T00:00:00+05:30\",\n" +
                 "                    \"lockinPeriod\": " + lockinPeriod + ",\n" +
                 "                    \"endDate\": \"2024-05-07T00:00:00+05:30\"\n" +
                 "        },\n" +
                 "        \"rentType\": \"VARIABLE_PERCENTAGE\",\n" +
                 "                \"incrementPercentage\": 10,\n" +
                 "                \"refundableDeposit\": " + refundableDeposit + ",\n" +
                 "                \"rentAmountList\": [\n" +
                 "        {\n" +
                 "            \"fromMonth\": 1,\n" +
                 "                \"toMonth\": 11,\n" +
                 "                \"amount\": " + amount + "\n" +
                 "        }\n" +
                 "        ],\n" +
                 "        \"depositPaymentDetails\": [\n" +
                 "        {\n" +
                 "            \"paymentMode\": \"CASH\",\n" +
                 "                \"bankName\": null,\n" +
                 "                \"branchName\": null,\n" +
                 "                \"referenceNumber\": null,\n" +
                 "                \"date\": null,\n" +
                 "                \"amount\": 10000\n" +
                 "        }\n" +
                 "        ],\n" +
                 "        \"rentDay\": 1,\n" +
                 "                \"noticePeriod\": " + noticePeriod + ",\n" +
                 "                \"amenity\": {\n" +
                 "            \"bed\": 1,\n" +
                 "                    \"fan\": 1\n" +
                 "        },\n" +
                 "        \"additionalAmenities\": {}\n" +
                 "    },\n" +
                 "        \"id\": \"" + agreementId + "\"\n" +
                 "    }";
         builder = new RequestSpecBuilder();
         builder.setBaseUri(BASE_URL)
                 .setBasePath(basePath)
                 .setContentType("application/json;charset=UTF-8")
                 .addHeader("homeservices-auth-id", authId)
                 .setBody(ownersDetails);
         spec = builder.build();
         return put(spec, termDetail);
     }
     public Response addDeliveryDetails(String leadName, String leadPhone, String leadEmail, String agreementId, String authId) {
         String payload = "{\n" +
                 "    \"deliveryDetail\": {\n" +
                 "        \"deliveryAddress\": {\n" +
                 "            \"street\": \"SDFSDFSD\",\n" +
                 "            \"pincode\": 244242,\n" +
                 "            \"customAddress\": true\n" +
                 "        },\n" +
                 "        \"contactPerson\": {\n" +
                 "            \"fullName\": \"" + leadName + "\",\n" +
                 "            \"phone\": \"" + leadPhone + "\",\n" +
                 "            \"email\": \"" + leadEmail + "\"\n" +
                 "        }\n" +
                 "    },\n" +
                 "    \"id\": \"" + agreementId + "\"\n" +
                 "}";
         builder = new RequestSpecBuilder();
         builder.setBaseUri(BASE_URL)
                 .setBasePath(basePath)
                 .setContentType("application/json;charset=UTF-8")
                 .addHeader("homeservices-auth-id", authId)
                 .setBody(payload);
         spec = builder.build();
         return put(spec, deliveryDetails);
     }

     public Response genratePaymentLink(String quotationId, String packageId, String authId) {
         String payload = "{\n" +
                 "    \"quotationId\": \"" + quotationId + "\",\n" +
                 "    \"packageId\": \"" + packageId + "\"\n" +
                 "}\n";
         builder = new RequestSpecBuilder();
         builder.setBaseUri(BASE_URL)
                 .setBasePath(basePath)
                 .setContentType("application/json;charset=UTF-8")
                 .addHeader("homeservices-auth-id", authId)
                 .setBody(payload);
         spec = builder.build();
         return postNew(spec, makePayment);
     }

     public Response makePayment(String leadId, String quotationID, String authId) {
         String payload = "{\n" +
                 "    \"leadId\": \"" + leadId + "\",\n" +
                 "    \"quotationId\": \"" + quotationID + "\",\n" +
                 "    \"leadType\": \"HS_LEGALDOCUMENT_LEAD\",\n" +
                 "    \"pseq\": \"TOKEN\",\n" +
                 "    \"pg\": \"PAYTM\",\n" +
                 "    \"status\": \"SUCCESS\",\n" +
                 "    \"amount\": \"885\"\n" +
                 "}";
         builder = new RequestSpecBuilder();
         builder.setBaseUri(BASE_URL)
                 .setBasePath(basePath)
                 .setContentType("application/json;charset=UTF-8")
                 .addHeader("homeservices-auth-id", authId)
                 .setBody(payload);
         spec = builder.build();
         return postNew(spec, makePayment);
     }
     public Response completePayment(String leadId, String quotationId, String pseq, Map headers)
     {
         String body = "{\n" +
                 "    \"leadId\": \"" + leadId + "\",\n" +
                 "    \"quotationId\":\"" + quotationId + "\",\n" +
                 "    \"leadType\": \"HOME_STORE_LEAD\",\n" +
                 "    \"pseq\": \"" + pseq + "\",\n" +
                 "    \"pg\": \"PAYTM\",\n" +
                 "    \"status\": \"SUCCESS\",\n" +
                 "    \"amount\": \"979\"\n" +
                 "}";

         builder = new RequestSpecBuilder();
         builder.setBaseUri(BASE_URL)
                 .setBasePath(basePath)
                 .addHeaders(headers)
                 .setBody(body);
         spec = builder.build();
         return postNew(spec, completePayment);
     }
     
     
     
}