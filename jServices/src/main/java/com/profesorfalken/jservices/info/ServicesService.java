/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.profesorfalken.jservices.info;

import com.profesorfalken.jservices.model.JServicesResponse;
import com.profesorfalken.jservices.model.ServiceInfo;
import java.util.List;

/**
 *
 * @author javier
 */
public interface ServicesService {
    List<ServiceInfo> getList();
    ServiceInfo getByName(String serviceName);
    JServicesResponse start(String serviceName);
    JServicesResponse stop(String serviceName);
}
