package winter.learn_netty4_1;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.log4j.Log4j2;

import java.nio.charset.StandardCharsets;

/**
 * @author : kirovj
 * @date : 2021/9/5 23:21
 */
@Log4j2
public class FirstClientHandler extends ChannelInboundHandlerAdapter {
    private ByteBuf content;
    private ChannelHandlerContext ctx;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        this.ctx = ctx;
        content = ctx.alloc().directBuffer(256).writeBytes("Hello flydean.com".getBytes(StandardCharsets.UTF_8));
        // 发送消息
        sayHello();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 异常处理
        log.error("出现异常",cause);
        ctx.close();
    }

    private void sayHello() {
        // 向服务器输出消息
        ctx.writeAndFlush(content.retain());
    }
}
