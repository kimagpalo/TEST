package common
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.ExcelData
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import groovy.ui.SystemOutputInterceptor
import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException


class ProductHierarchy {
	/**
	 * Get all rows of HTML table
	 * @param table Katalon test object represent for HTML table
	 * @param outerTagName outer tag name of TR tag, usually is TBODY
	 * @return All rows inside HTML table
	 */
	@Keyword
	def List<WebElement> getHtmlTableRows(TestObject table, String outerTagName) {
		WebElement mailList = WebUI.findWebElement(table)
		List<WebElement> selectedRows = mailList.findElements(By.xpath("./" + outerTagName + "/tr"))
		return selectedRows
	}

	/**
	 * Verification of the products in the following objects: 
	 *  	Opportunities
	 *  	Global Program
	 *  	Support Requests
	 *  	Growth Program
	 */
	@Keyword
	def verifyProduct(int rowNum) {
		ExcelData excelData = findTestData('Data Files/Product Hierarchy - Testing')
		String productStatus

		if (GlobalVariable.str == 'Opportunities' || GlobalVariable.str == 'Global Program' || GlobalVariable.str == 'Growth Program') {
			for (int index = 1 ; index <= rowNum ; index++) {
				KeywordUtil.logInfo('Run: ' + index)

				WebUI.setText(findTestObject('Object Repository/common/input_idContainsGeneric', ['id' : 'searchform_HierProdGroupName']), excelData.getValue('productName', index))
				WebUI.click(findTestObject('Object Repository/common/btn_idContainsGeneric', ['id' : 'searchId1']))
				WebUI.delay(5)

				productStatus = excelData.getValue('productStatus', index)

				if (productStatus.equalsIgnoreCase('Updated')) {
					KeywordUtil.logInfo('Product is updated.')
					WebUI.verifyElementPresent(findTestObject('Object Repository/product groups/span_firstRow', ['text' : excelData.getValue('productName', index)]), 5, FailureHandling.STOP_ON_FAILURE)
					KeywordUtil.logInfo('Product: ' + excelData.getValue('productName', index) + ' is present')
					WebUI.delay(5)
				} else if (productStatus.equalsIgnoreCase('Removed')) {
					KeywordUtil.logInfo('Product is removed.')
					WebUI.verifyElementNotPresent(findTestObject('Object Repository/product groups/span_firstRow', ['text' : excelData.getValue('productName', index)]), 5, FailureHandling.STOP_ON_FAILURE)
					KeywordUtil.logInfo('Product: ' + excelData.getValue('productName', index) + ' is not present')
					WebUI.delay(5)
				}
			}
		} else if (GlobalVariable.str == 'Support Requests') {
			for (int index = 1 ; index <= rowNum ; index++) {
				KeywordUtil.logInfo('Run: ' + index)

				WebUI.setText(findTestObject('Object Repository/common/input_idContainsGeneric', ['id' : 'it1::content']), excelData.getValue('productName', index))
				WebUI.click(findTestObject('Object Repository/common/a_roleBtn', ['text' : 'Search : All']))
				WebUI.delay(5)

				productStatus = excelData.getValue('productStatus', index)

				if (productStatus.equalsIgnoreCase('Updated')) {
					KeywordUtil.logInfo('Product is updated.')
					WebUI.verifyElementPresent(findTestObject('Object Repository/product groups/span_firstRow', ['text' : excelData.getValue('productName', index)]), 5, FailureHandling.STOP_ON_FAILURE)
					KeywordUtil.logInfo('Product: ' + excelData.getValue('productName', index) + ' is present')
					WebUI.delay(5)
				} else if (productStatus.equalsIgnoreCase('Removed')) {
					KeywordUtil.logInfo('Product is removed.')
					WebUI.verifyElementNotPresent(findTestObject('Object Repository/product groups/span_firstRow', ['text' : excelData.getValue('productName', index)]), 5, FailureHandling.STOP_ON_FAILURE)
					KeywordUtil.logInfo('Product: ' + excelData.getValue('productName', index) + ' is not present')
					WebUI.delay(5)
				}
			}
		}
		/*
		 else if (GlobalVariable.str == 'Growth Program') {
		 for (int index = 1 ; index <= rowNum ; index++) {
		 KeywordUtil.logInfo('Run: ' + index)
		 WebUI.setText(findTestObject('Object Repository/common/input_Generic', ['aria-label' : ' Product Group']), excelData.getValue('productName', index))
		 WebUI.click(findTestObject('Object Repository/common/btn_Generic', ['text' : 'Search']))
		 WebUI.delay(5)
		 productStatus = excelData.getValue('productStatus', index)
		 if (productStatus.equalsIgnoreCase('Updated')) {
		 KeywordUtil.logInfo('Product is updated.')
		 WebUI.verifyElementPresent(findTestObject('Object Repository/product groups/span_firstRow', ['text' : excelData.getValue('productName', index)]), 5, FailureHandling.STOP_ON_FAILURE)
		 KeywordUtil.logInfo('Product: ' + excelData.getValue('productName', index) + ' is present')
		 WebUI.delay(5)
		 } else if (productStatus.equalsIgnoreCase('Removed')) {
		 KeywordUtil.logInfo('Product is removed.')
		 WebUI.verifyElementNotPresent(findTestObject('Object Repository/product groups/span_firstRow', ['text' : excelData.getValue('productName', index)]), 5, FailureHandling.STOP_ON_FAILURE)
		 KeywordUtil.logInfo('Product: ' + excelData.getValue('productName', index) + ' is not present')
		 WebUI.delay(5)
		 }
		 }
		 }
		 */
	}

