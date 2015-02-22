package net.assimpj;

/** @brief Abstract interface for custom progress report receivers.
*
*  Each #Importer instance maintains its own #ProgressHandler. The default 
*  implementation provided by Assimp doesn't do anything at all. */
public abstract class ProgressHandler {

	/** @brief Progress callback.
	 *  @param percentage An estimate of the current loading progress,
	 *    in percent. Or -1.f if such an estimate is not available.
	 *
	 *  There are restriction on what you may do from within your 
	 *  implementation of this method: no exceptions may be thrown and no
	 *  non-const #Importer methods may be called. It is 
	 *  not generally possible to predict the number of callbacks 
	 *  fired during a single import.
	 *
	 *  @return Return false to abort loading at the next possible
	 *   occasion (loaders and Assimp are generally allowed to perform
	 *   all needed cleanup tasks prior to returning control to the
	 *   caller). If the loading is aborted, #Importer::ReadFile()
	 *   returns always NULL.
	 *   */
	public abstract boolean Update(float percentage /*= -1.f*/);

	// -------------------------------------------------------------------
	/** @brief Progress callback for file loading steps
	 *  @param numberOfSteps The number of total post-processing
	 *   steps
	 *  @param currentStep The index of the current post-processing
	 *   step that will run, or equal to numberOfSteps if all of
	 *   them has finished. This number is always strictly monotone
	 *   increasing, although not necessarily linearly.
	 *
	 *  @note This is currently only used at the start and the end
	 *   of the file parsing.
	 *   */
	public void UpdateFileRead(int currentStep /*= 0*/, int numberOfSteps /*= 0*/) {
		float f = numberOfSteps !=0 ? currentStep / (float)numberOfSteps : 1.0f;
		Update( f * 0.5f );
	};

	// -------------------------------------------------------------------
	/** @brief Progress callback for post-processing steps
	 *  @param numberOfSteps The number of total post-processing
	 *   steps
	 *  @param currentStep The index of the current post-processing
	 *   step that will run, or equal to numberOfSteps if all of
	 *   them has finished. This number is always strictly monotone
	 *   increasing, although not necessarily linearly.
	 *   */
	public void UpdatePostProcess(int currentStep /*= 0*/, int numberOfSteps /*= 0*/) {
		float f = numberOfSteps!=0 ? currentStep / (float)numberOfSteps : 1.0f;
		Update( f * 0.5f + 0.5f );
	};

}
