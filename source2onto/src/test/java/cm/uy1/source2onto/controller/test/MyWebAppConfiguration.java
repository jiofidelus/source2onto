package cm.uy1.source2onto.controller.test;
//package cm.uy1.source2onto;
//
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//
//import org.springframework.web.WebApplicationInitializer;
//import org.springframework.web.context.ContextLoaderListener;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//
//import cm.uy1.source2onto.controller.test.SpringConfiguration;
//
//public class MyWebAppConfiguration implements 
//	WebApplicationInitializer{
//
//	@Override
//	public void onStartup(ServletContext container) {
//
//		// Create the 'root' Spring application context
//	    AnnotationConfigWebApplicationContext rootContext =
//	      new AnnotationConfigWebApplicationContext();
//
//	    rootContext.register(SpringConfiguration.class);
//	    
//	    //Manage the lifecyle of the root application context
//	    container.addListener(new ContextLoaderListener(rootContext));
//	}
//}
