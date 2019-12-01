/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import javax.swing.JOptionPane;
/**
 *
 * @author URIEL PEREZ and ERICK LECHUGA
 */
public class Operaciones {

    public float[][] sumar(float matrizSumandoA[][], float matrizSumandoB[][], int nFilas, 
            final int nColumnas) {
        float[][] matrizResultado = new float[nFilas][nColumnas];

        for (int iFilas = 0; iFilas < nFilas; iFilas++) {
            for (int iColumnas = 0; iColumnas < nColumnas; iColumnas++) {
                matrizResultado[iFilas][iColumnas] = matrizSumandoA[iFilas][iColumnas]
                        + matrizSumandoB[iFilas][iColumnas];
            }
        }
        return matrizResultado;

    }

    public float[][] multEscalar(float escalar, float matriz[][], final int nFilas, 
          final  int nColumnas) {
        float[][] matrizEscalar = new float[nFilas][nColumnas];

        for (int iFilas = 0; iFilas < nFilas; iFilas++) {
            for (int iColumnas = 0; iColumnas < nColumnas; iColumnas++) {
                matrizEscalar[iFilas][iColumnas] = escalar * matriz[iFilas][iColumnas];
            }
        }
        return matrizEscalar;
    }

    public float[][] producto(float matrizFactorA[][], float matrizFactorB[][], int nFilasA, 
          final int nColumnasA, final int nColumnasB) {
        float[][] matrizResultado = new float[nFilasA][nColumnasB];

        for (int iFilas = 0; iFilas < nFilasA; iFilas++) {
            for (int iColumnas = 0; iColumnas < nColumnasB; iColumnas++) {
                for (int k = 0; k < nColumnasA; k++) {
                    matrizResultado[iFilas][iColumnas] = (matrizFactorA[iFilas][k] * matrizFactorB[k][iColumnas])
                            + matrizResultado[iFilas][iColumnas];
                }
            }
        }

        return matrizResultado;
    }

    public void obtenerMatrizTranspuesta(float[][] matriz, int numFilas, int numColumnas) {
        int numFilasTrans = numColumnas;
        int numColumnasTrans = numFilas;

        float[][] matrizTranspuesta = new float[numFilasTrans][numColumnasTrans];

        for (int fila = 0; fila < numFilas; fila++) {

            for (int columna = 0; columna < numColumnas; columna++) {
                matrizTranspuesta[columna][fila] = matriz[fila][columna];
            }
        }

        imprimeMatriz(matrizTranspuesta);
    }

    //Sacar la inversa de una matriz por metodo de Gauss-Jordan 
    public float[][] Inversa(float matriz[][], int dimensionMatriz) {
        float[][] matrizInversa = new float[dimensionMatriz][dimensionMatriz];
        float[][] matrizAumentada = new float[dimensionMatriz][2 * dimensionMatriz];

        if (determinante(matriz) == 0) {
            System.out.println("El determinante de la matriz es cero por lo "
                    + "tanto la matriz no tiene inversa");
            return null;
        }

        for (int contadorFila = 0; contadorFila < dimensionMatriz; contadorFila++) {
            System.arraycopy(matriz[contadorFila], 0, matrizAumentada[contadorFila], 0, dimensionMatriz);
        }

        validarPivotes(matrizAumentada, dimensionMatriz);
        aumentarMatriz(matrizAumentada, dimensionMatriz);

        for (int pivote = 0; pivote < dimensionMatriz; pivote++) {
            hacerUno(matrizAumentada, dimensionMatriz, pivote);
            hacerCeros(matrizAumentada, dimensionMatriz, pivote);
        }

        for (int contadorFila = 0; contadorFila < dimensionMatriz; contadorFila++) {
            for (int contadorColumna = 0; contadorColumna < dimensionMatriz; contadorColumna++) {
                matrizInversa[contadorFila][contadorColumna]
                        = matrizAumentada[contadorFila][contadorColumna + dimensionMatriz];
            }
        }
        return matrizInversa;
    }

    //convertir el elemento de la diagonal principal en 1
    static void hacerUno(float matriz[][], int dimensionMatriz, int pivote) {
        float elementoPivote = (1 / matriz[pivote][pivote]);
        for (int posColumna = 0; posColumna < (2 * dimensionMatriz); posColumna++) {
            matriz[pivote][posColumna] *= elementoPivote;
        }
    }

