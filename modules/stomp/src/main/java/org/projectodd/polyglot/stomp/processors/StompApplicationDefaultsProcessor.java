/*
 * Copyright 2008-2012 Red Hat, Inc, and individual contributors.
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

package org.projectodd.polyglot.stomp.processors;

import org.jboss.as.server.deployment.DeploymentPhaseContext;
import org.jboss.as.server.deployment.DeploymentUnit;
import org.jboss.as.server.deployment.DeploymentUnitProcessingException;
import org.jboss.as.server.deployment.DeploymentUnitProcessor;
import org.projectodd.polyglot.stomp.StompApplicationMetaData;

public class StompApplicationDefaultsProcessor implements DeploymentUnitProcessor {

    public StompApplicationDefaultsProcessor(boolean setContext) {
        this.setContext = setContext;
    }
    
    @Override
    public void deploy(DeploymentPhaseContext phaseContext) throws DeploymentUnitProcessingException {
        DeploymentUnit unit = phaseContext.getDeploymentUnit();
        
        StompApplicationMetaData stompAppMetaData = unit.getAttachment( StompApplicationMetaData.ATTACHMENT_KEY );
        
        if ( stompAppMetaData == null ) {
            return;
        }
        
        if (this.setContext && stompAppMetaData.getContextPath() == null ) {
            stompAppMetaData.setContextPath( "/" );
        }
        
    }

    @Override
    public void undeploy(DeploymentUnit context) {
        
    }

    private boolean setContext;
}
