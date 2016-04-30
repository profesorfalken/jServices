/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.profesorfalken.jservices.info;

import com.profesorfalken.jservices.model.JServicesResponse;
import com.profesorfalken.jservices.model.ServiceInfo;
import com.profesorfalken.jservices.util.ServicesUtils;
import java.util.List;
import java.util.Map;

/**
 *
 * @author javier
 */
public class UnixServicesService extends AbstractServicesService {
    private static final String SERVICE_COMMAND = "service";
    private static final String LISTALL_PARAM = "--status-all";
    
    

    @Override
    public ServiceInfo getByName(String serviceName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JServicesResponse start(String serviceName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JServicesResponse stop(String serviceName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String getServicesData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected List<Map<String, String>> parseList(String rawData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected List<ServiceInfo> buildInfoFromMap(List<Map<String, String>> mapList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
