/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dancinglinks;

import java.util.ArrayList;

/**
 *
 * @author jack
 */
public class HeaderNode extends Node
{
    /**
     * Shows the size of the column if used as a column object.
     * Empty if used as a start node for columns list.
     */
    public int S;
    /**
     * Identifier name for the column.
     */
    public String N;
    private Node LastRowNode;
    private Node RowNodes[];
    public HeaderNode(int columnNum, int rowCount, Node left, Node up, Node right, 
            Node down, HeaderNode column, String columnName)
    {
        super(-1, columnNum, left, up, right, down, column);
        S = 0;
        N = columnName;
        RowNodes = new Node[rowCount];
        LastRowNode = this;
    }
    public HeaderNode(int columnNum, int rowCount, String columnName)
    {
        super(-1, columnNum);
        S = 0;
        N = columnName;
        N = columnNum + "";
        RowNodes = new Node[rowCount];
        LastRowNode = this;
    }
    public Node getRowNodeByNum(int rowNum)
    {
        return RowNodes[rowNum];
    }
    public void addRowNode(Node rowNode)
    {
        LastRowNode.setDown(rowNode);
        rowNode.setUp(LastRowNode);
        rowNode.setC(this);
        rowNode.setDown(this);
        LastRowNode = rowNode;
        RowNodes[rowNode.Row] = rowNode;
        S++;
        HeaderNode rightColumn = (HeaderNode)this.R;
        while(rightColumn!=this)
        {
            if(rightColumn.Column!=-1 && rightColumn.getRowNodeByNum(rowNode.Row)!=null)
                break;
            rightColumn = (HeaderNode)rightColumn.R;
        }
        Node adjacentRightRow = rightColumn.getRowNodeByNum(rowNode.Row);
        adjacentRightRow.L.setRight(rowNode);
        rowNode.setLeft(adjacentRightRow.L);
        rowNode.setRight(adjacentRightRow);
        adjacentRightRow.setLeft(rowNode);
    }
}
