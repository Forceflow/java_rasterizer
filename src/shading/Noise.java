package shading;

import support.Vector4f;

public class Noise 
{
	private static Noise INSTANCE;
	private int n = 256;
	 //permutation of integers 0 to n-1 (Precomputed)
//	private int[] permutation = 
//	{151,160,137,91,90,15, 131,13,201,95,96,53,194,233,7,225,140,36,103,30,69,142,8,99,37,240,21,10,23,
//	190, 6,148,247,120,234,75,0,26,197,62,94,252,219,203,117,35,11,32,57,177,33,88,237,149,56,87,174,20,
//	125,136,171,168, 68,175,74,165,71,134,139,48,27,166,77,146,158,231,83,111,229,122,60,211,133,230,220,
//	105,92,41,55,46,245,40,244,102,143,54, 65,25,63,161,1,216,80,73,209,76,132,187,208,89,18,169,200,
//	196,135,130,116,188,159,86,164,100,109,198,173,186,3,64,52,217,226,250,124,123,5,202,38,147,118,126,
//	255,82,85,212,207,206,59,227,47,16,58,17,182,189,28,42,223,183,170,213,119,248,152,2,44,154,163,70,
//	221,153,101,155,167,43,172,9,129,22,39,253,19,98,108,110,79,113,224,232,178,185,112,104,218,246,97,
//	228,251,34,242,193,238,210,144,12,191,179,162,241,81,51,145,235,249,14,239,107,49,192,214,31,181,199,
//	106,157,184, 84,204,176,115,121,50,45,127,4,150,254,138,236,205,93,222,114,67,29,24,72,243,141,128,195,
//	78,66,215,61,156,180};
	// Array of random unit vectors (Precomputed at instance )
	private Vector4f[] G;
	
	public static Noise getInstance() 
	{
	      if (INSTANCE == null) {
	         synchronized(Noise.class) {
	           if (INSTANCE == null)
	             INSTANCE = new Noise();
	         }
	      }
	      return INSTANCE;
	}
	
//	protected Noise()
//	{
//		generateG();
//	}
//
//	public float noise(float x, float y, float z)
//	{
//		int i = (int) Math.floor(x);
//		int j = (int) Math.floor(y);
//		int k = (int) Math.floor(z);
//		// TODO: Deze rommel hieronder eens nakijken
//		return (omega(i,j,k,(x-i),(y-j),(z-k)) + omega(i,j,k+1,(x-i),(y-j),(z-(k+1))) +
//		omega(i,j+1,k,(x-i),(y-(j+1)),(z-k)) + omega(i,j+1,k+1,(x-i),(y-(j+1)),(z-(k+1)))+
//		omega(i+1,j,k,(x-(i+1)),(y-j),(z-k)) + omega((i+1),j,k+1,(x-(i+1)),(y-j),(z-(k+1))) +
//		omega(i+1,j+1,k,(x-(i+1)),(y-(j+1)),(z-k)) + omega((i+1),j+1,k+1,(x-(i+1)),(y-(j+1)),(z-(k+1))))*2;
//	}
//	
//	private float omega(int i, int j, int k, float u, float v, float w)
//	{
//		return omega_s(u)*omega_s(v)*omega_s(w)*(gamma(i,j,k).dot(new Vector4f(u,v,w)));
//	}
//	
//	private float omega_s(float t)
//	{
//		float t_a = Math.abs(t);
//		if (t_a < 1){return (2 * t_a * t_a * t_a) - (3 * t_a * t_a) + 1;}
//		return 0;
//	}
//	
//	private Vector4f gamma(int i, int j, int k)
//	{
//		//System.out.println(i + "|" + j+ "|" + k);
//		return G[phi(i+phi(j+phi(k)))];
//	}
//
//	private int phi(int i)
//	{
//		//System.out.println(i);
//		return permutation[Math.abs(i)%n];
//	}
//
//	private void generateG() 
//	{
//		System.out.println("Generating random unit vectors");
//		G = new Vector4f[n];
//		for(int i = 0; i<n; i++)
//		{
//			while(G[i] == null)
//			{
//				Vector4f current = new Vector4f((float)(2*Math.random() -1),
//						(float)(2*Math.random() -1),(float)(2*Math.random()-1));
//				if(current.getNorm() < 1)
//				{
//					G[i] = current.normalize();
//				}
//			}
//		}
//		System.out.println("Random unit vectors generated");
//		
//	}
	
