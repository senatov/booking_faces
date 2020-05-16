package org.springframework.webflow.ctx;



import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;



/**
 * configure dynamic Context Path in Spring Boot
 *
 * @since 05.2020
 */
@Slf4j
public class WrapRequestFilter extends OncePerRequestFilter {

	private static final String[] PATHS = new String[]{"/spring", "/"}; //NON-NLS


	public static String getMatchingContextPathForRequest(HttpServletRequest request) {

		return Arrays
				.stream(PATHS)
				.filter(path -> request
						.getServletPath()
						.startsWith(path))
				.findFirst()
				.orElse(null);
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		log.debug(String.join("\nRequest-Uri:", request.getRequestURI()));
		ServletContextFacadeRequestWrapper wrapper = new ServletContextFacadeRequestWrapper(request);
		/**

		 String path = getMatchingContextPathForRequest(request);
		 if (path != null) {
		 wrapper.setContextPath(request.getContextPath() + path);
		 String newPath = request.getServletPath().substring(path.length());
		 if (newPath.length() == 0) {
		 newPath = "/";
		 }
		 wrapper.setServletPath(newPath);

		 }
		 */
		filterChain.doFilter(wrapper, response);
	}

}