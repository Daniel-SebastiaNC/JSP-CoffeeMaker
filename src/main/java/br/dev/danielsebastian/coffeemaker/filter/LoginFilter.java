package br.dev.danielsebastian.coffeemaker.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        String url = req.getRequestURI();

        if (session.getAttribute("user") == null && !url.endsWith("login") && !url.contains("resources") && !url.contains("home")) {
            request.setAttribute("erro", "Entre com o usuário e senha!");
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }else {
            chain.doFilter(request, response);
        }
    }
}
