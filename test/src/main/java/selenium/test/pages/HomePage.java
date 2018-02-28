package selenium.test.pages;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import junit.framework.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class HomePage {

    protected RemoteWebDriver driver;
    protected List<WebElement> information,results,resultCardTitles;
    protected WebElement make,colour;
    protected String makeValue, colourValue;
    protected int offset = 500;

    public HomePage(RemoteWebDriver driver) {
        this.driver = driver;

    }

    public RemoteWebDriver goToPage(String url){
        driver.get("https://" + url);
        return driver;
    }
    
    public RemoteWebDriver clickOnButton(String buttonText) {
    	switch(buttonText) {
    	case "Continue":
    		driver.findElement(By.name(buttonText)).click();
    		break;
    	case "Start now":
    		driver.findElement(By.linkText(buttonText)).click();
    		break;
    	default:
    		Assert.fail("No button found");
    		break;
    	}
    	return driver;
    }
    
    public RemoteWebDriver checkVehicleDetails(String make,String colour) {
    		Assert.assertEquals(make.toLowerCase(), (driver.findElement(By.xpath("//*[@id='pr3']/div/ul/li[2]/span[2]/strong")).getText()).toLowerCase());
    		Assert.assertEquals(colour.toLowerCase(), (driver.findElement(By.xpath("//*[@id='pr3']/div/ul/li[3]/span[2]/strong")).getText()).toLowerCase());
		return driver;
    }

    public RemoteWebDriver enterSearchTerm(String searchTerm) {
        driver.findElement(By.id("Vrm")).sendKeys(searchTerm);
        return driver;
    }
    
    public RemoteWebDriver checkCurrentPageUrl(String url) {
        Assert.assertEquals(url, driver.getCurrentUrl());
        return driver;
    }

    public RemoteWebDriver searchForAnItem(String searchTerm) {
        enterSearchTerm(searchTerm);
        return driver;
    }
    
    public RemoteWebDriver readFromCSV(String fileName, String url) {
    	try {
            FileInputStream file = new FileInputStream(new File("/Users/vdesa4/java-exercise-test/java-test-1/src/vehicle-information/"+fileName));

            //Create Workbook instance holding reference to .xlsx file
            HSSFWorkbook workbook = new HSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            HSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
              
                if(row.getRowNum() != 0) {
                		Iterator<Cell> cellIterator = row.cellIterator();
                		Cell cell = cellIterator.next();
                		System.out.println(cell.getStringCellValue());
                		
                		this.goToPage(url);
                		this.clickOnButton("Start now");
                		this.searchForAnItem(cell.getStringCellValue());
                		this.clickOnButton("Continue");
                		
                		while (cellIterator.hasNext()) 
                     {
                			if(cell.getColumnIndex() == 0) {
                				break;
                			}
                			if(cell.getColumnIndex() == 1) {
                				makeValue = cell.getStringCellValue();
                			} else if(cell.getColumnIndex() == 2) {
                				colourValue = cell.getStringCellValue();
                			}
                			this.checkVehicleDetails(makeValue,colourValue);
                    }
                }
            }
            workbook.close();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    		return driver;
    }
 }
