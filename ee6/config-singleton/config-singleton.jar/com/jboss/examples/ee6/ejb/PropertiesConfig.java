/*
    To the extent possible under law, Red Hat, Inc. has dedicated all copyright to this software to the public domain worldwide, pursuant to the CC0 Public Domain Dedication. This software is distributed without any warranty.  See <http://creativecommons.org/publicdomain/zero/1.0/>.
*/
package com.jboss.examples.ee6.ejb;

import java.lang.management.ManagementFactory;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Date;

import java.util.Properties;

/**
 * @author bmaxwell
 */
@Singleton
@Startup
public class PropertiesConfig
{       
		@Resource(name="propertiesFile")
		private String propertiesFile;

		private Properties properties;

    @PostConstruct
    public void start()
    {
			try {
				if(propertiesFile != null) {
					properties = new Properties();
					properties.load(Thread.currentThread().getContextClassLoader().getResource(propertiesFile).openStream());
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
    }

    @PreDestroy
    public void stop()
    {
    }

		public Properties getProperties() {
			return properties;
		}
}
