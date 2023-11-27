Feature: Verify Upload your Draft Upload Functionality

  Background:
    Given User Landed on HomePage 
    When User Enters valid PhoneNumber, Name, and email
    And User clicks on login continue button 

  Scenario: New User SignUp Verification
    Then validate the outcome loggedIn or SignedUp "Successfully"

  Scenario: Verify Upload your Draft Functionality without Addons
    Given User is on the Legal Services Page
    When User selects a city from the available options
    And User clicks on the Check Price button 
    And User selects the "Upload your Draft" package
    And User uploads the Old or Existing Rent Agreement draft
    And User selects Stamp Paper option
    And User selects Rent Agreement Start Date
    And User enters LandlordName and TenantName
    And User selects Notarized Agreement Addon, eSign Addon, delivery Addon, and clicks on Save and Continue button
    Then Verify the Title of the selected "Upload your Draft" Package is displayed on the Summary Page
    Then validate the Quotation Price Details

  Scenario Outline: Verify Add Delivery Address Functionality in Upload your draft flow
    Given User is on the Legal Services Page
    When User selects a city from the available options
    And User clicks on the Check Price button 
    And User selects the "Upload your Draft" package
    And User uploads the Old or Existing Rent Agreement draft
    And User selects Stamp Paper option
    And User selects Rent Agreement Start Date
    And User enters LandlordName and TenantName
    And User selects Notarized Agreement Addon, eSign Addon, delivery Addon, and clicks on Save and Continue button
    And User clicks on Add New button in Summary
    And User submits delivery details name, email, phone "<address>" and "<pincode>"
    Then Validate the checked icon and delivery Receiver name in summary
    Examples:
      | address                        | pincode |
      | A 404 Bren Mercury,Bangalore   | 560035  |
      
 
  Scenario Outline: Verify Add Add Extra Copy Functionaliy in Upload your draft flow
    Given User is on the Legal Services Page
    When User selects a city from the available options
    And User clicks on the Check Price button 
    And User selects the "Upload your Draft" package
    And User uploads the Old or Existing Rent Agreement draft
    And User selects Stamp Paper option
    And User selects Rent Agreement Start Date
    And User enters LandlordName and TenantName
    And User selects Notarized Agreement Addon, eSign Addon, delivery Addon, and clicks on Save and Continue button
    And User clicks on Add New button in Summary
    And User submits delivery details name, email, phone "<address>" and "<pincode>"
    And User clicks on Number of Extracopy "<extraCopy>" button.
    Then validate the Quotation Price Details After adding extracopy
    Examples:
      | address                        | pincode |extraCopy | 
      | A 404 Bren Mercury,Bangalore   | 560035  |   2      |
      
     
    Scenario: MakePayment functionaliy
    Given User is on the Legal Services Page
    When User selects a city from the available options
    And User clicks on the Check Price button 
    And User selects the "Upload your Draft" package
    And User uploads the Old or Existing Rent Agreement draft
    And User selects Stamp Paper option
    And User selects Rent Agreement Start Date
    And User enters LandlordName and TenantName
    And User selects Notarized Agreement Addon, eSign Addon, delivery Addon, and clicks on Save and Continue button
    And User clicks on Add New button in Summary
    And User submits delivery details name, email, phone "<address>" and "<pincode>"
    And User clicks on Number of Extracopy "<extraCopy>" button.
    Then validate the Quotation Price Details After adding extracopy
    And User clicking on  PayButton 
    And User entered "<cardNumber>" "<expiryDate>" "<cvv>"  "<name>" and clicks on Make Payment Btn
    And User Enter "<otp>" and click on SubmitButton 
    Then Validating the Order Confirmation in  MyBookings 
    
    Examples:
     | address                     | pincode |extraCopy |cardNumber         |expiryDate|cvv  |name   |otp |
     | A 404 Bren Mercury,Bangalore| 560035  |   2      |4111-1111-1111-1111|0244      |121  |Milind |1234|
