package cm.uy1.source2onto.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;

import cm.uy1.source2onto.model.ClassModel;
import cm.uy1.source2onto.service.ClassService;

@ManagedBean
public class ClassView {
	
	List<ClassModel> listClass;
	
	List<ClassModel> listFilteredClasses;
	
	@ManagedProperty("#{classService}")
	ClassService service;
	
	@PostConstruct
	public void createClasses() {
		listClass=service.createClasses();
	}

	public List<ClassModel> getListClass() {
		return listClass;
	}

	public List<ClassModel> getListFilteredClasses() {
		return listFilteredClasses;
	}

	public void setListFilteredClasses(List<ClassModel> listFilteredClasses) {
		this.listFilteredClasses = listFilteredClasses;
	}

	public void setListClass(List<ClassModel> listClass) {
		this.listClass = listClass;
	}

	public ClassService getService() {
		return service;
	}

	public void setService(ClassService service) {
		this.service = service;
	}

	//Export data to ontology languages
	public void rdfExport() {
		
	}
	public void owlExport() {
		
	}
	public void textExport() {
		
	}
	 public void onCellEdit(CellEditEvent event) {
	        Object oldValue = event.getOldValue();
	        Object newValue = event.getNewValue();
	         
	        if(newValue != null && !newValue.equals(oldValue)) {
	            FacesMessage msg = new FacesMessage
	            		(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + 
	            oldValue + ", New:" + newValue);
	            FacesContext.getCurrentInstance().addMessage(null, msg);
	        }
	    }
}
