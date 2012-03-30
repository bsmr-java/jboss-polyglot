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

package org.projectodd.polyglot.messaging.destinations.processors;

import java.util.List;

import org.hornetq.jms.server.JMSServerManager;
import org.jboss.as.messaging.MessagingServices;
import org.jboss.as.messaging.jms.JMSQueueService;
import org.jboss.as.messaging.jms.JMSServices;
import org.jboss.as.server.deployment.DeploymentPhaseContext;
import org.jboss.as.server.deployment.DeploymentUnit;
import org.jboss.as.server.deployment.DeploymentUnitProcessingException;
import org.jboss.as.server.deployment.DeploymentUnitProcessor;
import org.jboss.msc.service.ServiceController.Mode;
import org.jboss.msc.service.ServiceName;
import org.jboss.logging.Logger;
import org.projectodd.polyglot.messaging.destinations.QueueMetaData;

/**
 * <pre>
 * Stage: REAL
 *    In: QueueMetaData
 *   Out: ManagedQueue
 * </pre>
 * 
 */
public class QueueInstaller implements DeploymentUnitProcessor {

    public QueueInstaller() {
    }

    @Override
    public void deploy(DeploymentPhaseContext phaseContext) throws DeploymentUnitProcessingException {
        DeploymentUnit unit = phaseContext.getDeploymentUnit();

        List<QueueMetaData> allMetaData = unit.getAttachmentList( QueueMetaData.ATTACHMENTS_KEY );

        for (QueueMetaData each : allMetaData) {
            deploy( phaseContext, each );
        }

    }

    protected void deploy(DeploymentPhaseContext phaseContext, QueueMetaData queue) {
        final JMSQueueService service = new DestroyableJMSQueueService(queue.getName(), null, queue.isDurable(), new String[] { queue.getBindName() } );
        final ServiceName hornetQserviceName = MessagingServices.getHornetQServiceName( "default" );
        final ServiceName serviceName = JMSServices.getJmsQueueBaseServiceName( hornetQserviceName ).append( queue.getName() );
        try {
            phaseContext.getServiceTarget().addService(serviceName, service)
                .addDependency(JMSServices.getJmsManagerBaseServiceName( hornetQserviceName ), JMSServerManager.class, service.getJmsServer() )
                .setInitialMode(Mode.ACTIVE)
                .install();
        } catch (org.jboss.msc.service.DuplicateServiceException ignored) {
            log.warn("Already started "+serviceName);
        }
    }

    @Override
    public void undeploy(DeploymentUnit context) {

    }

    static final Logger log = Logger.getLogger( "org.projectodd.polyglot.messaging" );
}
