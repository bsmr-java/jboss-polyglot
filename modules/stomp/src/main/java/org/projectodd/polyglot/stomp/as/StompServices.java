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

package org.projectodd.polyglot.stomp.as;

import org.jboss.as.server.deployment.DeploymentUnit;
import org.jboss.modules.ModuleIdentifier;
import org.jboss.msc.service.ServiceName;

public class StompServices {
    public static final ModuleIdentifier MODULE_IDENTIFIER = ModuleIdentifier.create( "org.projectodd.polyglot.stomp" );
    
    public static final ServiceName POLYGLOT = ServiceName.of( "polyglot" );
    public static final ServiceName STOMP = POLYGLOT.append( "stomp" );
    public static final ServiceName SERVER = STOMP.append( "server" );
    public static final ServiceName CONNECTOR = SERVER.append( "connector" );
    
    public static final ServiceName SSL_CONTEXT = STOMP.append( "ssl-context" );
    
    public static ServiceName container(DeploymentUnit unit) {
        return unit.getServiceName().append( "stomp", "container" );
    }
    
    public static ServiceName endpointBinding(DeploymentUnit unit) {
        return container( unit ).append( "endpoint-binding" );
    }
    
    public static ServiceName stomplet(DeploymentUnit unit, String name) {
        return container( unit ).append( name );
    }

}
