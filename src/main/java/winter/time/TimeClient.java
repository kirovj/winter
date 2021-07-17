package winter.time;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author kirovj
 * @date 2021/7/17 11:28
 */
public class TimeClient {

    public static void main(String[] args) throws InterruptedException {
        String host = "127.0.0.1";
        int port = 8080;

        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // BootStrap 和 ServerBootstrap 类似,不过他是对非服务端的 channel 而言，比如客户端或者无连接传输模式的 channel。
            Bootstrap b = new Bootstrap();
            // 如果你只指定了一个 EventLoopGroup，那他就会即作为一个 boss group ，也会作为一个 workder group，尽管客户端不需要使用到 boss worker 。
            b.group(workerGroup);
            // 代替NioServerSocketChannel的是NioSocketChannel,这个类在客户端channel 被创建时使用。
            b.channel(NioSocketChannel.class);
            // 不像在使用 ServerBootstrap 时需要用 childOption() 方法，因为客户端的 SocketChannel 没有父亲。
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new TimeDecoder(), new TimeClientHandler());
                }
            });

            // 启动客户端 我们用 connect() 方法代替了 bind() 方法。
            ChannelFuture f = b.connect(host, port).sync();

            // 等待连接关闭
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
