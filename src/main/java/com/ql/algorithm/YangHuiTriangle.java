package com.ql.algorithm;

/**
 * @Description TODO:
 * @Author qiuliang
 * @Time 2020-02-06 09:58
 * @Version 1.0
 **/
public class YangHuiTriangle {


    public static void main(String[] args) {
//        System.out.println(generate(5));
        int n = 5;
        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=i; j++)   //每层的数据个数
            {

                printf("%2d ",triangle(i,j));
            }
            printf("\n","");
        }
    }

    private static void printf(String s,Object res) {
        System.out.printf(s,res);
    }


    /**
     * 非递归版本
     * @param n
     * @return
     */
    static int[][] generate(int n){
        int[][] a = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<=i;j++){
                if(i==j || j==0){
                    a[i][j]=1;
                }else{
                    a[i][j] = a[i - 1][j - 1] + a[i - 1][j];
                }
            }
        }
        return a;
    }

    /**
     * 递归版本
     * @param i
     * @param j
     * @return
     */
    static int triangle(int i,int j) {
        if(j == 1 || i == j) {
            return 1;
        }
        return (triangle(i-1,j-1)+triangle(i-1,j));
    }
}
