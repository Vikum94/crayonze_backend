package servlets;

import com.google.gson.JsonObject;
import controller.DatabaseController;
import util.RequestReader;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "authorize admin",
        urlPatterns = "/login"
)
public class Login extends HttpServlet{

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        JsonObject obj = RequestReader.readRequest(req);
        DatabaseController.addNewUser(obj.get("username").getAsString(),
                obj.get("password").getAsString(),
                obj.get("email").getAsString());
    }


}
