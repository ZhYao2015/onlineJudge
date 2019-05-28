/*
ID:l_coder1
LANG:JAVA
TASK:runround
*/

//package stage2_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class runround {
	
	public static int[] s;
	
	public static int create_arr(int num){
		s=new int[10];
		int r,i;
		for(i=10;i>0;i--){
			if(num/(int)Math.pow(10,i)!=0){
				break;
			}
		}
		int len=i+1;
		while(num!=0){
			r=num%10;
			s[i]=r;
			num=num/10;
			i--;
		}
		return len;
	}
	
	public static boolean test_all_visited(int v[],int len){
		for(int i=0;i<len;i++){
			if(v[i]==0){
				return false;
			}
		}
		return true;
	}
	
	
	public static boolean test_round(int num){
		
		int len=create_arr(num);
		
		
		for(int p=0;p<len-1;p++)
			for(int q=p+1;q<len;q++)
				if(s[p]==s[q])
					return false;
			
		
		int visited[]=new int[10];
		int i=0;
		while(visited[i]==0){
			visited[i]=1;
			i=(i+s[i])%len;
		}
		if(i==0&&test_all_visited(visited,len)){
			return true;
		}else{
			return false;
		}
		
	}
	
	public static void main(String args[]) throws IOException{
		BufferedReader f=new BufferedReader(new FileReader("runround.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));
		int N=Integer.parseInt((new StringTokenizer(f.readLine())).nextToken());
		boolean found=false;
		int i=N+1;
		while(!found){
				if(test_round(i)){
					found=true;
				}else{
					i++;
				}
		}
		out.println(Integer.toString(i));
		out.close();
	}
}
