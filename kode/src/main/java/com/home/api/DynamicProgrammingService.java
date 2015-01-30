/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.api;

/**
 *
 * @author rtongbram
 */
public class DynamicProgrammingService {
    
    /**
     * Find the number of ways we can make the change of 
     * the amount value, using the coin denominations given as coins.
     * You have a large amount of each of the coins, and we can include a coin , or not to
     * build up the value.
     * Let C(i,j) => the number of ways we can make change of i value, using jth coin
     * then , 
     *  c(i,j) = c(i, j-1)+c(i-coins[j], j)
     * 
     * Here : c(i,j-1) : is the number of ways we can make i without including jth coin
     * and  : c(i-coins[j], j) : is the number of ways we can make i by including jth coin.
     * 
     * The reason we are subtracting coins[j] from i is because, since we have considered j, we know 
     * there is at least 1 j and so we are setting aside the value ( which the jth coin will contribute to the overall value).
     * And then we try to find the number of ways (including the coin j) on the remaining amount.
     * 
     * And then finally we can return c(value, coins.length).
     * 
     * 
     */
    public static int numWaysCoinChange(int value, int[] coins){
        int[][] table = new int[value+1][coins.length];
        table[0][0]=1;
        
        for(int i =1 ; i <=value;i++){
            table[i][0]=0;
        }
        
        for(int j=1;j<coins.length; j++){
            table[0][j]=1;
        }
        
        for (int i =1; i <= value; i++){
            for (int j=1;j<coins.length;j++){
                
                    int dontConsider = table[i][j-1];
                    int consider = 0;
                    if(i >= coins[j]){
                        consider=table[i-coins[j]][j];
                    }
                    table[i][j]= dontConsider+consider;
                
                
            }
        }
        
        return table[value][coins.length-1];
    }
    
    
    
    /**
     * Find the minimun number of coins which can make value
     * Let c(i,j) => minimum number of coins to sum up to i using j denominations
     * then, 
     *  c(i,j) = min ( c(i,j-1), c(i-coins[j],j)+1)
     * 
     * the argument is similar to that of above, we try to find the minimum of the 2 ways
     *  1) we dont consider the coin j c(i,j-1)
     *  2) we consider the coin j : c(i-coins[j],j) +1 .  Here we add a 1 because since we are considering the
     * coin j, we have to increment the number of coins by 1.
     *
     */
    public static int minCoinChange(int value, int[] coins){
        int[][] table = new int[value+1][coins.length];
        table[0][0]=1;
        
        for(int i =1 ; i <=value;i++){
            table[i][0]=0;
        }
        
        for(int j=1;j<coins.length; j++){
            table[0][j]=1;
        }
        
        for (int i =1; i <= value; i++){
            for (int j=1;j<coins.length;j++){
                
                    int dontConsider = table[i][j-1];
                    int consider = 0;
                    if(i >= coins[j]){
                        consider=table[i-coins[j]][j];
                        table[i][j]= Math.min(dontConsider,consider+1);
                        
                    }
                    
                    
                
                
            }
        }
        
        return table[value][coins.length-1];
    }
    
    
    
