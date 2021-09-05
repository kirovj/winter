package winter.learn_netty4_1;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.log4j.Log4j2;

import java.nio.charset.StandardCharsets;

/**
 * @author : kirovj
 * @date : 2021/9/5 22:51
 */
@Log4j2
public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf in = (ByteBuf) msg;
        try {
            log.info("收到消息:{}", in.toString(StandardCharsets.UTF_8));
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 异常处理
        log.error("出现异常", cause);
        ctx.close();
    }
}
