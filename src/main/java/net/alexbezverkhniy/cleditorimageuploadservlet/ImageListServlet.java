package net.alexbezverkhniy.cleditorimageuploadservlet;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * Servlet for output filenames
 * Servlet implementation class ImageListServlet
 */
@WebServlet("/imagelist")
public class ImageListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String UPLOAD_DIRECTORY = "/uploads";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImageListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Requests processing
	 */
	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		Writer writer = response.getWriter();
		File imageFolder = new File(request.getSession().getServletContext()
				.getRealPath(UPLOAD_DIRECTORY));
	
		// Mapper for converting array of filenames to JSON
		ObjectMapper mapper = new ObjectMapper();
	
		String[] fileNamesList = imageFolder.list();
		if (fileNamesList != null && fileNamesList.length > 0) {
			
			// Prepare filename for web
			for (int i = 0; i < fileNamesList.length; i++) {
				fileNamesList[i] = UPLOAD_DIRECTORY + "/" + fileNamesList[i];
			}
			mapper.writeValue(writer, fileNamesList);
			
		} else {
			writer.write("[]");
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
