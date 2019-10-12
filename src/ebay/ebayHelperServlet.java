package ebay;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ebayHelperServlet extends HttpServlet {

  private static final Long serialVersionUID = 1L;

  public ebayHelperServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    System.out.println("Using Get");
    PrintWriter out = response.getWriter();

    ApplicationController applicationController = new ApplicationController(request.getParameter("search"),request.getParameter("operation"));
    out.println(applicationController.run());
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    System.out.println("Using Post");
    PrintWriter out = response.getWriter();

    ApplicationController applicationController = new ApplicationController(request.getParameter("search"),request.getParameter("operation"));
    out.println(applicationController.run());
  }
}
