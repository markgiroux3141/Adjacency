import java.util.List;

public class CsvFile {
    private String[] header;
    private List<String[]> body;

    public CsvFile(List<String[]> input){
        header = input.get(0);
        input.remove(0);
        body = input;
    }

    public String[] getHeader() {
        return header;
    }

    public void setHeader(String[] header) {
        this.header = header;
    }

    public List<String[]> getBody() {
        return body;
    }

    public void setBody(List<String[]> body) {
        this.body = body;
    }
}
