package cm.uy1.source2onto.model;

import java.util.List;

public class AttributeModel {

	String name;
	String domain;
	String range;
	List<String> synonyms;
	String description;
	boolean validate;
	public AttributeModel() {
		super();
	}
	public AttributeModel(String name, String domain, String range, List<String> synonyms, String description,
			boolean validate) {
		super();
		this.name = name;
		this.domain = domain;
		this.range = range;
		this.synonyms = synonyms;
		this.description = description;
		this.validate = validate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public List<String> getSynonyms() {
		return synonyms;
	}
	public void setSynonyms(List<String> synonyms) {
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
	
}
