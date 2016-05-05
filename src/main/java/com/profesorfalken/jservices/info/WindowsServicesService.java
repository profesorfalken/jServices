/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.profesorfalken.jservices.info;

import com.profesorfalken.jservices.model.JServicesResponse;
import com.profesorfalken.jservices.model.ServiceStatus;
import com.profesorfalken.wmi4java.WMI4Java;
import com.profesorfalken.wmi4java.WMIClass;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author javier
 */
public class WindowsServicesService extends AbstractServicesService {
    private static final String LINE_BREAK_REGEX = "\\r?\\n";

    @Override
    protected String getServicesData() {
        return WMI4Java.get().VBSEngine().getRawWMIObjectOutput(WMIClass.WIN32_SERVICE);
    }

    @Override
    protected List<Map<String, String>> parseList(String rawData) {
        List<Map<String, String>> servicesDataList = new ArrayList<>();

        String[] dataStringLines = rawData.split(LINE_BREAK_REGEX);

        Map<String, String> serviceData = null;
        for (final String dataLine : dataStringLines) {
            if (dataLine.startsWith("Caption")) {
                serviceData = new HashMap<>();
            } else {
                String[] dataStringInfo = dataLine.split(":", 2);
                if (null != dataStringInfo[0].trim()) switch (dataStringInfo[0].trim()) {
                    case "Name":
                        serviceData.put("name", dataStringInfo[1].trim());
                        break;
                    case "State":
                        serviceData.put("status", getStatus(dataStringInfo[1].trim()).name());
                        servicesDataList.add(serviceData);
                        break;
                }
            }                 
        }
        return servicesDataList;
    }

    @Override
    public JServicesResponse start(String serviceName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JServicesResponse stop(String serviceName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private ServiceStatus getStatus(String state) {
        if (state == null) {
            return ServiceStatus.UNKNOWN;
        }
        
        switch (state.trim()) {
            case "Running":
                return ServiceStatus.RUNNING;
            case "Stopped":
                return ServiceStatus.STOPPED;            
            default:
                return ServiceStatus.UNKNOWN;
        }
    }
}
