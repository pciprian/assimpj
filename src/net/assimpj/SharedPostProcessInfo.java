package net.assimpj;

import java.util.Map;

//---------------------------------------------------------------------------
/** Helper class to allow post-processing steps to interact with each other.
*
*  The class maintains a simple property list that can be used by pp-steps
*  to provide additional information to other steps. This is primarily
*  intended for cross-step optimizations.
*/
public class SharedPostProcessInfo {

	//! Map of all stored properties
	private Map<String, Object> pmap;

	public void AddProperty(String name, Object in ){
		pmap.put(name, in);
	}

	public Object GetProperty(String name) {
		return pmap.get(name);
	}

}
