package net.assimpj;

public interface IOSystem {
	
	/** Tests for the existence of a file at the given path. 
	 *
	 * @param pFile Path to the file
	 * @return true if there is a file with this path, else false.
	 */
	public boolean Exists(String pFile);

	/**	Returns the system specific directory separator
	 *	@return	System specific directory separator
	 */
	public String getOsSeparator();

	/** Open a new file with a given path.
	 *
	 *  When the access to the file is finished, call Close() to release
	 *  all associated resources (or the virtual dtor of the IOStream).
	 *
	 *  @param pFile Path to the file
	 *  @param pMode Desired file I/O mode. Required are: "wb", "w", "wt",
	 *         "rb", "r", "rt".
	 *
	 *  @return New IOStream interface allowing the lib to access
	 *         the underlying file. 
	 *  @note When implementing this class to provide custom IO handling, 
	 *  you probably have to supply an own implementation of IOStream as well. 
	 */
	public IOStream Open(String pFile, String pMode/* = "rb" */);


	/** @brief Closes the given file and releases all resources 
	 *    associated with it.
	 *  @param pFile The file instance previously created by Open().
	 */
	public void Close(IOStream pFile);

	/** Compares two paths and check whether the point to
	 *         identical files.
	 *  
	 * The dummy implementation of this virtual member performs a 
	 * case-insensitive comparison of the given strings. The default IO
	 * system implementation uses OS mechanisms to convert relative into
	 * absolute paths, so the result can be trusted.
	 * @param one First file
	 * @param second Second file
	 * @return true if the paths point to the same file. The file needn't
	 *   be existing, however.
	 */
	public boolean ComparePaths(String one, String second);

}