	static public float noise(float x, float y, float z) 
	{
	      int X = (int)Math.floor(x) & 255,                  
	          Y = (int)Math.floor(y) & 255,                  
	          Z = (int)Math.floor(z) & 255;
	      x -= Math.floor(x);                                
	      y -= Math.floor(y);                                
	      z -= Math.floor(z);
	      float u = fade(x),                                
	             v = fade(y),                               
	             w = fade(z);
	      int A = p[X  ]+Y, AA = p[A]+Z, AB = p[A+1]+Z,      
	          B = p[X+1]+Y, BA = p[B]+Z, BB = p[B+1]+Z;     

	      return lerp(w, lerp(v, lerp(u, grad(p[AA  ], x  , y  , z   ), 
	                                     grad(p[BA  ], x-1, y  , z   )), 
	                             lerp(u, grad(p[AB  ], x  , y-1, z   ),  
	                                     grad(p[BB  ], x-1, y-1, z   ))),
	                     lerp(v, lerp(u, grad(p[AA+1], x  , y  , z-1 ),  
	                                     grad(p[BA+1], x-1, y  , z-1 )), 
	                             lerp(u, grad(p[AB+1], x  , y-1, z-1 ),
	                                     grad(p[BB+1], x-1, y-1, z-1 ))));
	   }
	  
	static float fade(float t) 
	{ 
		return t * t * t * (t * (t * 6 - 15) + 10); 
	}
	
	static float lerp(float t, float a, float b)
	{
		return a + t * (b - a); 
	}
	
	static float grad(int hash, float x, float y, float z)
	{
	      int h = hash & 15;                      
	      float u = h<8 ? x : y,                
	             v = h<4 ? y : h==12||h==14 ? x : z;
	      return ((h&1) == 0 ? u : -u) + ((h&2) == 0 ? v : -v);
	}
	
	static final int p[] = new int[512], permutation[] = { 151,160,137,91,90,15,
	   131,13,201,95,96,53,194,233,7,225,140,36,103,30,69,142,8,99,37,240,21,10,23,
	   190, 6,148,247,120,234,75,0,26,197,62,94,252,219,203,117,35,11,32,57,177,33,
	   88,237,149,56,87,174,20,125,136,171,168, 68,175,74,165,71,134,139,48,27,166,
	   77,146,158,231,83,111,229,122,60,211,133,230,220,105,92,41,55,46,245,40,244,
	   102,143,54, 65,25,63,161, 1,216,80,73,209,76,132,187,208, 89,18,169,200,196,
	   135,130,116,188,159,86,164,100,109,198,173,186, 3,64,52,217,226,250,124,123,
	   5,202,38,147,118,126,255,82,85,212,207,206,59,227,47,16,58,17,182,189,28,42,
	   223,183,170,213,119,248,152, 2,44,154,163, 70,221,153,101,155,167, 43,172,9,
	   129,22,39,253, 19,98,108,110,79,113,224,232,178,185, 112,104,218,246,97,228,
	   251,34,242,193,238,210,144,12,191,179,162,241, 81,51,145,235,249,14,239,107,
	   49,192,214, 31,181,199,106,157,184, 84,204,176,115,121,50,45,127, 4,150,254,
	   138,236,205,93,222,114,67,29,24,72,243,141,128,195,78,66,215,61,156,180
	   };
	static { for (int i=0; i < 256 ; i++) p[256+i] = p[i] = permutation[i]; 
	}
}
