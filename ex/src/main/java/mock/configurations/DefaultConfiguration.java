package mock.configurations;

public class DefaultConfiguration implements Configuration {

    /**
     * Constructor.
     *
     * @param configurationName
     */
    public DefaultConfiguration(String configurationName) {
    }

    /**
     * Getter method to get the sql that we want to execute.
     *
     * @return
     */
    @Override
    public String getSQL(String sqlString) {
        return null;
    }
}
