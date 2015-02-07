package net.assimpj;

import java.util.List;

import org.lwjgl.util.vector.Matrix4f;

/** A node in the imported hierarchy. 
*
* Each node has name, a parent node (except for the root node), 
* a transformation relative to its parent and possibly several child nodes.
* Simple file formats don't support hierarchical structures - for these formats 
* the imported scene does consist of only a single root node without children.
*/
public class aiNode {
	/** The name of the node. 
	 *
	 * The name might be empty (length of zero) but all nodes which 
	 * need to be accessed afterwards by bones or anims are usually named.
	 * Multiple nodes may have the same name, but nodes which are accessed
	 * by bones (see #aiBone and #aiMesh::mBones) *must* be unique.
	 * 
	 * Cameras and lights are assigned to a specific node name - if there
	 * are multiple nodes with this name, they're assigned to each of them.
	 * <br>
	 * There are no limitations regarding the characters contained in
	 * this text. You should be able to handle stuff like whitespace, tabs,
	 * linefeeds, quotation marks, ampersands, ... .
	 */
	private String mName;

	/** The transformation relative to the node's parent. */
	/** Represents a row-major 4x4 matrix, use this for homogeneous coordinates.
	 *  From ASSIMP DOC: This is *always* a row-major matrix.
	 */	
	private Matrix4f mTransformation;

	/** Parent node. NULL if this node is the root node. */
	private aiNode mParent;

	/** The number of child nodes of this node. */
	private int mNumChildren;

	/** The child nodes of this node. NULL if mNumChildren is 0. */
	private List<aiNode> mChildren;

	/** The number of meshes of this node. */
	private int mNumMeshes;

	/** The meshes of this node. Each entry is an index into the mesh */
	private List<Integer> mMeshes;

	/** Constructor */
	public aiNode() { 
		// set all members to zero by default
		mParent = null; 
		mNumChildren = 0; mChildren = null;
		mNumMeshes = 0; mMeshes = null;
	}

	/** Construction from a specific name */
	public aiNode(String name) 
	{ 
		// set all members to zero by default
		mParent = null; 
		mNumChildren = 0; mChildren = null;
		mNumMeshes = 0; mMeshes = null;
		mName = name;
	}

	/** Searches for a node with a specific name, beginning at this
	 *  nodes. Normally you will call this method on the root node
	 *  of the scene.
	 * 
	 *  @param name Name to search for
	 *  @return NULL or a valid Node if the search was successful.
	 */
	public aiNode FindNode(String name)	{
		if (mName.equals(name)) {
			return this;
		}
		for(int i = 0; i < mNumChildren;++i) {
			aiNode p = mChildren.get(i).FindNode(name);
			if (p != null) {
				return p;
			}
		}
		// there is definitely no sub node with this name
		return null;
	}

}
