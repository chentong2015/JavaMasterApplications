package metrics.healthchecks.process;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricSet;

import javax.management.JMException;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.PlatformManagedObject;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import static java.lang.management.ManagementFactory.getPlatformMBeanServer;

public class CpuMetricCollections implements MetricSet {

    // TODO. JVM实际运行在的OS上的一个管理接口
    //  The management interface for the operating system on which the Java virtual machine is running.
    private final OperatingSystemMXBean operatingSystem;

    public CpuMetricCollections() {
        this.operatingSystem = ManagementFactory.getOperatingSystemMXBean();
    }

    @Override
    public Map<String, Metric> getMetrics() {
        Map<String, Metric> metrics = new TreeMap<>();
        metrics.put("percentage", processCpuLoad());
        metrics.put("time_cumulated_s", processCpuTime());
        return metrics;
    }

    private Gauge<Double> processCpuLoad() {
        return () -> getManagedAttribute(operatingSystem, "ProcessCpuLoad", Double.class)
                .map(value -> 100 * value).orElse(Double.NaN);
    }

    private Counter processCpuTime() {
        return new Counter() {
            @Override
            public long getCount() {
                return TimeUnit.NANOSECONDS.toSeconds(
                        getManagedAttribute(operatingSystem, "ProcessCpuTime", Long.class).orElse(0L));
            }
        };
    }

    // 从指定的PlatformManagedObject中获取指定的attribute属性的信息
    static <T> Optional<T> getManagedAttribute(PlatformManagedObject platformManagedObject,
                                               String attribute, Class<T> type) {
        try {
            T value = type.cast(getPlatformMBeanServer()
                    .getAttribute(platformManagedObject.getObjectName(), attribute));
            return Optional.ofNullable(value);
        } catch (JMException exception) {
            // ("Error reading attribute {} for managed platform object {} :
            // {}", attribute, platformManagedObject.getObjectName(), exception.getMessage(), exception);
            return Optional.empty();
        }
    }
}
