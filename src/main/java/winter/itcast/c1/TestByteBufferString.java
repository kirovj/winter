package winter.itcast.c1;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static winter.itcast.utils.ByteBufferUtil.*;

/**
 * 与字符串转换
 * @author : kirovj
 * @date : 2021/9/8 23:42
 */
public class TestByteBufferString {

    public static void main(String[] args) {
        // 1. 字符串 -> ByteBuffer
        // 注意第一种方法的buffer转字符串时要切换到读模式
        var str = "hello";

        var buffer = ByteBuffer.allocate(16);
        buffer.put(str.getBytes());

        debugAll(buffer);

        // 2. Charset
        var buffer1 = StandardCharsets.UTF_8.encode(str);
        debugAll(buffer1);

        // 3. wrap
        var buffer3 = ByteBuffer.wrap(str.getBytes());
        debugAll(buffer3);

        // buffer -> 字符串
        System.out.println(StandardCharsets.UTF_8.decode(buffer3));
    }
}
