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

package org.projectodd.polyglot.core.processors;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.jboss.as.server.deployment.DeploymentUnit;
import org.jboss.as.server.deployment.module.MountHandle;
import org.jboss.as.server.deployment.module.ResourceRoot;
import org.jboss.vfs.VFS;
import org.junit.Test;
import org.projectodd.polyglot.core.processors.DescriptorRootMountProcessor;
import org.projectodd.polyglot.test.as.MockDeploymentPhaseContext;
import org.projectodd.polyglot.test.as.AbstractDeploymentProcessorTestCase;

public class DescriptorRootMountProcessorTest extends AbstractDeploymentProcessorTestCase {
    
    @Test
    public void testUnmountsOnUndeploy() throws Exception {
        DescriptorRootMountProcessor deployer = new DescriptorRootMountProcessor( "foo" );
        appendDeployer( deployer );
        
        MockDeploymentPhaseContext phaseContext = createPhaseContext();
        DeploymentUnit unit = phaseContext.getMockDeploymentUnit();
        MountHandle mountHandle = mock( MountHandle.class );
        ResourceRoot resourceRoot = new ResourceRoot( VFS.getChild( "." ), mountHandle );
        unit.putAttachment( DescriptorRootMountProcessor.DESCRIPTOR_ROOT, resourceRoot );
        
        undeploy( unit );
        
        verify( mountHandle ).close();
    }

}
