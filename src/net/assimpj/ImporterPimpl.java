package net.assimpj;

import java.util.Vector;
import java.util.Map;

import org.lwjgl.util.vector.Matrix4f;

class ImporterPimpl {
	/** IO handler to use for all file accesses. */
	public IOSystem mIOHandler;
	public boolean mIsDefaultHandler;
	
	/** Progress handler for feedback. */
	public ProgressHandler mProgressHandler;
	public boolean mIsDefaultProgressHandler;
	
	/** Format-specific importer worker objects - one for each format we can read.*/
	public Vector<BaseImporter> mImporter;
	
	/** Post processing steps we can apply at the imported data. */
	public Vector<BaseProcess> mPostProcessingSteps;
	
	/** The imported data, if ReadFile() was successful, NULL otherwise. */
	public aiScene mScene;
	
	/** The error description, if there was one. */
	String mErrorString;
	
	/** List of integer properties */
	public Map<Integer, Integer> mIntProperties;
	
	/** List of floating-point properties */
	public Map<Integer, Float> mFloatProperties;
	
	/** List of string properties */
	public Map<Integer, String> mStringProperties;
	
	/** List of Matrix properties */
	public Map<Integer, Matrix4f> mMatrixProperties;
	
	/** Used for testing - extra verbose mode causes the ValidateDataStructure-Step
	 *  to be executed before and after every single postprocess step */
	public boolean bExtraVerbose;
	
	/** Used by post-process steps to share data */
	SharedPostProcessInfo mPPShared;
	
}
