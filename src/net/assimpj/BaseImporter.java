package net.assimpj;

import java.util.List;
import java.util.Set;

public abstract class BaseImporter {

	/** Returns whether the class can handle the format of the given file.
	 *
	 * The implementation should be as quick as possible. A check for
	 * the file extension is enough. If no suitable loader is found with
	 * this strategy, CanRead() is called again, the 'checkSig' parameter
	 * set to true this time. Now the implementation is expected to
	 * perform a full check of the file structure, possibly searching the
	 * first bytes of the file for magic identifiers or keywords.
	 *
	 * @param pFile Path and file name of the file to be examined.
	 * @param pIOHandler The IO handler to use for accessing any file.
	 * @param checkSig Set to true if this method is called a second time.
	 *   This time, the implementation may take more time to examine the
	 *   contents of the file to be loaded for magic bytes, keywords, etc
	 *   to be able to load files with unknown/not existent file extensions.
	 * @return true if the class can read this file, false if not.
	 */
	public abstract boolean CanRead(String pFile, IOSystem pIOHandler, bool checkSig);

	/** Imports the given file and returns the imported data.
	 * If the import succeeds, ownership of the data is transferred to 
	 * the caller. If the import fails, NULL is returned. The function
	 * takes care that any partially constructed data is destroyed
	 * beforehand.
	 *
	 * @param pImp #Importer object hosting this loader.
	 * @param pFile Path of the file to be imported. 
	 * @param pIOHandler IO-Handler used to open this and possible other files.
	 * @return The imported data or NULL if failed. If it failed a 
	 * human-readable error description can be retrieved by calling 
	 * GetErrorText()
	 *
	 * @note This function is not intended to be overridden. Implement 
	 * InternReadFile() to do the import. If an exception is thrown somewhere 
	 * in InternReadFile(), this function will catch it and transform it into
	 *  a suitable response to the caller.
	 */
	public aiScene ReadFile(Importer pImp, String pFile, IOSystem pIOHandler) {
		
	}

	/** Returns the error description of the last error that occured. 
	 * @return A description of the last error that occured. An empty
	 * string if there was no error.
	 */
	public String GetErrorText() {
		return mErrorText;
	}

	/** Called prior to ReadFile().
	 * The function is a request to the importer to update its configuration
	 * basing on the Importer's configuration property list.
	 * @param pImp Importer instance
	 */
	public abstract void SetupProperties(Importer pImp);

	
	/** Called by #Importer::GetImporterInfo to get a description of 
	 *  some loader features. Importers must provide this information. */
	public abstract aiImporterDesc GetInfo();


	/** Called by #Importer::GetExtensionList for each loaded importer.
	 *  Take the extension list contained in the structure returned by
	 *  #GetInfo and insert all file extensions into the given set.
	 *  @param extension set to collect file extensions in*/
	public void GetExtensionList(Set<String> extensions) {
		
	}


	/** Imports the given file into the given scene structure. The 
	 * function is expected to throw an ImportErrorException if there is 
	 * an error. If it terminates normally, the data in aiScene is 
	 * expected to be correct. Override this function to implement the 
	 * actual importing.
	 * <br>
	 *  The output scene must meet the following requirements:<br>
	 * <ul>
	 * <li>At least a root node must be there, even if its only purpose
	 *     is to reference one mesh.</li>
	 * <li>aiMesh::mPrimitiveTypes may be 0. The types of primitives
	 *   in the mesh are determined automatically in this case.</li>
	 * <li>the vertex data is stored in a pseudo-indexed "verbose" format.
	 *   In fact this means that every vertex that is referenced by
	 *   a face is unique. Or the other way round: a vertex index may
	 *   not occur twice in a single aiMesh.</li>
	 * <li>aiAnimation::mDuration may be -1. Assimp determines the length
	 *   of the animation automatically in this case as the length of
	 *   the longest animation channel.</li>
	 * <li>aiMesh::mBitangents may be NULL if tangents and normals are
	 *   given. In this case bitangents are computed as the cross product
	 *   between normal and tangent.</li>
	 * <li>There needn't be a material. If none is there a default material
	 *   is generated. However, it is recommended practice for loaders
	 *   to generate a default material for yourself that matches the
	 *   default material setting for the file format better than Assimp's
	 *   generic default material. Note that default materials *should*
	 *   be named AI_DEFAULT_MATERIAL_NAME if they're just color-shaded
	 *   or AI_DEFAULT_TEXTURED_MATERIAL_NAME if they define a (dummy) 
	 *   texture. </li>
	 * </ul>
	 * If the AI_SCENE_FLAGS_INCOMPLETE-Flag is <b>not</b> set:<ul>
	 * <li> at least one mesh must be there</li>
	 * <li> there may be no meshes with 0 vertices or faces</li>
	 * </ul>
	 * This won't be checked (except by the validation step): Assimp will
	 * crash if one of the conditions is not met!
	 *
	 * @param pFile Path of the file to be imported.
	 * @param pScene The scene object to hold the imported data.
	 * NULL is not a valid parameter.
	 * @param pIOHandler The IO handler to use for any file access.
	 * NULL is not a valid parameter. */
	protected abstract void InternReadFile(String pFile, aiScene pScene, IOSystem pIOHandler);


