package com.java.networking.feign.target;

import com.java.networking.feign.target.client.AbsolutePathClient;
import com.java.networking.feign.target.client.RelatedPathClient;
import feign.Feign;
import feign.Target;

// Target: Feign Client请求访问的目标地址
// 包含两种类型的target: EmptyTarget / HardCodedTarget
public class FeignTargetDemo {

    public void testFeignClientTarget() {
        // 1. EmptyTarget does not contain any base url
        // => {baseUri}/v1/sample/index
        Target<AbsolutePathClient> target1 = Target.EmptyTarget.create(AbsolutePathClient.class);
        // => Exception: "Request with non-absolute URL not supported with empty target"
        Target<RelatedPathClient> target2 = Target.EmptyTarget.create(RelatedPathClient.class);
        RelatedPathClient apiClient = new Feign.Builder().target(target2);
        

        // 2. HardCodedTarget contains a hard coded base url
        // => {baseUri}/v1/sample/index, http://anotherhost:8080 is ignored
        Target<AbsolutePathClient> target3 = new Target.HardCodedTarget<>(
                AbsolutePathClient.class, "http://anotherhost:8080");
        // => Exception: "target values must be absolute"
        Target<RelatedPathClient> target4 = new Target.HardCodedTarget<>(
                RelatedPathClient.class, "related/path");
        // => http://myhost:8080/v1/sample/index
        Target<RelatedPathClient> target5 = new Target.HardCodedTarget<>(
                RelatedPathClient.class, "http://myhost:8080");
    }
}