    //hacer ceros los elementos que estan abajo y/o arriba del pivote
    static void hacerCeros(float matriz[][], int dimensionMatriz, int pivote) {
        for (int contadorFilas = 0; contadorFilas < dimensionMatriz; contadorFilas++) {
            if (!(contadorFilas == pivote)) {
                float elmntoTempCros = -matriz[contadorFilas][pivote];
                for (int contadorColumnas = 0; contadorColumnas < (2 * dimensionMatriz); contadorColumnas++) {
                    matriz[contadorFilas][contadorColumnas] += (elmntoTempCros
                            * matriz[pivote][contadorColumnas]);
                }
            }
        }
    }
    //unir la matriz con un la matriz identidad 
    
    
    
    
    static void aumentarMatriz(float matriz[][], int dimensionMatriz) {
        
        for (int posicionFila = 0; posicionFila < dimensionMatriz; posicionFila++) {
            for (int posicionColumna = (dimensionMatriz); posicionColumna < (2 * dimensionMatriz); posicionColumna++) {
                if (Math.abs(posicionFila - posicionColumna) == dimensionMatriz) {
                    matriz[posicionFila][posicionColumna] = 1;
                }

            }
        }

    }

    //Validar que los pivotes de la matriz principal no sean cero para poder hacer
    //las operaciones
    static void validarPivotes(float matriz[][], int numeroFilas) {
        for (int contadorFila = 0; contadorFila < numeroFilas; contadorFila++) {

            if (matriz[contadorFila][contadorFila] == 0) {
                System.out.println("el elemento " + contadorFila + " " + contadorFila + " es un cero");
                for (int contadorColumna = 0; contadorColumna < numeroFilas; contadorColumna++) {
                    matriz[contadorFila][contadorColumna] += 1;
                }
            }

        }

    }

    public float determinante(float[][] matriz) {
        float determinante;
        if (matriz.length == 2) {
            determinante = (matriz[0][0] * matriz[1][1]) - (matriz[1][0] * matriz[0][1]);
            return determinante;
        }
        float suma = 0;
        for (int contadorFila = 0; contadorFila < matriz.length; contadorFila++) {
            float[][] matrizTemporal = new float[matriz.length - 1][matriz.length - 1];
            for (int contadorColumna = 0; contadorColumna < matriz.length; contadorColumna++) {
                if (contadorColumna != contadorFila) {
                    for (int k = 1; k < matriz.length; k++) {
                        int indice = -1;
                        if (contadorColumna < contadorFila) {
                            indice = contadorColumna;
                        } else if (contadorColumna > contadorFila) {
                            indice = contadorColumna - 1;
                        }
                        matrizTemporal[indice][k - 1] = matriz[contadorColumna][k];
                    }
                }
            }
            if (contadorFila % 2 == 0) {
                suma += matriz[contadorFila][0] * determinante(matrizTemporal);
            } else {
                suma -= matriz[contadorFila][0] * determinante(matrizTemporal);
            }
        }
        return suma;
    }

    static public void imprimeMatriz(float matriz[][]) {

        for (int iFilas = 0; iFilas < matriz.length; iFilas++) {
            System.out.println("");
            for (int iColumnas = 0; iColumnas < matriz[iFilas].length; iColumnas++) {
                System.out.print(" " + matriz[iFilas][iColumnas]);

            }
            System.out.println("");
        }

    }
    
    
    
    
    
    
    
    
    
    
  
     
    /**
     *soluciona una ecuacion por Gauss.
     *@param matrizEcuaciones  matriz de entrada
     */
    public static float[][] solucionConGauss(float matrizEcuaciones[][],float ResultadosEcuaciones[][]){
        float[][] matrizAumentada;
        matrizAumentada = AumentaMatrizEcuacion(matrizEcuaciones, ResultadosEcuaciones);
        
        for (int contFila = 0;contFila<matrizAumentada.length;contFila++){
            ChecarPivotes(matrizAumentada, contFila);
            dividirEntrePivote(matrizAumentada, contFila);
            escalonaFila(matrizAumentada, contFila);
        }
        return matrizEcuaciones;
    }
    
       /**
        * Metodo que une la matriz de ecuaciones con la matriz de resultados
        * para hacer una sola.
        * @param matriz 
        * @param matrizVector
        */
    
