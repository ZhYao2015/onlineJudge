/*
ID:l_coder1
LANG:JAVA
TASK:concom
*/

//package stage2_3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class concom {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader f=new BufferedReader(new FileReader("concom.in"));
		PrintWriter out=new PrintWriter(new FileWriter("concom.out"));
		
		int mat[][]=new int [105][105];
		//have a controlled successor
		int ctrl[][]=new int [105][105];
		
		for(int i=0;i<100;i++){
			mat[i][i]=0;
			ctrl[i][i]=1;
		}
				
		int N=Integer.parseInt((new StringTokenizer(f.readLine()).nextToken()));
		String read="";
		while((read=f.readLine())!=null){
			StringTokenizer st=new StringTokenizer(read);
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			int w=Integer.parseInt(st.nextToken());
			//weight
			mat[x-1][y-1]=w;
			//distance
			if(w>50){
				ctrl[x-1][y-1]=1;
			}
			
		}
		
		//extensible?
		boolean  tag=true;
		
		//when computing a closure, the outter while loop is always requiered
		//and a way of measuring the state change, consider what makes a state change
		while(tag){
			//still matrix multiply
			//closure method is not suitable
			tag=false;
			
			for(int i=0;i<100;i++){
				//for each company
				for(int j=0;j<100;j++){
					
					if(ctrl[i][j]!=1){
						//j is currently not controlled by i
						int  sum=0; // the amount of stock of j indirectly occupied by i
						for(int k=0;k<100;k++){
							if(ctrl[i][k]==1){
								sum+=mat[k][j];
							}
						}
						
						if(sum>50){
							ctrl[i][j]=1;
							tag=true;
						}
					}
				}
			}
		}
		
		for(int i=0;i<100;i++){
			for(int j=0;j<100;j++){
				if(i!=j&&ctrl[i][j]==1){
					out.print(i+1);
					out.print(" ");
					out.println(j+1);
				}
			}
		}
		
		
		
		out.close();
	}
}
