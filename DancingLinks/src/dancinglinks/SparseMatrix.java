/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dancinglinks;

/**
 *
 * @author jack
 */
public class SparseMatrix {
    HeaderNode Root;
    private Node O[];
    private boolean  BlockSizesUsed;
    private BlockSizes BlockSizes;
    private Node NodeMatrix[][];
    private HeaderNode Columns[];
    int RowCount;
    int ColCount;
    public SparseMatrix(boolean[][] matrix, int rowCount, int colCount, BlockSizes blockSizes)
    {
        BlockSizes = blockSizes;
        BlockSizesUsed = true;
        Root = new HeaderNode(-1, 0, null);
        O = new Node[rowCount];
        HeaderNode leftNode = Root;
        HeaderNode columns[] = new HeaderNode[colCount];
        for(int i = 0; i<colCount; i++)
        {
            HeaderNode column = new HeaderNode(i, rowCount, null);
            columns[i] = column;
            leftNode.setRight(column);
            column.setLeft(leftNode);
            if(i==colCount-1)
            {
                column.setRight(Root);
                Root.setLeft(column);
            }
            leftNode = column;
        }
        int setEltCount = 0;
        for(int i = 0; i<rowCount; i++)
        {
            setEltCount = 0;
            for(int j = 0; j<colCount; j++)
                if(matrix[i][j])
                    setEltCount++;
            if(blockSizes.containsSize(setEltCount))
            {
                for(int j = 0; j<colCount; j++)
                {
                    if(matrix[i][j])
                    {
                        Node cell = new Node(i, j, setEltCount);
                        columns[j].addRowNode(cell);
                    }
                }
            }
        }
    }
    
    
    public SparseMatrix(boolean[][] matrix, int rowCount, int colCount)
    {
        NodeMatrix = new Node[rowCount][colCount];
        RowCount = rowCount;
        ColCount = colCount;
        Columns = new HeaderNode[colCount];
        Root = new HeaderNode(-1, 0, null);
        O = new Node[rowCount];
        HeaderNode leftNode = Root;
        HeaderNode columns[] = new HeaderNode[colCount];
        for(int i = 0; i<colCount; i++)
        {
            HeaderNode column = new HeaderNode(i, rowCount, null);
            Columns[i] = column;
            columns[i] = column;
            leftNode.setRight(column);
            column.setLeft(leftNode);
            if(i==colCount-1)
            {
                column.setRight(Root);
                Root.setLeft(column);
            }
            leftNode = column;
        }
        for(int i = 0; i<rowCount; i++)
        {
            for(int j = 0; j<colCount; j++)
            {
                if(matrix[i][j])
                {
                    Node cell = new Node(i, j);
                    columns[j].addRowNode(cell);
                    NodeMatrix[i][j] = cell;
                }
            }
        }
    }
    
