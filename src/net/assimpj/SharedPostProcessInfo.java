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
//public 	struct Base
//	{
//		virtual ~Base()
//		{}
//	};
//
//	//! Represents data that is allocated on the heap, thus needs to be deleted
//	template <typename T>
//	struct THeapData : public Base
//	{
//		THeapData(T* in)
//			: data (in)
//		{}
//
//		~THeapData()
//		{
//			delete data;
//		}
//		T* data;
//	};
//
//	//! Represents static, by-value data not allocated on the heap
//	template <typename T>
//	struct TStaticData : public Base
//	{
//		TStaticData(T in)
//			: data (in)
//		{}
//
//		~TStaticData()
//		{}
//
//		T data;
//	};



//public:
//
//	//! Destructor
//	~SharedPostProcessInfo()	
//	{
//		Clean();
//	}
//
//	//! Remove all stored properties from the table
//	void Clean()
//	{
//		// invoke the virtual destructor for all stored properties
//		for (PropertyMap::iterator it = pmap.begin(), end = pmap.end();
//			 it != end; ++it)
//		{
//			delete (*it).second;
//		}
//		pmap.clear();
//	}
//
//	//! Add a heap property to the list
//	template <typename T>
//	void AddProperty( const char* name, T* in ){
//		AddProperty(name,(Base*)new THeapData<T>(in));
//	}
//
//	//! Add a static by-value property to the list
//	template <typename T>
//	void AddProperty( const char* name, T in ){
//		AddProperty(name,(Base*)new TStaticData<T>(in));
//	}
//
//
//	//! Get a heap property
//	template <typename T>
//	bool GetProperty( const char* name, T*& out ) const
//	{
//		THeapData<T>* t = (THeapData<T>*)GetPropertyInternal(name);
//		if(!t)
//		{
//			out = NULL;
//			return false;
//		}
//		out = t->data;
//		return true;
//	}
//
//	//! Get a static, by-value property
//	template <typename T>
//	bool GetProperty( const char* name, T& out ) const
//	{
//		TStaticData<T>* t = (TStaticData<T>*)GetPropertyInternal(name);
//		if(!t)return false;
//		out = t->data;
//		return true;
//	}
//
//	//! Remove a property of a specific type
//	void RemoveProperty( const char* name)	{
//		SetGenericPropertyPtr<Base>(pmap,name,NULL);
//	}

//private:
//
//	void AddProperty( const char* name, Base* data)	{
//		SetGenericPropertyPtr<Base>(pmap,name,data);
//	}
//
//	Base* GetPropertyInternal( const char* name) const	{
//		return GetGenericProperty<Base*>(pmap,name,NULL);
//	}

	//! Map of all stored properties
	private Map<Integer, Base> pmap;
}
