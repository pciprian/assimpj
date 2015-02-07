package net.assimpj;

import org.lwjgl.util.Color;
import org.lwjgl.util.vector.Vector3f;

/** NOT CURRENTLY IN USE. An AnimMesh is an attachment to an #aiMesh stores per-vertex 
 *  animations for a particular frame.
 *  
 *  You may think of an #aiAnimMesh as a `patch` for the host mesh, which
 *  replaces only certain vertex data streams at a particular time. 
 *  Each mesh stores n attached attached meshes (#aiMesh::mAnimMeshes).
 *  The actual relationship between the time line and anim meshes is 
 *  established by #aiMeshAnim, which references singular mesh attachments
 *  by their ID and binds them to a time offset.
*/
public class aiAnimMesh {

	/** Replacement for aiMesh::mVertices. If this array is non-NULL, 
	 *  it *must* contain mNumVertices entries. The corresponding
	 *  array in the host mesh must be non-NULL as well - animation
	 *  meshes may neither add or nor remove vertex components (if
	 *  a replacement array is NULL and the corresponding source
	 *  array is not, the source data is taken instead)*/
	private Vector3f[] mVertices;

	/** Replacement for aiMesh::mNormals.  */
	private Vector3f[] mNormals;

	/** Replacement for aiMesh::mTangents. */
	private Vector3f[] mTangents;

	/** Replacement for aiMesh::mBitangents. */
	private Vector3f[] mBitangents;

	/** Replacement for aiMesh::mColors */
	private Color[] mColors; // //new Color[aiMeshConst.AI_MAX_NUMBER_OF_COLOR_SETS];

	/** Replacement for aiMesh::mTextureCoords */
	private Vector3f[] mTextureCoords; // new Vector3f[aiMeshConst.AI_MAX_NUMBER_OF_TEXTURECOORDS];

	/** The number of vertices in the aiAnimMesh, and thus the length of all
	 * the member arrays.
	 *
	 * This has always the same value as the mNumVertices property in the
	 * corresponding aiMesh. It is duplicated here merely to make the length
	 * of the member arrays accessible even if the aiMesh is not known, e.g.
	 * from language bindings.
	 */
	private int mNumVertices;

	public aiAnimMesh() {
		mVertices = null;
		mNormals = null;
		mTangents = null;
		mBitangents = null;
		// fixme consider moving this to the ctor initializer list as well
		for(int a = 0; a < aiMeshConst.AI_MAX_NUMBER_OF_TEXTURECOORDS; a++) {
			mTextureCoords[a] = null;
		}
		for(int a = 0; a < aiMeshConst.AI_MAX_NUMBER_OF_COLOR_SETS; a++) {
			mColors[a] = null;
		}			
	}

	/** Check whether the anim mesh overrides the vertex positions 
	 *  of its host mesh*/ 
	public boolean HasPositions() {
		return mVertices != null; 
	}

	/** Check whether the anim mesh overrides the vertex normals
	 *  of its host mesh*/ 
	public boolean HasNormals() { 
		return mNormals != null; 
	}

	/** Check whether the anim mesh overrides the vertex tangents
	 *  and bitangents of its host mesh. As for aiMesh,
	 *  tangents and bitangents always go together. */ 
	public boolean HasTangentsAndBitangents() { 
		return mTangents != null; 
	}

	/** Check whether the anim mesh overrides a particular
	 * set of vertex colors on his host mesh. 
	 *  @param pIndex 0<index<AI_MAX_NUMBER_OF_COLOR_SETS */ 
	public boolean HasVertexColors(int pIndex) { 
		return pIndex >= aiMeshConst.AI_MAX_NUMBER_OF_COLOR_SETS ? false : mColors[pIndex] != null; 
	}

	/** Check whether the anim mesh overrides a particular
	 * set of texture coordinates on his host mesh. 
	 *  @param pIndex 0<index<AI_MAX_NUMBER_OF_TEXTURECOORDS */ 
	public boolean HasTextureCoords(int pIndex) { 
		return pIndex >= aiMeshConst.AI_MAX_NUMBER_OF_TEXTURECOORDS ? false : mTextureCoords[pIndex] != null; 
	}
}
