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

import com.profesorfalken.jservices.info.ServicesFactory;
import com.profesorfalken.jservices.model.JServicesResponse;
import com.profesorfalken.jservices.model.ServiceInfo;
import java.util.List;

/**
 * Static class that gives access to Services details.
 * 
 * @author Javier Garcia Alonso
 */
public class JServices {
    public static List<ServiceInfo> listAll() {
        return ServicesFactory.getService().getList();
    }
    
    public static ServiceInfo getByName(String serviceName) {
        return ServicesFactory.getService().getByName(serviceName);
    }
    
    public static JServicesResponse stop(String serviceName) {
        return ServicesFactory.getService().stop(serviceName);
    }
    
    public static JServicesResponse start(String serviceName) {
        return ServicesFactory.getService().start(serviceName);
    }
}