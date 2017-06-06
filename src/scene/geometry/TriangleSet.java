package scene.geometry;

import java.util.ArrayList;

import support.Color3f;
import support.Point3f;
import support.TexCoord2f;
import support.Vector3f;
import support.Vector4f;
import util.Point4f;
import util.WorldTriangle;

public class TriangleSet extends Geometry 
{
	public TriangleSet(Point3f [] coordinates, Vector3f [] normals, TexCoord2f [] textureCoordinates, int [] coordinateIndices, int [] normalIndices, int [] textureCoordinateIndices, String name)
	{
		super(name);
		// convert arrays to actual WorldTriangles
		ArrayList<WorldTriangle> tr = new ArrayList<WorldTriangle>();
		for(int i = 0; i < coordinateIndices.length; i=i+3)
		{
			Point4f a = coordinates[coordinateIndices[i]].get4f();
			Point4f b = coordinates[coordinateIndices[i+1]].get4f();
			Point4f c = coordinates[coordinateIndices[i+2]].get4f();
			Point4f[] cornerpoints = {a,b,c};
			Vector4f a_normal = normals[normalIndices[i]].get4f();
			Vector4f b_normal = normals[normalIndices[i+1]].get4f();
			Vector4f c_normal = normals[normalIndices[i+2]].get4f();
			Vector4f[] norms = {a_normal,b_normal,c_normal};
			TexCoord2f a_tex = textureCoordinates[textureCoordinateIndices[i]];
			TexCoord2f b_tex = textureCoordinates[textureCoordinateIndices[i+1]];
			TexCoord2f c_tex = textureCoordinates[textureCoordinateIndices[i+2]];
			TexCoord2f[] texs = {a_tex,b_tex,c_tex};
			tr.add(new WorldTriangle(cornerpoints, norms, texs));
		}
	}

}
