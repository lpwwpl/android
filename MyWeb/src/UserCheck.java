

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserCheck
 */
@WebServlet("/UserCheck")
public class UserCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String result;
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("name");
		String passwd = request.getParameter("passwd");
		
		String parameter = request.getParameter("parameter");
		
		//out.println(parameter);
		
		/*if(name == null || name.length() == 0 || passwd == null || passwd.length() == 0 ){
			result = "用户名或密码不能为空。";
			out.println(result);
			return;
		}
		
		if(name.equals("andy") && passwd.equals("123456")){
			result = "验证成功！";
			out.println(result);
			return;
		}else{
			result = "验证失败！";
			out.println(result);
			return;
		}*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(request, response);
	}

}
