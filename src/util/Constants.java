package util;

import support.Color3f;

public abstract class Constants {
	
	// render settings
	
	// colors
	public static final boolean NOSHADING = false;
	public static final boolean Ambient = false;
	public static final boolean Diffuse = true;
	public static final boolean Phong = true;
	public static final boolean BumpMapping = true;

	// various parameters
	public static final boolean SCREENSPACE_INTERPOLATION = true;
	public static final float BSP_EPSILON = 0.1f;
	public static final float PHONG_P = 64;
	public static final Color3f PHONG_COLOUR = new Color3f(1,1,1);
	public static final Color3f DIFFUSE_COLOUR = new Color3f(1,1,1);
	public static final float BUMPMAPPING_EPSILON = 0.001f;
	public static final int DEFAULT_RESOLUTION = 512;

	// speed optimalisations
	public static final boolean BACKFACE_ELIMINATION = true; // when all objects are closed
	
	// debug switches
	public static final boolean DEBUG_SCENEGRAPH = true;
}