	@Keyword
	def addProduct(int index, int rowNum) {
		ExcelData excelData = findTestData('Data Files/Product Hierarchy')
		KeywordUtil.logInfo('Run: ' + index)

		WebUI.click(findTestObject('Object Repository/product groups/menubar_product', ['text' : 'Subgroups']))

		WebUI.click(findTestObject('Object Repository/common/img_titleGeneric', ['title' : 'Create']))
		WebUI.delay(3)

		// Create product
		WebUI.setText(findTestObject('Object Repository/common/input_idContainsGeneric', ['id' : 'it13::content']), excelData.getValue('productName', index))
		WebUI.setText(findTestObject('Object Repository/common/input_idContainsGeneric', ['id' : 'it14::content']), excelData.getValue('displayName', index))
		WebUI.setText(findTestObject('Object Repository/common/textarea_idContainsGeneric', ['id' : 'it15::content']), excelData.getValue('description', index))
		KeywordUtil.logInfo('Created product: ' + excelData.getValue('displayName', index))

		// for testing
		//WebUI.click(findTestObject('Object Repository/common/a_titleGeneric, ['title' : 'Save and Close']))
		//WebUI.click(findTestObject('Object Repository/common/btn_Generic', ['text' : 'Save and Close']))
		// WebUI.click(findTestObject('Object Repository/common/btn_idContainsGeneric', ['id' : 'cancelSubGrpBtn']))
		WebUI.delay(3)

		// for updating the exact product
//		WebUI.click(findTestObject('Object Repository/common/btn_Generic', ['text' : 'Save']))
		WebUI.click(findTestObject('Object Repository/product groups/a_dropdown', ['text' : excelData.getValue('levelFive', index)]))
		WebUI.click(findTestObject('Object Repository/product groups/span_product', [('summary') : 'Product Group Hierarchy', ('text') : excelData.getValue('productName', index)]))
		//		WebUI.click(findTestObject('Object Repository/product groups/span_product', ['text' : excelData.getValue('productName', index)]))

		// to add values in details tab of the product
		WebUI.click(findTestObject('Object Repository/product groups/menubar_product', ['text' : 'Details']))

		WebUI.setText(findTestObject('Object Repository/common/input_idContainsGeneric', ['id' : 'Level']), excelData.getValue('level', index))
		KeywordUtil.logInfo('Created product - Level: ' + excelData.getValue('level', index))

		WebUI.setText(findTestObject('Object Repository/common/input_idContainsGeneric', ['id' : 'BusinessUnit']), excelData.getValue('levelOne.Five', index))
		KeywordUtil.logInfo('Created product - Business Unit: ' + excelData.getValue('levelOne.Five', index))

		WebUI.setText(findTestObject('Object Repository/common/input_idContainsGeneric', ['id' : 'Category_c']), excelData.getValue('levelThree', index))
		WebUI.setText(findTestObject('Object Repository/common/input_idContainsGeneric', ['id' : 'Category1_c']), excelData.getValue('levelThree', index))
		KeywordUtil.logInfo('Created product - Category: ' + excelData.getValue('levelThree', index))

		WebUI.click(findTestObject('Object Repository/common/a_roleBtn', ['text' : 'Save and Close']))

		/*
		 if (index == rowNum) {
		 WebUI.click(findTestObject('Object Repository/common/a_roleBtn', ['text' : 'Unlock']))
		 KeywordUtil.logInfo('Unlock button.')
		 } else {
		 String currentValue = excelData.getValue('levelFive', index)
		 String nextValue = excelData.getValue('levelFive', index + 1)
		 if (currentValue.equalsIgnoreCase(nextValue)) {
		 WebUI.click(findTestObject('Object Repository/common/a_roleBtn', ['text' : 'Save and Close']))
		 KeywordUtil.logInfo('Save and Close button.')
		 } else {
		 WebUI.click(findTestObject('Object Repository/common/a_roleBtn', ['text' : 'Unlock']))
		 KeywordUtil.logInfo('Unlock button.')
		 WebUI.click(findTestObject('Object Repository/common/a_roleBtn', ['text' : 'Save and Close']))
		 }
		 }
		 */
	}

