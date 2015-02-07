package net.assimpj;

import java.util.Arrays;

import org.lwjgl.util.vector.Matrix4f;

/** @brief A single bone of a mesh.
*
*  A bone has a name by which it can be found in the frame hierarchy and by
*  which it can be addressed by animations. In addition it has a number of 
*  influences on vertices.
*/
public class aiBone {
	//! The name of the bone. 
	private String mName;

	//! The number of vertices affected by this bone
	//! The maximum value for this member is #AI_MAX_BONE_WEIGHTS.
	private int mNumWeights;

	//! The vertices affected by this bone
	private aiVertexWeight[] mWeights;

	//! Matrix that transforms from mesh space to bone space in bind pose
	private Matrix4f mOffsetMatrix;

	//! Default constructor
	public aiBone() {
		mNumWeights = 0; 
		mWeights = null;
	}

	//! Copy constructor
	public aiBone(aiBone other) {
		mNumWeights = other.mNumWeights;
		mOffsetMatrix = other.mOffsetMatrix;
		mName = other.mName;

		if (other.mWeights != null && other.mNumWeights > 0) {
			mWeights = Arrays.copyOf(other.mWeights, other.mNumWeights);
		}
	}
}
