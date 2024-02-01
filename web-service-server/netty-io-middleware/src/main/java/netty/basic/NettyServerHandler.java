package netty.basic;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

// �Զ���Handler����������Ҫ�̳й涨����HandlerAdapter
// ChannelInboundHandlerAdapter   �̳и����ʹ������Object���͵����ݣ���Ҫָ���ض��ı���ͽ�����
// SimpleChannelInboundHandler<T> �̳и������������������String(��װ)����Ҫ����String����ͽ�����

// TODO: ��ͬһ�������б����client�����ӣ����ServerHandler��Ҫ�����ó�"�ɹ���"
@ChannelHandler.Sharable
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    // ���ͻ������ӷ��������ʱ���������淽�����ڷ���˻ص�
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("Connection OK");
    }

    // ��ȡ�ͻ��˷��͵����ݣ����ݷ�װ��msgֱ��ʹ��(Դ���װʱʹ�õĻ������ݽṹ��ByteBuf)
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // Channel channel = ctx.channel(); �õ��������ӵĿͻ���Channel
        // ChannelPipeline pipeline = ctx.pipeline(); ˫��������վ����վ
        // ǿ��ת���ɷ�װ�����ݵ�����
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("Get message from client: " + byteBuf.toString(CharsetUtil.UTF_8));
        // ��д��Ϣ�ؿͻ���
        ctx.writeAndFlush(msg);
    }
}
