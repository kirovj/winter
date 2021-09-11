package winter.itcast.c1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * FileChannel 文件传输
 *
 * @author : kirovj
 * @date : 2021/9/11 23:05
 */
public class TestFileChannelTransferTo {

    public static void main(String[] args) {
        try (var from = new FileInputStream("file/c1/data.txt").getChannel();
             var to = new FileOutputStream("file/c1/data_to.txt").getChannel()) {
            // 效率高，底层会用到操作系统的零拷贝进行优化
            from.transferTo(0, from.size(), to);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
