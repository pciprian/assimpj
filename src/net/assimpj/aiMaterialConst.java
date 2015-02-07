package net.assimpj;

public class aiMaterialConst {
	
	public enum aiMatkeyBase {
		_AI_MATKEY_TEXTURE_BASE("$tex.file"),
		_AI_MATKEY_UVWSRC_BASE("$tex.uvwsrc"),
		_AI_MATKEY_TEXOP_BASE("$tex.op"),
		_AI_MATKEY_MAPPING_BASE("$tex.mapping"),
		_AI_MATKEY_TEXBLEND_BASE("$tex.blend"),
		_AI_MATKEY_MAPPINGMODE_U_BASE("$tex.mapmodeu"),
		_AI_MATKEY_MAPPINGMODE_V_BASE("$tex.mapmodev"),
		_AI_MATKEY_TEXMAP_AXIS_BASE("$tex.mapaxis"),
		_AI_MATKEY_UVTRANSFORM_BASE("$tex.uvtrafo"),
		_AI_MATKEY_TEXFLAGS_BASE("$tex.flags");
		
		private String key;
		private aiMatkeyBase(String key) {
			this.key = key;
		}
		
		public String getKey() {
			return key;
		}
	}
	
	/** 
	 *  This is a very difficult topic. Different 3D packages support different
	 *  kinds of textures. For very common texture types, such as bumpmaps, the
	 *  rendering results depend on implementation details in the rendering 
	 *  pipelines of these applications. Assimp loads all texture references from
	 *  the model file and tries to determine which of the predefined texture
	 *  types below is the best choice to match the original use of the texture
	 *  as closely as possible.<br>
	 *  
	 *  In content pipelines you'll usually define how textures have to be handled,
	 *  and the artists working on models have to conform to this specification,
	 *  regardless which 3D tool they're using.
	 */	
	public enum aiTextureType {
		/** Dummy value.
		 *
		 *  No texture, but the value to be used as 'texture semantic' 
		 *  (#aiMaterialProperty::mSemantic) for all material properties 
		 *  *not* related to textures.
		 */
		aiTextureType_NONE,

	    /** The texture is combined with the result of the diffuse
		 *  lighting equation.
	     */
	    aiTextureType_DIFFUSE,

		/** The texture is combined with the result of the specular
		 *  lighting equation.
	     */
	    aiTextureType_SPECULAR,

		/** The texture is combined with the result of the ambient
		 *  lighting equation.
	     */
	    aiTextureType_AMBIENT,

		/** The texture is added to the result of the lighting
		 *  calculation. It isn't influenced by incoming light.
	     */
	    aiTextureType_EMISSIVE,

		/** The texture is a height map.
		 *
		 *  By convention, higher gray-scale values stand for
		 *  higher elevations from the base height.
	     */
	    aiTextureType_HEIGHT,

		/** The texture is a (tangent space) normal-map.
		 *
		 *  Again, there are several conventions for tangent-space
		 *  normal maps. Assimp does (intentionally) not 
		 *  distinguish here.
	     */
	    aiTextureType_NORMALS,

		/** The texture defines the glossiness of the material.
		 *
		 *  The glossiness is in fact the exponent of the specular
		 *  (phong) lighting equation. Usually there is a conversion
		 *  function defined to map the linear color values in the
		 *  texture to a suitable exponent. Have fun.
	    */
	    aiTextureType_SHININESS,

		/** The texture defines per-pixel opacity.
		 *
		 *  Usually 'white' means opaque and 'black' means 
		 *  'transparency'. Or quite the opposite. Have fun.
	    */
	    aiTextureType_OPACITY,

		/** Displacement texture
		 *
		 *  The exact purpose and format is application-dependent.
	     *  Higher color values stand for higher vertex displacements.
	    */
	    aiTextureType_DISPLACEMENT,

		/** Lightmap texture (aka Ambient Occlusion)
		 *
		 *  Both 'Lightmaps' and dedicated 'ambient occlusion maps' are
		 *  covered by this material property. The texture contains a
		 *  scaling value for the final color value of a pixel. Its
		 *  intensity is not affected by incoming light.
	    */
	    aiTextureType_LIGHTMAP,

