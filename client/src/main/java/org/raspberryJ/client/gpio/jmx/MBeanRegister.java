package org.raspberryJ.client.gpio.jmx;

import org.raspberryJ.client.gpio.utils.Notifier;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.util.Set;

public class MBeanRegister {

    public static ObjectInstance registerMBean(Object bean, String objectName) {
        try {
            MBeanServer server = ManagementFactory.getPlatformMBeanServer();
            ObjectName mbeanName = new ObjectName(objectName);
            server.registerMBean(bean, mbeanName);
            Set<ObjectInstance> instances = server.queryMBeans(new ObjectName(objectName), null);
            Notifier.debug("Registrando bean " + objectName);
            return (ObjectInstance) instances.toArray()[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}