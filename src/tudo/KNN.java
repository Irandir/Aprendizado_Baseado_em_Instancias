package tudo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class KNN {
	
	static double[][] serie = {{2,3,4},{3,4,5},{4,5,6},{1,2,3}};
	static double[] saida = {5,6,7,4};
	
	public static void main(String[] args) {
		int k =2;//numero de vizinhos proximos
		double agora [] = {5,6,7};
		double desejado = 8;
		double distancias[] = calculaDistancia(agora);
		System.out.println("______________Distancias______________");
		for (int i = 0; i < distancias.length; i++) {
			System.out.println(distancias[i]);
		}
		List<Double>distanciasColecao = new ArrayList<>();
		for (int i = 0; i < distancias.length; i++) {
			distanciasColecao.add(distancias[i]);
		}
		
		Instancia[] instancias = new Instancia[saida.length];
		for (int i = 0; i < instancias.length; i++) {
			instancias[i] = new Instancia(distancias[i], serie[i], saida[i]);
		}
		//ordem
		
		Instancia[] instanciasOrdem = sort(instancias);
		System.out.println("_____________dist ordem______________");
		for (int i = 0; i < instanciasOrdem.length; i++) {
			System.out.println(instanciasOrdem[i].distancia);
		}
		double s = 0;
		for (int i = 0; i < k; i++) {
			s+=instanciasOrdem[i].saida;
		}
		s = s/k;
		System.out.println("s-->"+s);
	}
	
	public static Instancia[]sort(Instancia[] instancias){
		Instancia[] instanciasOrdem = new Instancia[instancias.length];
		for (int i = 0; i < instanciasOrdem.length; i++) {
			instanciasOrdem[i] = instancias[i];
		}
		int n = instancias.length;
		int k, j ;
		Instancia aux;
		for (k = n - 1; k > 0; k--) {
	        for (j = 0; j < k; j++) {
	            if (instanciasOrdem[j].distancia > instanciasOrdem[j + 1].distancia) {
	                aux          = instanciasOrdem[j];
	                instanciasOrdem[j]     = instanciasOrdem[j + 1];
	                instanciasOrdem[j + 1] = aux;
	            }
	        }
	    }
		return instanciasOrdem;
	}
	
	public static double[] calculaDistancia(double[] agora){
		double distancias[] = new double[serie.length];
		double d = 0;
		for (int i = 0; i < distancias.length; i++) {
			d = 0;
			//numero de dimensões
			for (int j = 0; j < serie[0].length; j++) {
				d +=Math.pow(agora[j] - serie[i][j],2);
			}
			distancias[i] = d;
		}
		return distancias;
	}
	
}
