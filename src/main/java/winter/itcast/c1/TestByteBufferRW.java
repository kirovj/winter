package winter.itcast.c1;

import static winter.itcast.utils.ByteBufferUtil.*;

import java.nio.ByteBuffer;

/**
 * @author : kirovj
 * @date : 2021/9/8 23:16
 */
public class TestByteBufferRW {

    public static void main(String[] args) {
        var buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 0x61); // 'a'
        debugAll(buffer);

        buffer.put(new byte[]{0x62, 0x63, 0x64}); // 'bcd'
        debugAll(buffer);

        buffer.flip();
        System.out.println(buffer.get());
        debugAll(buffer);

        buffer.compact();
        debugAll(buffer);

        buffer.put(new byte[]{0x65, 0x66}); // 'ef'
        debugAll(buffer);
    }
}
