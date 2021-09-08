package winter.itcast.c1;

import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @author : kirovj
 * @date : 2021/9/8 22:37
 */
@Log4j2
public class TestByteBuffer {

    public static void main(String[] args) {
        // FileChannel
        // 1.In/OutputStream 2. RandomAccessFile
        try(var channel = new FileInputStream("data.txt").getChannel()) {
            // 准备缓冲区
            var buffer = ByteBuffer.allocate(10);
            // 从 channel 中读取数据，向缓冲区 buffer 写入, 当len为-1时没有读取结束
            var len = channel.read(buffer);

            while (len != -1) {

                log.warn("read len: {}", len);
                // 输出 buffer 的内容
                buffer.flip(); // 切换的buffer的读模式

                // 检查是否剩余
                while (buffer.hasRemaining()) {
                    var b = buffer.get();
                    log.debug("result: {}", (char) b);
                }

                buffer.clear(); // 切换到写模式
                len = channel.read(buffer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
