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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.testdata.ExcelData as ExcelData

//import com.kms.katalon.core.webui.keyword.WebUI as WebUI
//import org.openqa.selenium.WebElement as WebElement
WebUI.openBrowser('https://ehgw-dev2.login.us6.oraclecloud.com/')

//WebUI.openBrowser('https://ehgw-dev1.login.us6.oraclecloud.com/')
//WebUI.openBrowser('https://ehgw-test.fa.us6.oraclecloud.com/')
//WebUI.openBrowser('https://ehgw.fa.us6.oraclecloud.com/')
WebUI.maximizeWindow()

//WebUI.click(findTestObject('Object Repository/common/btn_idContainsGeneric', ['id' : 'ssoBtn']))
WebUI.setText(findTestObject('Object Repository/common/input_Generic', [('aria-label') : 'User ID']), 'kimalfred.agpalo@emerson.com')

WebUI.setEncryptedText(findTestObject('Object Repository/common/input_Generic', [('aria-label') : 'Password']), 'sLWGwC7NFrCfa8pmvNevNA==')

// dev1 & stg sign in button
//WebUI.click(findTestObject('Object Repository/common/btn_Generic', [('text') : 'Sign In ']))
// dev2 sign in button
WebUI.click(findTestObject('Object Repository/common/btn_Generic', [('text') : '  Sign In ']))

WebUI.waitForPageLoad(5)

WebUI.click(findTestObject('Object Repository/common/img_titleGeneric', [('title') : 'Settings and Actions']))

WebUI.click(findTestObject('Object Repository/common/a_TextGeneric', [('text') : 'Setup and Maintenance']))

WebUI.delay(5)

WebUI.click(findTestObject('Object Repository/common/a_AriaLabelGeneric', [('aria-label') : 'Offering']))

WebUI.click(findTestObject('Object Repository/common/li_Generic', [('text') : 'Sales']))

WebUI.delay(5)

WebUI.click(findTestObject('Object Repository/common/td_textGeneric', [('text') : 'Sales Catalog and Products']))

WebUI.delay(2)

// call datafile	
excelData = findTestData('Data Files/Product Hierarchy')

int rowNum = excelData.getRowNumbers()

