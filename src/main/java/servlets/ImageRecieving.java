package servlets;

import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import controller.DatabaseController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(
        name = "get image",
        urlPatterns = "/upload-image"
)
public class ImageRecieving extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse res) {
        String fileName = request.getParameter("fileName");
        String username = request.getParameter("username");

        InputStream inputStream = null; // input stream of the upload file

        // obtains the upload file part in this multipart request
        Part filePart = null;
        Boolean success = false;
        try {
            filePart = request.getPart("photo");
            if (filePart != null) {
                // prints out some information for debugging
                System.out.println(filePart.getName());
                System.out.println(filePart.getSize());
                System.out.println(filePart.getContentType());

                // obtains input stream of the upload file
                inputStream = filePart.getInputStream();


                File imageFile = new File("img/" + fileName);
                GridFS gfsPhoto = new GridFS(DatabaseController.getMongoDB(), "photo");
                GridFSInputFile gfsFile = gfsPhoto.createFile(imageFile);
                gfsFile.setFilename(username + "_" + fileName);
                gfsFile.save();
                success = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        if (success) {
            /*Process image*/
            // send image as res
        }

    }
}
