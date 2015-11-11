package objParser;

/**
 * Representation objet d'un vertex 3D sous forme de triplet x, y, z
 * @author Yann GIROS, Guillaume RICHET, Mathieu CADIO
 * @version 03/11/15
 *
 */
public class Vertex3D
{
	// coordonnees du vertex
	private double x, y, z;
	
	/**
	 * Constructeur
	 * @param x coordonnee en x
	 * @param y coordonnee en y
	 * @param z coordonnee en z
	 */
	public Vertex3D(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Renvoi la coordonnee x du vertex
	 * @return x
	 */
	public double getX()
	{
		return x;
	}

	/**
	 * Modifie la coordonnee x du vertex
	 * @param x la nouvelle coordonnee du vertex
	 */
	public void setX(double x)
	{
		this.x = x;
	}

	/**
	 * Renvoi la coordonnee y du vertex
	 * @return y
	 */
	public double getY() 
	{
		return y;
	}

	/**
	 * Modifie la coordonnee y du vertex
	 * @param y la nouvelle coordonnee du vertex
	 */
	public void setY(double y) 
	{
		this.y = y;
	}

	/**
	 * Renvoi la coordonnee z du vertex
	 * @return z
	 */
	public double getZ() 
	{
		return z;
	}

	/**
	 * Modifie la coordonnee z du vertex
	 * @param z la nouvelle coordonnee du vertex
	 */
	public void setZ(double z) 
	{
		this.z = z;
	}
	
	/**
	 * Renvoi sous forme textuelle v x y z le vertex
	 */
	public String toString()
	{
		return "v " + x + " " + y + " " + z;
	}
}