    public void findExactCover()
    {
        if(BlockSizesUsed)
            searchWithBlockSizes(0);
        else
            search(0);
    }
    public void searchWithBlockSizes(int k)
    {
        if(Root.R == Root && BlockSizes.isEmpty())
        {
            //Matrix empty, print contents of O
            for(int i = 0; i < k; i++)
                System.out.println(O[i].Row+1);
            System.out.println("\n\n");
        }
        else
        {
            HeaderNode c = getColumn();
            cover(c);
            Node r = c.D;
            while(r != c)
            {
                if(BlockSizes.containsSize(r.RowEltCount))
                {
                    O[k] = r;
                    BlockSizes.removeBlockSize(r.RowEltCount);
                    Node j = r.R;
                    while(j != r)
                    {
                        cover(j.C);
                        j = j.R;
                    }
                    searchWithBlockSizes(k + 1);
                    r = O[k];
                    c = r.C;
                    j = r.L;
                    BlockSizes.addBlockSize(r.RowEltCount);
                    while(j != r)
                    {
                        uncover(j.C);
                        j = j.L;
                    }
                }
                r = r.D;
            }
            uncover(c);
        }
    }
    public void search(int k)
    {
        System.out.println("\n\nk = " + k);
        if(Root.R == Root)
        {
            //Matrix empty, print contents of O
            for(int i = 0; i < k; i++)
                System.out.println(O[i].Row+1);
            System.out.println("");
        }
        else
        {
            HeaderNode c = getColumn();
            //System.out.println("getColumn: " + c.Column + ", Col Count: " + c.S);
            if(c.S > 0)
            {
                //System.out.println("Before Cover: ");
                //print();
                cover(c);
                //System.out.println("After Cover: ");
                //print();
                Node r = c.D;
                while(r != c)
                {
                    O[k] = r;
                    Node j = r.R;
                    while(j != r)
                    {
                        //System.out.println("Cover - j.C: " + j.C.Column + ", Col Count: " + j.C.S);
                        cover(j.C);
                        //print();
                        j = j.R;
                    }
                    search(k + 1);
                    r = O[k];
                    c = r.C;
                    j = r.L;
                    while(j != r)
                    {
                        //System.out.println("Uncover - j.C: " + j.C.Column + ", Col Count: " + j.C.S);
                        uncover(j.C);
                        //print();
                        j = j.L;
                    }
                    r = r.D;
                }
                //System.out.println("Before Uncover: ");
                //print();
                uncover(c);
                //System.out.println("After Uncover: ");
                //print();
            }
        }
    }
    
    private HeaderNode getColumn()
    {
        HeaderNode column = (HeaderNode)Root.R;
        HeaderNode minSizeColumn = column;
        while(column != Root)
        {
            if(column.S<minSizeColumn.S)
                minSizeColumn = column;
            column = (HeaderNode)column.R;
        }
        return minSizeColumn;
    }
    
    private void cover(HeaderNode c)
    {
        c.R.L = c.L;
        c.L.R = c.R;
        Node i = c.D;
        while(i != c)
        {
            Node j = i.R;
            while(j != i)
            {
                j.D.U = j.U;
                j.U.D = j.D;
                j.C.S--;
                j = j.R;
            }
            i = i.D;
        }
    }
    
    private void uncover(HeaderNode c)
    {
        Node i = c.U;
        while(i != c)
        {
            Node j = i.L;
            while(j != i)
            {
                j.D.U = j;
                j.U.D = j;
                j.C.S++;
                j = j.L;
            }
            i = i.U;
        }
        c.R.L = c;
        c.L.R = c;
    }
    
    public void print()
    {
        for(HeaderNode obj : Columns)
        {
            System.out.print("\"" + obj.Name + " \nUp: " + obj.U.Name + ", Down: "+ obj.D.Name +" \",");
        }
        System.out.println("");
        for(int i = 0; i < RowCount; i++)
        {
            for(int j = 0; j < ColCount; j++)
            {
                if(NodeMatrix[i][j] != null)
                {
                    Node obj = NodeMatrix[i][j];
                    System.out.print("\"" + obj.Name + " \nUp: " + obj.U.Name + ", Down: "+ obj.D.Name +" \"");
                }
                if(j != ColCount - 1)
                    System.out.print(",");
                else
                    System.out.println("");
            }
        }
    }
    
    /*public void print()
    {
        int highestRow = 0;
        int highestColumn = 0;
        HeaderNode column = (HeaderNode)Root.R;
        while(column != Root)
        {
            Node row = column.D;
            while(row != column)
            {
                if(row.Row>highestRow)
                    highestRow = row.Row;
                if(row.Column>highestColumn)
                    highestColumn = row.Column;
                row = row.D;
            }
            column = (HeaderNode)column.R;
        }
        boolean matrix[][] = new boolean[highestRow+1][highestColumn+1];
        column = (HeaderNode)Root.R;
        while(column != Root)
        {
            Node row = column.D;
            while(row != column)
            {
               matrix[row.Row][row.Column] = true;
               row = row.D;
            }
            column = (HeaderNode)column.R;
        }
        for(int i = 0; i <= highestRow; i++)
        {
            for(int j = 0; j <= highestColumn; j++)
                System.out.print((matrix[i][j]?1:0) + ",");
            System.out.println();
        }
    }*/
}
