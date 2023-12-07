Feature: Verify the flow for Non-MH Rental Agreements

  Background:
    Given User is on the HomePage 
    When User enters a valid Login Credentials
    Then the user is logged in or signed up "Successfully"
    
  Scenario Outline: Validate the functionality for generating a rental agreement estimate without any addons 
    Given User Landed on the Legal Services HomePage
    When User Selects a "<city>" from the available options
    And User click on the Check Price button 
    And User Selects the "<packageTitle>" package
    And Selects Stamp Paper "<stamPaper>" option from the dropdowns
    And Selecting Rent Agreement Start Date
    And clicking on the summaryContent and redirects on the SummaryPage
    Then validate the title "E-Stamped Agreement" and Price details in SummaryPage
   Examples:
   | city      | packageTitle        | stamPaper    |
   | Bangalore | E-Stamped Agreement | ₹ 100 Stamp  |
   
   
  Scenario Outline: Validate the functionality for generating a rental agreement estimate with addons 
    Given User Landed on the Legal Services HomePage
    When User Selects a "<city>" from the available options
    And User click on the Check Price button 
    And User Selects the "<packageTitle>" package
    And Selects Stamp Paper "<stamPaper>" option from the dropdowns
    And Selecting Rent Agreement Start Date
    And User selects Notarized Agreement, eSign, delivery Addons and clicks on Save and Continue button
    And clicking on the summaryContent and redirects on the SummaryPage
    Then validating the title "E-Stamped Agreement" and Price details in SummaryPage
   Examples:
   | city      | packageTitle        | stamPaper    |
   | Bangalore | E-Stamped Agreement | ₹ 100 Stamp  |
   
   
  Scenario Outline: Verify the property Details Edit Functionality from Summary
    Given User Landed on the Legal Services HomePage
    When User Selects a "<city>" from the available options
    And User click on the Check Price button 
    And User Selects the "<packageTitle>" package
    And Selects Stamp Paper "<stamPaper>" option from the dropdowns
    And Selecting Rent Agreement Start Date
    And User selects Notarized Agreement, eSign, delivery Addons and clicks on Save and Continue button
    And clicking on the summaryContent and redirects on the SummaryPage
    And clicking on Edit "<editTitle>" 
    And User Enters Property Details:
    | Floor Number    | 10    |
    | BHK Type        | 4 BHK |
    | Type of Property| Shop  |
    | Shop Number     | 301   |
    | Building Name   | ABC   |
    | Locality        | HSR   |
    | Pincode         | 560035|
    And Clicking on Add Btn for adding Number of Bathrooms <NumberOfBathrooms> 
    Then validating the Property Details in Summary
  Examples:
  | city       | packageTitle        | stampPaper   | editTitle      | NumberOfBathrooms |
  | Bangalore  | E-Stamped Agreement | ₹ 100 Stamp  | Property Detail|      3            |
   
   
  Scenario Outline: Verify the Landlord Detail Edit Functionality from Summary
    Given User Landed on the Legal Services HomePage
    When User Selects a "<city>" from the available options
    And User click on the Check Price button 
    And User Selects the "<packageTitle>" package
    And Selects Stamp Paper "<stamPaper>" option from the dropdowns
    And Selecting Rent Agreement Start Date
    And User selects Notarized Agreement,eSign,delivery Addons and clicks on Save and Continue button
    And clicking on the summaryContent and redirects on the SummaryPage
    And clicking on Edit "<editTitle>"
    And User Enters Landlord Details:
    | Full Name               | Milind G      |
    | Age                     | 30            |
    | Phone                   | 9503333700    |
    | Permanent Address Full  | A-213,Banglore|
    | PAN Number (ABCDE1234F) | ABCDE4321F    |
    | Email Address           | mg@gmail.com  |
    | PIN Code                | 560035        |
    | Party Type              | Owner         |
    | Gender                  | Male          |
    
    Then validating the Landlord Details in Summary
  Examples:
  | city       | packageTitle        | stampPaper   | editTitle      | 
  | Bangalore  | E-Stamped Agreement | ₹ 100 Stamp  | Landlord Detail|
  
  
  
  Scenario Outline: Verify the Tenant Detail Edit Functionality from Summary
    Given User Landed on the Legal Services HomePage
    When User Selects a "<city>" from the available options
    And User click on the Check Price button 
    And User Selects the "<packageTitle>" package
    And Selects Stamp Paper "<stamPaper>" option from the dropdowns
    And Selecting Rent Agreement Start Date
    And User selects Notarized Agreement,eSign,delivery Addons and clicks on Save and Continue button
    And clicking on the summaryContent and redirects on the SummaryPage
    And clicking on Edit "<editTitle>"
    And User Enters Tenant Details:
    | Full Name               | Sandhya R     |
    | Age                     | 25            |
    | Phone                   | 9503333701    |
    | Permanent Address Full  | B-111,Banglore|
    | PAN Number (ABCDE1234F) | ABCDE4321G    |
    | Email Address           | gm@gmail.com  |
    | PIN Code                | 560035        |
    | Party Type              | Tenant        |
    | Gender                  | Female        |
    
   Then validating the Tenant Details in Summary
  Examples:
    | city       | packageTitle        | stampPaper   | editTitle      | 
    | Bangalore  | E-Stamped Agreement | ₹ 100 Stamp  | Tenant Detail  |
  
  Scenario Outline: Verify the Contract Detail Edit Functionality from Summary
    Given User Landed on the Legal Services HomePage
    When User Selects a "<city>" from the available options
    And User click on the Check Price button 
    And User Selects the "<packageTitle>" package
    And Selects Stamp Paper "<stamPaper>" option from the dropdowns
    And Selecting Rent Agreement Start Date
    And User selects Notarized Agreement,eSign,delivery Addons and clicks on Save and Continue button
    And clicking on the summaryContent and redirects on the SummaryPage
    And clicking on Edit "<editTitle>"
    And User Enters Contract Details:
    | Agreement Duration (In months)  | 2 Months   | 
	  | Rent Amount                     | 200000     |
	  | Refundable Deposit              | 1200000    |
	  | Minimum Lockin Period           | 3          |
	  And User clicking on Add New button in SummaryPage
	  And User submiting delivery details "<address>" and "<pincode>"
    And User clicking on Number of Extracopy "<NoCopy>" button.
	  Then Validate the Quotation Pricing Details After adding extracopy
	  And User clicking on  PayNow button
	  And User entering Payment Details "<cardNumber>" "<expiryDate>" "<cvv>"  "<name>" and clicks on Make Payment Btn
	  And User Entering "<otp>" and click on SubmitButton
	  Then Validating the Order Confirmation status in MyBooking
	  
  Examples:
   | city      |packageTitle       | stampPaper  |editTitle      |address                     |pincode|NoCopy| cardNumber        | expiryDate| cvv | name   | otp |
   | Bangalore |E-Stamped Agreement| ₹ 100 Stamp |Contract Detail|A 404 Bren Mercury,Bangalore|560035 |  2   |4111-1111-1111-1111| 02/44     | 121 | Milind | 1234|                                               
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
