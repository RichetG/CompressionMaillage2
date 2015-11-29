package Modele;

import java.io.File;


public class TestObjParser 
{
	static Compression compression;
	
	public static void main(String[] args)
	{	
		if(args.length != 1)
		{
			System.out.println("error -> usage : TestObjParser file.obj");
			System.exit(1);
		}
		try
		{
			File f = new File(args[0]);
			compression=new Compression(f);
			System.out.println("--------fichier origine extrait--------\n");
			compression.printData();
			compression.compressionMaillage(5, 5, 5);
			System.out.println("\n--------fichier compresse--------\n");
			compression.printData();
			String tmpName=args[0].replace(".", "Compressed.");
			compression.extractInverse(new File(tmpName));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
