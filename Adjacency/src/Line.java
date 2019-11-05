public class Line {
    private Point start;
    private Point end;
    private float value;

    public Line(Point start, Point end, float value){
        this.start = start;
        this.end = end;
        this.value = value;
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
