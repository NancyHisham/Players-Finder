import java.util.Scanner;
interface IPlayersFinder {
    /**
     * Search for players locations at the given photo
     * @param photo
     *     Two dimension array of photo contents
     *     Will contain between 1 and 50 elements, inclusive
     * @param team
     *     Identifier of the team
     * @param threshold
     *     Minimum area for an element
     *     Will be between 1 and 10000, inclusive
     * @return
     *     Array of players locations of the given team
     */
    int[] findPlayers(int i , int j , char[][] arr3 , int a ,int x , int y,int counter);
    int[][] sortarr(int[][] arr ,int x);
    void dashfun(int i , int j , char[][] arr3 , int a ,int x , int y,int counter);
  
}
public class PlayersFinder {
  
    
    static int k=0,g=0;
    static int area=4;
    static int XMax;
    static int XMin;
    static int YMax;
    static int YMin;
    public static void dashfun(int i , int j , char[][] arr3 , int a ,int x , int y,int counter) {
 
            if(i<arr3.length-1 && arr3[i+1][j]==a+'0') {
                
                if(XMax<i+1) { XMax=i+1; }
                arr3[i+1][j]='-';
                area+=4;
                
            dashfun(i+1, j , arr3 ,a ,x ,y ,counter);}
            if(i>0 && arr3[i-1][j]==a+'0') {
                
                if(XMin>i-1) { XMin=i-1; }
                arr3[i-1][j]='-';
                area+=4;
            dashfun(i-1, j , arr3 ,a ,x, y ,counter);}
            
            if(j<arr3[0].length-1 && arr3[i][j+1]==a+'0') {
                
                if(YMax<j+1) { YMax=j+1; }
                arr3[i][j+1]='-';
                area+=4;
            dashfun(i, j+1 , arr3 ,a, x, y ,counter);}
            
            if(j>0 && arr3[i][j-1]==a+'0') {
                
                if(YMin>j-1) { YMin=j-1; }
                arr3[i][j-1]='-';
                area+=4;
            dashfun(i, j-1 , arr3 ,a, x ,y ,counter);}
            
        
            
    }
    public static int[] findPlayers(int i , int j , char[][] arr3 , int a ,int x , int y,int counter){
        
        dashfun(i, j , arr3 ,a, x ,y ,counter);
        int [] ans =new int[2];
        if (area >= counter) {
            ans[0] = XMax + XMin +1 ;
            ans[1] = YMax + YMin +1 ;
            return ans;
        } 
        
        else {
            ans[0] = -1;
            ans[1] = -1;
            return ans;
        }
      
    }
    
    public static int[][] sortarr(int[][] arr ,int x){
        int i=0 , j=0 ,temp1=0,temp2=0 ,temp=0;
        for(i=0;i<x;i++) {
            for(j=i+1;j<x;j++) {
                if(arr[i][0]>arr[j][0]) {//(3,5),(2,4)
                    temp1=arr[j][0];   //temp1=2
                    temp2=arr[j][1];    //temp2=4
                    arr[j][0]=arr[i][0]; //aarr[1][0]=3
                    arr[j][1]=arr[i][1]; //arr[1][1]=5
                    arr[i][0]=temp1;     //arr[0][0]=2
                    arr[i][1]=temp2;     //arr[0][1]=4        [2,4][3,5]
                }
                else if(arr[i][0]==arr[j][0]) { //(3,5),(3,2)
                    if(arr[i][1]>arr[j][1]) {
                        temp=arr[j][1]; //2
                        arr[j][1]=arr[i][1];
                        arr[i][1]=temp;
                    }
                }
            }
        }
        
        return arr;
        
        
    }
         
            
    public static void main(String[] args) {
int x, y ,i=0 ,j=0,counter=0,count1=0,count2=0,count3=0;  
        
        Scanner input = new Scanner(System.in);
        String sin =input.nextLine();
        String[] arr1 = sin.split(", ");
         x= Integer.parseInt(arr1[0]);
         y= Integer.parseInt(arr1[1]);
       
         String[] arr2 =new String[x];
         for ( i = 0; i < x; i++){
             arr2[i] = input.nextLine();}
         x= arr2.length;
         y= arr2[0].length();
          char[][]arr3=new char[x][y];
         for (i=0;i<x;i++){
             for (j=0;j<y;j++){
                 arr3[i][j]=arr2[i].charAt(j);
             }
         }
       
         int a , b ,f=0,p=0 ;
         int[][] d2array=new int[100][2];
         a=input.nextInt();
         b=input.nextInt();
 
        for(i=0;i<arr3.length;i++) {
            for(j=0;j<arr3[0].length;j++) {
                if(arr3[i][j]==a+'0') { 
                    arr3[i][j]='-';
                    area=4;
                    XMax=i;
                    YMin=j;
                    XMin=i;
                    YMax=j;
                    int[] ans2=findPlayers(i, j , arr3 ,a ,x, y ,b);
                     if(ans2[1]!=-1 ){
                        d2array[f][p]=ans2[1];
                        d2array[f][p+1]=ans2[0];
                        counter++;
                        f++; 
                 
                     }
                }
            }
    }
        sortarr(d2array,counter);

        System.out.print("[");
        for(i=0;i<counter;i++) {
                System.out.print("("+d2array[i][0]+", "+d2array[i][1]+")");
                if(i!=counter-1){
                    System.out.print(", ");
                
            }
        }System.out.println("]");
        

    input.close();
    }
}