package cm.uy1.source2onto.controller;

import javax.faces.bean.ManagedBean;

import cm.uy1.helper.Helper;
import cm.uy1.modelUse.KnowledgeExtractionHMM;

@ManagedBean
public class KnowledgeExtraction {
	
	//Define the model configuration parameters
	private String programminLang;
	private String modelPath;
	private String model;
	private String modelDescription;
	private String modelType;
	private String knowledgeExtractionPath;
	private String dataSourcePath;
	public String getDataSourcePath() {
		return dataSourcePath;
	}

	public void setDataSourcePath(String dataSourcePath) {
		this.dataSourcePath = dataSourcePath;
	}

	private static String extrPathTmp;
	
	public String getExtrPathTmp() {
		return extrPathTmp;
	}

	public String getKnowledgeExtractionPath() {
		extrPathTmp = knowledgeExtractionPath;
		return knowledgeExtractionPath;
	}

	public KnowledgeExtraction() {
		super();
		extrPathTmp = knowledgeExtractionPath;
	}

	public KnowledgeExtraction(String programminLang, String model, String modelDescription) {
		super();
		this.programminLang = programminLang;
		this.model = model;
		this.modelDescription = modelDescription;
		
	}

	public String getProgramminLang() {
		return programminLang;
	}

	public void setProgramminLang(String programminLang) {
		this.programminLang = programminLang;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getModelDescription() {
		return modelDescription;
	}

	public void setModelDescription(String modelDescription) {
		this.modelDescription = modelDescription;
	}
	
	public String getModelPath() {
		return modelPath;
	}

	public void setModelPath(String modelPath) {
		this.modelPath = modelPath;
	}

	public String getModelType() {
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

	public void loadModel() {
		System.out.println("Loading model from the file: "+modelPath);

		if(modelType.equals("Regex"))
			model = Helper.getDataFromFiles(modelPath);
		if(modelType.equals("HMM"))
			model=Helper.formatHMMParameters(Helper.readHMMFromFile(modelPath));
		
		System.out.println("The Model is loaded!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}
	
	public void knowledgeExtraction() {
		//Calling the knowledge extraction method of the MHH/Regex module
		//Va voir comment on a procédé pour tester dans le module de tests
		KnowledgeExtractionHMM.viterbiTable(dataSourcePath, Helper.readHMMFromFile(modelPath));
	}

}
