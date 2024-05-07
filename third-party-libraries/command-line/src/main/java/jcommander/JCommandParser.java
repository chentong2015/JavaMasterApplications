package jcommander;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.Parameters;

import java.text.MessageFormat;

@Parameters(separators = "=")
public class JCommandParser {

    private static final String VERSION = "-version";
    private static final String V = "-v";
    private static final String OPTIONS = "-options";
    private static final String O = "-o";
    private static final String MIGRATE = "-migrate";
    private static final String M = "-m";
    private static final String APP_NAME_INFO_MSG = "java -jar {0}.jar";

    private boolean validArgs;

    @Parameter(names = {O, OPTIONS}, validateWith = OptionsValidator.class, description = "Use the specified file")
    private String importConfigFileLocation;

    @Parameter(names = {M, MIGRATE}, validateWith = OptionsValidator.class, description = "Use the specified file")
    private String migrationConfigFileLocation;

    @Parameter(names = {V, VERSION}, description = "Print product information.")
    private boolean isVersion;

    public String getImportConfigFileLocation() {
        return importConfigFileLocation;
    }

    public String getMigrationConfigFileLocation() {
        return migrationConfigFileLocation;
    }

    public boolean isValidArgs() {
        return validArgs;
    }

    public void parse(String[] args) {
        JCommander jCommander = new JCommander(this);
        jCommander.setProgramName(MessageFormat.format(APP_NAME_INFO_MSG, "name"));
        try {
            jCommander.parse(args);
            validateParamDependencies();
        } catch (ParameterException ex) {
            System.err.println(ex.getMessage());
            jCommander.usage();
        }
    }

    private void validateParamDependencies() {
        if (isVersion) {
            System.out.println("Print version");
        }
        System.out.println(importConfigFileLocation);
        System.out.println(migrationConfigFileLocation);
    }
}
