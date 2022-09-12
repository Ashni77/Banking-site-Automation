package testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Login_TC1 {
	
	WebDriver driver;
	
	@BeforeTest
	public void launchBrowser() {
	//Open Chrome Browser
	System.setProperty("webdriver.chrome.driver","C:\\Users\\aupadhyay\\eclipse-workspace\\com.demo\\src\\test\\resources\\drivers\\chromedriver.exe");
	driver = new ChromeDriver();
	//Open application URL
	driver.get("http://52.60.159.184/");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void verifyLogin() {
		WebElement login_link = driver.findElement(By.xpath("//a[contains(text(),'Log In')]"));
		login_link.click();
		WebElement email = driver.findElement(By.xpath("//input[@type = 'email']"));
		email.sendKeys("sonu@yahoo.com");
		WebElement password = driver.findElement(By.xpath("//input[@type = 'password']"));
		password.sendKeys("Jombone@1234");
		WebElement submit_btn = driver.findElement(By.xpath("//input[@type = 'submit']"));
		submit_btn.click();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
