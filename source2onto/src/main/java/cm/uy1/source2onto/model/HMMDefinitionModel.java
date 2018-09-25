package cm.uy1.source2onto.model;


public class HMMDefinitionModel {
	
	private String programmingLang;
	private String pre, target, post, other;
	private String modelDescription;
	public HMMDefinitionModel(String programmingLang, String pre, String target, String post, String other,
			String modelDescription) {
		super();
		this.programmingLang = programmingLang;
		this.pre = pre;
		this.target = target;
		this.post = post;
		this.other = other;
		this.modelDescription = modelDescription;
	}
	public HMMDefinitionModel() {
		super();
	}
	public String getProgrammingLang() {
		return programmingLang;
	}
	public void setProgrammingLang(String programmingLang) {
		this.programmingLang = programmingLang;
	}
	public String getPre() {
		return pre;
	}
	public void setPre(String pre) {
		this.pre = pre;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getModelDescription() {
		return modelDescription;
	}
	public void setModelDescription(String modelDescription) {
		this.modelDescription = modelDescription;
	}
	
	
}
