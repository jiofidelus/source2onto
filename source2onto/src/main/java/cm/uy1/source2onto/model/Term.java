package cm.uy1.source2onto.model;

public class Term {
	
	String name;
	String typeOfTerm;
	boolean validate=true;
	
	public Term() {
		super();
	}

	public Term(String name, String typeOfTerm, 
			boolean validate) {
		super();
		this.name = name;
		this.typeOfTerm = typeOfTerm;
		this.validate = validate;
	}

	public Term(String name, String typeOfTerm) {
		super();
		this.name = name;
		this.typeOfTerm = typeOfTerm;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeOfTerm() {
		return typeOfTerm;
	}

	public void setTypeOfTerm(String typeOfTerm) {
		this.typeOfTerm = typeOfTerm;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	
}
