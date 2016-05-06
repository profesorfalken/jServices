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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Javier Garcia Alonso
 */
public class UnixServicesService extends AbstractServicesService {

    private static final String SERVICE_COMMAND = "service";
    private static final String LISTALL_PARAM = "--status-all";
    private static final String START_PARAM = "start";
    private static final String STOP_PARAM = "stop";

    private static final String LINE_BREAK_PATTERN = "\\r?\\n";

    private static final Pattern FIND_STATUS_PATTERN = Pattern.compile("\\[(.*?)\\]");

    @Override
    protected String getServicesData() {
        return ServicesUtils.executeCommand(SERVICE_COMMAND, LISTALL_PARAM);
    }

    @Override
    protected List<Map<String, String>> parseList(String rawData) {
        System.out.println(rawData);
        List<Map<String, String>> servicesDataList = new ArrayList<>();

        String[] dataStringLines = rawData.split(LINE_BREAK_PATTERN);

        for (final String dataLine : dataStringLines) {
            Map<String, String> element = new LinkedHashMap<>();
            Matcher m = FIND_STATUS_PATTERN.matcher(dataLine);            
            if (m.find()) {      
                element.put("status", getStatus(m.group(1)).name());
            }
            element.put("name", dataLine.substring(dataLine.indexOf("]") + 1).trim());
            
            servicesDataList.add(element);
        }

        return servicesDataList;
    }

    @Override
    public JServicesResponse start(String serviceName) {
        JServicesResponse response = new JServicesResponse();
        if (ServicesUtils.executeCommandAndGetCode(SERVICE_COMMAND, serviceName, START_PARAM) == 0) {
            response.setSuccess(true);
        }
        return response;
    }

    @Override
    public JServicesResponse stop(String serviceName) {
        JServicesResponse response = new JServicesResponse();
        if (ServicesUtils.executeCommandAndGetCode(SERVICE_COMMAND, serviceName, STOP_PARAM) == 0) {
            response.setSuccess(true);
        }
        return response;
    }

    private ServiceStatus getStatus(String symbol) {
        if (symbol == null) {
            return ServiceStatus.UNKNOWN;
        }
        
        switch (symbol.trim()) {
            case "+":
                return ServiceStatus.RUNNING;
            case "-":
                return ServiceStatus.STOPPED;
            case "?":
            default:
                return ServiceStatus.UNKNOWN;
        }
    }
}
