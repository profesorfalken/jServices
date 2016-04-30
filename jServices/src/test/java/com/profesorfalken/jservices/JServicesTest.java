/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.profesorfalken.jservices;

import com.profesorfalken.jservices.model.JServicesResponse;
import com.profesorfalken.jservices.model.ServiceInfo;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author javier
 */
public class JServicesTest {
    
    public JServicesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of listAll method, of class JServices.
     */
    @Test
    public void testListAll() {
        System.out.println("listAll");
        List<ServiceInfo> expResult = null;
        List<ServiceInfo> result = JServices.listAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getByName method, of class JServices.
     */
    @Test
    public void testGetByName() {
        System.out.println("getByName");
        String serviceName = "";
        ServiceInfo expResult = null;
        ServiceInfo result = JServices.getByName(serviceName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stop method, of class JServices.
     */
    @Test
    public void testStop() {
        System.out.println("stop");
        String serviceName = "";
        JServicesResponse expResult = null;
        JServicesResponse result = JServices.stop(serviceName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of start method, of class JServices.
     */
    @Test
    public void testStart() {
        System.out.println("start");
        String serviceName = "";
        JServicesResponse expResult = null;
        JServicesResponse result = JServices.start(serviceName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
