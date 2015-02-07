package net.assimpj;

/** A single face in a mesh, referring to multiple vertices. 
*
* If mNumIndices is 3, we call the face 'triangle', for mNumIndices > 3 
* it's called 'polygon' (hey, that's just a definition!).
* <br>
* aiMesh::mPrimitiveTypes can be queried to quickly examine which types of
* primitive are actually present in a mesh. The #aiProcess_SortByPType flag 
* executes a special post-processing algorithm which splits meshes with
* *different* primitive types mixed up (e.g. lines and triangles) in several
* 'clean' submeshes. Furthermore there is a configuration option (
* #AI_CONFIG_PP_SBP_REMOVE) to force #aiProcess_SortByPType to remove 
* specific kinds of primitives from the imported scene, completely and forever.
* In many cases you'll probably want to set this setting to 
* @code 
* aiPrimitiveType_LINE|aiPrimitiveType_POINT
* @endcode
* Together with the #aiProcess_Triangulate flag you can then be sure that
* #aiFace::mNumIndices is always 3. 
* @note Take a look at the @link data Data Structures page @endlink for
* more information on the layout and winding order of a face.
*/
public class aiFace {

	//! Number of indices defining this face. 
	//! The maximum value for this member is #AI_MAX_FACE_INDICES.
	private int mNumIndices; 

	//! Pointer to the indices array. Size of the array is given in numIndices.
	private int[] mIndices;   

	public aiFace()	{
		mNumIndices = 0; mIndices = null;
	}

	//! Copy constructor. Copy the index array
	public aiFace(aiFace o)	{
		this.mNumIndices = o.mNumIndices;
		this.mIndices = o.mIndices;
	}

	//! Comparison operator. Checks whether the index array 
	//! of two faces is identical	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof aiFace))
			return false;
		aiFace face = (aiFace)obj;
		if (mIndices == face.mIndices)
			return true;
		else if (mIndices != null && mNumIndices == face.mNumIndices) {
			for (int i = 0;i < this.mNumIndices;++i) {
				if (mIndices[i] != face.mIndices[i])
					return false;
			}
			return true;
		}
		return false;
	}

}
