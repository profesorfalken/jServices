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
package com.profesorfalken.jservices.info;

import com.profesorfalken.jservices.model.JServicesResponse;
import com.profesorfalken.jservices.model.ServiceStatus;
import com.profesorfalken.jservices.util.ServicesUtils;
import com.profesorfalken.wmi4java.WMI4Java;
import com.profesorfalken.wmi4java.WMIClass;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Javier Garcia Alonso
 */
public class WindowsServicesService extends AbstractServicesService {
    private static final String SERVICE_COMMAND = "net";
    private static final String START_PARAM = "start";
    private static final String STOP_PARAM = "stop";
    
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
        JServicesResponse response = new JServicesResponse();
        if (ServicesUtils.executeCommandAndGetCode(SERVICE_COMMAND, START_PARAM, serviceName) == 0) {
            response.setSuccess(true);
        }
        return response;
    }

    @Override
    public JServicesResponse stop(String serviceName) {
        JServicesResponse response = new JServicesResponse();
        if (ServicesUtils.executeCommandAndGetCode(SERVICE_COMMAND, STOP_PARAM, serviceName) == 0) {
            response.setSuccess(true);
        }
        return response;
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
