package net.assimpj;

/** A single influence of a bone on a vertex.
 */
public class aiVertexWeight {

	//! Index of the vertex which is influenced by the bone.
	private int mVertexId;

	//! The strength of the influence in the range (0...1).
	//! The influence from all bones at one vertex amounts to 1.
	private float mWeight;     


	//! Initialisation from a given index and vertex weight factor
	//! \param pID ID
	//! \param pWeight Vertex weight factor
	public aiVertexWeight( int pID, float pWeight) {
		this.mVertexId = pID;
		this.mWeight = pWeight; 
	}

}
