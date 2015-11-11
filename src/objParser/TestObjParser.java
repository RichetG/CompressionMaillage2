package objParser;

import java.io.File;
import java.util.ArrayList;

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
			compression.printData();
			ArrayList<Vertex3D>list=compression.determinationMaxMin();
			System.out.println("\n");
			for(int i=0; i<list.size(); i++){
				System.out.println(list.get(i).toString());
			}
			compression.conversion();
			System.out.println("\n");
			compression.conversionInverse();
			compression.printData();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