      private static float[][] AumentaMatrizEcuacion(float matriz[][],float matrizVector[][]){
        int filas = matriz.length;
        int columnas = matriz[0].length;
        float[][] matrizAumentada = new float[filas][columnas+1];
        for(int indFilas =0; indFilas<matrizAumentada.length;indFilas++){
            for (int indColum = 0; indColum<matrizAumentada[0].length; indColum++) {
                if(indColum <matrizAumentada[0].length-1){
                    matrizAumentada[indFilas][indColum] = matriz[indFilas][indColum];
                }
                else{
                    matrizAumentada[indFilas][indColum] = matrizVector[indFilas][0];
                }
            }
        }
        return matrizAumentada;
    }
      
      
    /**
     * Metodo que revisa si hay un pivote que pueda ser cero, si no es cero, continua 
     * haciendo el algoritmo, si es cero, llama a un metodo de busqueda de una fila con
     * un pivote que no sea cero y luego intercambia las filas para que no se produzca error
     * de division entre cero.
     * @param matriz  a la que se le revisaran los pivotes en las filas
     * @param indPivote es indice del pivote en el que preguntara si es distinto de cero
     */
    public static void ChecarPivotes(float matriz[][], int indPivote){
        if(matriz[indPivote][indPivote]!=0.0){
            
            return;
        }else{
             //continua con el proceso 
            int indicePivoteEncontrado = buscarFilaSinPivoteCero(matriz, indPivote+1);
            intercambiarFilas(matriz, indPivote, indicePivoteEncontrado); 
        }
    }
    
     /*
     *intercambia 2 filas de una matriz
     */
    public static void intercambiarFilas(float matriz[][], int filaA, int filaB){
        int colum = matriz[0].length;     
        for (int columnasCount = 0;columnasCount < colum;columnasCount++) {
                float aux = matriz[filaA][columnasCount];
                matriz[filaA][columnasCount] = matriz[filaB][columnasCount];
                matriz[filaB][columnasCount] = aux;
        }       
    }

    

    /**
     * Metodo que busca una fila de la matriz en el que el pivote sea distinto de cero
     * @param matriz matriz a la cual se le busca el pivote
     * @param indiceABuscar indice que se empezara a buscar un pivote valido
     * @return el indice de la fila  de la matriz con pivote valido, distinto de cero
     */
     private static int buscarFilaSinPivoteCero(float matriz[][], int indiceABuscar){
        int filas = matriz.length;
        for (int contadorFila = indiceABuscar; contadorFila < filas; contadorFila++) {
            if(matriz[contadorFila][contadorFila]!=0.0){
                System.out.println("ENCONTRADO"+ contadorFila);
                return contadorFila;
            }
        }  
        JOptionPane.showMessageDialog(null,"ERROR");
        return 0;
    }

   
    
   
    
  
    
    /**
     * Metodo que divide una fila de una matriz entre el pivote para que la casilla del pivote
     * se vuelva 1.
     * @param matriz matriz a la que se le aplicara el algoritmo
     * @param indicePivote indice del pivote que se esta analizando
     */    
    private static void dividirEntrePivote(float matriz[][], int indicePivote) {
        int columnas = matriz[0].length;
        float pivote = matriz[indicePivote][indicePivote];   
        for (int contadorColum = 0; contadorColum< columnas; contadorColum++) {
            matriz[indicePivote][contadorColum] = matriz[indicePivote][contadorColum] / pivote;
        }
    }
    
    /**
     * Metodo que escalona la fila del pivote especifico, volviendo 0 toda la columna del pivote,
     * a excepcion del pivote mismo, de acuerdo al agoritmo de Gauss Jordan.
     * @param matriz matriz a la que se le escalonara la fila
     * @param indicePivote indice del pivote que se esta analizando
     */      
    private static void escalonaFila(float matriz[][], int indicePivote) {
        int filas = matriz.length;
        for (int contadorFila = 0; contadorFila < filas; contadorFila++) {
            if (contadorFila != indicePivote) {
                multiplicarCoeficienteOpuesto(contadorFila,indicePivote, matriz);
            }
        }
    }
    /**
     * Metodo que multiplica la fila de la matriz donde esta el pivote, por
     * el opuesto en signo del numero de la misma columna donde esta el pivote,
     * y luego lo suma con la otra fila  para poder hacer que la columna del pivote especifico, se encuentre entre 0's.
     * @param matriz matriz a la que se le aplicara el algoritmo
     * @param indicePivote indice del pivote que se esta analizando
     */      
    private static void multiplicarCoeficienteOpuesto(int contadorFila, int indicePivote, float [][]matriz){
        int columnas = matriz[0].length;
        float coeficienteOpuesto = -1 *matriz[contadorFila][indicePivote];
        for (int contadorColum = 0; contadorColum < columnas; contadorColum++) {
            matriz[contadorFila][contadorColum] = (coeficienteOpuesto * matriz[indicePivote][contadorColum])
                + matriz[contadorFila][contadorColum];
        }
    }
    
    
    
    
    
   
}
