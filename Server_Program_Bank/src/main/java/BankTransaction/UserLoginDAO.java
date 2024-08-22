package BankTransaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserLoginDAO

{
	public BankBean ba = null;

	public BankBean Login(String AccNo, String AccHName) {
		try {
			Connection con = DBConnection.getCon();
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement("select * from BankAccount where AccNo=? and AccHolederName=?");

			ps.setString(1, AccNo);
			ps.setString(2, AccHName);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ba = new BankBean();
				ba.setAccNo(rs.getString(1));
				ba.setAccName(rs.getString(2));
				ba.setAccBalance(rs.getFloat(3));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ba;

	}

}
