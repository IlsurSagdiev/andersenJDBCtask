package com.andersen.web;

import com.andersen.database.dao.UserDao;
import com.andersen.database.dao.impl.UserDaoImpl;
import com.andersen.database.entity.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/create")
public class CreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/webapp/view/create-user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UserDao userDao = new UserDaoImpl();
        User user = new User(email, password);

        if (userDao.isExists(user)) {
            req.setAttribute("error", "User already exists");
            req.getRequestDispatcher("/webapp/view/create-user.jsp").forward(req, resp);
        } else {
            userDao.save(user);
            req.setAttribute("success", "User created");
            resp.sendRedirect("/");
        }
    }
}