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
        System.out.println("===============Testing listAll============");
        
        List<ServiceInfo> result = JServices.listAll();
        
        //check that the command has worked
        assertTrue(result != null);
        
        //check that if has loaded several services
        assertTrue(result.size() > 15);        
        
        System.out.println("===============End Testing listAll============");
    }

    /**
     * Test of getByName method, of class JServices.
     */
    @Test
    public void testGetByName() {
        System.out.println("===============Testing getByName============");
        
        String serviceName = "azerty";
        ServiceInfo result = JServices.getByName(serviceName);
        assertTrue(result == null);
        
        serviceName = "sudo";
        result = JServices.getByName(serviceName);
        assertTrue(result != null);
        assertTrue("sudo".equals(result.getName()));
        
        System.out.println("===============End Testing getByName============");
    }

    /**
     * Test of stop method, of class JServices.
     */
    //@Test
    public void testStop() {
        System.out.println("===============Testing stop============");
        
        String serviceName = "samba";
        JServicesResponse result = JServices.stop(serviceName);
        assertTrue(result.isSuccess());
        
        System.out.println("===============End Testing stop============");
    }

    /**
     * Test of start method, of class JServices.
     */
    //@Test
    public void testStart() {
        System.out.println("===============Testing start============");
        
        String serviceName = "samba";
        JServicesResponse result = JServices.start(serviceName);
        assertTrue(result.isSuccess());
        
        System.out.println("===============End Testing start============");
    }
    
}
