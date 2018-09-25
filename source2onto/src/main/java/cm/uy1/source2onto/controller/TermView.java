package cm.uy1.source2onto.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import cm.uy1.helper.Helper;
import cm.uy1.source2onto.model.Term;
import cm.uy1.source2onto.service.TermService;

@ManagedBean
@ViewScoped
public class TermView implements Serializable{
	
	private List<Term> listTerms;
	private List<Term> filteredTerms;
	private List<Term> selectedTerms;
	
	@ManagedProperty("#{termService}")
	private TermService service;
	
	
	@PostConstruct
	public void createTerms() {
		System.out.println("**************"+service);
		listTerms = service.createTerms();
	}

	public List<Term> getListTerms() {
		return listTerms;
	}

	public void setListTerms(List<Term> listTerms) {
		this.listTerms = listTerms;
	}

	public TermService getService() {
		return service;
	}

	public void setService(TermService service) {
		this.service = service;
	}
	public List<Term> getFilteredTerms() {
		return filteredTerms;
	}

	public void setFilteredTerms(List<Term> filteredTerms) {
		this.filteredTerms = filteredTerms;
	}

	public List<Term> getSelectedTerms() {
		return selectedTerms;
	}

	public void setSelectedTerms(List<Term> selectedTerms) {
		this.selectedTerms = selectedTerms;
	}

	//Export data to ontology languages
	public void rdfExport() {
		
	}
	public void owlExport() {
		
	}
	public void textExport() {
		
	}
}
