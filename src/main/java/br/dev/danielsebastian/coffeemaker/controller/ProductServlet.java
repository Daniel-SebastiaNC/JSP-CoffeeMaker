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
import java.util.List;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {

    private ProductDao dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        dao = DaoFactory.getProductDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "register":
                this.registerProduct(req, resp);
                break;
            case "update":
                this.updateProduct(req, resp);
                break;
        }
        registerProduct(req, resp);
    }

    private void registerProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        }

        req.getRequestDispatcher("register-product.jsp").forward(req, resp);
    }

    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        double value = Double.parseDouble(req.getParameter("value"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        LocalDate dateCreated = LocalDate.parse(req.getParameter("dateCreated"));

        Product product = new Product(id, name, value, quantity, dateCreated);

        try {
            dao.update(product);
            req.setAttribute("message", "product atualizado com sucesso");
        } catch (DBException e) {
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
        }

        this.list(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        switch (action) {
            case "list":
                this.list(req, resp);
                break;
            case "open-edition-form":
                Long id = Long.parseLong(req.getParameter("id"));
                Product byId = dao.findById(id);
                req.setAttribute("product", byId);
                req.getRequestDispatcher("edit-product.jsp").forward(req, resp);
                break;
            default:
                break;
        }
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> all = dao.findAll();
        req.setAttribute("products", all);
        req.getRequestDispatcher("list-product.jsp").forward(req, resp);
    }
}
