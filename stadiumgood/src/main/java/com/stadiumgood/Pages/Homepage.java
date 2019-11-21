package com.stadiumgood.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.stadiumgood.Base.Driver;

public class Homepage extends Driver{
	
	@FindBy(xpath = "//*[@class='subscribe-close-header']")
	WebElement x;
	//driver.findElement(By.xpath("//*[@class='subscribe-close-header']")).click()
	@FindBy(xpath = "//div[@class='header-row-secondary']/.//li[starts-with(@class,'level0')]")    
	List<WebElement> allElements;
	
	@FindBy(xpath = "//*[@id='header-nav']/nav/ol/li[2]/a")
	WebElement Jordan;
	
	public Homepage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	public void clickX(){
		x.click();
	}
	
	public int getnotabs(){
		return allElements.size();
	}
	
	public void navigatetoJordan(){
		Jordan.click();
	}
	
	
	@FindBy(xpath = "//*[@class='subscribe-close-header']")
	WebElement xClick;
	
	

}
