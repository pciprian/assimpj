package net.assimpj;

import org.lwjgl.util.Color;
import org.lwjgl.util.vector.Vector3f;

/** @brief A mesh represents a geometry or model with a single material. 
*
* It usually consists of a number of vertices and a series of primitives/faces 
* referencing the vertices. In addition there might be a series of bones, each 
* of them addressing a number of vertices with a certain weight. Vertex data 
* is presented in channels with each channel containing a single per-vertex 
* information such as a set of texture coords or a normal vector.
* If a data pointer is non-null, the corresponding data stream is present.
* From C++-programs you can also use the comfort functions Has*() to
* test for the presence of various data streams.
*
* A Mesh uses only a single material which is referenced by a material ID.
* @note The mPositions member is usually not optional. However, vertex positions 
* *could* be missing if the #AI_SCENE_FLAGS_INCOMPLETE flag is set in 
* @code
* aiScene::mFlags
* @endcode
*/
public class aiMesh {

	/** Bitwise combination of the members of the #aiPrimitiveType enum.
	 * This specifies which types of primitives are present in the mesh.
	 * The "SortByPrimitiveType"-Step can be used to make sure the 
	 * output meshes consist of one primitive type each.
	 */
	private int mPrimitiveTypes;

	/** The number of vertices in this mesh. 
	* This is also the size of all of the per-vertex data arrays.
	* The maximum value for this member is #AI_MAX_VERTICES.
	*/
	private int mNumVertices;

	/** The number of primitives (triangles, polygons, lines) in this  mesh. 
	* This is also the size of the mFaces array.
	* The maximum value for this member is #AI_MAX_FACES.
	*/
	private int mNumFaces;

	/** Vertex positions. 
	* This array is always present in a mesh. The array is 
	* mNumVertices in size. 
	*/
	private Vector3f[] mVertices;

	/** Vertex normals. 
	* The array contains normalized vectors, NULL if not present. 
	* The array is mNumVertices in size. Normals are undefined for
	* point and line primitives. A mesh consisting of points and
	* lines only may not have normal vectors. Meshes with mixed
	* primitive types (i.e. lines and triangles) may have normals,
	* but the normals for vertices that are only referenced by
	* point or line primitives are undefined and set to QNaN (WARN:
	* qNaN compares to inequal to *everything*, even to qNaN itself.
	* Using code like this to check whether a field is qnan is:
	* @code
	* #define IS_QNAN(f) (f != f)
	* @endcode
	* still dangerous because even 1.f == 1.f could evaluate to false! (
	* remember the subtleties of IEEE754 artithmetics). Use stuff like
	* @c fpclassify instead.
	* @note Normal vectors computed by Assimp are always unit-length.
	* However, this needn't apply for normals that have been taken
	*   directly from the model file.
	*/
	private Vector3f[] mNormals;

	/** Vertex tangents. 
	* The tangent of a vertex points in the direction of the positive 
	* X texture axis. The array contains normalized vectors, NULL if
	* not present. The array is mNumVertices in size. A mesh consisting 
	* of points and lines only may not have normal vectors. Meshes with 
	* mixed primitive types (i.e. lines and triangles) may have 
	* normals, but the normals for vertices that are only referenced by
	* point or line primitives are undefined and set to qNaN.  See
	* the #mNormals member for a detailled discussion of qNaNs.
	* @note If the mesh contains tangents, it automatically also 
	* contains bitangents.
	*/
	private Vector3f[] mTangents;

	/** Vertex bitangents. 
	* The bitangent of a vertex points in the direction of the positive 
	* Y texture axis. The array contains normalized vectors, NULL if not
	* present. The array is mNumVertices in size. 
	* @note If the mesh contains tangents, it automatically also contains
	* bitangents.  
	*/
	private Vector3f[] mBitangents;

	/** Vertex color sets. 
	* A mesh may contain 0 to #AI_MAX_NUMBER_OF_COLOR_SETS vertex 
	* colors per vertex. NULL if not present. Each array is
	* mNumVertices in size if present.
	*/
	private Color[] mColors; //new Color[aiMeshConst.AI_MAX_NUMBER_OF_COLOR_SETS];

	/** Vertex texture coords, also known as UV channels.
	* A mesh may contain 0 to AI_MAX_NUMBER_OF_TEXTURECOORDS per
	* vertex. NULL if not present. The array is mNumVertices in size. 
	*/
	private Vector3f[] mTextureCoords; // new Vector3f[aiMeshConst.AI_MAX_NUMBER_OF_TEXTURECOORDS];

	/** Specifies the number of components for a given UV channel.
	* Up to three channels are supported (UVW, for accessing volume
	* or cube maps). If the value is 2 for a given channel n, the
	* component p.z of mTextureCoords[n][p] is set to 0.0f.
	* If the value is 1 for a given channel, p.y is set to 0.0f, too.
	* @note 4D coords are not supported 
	*/
	private int[] mNumUVComponents; // new int[aiMeshConst.AI_MAX_NUMBER_OF_TEXTURECOORDS];

