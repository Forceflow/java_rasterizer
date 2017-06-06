package support;
/**
 * Interface for our handlers.  This interface
 * defines for each tag in our SDL a start- and an
 * end-method. 
 */
public interface ParserHandler
{
    public void startSdl() throws Exception;
    public void endSdl() throws Exception;

    public void startCameras() throws Exception;
    public void endCameras() throws Exception;

    public void startCamera(Point3f position, Vector3f direction, Vector3f up, float fovy, String name) throws Exception;
    public void endCamera() throws Exception;

    public void startLights() throws Exception;
    public void endLights() throws Exception;

    public void startDirectionalLight(Vector3f direction, float intensity, Color3f color, String name) throws Exception;
    public void endDirectionalLight() throws Exception;

    public void startPointLight(Point3f position, float intensity, Color3f color, String name) throws Exception;
    public void endPointLight() throws Exception;

    public void startSpotLight(Point3f position, Vector3f direction, float angle, float intensity, Color3f color, String name) throws Exception;
    public void endSpotLight() throws Exception;

    public void startGeometry() throws Exception;
    public void endGeometry() throws Exception;

    public void startSphere(float radius, String name) throws Exception;
    public void endSphere() throws Exception;

    public void startCylinder(float radius, float height, boolean capped, String name) throws Exception;
    public void endCylinder() throws Exception;

    public void startCone(float radius, float height, boolean capped, String name) throws Exception;
    public void endCone() throws Exception;

    public void startTorus(float innerRadius, float outerRadius, String name) throws Exception;
    public void endTorus() throws Exception;

    public void startTeapot(float size, String name) throws Exception;
    public void endTeapot() throws Exception;

    public void startIndexedTriangleSet(Point3f [] coordinates, Vector3f [] normals, TexCoord2f [] textureCoordinates, int [] coordinateIndices, int [] normalIndices, int [] textureCoordinateIndices, String name) throws Exception;
    public void endIndexedTriangleSet() throws Exception;

    public void startTextures() throws Exception;
    public void endTextures() throws Exception;

    public void startTexture(String src, String name) throws Exception;
    public void endTexture() throws Exception;

    public void startMaterials() throws Exception;
    public void endMaterials() throws Exception;

    public void startDiffuseMaterial(Color3f color, String name) throws Exception;
    public void endDiffuseMaterial() throws Exception;

    public void startPhongMaterial(Color3f color, float shininess, String name) throws Exception;
    public void endPhongMaterial() throws Exception;

    public void startLinearCombinedMaterial(String material1Name, float weight1, String material2Name, float weight2, String name) throws Exception;
    public void endLinearCombinedMaterial() throws Exception;

    public void startScene(String cameraName, String [] lightNames, Color3f background) throws Exception;
    public void endScene() throws Exception;

    public void startShape(String geometryName, String materialName, String textureName) throws Exception;
    public void endShape() throws Exception;

    public void startRotate(Vector3f axis, float angle) throws Exception;
    public void endRotate() throws Exception;

    public void startTranslate(Vector3f vector) throws Exception;
    public void endTranslate() throws Exception;

    public void startScale(Vector3f scale) throws Exception;
    public void endScale() throws Exception;
}
