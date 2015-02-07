package net.assimpj;

public enum aiOrigin {
	/** Beginning of the file */
	aiOrigin_SET,	
	/** Current position of the file pointer */
	aiOrigin_CUR,		
	/** End of the file, offsets must be negative */
	aiOrigin_END
}
