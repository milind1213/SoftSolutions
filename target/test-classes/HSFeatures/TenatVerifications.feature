@sanity
Feature: Verify the Tenant Verification Flow

  Background: 
    Given the user has landed on the NoBroker LoginPage
    When the user logs in with valid login credentials
    

  Scenario Outline: Instant Verification Package Functionality
    Given the user lands on the Legal Services Page and selects Tenant Verification
    And clicks on the Get Started button and selects the "<tvPackage>" package
    And the user selects Verification ID Type "<idType>" Enters ID Number "<idNumber>" name "<name>" and DOB "<DOB>"
    And clicks on the Save and Continue button
    Then validate the price <99> and Package Title "Instant Verification" in the summary Page

    Examples: 
      | tvPackage            | idType   | idNumber   | name   | DOB      |
      | Instant Verification | PAN Card | BREPG1234F | Milind | 11112000 |
  
  Scenario Outline: Basic Verification Package Functionality
    Given the user lands on the Legal Services Page and selects Tenant Verification
    And clicks on the Get Started button and selects the "<tvPackage>" package
    And Entering Verification Details ID "<idType>" ID No "<idNumber>" name "<name>" phone "<phone>" DOB "<DOB>" Gender and FatherName "<FatherName>"
    And clicks on the Save and Continue button
    And Entering Address Details "<flatNo>" "<locality>" and pincode "<pincode>"
    Then validates the price <249> and Summary Package Title "Basic Verification" in the summary Page

    Examples: 
      | tvPackage          | idType   | idNumber   | name   | phone      | DOB      | FatherName | flatNo  | locality | pincode |
      | Basic Verification | PAN Card | BREPG1234F | Milind | 9876543210 | 11112000 | John Doe   | A-12011 | HSR      |  560035 |
  
   
  Scenario Outline: Standard Verification Package Functionality
    Given the user lands on the Legal Services Page and selects Tenant Verification
    And clicks on the Get Started button and selects the "<tvPackage>" package
    And Entering Verification Details ID "<idType>" ID No "<idNumber>" name "<name>" phone "<phone>" DOB "<DOB>" Gender and FatherName "<FatherName>"
    And clicks on the Save and Continue button
    And Entering Address Details "<flatNo>" "<locality>" and pincode "<pincode>"
    Then validates the price <249> and Summary Package Title "Standard Verification" in the summary Page

    Examples: 
      | tvPackage             | idType   | idNumber   | name   | phone      | DOB      | FatherName | flatNo  | locality | pincode |
      | Standard Verification | PAN Card | BREPG1234F | Milind | 9876543210 | 11112000 | John Doe   | A-12011 | HSR      |  560035 |
  
  Scenario Outline: Comprehensive Verification Package Functionality
    Given the user lands on the Legal Services Page and selects Tenant Verification
    And clicks on the Get Started button and selects the "<tvPackage>" package
    And Entering Verification Details ID "<idType>" ID No "<idNumber>" name "<name>" phone "<phone>" DOB "<DOB>" Gender and FatherName "<FatherName>"
    And clicks on the Save and Continue button
    And Entering Address Details "<flatNo>" "<locality>" and pincode "<pincode>"
    And Enter Reference Dertails  name "<refName>" "<refMobile>" and "<refEmail>"
    Then validates the price <899> and Summary Package Title "Comprehensive Verification" in the summary Page

    Examples: 
      | tvPackage                  | idType   | idNumber   | name   | phone      | DOB      | FatherName | flatNo  | locality | pincode | refName | refMobile  | refEmail      |
      | Comprehensive Verification | PAN Card | BREPG1234F | Milind | 9876543210 | 11112000 | John Doe   | A-12011 | HSR      |  560035 | John    | 8765434567 | ref@gmail.com |
