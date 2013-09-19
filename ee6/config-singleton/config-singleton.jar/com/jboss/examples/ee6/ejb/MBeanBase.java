/*
    To the extent possible under law, Red Hat, Inc. has dedicated all copyright to this software to the public domain worldwide, pursuant to the CC0 Public Domain Dedication. This software is distributed without any warranty.  See <http://creativecommons.org/publicdomain/zero/1.0/>.
*/
package com.jboss.examples.ee6.ejb;

import java.lang.management.ManagementFactory;
import javax.management.ObjectName;
import javax.management.MBeanServer;

/**
 * @author bmaxwell
 */
public class MBeanBase
{       
    private ObjectName objectName; 
    private MBeanServer platformMBeanServer;

    public void bindIntoJMX(String jmxName)
    {
        try {
          	objectName = new ObjectName(jmxName);
            platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
            platformMBeanServer.registerMBean(this, objectName);
        } catch (Exception e) 
				{
            throw new IllegalStateException("Failed to register " + objectName + " into JMX:" + e);
        }
    }
}
