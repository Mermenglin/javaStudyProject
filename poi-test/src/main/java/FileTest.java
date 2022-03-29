import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * 自动创建一百个文件，文件大小从 1k 到 10k 之间
 *
 * @author Meimengling
 * @date 2021-7-7 13:46
 */
public class FileTest {

    static String STRICT_DATE_TIME = "yyyy-MM-dd";
    static DateTimeFormatter STRICT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern(STRICT_DATE_TIME);


    // 阿里云，一百个文件
    public static void main(String[] args) throws IOException {
        String filePath = "D:\\temp\\api-auto-100-files";
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        } else {
            deleteFile(file);
            file.mkdirs();
        }
        long start = System.currentTimeMillis();
        Random ra = new Random();

        for (int i = 0; i < 100; i++) {
            String fileName = LocalDateTime.now().format(STRICT_DATE_TIME_FORMAT) + "-auto-" + i + ".m";
            File f = new File(filePath, fileName);
            if (!f.exists()) {
                createFile(f, ra.nextInt(10) + 1);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("total times " + (end - start));
    }

    private static void deleteFile(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else {//不是文件，对于文件夹的操作
                //保存 路径D:/1/新建文件夹2  下的所有的文件和文件夹到listFiles数组中
                File[] listFiles = file.listFiles();//listFiles方法：返回file路径下所有文件和文件夹的绝对路径
                for (File file2 : listFiles) {
                    /*
                     * 递归作用：由外到内先一层一层删除里面的文件 再从最内层 反过来删除文件夹
                     *    注意：此时的文件夹在上一步的操作之后，里面的文件内容已全部删除
                     *         所以每一层的文件夹都是空的  ==》最后就可以直接删除了
                     */
                    deleteFile(file2);
                }
            }
            file.delete();
        } else {
            System.out.println("路径不存在！");
        }
    }

    private static void createFile(File file, long length) {
        RandomAccessFile ff = null;
        try {
            ff = new RandomAccessFile(file, "rw");
            ff.setLength(length);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ff != null) {
                try {
                    ff.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void createFixLengthFile(File file, long length) throws IOException {
        long start = System.currentTimeMillis();
        FileOutputStream fos = null;
        FileChannel output = null;
        try {
            fos = new FileOutputStream(file);
            output = fos.getChannel();
            output.write(ByteBuffer.allocate(1), length - 1);
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("total times " + (end - start));
    }
}
