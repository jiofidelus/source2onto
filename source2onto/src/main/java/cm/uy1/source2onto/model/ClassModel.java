package cm.uy1.source2onto.model;

import java.util.List;

public class ClassModel {
	
	String name;
	//For the moment, we use the list of simple words
	//however, nextime, we will use the list to expand 
	//class hierarchy
//	List<String> synonyms;
//	List<String> subClasses;
	String synonyms;
	String subClasses;
	String description;
	boolean validate;
	public ClassModel() {
		super();
	}

	public ClassModel(String name, String synonyms, String subClasses, String description,
			boolean validate) {
		super();
		this.name = name;
		this.synonyms = synonyms;
		this.subClasses = subClasses;
		this.description = description;
		this.validate = validate;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSynonyms() {
		return synonyms;
	}
	public void setSynonyms(String synonyms) {
		this.synonyms = synonyms;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isValidate() {
		return validate;
	}
	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public String getSubClasses() {
		return subClasses;
	}

	public void setSubClasses(String subClasses) {
		this.subClasses = subClasses;
	}
	
	

}
