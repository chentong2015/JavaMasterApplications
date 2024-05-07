package metrics.healthchecks.process;

import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricSet;

import java.util.Map;
import java.util.TreeMap;

public class MemoryMetricCollections implements MetricSet {

    @Override
    public Map<String, Metric> getMetrics() {
        Map<String, Metric> metrics = new TreeMap<>();
        metrics.put("jvm", jvmMemoryMetrics());
        // metrics.put("gc", new GarbageCollectionMetricSet());
        return metrics;
    }

    private MetricSet jvmMemoryMetrics() {
        return () -> {
            Map<String, Metric> jvm = new TreeMap<>();
            // jvm.put("heap", heapMemoryMetrics());
            // jvm.put("nonheap", nonheapMemoryMetrics());
            return jvm;
        };
    }
}
