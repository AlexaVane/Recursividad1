package Algoritmo_Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DijkstraRecursividad 
{
	public static int[][] intMatrizInicial; 
	public static boolean[][] blnMatrizRecorrida;
	public static int[][] intMatrizPeso;

	public static void main (String args[])
	{	
		int[] intTrayecto={0,0};
		intTrayecto=Datos();
		String strOrigen=""+(char)(intTrayecto[0]+65);
		String strDestino=""+(char)(intTrayecto[1]+65);
		ArrayList<String> lstAuxiliar= new ArrayList<String>();
		ArrayList<String> lstSolucion= new ArrayList<String>();

		lstAuxiliar=Dijkstra(intTrayecto[0],intTrayecto[0],intTrayecto[0],intTrayecto[0],intTrayecto[1],lstAuxiliar);

		lstSolucion=Resultado(lstAuxiliar,lstAuxiliar.size()-1,lstSolucion);
		System.out.println("Partida: "+ strOrigen+"->"+(strOrigen.codePointAt(0)-65)+(strOrigen.codePointAt(0)-65));
		System.out.print("Recorrido: ");
		for(int i=lstSolucion.size()-1; i>-1;i--)
		{
			System.out.print((char)(Integer.parseInt(lstSolucion.get(i).substring(1))+65)+"->"+lstSolucion.get(i)+"\t");
		}
		System.out.println();
		System.out.println("Llegada: "+strDestino+"->"+(strDestino.codePointAt(0)-65)+(strDestino.codePointAt(0)-65));
	}

	

	public static ArrayList<String> Dijkstra (int intOrigen,int intFilaAnterior, int intColumnaAnterior ,int intActual,int intDestino, ArrayList<String>  lstAuxiliar)
	{
		if(intActual!=intDestino)
		{
			for(int i=0; i<intMatrizInicial[intActual].length;i++)
			{
				if(i!=intActual && intMatrizInicial[intActual][i]>0)
				{
					intMatrizPeso[intActual][i]=intMatrizPeso[intFilaAnterior][intColumnaAnterior]+intMatrizInicial[intActual][i];
				}
			}

			int intMinimo=-1;
			int [] intPosicion = {0,0};
			int i=0, j=0;

			for(i=0; i<intMatrizInicial.length;i++)
			{
				for(j=0; j<intMatrizInicial.length;j++)
				{
					if(blnMatrizRecorrida[i][j]==false && (intMinimo>intMatrizPeso[i][j]||intMinimo==-1) && intMatrizPeso[i][j]!=0)
					{
						intMinimo=intMatrizPeso[i][j];
						intPosicion[0]=i;
						intPosicion[1]=j;
					}
				}
			}

			blnMatrizRecorrida[intPosicion[0]][intPosicion[1]]=true;

			lstAuxiliar.add(intPosicion[0]+""+intPosicion[1]);
			for(i=0; i<lstAuxiliar.size();i++)
			{
				if(Integer.parseInt(lstAuxiliar.get(i).substring(1))==intPosicion[1]&&Integer.parseInt(lstAuxiliar.get(i).substring(0,1))!=intPosicion[0])
				{
					if((intMatrizPeso[intPosicion[0]][intPosicion[1]]<intMatrizPeso[Integer.parseInt(lstAuxiliar.get(i).substring(0,1))][Integer.parseInt(lstAuxiliar.get(i).substring(1))]))
					{
						lstAuxiliar.remove(i);
						System.out.println("asdfasdf gdfgdfg");
						break;
					}
					else
					{
						lstAuxiliar.remove(lstAuxiliar.size()-1);
					}
				}
			}
			return Dijkstra(intOrigen,intPosicion[0] ,intPosicion[1],intPosicion[1], intDestino, lstAuxiliar);
		}
		return lstAuxiliar;
	}

	public static ArrayList<String> Resultado(ArrayList<String> lstAuxiliar, int intPosicion,ArrayList<String> lstSolucion)
	{
		for(int i=0; i<lstAuxiliar.size();i++)
		{
			if(lstAuxiliar.get(intPosicion).substring(0,1).equals(lstAuxiliar.get(i).substring(1)))
			{
				lstSolucion.add(lstAuxiliar.get(i));
				Resultado(lstAuxiliar, i, lstSolucion);
				break;
			}

		}
		return lstSolucion;
	}
	
	public static int [] Datos()
	{
		int num=0;

		System.out.print("Numero de nodos que tiene el grafo a resolver? ");

		do{
			try{
				InputStreamReader l1 = new InputStreamReader(System.in);
				BufferedReader l2 = new BufferedReader(l1);
				num=Integer.valueOf(l2.readLine()).intValue();
			}
			catch(IOException e){
				System.out.println("Error: "+e);
				System.out.println("Ingresa el numero de nodos que tiene el grafo a resolver: ");
			}
			catch(NumberFormatException e2){
				System.out.println("Error: "+e2);
				System.out.println("Ingresa el numero de nodos que tiene el grafo a resolver: ");
			}
			if(num<3 || num>26)
				System.out.print(" * El numero de nodos debe estar entre 3 y 26 ");

		}while(num<3 || num>26);

		intMatrizInicial = new int[num][num];
		intMatrizPeso=new int[num][num];
		blnMatrizRecorrida= new boolean[num][num];
		InputStreamReader l1;
		BufferedReader l2;

		l1 = new InputStreamReader(System.in);
		l2 = new BufferedReader(l1);

		boolean ocurrioError;
		System.out.println(" * Ahora ingresa los datos que se te soliciten: ");
		for(int cuenta=1;cuenta<=num;cuenta++)
			for(int cnt=1;cnt<=num;cnt++){
				if(cnt!=cuenta){
					System.out.println("Costo de la arista dirigida del nodo "+(char)(cuenta+64)+" al nodo "+(char)(cnt+64));
					System.out.print("(Ingresa 0 si la arista no existe) ");
					ocurrioError=false;
					try{
						intMatrizInicial[cuenta-1][cnt-1]=Integer.valueOf(l2.readLine()).intValue();
						ocurrioError=(intMatrizInicial[cuenta-1][cnt-1]<0?true:false);
						intMatrizInicial[cuenta-1][cnt-1]=(intMatrizInicial[cuenta-1][cnt-1]==0?-1:intMatrizInicial[cuenta-1][cnt-1]);
					}
					catch(IOException e0){
						System.out.println("Error: "+e0);
						ocurrioError=true;
					}
					catch(NumberFormatException e){
						System.out.println("Error: "+e);
						ocurrioError=true;
					}
					if(ocurrioError)
						cnt--;
				}
				else
					intMatrizInicial[cuenta-1][cuenta-1]=-1;
			}

		for(int i=0; i<intMatrizInicial.length;i++)
		{
			for(int j=0; j<intMatrizInicial[i].length;j++)
			{
				intMatrizPeso[i][j]=0;
				blnMatrizRecorrida[i][j]=false;
			}
		}

		System.out.println("Matriz Inicial");
		System.out.print("   ");
		for(int i=0; i<intMatrizInicial.length;i++)
		{
			System.out.print((char)(i+65)+"\t");
		}
		System.out.println();
		for(int i=0; i<intMatrizInicial.length;i++)
		{
			System.out.print((char)(i+65)+"  ");
			for(int j=0; j<intMatrizInicial[i].length;j++)
			{
				System.out.print(intMatrizInicial[i][j]+"\t");
			}
			System.out.println();
		}


		int aux=0;
		int[] trayecto={0,0};
		do{
			try{
				System.out.print(" * Cual es el nodo origen: ");
				aux=((int)((l2.readLine()).toUpperCase()).charAt(0))-65;
			}
			catch(IOException e2){
				System.out.println("Error: "+e2);
				aux=-1;
			}
			catch(StringIndexOutOfBoundsException e3){
				System.out.println("Error: "+e3);
				aux=-1;
			}
		}while(aux<0 || aux>num-1);
		trayecto[0]=aux;
		do{
			try{
				System.out.print(" * Cual es el nodo Destino: ");
				aux=((int)((l2.readLine()).toUpperCase()).charAt(0))-65;
			}
			catch(IOException e2){
				System.out.println("Error: "+e2);
				aux=-1;
			}
			catch(StringIndexOutOfBoundsException e3){
				System.out.println("Error: "+e3);
				aux=-1;
			}
		}while(aux<0 || aux>num-1);
		trayecto[1]=aux;
		return trayecto;
	}
}