	@Keyword
	def addProductLevelFour(int index, int rowNum) {
		ExcelData excelData = findTestData('Data Files/Product Hierarchy')
		KeywordUtil.logInfo('Run: ' + index)

		WebUI.click(findTestObject('Object Repository/product groups/menubar_product', ['text' : 'Subgroups']))

		WebUI.click(findTestObject('Object Repository/common/img_titleGeneric', ['title' : 'Create']))
		WebUI.delay(3)

		// Create product
		WebUI.setText(findTestObject('Object Repository/common/input_idContainsGeneric', ['id' : 'it13::content']), excelData.getValue('productName', index))
		WebUI.setText(findTestObject('Object Repository/common/input_idContainsGeneric', ['id' : 'it14::content']), excelData.getValue('displayName', index))
		WebUI.setText(findTestObject('Object Repository/common/textarea_idContainsGeneric', ['id' : 'it15::content']), excelData.getValue('description', index))
		KeywordUtil.logInfo('Created product: ' + excelData.getValue('displayName', index))

		// for testing
		WebUI.click(findTestObject('Object Repository/common/btn_Generic', ['text' : 'ave and Close']))
		//		WebUI.click(findTestObject('Object Repository/common/btn_idContainsGeneric', ['id' : 'cancelSubGrpBtn']))
		WebUI.delay(3)

		// newly added line
		//		WebUI.click(findTestObject('Object Repository/common/btn_Generic', ['text' : 'Save']))

		// for updating the exact product
		//		WebUI.click(findTestObject('Object Repository/product groups/a_dropdown', ['text' : excelData.getValue('productName', index)]))
		WebUI.click(findTestObject('Object Repository/common/btn_Generic', ['text' : 'Save']))
		WebUI.click(findTestObject('Object Repository/product groups/span_product', [('summary') : 'Product Group Hierarchy', ('text') : excelData.getValue('productName', index)]))
		//		WebUI.click(findTestObject('Object Repository/product groups/span_product', ['text' : excelData.getValue('productName', index)]))

		// to add values in details tab of the product
		WebUI.click(findTestObject('Object Repository/product groups/menubar_product', ['text' : 'Details']))

		WebUI.setText(findTestObject('Object Repository/common/input_idContainsGeneric', ['id' : 'Level']), excelData.getValue('level', index))
		KeywordUtil.logInfo('Created product - Level: ' + excelData.getValue('level', index))

		WebUI.setText(findTestObject('Object Repository/common/input_idContainsGeneric', ['id' : 'BusinessUnit']), excelData.getValue('levelOne.Five', index))
		KeywordUtil.logInfo('Created product - Business Unit: ' + excelData.getValue('levelOne.Five', index))

		WebUI.setText(findTestObject('Object Repository/common/input_idContainsGeneric', ['id' : 'Category_c']), excelData.getValue('levelThree', index))
		WebUI.setText(findTestObject('Object Repository/common/input_idContainsGeneric', ['id' : 'Category1_c']), excelData.getValue('levelThree', index))
		KeywordUtil.logInfo('Created product - Category: ' + excelData.getValue('levelThree', index))

		WebUI.click(findTestObject('Object Repository/common/a_roleBtn', ['text' : 'Save and Close']))

	}

