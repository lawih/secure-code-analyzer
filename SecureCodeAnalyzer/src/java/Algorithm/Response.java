package Algorithm;

public class Response {

    String wrongCode;
    String recomendation;
    String solution;
    String type;
    int line;

    public String getWrongCode() {
        return wrongCode;
    }

    public void setWrongCode(String wrongCode) {
        this.wrongCode = wrongCode;
    }

    public String getRecomendation() {
        return recomendation;
    }

    public void setRecomendation(String recomendation) {
        this.recomendation = recomendation;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public Response(String wrongCode, String recomendation, String solution, String type, int line) {
        super();
        this.wrongCode = wrongCode;
        this.recomendation = recomendation;
        this.solution = solution;
        this.type = type;
        this.line = line;
    }

    @Override
    public String toString() {
        return "Response [wrongCode=" + wrongCode + ", recomendation=" + recomendation + ", solution=" + solution
                + ", type=" + type + ", line=" + line + "]";
    }

}
