package net.assimpj;

import java.nio.Buffer;

/** 
 * If you implement this interface, be sure to also provide an
 *  implementation for IOSystem that creates instances of your custom IO class.
 */
public interface IOStream {
	/** Read from the file
	 *
	 * See fread() for more details
	 * This fails for write-only files */
	int Read(Buffer pvBuffer, int pSize, int pCount);

	/** Write to the file
	*
	* See fwrite() for more details
	* This fails for read-only files */
    int Write(Buffer pvBuffer, int pSize, int pCount);

	/** Set the read/write cursor of the file
	 *
	 * Note that the offset is _negative_ for aiOrigin_END.
	 * See fseek() for more details */
    aiReturn Seek(int pOffset, aiOrigin pOrigin);

	/** Get the current position of the read/write cursor
	 *
	 * See ftell() for more details */
    int Tell();

	/**	Returns filesize
	 *	Returns the filesize. */
	int FileSize();

	/**	Flush the contents of the file buffer (for writers) 
	 *	See fflush() for more details.
	 */
	void Flush();

}
