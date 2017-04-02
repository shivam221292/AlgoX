/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dancinglinks;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
/**
 *
 * @author jack
 */
public class DancingLinks {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        List<String> lines = Files.readAllLines(Paths.get("f:\\SparseMatrix.txt"), Charset.defaultCharset());
        int colCount = lines.get(0).length();
        int rowCount = lines.size();
        boolean matrix[][] = new boolean[rowCount][colCount];
        int i = 0;
        for(String line: lines)
        {
            for(int j = 0; j < colCount; j++)
                if(line.charAt(j) == '1')
                    matrix[i][j] = true;
            i++;
        }
        SparseMatrix sparseMatrix = new SparseMatrix(matrix, rowCount, colCount);
        sparseMatrix.findExactCover();
        /*HeaderNode root = sparseMatrix.Root;
        HeaderNode column = (HeaderNode)root.R;
        while(column != root)
        {
            Node row = column.D;
            System.out.println("Column " + column.Column);
            while(row != column)
            {
                System.out.println("  Row Num " + row.Row);
                System.out.println("    Row.Left: " + row.L.Row + ", " + row.L.Column);
                System.out.println("    Row.Right: " + row.R.Row + ", " + row.R.Column);
                System.out.println("");
                row = row.D;
            }
            System.out.println("\n\n");
            column = (HeaderNode)column.R;
        }*/
    }
    
}
