package org.hyperic.plugin.demo;

import java.io.File;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hyperic.hq.product.*;

public class Measurement extends MeasurementPlugin {

    private static Log log = LogFactory.getLog(Measurement.class.getName());

    @Override
    public MetricValue getValue(Metric metric) throws PluginException, MetricNotFoundException, MetricUnreachableException {
        log.debug("[getValue]metric" + metric);
        MetricValue metricValue;
        if (metric.isAvail()) {
            String id = metric.getObjectProperty("id");
            String sid = metric.getObjectProperty("sid");
            sid = (sid!=null)?sid:"server";
            File f = new File("/tmp","server"+id+"_"+sid);
            log.info("[getValue] f:"+f+" => "+(f.exists()?"OK":"ko"));
            metricValue = new MetricValue(f.exists()?Metric.AVAIL_UP:Metric.AVAIL_DOWN);
        } else {
            String id = metric.getObjectProperty("id");
            String sid = metric.getObjectProperty("sid");
            Double val = Double.parseDouble(id);
            if (sid != null) {
                val += Double.parseDouble(sid);
            }
            metricValue = new MetricValue(val);
        }
        return metricValue;
    }
}