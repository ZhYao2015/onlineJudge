/*
ID:l_coder1
LANG:JAVA
TASK:maze1
*/

//package stage2_4;

//NOT EXACTLY A GRAPH!!!
//WE NEED TO PAY ATTENTION TO EXTRA NODES

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class maze1 {
	
	static final int di[][]={{-1,0},{0,1},{1,0},{0,-1}};
	
	
	
	static int exit[][]=new int[2][2];
	static Queue<int []> queue=new LinkedList<int []>();
	static int W;
	static int H;


	
	static int visited[][];
	static char [][] maze;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader f=new BufferedReader(new FileReader("maze1.in"));
		PrintWriter out=new PrintWriter(new FileWriter("maze1.out"));
		
		StringTokenizer st=new StringTokenizer(f.readLine());
		
		W=Integer.parseInt(st.nextToken());
		H=Integer.parseInt(st.nextToken());
		
		maze=new char [2*H+1][2*W+1];
		
		visited=new int[2*H+1][2*W+1];
		
		int ch,i=0,i_exit=0;
		
		int exitX=-1,exitY=-1;
		
		
		
		
		while((ch=f.read())!=-1){
			if(ch!='\n'&& ch!='\r'){
				maze[i/(2*W+1)][i%(2*W+1)]=(char)ch;
				if(ch==' '){
					//check if it is the exit?(on border)
					if(i/(2*W+1)==0||i/(2*W+1)==2*H||i%(2*W+1)==0||i%(2*W+1)==2*W){
						if(i_exit>1){
							
						}else{
							exitX=i/(2*W+1);
							exitY=i%(2*W+1);
							exit[i_exit][0]=exitX;
							exit[i_exit][1]=exitY;
							i_exit++;
						}
						
					}
				}
				i++;
			}
		}
		
		//BFS
		queue.add(exit[0]);
		visited[exit[0][0]][exit[0][1]]=1;
		int dist1[]=bfs(exit[0]);
		queue.add(exit[1]);
		
		for(int i1=0;i1<2*H+1;i1++){
			for(int j1=0;j1<2*W+1;j1++){
				visited[i1][j1]=0;
			}
		}
		visited[exit[1][0]][exit[1][1]]=1;
		int dist2[]=bfs(exit[1]);
		
		for(int i1=0;i1<(2*H+1)*(2*W+1);i1++){
			if(dist1[i1]%2==0){
				dist1[i1]=dist1[i1]/2;
			}else{
				dist1[i1]=(dist1[i1]+1)/2;
			}
			
			if(dist2[i1]%2==0){
				dist2[i1]=dist2[i1]/2;
			}else{
				dist2[i1]=(dist2[i1]+1)/2;
			}
		}
		
		List<Integer> arr=new ArrayList<Integer>();
		
		for(int i1=0;i1<(2*H+1)*(2*W+1);i1++){
			if(dist1[i1]==-1){
				
			}else{
				arr.add(dist1[i1]>dist2[i1]?dist2[i1]:dist1[i1]);
			}
		}
		
		Collections.sort(arr,new Comparator<Integer>(){

			@Override
			public int compare(Integer a, Integer b) {
				// TODO Auto-generated method stub
				if(a.intValue()>b.intValue()){
					return -1;
				}else if(a.intValue()==b.intValue()){
					return 0;
				}else{
					return 1;
				}
			}
		});
		
		out.println(arr.get(0));
		out.close();
	}

	public static int [] bfs(int [] p){
		int dist[]=new int [(2*H+1)*(2*W+1)];
		for(int i=0;i<(2*H+1)*(2*W+1);i++){
			dist[i]=-1;
		}
		dist[p[0]*(2*W+1)+p[1]]=0;
		
		while(true){
			if(queue.isEmpty()){
				return dist;
			}
			int point[]=queue.poll();
			
			//visited[point[0]][point[1]]=1;
			
			
			for(int i=0;i<4;i++){
				int [] npoint=new int [2];
				npoint[0]=point[0]+di[i][0];
				npoint[1]=point[1]+di[i][1];
				if(npoint[0]<0||npoint[0]>2*H||npoint[1]<0||npoint[1]>2*W){
					
				}else{
					if(maze[npoint[0]][npoint[1]]==' '&& visited[npoint[0]][npoint[1]]==0){
						queue.add(npoint);
						visited[npoint[0]][npoint[1]]=1;
						int index_p=point[0]*(2*W+1)+point[1];
						int index=npoint[0]*(2*W+1)+npoint[1];
						
						dist[index]=dist[index_p]+1;
					}
				}
			}
		}
		
	}
}
