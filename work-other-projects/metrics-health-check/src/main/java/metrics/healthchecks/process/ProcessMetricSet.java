package metrics.healthchecks.process;

import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricSet;

import java.util.Map;
import java.util.TreeMap;

// 对Java Process在生产环境下的Metric指标的检测
public class ProcessMetricSet implements MetricSet {

    @Override
    public Map<String, Metric> getMetrics() {
        Map<String, Metric> metrics = new TreeMap<>();
        metrics.put("cpu", new CpuMetricCollections());
        metrics.put("memory", new MemoryMetricCollections());
        metrics.put("files", new FileMetricCollections());
        return metrics;
    }
}
