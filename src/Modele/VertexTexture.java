package Modele;

/**
 * Representation objet d'un vertex texture (vt) sous forme de doublon x y
 * @author Yann GIROS, Guillaume RICHET, Mathieu CADIO
 * @version 03/11/15
 *
 */
public class VertexTexture {

	// coordonnees du vertex texture
	private double x, y;
	
	/**
	 * Constructeur
	 * @param x coordonnee en x
	 * @param y coordonnee en y
	 */
	public VertexTexture(double x, double y){
		this.x = x;
		this.y = y;
	}

	/**
	 * Renvoi la coordonnee x du vertex texture 
	 * @return x
	 */
	public double getX() {
		return x;
	}

	/**
	 * Modifie la coordonnee y du vertex texture
	 * @param x la nouvelle coordonnee du vertex texture 
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Renvoi la coordonnee y du vertex texture
	 * @return y
	 */
	public double getY() {
		return y;
	}

	/**
	 * Modifie la coordonnee y du vertex texture
	 * @param y
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * Renvoi sous forme textuelle vt x y le vertex texture
	 */
	public String toString()
	{
		return "vt " + x + " " + y;
	}
}
