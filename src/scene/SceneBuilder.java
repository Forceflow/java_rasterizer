package scene;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import org.xml.sax.InputSource;
import scene.cameras.*;
import scene.geometry.*;
import scene.graph.*;
import scene.lights.*;
import scene.materials.*;
import scene.textures.*;
import support.Color3f;
import support.Parser;
import support.ParserHandler;
import support.Point3f;
import support.TexCoord2f;
import support.Vector3f;
import util.Camerasystem;
import util.Constants;
import util.Lightsystem;

/**
  * Class used to build a scene from a given sdl file.
  * Implements the ParserHandler interface (these methods
  * need to be filled in by you).
  * 
  * Note that this class keeps the absolute path to the
  * directory where the sdl file was found.  If you put your
  * textures in the same directory, you can use this path
  * to construct an absolute file name for each texture.
  * You will probably need absolute file names when loading
  * the texture.
  */
public class SceneBuilder implements ParserHandler
{
    // the scene being build
    private Scene scene = null;
    
    // All known cameras
    private HashMap<String,Camera> cameras = new HashMap<String, Camera>();
    // All known lights
    private HashMap<String,Light> lights = new HashMap<String, Light>();
    // All known geometry
    private HashMap<String,Geometry> geometry = new HashMap<String, Geometry>();
    // All known textures
    private HashMap<String,Texture> textures = new HashMap<String, Texture>();
    // All known materials
    private HashMap<String,Material> materials = new HashMap<String, Material>();
    
    // push/pop active Node stack
    private ArrayList<Node> stack = new ArrayList<Node>();
    
    private void push(Node s)
    {
    	if(! stack.contains(s)){stack.add(s);}
    	else{System.out.println("Error: pushing element which is already in stack ...");}
    }
    
    private void pop()
    {
    	stack.remove(stack.size()-1);
    }
    
    private Node last()
    {
    	return stack.get(stack.size()-1);
    }
    
    /**
     * Load a scene.
     * @param filename The name of the file that contains the scene.
     * @return The scene, or null if something went wrong.
     * @throws FileNotFoundException The file could not be found.
     */
    public Scene loadScene(String filename) throws FileNotFoundException
    {
        // create file and file input stream
        File file = new File(filename);
        FileInputStream fileInputStream = new FileInputStream(file);

        // set the system id so that the dtd can be a relative path
        // the first 2 lines of your sdl file should always be
        //    <?xml version='1.0' encoding='utf-8'?>
        //    <!DOCTYPE Sdl SYSTEM "sdl.dtd">
        // and sdl.dtd should be in the same directory as the dtd
        // if you experience dtd problems, commend the doctype declaration
        //    <!-- <!DOCTYPE Sdl SYSTEM "sdl.dtd"> -->
        // and disable validation (see further)
        // although this is in general not a good idea

        InputSource inputSource = new InputSource(fileInputStream);
        String parentPath = file.getParentFile().getAbsolutePath() + "/";
        path = file.getParentFile().getAbsolutePath() + "/";
        inputSource.setSystemId("file:///" + file.getParentFile().getAbsolutePath() + "/");



        // create the new scene
        scene = null;

        // create the parser and parse the input file
        Parser parser = new Parser();
        parser.setHandler(this);

        // if the output bothers you, set echo to false
        // also, if loading a large file (with lots of triangles), set echo to false
        // you should leave validate to true
        // if the docuement is not validated, the parser will not detect syntax errors
        if (parser.parse(inputSource, /* validate */ true, /* echo */ false) == false)
        {
            scene = null;
        }

        // return the scene
        return scene;
    }

    /*
     *  (non-Javadoc)
     * ParserHandler callbacks
     */	

    public void startSdl() throws Exception
    {
    }

    public void endSdl() throws Exception
    {
    }

    public void startCameras() throws Exception
    {
    }

    public void endCameras() throws Exception
    {
    }

    public void startCamera(Point3f position, Vector3f direction, Vector3f up, float fovy, String name) throws Exception
    {
    	cameras.put(name, new Camera(position, direction, up, fovy, name));
    }

    public void endCamera() throws Exception
    {
    }

    public void startLights() throws Exception
    {
    }

    public void endLights() throws Exception
    {
    }

    public void startDirectionalLight(Vector3f direction, float intensity, Color3f color, String name) throws Exception
    {
    	lights.put(name,new DirectionalLight(direction, intensity, color, name));
    }

    public void endDirectionalLight() throws Exception
    {
    }

    public void startPointLight(Point3f position, float intensity, Color3f color, String name) throws Exception
    {
    	lights.put(name,new PointLight(position,intensity,color,name));
    }

    public void endPointLight() throws Exception
    {
    }

    public void startSpotLight(Point3f position, Vector3f direction, float angle, float intensity, Color3f color, String name) throws Exception
    {
    	lights.put(name,new SpotLight(position, direction, intensity, intensity, color, name));
    }

    public void endSpotLight() throws Exception
    {
    }

    public void startGeometry() throws Exception
    {
    }

