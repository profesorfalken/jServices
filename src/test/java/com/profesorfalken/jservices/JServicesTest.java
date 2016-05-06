/*
 * Copyright 2016 Javier Garcia Alonso.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.profesorfalken.jservices;

import com.profesorfalken.jservices.model.JServicesResponse;
import com.profesorfalken.jservices.model.ServiceInfo;
import com.profesorfalken.jservices.util.OSDetector;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Javier Garcia Alonso
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
        
        if(OSDetector.isWindows()) {
            serviceName = "Messenger";
        } else {
            serviceName = "sudo";
        }
        result = JServices.getByName(serviceName);
        assertTrue(result != null);
        assertTrue(serviceName.equals(result.getName()));
        
        System.out.println("===============End Testing getByName============");
    }

    /**
     * Test of stop method, of class JServices.
     */
    //@Test
    public void testStop() {
        System.out.println("===============Testing stop============");
        
        String serviceName = "test";
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
        
        String serviceName = "test";
        JServicesResponse result = JServices.start(serviceName);
        assertTrue(result.isSuccess());
        
        System.out.println("===============End Testing start============");
    }
    
}