    /**
     * Returns true if string s3 is an interleaving of string s1 and s2. 
     * It means s3 is made up of letters of s1 and s2 and the orders are preserved.
     * s1 = abc , s2 = def , and s3=adebfc is true.
     * let : 
     * a[i][j] => true if s3[0 to i+j] can be formed by s1[0 to i] and s2[0 toj]
     * s1 : 0 1 2 .... i
     * s2 : 0 1 2 .... j
     * s3 : 0 1 2 .......... i+j
     * a[i][j]=(s3[i+j]==s1[i] && a[i-1][j]) ||  (s3[i+j]== s2[j] && a[i][j-1])
     * There are 2 conditions for a[i][j] to be true. 
     * 1) if the last char of s3 which is s3[i+j] is equal to the last char or s1  which is s1[i]
     *      then we are considering the last char of s3 to be the last char of s1
     *      and so we need to make sure that remaining chars of s1 (s1[0 to i-1]) and all chars of s2( s2[0 to j]) 
     *      forms an interleaving string.
     *      In other words, a[i-i][j] needs to be true.
     * 
     * 2) if the last char of s3 ( s3[i+j] ) is equal to the last char of s2 ( s2[j])
     *      then we are considering the last char of s3 to be the last char of s2.
     *      So we need to make sure that remaining chars of s2 (s2[0 to j-1]) and all of s1 ( s1[0 to i]) 
     *      forms an interleaving string. 
     *      Or, a[i][j-1] needs to be true.
     * 
     */
    public static boolean isInterleaving(String s1, String s2, String s3){
        
        boolean [][] a = new boolean[s1.length()+1][s2.length()+1];
        
        a[0][0]= true;
        a[1][0]= (s3.charAt(0)==s1.charAt(0));
        a[0][1]= (s3.charAt(0)==s2.charAt(0));
        for(int i =1; i< s1.length()+1; i++){
            for (int j = 1; j< s2.length()+1; j++){
                a[i][j]=(s3.charAt(i+j-1)==s1.charAt(i-1) && a[i-1][j]) 
                        ||  (s3.charAt(i+j-1)== s2.charAt(j-1) && a[i][j-1]);
            }
        }
        
        return a[s1.length()][s2.length()];
    }
    
    
    /**
     * Given strings S and T , return the number of distinct subsequences of T in S.
     *  
     * S = "rabbbit" , T = "rabbit" , return 3
     * 
     * Let w[i][j] => number of distinct subsequences formed by S(0,i) and T(0,j)
     * w[i][j] = w[i-1][j-1]+w[i-1][j] if S[i]==T[j]
     *           w[i-1][j] otherwise.
     * 
     * So, when we consider i in S and j in T, if S(i) and T(j) are similar, we have 2 options:
     * 1) we consider S(i) : 
     *      if we consider s(i) , then its the value of w[i-1][j-1]. The reason is because if
     *      S(i) and T(j) are same chars, it does not increase the number of ways to form 
     *      "distict" subsequence ( in S ) from the prevoius value which was formed 
     *      from S(0,i-1) and T(0,j-1).
     * 
     * 2) we dont consider S(i) :
     *      When we dont consider s(i), we take into account the number of distinct subsequences formed
     *      by taking s(0, i-1) and t(0,j) , which is w[i-1][j]
     * 
     * 
     * But if S(i) is not equal to T(j), we cannot consider S(i) at all, 
     * so it leaves the value w[i-1][j]
     * 
     * 
     * One other way of solving this is take 2 examples , write them down in rows and columns
     * then try to find the value of the slots by looking at the values of string and see what value
     * will fill up the slot . For example , when we consider S as cat and T as ca ( 2,3 in the matrix)
     * we know that there's only 1 way of forming ca from cat so write 1. After filling out the matrix,
     * look for patterns of how a specific slot gets calculated from previous neighboring values and formulate
     * the recursion.
     *         c a t t a t  => S
     *      1  1 1 1 1 1 1
     *    c 0  1 1 1 1 1 1
     *    a 0  0 1 1 1 2 2
     *    t 0  0 0 1 2 2 4
     *    | 
     *    T
     */
    public static int distinctSubsequences(String s, String t){
        
        int[][] w = new int[s.length()+1][t.length()+1];
        
        w[0][0]=1;
        for(int i = 1 ; i < s.length(); i++){
            w[i][0]=1;
        }
        for(int j = 1; j < t.length(); j++){
            w[0][j]=0;
        }
        for(int i = 1; i < s.length()+1; i++){
            for (int j = 1 ; j < t.length()+1; j++){
                if(s.charAt(i-1)==t.charAt(j-1)){
                    w[i][j]= w[i-1][j-1]+w[i-1][j];
                }
                else{
                    w[i][j]= w[i-1][j];
                }
                
            }
        }
        
        return w[s.length()][t.length()];
    }
    
    /**
     * Given an array , find the mimimum difference between 2 partitions.
     * 
     * Let p[i][j] => true if there is a subset in values[0 to j] which sums up to i
     * 
     * so , p[i][j]= p[i][j-1] || p[i-Vj][j-1]   //{ Vj = values[j] }
     * 
     * p[i][j-1] => condition where we dont consider Vj, if there's already a subset in the 
     *              array ( 0 to j-1), then p[i][j] is also true
     * 
     * p[i-Vj][j-1] => condition where we considered Vj, we want to make sure we have a subset in 
     *              array ( 0 to j-1) that sums upto i-Vj. i-Vj because since we consider Vj , we need to 
     *              set aside space for Vj from i so when we add it it sums up to i.
     * 
     * 
     * Once we have p[i][j], we can then check from p[sum/2][n] by going both sides ( incrementing and decrementing)
     * and see if its true. whichever comes first is the closest difference between the 2 groups.
     * 
     */
    public static int integerPartition(int[] values){
        int result=Integer.MAX_VALUE;
        int sum = 0;
        for(int val : values){
            sum+=val;
        }
        
        boolean [][] p = new boolean[sum+1][values.length+1];
        p[0][0]=true;
        p[1][0]= false;
        
        for(int i = 1 ; i < sum+1; i++){
            p[i][0]=false;
        }
        
        for(int i = 0 ; i< sum+1 ; i++){
            for(int j = 1 ; i < values.length+1 ; i++){
                // we are using v[j-1] and not v[j] bcos in array index starts from 0
                // and not from 1. so the jth elem is v[j-1]
                p[i][j]=  p[i][j-1] || (p[i-values[j-1]][j-1]); 
            }
        }
        
        // Now that the boolean array is calculated find the min difference from sum/2
        int halfSum = sum/2;
        for(int i = halfSum, j = halfSum; i > 0; i--, j++){
            if(p[i][values.length]){
                int diff = halfSum-i;
                return 2*diff;
            }
            if(p[j][values.length]){
                int diff = j-halfSum;
                return 2*diff;
            }
        }
        return result;
    }
    
}
