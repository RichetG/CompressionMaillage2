import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Compression {

	public ArrayList<Vecteur> vecteurs;
	
	public Compression(){
		vecteurs=new ArrayList<Vecteur>();
	}

	/**
	 * Extraire d'un ficher obj tous les vertices
	 * @param fichier
	 */
	public void parser(String fichier){
		try {
			String[] line;
			Scanner scanner=new Scanner(new File(fichier));
			while(scanner.hasNextLine()){
				line=scanner.nextLine().replace("  ", " ").split(" ");
				if(line[0].equals("v")){
					Vecteur vecteur=new Vecteur(Double.valueOf(line[1]), Double.valueOf(line[2]), Double.valueOf(line[3]));
					vecteurs.add(vecteur);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Recuperation de 2 vecteurs avec les coords max et min
	 * 
	 */
	public ArrayList<Vecteur> determinationMaxMin(){
		ArrayList<Vecteur> limite=new ArrayList<Vecteur>(2);
		double minX=vecteurs.get(0).getX(), minY=vecteurs.get(0).getY(), minZ=vecteurs.get(0).getZ(), maxX=vecteurs.get(0).getX(), maxY=vecteurs.get(0).getY(), maxZ=vecteurs.get(0).getZ();
		for(int i=1; i< vecteurs.size(); i++){
			if(minX>=vecteurs.get(i).getX()){
				minX=vecteurs.get(i).getX();
			}
			if(minY>=vecteurs.get(i).getY()){
				minY=vecteurs.get(i).getY();
			}
			if(minZ>=vecteurs.get(i).getZ()){
				minZ=vecteurs.get(i).getZ();
			}
			if(maxX<=vecteurs.get(i).getX()){
				maxX=vecteurs.get(i).getX();
			}
			if(maxY<=vecteurs.get(i).getY()){
				maxY=vecteurs.get(i).getY();
			}
			if(maxZ<=vecteurs.get(i).getZ()){
				maxZ=vecteurs.get(i).getZ();
			}
		}
		Vecteur max=new Vecteur(maxX, maxY, maxZ);
		Vecteur min=new Vecteur(minX, minY, minZ);
		limite.add(max);
		limite.add(min);
		return limite;
	}
	
	/**
	 * conversion des vertices borné entre 0-1
	 */
	public void conversion(){
		ArrayList<Vecteur>MaxMin=determinationMaxMin();
		for(int i=0; i<vecteurs.size(); i++){
			vecteurs.get(i).setX((vecteurs.get(i).getX()+Math.abs(MaxMin.get(1).getX()))/(MaxMin.get(0).getX()+Math.abs(MaxMin.get(1).getX())));
			vecteurs.get(i).setY((vecteurs.get(i).getY()+Math.abs(MaxMin.get(1).getY()))/(MaxMin.get(0).getY()+Math.abs(MaxMin.get(1).getY())));
			vecteurs.get(i).setZ((vecteurs.get(i).getZ()+Math.abs(MaxMin.get(1).getZ()))/(MaxMin.get(0).getZ()+Math.abs(MaxMin.get(1).getZ())));
		}
	}
	
	public static void main(String[] args){
		Compression compression=new Compression();
		compression.parser("C:\\apple.obj");
		compression.conversion();System.out.println(compression.vecteurs.get(0).getX());
	}
}
