package com.sos.Pages.WebPages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.sos.CommonFactory.CommonSelenium;


public class DashboardPage extends CommonSelenium {

	private WebDriver driver;

	public DashboardPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	private By societyLogin = By.xpath("//div[@class='nb__GGkUH' or contains(text(), 'Society Login')]");
	private By emailField = By.xpath("(//input[@placeholder='Email'])[1]");
	private By passField = By.xpath("//input[@placeholder='Password']");
	private By loginBtn = By.xpath("//div[@class='nb__1Zwxx' and contains(text(),'Login')]");
	private By userName = By.cssSelector(".nb__19WIg:first-of-type");
	private By title = By.cssSelector(".nb__mLFkD:nth-of-type(2)");
	private By loginError = By.id("alertMessageBox");
	private By activePagelocator = By.xpath("//li//a[contains(@aria-label,'your current page')]");
	private By lastPagelocator = By.xpath("//a[@aria-label='Page 73']//parent::li");
	private By socityOveview = By.xpath("//p[text()='Society Overview']");
	private By currentyInNumbers = By.xpath("(//div[@class='nb__1xMYc'])[1]");
	private By staffOnDuty = By.xpath("(//div[@class='nb__1xMYc'])[2]");
	private By totalFlats = By.xpath("(//div[@class='nb__1xMYc'])[3]");
	private By pendingApprovals = By.xpath("(//div[@class='nb__1xMYc'])[3]");
	private By containsLocator = By.xpath("//div[@class='nb__YR5MK']//child::div[@class='nb__2xOis']");
		
	
	public void pendingApprovals() {
		waitForElementToBeClickable(driver,socityOveview,10);
		click(socityOveview);	
		String  pendingApprovalText = driver.findElement(pendingApprovals).getText();
		String[] part1 = pendingApprovalText.split("\n");
		String pendings = part1[1];
		int totalPendingAprrovals = Integer.parseInt(pendings);	
		List<WebElement> ele = driver.findElements(containsLocator);
		int sumFlat = 0;
		for (WebElement el : ele) {
				if (el.getText().contains("Move In")) {
					String moveInText = el.getText();
					String[] moveIn = moveInText.split("\n");
					int moveInNumbers = Integer.parseInt(moveIn[1]);
					sumFlat = sumFlat + moveInNumbers;
				}
				
				if (el.getText().contains("Staffs")) {
					String staffsText = el.getText();
					String[] staffs = staffsText.split("\n");
					int staffsNumbers = Integer.parseInt(staffs[1]);
					sumFlat = sumFlat + staffsNumbers;
				}
				
				if (el.getText().contains("Services")) {
					String servicesText = el.getText();
					String[] services = servicesText.split("\n");
					int servicesNumbers = Integer.parseInt(services[1]);
					sumFlat = sumFlat + servicesNumbers;
				}
				
				if (el.getText().contains("Move Out")) {
					String movedOutText = el.getText();
					String[] movedOut = movedOutText.split("\n");
					int movedOutNumbers = Integer.parseInt(movedOut[1]);
					sumFlat = sumFlat + movedOutNumbers;
					break;
				}	
		      }
	       }
	
	public void totalFlats() throws InterruptedException {
		waitForElementToBeClickable(driver,socityOveview,10);
		click(socityOveview);
		Thread.sleep(3000);
		String  totalFlatText = driver.findElement(totalFlats).getText();
		String[] part1 = totalFlatText.split("\n");
		String flats = part1[1];
		int totalFlat = Integer.parseInt(flats);		
		List<WebElement> ele = driver.findElements(containsLocator);
		int sumFlat = 0;
		for (WebElement el : ele) {
				if (el.getText().contains("Owners Residing")) {
					String ownerText = el.getText();
					String[] owner = ownerText.split("\n");
					int ownerFlatNumbers = Integer.parseInt(owner[1]);
					sumFlat = sumFlat + ownerFlatNumbers;
				}
				if (el.getText().contains("Tenants")) {
					String tenantsText = el.getText();
					String[] tenants = tenantsText.split("\n");
					int tenantsNumbers = Integer.parseInt(tenants[1]);
					sumFlat = sumFlat + tenantsNumbers;
				}
				
				if (el.getText().contains("Vacants")) {
					String vacantText = el.getText();
					String[] vacant = vacantText.split("\n");
					int vacantFlats = Integer.parseInt(vacant[1]);
					sumFlat = sumFlat + vacantFlats;
				}
				if (el.getText().contains("Free")) {
					String freeText = el.getText();
					String[] free = freeText.split("\\s");
					int freeFlats = Integer.parseInt(free[1]);
					sumFlat = sumFlat + freeFlats;
				}
		     }
      	}
		
	
	public void onDutyStaff(){
		waitForElementToBeClickable(driver,socityOveview,10);
		click(socityOveview);	
		waitForElementDisplay(driver,staffOnDuty,5);
		String  staffOnDutysText = driver.findElement(staffOnDuty).getText();
		String[] part1 = staffOnDutysText.split("\n");
		String dutyStaff = part1[1];
		int dutyStaffs = Integer.parseInt(dutyStaff);
	
		List<WebElement> ele = driver.findElements(containsLocator);
		int sum = 0;
		for (WebElement el : ele) {
				if (el.getText().contains("Security")) {
					String securityText = el.getText();
					String[] security = securityText.split("\\s");
					int securityNumbers = Integer.parseInt(security[1]);
					sum = sum + securityNumbers;
				}
				if (el.getText().contains("Others")) {
					String OthersText = el.getText();
					String[] others = OthersText.split("\\s");
					int othersNumbers = Integer.parseInt(others[1]);
					sum = sum + othersNumbers;
					break;
				}
		     }
	//	Assert.assertEquals(dutyStaffs, sum);
      	}
	
