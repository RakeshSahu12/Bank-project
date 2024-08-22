package BankTransaction;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@SuppressWarnings("serial")
@WebServlet("/transfer")
public class TransferServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session == null) {
            req.setAttribute("msg", "Session expired");
            req.getRequestDispatcher("Msg.jsp").forward(req, res);
        } else {
            BankBean sourceAccount = (BankBean) session.getAttribute("bbean");
            String sourceAccNo = sourceAccount.getAccNo();
            String targetAccNo = req.getParameter("accno");
            String AccHName=req.getParameter("hname"); 
            float amount = Float.parseFloat(req.getParameter("amount"));
            
            if (amount > sourceAccount.getAccBalance()) {
                req.setAttribute("msg", "Insufficient funds for transfer");
                req.getRequestDispatcher("Msg.jsp").forward(req, res);
                return; // Stop further processing
                
            }
          //  TransferDAO transferDAO = new TransferDAO();
            
            if ( new TransferDAO().transfer(sourceAccNo, targetAccNo, amount,AccHName)) {
                req.setAttribute("msg", "Transfer Successful to..."+targetAccNo+"&nbsp&nbsp" +AccHName+"<br>");
                RequestDispatcher rd = req.getRequestDispatcher("transfersuccess.jsp");
                rd.forward(req, res);
            } else {
                req.setAttribute("msg", "Transfer failed");
                RequestDispatcher rd = req.getRequestDispatcher("Msg.jsp");
                rd.forward(req, res);
            }
        }
    }
}
