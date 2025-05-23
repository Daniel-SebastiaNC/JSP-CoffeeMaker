package br.dev.danielsebastian.coffeemaker.controller;

import br.dev.danielsebastian.coffeemaker.bo.EmailBo;
import br.dev.danielsebastian.coffeemaker.dao.UserDao;
import br.dev.danielsebastian.coffeemaker.exception.EmailException;
import br.dev.danielsebastian.coffeemaker.factory.DaoFactory;
import br.dev.danielsebastian.coffeemaker.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDao dao;
    private EmailBo bo;

    public LoginServlet() {
        dao = DaoFactory.getUserDao();
        bo = new EmailBo();
    }

    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = new User(email, password);

        if (dao.validUser(user)) {

            HttpSession session = req.getSession();
            session.setAttribute("user", email);
            String message =
                    "Um login foi realizado na plataforma em " + LocalDate.now();
            req.getRequestDispatcher("home.jsp").forward(req, resp);
            try {
                bo.sendEmail(email, "Login Realizado", message);
            } catch (EmailException e) {
                e.printStackTrace();
            }

        }else {
            req.setAttribute("erro", "Usuário e/ou senha inválidos");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }
}
