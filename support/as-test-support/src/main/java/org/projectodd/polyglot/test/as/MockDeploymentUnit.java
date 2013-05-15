/*
 * Copyright 2008-2013 Red Hat, Inc, and individual contributors.
 * 
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 * 
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.projectodd.polyglot.test.as;

import org.jboss.as.controller.PathAddress;
import org.jboss.as.controller.PathElement;
import org.jboss.as.controller.registry.Resource;
import org.jboss.as.server.deployment.DeploymentUnit;
import org.jboss.as.server.deployment.SimpleAttachable;
import org.jboss.dmr.ModelNode;
import org.jboss.msc.service.ServiceName;
import org.jboss.msc.service.ServiceRegistry;

public class MockDeploymentUnit extends SimpleAttachable implements DeploymentUnit {
    
    private String name;
    private ServiceName serviceName;
    private MockServiceRegistry serviceRegistry;

    public MockDeploymentUnit(MockServiceRegistry serviceRegistry) {
        this( serviceRegistry, "test-unit");
    }
    
    public MockDeploymentUnit(MockServiceRegistry serviceRegistry, String name) {
        this.name = name;
        this.serviceName = ServiceName.of(  "TEST" ).append( name );
        this.serviceRegistry = serviceRegistry;
    }

    @Override
    public ServiceName getServiceName() {
        return this.serviceName;
    }

    @Override
    public DeploymentUnit getParent() {
        return null;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public ServiceRegistry getServiceRegistry() {
        return null;
    }
    
    @Override
    public ModelNode getDeploymentSubsystemModel(String subsystemName) {
        return null;
    }

    @Override
    public ModelNode createDeploymentSubModel(String subsystemName, PathElement address) {
        return null;
    }

    @Override
    public ModelNode createDeploymentSubModel(String subsystemName, PathAddress address) {
        return null;
    }

    @Override
    public ModelNode createDeploymentSubModel(String subsystemName, PathAddress address, Resource resource) {
        return null;
    }

}
