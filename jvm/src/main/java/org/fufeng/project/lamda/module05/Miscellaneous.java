package org.fufeng.project.lamda.module05;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.stream.Stream;

import static java.lang.System.in;
import static java.lang.System.out;

/**
 * @ClassName: Miscellaneous
 * @author: LuoChunYun
 * @Date: 2019/4/29 10:51
 * @Description: TODO
 */
public class Miscellaneous {

    public static void main(String[] args) {

        //001 join
        joinString();

        //002 stream close
        streamOnClose();

        //003 Base64
        base64Test();

        //004 自动编码器
        autoEncoder();

        //005 解码器
        autoDecoder();
    }

    private static void autoDecoder() {
        Path decoderPath = Paths.get(System.getProperty("user.dir")+"/jvm/src/main/java/org/fufeng/project/lamda/module05/encodered.txt")
                ,decoderedPath = Paths.get(System.getProperty("user.dir")+"/jvm/src/main/java/org/fufeng/project/lamda/module05/decodered.txt");
        final Base64.Decoder mimeDecoder = Base64.getMimeDecoder();
        try (final InputStream inputStream = Files.newInputStream(decoderPath)){
            Files.copy(mimeDecoder.wrap(inputStream),decoderedPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void autoEncoder() {
        Path originalPath = Paths.get(System.getProperty("user.dir")+"/jvm/src/main/java/org/fufeng/project/lamda/module05/original.txt")
                ,encoderPath = Paths.get(System.getProperty("user.dir")+"/jvm/src/main/java/org/fufeng/project/lamda/module05/encodered.txt");
        final Base64.Encoder mimeEncoder = Base64.getMimeEncoder();
        try(final OutputStream outputStream = Files.newOutputStream(encoderPath)){
            Files.copy(originalPath,mimeEncoder.wrap(outputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void base64Test() {
        //获取编码器
        final Base64.Encoder encoder = Base64.getEncoder();
        String encoderContent = String.join("-","luochunyun","123456");
        final String encodedContent = encoder.encodeToString(
                encoderContent.getBytes(StandardCharsets.UTF_8));
        out.println(encoderContent+":"+encodedContent);
        //获取解码器
        final Base64.Decoder decoder = Base64.getDecoder();
        final byte[] decodeContent = decoder.decode(encodedContent.getBytes());
        final String decodedContent = new String(decodeContent);
        out.println(decodedContent+":"+decodeContent);
    }

    private static void streamOnClose() {
        try (final Stream<String> stringStream = Files.lines(
                Paths.get(System.getProperty("user.dir")+"/jvm/src/main/java/org/fufeng/project/lamda/module01/FirstLamdba.java"))
                .onClose(()->out.println("closing"))
                .filter(s -> s.contains("java"))){
            stringStream.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void joinString() {
        //join @since 1.8
        final String join = String.join(" , ", "1", "2", "3");
        out.println(join);
    }

}
