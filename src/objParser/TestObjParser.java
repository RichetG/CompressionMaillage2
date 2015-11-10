package objParser;

import java.io.File;

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
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