		/** Reflection texture
		 *
		 * Contains the color of a perfect mirror reflection.
		 * Rarely used, almost never for real-time applications.
	    */
	    aiTextureType_REFLECTION,

		/** Unknown texture
		 *
		 *  A texture reference that does not match any of the definitions 
		 *  above is considered to be 'unknown'. It is still imported,
		 *  but is excluded from any further postprocessing.
	    */
	    aiTextureType_UNKNOWN;
	}	
	
	// For backward compatibility and simplicity
	public enum aiMatkeyTexture {
		AI_MATKEY_TEXTURE_DIFFUSE(aiTextureType.aiTextureType_DIFFUSE),		
		AI_MATKEY_TEXTURE_SPECULAR(aiTextureType.aiTextureType_SPECULAR),		
		AI_MATKEY_TEXTURE_AMBIENT(aiTextureType.aiTextureType_AMBIENT),		
		AI_MATKEY_TEXTURE_EMISSIVE(aiTextureType.aiTextureType_EMISSIVE),
		AI_MATKEY_TEXTURE_NORMALS(aiTextureType.aiTextureType_NORMALS),
		AI_MATKEY_TEXTURE_HEIGHT(aiTextureType.aiTextureType_HEIGHT),
		AI_MATKEY_TEXTURE_SHININESS(aiTextureType.aiTextureType_SHININESS),
		AI_MATKEY_TEXTURE_OPACITY(aiTextureType.aiTextureType_OPACITY),
		AI_MATKEY_TEXTURE_DISPLACEMENT(aiTextureType.aiTextureType_DISPLACEMENT),
		AI_MATKEY_TEXTURE_LIGHTMAP(aiTextureType.aiTextureType_LIGHTMAP),
		AI_MATKEY_TEXTURE_REFLECTION(aiTextureType.aiTextureType_REFLECTION);
		
		private aiMatkeyBase base = aiMatkeyBase._AI_MATKEY_TEXTURE_BASE;
		private aiTextureType type;
		private aiMatkeyTexture(aiTextureType type) {
			this.type = type;
		}

		public aiMatkeyBase getBase() {
			return base;
		}

		public aiTextureType getType() {
			return type;
		}
	}
	
	// For backward compatibility and simplicity
	public enum aiMatkeyUVWSrc {
		AI_MATKEY_UVWSRC_DIFFUSE(aiTextureType.aiTextureType_DIFFUSE),
		AI_MATKEY_UVWSRC_SPECULAR(aiTextureType.aiTextureType_SPECULAR),
		AI_MATKEY_UVWSRC_AMBIENT(aiTextureType.aiTextureType_AMBIENT),
		AI_MATKEY_UVWSRC_EMISSIVE(aiTextureType.aiTextureType_EMISSIVE),
		AI_MATKEY_UVWSRC_NORMALS(aiTextureType.aiTextureType_NORMALS),
		AI_MATKEY_UVWSRC_HEIGHT(aiTextureType.aiTextureType_HEIGHT),
		AI_MATKEY_UVWSRC_SHININESS(aiTextureType.aiTextureType_SHININESS),
		AI_MATKEY_UVWSRC_OPACITY(aiTextureType.aiTextureType_OPACITY),
		AI_MATKEY_UVWSRC_DISPLACEMENT(aiTextureType.aiTextureType_DISPLACEMENT),
		AI_MATKEY_UVWSRC_LIGHTMAP(aiTextureType.aiTextureType_LIGHTMAP),
		AI_MATKEY_UVWSRC_REFLECTION(aiTextureType.aiTextureType_REFLECTION);
		
		private aiMatkeyBase base = aiMatkeyBase._AI_MATKEY_UVWSRC_BASE;
		private aiTextureType type;
		private aiMatkeyUVWSrc(aiTextureType type) {
			this.type = type;
		}
		
		public aiMatkeyBase getBase() {
			return base;
		}
		
		public aiTextureType getType() {
			return type;
		}
	}
	
