package jaxb.utils;

import jakarta.xml.bind.DatatypeConverter;

// DatatypeConverter 提供对常见数据类型的读取和转换
public class JaxbDatatypeConverter {

    public String toHex(byte[] bytes) {
        return DatatypeConverter.printHexBinary(bytes);
    }

    public byte[] hexToBytes(String hexString) {
        return DatatypeConverter.parseHexBinary(hexString);
    }
}
