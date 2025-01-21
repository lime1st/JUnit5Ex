package mock.configurations;

public class MockConfiguration implements Configuration {

    /**
     * Sets the sql query.
     *
     * @param sqlString
     */
    public void setSQL(String sqlString) {
    }

    /**
     * Gets the sql query.
     *
     * @param sqlString
     * @return
     */
    @Override
    public String getSQL(String sqlString) {
        return "";
    }
}
