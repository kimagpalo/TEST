import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil

WebUI.openBrowser('https://ehgw-dev1.login.us6.oraclecloud.com/')

WebUI.maximizeWindow()

WebUI.setText(findTestObject('Object Repository/common/input_Generic', ['aria-label' : 'User ID']), 'ricaimee.bondoc@emerson.com')

WebUI.setEncryptedText(findTestObject('Object Repository/common/input_Generic', ['aria-label' : 'Password']), 'P22/mDurbpKjdVgV9vjhhw==')

WebUI.click(findTestObject('Object Repository/common/btn_Generic', ['text' : 'Sign In ']))
WebUI.waitForPageLoad(5)

// to update???
WebUI.click(findTestObject('Object Repository/common/img_titleGeneric', ['title' : 'Settings and Actions']))

WebUI.click(findTestObject('Object Repository/common/a_TextGeneric', ['text' : 'Setup and Maintenance']))
WebUI.delay(5)

WebUI.click(findTestObject('Object Repository/common/a_AriaLabelGeneric', ['aria-label' : 'Offering']))
WebUI.click(findTestObject('Object Repository/common/li_Generic', ['text' : 'Sales']))
WebUI.delay(5)

WebUI.click(findTestObject('Object Repository/common/td_textGeneric', ['text' : 'Sales Catalog and Products']))
WebUI.delay(2)

// call datafile
excelData = findTestData('Data Files/Product Hierarchy')
int rowNum = excelData.getRowNumbers()

// start of loop until there's no data in data file
for (int index = 1 ; index <= rowNum ; index++) {
	KeywordUtil.logInfo('Run: ' + index)
	
	WebUI.click(findTestObject('Object Repository/common/a_TextGeneric', ['text' : 'Manage Product Groups']))
	WebUI.delay(5)
	
	WebUI.waitForElementVisible(findTestObject('Object Repository/common/input_idContainsGeneric', ['id' : 'c1::content']), 3)
	WebUI.setText(findTestObject('Object Repository/common/input_idContainsGeneric', ['id' : 'c1::content']), 'Emerson Automation Solutions')
	WebUI.sendKeys(findTestObject('Object Repository/common/input_idContainsGeneric', ['id' : 'c1::content']), Keys.chord(Keys.ENTER))
	WebUI.delay(5)
	
	WebUI.click(findTestObject('Object Repository/common/img_titleGeneric', ['title' : 'Tree View']))
	
	// to narrow down to a product level
	WebUI.click(findTestObject('Object Repository/product groups/a_dropdown', ['text' : excelData.getValue('levelZero', index)]))
	WebUI.click(findTestObject('Object Repository/product groups/a_dropdown', ['text' : excelData.getValue('levelOne', index)]))
	WebUI.click(findTestObject('Object Repository/product groups/a_dropdown', ['text' : excelData.getValue('levelOne.Five', index)]))
	WebUI.click(findTestObject('Object Repository/product groups/a_dropdown', ['text' : excelData.getValue('levelTwo', index)]))
	WebUI.click(findTestObject('Object Repository/product groups/a_dropdown', ['text' : excelData.getValue('levelThree', index)]))
	WebUI.click(findTestObject('Object Repository/product groups/a_dropdown', ['text' : excelData.getValue('levelFour', index)]))
	
	// product level to update
	WebUI.click(findTestObject('Object Repository/product groups/span_product', ['text' : excelData.getValue('levelFive', index)]))
	WebUI.delay(5)
	
	String lockValue = WebUI.verifyElementPresent(findTestObject('Object Repository/common/a_roleBtn', ['text' : 'Lock']), 5, FailureHandling.OPTIONAL)
	
	KeywordUtil.logInfo('Run: ' + lockValue)

	
	if (lockValue.equals('true')) {
		WebUI.click(findTestObject('Object Repository/common/a_roleBtn', ['text' : 'Lock']))
		
		// to delete created test product
		WebUI.click(findTestObject('Object Repository/product groups/menubar_product', ['text' : 'Subgroups']))
		WebUI.delay(5)
		WebUI.scrollToElement(findTestObject('Object Repository/product groups/span_firstRow', ['text' : excelData.getValue('productName', index)]), 5)
		WebUI.waitForElementVisible(findTestObject('Object Repository/product groups/span_firstRow', ['text' : excelData.getValue('productName', index)]), 5)
		WebUI.click(findTestObject('Object Repository/product groups/span_firstRow', ['text' : excelData.getValue('productName', index)]))
		WebUI.delay(5)
		
		WebUI.click(findTestObject('Object Repository/common/img_titleGeneric', ['title' : 'Delete']))
		WebUI.click(findTestObject('Object Repository/common/btn_Generic', ['text' : 'es']))
		WebUI.delay(5)
		
		WebUI.click(findTestObject('Object Repository/common/a_roleBtn', ['text' : 'Save and Close']))
		
	} else {
		// to delete created test product
		WebUI.click(findTestObject('Object Repository/product groups/menubar_product', ['text' : 'Subgroups']))
		WebUI.delay(5)
		WebUI.scrollToElement(findTestObject('Object Repository/product groups/span_firstRow', ['text' : excelData.getValue('productName', index)]), 5)
		WebUI.waitForElementVisible(findTestObject('Object Repository/product groups/span_firstRow', ['text' : excelData.getValue('productName', index)]), 5)
		WebUI.click(findTestObject('Object Repository/product groups/span_firstRow', ['text' : excelData.getValue('productName', index)]))
		WebUI.delay(5)
		
		WebUI.click(findTestObject('Object Repository/common/img_titleGeneric', ['title' : 'Delete']))
		WebUI.click(findTestObject('Object Repository/common/btn_Generic', ['text' : 'es']))
		WebUI.delay(5)
		
		if (index == rowNum) {
			WebUI.click(findTestObject('Object Repository/common/a_roleBtn', ['text' : 'Unlock']))
			KeywordUtil.logInfo('Unlock button.')
		} else {
			WebUI.click(findTestObject('Object Repository/common/a_roleBtn', ['text' : 'Save and Close']))
			KeywordUtil.logInfo('Save and Close button.')
		}
	}
	
}

// to save changes - temp
//WebUI.click(findTestObject('Object Repository/common/a_roleBtn', ['text' : 'Unlock']))
//WebUI.click(findTestObject('Object Repository/common/a_roleBtn', ['text' : 'Publish']))

// Logout user
WebUI.click(findTestObject('Object Repository/common/img_titleGeneric', ['title' : 'Settings and Actions']))
WebUI.delay(3)
WebUI.click(findTestObject('Object Repository/common/a_TextGeneric', ['text' : 'Sign Out']))
WebUI.click(findTestObject('Object Repository/common/btn_Generic', ['text' : ' Confirm']))

WebUI.closeBrowser()
