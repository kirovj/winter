package winter.itcast.c1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

import static winter.itcast.utils.ByteBufferUtil.*;

/**
 * 集中写入
 *
 * @author : kirovj
 * @date : 2021/9/8 23:56
 */
public class TestGatheringWrite {

    public static void main(String[] args) {
        var b1 = StandardCharsets.UTF_8.encode("hello");
        var b2 = StandardCharsets.UTF_8.encode("world");
        var b3 = StandardCharsets.UTF_8.encode("你好");

        try (var channel = new RandomAccessFile("file/c1/words2.txt", "rw").getChannel()) {
            channel.write(new ByteBuffer[]{b1, b2, b3});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