	/** The faces the mesh is constructed from. 
	* Each face refers to a number of vertices by their indices. 
	* This array is always present in a mesh, its size is given 
	* in mNumFaces. If the #AI_SCENE_FLAGS_NON_VERBOSE_FORMAT
	* is NOT set each face references an unique set of vertices.
	*/
	private aiFace[] mFaces;

	/** The number of bones this mesh contains. 
	* Can be 0, in which case the mBones array is NULL. 
	*/
	private int mNumBones;

	/** The bones of this mesh. 
	* A bone consists of a name by which it can be found in the
	* frame hierarchy and a set of vertex weights.
	*/
	private aiBone[] mBones;

	/** The material used by this mesh. 
	 * A mesh does use only a single material. If an imported model uses
	 * multiple materials, the import splits up the mesh. Use this value 
	 * as index into the scene's material list.
	 */
	private int mMaterialIndex;

	/** Name of the mesh. Meshes can be named, but this is not a
	 *  requirement and leaving this field empty is totally fine.
	 *  There are mainly three uses for mesh names: 
	 *   - some formats name nodes and meshes independently.
	 *   - importers tend to split meshes up to meet the
	 *      one-material-per-mesh requirement. Assigning
	 *      the same (dummy) name to each of the result meshes
	 *      aids the caller at recovering the original mesh
	 *      partitioning.
	 *   - Vertex animations refer to meshes by their names.
	 **/
	private String mName;


	/** NOT CURRENTLY IN USE. The number of attachment meshes */
	private int mNumAnimMeshes;

	/** NOT CURRENTLY IN USE. Attachment meshes for this mesh, for vertex-based animation. 
	 *  Attachment meshes carry replacement data for some of the
	 *  mesh'es vertex components (usually positions, normals). */
	private aiAnimMesh[] mAnimMeshes;

	public aiMesh() {
		mNumVertices    = 0; 
		mNumFaces       = 0;

		mNumAnimMeshes = 0;

		mPrimitiveTypes = 0;
		mVertices = null; mFaces    = null;
		mNormals  = null; mTangents = null;
		mBitangents = null;
		mAnimMeshes = null;

		for(int a = 0; a < aiMeshConst.AI_MAX_NUMBER_OF_TEXTURECOORDS; a++) {
			mNumUVComponents[a] = 0;
			mTextureCoords[a] = null;
		}
		for(int a = 0; a < aiMeshConst.AI_MAX_NUMBER_OF_COLOR_SETS; a++) {
			mColors[a] = null;
		}
		mNumBones = 0; mBones = null;
		mMaterialIndex = 0;
		mNumAnimMeshes = 0;
		mAnimMeshes = null;
	}

	//! Check whether the mesh contains positions. Provided no special
	//! scene flags are set (such as #AI_SCENE_FLAGS_ANIM_SKELETON_ONLY), 
	//! this will always be true 
	public boolean HasPositions()  
		{ return mVertices != null && mNumVertices > 0; }

	//! Check whether the mesh contains faces. If no special scene flags
	//! are set this should always return true
	public boolean HasFaces()  
		{ return mFaces != null && mNumFaces > 0; }

	//! Check whether the mesh contains normal vectors
	public boolean HasNormals()  
		{ return mNormals != null && mNumVertices > 0; }

	//! Check whether the mesh contains tangent and bitangent vectors
	//! It is not possible that it contains tangents and no bitangents
	//! (or the other way round). The existence of one of them
	//! implies that the second is there, too.
	public boolean HasTangentsAndBitangents()  
		{ return mTangents != null && mBitangents != null && mNumVertices > 0; }

	//! Check whether the mesh contains a vertex color set
	//! \param pIndex Index of the vertex color set
	public boolean HasVertexColors(int pIndex)
	{ 
		if( pIndex >= aiMeshConst.AI_MAX_NUMBER_OF_COLOR_SETS) 
			return false; 
		else 
			return mColors[pIndex] != null && mNumVertices > 0; 
	}

	//! Check whether the mesh contains a texture coordinate set
	//! \param pIndex Index of the texture coordinates set
	public boolean HasTextureCoords(int pIndex)
	{ 
		if( pIndex >= aiMeshConst.AI_MAX_NUMBER_OF_TEXTURECOORDS) 
			return false; 
		else 
			return mTextureCoords[pIndex] != null && mNumVertices > 0; 
	}

	//! Get the number of UV channels the mesh contains
	public int GetNumUVChannels()  
	{
		int n = 0;
		while (n < aiMeshConst.AI_MAX_NUMBER_OF_TEXTURECOORDS && mTextureCoords[n] != null) {
			++n;
		}
		return n;
	}

	//! Get the number of vertex color channels the mesh contains
	public int GetNumColorChannels() 
	{
		int n = 0;
		while (n < aiMeshConst.AI_MAX_NUMBER_OF_COLOR_SETS && mColors[n] != null) {
			++n;
		}
		return n;
	}

	//! Check whether the mesh contains bones
	public boolean HasBones()
		{ return mBones != null && mNumBones > 0; }

}
