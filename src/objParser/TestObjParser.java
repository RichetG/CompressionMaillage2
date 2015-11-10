package objParser;

import java.io.File;
import java.util.ArrayList;

public class TestObjParser 
{
	static ObjParser objParser;
	
	public static void main(String[] args)
	{	
		if(args.length != 1)
		{
			System.out.println("error -> usage : TestObjParser file.obj");
			System.exit(1);
		}
		objParser = new ObjParser();
		try
		{
			File f = new File(args[0]);
			objParser.extract(f);
			objParser.printData();
			ArrayList<Vertex3D>list=objParser.determinationMaxMin();
			System.out.println("\n");
			for(int i=0; i<list.size(); i++){
				System.out.println(list.get(i).toString());
			}
			objParser.conversion();
			System.out.println("\n");
			objParser.conversionInverse();
			objParser.printData();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
