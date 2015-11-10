package objParser;

/**
 * Representation objet d'un doublon n°vertex3D/n°vertexTexture d'une face
 * @author Yann GIROS
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
	 * Renvoi 
	 * @return
	 */
	public int getV()
	{
		return v;
	}

	public void setV(int v)
	{
		this.v = v;
	}

	public int getVt()
	{
		return vt;
	}

	public void setVt(int vt)
	{
		this.vt = vt;
	}
	
	public String toString()
	{
		return v + "/" + vt;
	}
}
