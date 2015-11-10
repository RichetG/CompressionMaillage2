package objParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ObjParser
{

	public static ArrayList<Vertex3D> vertexList, maxMin;
	public static ArrayList<VertexTexture> vertexTextureList;
	public static ArrayList<Face> faceList;

	public ObjParser()
	{
		vertexList = new ArrayList<Vertex3D>();
		vertexTextureList = new ArrayList<VertexTexture>();
		faceList = new ArrayList<Face>();
	}

	public void extract(File f)
	{
		vertexList.clear();
		vertexTextureList.clear();
		faceList.clear();
		String file = f.getName();
		try
		{
			InputStream ips = new FileInputStream(file); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			String[] mot;
			String[] composant;
			while((ligne = br.readLine()) != null)
			{
				mot = ligne.split(" ");
				switch(mot[0])
				{
				case "v":	// vertex
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
					Face face = new Face();
					for(int i = 1 ; i < mot.length ; i++){
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
	 * Recuperation de 2 vecteurs avec les coords max et min
	 * 
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
	 * conversion des vertices borné entre 0-1
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
