package objParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Parser de fichier .obj pour collecte des donnees.
 * Les donnees collectees sont les vertex (v), vertexTexture(vt) et face (f).
 * @author Yann GIROS, Guillaume RICHET, Mathieu CADIO
 * @version 03/11/15
 */

public class ObjParser
{
	// listes pour la collecte des 3 types de donnees differentes
	public static ArrayList<Vertex3D> vertexList, maxMin;
	public static ArrayList<VertexTexture> vertexTextureList;
	public static ArrayList<Face> faceList;

	/**
	 * Constructeur.
	 * Initialise les listes de donnees
	 */
	public ObjParser()
	{
		vertexList = new ArrayList<Vertex3D>();
		vertexTextureList = new ArrayList<VertexTexture>();
		faceList = new ArrayList<Face>();
	}

	/**
	 * Extraction des donnees lues dans le fichier .obj
	 * @param f le fichier .obj a parser
	 */
	public void extract(File f)
	{
		// nettoyage des listes pour nouvelle lecture de fichier
		vertexList.clear();
		vertexTextureList.clear();
		faceList.clear();
		String file = f.getName();
		try
		{
			// lecture du fichier f
			InputStream ips = new FileInputStream(file); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			String[] mot;
			String[] composant;
			while((ligne = br.readLine()) != null)	// tant qu'il reste de lignes a lire dabs le fichier...
			{
				mot = ligne.split(" ");				// recuperation de chaque terme de la ligne (separes par " ")
				switch(mot[0])						// selon le premier terme de la ligne, appliquer la situation adequate ...
				{
				case "v":	// vertex
					// creation d'un vertex3D a partir des termes collectes
					// ajout du Vertex3D dans la liste vertexList
					try
					{
						Vertex3D vertex = new Vertex3D(Double.parseDouble(mot[1]), Double.parseDouble(mot[2]), Double.parseDouble(mot[3]));
						vertexList.add(vertex);
					} catch (Exception e)
					{
						e.printStackTrace();
					}
					break;
				case "vt":	// vertex texture
					// creation d'un VertexTexture a partir des termes collectes
					// ajout du vertexTexture dans la liste vertexTextureList
					try
					{
						VertexTexture vt = new VertexTexture(Double.parseDouble(mot[1]), Double.parseDouble(mot[2]));
						vertexTextureList.add(vt);
					} catch (Exception e)
					{
						e.printStackTrace();
					}
					break;
				case "f":	//face
					// creation d'une Face
					// creation de FaceComponent pour chaque termes collectes
					// ajout des FaceComponent dans la Face
					// ajout de la Face dans la liste faceList
					Face face = new Face();
					for(int i = 1 ; i < mot.length ; i++)
					{
						composant = mot[i].split("/");
						try
						{
							FaceComponent fc = new FaceComponent(Integer.parseInt(composant[0]), Integer.parseInt(composant[1]));
							face.add(fc);
						} catch (Exception e)
						{
							e.printStackTrace();
						}
					}
					faceList.add(face);
					break;
				default:
					// pour les lignes qui ne commencent pas par "v", "vt" ou "f", ne rien faire
					break;
				}
			}
			br.close(); 
		}		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Sorti du fichier compresse
	 * @param file
	 * @throws IOException
	 */
	public void extractInverse(File file) throws IOException{
		try {
			OutputStream ops = new FileOutputStream(file);
			OutputStreamWriter opsw=new OutputStreamWriter(ops);
			BufferedWriter bw=new BufferedWriter(opsw);
			for(int i=0; i<vertexList.size(); i++){
				bw.write(vertexList.get(i).toString()+"\n");
				bw.flush();
			}
			for(int i=0; i<vertexTextureList.size(); i++){
				bw.write(vertexTextureList.get(i).toString()+"\n");
				bw.flush();
			}
			for(int i=0; i<faceList.size(); i++){
				bw.write(faceList.get(i).toString()+"\n");
				bw.flush();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * Affichage du contenu du ficher .obj extrait
	 */
	public void printData()
	{
		for(Vertex3D v : vertexList)
		{
			System.out.println(v.toString());
		}
		for(VertexTexture vt : vertexTextureList)
		{
			System.out.println(vt.toString());
		}
		for(Face f : faceList)
		{
			System.out.println(f.toString());
		}
	}
}
