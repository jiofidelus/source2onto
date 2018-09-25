package cm.uy1.source2onto.model;

public class ActionModel {
	
	String relationName;
	String domain;
	String range;
	int minCardinalityDomain;
	int minCardinalityRange;
	int maxCardinalityDomain;
	public ActionModel() {
		super();
	}
	int maxCardinalityRange;
	public ActionModel(String relationName, String domain, String range, int minCardinalityDomain,
			int minCardinalityRange, int maxCardinalityDomain, int maxCardinalityRange) {
		super();
		this.relationName = relationName;
		this.domain = domain;
		this.range = range;
		this.minCardinalityDomain = minCardinalityDomain;
		this.minCardinalityRange = minCardinalityRange;
		this.maxCardinalityDomain = maxCardinalityDomain;
		this.maxCardinalityRange = maxCardinalityRange;
	}
	public String getRelationName() {
		return relationName;
	}
	public void setRelationName(String relationName) {
		this.relationName = relationName;
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
	public int getMinCardinalityDomain() {
		return minCardinalityDomain;
	}
	public void setMinCardinalityDomain(int minCardinalityDomain) {
		this.minCardinalityDomain = minCardinalityDomain;
	}
	public int getMinCardinalityRange() {
		return minCardinalityRange;
	}
	public void setMinCardinalityRange(int minCardinalityRange) {
		this.minCardinalityRange = minCardinalityRange;
	}
	public int getMaxCardinalityDomain() {
		return maxCardinalityDomain;
	}
	public void setMaxCardinalityDomain(int maxCardinalityDomain) {
		this.maxCardinalityDomain = maxCardinalityDomain;
	}
	public int getMaxCardinalityRange() {
		return maxCardinalityRange;
	}
	public void setMaxCardinalityRange(int maxCardinalityRange) {
		this.maxCardinalityRange = maxCardinalityRange;
	}
	
	

}
