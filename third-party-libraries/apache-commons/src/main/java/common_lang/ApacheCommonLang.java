package common_lang;

import org.apache.commons.lang3.StringUtils;

public class ApacheCommonLang {

    // TODO. StringUtils提供常见的String处理的APIs
    public static void main(String[] args) {
        System.out.println(StringUtils.isBlank(null));
        System.out.println(StringUtils.isEmpty(""));
        System.out.println(StringUtils.upperCase("test"));

        // 移除字符串头部和尾部的空格Whitespace
        // str = stripStart(str, stripChars);
        // str = stripEnd(str, stripChars);
        StringUtils.stripToEmpty("abc ");
    }
}
