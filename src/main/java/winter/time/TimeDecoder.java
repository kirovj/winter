package winter.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author kirovj
 * @date 2021/7/17 14:31
 */
public class TimeDecoder extends ByteToMessageDecoder {
    // ByteToMessageDecoder 是 ChannelInboundHandler 的一个实现类，他可以在处理数据拆分的问题上变得很简单
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        // 每当有新数据接收的时候，ByteToMessageDecoder 都会调用 decode() 方法来处理内部的那个累积缓冲。
        if (in.readableBytes() < 4) {
            // Decode() 方法可以决定当累积缓冲里没有足够数据时可以往 out 对象里放任意数据
            // 当有更多的数据被接收了 ByteToMessageDecoder 会再一次调用 decode() 方法。
            return;
        }

        // 如果在 decode() 方法里增加了一个对象到 out 对象里，这意味着解码器解码消息成功
        // ByteToMessageDecoder 将会丢弃在累积缓冲里已经被读过的数据
        // 请记得你不需要对多条消息调用 decode()，ByteToMessageDecoder 会持续调用 decode() 直到不放任何数据到 out 里。
        out.add(new UnixTime(in.readUnsignedInt()));
    }
}
