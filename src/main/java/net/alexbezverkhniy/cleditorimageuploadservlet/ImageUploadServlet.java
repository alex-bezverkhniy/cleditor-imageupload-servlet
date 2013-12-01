package net.alexbezverkhniy.cleditorimageuploadservlet;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet for file uploading
 * Servlet implementation class ImageUploadServlet
 */
@WebServlet("/imageuploader")
public class ImageUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String FILENAME_PARAM = "filename";
	private final String UPLOAD_DIRECTORY = "/uploads";

	/**
	 * Default constructor.
	 */
	public ImageUploadServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Requests processing
	 */
	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Writer writer = response.getWriter();
		//process only if its multipart content
        if(ServletFileUpload.isMultipartContent(request)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
              
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        String name = new File(item.getName()).getName();
                        item.write( new File(request.getSession().getServletContext()
                				.getRealPath(UPLOAD_DIRECTORY) + File.separator + name));
                        //File uploaded successfully
                        writer.write("<div id=\"image\">" + UPLOAD_DIRECTORY + name + "</div>");
                    }
                }
           
            } catch (Exception ex) {
               ex.printStackTrace();
            }          
         
        }else{
        	writer.write("File not found!");
        }     
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
