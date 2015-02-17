package net.assimpj;

import static net.assimpj.AssertUtils.*;

abstract class BaseProcess {

	/** See the doc of #SharedPostProcessInfo for more details */
	protected SharedPostProcessInfo shared;

	/** Currently active progress handler */
	protected ProgressHandler progress;

	public BaseProcess() {
		this.shared = new SharedPostProcessInfo();
		this.progress = new ProgressHandler();
	}


	// -------------------------------------------------------------------
	/** Returns whether the processing step is present in the given flag.
	 * @param pFlags The processing flags the importer was called with. A
	 *   bitwise combination of #aiPostProcessSteps.
	 * @return true if the process is present in this flag fields, 
	 *   false if not.
	*/
	public abstract boolean IsActive(int pFlags);

	// -------------------------------------------------------------------
	/** Check whether this step expects its input vertex data to be 
	 *  in verbose format. */
	public abstract boolean RequireVerboseFormat();

	// -------------------------------------------------------------------
	/** Executes the post processing step on the given imported data.
	* The function deletes the scene if the postprocess step fails (
	* the object pointer will be set to NULL).
	* @param pImp Importer instance (pImp->mScene must be valid)
	*/
	public void ExecuteOnScene(Importer pImp) throws NullPointerException {
		assertNull(pImp, "[BaseProcess.ExecuteOnScene] Importer is null");
		assertNull(pImp.Pimpl().mScene, "[BaseProcess.ExecuteOnScene] Scene in importer is null");

		progress = pImp.GetProgressHandler();
		assertNull(progress, "[BaseProcess.ExecuteOnScene] ProgressHandler is null");

		SetupProperties(pImp);

		// catch exceptions thrown inside the PostProcess-Step
		try {
			Execute(pImp.Pimpl().mScene);

		} catch(Exception err) {
			// extract error description
			pImp.Pimpl().mErrorString = err.getMessage();
			// TODO
			//DefaultLogger::get()->error(pImp->Pimpl()->mErrorString);

			// and kill the partially imported data
			pImp.Pimpl().mScene = null;
		}		
	}

	// -------------------------------------------------------------------
	/** Called prior to ExecuteOnScene().
	* The function is a request to the process to update its configuration
	* basing on the Importer's configuration property list.
	*/
	public abstract void SetupProperties(Importer pImp);

	// -------------------------------------------------------------------
	/** Executes the post processing step on the given imported data.
	* A process should throw an ImportErrorException* if it fails.
	* This method must be implemented by deriving classes.
	* @param pScene The imported data to work at.
	*/
	public abstract void Execute(aiScene pScene);


	// -------------------------------------------------------------------
	/** Assign a new SharedPostProcessInfo to the step. This object
	 *  allows multiple postprocess steps to share data.
	 * @param sh May be NULL
	*/
	public void SetSharedData(SharedPostProcessInfo sh)	{
		shared = sh;
	}

	// -------------------------------------------------------------------
	/** Get the shared data that is assigned to the step.
	*/
	public SharedPostProcessInfo GetSharedData()	{
		return shared;
	}

}
