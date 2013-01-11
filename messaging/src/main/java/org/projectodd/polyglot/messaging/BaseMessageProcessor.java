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

package org.projectodd.polyglot.messaging;

import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;

public abstract class BaseMessageProcessor implements MessageListener {

    public BaseMessageProcessorGroup getGroup() {
        return group;
    }
    
    public void setGroup(BaseMessageProcessorGroup group) {
        this.group = group;
    }
    
    public void setService(MessageProcessorService service) {
        this.service = service;
    }
    
    public Session getSession() {
        return this.service.getSession();
    }
    
    public MessageConsumer getConsumer() {
        return this.service.getConsumer();
    }
   
    
    private BaseMessageProcessorGroup group;
    private MessageProcessorService service;
}