// start of loop until there's no data in data file
for (int index = 1; index <= rowNum; index++) {
    KeywordUtil.logInfo('Run: ' + index)

    WebUI.click(findTestObject('Object Repository/common/a_TextGeneric', [('text') : 'Manage Product Groups']))

    WebUI.delay(5)

    WebUI.waitForElementVisible(findTestObject('Object Repository/common/input_idContainsGeneric', [('id') : 'c1::content']), 
        3)

    WebUI.setText(findTestObject('Object Repository/common/input_idContainsGeneric', [('id') : 'c1::content']), 'Emerson Automation Solutions')

    WebUI.sendKeys(findTestObject('Object Repository/common/input_idContainsGeneric', [('id') : 'c1::content']), Keys.chord(
            Keys.ENTER))

    WebUI.delay(5)

    WebUI.click(findTestObject('Object Repository/common/img_titleGeneric', [('title') : 'Tree View']))

    // to narrow down to a product level
    WebUI.click(findTestObject('Object Repository/product groups/a_dropdown', [('text') : excelData.getValue('levelZero', 
                    index)]))

    WebUI.click(findTestObject('Object Repository/product groups/a_dropdown', [('text') : excelData.getValue('levelOne', 
                    index)]))

    WebUI.click(findTestObject('Object Repository/product groups/a_dropdown', [('text') : excelData.getValue('levelOne.Five', 
                    index)]))

    WebUI.click(findTestObject('Object Repository/product groups/a_dropdown', [('text') : excelData.getValue('levelTwo', 
                    index)]))

    WebUI.click(findTestObject('Object Repository/product groups/a_dropdown', [('text') : excelData.getValue('levelThree', 
                    index)]))

    WebUI.click(findTestObject('Object Repository/product groups/a_dropdown', [('text') : excelData.getValue('levelFour', 
                    index)]))

    WebUI.dragAndDropByOffset(findTestObject('common/td_panelsource'), 300, 0)
	//WebUI.executeJavascript("var scroll = document.querySelectorAll('[id$=pc1'); scroll[0].scrollBy(0,300);")
	//WebUI.executeJavaScript("var scroll = document.querySelectorAll('[id$=pc1]'); scroll[0].scrollBy(0, 300);",null)
	//WebUI.scrollToElement(findTestObject('common/div_scroll'), 30)
   // WebUI.scrollToPosition(0, 50)
    //WebUI.scrollToElement(findTestObject('common/div_scroll'), 0)
    //WebUI.delay(3)
    //WebUI.executeJavaScript('window.scrollTo(0, 1200);', null)
    // Assuming your inner scrollable element has an id of 'innerScrollElement' 
    //WebUI.executeJavaScript('document.querySelector("#innerScrollElement").scrollBy(0, 1200);', null)
    //WebUI.executeJavaScript('window.scrollTo(0, document.body.scrollHeight)', null)
    //WebUI.executeJavaScript("window.scrollTo(0, 50);", null)
    //WebUI.executeJavaScript('var scroll = document.querySelectorAll(\'[id$=pc1\'); scroll[0].scrollBy(0,30)', null)
    //Thread.sleep(5000)
    //WebUI.executeJavaScript('', null)
    // WebUI.dragAndDropByOffset(findTestObject('common/div_scroll'), 0, 50)
    //WebUI.scrollToElement(findTestObject('common/div_scroll'), 0)
    String value = excelData.getValue('levelFive', index)

    if (value.isEmpty()) {
        KeywordUtil.logInfo('level five empty')

        WebUI.click(findTestObject('Object Repository/product groups/span_product', [('summary') : 'Product Group Hierarchy'
                    , ('text') : excelData.getValue('levelFour', index)]))

        WebUI.delay(5)

        String lockValue = WebUI.verifyElementPresent(findTestObject('Object Repository/common/a_roleBtn', [('text') : 'Lock']), 
            5, FailureHandling.OPTIONAL)

        KeywordUtil.logInfo('Lock button value ' + lockValue)

        if (lockValue.equals('true')) {
            WebUI.click(findTestObject('Object Repository/common/a_roleBtn', [('text') : 'Lock']))

            CustomKeywords.'common.ProductHierarchy.addProductLevelFour'(index, rowNum)
        } else {
            CustomKeywords.'common.ProductHierarchy.addProductLevelFour'(index, rowNum)
        }
    } else {
        WebUI.click(findTestObject('Object Repository/product groups/span_product', [('summary') : 'Product Group Hierarchy'
                    , ('text') : excelData.getValue('levelFive', index)]))

        WebUI.delay(5)

        String lockValue = WebUI.verifyElementPresent(findTestObject('Object Repository/common/a_roleBtn', [('text') : 'Lock']), 
            5, FailureHandling.OPTIONAL)

        KeywordUtil.logInfo('Lock button value ' + lockValue)

        if (lockValue.equals('true')) {
            WebUI.click(findTestObject('Object Repository/common/a_roleBtn', [('text') : 'Lock']))

            CustomKeywords.'common.ProductHierarchy.addProduct'(index, rowNum)
        } else {
            CustomKeywords.'common.ProductHierarchy.addProduct'(index, rowNum)
        }
    }
}

// to save changes - temp
//WebUI.click(findTestObject('Object Repository/common/a_roleBtn', ['text' : 'Publish']))
// Logout user
WebUI.click(findTestObject('Object Repository/common/img_titleGeneric', [('title') : 'Settings and Actions']))

WebUI.delay(3)

WebUI.click(findTestObject('Object Repository/common/a_TextGeneric', [('text') : 'Sign Out']))

WebUI.click(findTestObject('Object Repository/common/btn_Generic', [('text') : ' Confirm']))

WebUI.closeBrowser()

