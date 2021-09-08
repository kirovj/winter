package winter.itcast.c1;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

import static winter.itcast.utils.ByteBufferUtil.*;

/**
 * 分散写入
 * @author : kirovj
 * @date : 2021/9/8 23:52
 */
public class TestScatteringRead {


    public static void main(String[] args) {
        try (var channel = new RandomAccessFile("file/c1/words.txt", "r").getChannel()) {
            var b1 = ByteBuffer.allocate(3);
            var b2 = ByteBuffer.allocate(3);
            var b3 = ByteBuffer.allocate(5);
            channel.read(new ByteBuffer[]{b1, b2, b3});
            b1.flip();
            b2.flip();
            b3.flip();
            debugAll(b1);
            debugAll(b2);
            debugAll(b3);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
