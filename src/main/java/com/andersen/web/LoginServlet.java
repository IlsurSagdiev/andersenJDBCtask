package com.andersen.web;


import com.andersen.database.dao.impl.UserDaoImpl;
import com.andersen.database.entity.RoleEnum;
import com.andersen.database.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final String PATH = "/webapp/view";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.getUserByEmail(email);
        checkUserRole(user, password, request, response);
    }

    private void checkUserRole(User user, String password, HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {
        if (user != null && user.getPassword().equals(password)) {
            RoleEnum role = user.getRole();
            request.getSession().setAttribute("admin", false);
            if (role.equals(RoleEnum.USER) || role.equals(RoleEnum.ADMIN)) {
                if (role.equals(RoleEnum.ADMIN)) {
                    request.getSession().setAttribute("admin", true);
                }
                request.getRequestDispatcher(PATH + "/user-page.jsp").forward(request, resp);
            }
            if (role.equals(RoleEnum.GUEST)) {
                request.getRequestDispatcher(PATH + "/guest-page.html").forward(request, resp);
            }
        }
        request.setAttribute("error", "Email / password incorrect! Please try again later");
        request.getRequestDispatcher(PATH + "/login.jsp").forward(request, resp);
    }
}
