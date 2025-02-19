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

WebUI.openBrowser('https://ehgw-dev2.fa.us6.oraclecloud.com/')
//WebUI.openBrowser('https://ehgw-test.fa.us6.oraclecloud.com/')

WebUI.maximizeWindow()

WebUI.click(findTestObject('Object Repository/common/btn_idContainsGeneric', ['id' : 'ssoBtn']))
/*
WebUI.setText(findTestObject('Object Repository/common/input_Generic', ['aria-label' : 'User ID']), 'ricaimee.bondoc@emerson.com')
WebUI.setEncryptedText(findTestObject('Object Repository/common/input_Generic', ['aria-label' : 'Password']), 'P22/mDurbpKjdVgV9vjhhw==')
WebUI.click(findTestObject('Object Repository/common/btn_Generic', ['text' : '  Sign In ']))
*/

WebUI.waitForPageLoad(5)

// Navigate to Sales > Opportunity object
WebUI.click(findTestObject('Object Repository/common/a_titleGeneric', ['title' : 'Oracle Logo Home']))
WebUI.waitForPageLoad(5)
WebUI.click(findTestObject('Object Repository/common/a_textGeneric', ['text' : 'Sales']))

// Get the value of the object to be tested
GlobalVariable.str = WebUI.getAttribute(findTestObject('Object Repository/common/a_idGeneric', ['id' : 'itemNode_sales_service_requests_0']), 'text')
KeywordUtil.logInfo('Object to be tested is: ' + GlobalVariable.str)

WebUI.click(findTestObject('Object Repository/common/a_idGeneric', ['id' : 'itemNode_sales_service_requests_0']))

// call datafile
excelData = findTestData('Data Files/Product Hierarchy - Testing')
int rowNum = excelData.getRowNumbers()

// Click Create SR button
WebUI.click(findTestObject('Object Repository/common/btn_Generic', ['text' : 'Create Support Request']))
WebUI.delay(5)

WebUI.scrollToElement(findTestObject('Object Repository/common/a_titleGeneric', ['title' : 'Select: Product']), 5)
WebUI.waitForElementVisible(findTestObject('Object Repository/common/a_titleGeneric', ['title' : 'Select: Product']), 5)
WebUI.click(findTestObject('Object Repository/common/a_titleGeneric', ['title' : 'Select: Product']))
WebUI.delay(3)

WebUI.click(findTestObject('Object Repository/common/a_textGeneric', ['text' : 'Search...']))
WebUI.delay(3)

// start of loop until there's no data in data file
CustomKeywords.'common.ProductHierarchy.verifyProduct'(rowNum)

// close search for product
WebUI.click(findTestObject('Object Repository/common/btn_idContainsGeneric', ['id' : 'pt1:cb4']))
WebUI.delay(3)

// close support request creation form
WebUI.click(findTestObject('Object Repository/common/btn_idContainsGeneric', ['id' : 'FAc3']))
WebUI.delay(3)

// Logout user
WebUI.click(findTestObject('Object Repository/common/img_titleGeneric', ['title' : 'Settings and Actions']))
WebUI.delay(3)
WebUI.click(findTestObject('Object Repository/common/a_TextGeneric', ['text' : 'Sign Out']))
WebUI.click(findTestObject('Object Repository/common/btn_Generic', ['text' : ' Confirm']))

GlobalVariable.str = ''
WebUI.closeBrowser()