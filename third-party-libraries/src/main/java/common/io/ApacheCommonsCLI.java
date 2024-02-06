package common.io;

import org.apache.commons.cli.*;

// Apache Commons CLI provides a simple API
// for presenting, processing and validating a Command Line Interface.
public class ApacheCommonsCLI {

    // 输出控制台的帮助文档, 使用Command CLI标准读取格式
    // usage: Command line help:
    // -h,--help             Prints this help
    // -u,--username <arg>   Username for connection.
    public static void main(String[] args) {
        Options options = getCommandOptions();
        try {
            final CommandLineParser parser = new GnuParser();
            final CommandLine cmd = parser.parse(options, args, true);
            if (cmd.hasOption("help")) {
                HelpFormatter help = new HelpFormatter();
                help.setWidth(80);
                help.printHelp("Command line help: ", options);
                return;
            }
            final String username = cmd.getOptionValue("username");
            System.out.println(username);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static Options getCommandOptions() {
        return new Options()
                .addOption("u", "username", true, "Username for connection.")
                .addOption("h", "help", false, "Prints this help");
    }

    static String getMandatoryOptionValue(final CommandLine cmd, final String name) {
        final String value = cmd.getOptionValue(name);
        if (value == null) {
            throw new RuntimeException(String.format("'%s' is missing use --help for help", name));
        }
        return value;
    }
}
