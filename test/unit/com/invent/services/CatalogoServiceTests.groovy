package com.invent.services

import grails.test.*

class CatalogoServiceTests extends GrailsUnitTestCase {
    
    def catalogoService
    
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSomething() {
        
         def result = catalogoService.findAssets('3', '182')
             
         aserrtNotnull result
        
        
    }
}
