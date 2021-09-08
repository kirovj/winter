package winter.itcast.c1;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import static winter.itcast.utils.ByteBufferUtil.*;

/**
 * 粘包 半包 演示
 * @author : kirovj
 * @date : 2021/9/9 0:15
 */
public class TestByteBufferExam {

    public static void main(String[] args) {

        var str = "hello world!\ni am j\nho";
        var str2 = "w are you\n";

        var buffer = StandardCharsets.UTF_8.encode(str);
        split(buffer);
        buffer = StandardCharsets.UTF_8.encode(str2);
        split(buffer);
    }

    static void split(ByteBuffer buffer) {
        for (int i = 0; i < buffer.limit(); i++) {
            if (buffer.get(i) == '\n') {
                int len = i - buffer.position() + 1;
                var target = ByteBuffer.allocate(len);
                for (int j = 0; j < len; j++) {
                    target.put(buffer.get());
                }
                debugAll(target);
            }
        }
        buffer.compact();
    }
}