	// For backward compatibility and simplicity
	public enum aiMatkeyTexop {
		AI_MATKEY_TEXOP_DIFFUSE(aiTextureType.aiTextureType_DIFFUSE),
		AI_MATKEY_TEXOP_SPECULAR(aiTextureType.aiTextureType_SPECULAR),
		AI_MATKEY_TEXOP_AMBIENT(aiTextureType.aiTextureType_AMBIENT),
		AI_MATKEY_TEXOP_EMISSIVE(aiTextureType.aiTextureType_EMISSIVE),
		AI_MATKEY_TEXOP_NORMALS(aiTextureType.aiTextureType_NORMALS),
		AI_MATKEY_TEXOP_HEIGHT(aiTextureType.aiTextureType_HEIGHT),
		AI_MATKEY_TEXOP_SHININESS(aiTextureType.aiTextureType_SHININESS),
		AI_MATKEY_TEXOP_OPACITY(aiTextureType.aiTextureType_OPACITY),
		AI_MATKEY_TEXOP_DISPLACEMENT(aiTextureType.aiTextureType_DISPLACEMENT),
		AI_MATKEY_TEXOP_LIGHTMAP(aiTextureType.aiTextureType_LIGHTMAP),		
		AI_MATKEY_TEXOP_REFLECTION(aiTextureType.aiTextureType_REFLECTION);
		
		private aiMatkeyBase base = aiMatkeyBase._AI_MATKEY_TEXOP_BASE;
		private aiTextureType type;
		private aiMatkeyTexop(aiTextureType type) {
			this.type = type;
		}
		
		public aiMatkeyBase getBase() {
			return base;
		}
		public aiTextureType getType() {
			return type;
		}		
	}
	
	// For backward compatibility and simplicity
	public enum aiMatkeyMapping {
		AI_MATKEY_MAPPING_DIFFUSE(aiTextureType.aiTextureType_DIFFUSE),
		AI_MATKEY_MAPPING_SPECULAR(aiTextureType.aiTextureType_SPECULAR),
		AI_MATKEY_MAPPING_AMBIENT(aiTextureType.aiTextureType_AMBIENT),
		AI_MATKEY_MAPPING_EMISSIVE(aiTextureType.aiTextureType_EMISSIVE),
		AI_MATKEY_MAPPING_NORMALS(aiTextureType.aiTextureType_NORMALS),
		AI_MATKEY_MAPPING_HEIGHT(aiTextureType.aiTextureType_HEIGHT),
		AI_MATKEY_MAPPING_SHININESS(aiTextureType.aiTextureType_SHININESS),
		AI_MATKEY_MAPPING_OPACITY(aiTextureType.aiTextureType_OPACITY),
		AI_MATKEY_MAPPING_DISPLACEMENT(aiTextureType.aiTextureType_DISPLACEMENT),
		AI_MATKEY_MAPPING_LIGHTMAP(aiTextureType.aiTextureType_LIGHTMAP),
		AI_MATKEY_MAPPING_REFLECTION(aiTextureType.aiTextureType_REFLECTION);
		
		private aiMatkeyBase base = aiMatkeyBase._AI_MATKEY_MAPPING_BASE;
		private aiTextureType type;
		private aiMatkeyMapping(aiTextureType type) {
			this.type = type;
		}
		
		public aiMatkeyBase getBase() {
			return base;
		}
		public aiTextureType getType() {
			return type;
		}		
	}
	
	// For backward compatibility and simplicity
	public enum aiMatkeyTexblend {
		AI_MATKEY_TEXBLEND_DIFFUSE(aiTextureType.aiTextureType_DIFFUSE),
		AI_MATKEY_TEXBLEND_SPECULAR(aiTextureType.aiTextureType_SPECULAR),
		AI_MATKEY_TEXBLEND_AMBIENT(aiTextureType.aiTextureType_AMBIENT),
		AI_MATKEY_TEXBLEND_EMISSIVE(aiTextureType.aiTextureType_EMISSIVE),
		AI_MATKEY_TEXBLEND_NORMALS(aiTextureType.aiTextureType_NORMALS),
		AI_MATKEY_TEXBLEND_HEIGHT(aiTextureType.aiTextureType_HEIGHT),
		AI_MATKEY_TEXBLEND_SHININESS(aiTextureType.aiTextureType_SHININESS),
		AI_MATKEY_TEXBLEND_OPACITY(aiTextureType.aiTextureType_OPACITY),
		AI_MATKEY_TEXBLEND_DISPLACEMENT(aiTextureType.aiTextureType_DISPLACEMENT),
		AI_MATKEY_TEXBLEND_LIGHTMAP(aiTextureType.aiTextureType_LIGHTMAP),
		AI_MATKEY_TEXBLEND_REFLECTION(aiTextureType.aiTextureType_REFLECTION);
		
