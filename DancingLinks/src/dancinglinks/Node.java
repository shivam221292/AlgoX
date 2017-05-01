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
public class Node {
    //public boolean Val;
    public Node L;
    public Node U;
    public Node R;
    public Node D;
    public HeaderNode C;
    public int Row;
    public int Column;
    public int RowEltCount;
    public char Name;
    static char x = 'A';
    public Node(int rowNum, int columnNum, int rowEltCount)
    {
        Name = x;
        x++;
        if(x == 91)
            x = 'a';
        Row = rowNum;
        Column = columnNum;
        RowEltCount = rowEltCount;
        //Val = val;
        L = this;
        U = this;
        R = this;
        D = this;
        C = null;
    }
    public Node(int rowNum, int columnNum)
    {
        Name = x;
        x++;
        if(x == 91)
            x = 'a';
        Row = rowNum;
        Column = columnNum;
        //Val = val;
        L = this;
        U = this;
        R = this;
        D = this;
        C = null;
    }
    public Node(int rowNum, int columnNum, Node left, Node up, Node right, Node down, HeaderNode column)
    {
        Row = rowNum;
        Column = columnNum;
        //Val = val;
        L = left;
        U = up;
        R = right;
        D = down;
        C = column;
    }
    public void setLeft(Node left)
    {
        L = left;
    }
    public void setUp(Node up)
    {
        U = up;
    }
    public void setRight(Node right)
    {
        R = right;
    }
    public void setDown(Node down)
    {
        D = down;
    }
    public void setC(HeaderNode node)
    {
        C = node;
    }
    public void removeLeft()
    {
        L = null;
    }
    public void removeUp()
    {
        U = null;
    }
    public void removeRight()
    {
        R = null;
    }
    public void removeDown()
    {
        D = null;
    }
}