	public void currentlyInSummary() {
		waitForElementToBeClickable(driver,socityOveview,10);
		click(socityOveview);
		waitForElementDisplay(driver,currentyInNumbers,5);
        String currentlyInNumbers =  driver.findElement(currentyInNumbers).getText();
        String totalNumbers  = currentlyInNumbers.substring(currentlyInNumbers.length()-2);
		List<WebElement> ele = driver.findElements(containsLocator);
		int sum = 0;
		for (WebElement el : ele) {
			if (el.getText().contains("Visitors")) {
				String visotorText = el.getText();
				String[] vistors = visotorText.split("\\s");
				int visitorNumbers = Integer.parseInt(vistors[1]);
				sum = sum + visitorNumbers;
			}
			if (el.getText().contains("Services")) {
				String servicesText = el.getText();
				String[] services = servicesText.split("\\s");
				int ServicesNumbers = Integer.parseInt(services[1]);
				sum = sum + ServicesNumbers;
			}

			if (el.getText().contains("Delivery")) {
				String deliveryText = el.getText();
				String[] delivery = deliveryText.split("\\s");
				int deliveryNumbers = Integer.parseInt(delivery[1]);
				sum = sum + deliveryNumbers;
			}

			if (el.getText().contains("Cab")) {
				String cabText = el.getText();
				String[] cab = cabText.split("\\s");
				int cabNumbers = Integer.parseInt(cab[1]);
				sum = sum + cabNumbers;
				break;
			  }
		    }
	     }
	
	
	public void getSocietystafandSecurityDetails() {
	    WebElement lastEle = driver.findElement(lastPagelocator);
	    scrollIntoView(lastEle);
	    String lastEleText = lastEle.getText();
	    int totalPages = Integer.parseInt(lastEleText);
        System.out.println("Total Pages : "+totalPages);
        
	    for (int i = 1; i <=totalPages; i++) {
	       	 click(activePagelocator); // Active Element
	         int rows = driver.findElements(By.xpath("//table[@class='table-striped table-bordered table-hover table']//tbody//tr")).size();
	         System.out.println("Number of Rows : " + rows);
	         String pageNum = Integer.toString(i + 1); 
	         // Read all the rows from each Page 
	         for (int j=1;j<rows;j++) {
	         String name  = driver.findElement(By.xpath("//table[@class='table-striped table-bordered table-hover table']//tbody//tr["+j+"]//td[1]")).getText();
	         String profession  = driver.findElement(By.xpath("//table[@class='table-striped table-bordered table-hover table']//tbody//tr["+j+"]//td[3]")).getText();
	         String createdOn  = driver.findElement(By.xpath("//table[@class='table-striped table-bordered table-hover table']//tbody//tr["+j+"]//td[6]")).getText();
	         String approvedBy  = driver.findElement(By.xpath("//table[@class='table-striped table-bordered table-hover table']//tbody//tr["+j+"]//td[7]")).getText();

	        if(approvedBy.equals("Test")) {
	         System.out.println(String.format("%-25s %-25s %-19s %s", name, profession, createdOn, approvedBy));
	           }    	
	         }
	         
	         if (i == totalPages) {
	             break;
	         }
	         
	         WebElement nextPage = driver.findElement(By.xpath("//a[contains(@aria-label,'Page "+pageNum+"')]"));
	         waitForElementToBeClickable(driver, nextPage,2);
	         nextPage.click();  
	      }
	    }

	public void clickDashbpardElement(String mainModuleTitle, String subModuleTetle) {
		String xpath = "//div[@class='nb__3PXXE']//following::div[contains(text(),'" + mainModuleTitle + "')]";
		WebElement mainModuleEle = waitForElementClickable(driver.findElement(By.xpath(xpath)), 5);
		mainModuleEle.click();
		String xpathsub = "//div[contains(@class,'3CnSO')]//following::a[@title='Manage Staff/Security']"
				.replace("Manage Staff/Security", subModuleTetle);
		click(By.xpath(xpathsub));
		waitFor(2);
	 }

	public void societyAdminLogin(String email, String password) {
		try {
			click(driver.findElement(societyLogin));
			waitForElementDisplay(driver,emailField, 3);
			sendKeys(emailField, email);
			sendKeys(passField, password);
			click(driver.findElement(loginBtn));
		} catch (Exception e) {
			System.err.println("An error occurred during societyAdminLogin: " + e.getMessage());
		}
	}

	public String getUserNameText() {
		try {
			WebElement userNameEle = driver.findElement(userName);
			waitForElementDisplay(userNameEle, 5);
			String userNameText = userNameEle.getText();
			return userNameText;
		} catch (Exception e) {
			System.err.println("An error occurred during getUserNameText: " + e.getMessage());
			return "";
		}
	}

	public String getPageTitle() {
		try {
			WebElement titleEle = driver.findElement(title);
			waitForElementDisplay(titleEle, 5);
			String dashboardTitle = titleEle.getText();
			return dashboardTitle;
		} catch (Exception e) {
			System.err.println("An error occurred during getPageTitle: " + e.getMessage());
			return "";
		}
	}

	public String getLoginErrortext() {
		WebElement errorEle = driver.findElement(loginError);
		waitForElementDisplay(errorEle, 2);
		String loginErrorText = errorEle.getText();
		return loginErrorText;
	}

}