		private aiMatkeyBase base = aiMatkeyBase._AI_MATKEY_TEXBLEND_BASE;
		private aiTextureType type;
		private aiMatkeyTexblend(aiTextureType type) {
			this.type = type;
		}
		
		public aiMatkeyBase getBase() {
			return base;
		}
		public aiTextureType getType() {
			return type;
		}		
	}
	
	// For backward compatibility and simplicity
	public enum aiMatkeyMappingModeU {
		AI_MATKEY_MAPPINGMODE_U_DIFFUSE(aiTextureType.aiTextureType_DIFFUSE),
		AI_MATKEY_MAPPINGMODE_U_SPECULAR(aiTextureType.aiTextureType_SPECULAR),
		AI_MATKEY_MAPPINGMODE_U_AMBIENT(aiTextureType.aiTextureType_AMBIENT),
		AI_MATKEY_MAPPINGMODE_U_EMISSIVE(aiTextureType.aiTextureType_EMISSIVE),
		AI_MATKEY_MAPPINGMODE_U_NORMALS(aiTextureType.aiTextureType_NORMALS),
		AI_MATKEY_MAPPINGMODE_U_HEIGHT(aiTextureType.aiTextureType_HEIGHT),
		AI_MATKEY_MAPPINGMODE_U_SHININESS(aiTextureType.aiTextureType_SHININESS),
		AI_MATKEY_MAPPINGMODE_U_OPACITY(aiTextureType.aiTextureType_OPACITY),
		AI_MATKEY_MAPPINGMODE_U_DISPLACEMENT(aiTextureType.aiTextureType_DISPLACEMENT),
		AI_MATKEY_MAPPINGMODE_U_LIGHTMAP(aiTextureType.aiTextureType_LIGHTMAP),
		AI_MATKEY_MAPPINGMODE_U_REFLECTION(aiTextureType.aiTextureType_REFLECTION);

		private aiMatkeyBase base = aiMatkeyBase._AI_MATKEY_MAPPINGMODE_U_BASE;
		private aiTextureType type;
		private aiMatkeyMappingModeU(aiTextureType type) {
			this.type = type;
		}
		
		public aiMatkeyBase getBase() {
			return base;
		}
		public aiTextureType getType() {
			return type;
		}		
	}
	
	// For backward compatibility and simplicity
	public enum aiMatkeyMappingModeV {
		AI_MATKEY_MAPPINGMODE_V_DIFFUSE(aiTextureType.aiTextureType_DIFFUSE),
		AI_MATKEY_MAPPINGMODE_V_SPECULAR(aiTextureType.aiTextureType_SPECULAR),
		AI_MATKEY_MAPPINGMODE_V_AMBIENT(aiTextureType.aiTextureType_AMBIENT),
		AI_MATKEY_MAPPINGMODE_V_EMISSIVE(aiTextureType.aiTextureType_EMISSIVE),
		AI_MATKEY_MAPPINGMODE_V_NORMALS(aiTextureType.aiTextureType_NORMALS),
		AI_MATKEY_MAPPINGMODE_V_HEIGHT(aiTextureType.aiTextureType_HEIGHT),
		AI_MATKEY_MAPPINGMODE_V_SHININESS(aiTextureType.aiTextureType_SHININESS),
		AI_MATKEY_MAPPINGMODE_V_OPACITY(aiTextureType.aiTextureType_OPACITY),
		AI_MATKEY_MAPPINGMODE_V_DISPLACEMENT(aiTextureType.aiTextureType_DISPLACEMENT),
		AI_MATKEY_MAPPINGMODE_V_LIGHTMAP(aiTextureType.aiTextureType_LIGHTMAP),
		AI_MATKEY_MAPPINGMODE_V_REFLECTION(aiTextureType.aiTextureType_REFLECTION);

