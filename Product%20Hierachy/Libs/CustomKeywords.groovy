
/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */

import com.kms.katalon.core.testobject.TestObject

import java.lang.String


 /**
	 * Get all rows of HTML table
	 * @param table Katalon test object represent for HTML table
	 * @param outerTagName outer tag name of TR tag, usually is TBODY
	 * @return All rows inside HTML table
	 */ 
def static "common.ProductHierarchy.getHtmlTableRows"(
    	TestObject table	
     , 	String outerTagName	) {
    (new common.ProductHierarchy()).getHtmlTableRows(
        	table
         , 	outerTagName)
}

 /**
	 * Verification of the products in the following objects: 
	 *  	Opportunities
	 *  	Global Program
	 *  	Support Requests
	 *  	Growth Program
	 */ 
def static "common.ProductHierarchy.verifyProduct"(
    	int rowNum	) {
    (new common.ProductHierarchy()).verifyProduct(
        	rowNum)
}


def static "common.ProductHierarchy.addProduct"(
    	int index	
     , 	int rowNum	) {
    (new common.ProductHierarchy()).addProduct(
        	index
         , 	rowNum)
}


def static "common.ProductHierarchy.addProductLevelFour"(
    	int index	
     , 	int rowNum	) {
    (new common.ProductHierarchy()).addProductLevelFour(
        	index
         , 	rowNum)
}


def static "common.ProductHierarchy.deleteProduct"(
    	int index	
     , 	int rowNum	) {
    (new common.ProductHierarchy()).deleteProduct(
        	index
         , 	rowNum)
}


def static "common.ProductHierarchy.reinstateProduct"(
    	int index	
     , 	int rowNum	) {
    (new common.ProductHierarchy()).reinstateProduct(
        	index
         , 	rowNum)
}
