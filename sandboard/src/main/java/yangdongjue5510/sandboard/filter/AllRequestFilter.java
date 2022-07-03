package yangdongjue5510.sandboard.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AllRequestFilter implements Filter {

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        log.info("AllRequestFilter가 생성되었습니다.");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final String requestMethod = httpRequest.getMethod();
        final String requestURI = httpRequest.getRequestURI();

        final HttpServletResponse httpResponse = (HttpServletResponse) response;
        final int status = httpResponse.getStatus();

        try {
            log.info("[REQUEST] [{}] : {}", requestMethod, requestURI);
            chain.doFilter(request, response);
        } catch (Exception e) {
            log.warn("[EXCEPTION] {}", e.getMessage());
        } finally {
            log.info("[RESPONSE] [{}] : {}", status, requestURI);
        }
    }

    @Override
    public void destroy() {
        log.info("AllRequestFilter가 제거되었습니다.");
        Filter.super.destroy();
    }
}