	@Keyword
	def deleteProduct(int index, int rowNum) {
		ExcelData excelData = findTestData('Data Files/Product Hierarchy')
		KeywordUtil.logInfo('Run: ' + index)

		// to delete product
		WebUI.click(findTestObject('Object Repository/product groups/menubar_product', ['text' : 'Subgroups']))
		WebUI.delay(5)
		/*
		 WebUI.scrollToElement(findTestObject('Object Repository/product groups/span_firstRow', ['text' : excelData.getValue('productName', index)]), 5)
		 WebUI.waitForElementVisible(findTestObject('Object Repository/product groups/span_firstRow', ['text' : excelData.getValue('productName', index)]), 5)
		 WebUI.click(findTestObject('Object Repository/product groups/span_firstRow', ['text' : excelData.getValue('productName', index)]))
		 */
		WebUI.scrollToElement(findTestObject('Object Repository/product groups/span_product', [('summary') : 'Subgroups', ('text') : excelData.getValue('productName', index)]), 5)
		WebUI.waitForElementVisible(findTestObject('Object Repository/product groups/span_product', [('summary') : 'Subgroups', ('text') : excelData.getValue('productName', index)]), 5)
		WebUI.click(findTestObject('Object Repository/product groups/span_product', [('summary') : 'Subgroups', ('text') : excelData.getValue('productName', index)]))

		WebUI.delay(5)

		WebUI.click(findTestObject('Object Repository/common/img_titleGeneric', ['title' : 'Delete']))
		// for testing
		WebUI.click(findTestObject('Object Repository/common/btn_Generic', ['text' : 'es']))
		WebUI.delay(5)
		KeywordUtil.logInfo('Delete product: ' + excelData.getValue('productName', index))

		WebUI.click(findTestObject('Object Repository/common/a_roleBtn', ['text' : 'Save and Close']))

		/*
		 if (index == rowNum) {
		 WebUI.click(findTestObject('Object Repository/common/a_roleBtn', ['text' : 'Unlock']))
		 KeywordUtil.logInfo('Unlock button.')
		 } else {
		 String currentValue = excelData.getValue('levelFive', index)
		 String nextValue = excelData.getValue('levelFive', index + 1)
		 if (currentValue.equalsIgnoreCase(nextValue)) {
		 WebUI.click(findTestObject('Object Repository/common/a_roleBtn', ['text' : 'Save and Close']))
		 KeywordUtil.logInfo('Save and Close button.')
		 } else {
		 WebUI.click(findTestObject('Object Repository/common/a_roleBtn', ['text' : 'Unlock']))
		 KeywordUtil.logInfo('Unlock button.')
		 WebUI.click(findTestObject('Object Repository/common/a_roleBtn', ['text' : 'Save and Close']))
		 }
		 }
		 */
	}

	@Keyword
	def reinstateProduct(int index, int rowNum) {
		ExcelData excelData = findTestData('Data Files/Product Hierarchy')

		WebUI.click(findTestObject('Object Repository/product groups/menubar_product', ['text' : 'Subgroups']))

		WebUI.click(findTestObject('Object Repository/common/img_titleGeneric', ['title' : 'Select and Add']))
		WebUI.delay(3)

		// search product to be reinstated
		WebUI.setText(findTestObject('Object Repository/common/input_Generic', ['aria-label' : ' Name']), excelData.getValue('productName', index))
		WebUI.click(findTestObject('Object Repository/common/btn_Generic', ['text' : 'Search']))
		WebUI.delay(5)

		WebUI.waitForElementVisible(findTestObject('Object Repository/product groups/span_firstRow', ['text' : excelData.getValue('productName', index)]), 5)
		WebUI.click(findTestObject('Object Repository/product groups/span_firstRow', ['text' : excelData.getValue('productName', index)]))
		WebUI.delay(5)

		WebUI.click(findTestObject('Object Repository/common/btn_Generic', ['text' : 'App']))
		WebUI.click(findTestObject('Object Repository/common/btn_Generic', ['text' : 'O']))

		WebUI.click(findTestObject('Object Repository/common/a_roleBtn', ['text' : 'Save and Close']))

		/*
		 if (index == rowNum) {
		 WebUI.click(findTestObject('Object Repository/common/a_roleBtn', ['text' : 'Unlock']))
		 KeywordUtil.logInfo('Unlock button.')
		 } else {
		 String currentValue = excelData.getValue('levelFive', index)
		 String nextValue = excelData.getValue('levelFive', index + 1)
		 if (currentValue.equalsIgnoreCase(nextValue)) {
		 WebUI.click(findTestObject('Object Repository/common/a_roleBtn', ['text' : 'Save and Close']))
		 KeywordUtil.logInfo('Save and Close button.')
		 } else {
		 WebUI.click(findTestObject('Object Repository/common/a_roleBtn', ['text' : 'Unlock']))
		 KeywordUtil.logInfo('Unlock button.')
		 WebUI.click(findTestObject('Object Repository/common/a_roleBtn', ['text' : 'Save and Close']))
		 }
		 }
		 */
	}
}