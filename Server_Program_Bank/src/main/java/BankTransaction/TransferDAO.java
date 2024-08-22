package BankTransaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Random;

import javax.servlet.ServletException;

public class TransferDAO {
	public int p1 = 0, p2 = 0;
	public boolean f = true;

	public boolean transfer(String sourceAccNo, String targetAccNo, float amount, String accHName) {

		try {
			Connection con = DBConnection.getCon();
			con.setAutoCommit(false);

			// Deduct amount from source account
			PreparedStatement ps1 = con
					.prepareStatement("UPDATE BankAccount SET accbalance = accbalance  and ACCHOLEDERNAME= ACCHOLEDERNAME - ? WHERE accno = ? and accHName= ?");
			ps1.setFloat(1, amount);
			ps1.setString(2, sourceAccNo);
			ps1.setString(3, accHName);
			p1 = ps1.executeUpdate();

			// Add amount to target account
			PreparedStatement ps2 = con
					.prepareStatement("UPDATE BankAccount SET accbalance = accbalance + ? WHERE accno = ? and  accHName= ?");
			ps2.setFloat(1, amount);
			ps2.setString(2, targetAccNo);
			ps2.setString(3, accHName);
			p2 = ps2.executeUpdate();

			if (p1 == 1 && p2 == 1) {
				StoreTransactionDetails(targetAccNo, generateTransactonId(), getCurrentTimeStamp(),amount);

				con.commit();
				f = true;
			} else {
				f = false;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return f;

	}
	private void StoreTransactionDetails(String string, int txId, Timestamp txTime, Float amount)
			throws ServletException, Exception {
		Connection con = DBConnection.getCon();
		PreparedStatement ps = con.prepareStatement("insert into transaction values(?,?,?,?)");

		ps.setString(1, string);
		ps.setInt(2, txId);
		ps.setTimestamp(3, txTime);
		ps.setFloat(4, amount);

		ps.execute();

	}

	private int generateTransactonId() {
		return new Random().nextInt();

	}

	private Timestamp getCurrentTimeStamp() {
		return new Timestamp(System.currentTimeMillis());
	}
}
