package winter.itcast.c1;

import io.netty.buffer.ByteBuf;
import lombok.extern.log4j.Log4j2;

import java.nio.ByteBuffer;

/**
 * @author : kirovj
 * @date : 2021/9/8 23:24
 */
@Log4j2
public class TestByteBufferAllocate {

    public static void main(String[] args) {
        log.info(ByteBuffer.allocate(16).getClass()); // HeapByteBuffer
        log.info(ByteBuffer.allocateDirect(16).getClass()); // DirectByteBuffer
        /*
        class java.nio.HeapByteBuffer   - 堆内存 读写效率较低，收到 GC 影响
        class java.nio.DirectByteBuffer - 直接内存 读写效率高(少一次拷贝)，不会受到 GC 影响，分配的效率低，使用不当可能会造成内存泄露
         */
    }
}
