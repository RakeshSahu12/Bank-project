package BankTransaction;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@SuppressWarnings("serial")
@WebServlet("/log")
public class UserLoginServlet extends HttpServlet
{
	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		String AccNo=req.getParameter("accno");
		String AccHName=req.getParameter("hname");
		
		BankBean ba=new UserLoginDAO().Login(AccNo, AccHName);
		if(ba==null)
		{
			req.setAttribute("msg", "Invallid user login...<br>");
			req.getRequestDispatcher("Msg.jsp").forward(req, res);
			
		}
		else
		{
			HttpSession hs= req.getSession();
			hs.setAttribute("bbean", ba);
			req.getRequestDispatcher("UserLoginSuccess.jsp").forward(req, res);
			
		}
	}

}
