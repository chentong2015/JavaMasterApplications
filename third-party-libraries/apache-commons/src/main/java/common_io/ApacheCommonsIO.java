package common_io;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ApacheCommonsIO {

    // SerializationUtils: 对象的序列化和反序列化
    public void testSerializationUtils() {
        User user = new User(1, "test");
        byte[] data = SerializationUtils.serialize(user);
        User deserializedUser = SerializationUtils.deserialize(data);
        System.out.println(deserializedUser);
    }

    // IOUtils: 将InputStream流按照指定的编码格式转换成字符串 !!
    private String extractResponseBody(InputStream inputStream) {
        try {
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (IOException exception) {
            // Unable to read response body from server response
            return null;
        }
    }

    // IOUtils: 将InputStream转成byte[]
    // inputStream.readAllBytes()
    private void testReadBytes(InputStream inputStream) throws IOException {
        byte[] content = IOUtils.toByteArray(inputStream);
    }

    private static boolean testIOUtilsCopy(InputStream inputStream) throws Exception {
        StringWriter writer = new StringWriter();
        IOUtils.copy(inputStream, writer, Charset.defaultCharset());
        return writer.toString().contains("maintenance");
    }

    class User implements Serializable {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
