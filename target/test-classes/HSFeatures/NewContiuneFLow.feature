@sanity
Feature: Verify the Non-MH Rental Agreements flow

Background:
  Given User landed on the on the NoBroker Login HomePage
  When User login  with valid Login Credentials

Scenario Outline: Generate Estimate
  Given User Navigates to the Legal Services Page
  When User Clicks on the Check Price button and Selects the "<package>" Package
  And User Selects Stamp "<stampPaper>", Agreement Duration "<agrMonth>", and Notarised Agreement, E-Sign <esignNum> and Delivery addons
  And Clicks on Save and Continue button
  And Lands on Property Detail Page and Enters Property Details:
    | Floor Number                | 10              |
    | BHK Type                    | 4 BHK           |
    | Type of Property            | Shop            |
    | Shop Number                 | 301             |
    | Building Name               | ABC             |
    | Locality                    | HSR             |
    | Pincode                     | 560035          |
  And Click and Add Number of <2> Bathrooms
  And Clicks on Save and Continue button
  Then Lands on Landlord Detail Page and Enters Landlord Details:
    | Full Name                   | Milind G        |
    | Age                         | 30              |
    | Phone                       | 9503333700      |
    | Permanent Address Full      | A-213, Bangalore|
    | PAN Number (ABCDE1234F)     | ABCDE4321F      |
    | Email Address               | mg@gmail.com    |
    | PIN Code                    | 560035          |
    | Party Type                  | Owner           |
    | Gender                      | Male            |
  And Clicks on Save and Continue button
  Then Lands on Tenant Detail Page and Enters Tenant Details:
    | Full Name                   | Sandhya R       |
    | Age                         | 25              |
    | Phone                       | 9503333701      |
    | Permanent Address Full      | B-111, Bangalore|
    | PAN Number (ABCDE1234F)     | ABCDE4321G      |
    | Email Address               | gm@gmail.com    |
    | PIN Code                    | 560035          |
    | Party Type                  | Tenant          |
    | Gender                      | Female          |
  And Clicks on Save and Continue button
  Then Lands on Contract Details Page and Enters Contract Details:
    | Agreement Duration (In months)| 2 Months      |
    | Rent Amount                  | 200000         |
    | Refundable Deposit           | 1200000        |
    | Minimum Lockin Period        | 3              |
  And User Enters Deposit Payment Details,
  And User Selects Rent Day, Notice Period, and Amenities
  And Clicks on Save and Continue button and Navigate to SummaryPage 
  And Clicking on Add New button and submitting delivery Address "<address>" and "<pincode>"
  And Clicking on Number of Extra copy "<NoCopy>" button.
  Then Validate Agreement and Quotation Pricing Details in Summary Page
  
Examples:
  | package             | stampPaper | agrMonth    | esignNum | address                       | pincode | NoCopy |
  | E-Stamped Agreement |₹ 100 Stamp |October 2023 |  2       | A 404 Bren Mercury, Bangalore | 560035  | 2      |



Scenario Outline: Make Payment and Validate Order Status
  Given User Navigates to the Legal Services Page
  When User Clicks on the Check Price button and Selects the "<package>" Package
  And User Selects Stamp "<stampPaper>", Agreement Duration "<agrMonth>", and Notarised Agreement, E-Sign <esignNum> and Delivery addons
  And Clicks on Save and Continue button
  And Lands on Property Detail Page and Enters Property Details:
    | Floor Number                | 10              |
    | BHK Type                    | 4 BHK           |
    | Type of Property            | Shop            |
    | Shop Number                 | 301             |
    | Building Name               | ABC             |
    | Locality                    | HSR             |
    | Pincode                     | 560035          |
  And Click and Add Number of <2> Bathrooms
  And Clicks on Save and Continue button
  Then Lands on Landlord Detail Page and Enters Landlord Details:
    | Full Name                   | Milind G        |
    | Age                         | 30              |
    | Phone                       | 9503333700      |
    | Permanent Address Full      | A-213, Bangalore|
    | PAN Number (ABCDE1234F)     | ABCDE4321F      |
    | Email Address               | mg@gmail.com    |
    | PIN Code                    | 560035          |
    | Party Type                  | Owner           |
    | Gender                      | Male            |
  And Clicks on Save and Continue button
  Then Lands on Tenant Detail Page and Enters Tenant Details:
    | Full Name                   | Sandhya R       |
    | Age                         | 25              |
    | Phone                       | 9503333701      |
    | Permanent Address Full      | B-111, Bangalore|
    | PAN Number (ABCDE1234F)     | ABCDE4321G      |
    | Email Address               | gm@gmail.com    |
    | PIN Code                    | 560035          |
    | Party Type                  | Tenant          |
    | Gender                      | Female          |
  And Clicks on Save and Continue button
  Then Lands on Contract Details Page and Enters Contract Details:
    | Agreement Duration (In months)| 2 Months      |
    | Rent Amount                  | 200000         |
    | Refundable Deposit           | 1200000        |
    | Minimum Lockin Period        | 3              |
  And User Enters Deposit Payment Details,
  And User Selects Rent Day, Notice Period, and Amenities
  And Clicks on Save and Continue button and Navigate to SummaryPage 
  And Clicking on Add New button and submitting delivery Address "<address>" and "<pincode>"
  And Clicking on Number of Extra copy "<NoCopy>" button.
  Then Validate Agreement and Quotation Pricing Details in Summary Page
  When Clicking on Pay button and Navigate to Payment Dashboard
  And User entering the Payment Details "<cardNumber>" "<expiryDate>" "<cvv>"  "<name>" and clicks on Make Payment Btn
  And User Entering OTP "<otp>" and click on SubmitBtn
  Then Validating the Payment and Order Confirmation status in MyBooking

Examples:
  | package             | stampPaper | agrMonth    | esignNum | address                       | pincode | NoCopy | cardNumber          | expiryDate | cvv | name   | otp  |
  | E-Stamped Agreement |₹ 100 Stamp |October 2023 |  2       | A 404 Bren Mercury, Bangalore | 560035  | 2      | 4111-1111-1111-1111 | 02/44      | 121 | Milind | 1234 |

  
  
  
  
  