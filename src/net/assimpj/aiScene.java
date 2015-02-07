package net.assimpj;

import java.nio.Buffer;

/** The root structure of the imported data. 
 * 
 *  Everything that was imported from the given file can be accessed from here.
 *  Objects of this class are generally maintained and owned by Assimp, not
 *  by the caller. You shouldn't want to instance it, nor should you ever try to
 *  delete a given scene on your own.
 */
public class aiScene {

	/** 
	 * Specifies that the scene data structure that was imported is not complete.
	 * This flag bypasses some internal validations and allows the import 
	 * of animation skeletons, material libraries or camera animation paths 
	 * using Assimp. Most applications won't support such data. 
	 */
	public static int AI_SCENE_FLAGS_INCOMPLETE	= 0x1;

	/** 
	 * This flag is set by the validation postprocess-step (aiPostProcess_ValidateDS)
	 * if the validation is successful. In a validated scene you can be sure that
	 * any cross references in the data structure (e.g. vertex indices) are valid.
	 */
	public static int AI_SCENE_FLAGS_VALIDATED = 0x2;

	/** 
	 * This flag is set by the validation postprocess-step (aiPostProcess_ValidateDS)
	 * if the validation is successful but some issues have been found.
	 * This can for example mean that a texture that does not exist is referenced 
	 * by a material or that the bone weights for a vertex don't sum to 1.0 ... .
	 * In most cases you should still be able to use the import. This flag could
	 * be useful for applications which don't capture Assimp's log output.
	 */
	public static int AI_SCENE_FLAGS_VALIDATION_WARNING = 0x4;

	/** 
	 * This flag is currently only set by the aiProcess_JoinIdenticalVertices step.
	 * It indicates that the vertices of the output meshes aren't in the internal
	 * verbose format anymore. In the verbose format all vertices are unique,
	 * no vertex is ever referenced by more than one face.
	 */
	public static int AI_SCENE_FLAGS_NON_VERBOSE_FORMAT = 0x8;

	 /** 
	 * Denotes pure height-map terrain data. Pure terrains usually consist of quads, 
	 * sometimes triangles, in a regular grid. The x,y coordinates of all vertex 
	 * positions refer to the x,y coordinates on the terrain height map, the z-axis
	 * stores the elevation at a specific point.
	 *
	 * TER (Terragen) and HMP (3D Game Studio) are height map formats.
	 * @note Assimp is probably not the best choice for loading *huge* terrains -
	 * fully triangulated data takes extremely much free store and should be avoided
	 * as long as possible (typically you'll do the triangulation when you actually
	 * need to render it).
	 */
	public static int AI_SCENE_FLAGS_TERRAIN = 0x10;

	/** Any combination of the AI_SCENE_FLAGS_XXX flags. By default 
	* this value is 0, no flags are set. Most applications will
	* want to reject all scenes with the AI_SCENE_FLAGS_INCOMPLETE 
	* bit set.
	*/
	int mFlags;

	/** The root node of the hierarchy. 
	* 
	* There will always be at least the root node if the import
	* was successful (and no special flags have been set). 
	* Presence of further nodes depends on the format and content 
	* of the imported file.
	*/
	private aiNode mRootNode;

	/** The number of meshes in the scene. */
	private int mNumMeshes;

	/** The array of meshes. 
	*
	* Use the indices given in the aiNode structure to access 
	* this array. The array is mNumMeshes in size. If the
	* AI_SCENE_FLAGS_INCOMPLETE flag is not set there will always 
	* be at least ONE material.
	*/
	private aiMesh[] mMeshes;

	/** The number of materials in the scene. */
	private int mNumMaterials;

	/** The array of materials. 
	* 
	* Use the index given in each aiMesh structure to access this
	* array. The array is mNumMaterials in size. If the
	* AI_SCENE_FLAGS_INCOMPLETE flag is not set there will always 
	* be at least ONE material.
	*/
	private aiMaterial[] mMaterials;



	/** The number of animations in the scene. */
	private int mNumAnimations; 

	/** The array of animations. 
	*
	* All animations imported from the given file are listed here.
	* The array is mNumAnimations in size.
	*/
	private aiAnimation[] mAnimations;



	/** The number of textures embedded into the file */
	private int mNumTextures;

	/** The array of embedded textures.
	* 
	* Not many file formats embed their textures into the file.
	* An example is Quake's MDL format (which is also used by
	* some GameStudio versions)
	*/
	private aiTexture[] mTextures;


	/** The number of light sources in the scene. Light sources
	 * are fully optional, in most cases this attribute will be 0 
     */
	private int mNumLights;

	/** The array of light sources.
	* 
	* All light sources imported from the given file are
	* listed here. The array is mNumLights in size.
	*/
	private aiLight[] mLights;


	/** The number of cameras in the scene. Cameras
	* are fully optional, in most cases this attribute will be 0 
        */
	private int mNumCameras;

	/** The array of cameras.
	* 
	* All cameras imported from the given file are listed here.
	* The array is mNumCameras in size. The first camera in the
	* array (if existing) is the default camera view into
	* the scene.
	*/
	private aiCamera[] mCameras;


	public aiScene() {
		
	}

	//! Check whether the scene contains meshes
	//! Unless no special scene flags are set this will always be true.
	public boolean HasMeshes() { 
		return mMeshes != null && mNumMeshes > 0; 
	}

	//! Check whether the scene contains materials
	//! Unless no special scene flags are set this will always be true.
	public boolean HasMaterials() {
		return mMaterials != null && mNumMaterials > 0; 
	}

	//! Check whether the scene contains lights
	public boolean HasLights() { 
		return mLights != null && mNumLights > 0; 
	}

	//! Check whether the scene contains textures
	public boolean HasTextures() {
		return mTextures != null && mNumTextures > 0; 
	}

	//! Check whether the scene contains cameras
	public boolean HasCameras() {
		return mCameras != null && mNumCameras > 0; 
	}

	//! Check whether the scene contains animations
	public boolean HasAnimations() {
		return mAnimations != null && mNumAnimations > 0; 
	}

	/**  Internal data, do not touch */
	Buffer mPrivate;

}
