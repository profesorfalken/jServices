/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author javier
 */
public class UnixServicesService extends AbstractServicesService {

    private static final String SERVICE_COMMAND = "service";
    private static final String LISTALL_PARAM = "--status-all";
    private static final String START_PARAM = "--status-all";
    private static final String STOP_PARAM = "--status-all";

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
