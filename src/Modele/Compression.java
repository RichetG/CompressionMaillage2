package Modele;

import java.io.File;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Compression d'un fichier .obj 
 * @author Yann GIROS, Guillaume RICHET, Mathieu CADIO
 * @version 11/11/15
 */

public class Compression extends ObjParser{

	private DecimalFormat df;
	private double marge=0.02;
	private ArrayList<Vertex3D>vertexListOrigin=new ArrayList<Vertex3D>();

	/**
	 * Constructeur
	 * Compression du fichier .obj
	 * @param file
	 */
	public Compression(File file){
		extract(file);
		df=new DecimalFormat("#.#");
		df.setRoundingMode(RoundingMode.UP);
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
			vertexList.get(i).setX((vertexList.get(i).getX()+(maxMin.get(1).getX()*-1))/(maxMin.get(0).getX()+(maxMin.get(1).getX()*-1)));
			vertexList.get(i).setY((vertexList.get(i).getY()+(maxMin.get(1).getY()*-1))/(maxMin.get(0).getY()+(maxMin.get(1).getY()*-1)));
			vertexList.get(i).setZ((vertexList.get(i).getZ()+(maxMin.get(1).getZ()*-1))/(maxMin.get(0).getZ()+(maxMin.get(1).getZ()*-1)));
		}
	}

	/**
	 * Conversion inverse des vertices à mettre à l'echelle initiale
	 */
	public void conversionInverse(){
		for(int i=0; i<vertexList.size(); i++){
			vertexList.get(i).setX(Double.parseDouble(df.format((Math.round(maxMin.get(0).getX())+(Math.round(maxMin.get(1).getX())*-1))*vertexList.get(i).getX()-(Math.round(maxMin.get(1).getX())*-1)).replace(",", ".")));
			vertexList.get(i).setY(Double.parseDouble(df.format((Math.round(maxMin.get(0).getY())+(Math.round(maxMin.get(1).getY())*-1))*vertexList.get(i).getY()-(Math.round(maxMin.get(1).getY())*-1)).replace(",", ".")));
			vertexList.get(i).setZ(Double.parseDouble(df.format((Math.round(maxMin.get(0).getZ())+(Math.round(maxMin.get(1).getZ())*-1))*vertexList.get(i).getZ()-(Math.round(maxMin.get(1).getZ())*-1)).replace(",", ".")));
		}
	}

	/**
	 * Détermination du quadrillage du polygone
	 * @param nbDivisionLongueur
	 * @param nbDivisionHauteur
	 * @param nbDivisionProfondeur
	 * @return quadrillage
	 */
	public void grille(int nbDivisionLongueur, int nbDivisionHauteur, int nbDivisionProfondeur){
		ArrayList<Vertex3D> gril=new ArrayList<Vertex3D>();
		//determination du quadrillage borné entre 0-1
		for(int z=0; z<=nbDivisionProfondeur; z++){
			for(int y=0; y<=nbDivisionHauteur; y++){
				for(int x=0; x<=nbDivisionLongueur; x++){
					Vertex3D vertex3d=new Vertex3D(x*(1.0/(double)nbDivisionLongueur), y*(1.0/(double)nbDivisionHauteur), z*(1.0/(double)nbDivisionProfondeur));
					gril.add(vertex3d);
				}
			}
		}
		//determination du quadrillage taille reelle
		for(int i=0; i<gril.size(); i++){
			gril.get(i).setX(Double.parseDouble(df.format((Math.round(maxMin.get(0).getX())+(Math.round(maxMin.get(1).getX())*-1))*gril.get(i).getX()-(Math.round(maxMin.get(1).getX())*-1)).replace(",", ".")));
			gril.get(i).setY(Double.parseDouble(df.format((Math.round(maxMin.get(0).getY())+(Math.round(maxMin.get(1).getY())*-1))*gril.get(i).getY()-(Math.round(maxMin.get(1).getY())*-1)).replace(",", ".")));
			gril.get(i).setZ(Double.parseDouble(df.format((Math.round(maxMin.get(0).getZ())+(Math.round(maxMin.get(1).getZ())*-1))*gril.get(i).getZ()-(Math.round(maxMin.get(1).getZ())*-1)).replace(",", ".")));
		}
		//mise en place des nouvelles vertex fichier
		for(int i=0; i<gril.size(); i++){
			ajoutVertex.add(new Vertex3D(gril.get(i).getX()-marge, gril.get(i).getY()-marge, gril.get(i).getZ()-marge));
			ajoutVertex.add(new Vertex3D(gril.get(i).getX()+marge, gril.get(i).getY()-marge, gril.get(i).getZ()-marge));
			ajoutVertex.add(new Vertex3D(gril.get(i).getX()+marge, gril.get(i).getY()+marge, gril.get(i).getZ()-marge));
			ajoutVertex.add(new Vertex3D(gril.get(i).getX()-marge, gril.get(i).getY()+marge, gril.get(i).getZ()-marge));
			ajoutVertex.add(new Vertex3D(gril.get(i).getX()-marge, gril.get(i).getY()-marge, gril.get(i).getZ()+marge));
			ajoutVertex.add(new Vertex3D(gril.get(i).getX()+marge, gril.get(i).getY()-marge, gril.get(i).getZ()+marge));
			ajoutVertex.add(new Vertex3D(gril.get(i).getX()+marge, gril.get(i).getY()+marge, gril.get(i).getZ()+marge));
			ajoutVertex.add(new Vertex3D(gril.get(i).getX()-marge, gril.get(i).getY()+marge, gril.get(i).getZ()+marge));
		}
		int index=vertexList.size();
		//mise en place des nouvelles faces fichier
		//trait profondeur
		for(int i=0; i<(nbDivisionLongueur+1)*(nbDivisionHauteur+1); i++){
			int oppose=i+(nbDivisionProfondeur*(nbDivisionLongueur+1)*(nbDivisionHauteur+1));
			Face face1=new Face();
			Face face2=new Face();
			Face face3=new Face();
			Face face4=new Face();
			Face face5=new Face();
			Face face6=new Face();
			//face avant
			face1.add(new FaceComponent(index+(i*8+1), 1));
			face1.add(new FaceComponent(index+(i*8+2), 1));
			face1.add(new FaceComponent(index+(i*8+3), 1));
			face1.add(new FaceComponent(index+(i*8+4), 1));
			//face arriere
			face2.add(new FaceComponent(index+(oppose*8+5), 1));
			face2.add(new FaceComponent(index+(oppose*8+6), 1));
			face2.add(new FaceComponent(index+(oppose*8+7), 1));
			face2.add(new FaceComponent(index+(oppose*8+8), 1));			
			//face gauche
			face3.add(new FaceComponent(index+(i*8+1), 1));
			face3.add(new FaceComponent(index+(i*8+4), 1));
			face3.add(new FaceComponent(index+(oppose*8+8), 1));
			face3.add(new FaceComponent(index+(oppose*8+5), 1));
			//face droite
			face4.add(new FaceComponent(index+(i*8+2), 1));
			face4.add(new FaceComponent(index+(i*8+3), 1));
			face4.add(new FaceComponent(index+(oppose*8+7), 1));
			face4.add(new FaceComponent(index+(oppose*8+6), 1));
			//face haut
			face5.add(new FaceComponent(index+(i*8+3), 1));
			face5.add(new FaceComponent(index+(i*8+4), 1));
			face5.add(new FaceComponent(index+(oppose*8+8), 1));
			face5.add(new FaceComponent(index+(oppose*8+7), 1));
			//face bas
			face6.add(new FaceComponent(index+(i*8+1), 1));
			face6.add(new FaceComponent(index+(i*8+2), 1));
			face6.add(new FaceComponent(index+(oppose*8+6), 1));
			face6.add(new FaceComponent(index+(oppose*8+5), 1));
			ajoutFace.add(face1);
			ajoutFace.add(face2);
			ajoutFace.add(face3);
			ajoutFace.add(face4);
			ajoutFace.add(face5);
			ajoutFace.add(face6);
		}
		//trait longueur
		for(int i=0; i<(nbDivisionProfondeur+1)*(nbDivisionHauteur+1); i++){
			Face face1=new Face();
			Face face2=new Face();
			Face face3=new Face();
			Face face4=new Face();
			Face face5=new Face();
			Face face6=new Face();
			int droite=i*(nbDivisionLongueur+1)+nbDivisionLongueur;
			int gauche=i*(nbDivisionLongueur+1);
			//face droite
			face1.add(new FaceComponent(index+(droite*8+2), 1));
			face1.add(new FaceComponent(index+(droite*8+6), 1));
			face1.add(new FaceComponent(index+(droite*8+7), 1));
			face1.add(new FaceComponent(index+(droite*8+3), 1));
			//face gauche
			face2.add(new FaceComponent(index+(gauche*8+2), 1));
			face2.add(new FaceComponent(index+(gauche*8+6), 1));
			face2.add(new FaceComponent(index+(gauche*8+7), 1));
			face2.add(new FaceComponent(index+(gauche*8+3), 1));
			//face avant
			face3.add(new FaceComponent(index+(gauche*8+1), 1));
			face3.add(new FaceComponent(index+(droite*8+2), 1));
			face3.add(new FaceComponent(index+(droite*8+3), 1));
			face3.add(new FaceComponent(index+(gauche*8+4), 1));
			//face arriere
			face4.add(new FaceComponent(index+(gauche*8+5), 1));
			face4.add(new FaceComponent(index+(droite*8+6), 1));
			face4.add(new FaceComponent(index+(droite*8+7), 1));
			face4.add(new FaceComponent(index+(gauche*8+8), 1));
			//face bas
			face5.add(new FaceComponent(index+(gauche*8+1), 1));
			face5.add(new FaceComponent(index+(droite*8+2), 1));
			face5.add(new FaceComponent(index+(droite*8+6), 1));
			face5.add(new FaceComponent(index+(gauche*8+5), 1));
			//face haut
			face6.add(new FaceComponent(index+(gauche*8+4), 1));
			face6.add(new FaceComponent(index+(droite*8+3), 1));
			face6.add(new FaceComponent(index+(droite*8+7), 1));
			face6.add(new FaceComponent(index+(gauche*8+8), 1));
			ajoutFace.add(face1);
			ajoutFace.add(face2);
			ajoutFace.add(face3);
			ajoutFace.add(face4);
			ajoutFace.add(face5);
			ajoutFace.add(face6);
		}
		//trait hauteur
		for(int i=0; i<(nbDivisionProfondeur+1); i++){
			int haut=0, bas=0;
			for(int j=0; j<(nbDivisionLongueur+1); j++){
				Face face1=new Face();
				Face face2=new Face();
				Face face3=new Face();
				Face face4=new Face();
				Face face5=new Face();
				Face face6=new Face();
				haut=(j+((nbDivisionHauteur+1)*(nbDivisionLongueur+1))*i)+((nbDivisionLongueur+1)*nbDivisionHauteur);
				bas=j+((nbDivisionHauteur+1)*(nbDivisionLongueur+1))*i;
				//face bas
				face1.add(new FaceComponent(index+(bas*8+1), 1));
				face1.add(new FaceComponent(index+(bas*8+2), 1));
				face1.add(new FaceComponent(index+(bas*8+6), 1));
				face1.add(new FaceComponent(index+(bas*8+5), 1));
				//face haut
				face2.add(new FaceComponent(index+(haut*8+4), 1));
				face2.add(new FaceComponent(index+(haut*8+3), 1));
				face2.add(new FaceComponent(index+(haut*8+7), 1));
				face2.add(new FaceComponent(index+(haut*8+8), 1));
				//face avant
				face3.add(new FaceComponent(index+(bas*8+1), 1));
				face3.add(new FaceComponent(index+(bas*8+2), 1));
				face3.add(new FaceComponent(index+(haut*8+3), 1));
				face3.add(new FaceComponent(index+(haut*8+4), 1));
				//face arriere
				face4.add(new FaceComponent(index+(bas*8+5), 1));
				face4.add(new FaceComponent(index+(bas*8+6), 1));
				face4.add(new FaceComponent(index+(haut*8+7), 1));
				face4.add(new FaceComponent(index+(haut*8+8), 1));
				//face gauche
				face5.add(new FaceComponent(index+(bas*8+1), 1));
				face5.add(new FaceComponent(index+(bas*8+5), 1));
				face5.add(new FaceComponent(index+(haut*8+8), 1));
				face5.add(new FaceComponent(index+(haut*8+4), 1));
				//face droite
				face6.add(new FaceComponent(index+(bas*8+2), 1));
				face6.add(new FaceComponent(index+(bas*8+6), 1));
				face6.add(new FaceComponent(index+(haut*8+7), 1));
				face6.add(new FaceComponent(index+(haut*8+3), 1));
				ajoutFace.add(face1);
				ajoutFace.add(face2);
				ajoutFace.add(face3);
				ajoutFace.add(face4);
				ajoutFace.add(face5);
				ajoutFace.add(face6);
			}
		}
	}

	/**
	 * Compression du maillage à partir d'un quadrillage donné
	 * @param nbDivisionLongueur
	 * @param nbDivisionHauteur
	 */
	public void compressionMaillage(int nbDivisionLongueur, int nbDivisionHauteur, int nbDivisionProfondeur, boolean grilleOn){
		double portionLongueur=1/(double) nbDivisionLongueur, portionHauteur=1/(double) nbDivisionHauteur, portionPofondeur=1/(double) nbDivisionProfondeur;//1 equivaut à la matrice de taille 1
	
		conversion();
		int tmpX=0, tmpY=0, tmpZ=0; 
		for(int i=0; i<vertexList.size(); i++){
			tmpX=(int) (vertexList.get(i).getX()/portionLongueur);
			tmpY=(int) (vertexList.get(i).getY()/portionHauteur);
			tmpZ=(int) (vertexList.get(i).getZ()/portionPofondeur);
			if(!(vertexList.get(i).getX()==0 || vertexList.get(i).getX()==1)){
				vertexList.get(i).setX((tmpX+1)*portionLongueur);
			}
			if(!(vertexList.get(i).getY()==0 || vertexList.get(i).getY()==1)){
				vertexList.get(i).setY((tmpY+1)*portionHauteur);
			}
			if(!(vertexList.get(i).getZ()==0 || vertexList.get(i).getZ()==1)){
				vertexList.get(i).setZ((tmpZ+1)*portionPofondeur);
			}
		}
		if(grilleOn){
			grille(nbDivisionLongueur, nbDivisionHauteur, nbDivisionProfondeur);
		}
		conversionInverse();
	}
	
	public double tauxErreur(ArrayList<Vertex3D>vertexListOrigin){
		double result=0;
		for(int i=0; i<vertexListOrigin.size();i++){
			System.out.println(vertexListOrigin.get(i).getX()+" "+vertexList.get(i).getX());
			result+=Math.sqrt(Math.pow(vertexList.get(i).getX()-vertexListOrigin.get(i).getX(), 2)+Math.pow(vertexList.get(i).getY()-vertexListOrigin.get(i).getY(), 2)+Math.pow(vertexList.get(i).getZ()-vertexListOrigin.get(i).getZ(), 2));
		}
		result=(double)result/vertexListOrigin.size();
		return result;
	}

}
