package winter.chat.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * handler 是由 Netty 生成用来处理 I/O 事件的
 * <p>
 * SimpleChatServerHandler 继承自 SimpleChannelInboundHandler，这个类实现了 ChannelInboundHandler 接口
 * ChannelInboundHandler 提供了许多事件处理的接口方法，然后你可以覆盖这些方法
 * 现在仅仅只需要继承 SimpleChannelInboundHandler 类而不是你自己去实现接口方法。
 * </p>
 *
 * @author kirovj
 * @date 2021/7/17 15:51
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        // 覆盖了 handlerAdded() 事件处理方法
        // 每当从服务端收到新的客户端连接时，客户端的 Channel 存入 ChannelGroup 列表中，并通知列表中的其他客户端 Channel

        Channel in = ctx.channel();

        // 广播一条消息到其他 channels
        channels.writeAndFlush("[SERVER] - " + in.remoteAddress() + " join in\n");
        channels.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        // 覆盖了 handlerRemoved() 事件处理方法
        // 每当从服务端收到客户端断开时，客户端的 Channel 自动从 ChannelGroup 列表中移除了，并通知列表中的其他客户端 Channel

        Channel in = ctx.channel();
        // Broadcast a message to multiple Channels
        channels.writeAndFlush("[SERVER] - " + in.remoteAddress() + " left\n");
        // A closed Channel is automatically removed from ChannelGroup,
        // so there is no need to do "channels.remove(ctx.channel());"
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        // 覆盖了 channelRead0() 事件处理方法
        // 每当从服务端读到客户端写入信息时，将信息转发给其他客户端的 Channel
        // 其中如果你使用的是 Netty 5.x 版本时，需要把 channelRead0() 重命名为messageReceived()

        Channel in = ctx.channel();

        for (Channel channel : channels) {
            if (channel != in) {
                channel.writeAndFlush("[" + in.remoteAddress() + "]" + msg + "\n");
            } else {
                channel.writeAndFlush("[you]" + msg + "\n");
            }
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        // 覆盖了 channelActive() 事件处理方法。服务端监听到客户端活动

        Channel in = ctx.channel();
        System.out.println("chat client: " + in.remoteAddress() + " online");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        // 覆盖了 channelInactive() 事件处理方法。服务端监听到客户端不活动
        Channel in = ctx.channel();
        System.out.println("chat client: " + in.remoteAddress() + " offline");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // exceptionCaught() 事件处理方法是当出现 Throwable 对象才会被调用，即当 Netty 由于 IO 错误或者处理器在处理事件时抛出的异常时
        // 在大部分情况下，捕获的异常应该被记录下来并且把关联的 channel 给关闭掉
        // 然而这个方法的处理方式会在遇到不同异常的情况下有不同的实现，比如你可能想在关闭连接之前发送一个错误码的响应消息。

        Channel in = ctx.channel();
        System.out.println("chat client:" + in.remoteAddress() + " error!");

        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}
