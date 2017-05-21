/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dancinglinks;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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
    public static int BlockSizesArr[] = new int[]{4,2,1,1,1}; 
    public static int Sets[] = {2, 4, 8, 5};
    public static int ElementCount = 4;
    public static IActionCaller ActionCaller;
    public static void run(){
        // TODO code application logic here

        BlockSizes blockSizes = new BlockSizes(BlockSizesArr);
        //blockSizes.print();
        int colCount = ElementCount;
        int rowCount = Sets.length;
        boolean matrix[][] = new boolean[rowCount][colCount];
        int i = 0;
        for(int set : Sets)
        {
            String line = Integer.toBinaryString(set);
            int k = colCount - 1;
            for(int j = line.length()-1; j >= 0; j--)
            {
                if(line.charAt(j) == '1')
                    matrix[i][k] = true;
                k--;
            }
            i++;
        }
        //System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream("f:\\DancingLinksOutputBlockSize.txt")), true));
        SparseMatrix.ActionCaller = ActionCaller;
        SparseMatrix sparseMatrix = new SparseMatrix(matrix, rowCount, colCount, blockSizes);
        //SparseMatrix sparseMatrix = new SparseMatrix(matrix, rowCount, colCount);
        //sparseMatrix.print();
        sparseMatrix.findExactCover();
        //sparseMatrix.print();
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
