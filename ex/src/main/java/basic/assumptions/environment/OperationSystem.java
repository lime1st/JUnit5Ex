package basic.assumptions.environment;

public class OperationSystem {

    private final String name;
    private final String architecture;

    public OperationSystem(String name, String architecture) {
        this.name = name;
        this.architecture = architecture;
    }

    public String getName() {
        return name;
    }

    public String getArchitecture() {
        return architecture;
    }
}