    public void endGeometry() throws Exception
    {
    }

    public void startSphere(float radius, String name) throws Exception
    {
    	geometry.put(name, new Sphere(radius,name));
    }

    public void endSphere() throws Exception
    {
    }

    public void startCylinder(float radius, float height, boolean capped, String name) throws Exception
    {
    	geometry.put(name, new Cylinder(radius,height,capped,name));
    }

    public void endCylinder() throws Exception
    {
    }

    public void startCone(float radius, float height, boolean capped, String name) throws Exception
    {
    	geometry.put(name, new Cone(name, height, height, capped));
    }

    public void endCone() throws Exception
    {
    }

    public void startTorus(float innerRadius, float outerRadius, String name) throws Exception
    {
    	geometry.put(name, new Torus(innerRadius,outerRadius,name));
    }

    public void endTorus() throws Exception
    {
    }

    public void startTeapot(float size, String name) throws Exception
    {
    	geometry.put(name, new Teapot(size,name));
    }

    public void endTeapot() throws Exception
    {
    }

    public void startIndexedTriangleSet(Point3f [] coordinates, Vector3f [] normals, TexCoord2f [] textureCoordinates, int [] coordinateIndices, int [] normalIndices, int [] textureCoordinateIndices, String name) throws Exception
    {
    	geometry.put(name, new TriangleSet(coordinates, normals, textureCoordinates, textureCoordinateIndices, textureCoordinateIndices, textureCoordinateIndices, name));
    }

    public void endIndexedTriangleSet() throws Exception
    {
    }

    public void startTextures() throws Exception
    {
    }

    public void endTextures() throws Exception
    {
    }

    public void startTexture(String src, String name) throws Exception
    {
    	textures.put(name, new Texture(src,name));
    }

    public void endTexture() throws Exception
    {
    }

    public void startMaterials() throws Exception
    {
    }

    public void endMaterials() throws Exception
    {
    }

    public void startDiffuseMaterial(Color3f color, String name) throws Exception
    {
    	materials.put(name, new DiffuseMaterial(color,name));
    }

    public void endDiffuseMaterial() throws Exception
    {
    }

    public void startPhongMaterial(Color3f color, float shininess, String name) throws Exception
    {
    	materials.put(name, new PhongMaterial(color,shininess, name));
    }

    public void endPhongMaterial() throws Exception
    {
    }

    public void startLinearCombinedMaterial(String material1Name, float weight1, String material2Name, float weight2, String name) throws Exception
    {
    	materials.put(name, new LinearCombinedMaterial(materials.get(material1Name),weight1,materials.get(material2Name),weight2,name));
    }

    public void endLinearCombinedMaterial() throws Exception
    {
    }

    public void startScene(String cameraName, String [] lightNames, Color3f background) throws Exception
    {
    	Camera c = cameras.get(cameraName);
    	Camerasystem cs = new Camerasystem(Constants.DEFAULT_RESOLUTION,Constants.DEFAULT_RESOLUTION,c.getPosition().get4f(),c.getDirection().get4f(),c.getUp().get4f(),c.getFovy(),0.1f,-999f);
    	Lightsystem ll = new Lightsystem();
    	for(String s : lightNames)
    	{
    		// Only pointlights supported)
    		PointLight current = (PointLight) lights.get(s);
    		ll.addLight(new util.Light(current.getPosition().get4f(), current.getColor()));
    	}
    	SceneNode root = new SceneNode();
    	push(root);
    	scene = new Scene("myscene", cs, ll, root);
    }

    public void endScene() throws Exception
    {
    	pop(); // pop scene node from stack = end of parsing
    }

    public void startShape(String geometryName, String materialName, String textureName) throws Exception
    {
    	ShapeNode s = new ShapeNode(last(),materials.get(materialName),geometry.get(geometryName));
    	last().addKid(s); // don't push node on stack, shapes can't be subclassed anyway.
    }

    public void endShape() throws Exception
    {
    	// wasn't pushed, so we don't pop
    }

    public void startRotate(Vector3f axis, float angle) throws Exception
    {
    	RotationNode t = new RotationNode(last(),axis,angle);
    	last().addKid(t);
    	push(t); // push new node on stack
    }

    public void endRotate() throws Exception
    {
    	pop(); // pop from stack
    }

    public void startTranslate(Vector3f vector) throws Exception
    {
    	TranslationNode t = new TranslationNode(last(),vector);
    	last().addKid(t);
    	push(t); // push new node on stack
    }

    public void endTranslate() throws Exception
    {
    	pop(); // pop from stack
    }

    public void startScale(Vector3f scale) throws Exception
    {
    	ScaleNode t = new ScaleNode(last(),scale);
    	last().addKid(t);
    	push(t); // push new node on stack
    }

    public void endScale() throws Exception
    {
    	pop(); // pop from stack
    }
    
    private Scene getScene() { return scene; }

    // the path to the xml directory
    // this path can be used to put in front of the texture file name
    // to load the textures
    private String path = null;

    public String getPath() { return path; }

}
