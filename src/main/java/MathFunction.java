public class MathFunction {
    private String functionName;
    private String functionCode;

    public MathFunction(String functionName, String functionCode) {
        this.functionName = functionName;
        this.functionCode = functionCode;
    }

    public String getFunctionName() {
        return functionName;
    }

    public String getFunctionCode() {
        return functionCode;
    }
}
