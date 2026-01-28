package com.app.filter;

import java.io.IOException;

import com.complaint.system.model.User;
import com.complaint.system.model.UserRole;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/client/*", "/admin/*", "/delete_complaint", "/raise_complaint"})
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
                // Cast to HTTP-specific types to access Session and Redirects
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);//?why we passed false?

        // Retrieve the User object we stored during Login
        User user = (session != null) ? (User) session.getAttribute("user") : null;//why not just User user = (User) session.getAttribute("user")

        // STEP 1: If no user is found, send them to login
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/auth/login.jsp?error=unauthorized");
            return; // STOP the request here
        }

        String path = req.getRequestURI();

        // STEP 2: Role-Based Authorization
        // If a CLIENT tries to access /admin/ URLs
        if (path.contains("/admin/") && user.getRole() != UserRole.ADMIN) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied: Admins Only");
            return;
        }

        // If an ADMIN tries to access /client/ URLs
        if (path.contains("/client/") && user.getRole() != UserRole.CLIENT) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied: Clients Only");
            return;
        }

        // STEP 3: Successful Validation - Hand control to the next Filter or Servlet
        chain.doFilter(request, response);

    }

    

}
