package net.assimpj;

/** @brief Data structure for a material
*
*  Material data is stored using a key-value structure. A single key-value
*  pair is called a 'material property'. C++ users should use the provided
*  member functions of aiMaterial to process material properties, C users
*  have to stick with the aiMaterialGetXXX family of unbound functions.
*  The library defines a set of standard keys (AI_MATKEY_XXX).
*/
public class aiMaterial {


	public aiMaterial() {
		// TODO
	}


	    /** @brief Retrieve an array of Type values with a specific key 
	     *  from the material
	     *
	     * @param pKey Key to search for. One of the AI_MATKEY_XXX constants.
	     * @param type .. set by AI_MATKEY_XXX
	     * @param idx .. set by AI_MATKEY_XXX
	     * @param pOut Pointer to a buffer to receive the result. 
	     * @param pMax Specifies the size of the given buffer, in Type's.
	     * Receives the number of values (not bytes!) read. 
	     * NULL is a valid value for this parameter.
	     */
	    template <typename Type>
	    aiReturn Get(const char* pKey,unsigned int type,
			unsigned int idx, Type* pOut, unsigned int* pMax) const;

	    /** @brief Retrieve a Type value with a specific key 
	     *  from the material
		 *
		 * @param pKey Key to search for. One of the AI_MATKEY_XXX constants.
	    * @param type Specifies the type of the texture to be retrieved (
	    *    e.g. diffuse, specular, height map ...)
	    * @param idx Index of the texture to be retrieved.
		 * @param pOut Reference to receive the output value
		 */
		template <typename Type>
		aiReturn Get(const char* pKey,unsigned int type,
			unsigned int idx,Type& pOut) const;

		/** Get the number of textures for a particular texture type.
		 *  @param type Texture type to check for
		 *  @return Number of textures for this type.
		 *  @note A texture can be easily queried using #GetTexture() */
		unsigned int GetTextureCount(aiTextureType type) const;

		/** Helper function to get all parameters pertaining to a 
		 *  particular texture slot from a material.
		*
		*  This function is provided just for convenience, you could also
		*  read the single material properties manually.
		*  @param type Specifies the type of the texture to be retrieved (
		*    e.g. diffuse, specular, height map ...)
		*  @param index Index of the texture to be retrieved. The function fails
		*    if there is no texture of that type with this index. 
		*    #GetTextureCount() can be used to determine the number of textures
		*    per texture type.
		*  @param path Receives the path to the texture.
		*	 NULL is a valid value.
	   *  @param mapping The texture mapping.
	   *		NULL is allowed as value.
		*  @param uvindex Receives the UV index of the texture. 
		*    NULL is a valid value.
		*  @param blend Receives the blend factor for the texture
		*	 NULL is a valid value.
		*  @param op Receives the texture operation to be performed between
		*	 this texture and the previous texture. NULL is allowed as value.
		*  @param mapmode Receives the mapping modes to be used for the texture.
		*    The parameter may be NULL but if it is a valid pointer it MUST
		*    point to an array of 3 aiTextureMapMode's (one for each
		*    axis: UVW order (=XYZ)). 
		*/

		aiReturn GetTexture(aiTextureType type,
			unsigned int  index,
			C_STRUCT aiString* path,
			aiTextureMapping* mapping	= NULL,
			unsigned int* uvindex		= NULL,
			float* blend				   = NULL,
			aiTextureOp* op				= NULL,
			aiTextureMapMode* mapmode	= NULL) const; 


		// Setters


		/** @brief Add a property with a given key and type info to the material
		 *  structure 
		 *
		 *  @param pInput Pointer to input data
		 *  @param pSizeInBytes Size of input data
		 *  @param pKey Key/Usage of the property (AI_MATKEY_XXX)
		 *  @param type Set by the AI_MATKEY_XXX macro
		 *  @param index Set by the AI_MATKEY_XXX macro
		 *  @param pType Type information hint */
		aiReturn AddBinaryProperty (const void* pInput,
			unsigned int pSizeInBytes,
			const char* pKey,
			unsigned int type ,
			unsigned int index ,
			aiPropertyTypeInfo pType);

		/** @brief Add a string property with a given key and type info to the 
		 *  material structure 
		 *
		 *  @param pInput Input string
		 *  @param pKey Key/Usage of the property (AI_MATKEY_XXX)
		 *  @param type Set by the AI_MATKEY_XXX macro
		 *  @param index Set by the AI_MATKEY_XXX macro */
		aiReturn AddProperty (const aiString* pInput,
			const char* pKey,
			unsigned int type  = 0,
			unsigned int index = 0);

		// ------------------------------------------------------------------------------
		/** @brief Add a property with a given key to the material structure 
		 *  @param pInput Pointer to the input data
		 *  @param pNumValues Number of values in the array
		 *  @param pKey Key/Usage of the property (AI_MATKEY_XXX)
		 *  @param type Set by the AI_MATKEY_XXX macro
		 *  @param index Set by the AI_MATKEY_XXX macro  */
		template<class TYPE>
		aiReturn AddProperty (const TYPE* pInput,
			unsigned int pNumValues,
			const char* pKey,
			unsigned int type  = 0,
			unsigned int index = 0);

		// ------------------------------------------------------------------------------
		/** @brief Remove a given key from the list.
		 *
		 *  The function fails if the key isn't found
		 *  @param pKey Key to be deleted */
		aiReturn RemoveProperty (const char* pKey,
			unsigned int type  = 0,
			unsigned int index = 0);

		// ------------------------------------------------------------------------------
		/** @brief Removes all properties from the material.
		 *
		 *  The data array remains allocated so adding new properties is quite fast.  */
		void Clear();

		// ------------------------------------------------------------------------------
		/** Copy the property list of a material
		 *  @param pcDest Destination material
		 *  @param pcSrc Source material
		 */
		static void CopyPropertyList(aiMaterial* pcDest, 
			const aiMaterial* pcSrc);


	    /** List of all material properties loaded. */
	    C_STRUCT aiMaterialProperty** mProperties;

	    /** Number of properties in the data base */
	    private int mNumProperties;

		 /** Storage allocated */
	    //int mNumAllocated;
	
	
}