	/** A utility for CanRead().
	 *
	 *  The function searches the header of a file for a specific token
	 *  and returns true if this token is found. This works for text
	 *  files only. There is a rudimentary handling of UNICODE files.
	 *  The comparison is case independent.
	 *
	 *  @param pIOSystem IO System to work with
	 *  @param file File name of the file
	 *  @param tokens List of tokens to search for
	 *  @param numTokens Size of the token array
	 *  @param searchBytes Number of bytes to be searched for the tokens.
	 */
	public static boolean SearchFileHeaderForToken(IOSystem pIOSystem, String file,
		String tokens, int numTokens, int searchBytes/* = 200 */, boolean tokensSol)/*  = false */ {
		
	}

	/** @brief Check whether a file has a specific file extension
	 *  @param pFile Input file
	 *  @param ext0 Extension to check for. Lowercase characters only, no dot!
	 *  @param ext1 Optional second extension
	 *  @param ext2 Optional third extension
	 *  @note Case-insensitive
	 */
	public static boolean SimpleExtensionCheck(String pFile, String ext0, String ext1/* = NULL */,
		String ext2/* = NULL */) {
		
	}

	// -------------------------------------------------------------------
	/** @brief Extract file extension from a string
	 *  @param pFile Input file
	 *  @return Extension without trailing dot, all lowercase
	 */
	public static String GetExtension(String pFile) {
		
	}

	// -------------------------------------------------------------------
	/** @brief Check whether a file starts with one or more magic tokens
	 *  @param pFile Input file
	 *  @param pIOHandler IO system to be used
	 *  @param magic n magic tokens
	 *  @params num Size of magic
	 *  @param offset Offset from file start where tokens are located
	 *  @param Size of one token, in bytes. Maximally 16 bytes.
	 *  @return true if one of the given tokens was found
	 *
	 *  @note For convinence, the check is also performed for the
	 *  byte-swapped variant of all tokens (big endian). Only for
	 *  tokens of size 2,4.
	 */
	public static boolean CheckMagicToken(IOSystem pIOHandler, String pFile, 
		List magic, int num, int offset/* = 0 */, int size/* = 4 */) {
		
	}

	/** An utility for all text file loaders. It converts a file to our
	 *   UTF8 character set. Errors are reported, but ignored.
	 *
	 *  @param data File buffer to be converted to UTF8 data. The buffer 
	 *  is resized as appropriate. */
	public static void ConvertToUTF8(String data) {
		
	}

	/** Utility for text file loaders which copies the contents of the
	 *  file into a memory buffer and converts it to our UTF8
	 *  representation.
	 *  @param stream Stream to read from. 
	 *  @param data Output buffer to be resized and filled with the
	 *   converted text file data. The buffer is terminated with
	 *   a binary 0. */
	public static void TextFileToBuffer(IOStream stream, String data) {
		
	}


	/** Error description in case there was one. */
	protected String mErrorText;

	/** Currently set progress handler */
	protected ProgressHandler progress;
}
