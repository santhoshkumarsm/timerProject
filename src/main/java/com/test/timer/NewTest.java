package com.test.timer;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class NewTest {
	 WebDriver driver;
	 ArrayList<String> arlist = new ArrayList<String>();
  @Test
  public void f() throws InterruptedException {
	  driver=new ChromeDriver();
	  // Open facebook
	  driver.get("http://e.ggtimer.com/");
	  System.out.println("Launched Browser");

	  // Maximize browser
	  driver.manage().window().maximize();
	  System.out.println("Windows Maximized");

	  driver.findElement(By.xpath("//*[@id='header']/h1/img"));
	  driver.findElement(By.xpath("//*[@id='start_a_timer']")).clear();
	  driver.findElement(By.xpath("//*[@id='start_a_timer']")).sendKeys("30");
	  System.out.println("Set the time unit to 30 secs");

	  driver.findElement(By.xpath("//*[@id='timergo']")).submit();


	  for (String winHandle : driver.getWindowHandles()) {
		  driver.switchTo().window(winHandle); 
		 }
	  
	  for(int i=0;i<30;i++)
	  {
		  String timerVal = driver.findElement(By.xpath("//*[@id='progressText']")).getText();

//		  driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		  
		  WebDriverWait wait = new WebDriverWait(driver,2);
		  WebElement timeEle= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='progressText']")));
		  
		  arlist.add(timerVal.split(" ")[0]);
		  
		  if(timerVal.contains("Expired")) {
			  continue;
			  
		  }
	  }
	  
	  System.out.println("Timer Values => "+arlist);

	  Set<String> set = new LinkedHashSet<>(); 
	  
      // Add the elements to set 
      set.addAll(arlist); 

      arlist.clear(); 

      // Add elements back to list with no duplicates 
      arlist.addAll(set);

      Collections.sort(arlist);
      System.out.println("Timer Values without duplicates => "+arlist);
      
      Integer prev = null;
      int seq = 0;
      
      for(int k=0;k<arlist.size();k++) {
    	  int temp = Integer.parseInt(arlist.get(k));

    	  if(prev != null && prev+1 == temp)
    		  seq = seq == 0 ? 2 : seq+1;
    	  System.out.println("Seq =>"+seq);
    	  prev = temp;
    	  System.out.println("prev =>"+prev);
      }
  }
  @BeforeTest
  public void beforeTest() {
	  System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\driver\\chromedriver.exe");
  }

  @AfterTest
  public void afterTest() {
//	  driver.quit();
  }

}
