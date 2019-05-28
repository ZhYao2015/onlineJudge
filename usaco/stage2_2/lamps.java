/*
ID:l_coder1
LANG:JAVA
TASK:lamps
*/
//package stage2_2;
//Initialization restoration and close file stream!!!
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class lamps {
	
	public static void main(String args[]) throws IOException{
		BufferedReader f=new BufferedReader(new FileReader("lamps.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));
		int N=Integer.parseInt((new StringTokenizer(f.readLine())).nextToken());
		int C=Integer.parseInt((new StringTokenizer(f.readLine())).nextToken());
		
		int [] lamp=new int[N];
		
		for(int i=0;i<lamp.length;i++){
			lamp[i]=1;
		}
		
		int [] on_off_list=new int[N];
		
		List<String> solution=new ArrayList<String>();
		
		StringTokenizer st_on=new StringTokenizer(f.readLine());
		int index=Integer.parseInt(st_on.nextToken());
		// 0 - undefined, 1 - on, -1 - off
		// stringtokenizer nexttoken cannot be reversable
		if(index==-1){
			
		}else{
			do{
				on_off_list[index-1]=1;
			}while((index=Integer.parseInt(st_on.nextToken()))!=-1);
		}
		
		StringTokenizer st_off=new StringTokenizer(f.readLine());
		
		index=Integer.parseInt(st_off.nextToken());
		
		if(index==-1){
			
		}else{
			do{
					on_off_list[index-1]=-1;
				
			}while((index=Integer.parseInt(st_off.nextToken()))!=-1);
		}
		
		//Traverse
//		int []a={1,0,1,1,0,1};
//		int []c={1,0,0,1,1,0};
//		boolean state=checkFinalState(a,c);
//		out.println(state);
		
		boolean found=false;
		for(int i4=0;i4<2;i4++){
			for(int i3=0;i3<2;i3++)
				for(int i2=0;i2<2;i2++)
					for(int i1=0;i1<2;i1++){
						int count=0;
						for(int i=0;i<lamp.length;i++){
							lamp[i]=1;
						}
						if(i1==1){
							button1(lamp);
							count++;
						}
						if(i2==1){
							button2(lamp);
							count++;
						}
						if(i3==1){
							button3(lamp);
							count++;
						}
						if(i4==1){
							button4(lamp);
							count++;
						}
						
						if(validPress(count,C)){
							if(checkFinalState(lamp,on_off_list)){								
								found=true;
								//print(lamp,out);
								solution.add(ints2str(lamp));
							}
						}
					}
		}
		
		
		
		if(!found){
			out.println("IMPOSSIBLE");
		}else{
			Collections.sort(solution);
			for(int i=0;i<solution.size();i++){
				out.println(solution.get(i));
			}
		}
		
		out.close();
		
	}
	
	public static String ints2str(int []lamp){
		return Arrays.toString(lamp).replaceAll(",|\\[|\\]|\\s", "");
	}
	
	public static void print(int  lamp[],PrintWriter out){
		for(int i=0;i<lamp.length;i++){
			out.print(lamp[i]);
		}
		out.println();
	}
	
	public static boolean validPress(int count,int C){
		if(count>C){
			return false;
		}else{
			return (C-count)%2==0;
		}
	}
	
	//Vorsicht bei der Indices
	//all
	public static boolean checkFinalState(int [] lamp, int [] check){
		for(int i=0;i<check.length;i++){
			if(check[i]==1&&lamp[i]==0){
				return false;
			}
			if(check[i]==-1&&lamp[i]==1){
				return false;
			}
		}
		return true;
	}
	
	public static void button1(int [] lamp){
		for(int i=0;i<lamp.length;i++){
			if(lamp[i]==0){
				lamp[i]=1;
			}else{
				lamp[i]=0;
			}
		}
	}
	//odd number
	public static void button2(int [] lamp){
		for(int i=0;i<lamp.length;i+=2){
			if(lamp[i]==0){
				lamp[i]=1;
			}else{
				lamp[i]=0;
			}
		}
	}
	
	//even number
	public static void button3(int [] lamp){
		for(int i=1;i<lamp.length;i+=2){
			if(lamp[i]==0){
				lamp[i]=1;
			}else{
				lamp[i]=0;
			}
		}
	}
	
	
	//3k+1 number
	public static void button4(int [] lamp){
		for(int i=0;3*i+1-1<lamp.length;i++){
			if(lamp[3*i+1-1]==0){
				lamp[3*i+1-1]=1;
			}else{
				lamp[3*i+1-1]=0;
			}
		}
	}
	
	
}
