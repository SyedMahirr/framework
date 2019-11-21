package com.stadiumgood.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Jordanpage{
	
	
	@FindBy(xpath = "//*[@id='top']/body/div[2]/div[1]/div[2]/div/div[2]/main/div[2]/div/div[2]/div[2]/div/select")
	WebElement soryBy;
	
	//*[@id='sort_by_chosen']/.//a/.//div
	
	@FindBy(xpath = "//*[@id='sort_by_chosen']/.//a/.//div")
	WebElement soryBy1;
	
	@FindBy(xpath = "//li[contains(.='Price Low to High')]")
	WebElement pricelowtohigh;
	
	public Jordanpage(WebDriver driver){
		PageFactory.initElements(driver, this);		
	}
	
	public void selectPriceHightoLow() throws Exception{
		Select sel = new Select(soryBy);
		//sel.selectByVisibleText("Price High to Low");
		sel.selectByVisibleText("Price Low to High");
	}

}
