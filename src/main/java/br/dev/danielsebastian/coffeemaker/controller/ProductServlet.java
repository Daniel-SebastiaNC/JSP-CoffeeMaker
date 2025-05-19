package br.dev.danielsebastian.coffeemaker.controller;

import br.dev.danielsebastian.coffeemaker.dao.ProductDao;
import br.dev.danielsebastian.coffeemaker.exception.DBException;
import br.dev.danielsebastian.coffeemaker.factory.DaoFactory;
import br.dev.danielsebastian.coffeemaker.model.Product;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {

    private ProductDao dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        dao = DaoFactory.getProductDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        double value = Double.parseDouble(req.getParameter("value"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        LocalDate dateCreated = LocalDate.parse(req.getParameter("dateCreated"));

        Product product = new Product(null, name, value, quantity, dateCreated);

        try {
            dao.register(product);
            req.setAttribute("message", "product cadastrado com sucesso");
        } catch (DBException e) {
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/product.jsp").forward(req, resp);
        }

        req.getRequestDispatcher("register-product.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    /*@Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }*/
}
