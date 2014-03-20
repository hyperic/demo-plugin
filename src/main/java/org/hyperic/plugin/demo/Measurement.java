package org.hyperic.plugin.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
            sid = (sid != null) ? sid : "server";
            File f = new File("/tmp", "server" + id + "_" + sid);
            double val = readValue(f);
            log.info("[getValue] " + f + " => " + val);
            metricValue = new MetricValue(val);
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

    private double readValue(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            StringBuilder stringBuilder = new StringBuilder();
            String ls = System.getProperty("line.separator");

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }

            return Double.parseDouble(stringBuilder.toString());
        } catch (Exception e) {
            return 0;
        }
    }
}
