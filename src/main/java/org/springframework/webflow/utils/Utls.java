package org.springframework.webflow.utils;



import com.sun.faces.config.ConfigureListener;

import javax.servlet.ServletContext;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;



final public class Utls {


	public static void setParams(ServletContext sc) {

		sc.addListener(ConfigureListener.class);
		sc.setInitParameter("com.sun.faces.expressionFactory", "org.apache.el.ExpressionFactoryImpl");
		sc.setInitParameter("com.sun.faces.forceLoadConfiguration", TRUE.toString());
		sc.setInitParameter("facelets.DEVELOPMENT", TRUE.toString());
		sc.setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "1");
		sc.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", FALSE.toString());
		sc.setInitParameter("javax.faces.PARTIAL_STATE_SAVING_METHOD", TRUE.toString());
		sc.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
		sc.setInitParameter("javax.faces.STATE_SAVING_METHOD", "server");
		sc.setInitParameter("primefaces.CLIENT_SIDE_VALIDATION", TRUE.toString());
		sc.setInitParameter("primefaces.FONT_AWESOME", TRUE.toString());
		sc.setInitParameter("primefaces.THEME", "redmond");
		sc.setInitParameter("javax.faces.WEBAPP_RESOURCES_DIRECTORY", "/");
		sc.setInitParameter("com.sun.faces.compressJavaScript", FALSE.toString());
		sc.setInitParameter("Javax.faces.CONFIG_FILES", "/WEB-INF/faces-config.xml");

	}
}
