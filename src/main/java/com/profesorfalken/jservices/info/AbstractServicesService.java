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

import com.profesorfalken.jservices.model.ServiceInfo;
import com.profesorfalken.jservices.model.ServiceStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Info related with processes
 *
 * @author Javier Garcia Alonso
 */
abstract class AbstractServicesService implements ServicesService {


    @Override
    public List<ServiceInfo> getList() {
        String rawData = getServicesData();

        List<Map<String, String>> mapList = parseList(rawData);

        return buildInfoFromMap(mapList);
    }
    
        @Override
    public ServiceInfo getByName(String serviceName) {
        if (serviceName == null) {
            return null;
        }
        
        //No risks. For the moment just a linear search
        List<Map<String, String>> servicesDataList = parseList(getServicesData());
        ServiceInfo service = null;
        
        for (final Map<String, String> serviceData : servicesDataList) {
            if (serviceName.equals(serviceData.get("name"))) {
                service = new ServiceInfo();
                service.setName(serviceData.get("name"));
                service.setStatus(ServiceStatus.valueOf(serviceData.get("status")));
            }
        }
        
        return service;
    }
    
    protected abstract String getServicesData();
    
    protected abstract List<Map<String, String>> parseList(String rawData);

    protected List<ServiceInfo> buildInfoFromMap(List<Map<String, String>> mapList) {
        List<ServiceInfo> servicesList = new ArrayList<>();
        for (final Map<String, String> element : mapList) {
            ServiceInfo serviceItem = new ServiceInfo();
            serviceItem.setName(element.get("name"));
            serviceItem.setStatus(ServiceStatus.valueOf(element.get("status")));
            servicesList.add(serviceItem);
        }
        
        return servicesList;
    }

}
