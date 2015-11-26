package Modele;

import java.util.ArrayList;

/**
 * Representation objet d'une face sous forme d'une liste de FaceComponent (cf FaceComponent pour plus de details)
 * @author Yann GIROS, Guillaume RICHET, Mathieu CADIO
 * @version 03/11/15
 */

public class Face
{

	// liste de FaceComponent
	private ArrayList<FaceComponent> fcList;

	/**
	 * Constrcuteur
	 */
	public Face()
	{
		fcList = new ArrayList<FaceComponent>();
	}
	
	/**
	 * Ajoute un FaceComponent dans la liste
	 * @param fc l'element a ajouter dans la liste
	 */
	public void add(FaceComponent fc)
	{
		fcList.add(fc);
	}
	
	/**
	 * Supprime un element de la liste a l'indice index
	 * @param index l'indice de l'objet a supprimer
	 */
	public void remove(int index)
	{
		fcList.remove(index);
	}
	
	/**
	 * Renvoi l'element FaceComponent a l'indice index de la liste
	 * @param index l'indice de l'objet a renvoyer
	 * @return l'objet fc trouve a l'indice index
	 */
	public FaceComponent get(int index)
	{
		return fcList.get(index);
	}
	
	/**
	 * Renvoi sous forme textuelle f fc1, fc2, ..., fcn la liste des FaceComponent de la face
	 */
	public String toString()
	{
		String result = "f ";
		for(FaceComponent fc : fcList)
		{
			result += fc.toString() + " ";
		}
		return result;
	}
}
