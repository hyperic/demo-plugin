/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hyperic.plugin.demo;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hyperic.hq.product.AutoServerDetector;
import org.hyperic.hq.product.PluginException;
import org.hyperic.hq.product.ServerDetector;
import org.hyperic.hq.product.ServerResource;
import org.hyperic.hq.product.ServiceResource;
import org.hyperic.util.config.ConfigResponse;

/**
 *
 * @author laullon
 */
public class Discovery extends ServerDetector implements AutoServerDetector {

    private static Log log = LogFactory.getLog(Discovery.class);

    public List getServerResources(ConfigResponse platfromConfig) throws PluginException {
        log.debug("[getServerResources] platfromConfig=" + platfromConfig);
        List servers = new ArrayList();
        for (int i = 1; i <= 2; i++) {
            ServerResource server = createServerResource("/opt/server/" + i + "/");
            server.setName(server.getName() + " Server "+i);
            ConfigResponse productConfig = new ConfigResponse();
            productConfig.setValue("process.ID", i);
            setProductConfig(server, productConfig);
            setMeasurementConfig(server, new ConfigResponse());
            servers.add(server);
        }
        return servers;
    }

    @Override
    protected List discoverServices(ConfigResponse serverConfig) throws PluginException {
        log.debug("[discoverServices] serverConfig=" + serverConfig);
        String serverID = serverConfig.getValue("process.ID");
        List services = new ArrayList();
        for (int i = 1; i <= 2; i++) {
            ServiceResource service = createServiceResource("Demo Service");
            service.setName("Server " + serverID + " Service " + i);
            ConfigResponse productConfig = new ConfigResponse();
            productConfig.setValue("service.ID", i);
            setProductConfig(service, productConfig);
            setMeasurementConfig(service, new ConfigResponse());
            services.add(service);
        }
        return services;
    }
}
