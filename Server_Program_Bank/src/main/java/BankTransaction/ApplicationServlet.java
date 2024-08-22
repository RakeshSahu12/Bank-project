package BankTransaction;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/selection")
public class ApplicationServlet extends HttpServlet
{
	@Override 
	protected void doPost(HttpServletRequest req,HttpServletResponse rep)throws ServletException ,IOException
	{
		String s= req.getParameter("s");
		if(s.equals("Deposit"))
		{
			RequestDispatcher rd= req.getRequestDispatcher("Deposit.html");
			rd.forward(req, rep);
		}
		else if(s.equals("Transfer"))
		{
			RequestDispatcher rd= req.getRequestDispatcher("Transfer.html");
			rd.forward(req, rep);
		}
		
	}
	
}
