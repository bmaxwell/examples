/*
    To the extent possible under law, Red Hat, Inc. has dedicated all copyright to this software to the public domain worldwide, pursuant to the CC0 Public Domain Dedication. This software is distributed without any warranty.  See <http://creativecommons.org/publicdomain/zero/1.0/>.
*/
package com.jboss.examples.test;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.EJB;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import java.util.Properties;
import com.jboss.examples.ee6.ejb.*;

/**
 * @author bmaxwell
 */
@Singleton
@Startup
public class TestSingleton
{       
		@EJB 
		private PropertiesConfig propertiesConfig;

    @PostConstruct
    public void start()
    {
			try {

				propertiesConfig.getProperties().list(System.out);

			} catch(Exception e) {
				e.printStackTrace();
			}
    }

    @PreDestroy
    public void stop()
    {
    }
}
