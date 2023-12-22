package netty.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

// �̳�ָ�������ͣ�ʵ��ָ��ģ�巽��
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    // GlobalEventExecutor.INSTANCE����/��̬��Ա��ȫ�ֵ��¼�ִ����(�������ӵ�Server�˵�����Client)
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    // ����Channel��¼�������¼�
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        System.out.println("Client " + channel.remoteAddress() + " connected");
        // TODO: �����������е�Channel��������Ϣ�����͵�ȫ���Ŀͻ���
        channelGroup.writeAndFlush(formatChannelMessage(channel));
        // ����ǰ��channelҲ��ӵ������У���Ⱥ
        channelGroup.add(channel);
    }

    private String formatChannelMessage(Channel channel) {
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        String dateStr = dateFormat.format(new Date());
        return "Client: " + channel.remoteAddress() + " up," + dateStr + "\n";
    }

    // �������(read)�ͻ�������Ϣ���¼�����Ϣ��װ��msg��
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        Channel channel = ctx.channel();
        // ��Netty Server���յ���Ϣ�󣬻Ὣ��ϢȺ����ÿһ��Channel
        channelGroup.forEach(ch -> {
            String msgTitle = (ch == channel) ? "Me: " : "Client: ";
            ch.writeAndFlush(msgTitle + channel.remoteAddress() + " send message: " + msg);
        });
    }

    // ����Channel���ߣ����ڲ��״̬ ==> �ͻ��˵���clientChannel.close();
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush(formatChannelMessage(channel));  // �ڿͻ�����ʾ
        System.out.println(channel.remoteAddress() + " goes away"); // �ڷ������ʾ
        System.out.println("Remaining connections: " + channelGroup.size());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
