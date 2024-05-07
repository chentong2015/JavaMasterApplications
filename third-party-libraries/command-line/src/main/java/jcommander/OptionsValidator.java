package jcommander;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

import java.io.*;
import java.text.MessageFormat;

// 用于验证命令行参数的格式
public class OptionsValidator implements IParameterValidator {

    private static final String EXTENSION_CFG = "cfg";
    private static final String FILE_NOT_FOUND_ERR_MSG = "Specified file ''{0}'' not found";
    private static final String FILE_IS_DIR_ERR_MSG = "Specified file ''{0}'' is a directory";
    private static final String INVALID_FILE_EXT_ERR_MSG = "Invalid file extension, expected ''.{0}'' file";

    @Override
    public void validate(String param, String arg) {
        File file = new File(arg);
        if (!file.exists()) {
            throw new ParameterException(MessageFormat.format(FILE_NOT_FOUND_ERR_MSG, arg));
        }
        if (file.isDirectory()) {
            throw new ParameterException(MessageFormat.format(FILE_IS_DIR_ERR_MSG, arg));
        }
        if (!isFileExtension(file, EXTENSION_CFG)) {
            throw new ParameterException(MessageFormat.format(INVALID_FILE_EXT_ERR_MSG, EXTENSION_CFG));
        }
    }

    public static boolean isFileExtension(File file, String extension) {
        String fileName = file.getName();
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex != -1 && lastDotIndex != 0) {
            String extensionFile = fileName.substring(lastDotIndex + 1);
            return extension.equals(extensionFile);
        }
        return false;
    }
}
