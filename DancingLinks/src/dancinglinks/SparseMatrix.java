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
    public SparseMatrix(boolean[][] matrix, int rowCount, int colCount)
    {
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
        for(int i = 0; i<rowCount; i++)
        {
            for(int j = 0; j<colCount; j++)
            {
                if(matrix[i][j])
                {
                    Node cell = new Node(i, j);
                    columns[j].addRowNode(cell);
                }
            }
        }
    }
    private Node O[];
    
    public void findExactCover()
    {
        search(0);
    }
    
    public void search(int k)
    {
        if(Root.R == Root)
        {
            //Matrix empty, print contents of O
            for(int i = 0; i < k; i++)
                System.out.println(O[i].Row);
            System.out.println("");
        }
        else
        {
            HeaderNode c = getColumn();
            cover(c);
            Node r = c.D;
            while(r != c)
            {
                O[k] = r;
                Node j = r.R;
                while(j != r)
                {
                    cover(j.C);
                    j = j.R;
                }
                search(k + 1);
                r = O[k];
                c = r.C;
                j = r.L;
                while(j != r)
                {
                    uncover(j.C);
                    j = j.L;
                }
                r = r.D;
            }
            uncover(c);
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
}