		private aiMatkeyBase base = aiMatkeyBase._AI_MATKEY_MAPPINGMODE_V_BASE;
		private aiTextureType type;
		private aiMatkeyMappingModeV(aiTextureType type) {
			this.type = type;
		}
		
		public aiMatkeyBase getBase() {
			return base;
		}
		public aiTextureType getType() {
			return type;
		}		
	}
	
	// For backward compatibility and simplicity
	public enum aiMatkeyTexmapAxis {
		AI_MATKEY_TEXMAP_AXIS_DIFFUSE(aiTextureType.aiTextureType_DIFFUSE),
		AI_MATKEY_TEXMAP_AXIS_SPECULAR(aiTextureType.aiTextureType_SPECULAR),
		AI_MATKEY_TEXMAP_AXIS_AMBIENT(aiTextureType.aiTextureType_AMBIENT),
		AI_MATKEY_TEXMAP_AXIS_EMISSIVE(aiTextureType.aiTextureType_EMISSIVE),
		AI_MATKEY_TEXMAP_AXIS_NORMALS(aiTextureType.aiTextureType_NORMALS),
		AI_MATKEY_TEXMAP_AXIS_HEIGHT(aiTextureType.aiTextureType_HEIGHT),
		AI_MATKEY_TEXMAP_AXIS_SHININESS(aiTextureType.aiTextureType_SHININESS),
		AI_MATKEY_TEXMAP_AXIS_OPACITY(aiTextureType.aiTextureType_OPACITY),
		AI_MATKEY_TEXMAP_AXIS_DISPLACEMENT(aiTextureType.aiTextureType_DISPLACEMENT),
		AI_MATKEY_TEXMAP_AXIS_LIGHTMAP(aiTextureType.aiTextureType_LIGHTMAP),
		AI_MATKEY_TEXMAP_AXIS_REFLECTION(aiTextureType.aiTextureType_REFLECTION);

		private aiMatkeyBase base = aiMatkeyBase._AI_MATKEY_TEXMAP_AXIS_BASE;
		private aiTextureType type;
		private aiMatkeyTexmapAxis(aiTextureType type) {
			this.type = type;
		}
		
		public aiMatkeyBase getBase() {
			return base;
		}
		public aiTextureType getType() {
			return type;
		}		
	}
	
	// For backward compatibility and simplicity
	public enum aiMatkeyUVTransform {
		AI_MATKEY_UVTRANSFORM_DIFFUSE(aiTextureType.aiTextureType_DIFFUSE),
		AI_MATKEY_UVTRANSFORM_SPECULAR(aiTextureType.aiTextureType_SPECULAR),
		AI_MATKEY_UVTRANSFORM_AMBIENT(aiTextureType.aiTextureType_AMBIENT),
		AI_MATKEY_UVTRANSFORM_EMISSIVE(aiTextureType.aiTextureType_EMISSIVE),
		AI_MATKEY_UVTRANSFORM_NORMALS(aiTextureType.aiTextureType_NORMALS),
		AI_MATKEY_UVTRANSFORM_HEIGHT(aiTextureType.aiTextureType_HEIGHT),
		AI_MATKEY_UVTRANSFORM_SHININESS(aiTextureType.aiTextureType_SHININESS),
		AI_MATKEY_UVTRANSFORM_OPACITY(aiTextureType.aiTextureType_OPACITY),
		AI_MATKEY_UVTRANSFORM_DISPLACEMENT(aiTextureType.aiTextureType_DISPLACEMENT),
		AI_MATKEY_UVTRANSFORM_LIGHTMAP(aiTextureType.aiTextureType_LIGHTMAP),
		AI_MATKEY_UVTRANSFORM_REFLECTION(aiTextureType.aiTextureType_REFLECTION),
		AI_MATKEY_UVTRANSFORM_UNKNOWN(aiTextureType.aiTextureType_UNKNOWN);

