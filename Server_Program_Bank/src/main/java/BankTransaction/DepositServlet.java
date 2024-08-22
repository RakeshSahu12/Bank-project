package BankTransaction;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/deposit")
public class DepositServlet extends HttpServlet

{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession hs = req.getSession();
		if (hs == null) {
			req.setAttribute("msg", "Session expired");
			req.getRequestDispatcher("Msg.jsp").forward(req, res);

		} else {
			BankBean bb = (BankBean) hs.getAttribute("bbean");
			Float amount = Float.parseFloat(req.getParameter("amount"));
			bb.setAccBalance(amount);
			int k = new DepositDAO().deposit(bb);
			if (k > 0) {
				req.setAttribute("msg", "Deposit successfully..."+amount +"<br>");
				RequestDispatcher rd = req.getRequestDispatcher("transfersuccess.jsp");
				rd.forward(req, res);
			}

		}

	}
}
