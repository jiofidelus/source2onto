package cm.uy1.source2onto.model;

public class RegexDefinitionModel {
	private String programmingLang;
	private String regex;
	private String modelDescription;
	
	public RegexDefinitionModel(String programmingLang, 
			String regex, String modelDescription) {
		super();
		this.programmingLang = programmingLang;
		this.regex = regex;
		this.modelDescription = modelDescription;
	}

	public RegexDefinitionModel() {
		super();
	}

	public String getProgrammingLang() {
		return programmingLang;
	}

	public void setProgrammingLang(String programmingLang) {
		this.programmingLang = programmingLang;
	}

	public String getModel() {
		return regex;
	}

	public void setModel(String regex) {
		this.regex = regex;
	}

	public String getModelDescription() {
		return modelDescription;
	}

	public void setModelDescription(String modelDescription) {
		this.modelDescription = modelDescription;
	}
}
