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

package org.projectodd.polyglot.stomp;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.catalina.Host;
import org.apache.catalina.core.StandardHost;
import org.jboss.as.network.NetworkInterfaceBinding;
import org.jboss.as.network.SocketBinding;
import org.jboss.as.network.SocketBindingManager;
import org.jboss.as.server.services.net.SocketBindingManagerService;
import org.jboss.as.web.VirtualHost;
import org.jboss.msc.service.StartContext;
import org.junit.Test;
import org.projectodd.polyglot.stomp.StompEndpointBindingService;

public class StompEndpointBindingServiceTest {

    @Test
    public void testWithoutVirtualHost() throws Exception {
        StompEndpointBindingService service = new StompEndpointBindingService( null, "/", false );

        InetAddress address = InetAddress.getByAddress( new byte[] { 10, 42, 42, 2 } );
        Collection<NetworkInterface> networkInterfaces = new ArrayList<NetworkInterface>();
        NetworkInterfaceBinding networkInterfaceBinding = new NetworkInterfaceBinding( networkInterfaces, address );
        SocketBindingManager socketBindingManager = new SocketBindingManagerService( 0 );
        SocketBinding socketBinding = new SocketBinding( "stomp", 8675, false, null, 0, networkInterfaceBinding, socketBindingManager, null );
        service.getSocketBindingInjector().inject( socketBinding );

        StartContext context = new MockStartContext();
        service.start( context );
        StompEndpointBinding binding = service.getValue();

        assertNotNull( binding );

        assertEquals( "10.42.42.2", binding.getHost() );
        assertEquals( 8675, binding.getPort() );
        assertEquals( false, binding.isSecure() );
    }

    @Test
    public void testWithStompVirtualHost() throws Exception {
        StompEndpointBindingService service = new StompEndpointBindingService( "tacos.com", "/", false );

        InetAddress address = InetAddress.getByAddress( new byte[] { 10, 42, 42, 2 } );
        Collection<NetworkInterface> networkInterfaces = new ArrayList<NetworkInterface>();
        NetworkInterfaceBinding networkInterfaceBinding = new NetworkInterfaceBinding( networkInterfaces, address );
        SocketBindingManager socketBindingManager = new SocketBindingManagerService( 0 );
        SocketBinding socketBinding = new SocketBinding( "stomp", 8675, false, null, 0, networkInterfaceBinding, socketBindingManager, null );
        service.getSocketBindingInjector().inject( socketBinding );

        StartContext context = new MockStartContext();
        service.start( context );
        StompEndpointBinding binding = service.getValue();

        assertNotNull( binding );

        assertEquals( "tacos.com", binding.getHost() );
        assertEquals( 8675, binding.getPort() );
        assertEquals( false, binding.isSecure() );
    }

    @Test
    public void testWithWebVirtualHost() throws Exception {
        StompEndpointBindingService service = new StompEndpointBindingService( null, "/", false );

        InetAddress address = InetAddress.getByAddress( new byte[] { 10, 42, 42, 2 } );
        Collection<NetworkInterface> networkInterfaces = new ArrayList<NetworkInterface>();
        NetworkInterfaceBinding networkInterfaceBinding = new NetworkInterfaceBinding( networkInterfaces, address );
        SocketBindingManager socketBindingManager = new SocketBindingManagerService( 0 );
        SocketBinding socketBinding = new SocketBinding( "stomp", 8675, false, null, 0, networkInterfaceBinding, socketBindingManager, null );
        service.getSocketBindingInjector().inject( socketBinding );

        Host host = new StandardHost();
        host.setName( "fajitas.com" );
        VirtualHost virtualHost = new VirtualHost( host, false );
        service.getVirtualHostInjector().inject( virtualHost );

        StartContext context = new MockStartContext();
        service.start( context );
        StompEndpointBinding binding = service.getValue();

        assertNotNull( binding );

        assertEquals( "fajitas.com", binding.getHost() );
        assertEquals( 8675, binding.getPort() );
        assertEquals( false, binding.isSecure() );
    }

    @Test
    public void testWithStompAndWebVirtualHost() throws Exception {
        StompEndpointBindingService service = new StompEndpointBindingService( "tacos.com", "/", false );

        InetAddress address = InetAddress.getByAddress( new byte[] { 10, 42, 42, 2 } );
        Collection<NetworkInterface> networkInterfaces = new ArrayList<NetworkInterface>();
        NetworkInterfaceBinding networkInterfaceBinding = new NetworkInterfaceBinding( networkInterfaces, address );
        SocketBindingManager socketBindingManager = new SocketBindingManagerService( 0 );
        SocketBinding socketBinding = new SocketBinding( "stomp", 8675, false, null, 0, networkInterfaceBinding, socketBindingManager, null );
        service.getSocketBindingInjector().inject( socketBinding );

        Host host = new StandardHost();
        host.setName( "fajitas.com" );
        VirtualHost virtualHost = new VirtualHost( host, false );
        service.getVirtualHostInjector().inject( virtualHost );

        StartContext context = new MockStartContext();
        service.start( context );
        StompEndpointBinding binding = service.getValue();

        assertNotNull( binding );

        assertEquals( "tacos.com", binding.getHost() );
        assertEquals( 8675, binding.getPort() );
        assertEquals( false, binding.isSecure() );
    }

}
