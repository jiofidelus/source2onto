package cm.uy1.source2onto.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import cm.uy1.modelDefinition.HMM;
import cm.uy1.source2onto.model.RegexDefinitionModel;


@ManagedBean(name="regexDefinitionController")
public class RegexDefinitionController {
	private String programmingLang;
	private String regex;
	private String modelDescription;
	private String selectedModel;
	
	
	public RegexDefinitionController(String programmingLang, 
			String regex, String modelDescription) {
		super();
		this.programmingLang = programmingLang;
		this.regex = regex;
		this.modelDescription = modelDescription;
	}
	
	@PostConstruct
	public void init() {
		modelDescription = new String();
	}

	public RegexDefinitionController() {
		super();
	}

	public String getProgrammingLang() {
		return programmingLang;
	}

	public void setProgrammingLang(String programmingLang) {
		this.programmingLang = programmingLang;
	}


	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public String getModelDescription() {
		return modelDescription;
	}

	public void setModelDescription(String modelDescription) {
		this.modelDescription = modelDescription;
	}

	public String getSelectedModel() {
		return selectedModel;
	}

	public void setSelectedModel(String selectedModel) {
		this.selectedModel = selectedModel;
	}
	
	////////////////////////////
	public void saveModel() {
		HMM hmm = new HMM();
		hmm.init();
		System.out.println("------------------The HMM parameters are ------------------------");
		System.out.println(hmm);
		System.out.println("*******************Saving the model*******************");
		System.out.println(this.programmingLang+" - " + this.regex+" - "+this.modelDescription+" - ");
	}
	public void updateModel() {
		System.out.println("*******************Saving the model*******************");
	}
	
}
