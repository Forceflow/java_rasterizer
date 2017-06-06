package support;

import java.util.ArrayList;

import util.Lightsystem;
import util.Point4f;
import util.WorldTriangle;

public class ObjParser 
{
	boolean flipnormals = false;
	FileReader reader;
	ArrayList<Point4f> cornerpoints = new ArrayList<Point4f>();
	int cornerpoints_counter = 0;
	ArrayList<TexCoord2f> texturecoords = new ArrayList<TexCoord2f>();
	int texturecoords_counter = 0;
	ArrayList<Vector4f> normals = new ArrayList<Vector4f>();
	int normals_counter = 0;
	ArrayList<WorldTriangle> faces = new ArrayList<WorldTriangle>();
	int faces_counter = 0;	
	
	public ObjParser(FileReader reader)
	{
		this.reader = reader;
		fill_Lists();
	}

	private void fill_Lists() {
		while(reader.hasNextLine())
		{
			String line = reader.nextLine();
			String[] words = line.split(" ");
			if (words[0].equalsIgnoreCase("v")){parseVertex(words);}
			if (words[0].equalsIgnoreCase("vt")){parseTextureCoord(words);}
			if (words[0].equalsIgnoreCase("vn")){parseNormal(words);}
			if (words[0].equalsIgnoreCase("f")){parseFace(words);}
		}
		
	}

	private void parseNormal(String[] line) {
		Vector4f normal = new Vector4f(Float.parseFloat(line[1]),Float.parseFloat(line[2]),Float.parseFloat(line[3]));
		if (flipnormals)
		{
			normal = normal.scale(-1);
		}
		normals.add(normals_counter,normal);
		normals_counter++;
		
	}

	private void parseTextureCoord(String[] line) {
		TexCoord2f point = new TexCoord2f(Float.parseFloat(line[1]),Float.parseFloat(line[2]));
		texturecoords.add(texturecoords_counter,point);
		texturecoords_counter++;
		
	}

	private void parseVertex(String[] line)
	{
		Point4f point = new Point4f(Float.parseFloat(line[1]),Float.parseFloat(line[2]),Float.parseFloat(line[3]));
		cornerpoints.add(cornerpoints_counter,point);
		cornerpoints_counter++;
	}
	
	private void parseFace(String[] words) 
	{
		Point4f[] corners = new Point4f[3];
		TexCoord2f[] texturecoords = new TexCoord2f[3];
		Vector4f[] normals = new Vector4f[3];
		
		String[] corner1 = words[1].split("/");
		String[] corner2 = words[2].split("/");
		String[] corner3 = words[3].split("/");
		
		corners[0] = (cornerpoints.get(Integer.parseInt(corner1[0])-1));
		corners[1] = (cornerpoints.get(Integer.parseInt(corner2[0])-1));
		corners[2] = (cornerpoints.get(Integer.parseInt(corner3[0])-1));
		
		try{
			texturecoords[0] = (this.texturecoords.get(Integer.parseInt(corner1[1])-1));
			texturecoords[1] = (this.texturecoords.get(Integer.parseInt(corner2[1])-1));
			texturecoords[2] = (this.texturecoords.get(Integer.parseInt(corner3[1])-1));
		}
		catch(NumberFormatException e)
		{
			
		}
		
		
		normals[0] = (this.normals.get(Integer.parseInt(corner1[2])-1));
		normals[1] = (this.normals.get(Integer.parseInt(corner2[2])-1));
		normals[2] = (this.normals.get(Integer.parseInt(corner3[2])-1));
		
		faces.add(faces_counter,new WorldTriangle(corners,normals,texturecoords));
		faces_counter++;
	}
	
	public ArrayList<WorldTriangle> getTriangles()
	{
		System.out.println("Read " + faces_counter + " triangles from file "+reader.filename);
		return faces;
	}
}
