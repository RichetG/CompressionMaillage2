package objParser;

import java.io.File;
import java.util.ArrayList;

/**
 * Compression d'un fichier .obj 
 * @author Yann GIROS, Guillaume RICHET, Mathieu CADIO
 * @version 11/11/15
 */

public class Compression extends ObjParser{

	private ArrayList<Vertex3D>maxMin;
	
	/**
	 * Constructeur
	 * Compression du fichier .obj
	 * @param file
	 */
	public Compression(File file){
		extract(file);
	}
	
	/**
	 * Recuperation de 2 vecteurs avec les coords max et min
	 * @return une liste de Vertex3D 
	 */
	public ArrayList<Vertex3D> determinationMaxMin(){
		ArrayList<Vertex3D> limite=new ArrayList<Vertex3D>(2);
		double minX=vertexList.get(0).getX(), minY=vertexList.get(0).getY(), minZ=vertexList.get(0).getZ(), maxX=vertexList.get(0).getX(), maxY=vertexList.get(0).getY(), maxZ=vertexList.get(0).getZ();
		for(int i=1; i< vertexList.size(); i++){
			if(minX>=vertexList.get(i).getX()){
				minX=vertexList.get(i).getX();
			}
			if(minY>=vertexList.get(i).getY()){
				minY=vertexList.get(i).getY();
			}
			if(minZ>=vertexList.get(i).getZ()){
				minZ=vertexList.get(i).getZ();
			}
			if(maxX<=vertexList.get(i).getX()){
				maxX=vertexList.get(i).getX();
			}
			if(maxY<=vertexList.get(i).getY()){
				maxY=vertexList.get(i).getY();
			}
			if(maxZ<=vertexList.get(i).getZ()){
				maxZ=vertexList.get(i).getZ();
			}
		}
		Vertex3D max=new Vertex3D(maxX, maxY, maxZ);
		Vertex3D min=new Vertex3D(minX, minY, minZ);
		limite.add(max);
		limite.add(min);
		return limite;
	}
	
	/**
	 * Conversion des vertices bornï¿½ entre 0-1
	 */
	public void conversion(){
		maxMin=determinationMaxMin();
		for(int i=0; i<vertexList.size(); i++){
			vertexList.get(i).setX((vertexList.get(i).getX()+(maxMin.get(1).getX()*-1))/(maxMin.get(0).getX()+(maxMin.get(1).getX())*-1));
			vertexList.get(i).setY((vertexList.get(i).getY()+(maxMin.get(1).getY()*-1))/(maxMin.get(0).getY()+(maxMin.get(1).getY())*-1));
			vertexList.get(i).setZ((vertexList.get(i).getZ()+(maxMin.get(1).getZ()*-1))/(maxMin.get(0).getZ()+(maxMin.get(1).getZ())*-1));
		}
	}
	
	/**
	 * conversion inverse des vertices à mettre à l'echelle initiale
	 */
	public void conversionInverse(){
		for(int i=0; i<vertexList.size(); i++){
			vertexList.get(i).setX((maxMin.get(0).getX()+(maxMin.get(1).getX()*-1))*vertexList.get(i).getX()-(maxMin.get(1).getX()*-1));
			vertexList.get(i).setY((maxMin.get(0).getY()+(maxMin.get(1).getY()*-1))*vertexList.get(i).getY()-(maxMin.get(1).getY()*-1));
			vertexList.get(i).setZ((maxMin.get(0).getZ()+(maxMin.get(1).getZ()*-1))*vertexList.get(i).getZ()-(maxMin.get(1).getZ()*-1));
		}
	}
	
}
