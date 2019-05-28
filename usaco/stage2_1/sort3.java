/*
 ID:l_coder1
 LANG:JAVA
 TASK:sort3
*/

//package stage2_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class sort3 {

	private static int swap=0;
	private static int []l=new int[1000];
	
	public static void swap(int i,int j){
		int temp=l[i];
		l[i]=l[j];
		l[j]=temp;
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader f=new BufferedReader(new FileReader("sort3.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));
		int N=Integer.parseInt((new StringTokenizer(f.readLine())).nextToken());
		for(int i=0;i<N;i++){
			StringTokenizer s=new StringTokenizer(f.readLine());
			l[i]=Integer.parseInt(s.nextToken());
		}
		int c1=0,c2=0,c3=0;
		for(int i=0;i<N;i++){
			if(l[i]==1){
				c1++;
			}
			if(l[i]==2){
				c2++;
			}
			if(l[i]==3){
				c3++;
			}
		}
		
		int step=0;
		for(int i=0;i<c1;i++){
			if(l[i]==2){
				boolean flag=false;
				for(int j=c1;j<c1+c2;j++){
					if(l[j]==1){
						swap(i,j);
						step++;
						flag=true;
						break;
					}
				}
				if(!flag){
					for(int k=c1+c2;k<N;k++){
						if(l[k]==1){
							swap(i,k);
							step++;
							flag=true;
							break;
						}
					}
				}
			}
			
			if(l[i]==3){
				boolean flag=false;
				for(int j=c1+c2;j<N;j++){
					if(l[j]==1){
						swap(i,j);
						step++;
						flag=true;
						break;
					}
				}
				if(!flag){
					for(int k=c1;k<c1+c2;k++){
						if(l[k]==1){
							swap(i,k);
							step++;
							flag=true;
							break;
						}
					}
				}
			}
		}
		
		for(int i=c1;i<c1+c2;i++){
			if(l[i]==3){
				for(int j=c1+c2;j<N;j++){
					if(l[j]==2){
						swap(i,j);
						step++;
						break;
					}
				}
			}
		}
		
		out.println(Integer.toString(step));
		out.close();
		
	}

}
