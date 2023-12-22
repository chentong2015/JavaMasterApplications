package netty.encode_decode;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NServerHandler extends ChannelInboundHandlerAdapter {

    // �����ڶ�ȡ��ʱ���Զ������н����Object����Ȼ��ǿת��ָ�����͵Ķ���
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        DemoClass demoClass = (DemoClass) msg;
        System.out.println(demoClass.getName());
        demoClass.setName("New name");
        ctx.writeAndFlush(demoClass);
    }
}
