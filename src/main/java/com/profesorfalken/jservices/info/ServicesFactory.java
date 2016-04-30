/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.profesorfalken.jservices.info;

import com.profesorfalken.jservices.util.OSDetector;

/**
 *
 * @author javier
 */
public class ServicesFactory {
    //Hide constructor
    private ServicesFactory() {
    }
    
   //use getShape method to get object of type shape 
   public static ServicesService getService(){     
      if (OSDetector.isWindows()) {
          return new WindowsServicesService();
      } else if (OSDetector.isUnix()) {
          return new UnixServicesService();
      }
      
      throw new UnsupportedOperationException("Your Operating System is not supported by this library.");
   }
}
