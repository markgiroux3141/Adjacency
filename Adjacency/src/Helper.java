import java.util.List;

public class Helper {
    public static float[][] convertCsvToMatrix(CsvFile csvFile){
        float[][] matrix = new float[csvFile.getBody().size()][];
        for(int i=0;i<matrix.length;i++){
            matrix[i] = new float[csvFile.getBody().get(i).length - 1];
            for(int n=0;n<matrix[i].length;n++){
                matrix[i][n] = Float.parseFloat(csvFile.getBody().get(i)[n + 1]);
            }
        }
        return matrix;
    }

    public static float getDistance(Point p1, Point p2){
        return (float)Math.sqrt(((p2.x - p1.x) * (p2.x - p1.x)) + ((p2.y - p1.y) * (p2.y - p1.y)));
    }
}
