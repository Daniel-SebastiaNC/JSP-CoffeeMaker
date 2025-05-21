package br.dev.danielsebastian.coffeemaker.controller;

import br.dev.danielsebastian.coffeemaker.dao.CategoryDao;
import br.dev.danielsebastian.coffeemaker.dao.ProductDao;
import br.dev.danielsebastian.coffeemaker.exception.DBException;
import br.dev.danielsebastian.coffeemaker.factory.DaoFactory;
import br.dev.danielsebastian.coffeemaker.model.Category;
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

    private ProductDao productDao;
    private CategoryDao categoryDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        productDao = DaoFactory.getProductDao();
        categoryDao = DaoFactory.getCategoryDao();
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
            case "delete":
                this.deleteProduct(req, resp);
                break;
        }
    }

    private void registerProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        double value = Double.parseDouble(req.getParameter("value"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        LocalDate dateCreated = LocalDate.parse(req.getParameter("dateCreated"));
        Long idCategory = Long.parseLong(req.getParameter("category"));

        Product product = new Product(null, name, value, quantity, dateCreated);
        product.setCategory(new Category(idCategory, null));

        try {
            productDao.register(product);
            req.setAttribute("message", "product cadastrado com sucesso");
        } catch (DBException e) {
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
        }
        System.out.println("register");
        //req.getRequestDispatcher("register-product.jsp").forward(req, resp);
        this.openFormRegister(req, resp);
    }

    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        double value = Double.parseDouble(req.getParameter("value"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        LocalDate dateCreated = LocalDate.parse(req.getParameter("dateCreated"));
        Long idCategory = Long.parseLong(req.getParameter("category"));

        Product product = new Product(id, name, value, quantity, dateCreated);
        product.setCategory(new Category(idCategory, null));

        try {
            productDao.update(product);
            req.setAttribute("message", "product atualizado com sucesso");
        } catch (DBException e) {
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
        }
        System.out.println("update");
        this.list(req, resp);
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("idDelete"));

        try {
            productDao.delete(id);
            req.setAttribute("message", "product deletado com sucesso");
        } catch (DBException e) {
            req.setAttribute("error", "Erro ao deletar o produto");
            e.printStackTrace();
        }
        System.out.println("delete!");
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
                openFormEdit(req, resp);
                break;
            case "open-form-register":
                this.openFormRegister(req, resp);
                break;
        }
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("list");
        List<Product> all = productDao.findAll();
        req.setAttribute("products", all);
        req.getRequestDispatcher("list-product.jsp").forward(req, resp);
    }

    private void openFormEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Product byId = productDao.findById(id);
        this.loadCategories(req);
        req.setAttribute("product", byId);
        req.getRequestDispatcher("edit-product.jsp").forward(req, resp);
    }

    private void openFormRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.loadCategories(req);
        req.getRequestDispatcher("register-product.jsp").forward(req, resp);
    }

    private void loadCategories(HttpServletRequest req) {
        List<Category> categories = categoryDao.findAll();
        req.setAttribute("categories", categories);
    }
}
