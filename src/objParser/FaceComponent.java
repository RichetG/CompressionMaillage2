package objParser;

/**
 * Representation objet d'un doublon n°vertex3D/n°vertexTexture d'une face
 * @author Yann GIROS, Guillaume RICHET, Mathieu CADIO
 * @version 09/11/15
 */

public class FaceComponent
{

	// doublon n°vertex3D/n°vertexTexture
	private int v, vt;
	
	/**
	 * Constructeur
	 * @param v n°vertex3D
	 * @param vt n°vertexTexture
	 */
	public FaceComponent(int v, int vt)
	{
		this.v = v;
		this.vt = vt;
	}

	/**
	 * Getter vertex3D
	 * @return v
	 */
	public int getV()
	{
		return v;
	}

	/**
	 * Setter vertex3D
	 * @param v
	 */
	public void setV(int v)
	{
		this.v = v;
	}

	/**
	 * Getter vertexTexture
	 * @return vt
	 */
	public int getVt()
	{
		return vt;
	}

	/**
	 * Setter vertexTexture
	 * @param vt
	 */
	public void setVt(int vt)
	{
		this.vt = vt;
	}
	
	/**
	 * Affichage textuelle du doublon vetex3D/vertexTerture
	 */
	public String toString()
	{
		return v + "/" + vt;
	}
}
