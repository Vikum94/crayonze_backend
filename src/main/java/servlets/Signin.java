package servlets;

import com.google.gson.JsonObject;
import controller.DatabaseController;
import util.RequestReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
    name = "Signin",
    urlPatterns = "/sign-in"
)
public class Signin extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        JsonObject obj = RequestReader.readRequest(req);
        if (DatabaseController.authorizeUser(obj.get("username").getAsString(),
                obj.get("password").getAsString())) {
            res.setStatus(HttpServletResponse.SC_OK);
        } else res.setStatus(HttpServletResponse.SC_NOT_FOUND);

    }

}
