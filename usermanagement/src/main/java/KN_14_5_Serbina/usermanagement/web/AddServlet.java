package KN_14_5_Serbina.usermanagement.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import KN_14_5_Serbina.usermanagement.User;
import KN_14_5_Serbina.usermanagement.db.DaoFactory;
import KN_14_5_Serbina.usermanagement.db.DatabaseException;

public class AddServlet extends EditServlet {

	protected void processUser(User user) throws DatabaseException {
        DaoFactory.getInstance().getUserDao().create(user);
    }
	
	protected void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/add.jsp").forward(req, resp);
    }
}