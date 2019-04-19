package web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "encodingFilter", urlPatterns = "/*", initParams = {@WebInitParam(name = "encoding", value = "UTF-8")})
public class EncodingFilter implements Filter {

    private String encoding = "utf-8";

	public void doFilter(ServletRequest request,

	ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		request.setCharacterEncoding(encoding);

		response.setContentType("text/html; charset=" + encoding);
        response.setCharacterEncoding(encoding);
		((HttpServletResponse)response).setHeader("Content-Language", "UTF-8");
		filterChain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		String encodingParam = filterConfig.getInitParameter("encoding");
		if (encodingParam != null && !"".equals(encodingParam)) {
			encoding = encodingParam;
		}
	}

	public void destroy() {
	}
}
