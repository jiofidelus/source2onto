package cm.uy1.source2onto.service;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import cm.uy1.source2onto.model.ClassModel;

@ManagedBean
@ApplicationScoped
public class ClassService {
	
	List<ClassModel> classes;
	
	public List<ClassModel> createClasses(){
		classes = new ArrayList<>();

		classes.add(new ClassModel("org.geoserver.csw.records", "syn1, syn2", 
				"subclass1, subclass2", "description", true));		
		classes.add(new ClassModel("CRSRecordProjectyPathAdapter", 
				"syn1, syn2", "subclass1, subclass2", "Description", true));		
		classes.add(new ClassModel("DuplicatingFilterVisitor", 
				"syn1, syn2", "subclass1, subclass2", "Description", true));		
		classes.add(new ClassModel("CoordinateReferenceSystem", 
				"syn1, syn2", "subclass1, subclass2", "Description", true));		
		classes.add(new ClassModel("GridCoverage2D", 
				"syn1, syn2", "subclass1, subclass2", "Description", true));		
//		classes.add(new ClassModel("", 
//				synonyms, subClasses, "Description", true));		
//		classes.add(new ClassModel("", 
//				synonyms, subClasses, "Description", true));		
//		classes.add(new ClassModel("", 
//				synonyms, subClasses, "Description", true));		
//		classes.add(new ClassModel("", 
//				synonyms, subClasses, "Description", true));		
//		classes.add(new ClassModel("", 
//				synonyms, subClasses, "Description", true));		
		return classes;
	}
	
//	public List<ClassModel> createClasses(){
//		
//		classes = new ArrayList<>();
//		List<String> listSynonyms = new ArrayList<>();
//		listSynonyms.add("org.geoserver.csw.records");
//		listSynonyms.add("CRSRecordProjectyPathAdapter");
//		listSynonyms.add("DuplicatingFilterVisitor");
//		listSynonyms.add("CRSRecordProjectyPathAdapter");
//		listSynonyms.add("org.geoserver.catalog.impl");
//		listSynonyms.add("NamespaceInfoImpl");
//		listSynonyms.add("NamespaceInfo");
//		listSynonyms.add("org.geoserver.gwc.web.layer");
//		listSynonyms.add("NewCachedLayerPage");
//		listSynonyms.add("GeoServerSecuredPage");
//		listSynonyms.add("GridCoverage2D");
//		listSynonyms.add("CoordinateReferenceSystem");
//		listSynonyms.add("CoordinateReferenceSystem");
//		listSynonyms.add("GridCoverage2D");
//		List<String> listSubClasses1 = new ArrayList<>();
//		listSubClasses1.add("DuplicatingFilterVisitor");
//		List<String> listSubClasses2 = new ArrayList<>();
//		listSubClasses2.add("GeoServerSecuredPage");
//		List<String> listSubClasses3 = new ArrayList<>();
//
//		ClassModel c1 = new ClassModel("org.geoserver.csw.records", listSynonyms,   null,
//				"", true);
//		ClassModel c2 = new ClassModel("CRSRecordProjectyPathAdapter", listSynonyms,
//				listSubClasses1, "", true);
//		ClassModel c3 = new ClassModel("org.geoserver.catalog.impl", listSynonyms, 
//				null, "", true);
//		ClassModel c4 = new ClassModel("NamespaceInfoImpl", listSynonyms, 
//				null, "", true);
//		ClassModel c5 = new ClassModel("NamespaceInfo", listSynonyms, 
//				null, "", true);
//		ClassModel c6 = new ClassModel("org.geoserver.gwc.web.layer", listSynonyms, 
//				null, "", true);
//		ClassModel c7 = new ClassModel("NewCachedLayerPage", listSynonyms, 
//				listSubClasses2, "", true);
//		ClassModel c8 = new ClassModel("GridCoverage2D", listSynonyms, 
//				null, "", true);
//		ClassModel c9 = new ClassModel("CoordinateReferenceSystem", listSynonyms, 
//				null, "", true);
//		ClassModel c10 = new ClassModel("CoordinateReferenceSystem", listSynonyms, 
//				null, "", true);
//		ClassModel c11 = new ClassModel("GridCoverage2D", listSynonyms, 
//				null, "", true);
//
//		classes.add(c1);
//		classes.add(c2);
//		classes.add(c3);
//		classes.add(c4);
//		classes.add(c5);
//		classes.add(c6);
//		classes.add(c7);
//		classes.add(c8);
//		classes.add(c9);
//		classes.add(c10);
//		classes.add(c11);
//		return classes;
//	}

	public List<ClassModel> getClasses() {
		return classes;
	}

	public void setClasses(List<ClassModel> classes) {
		this.classes = classes;
	}

}
