package testcases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class LoginDB {
	
	WebDriver driver;
	
	/*@BeforeTest
	public void launchBrowser() {
	//Open Chrome Browser
	System.setProperty("webdriver.chrome.driver","C:\\Users\\aupadhyay\\eclipse-workspace\\com.demo\\src\\test\\resources\\drivers\\chromedriver.exe");
	driver = new ChromeDriver();
	//Open application URL
	driver.get("http://52.60.159.184/");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}*/
	
	@Test(dataProvider="DP")
	public void validateLogin(String EMAIL, String PASSWORD) throws InterruptedException {
		
		/*WebElement login_link = driver.findElement(By.xpath("//a[contains(text(),'Log In')]"));
		login_link.click();
		WebElement email = driver.findElement(By.xpath("//input[@type = 'email']"));
		email.sendKeys(EMAIL);
		WebElement password = driver.findElement(By.xpath("//input[@type = 'password']"));
		password.sendKeys(PASSWORD);
		
		WebElement submit_btn = driver.findElement(By.xpath("//input[@type = 'submit']"));
		submit_btn.click();
		Thread.sleep(7000);*/
		System.out.println("Db values are: " + EMAIL);
		System.out.println("Db values are: " + PASSWORD);
	}
	
	@DataProvider(name = "DP")
	public String[][] feedDp() throws ClassNotFoundException, SQLException{
		
		String data[][] = getDBValues();
		return data;
	}
	
	public String[][] getDBValues() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/login";
		String username = "root";
		String password = "root";
		
		Connection con = DriverManager.getConnection(url,username,password);
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stmt.executeQuery("Select * from login.logindata");
		rs.last();
		
		int rows = rs.getRow();
		System.out.println("Number of rows " + rows);
		
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		System.out.println("Number of cols: " + cols);
		
		String data[][] = new String[rows][cols];
		
		int i =0;
		rs.beforeFirst();
		while(rs.next())
		{
			for(int j=0;j<cols;j++)
			{
				data[i][j] = rs.getString(j+1);
				System.out.println(data[i][j]);
		}
		i++;
		}
		return data;
	}
	
	/*@AfterTest
	public void tearDown() {
		driver.quit();
	}*/
}

