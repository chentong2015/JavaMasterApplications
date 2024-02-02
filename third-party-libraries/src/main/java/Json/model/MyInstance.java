package json.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import static java.util.Objects.requireNonNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MyInstance {

    private final String name;
    // TODO. 如果是Web请求返回的JSON格式的数据，如果和这里的字段不匹配
    //  则jackson在解析json成object的时候，是无法完成构造的 !!
    //  必须严格的保证设计上的POJOs一致性
    private final List<String> services;

    @JsonCreator
    public MyInstance(@JsonProperty("name") final String name,
                      @JsonProperty("services") List<String> services) {
        this.name = requireNonNull(name);
        this.services = requireNonNull(services);
    }

    // 标记注解的方法将会同时作用在Json<->Object之间的转换上
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("services")
    public List<String> getServices() {
        return services;
        // return Collections.unmodifiableList(services);
    }
}
