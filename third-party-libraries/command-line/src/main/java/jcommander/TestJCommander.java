package jcommander;

public class TestJCommander {

    // Usage: java -jar name.jar [options]
    //  Options:
    //    -m, -migrate
    //      Use the specified file
    //    -o, -options
    //      Use the specified file
    //    -v, -version
    //      Print product information.
    //      Default: false
    public static void main(String[] args) {
        JCommandParser commandParser = new JCommandParser();
        commandParser.parse(args);
    }
}
