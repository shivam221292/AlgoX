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
public class MainClass implements IActionCaller {
    @Override
    public void run(int[] exactCover){
        //write the code for what you need to do with exact cover
        int sets[] = new int[]{26, 4, 1, 5, 27, 11, 16};
        for(int i:exactCover)
            System.out.println(sets[i]);
    }
    
    public static void main(String args[]){
        MainClass obj = new MainClass();
        DancingLinks.ActionCaller = obj;
        DancingLinks.BlockSizesArr = new int[]{3,1, 1};
        DancingLinks.ElementCount = 5;
        DancingLinks.Sets = new int[]{26, 4, 1, 5, 27, 11, 16};
        DancingLinks.run();
    }
    
}