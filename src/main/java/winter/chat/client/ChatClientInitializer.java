package winter.chat.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author kirovj
 * @date 2021/7/19 14:59
 */
public class ChatClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline cp = ch.pipeline();
        cp.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        cp.addLast("decoder", new StringDecoder());
        cp.addLast("encode", new StringEncoder());
        cp.addLast("handler", new ChatClientHandler());
        System.out.println("chat client: " + ch.remoteAddress() + "connected");
    }
}
