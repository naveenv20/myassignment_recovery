package sample.recovery.samplerecovery.keywords;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

public class AppKeywords extends GenericKeywords{

	
	
	public void validateLogin()  {
		test.log(Status.INFO,"Inside the validate login function");	
		if(isElementPresent("logout_xpath")){
			test.log(Status.INFO,"Login Success");	
		}
		else{
			test.log(Status.INFO,"Not able to login");
			reportFailure("Login Failed");
		}
		
	}
	
	public void defaultlogin() {
		String username="";
		String password="";
		username=data.get("Username");
		password=data.get("Password");
		test.log(Status.INFO,"Inside the default login function");	
		getelement("money_lnk_xpath").click();
		getelement("signin_lnk_xpath").click();
		getelement("login_tb_xpath").sendKeys(username);
		getelement("login_ctn_btn_xpath").click();
		WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(getelement("pwd_tb_xpath")));
		getelement("pwd_tb_xpath").sendKeys(password);
		getelement("pwd_cn_btn_xpath").click();
		wait.until(ExpectedConditions.invisibilityOf(getelement("pwd_tb_xpath")));
		
		
	}
	
	public void redmoney_login_succ() {
//		String Expectedheader=data.get(datakey);
//		String actualheader=gettext();
//		if(Expectedheader.equals(actualheader)) {
//			test.log(Status.PASS, "Login success and header match");
//		}
//			else {
//			reportFailure("Login is a faiure ,Expected is :"+Expectedheader +" and actual is : "+actualheader);
//			
//		}

       if(getelement(objectkey)!=null){
    	   test.log(Status.PASS, "Able to see the my portfolio section");
       }
       else{
    	   reportFailure("Login failure");
       }
	
	
	}
		
		public void verifyportfolio() {
			
			test.log(Status.INFO, "Checking if the portfolio is created or not "+data.get(datakey));
			//waitforinvisibilityofelement("create_PF_BTN_id_xpath");
			Select s= new Select(getelement(objectkey));
			String text=s.getFirstSelectedOption().getText();	
			test.log(Status.INFO, "go the value as :"+text);
			if(!text.equalsIgnoreCase(data.get(datakey)))
				reportFailure("value in DP did not matched");	
		}
		
		
		public int getRowWithCellData(String data){
			List<WebElement> rows = driver.findElements(By.xpath("//table[@id='stock']/tbody/tr"));
			for(int rNum=0;rNum<rows.size();rNum++){
				WebElement row = rows.get(rNum);
				List<WebElement> cells = row.findElements(By.tagName("td"));
				for(int cNum=0;cNum<cells.size();cNum++){
					WebElement cell = cells.get(cNum);
					if(!cell.getText().trim().equals("") && data.contains(cell.getText()))
						return ++rNum;
				}
			}
			
			return -1;// not found
		}
		
		
		public void redmoney_addstk_succ(){
			test.log(Status.INFO, "trying to check if the stock is available");
			String stockname=data.get(datakey);
			int rNum=getRowWithCellData(stockname);
			System.out.println("Row " + rNum);
			if(rNum==-1)
				reportFailure("Stock not found");
			else{
				test.log(Status.INFO,"Stock found !!!!!!!");
			}
			
		}
		
		public void deletestockontable(){
			String stockname=data.get(datakey);
			int rNum=getRowWithCellData(stockname);	
			//String stk_radio_xpath=//table[@id='stock']/tbody/tr["+rNum+"]/td[1];
			//driver.findElement(By.xpath("//table[@id='stock']/tbody/tr["+rNum+"]/td[1]")).click();
			click("stock_row_xpath");
			//String stk_delete_xpath=//table[@id='stock']/tbody/tr["+rNum+"]/td[1];
			driver.findElements(By.xpath("//input[@name='Delete']")).get(rNum-1).click();
			driver.switchTo().alert().accept();
			waitForPageToLoad();
			driver.switchTo().defaultContent();
			rNum=getRowWithCellData(stockname);
			System.out.println(rNum);
			if(rNum== -1){
				test.log(Status.PASS, "Deleted the stock successfully");
			}
				else{
					reportFailure("Problem with stock deletion ");
				}
			
		}
}