		private aiMatkeyBase base = aiMatkeyBase._AI_MATKEY_UVTRANSFORM_BASE;
		private aiTextureType type;
		private aiMatkeyUVTransform(aiTextureType type) {
			this.type = type;
		}
		
		public aiMatkeyBase getBase() {
			return base;
		}
		public aiTextureType getType() {
			return type;
		}		
	}

////---------------------------------------------------------------------------
	//#define AI_MATKEY_TEXFLAGS(type, N) _AI_MATKEY_TEXFLAGS_BASE,type,N
	//
	//// For backward compatibility and simplicity
	public enum aiMatkeyTexflags {
		AI_MATKEY_TEXFLAGS_DIFFUSE(aiTextureType.aiTextureType_DIFFUSE),
		AI_MATKEY_TEXFLAGS_SPECULAR(aiTextureType.aiTextureType_SPECULAR),
		AI_MATKEY_TEXFLAGS_AMBIENT(aiTextureType.aiTextureType_AMBIENT),
		AI_MATKEY_TEXFLAGS_EMISSIVE(aiTextureType.aiTextureType_EMISSIVE),
		AI_MATKEY_TEXFLAGS_NORMALS(aiTextureType.aiTextureType_NORMALS),
		AI_MATKEY_TEXFLAGS_HEIGHT(aiTextureType.aiTextureType_HEIGHT),
		AI_MATKEY_TEXFLAGS_SHININESS(aiTextureType.aiTextureType_SHININESS),
		AI_MATKEY_TEXFLAGS_OPACITY(aiTextureType.aiTextureType_OPACITY),
		AI_MATKEY_TEXFLAGS_DISPLACEMENT(aiTextureType.aiTextureType_DISPLACEMENT),
		AI_MATKEY_TEXFLAGS_LIGHTMAP(aiTextureType.aiTextureType_LIGHTMAP),
		AI_MATKEY_TEXFLAGS_REFLECTION(aiTextureType.aiTextureType_REFLECTION),
		AI_MATKEY_TEXFLAGS_UNKNOWN(aiTextureType.aiTextureType_UNKNOWN);
	
		private aiMatkeyBase base = aiMatkeyBase._AI_MATKEY_UVTRANSFORM_BASE;
		private aiTextureType type;
		private aiMatkeyTexflags(aiTextureType type) {
			this.type = type;
		}
		
		public aiMatkeyBase getBase() {
			return base;
		}
		public aiTextureType getType() {
			return type;
		}		
	}	
	
		
	//---------------------------------------------------------------------------
	// All have ,0,0 suffix
	//---------------------------------------------------------------------------	
	public static String AI_MATKEY_NAME = "?mat.name";
	public static String AI_MATKEY_TWOSIDED ="$mat.twosided";
	public static String AI_MATKEY_SHADING_MODEL ="$mat.shadingm";
	public static String AI_MATKEY_ENABLE_WIREFRAME ="$mat.wireframe";
	public static String AI_MATKEY_BLEND_FUNC ="$mat.blend";
	public static String AI_MATKEY_OPACITY ="$mat.opacity";
	public static String AI_MATKEY_BUMPSCALING ="$mat.bumpscaling";
	public static String AI_MATKEY_SHININESS ="$mat.shininess";
	public static String AI_MATKEY_REFLECTIVITY ="$mat.reflectivity";
	public static String AI_MATKEY_SHININESS_STRENGTH ="$mat.shinpercent";
	public static String AI_MATKEY_REFRACTI ="$mat.refracti";
	public static String AI_MATKEY_COLOR_DIFFUSE ="$clr.diffuse";
	public static String AI_MATKEY_COLOR_AMBIENT ="$clr.ambient";
	public static String AI_MATKEY_COLOR_SPECULAR ="$clr.specular";
	public static String AI_MATKEY_COLOR_EMISSIVE ="$clr.emissive";
	public static String AI_MATKEY_COLOR_TRANSPARENT ="$clr.transparent";
	public static String AI_MATKEY_COLOR_REFLECTIVE ="$clr.reflective";
	public static String AI_MATKEY_GLOBAL_BACKGROUND_IMAGE ="?bg.global";
}

