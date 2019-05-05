import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * http://tech-algorithm.com/articles/nearest-neighbor-image-scaling/
 */

/**
 * @author arauj
 *
 */
public class AmplRed_Tests{

	/**
	 * @param args
	 */
	
	
	
	public static int[][] resizePixels(int[][] pixels,int linhas,int colunas){
	    int[][] temp = new int[colunas][linhas] ;
	    double x_ratio = pixels[0].length/(double)linhas;
	    double y_ratio = pixels.length/(double)colunas;
	    
	    double px, py ; 
	    for (int i = 0; i < colunas; i++)
	        for (int j = 0; j < linhas; j++) {
	            px = Math.floor(i*x_ratio);
	            py = Math.floor(j*y_ratio) ;
	            temp[i][j] = pixels[(int)px][(int)py];
	        }
	    return temp ;
	}
	
	
	/*
	
	public static int[] resizePixels(int[] pixels,int w1,int h1, int linhas,int colunas) {
	    int[] temp = new int[linhas*colunas] ;
	    double x_ratio = w1/(double)linhas;
	    double y_ratio = h1/(double)colunas;
	    
	    double px, py ; 
	    for (int i = 0; i < colunas; i++)
		        for (int j = 0; j < linhas; j++) {
	            px = Math.floor(j*x_ratio) ;
	            py = Math.floor(i*y_ratio) ;
	            temp[(i*linhas)+j] = pixels[(int)((py*w1)+px)];
	        }
	    return temp ;
	}
	
	*/
	
	public static int[][] to2dArray(int[] aO, int linhas, int colunas){ // aO: Array Original
		int[][] retorno = new int[linhas][colunas];
		for (int i = 0; i < linhas; i++) 
			for (int j = 0; j < colunas; j++) 
				retorno[i][j] = aO[(j*linhas)+i];
		return retorno;
	}
	
	public static int[] toArray(int[][] mO){ // mO: Matriz Original
		int[] retorno = new int[mO.length*mO[0].length];
		for (int i = 0; i < mO.length; i++) 
			for (int j = 0; j < mO[0].length; j++)
				retorno[(i*mO[0].length)+j] = mO[i][j];
		return retorno;
	}
	
	public static int[][] getMatrix(BufferedImage input){
		int height = input.getHeight(); 
		int width = input.getWidth();
		int [][] array = new int[height][width];
		for(int i=0; i<height; i++)
			for(int j=0; j<width; j++)
					array[i][j] = input.getRGB(j, i);
		return array;
	}

	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedImage imagem = ImageIO.read(new File("landscape.jpg"));
		int linhas = imagem.getWidth();
		int colunas = imagem.getHeight();
		int[] pixels = imagem.getRGB(0, 0, linhas, colunas, null, 0, linhas);
		
		int[][] matriz = getMatrix(imagem);
		
		int[][] x = to2dArray(pixels, linhas, colunas);
		int[] y = toArray(matriz);
		
		int lD = linhas/2;	//	Tamanho das linhas desejadas
		int cD = colunas/2;	//	Tamanho das colunas desejadas
		
				
		int[][] resultante = resizePixels(matriz, lD, cD);
		
		BufferedImage bufferedImage = new BufferedImage(lD, cD, BufferedImage.TYPE_INT_RGB);
		bufferedImage.setRGB(0, 0, lD, cD, toArray(resultante), 0, lD);
		ImageIO.write(bufferedImage, "PNG", new File("imagemResultante.png"));
		System.out.println("Pronto!");
		
	}

}
