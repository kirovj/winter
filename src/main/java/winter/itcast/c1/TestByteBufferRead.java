package winter.itcast.c1;

import java.nio.ByteBuffer;
import static winter.itcast.utils.ByteBufferUtil.*;

/**
 * @author : kirovj
 * @date : 2021/9/8 23:32
 */
public class TestByteBufferRead {

    public static void main(String[] args) {
        var buffer = ByteBuffer.allocate(10);
        buffer.put(new byte[]{'a','b','c','d'});
        buffer.flip();

        // rewind 从头开始读
//        buffer.get(new byte[4]);
//        debugAll(buffer);
//        buffer.rewind();
//        System.out.println(buffer.get());

        // mark & reset
        // mark 做一个标记，记录 position 位置，reset 是将 position 重置到 mark 的位置
//        System.out.println((char) buffer.get());
//        System.out.println((char) buffer.get());
//        buffer.mark(); // 标记  position = 2
//        System.out.println((char) buffer.get());
//        System.out.println((char) buffer.get()); // position = 4
//
//        buffer.reset(); // 将 position = 2
//        System.out.println((char) buffer.get());
//        System.out.println((char) buffer.get());

        // get(i) 不会改变索引的位置
        System.out.println((char) buffer.get(3));
        debugAll(buffer);  // pos = 0
    }
}
