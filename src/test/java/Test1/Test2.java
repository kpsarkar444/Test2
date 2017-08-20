package Test1;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class Test2 {
	
static WebDriver driver=null;
	
	
	@Test
	public static void m1() throws Exception{
		
		String expected= "Password is Incorrect for ";
		
	driver=new FirefoxDriver();
	driver.manage().window().maximize();
	driver.get("http://www.bangaloreproperties.com/");
	
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	WebElement frame = driver.findElement(By.xpath("//iframe[@src='seller.asp']"));
	driver.switchTo().frame(frame);
	
	
	// printing all the list in the console by findElements
	
	List<WebElement> ls = driver.findElements(By.xpath("//select[@name='area']"));
	int size = ls.size();
	for(int i=0; i<size; i++){
		String text = ls.get(i).getText();
		System.out.println(text);
	}
	
	
	//Using Select class
	
	WebElement e = driver.findElement(By.xpath("//select[@name='area']"));
	Select sel=new Select(e);
	
	List<WebElement> option = sel.getOptions();
	
	for(WebElement e1 : option){
		if(e1.getText().equalsIgnoreCase("Electronic City")){
			
			e1.click();
			System.out.println(e1.isSelected());
			break;
			
		}
	}
	
	driver.switchTo().defaultContent();
	Thread.sleep(3000);
	
	driver.findElement(By.xpath("//input[@value='Login']")).click();
	WebElement e2 = driver.findElement(By.xpath("//form[@action='signin_handler.asp']/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td/font/b"));
	String actual = e2.getText();
	System.out.println(actual);
	Thread.sleep(2000);
	if(expected.equalsIgnoreCase(actual)){
		System.out.println("Page Verified");
	}
	else{
		System.out.println(" NOT PAGE VERIFIED");
	}
	
	Thread.sleep(3000);
	
	
	
	}
	
	@AfterMethod
	public void tearDown(){
		driver.close();
	}

}
