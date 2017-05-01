/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dancinglinks;

import java.util.HashMap;

/**
 *
 * @author jack
 */
public class BlockSizes {
    private HashMap<Integer, Integer> Sizes;
    //private int[] Sizes;
    //private int MaxBlockSize;
    private int Count;
    public BlockSizes(int[] blockSizes)
    {
        Sizes = new HashMap();
        Count = blockSizes.length;
        for(int i = 0; i < Count; i++)
        {
            Integer size = blockSizes[i];
            if(Sizes.containsKey(size))
                Sizes.put(size, Sizes.get(size) + 1);
            else
                Sizes.put(size, 1);
        }
    }
    public boolean containsSize(int size)
    {
        return Sizes.containsKey(size) && Sizes.get(size)>0;
    }
    public void removeBlockSize(int size)
    {
        if(containsSize(size))
        {
            Sizes.put(size, Sizes.get(size) - 1);
            Count--;
        }
    }
    public void addBlockSize(int size)
    {
        if(Sizes.containsKey(size))
        {
            Sizes.put(size, Sizes.get(size) + 1);
            Count++;
        }
    }
    public boolean isEmpty()
    {
        return Count <= 0;
    }
    public void print()
    {
        for(Integer i : Sizes.keySet())
            System.out.println("Key: " + i + ", Value: " + Sizes.get(i));
    }
}
