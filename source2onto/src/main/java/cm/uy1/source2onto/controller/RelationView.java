package cm.uy1.source2onto.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;

import cm.uy1.source2onto.model.RelationModel;
import cm.uy1.source2onto.service.RelationService;

@ManagedBean
public class RelationView {

	List<RelationModel> listRelations;
	List<RelationModel> listFilteredRelations;
	
	@ManagedProperty("#{}")
	RelationService service;
	
	@PostConstruct
	public void getAllRelations(){
		listRelations = service.getAllRelation();
	}
	
	
	public List<RelationModel> getListRelations() {
		return listRelations;
	}


	public void setListRelations(List<RelationModel> listRelations) {
		this.listRelations = listRelations;
	}


	public List<RelationModel> getListFilteredRelations() {
		return listFilteredRelations;
	}


	public void setListFilteredRelations(List<RelationModel> listFilteredRelations) {
		this.listFilteredRelations = listFilteredRelations;
	}


	public RelationService getService() {
		return service;
	}


	public void setService(RelationService service) {
		this.service = service;
	}


	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		
		if(newValue!=null && !newValue.equals(oldValue)) {
			//Updating the new value
		}
	}

	//Export data to ontology languages
	public void rdfExport() {
		
	}
	public void owlExport() {
		
	}
	public void textExport() {
		
	}
		
}
