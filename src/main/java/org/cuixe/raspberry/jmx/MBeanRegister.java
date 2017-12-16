package org.cuixe.raspberry.jmx;

import javax.management.MBeanServer;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.Set;

public class MBeanRegister {

    public static ObjectInstance registerMBean(Object bean, String objectName) throws Exception {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        ObjectName mbeanName = new ObjectName(objectName);
        server.registerMBean(bean, mbeanName);
        Set<ObjectInstance> instances = server.queryMBeans(new ObjectName(objectName), null);
        return (ObjectInstance) instances.toArray()[0];
    }

}