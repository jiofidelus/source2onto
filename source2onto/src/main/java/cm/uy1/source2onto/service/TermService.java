package cm.uy1.source2onto.service;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import cm.uy1.helper.Helper;
import cm.uy1.source2onto.controller.KnowledgeExtraction;
import cm.uy1.source2onto.model.Term;

@ManagedBean
@ApplicationScoped
public class TermService {

	private static String[] names;
	private static String[] typeOfTerm;
	
	public List<Term> createTerms(){
		
		List<Term> listTerms = new ArrayList<Term>();
//		Term term;
//		//Get all the terms
//		String listTermsExtracted = 
//				Helper.getAllTerms(KnowledgeExtraction.getProgramminLang());
//		
////		for (String termExtracted : listTermsExtracted) {
////			term = new Term(termExtracted, "class"); 
////		}
//		
		listTerms.add(new Term("package", "metadata"));
		listTerms.add(new Term("org.geoserver.csw.records", "class"));
		listTerms.add(new Term("CRSRecordProjectyPathAdapter", "class"));
		listTerms.add(new Term("extends", "metadata"));
		listTerms.add(new Term("DuplicatingFilterVisitor", "class"));
		listTerms.add(new Term("int", "metadata"));
		listTerms.add(new Term("age", "attribute"));
		listTerms.add(new Term("String", "metadata"));
		listTerms.add(new Term("nom", "attribute"));
		listTerms.add(new Term("String", "metadata"));
		listTerms.add(new Term("job", "relation"));
		listTerms.add(new Term("package", "metadata"));
		listTerms.add(new Term("exam", "class"));
		listTerms.add(new Term("class", "metadata"));
		listTerms.add(new Term("Serology", "class"));
		listTerms.add(new Term("String", "metadata"));
		listTerms.add(new Term("result", "class"));


		return listTerms;
	}

	public static String[] getNames() {
		return names;
	}

	public static void setNames(String[] names) {
		TermService.names = names;
	}

	public static String[] getTypeOfTerm() {
		return typeOfTerm;
	}

	public static void setTypeOfTerm(String[] typeOfTerm) {
		TermService.typeOfTerm = typeOfTerm;
	}

	
}
