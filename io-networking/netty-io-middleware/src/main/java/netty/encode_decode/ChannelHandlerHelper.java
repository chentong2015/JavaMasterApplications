package netty.encode_decode;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class ChannelHandlerHelper {

    // TODO: ���ݴ�������������Զ�������Object����ͽ�����
    // �ڽ���ʱ��Ҫ��֤���������һ��
    public static ChannelHandler getChannelHandler(ChannelHandler handler) {
        return new ChannelInitializer<>() {
            @Override
            protected void initChannel(Channel channel) {
                ChannelPipeline channelPipeline = channel.pipeline();
                ClassLoader classLoader = this.getClass().getClassLoader();
                ClassResolver classResolver = ClassResolvers.weakCachingConcurrentResolver(classLoader);
                channelPipeline.addLast("decoder", new ObjectDecoder(classResolver));
                channelPipeline.addLast("encoder", new ObjectEncoder());
                channelPipeline.addLast("handler", handler);
            }
        };
    }
}
