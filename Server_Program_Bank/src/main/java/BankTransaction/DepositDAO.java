package BankTransaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Random;

import javax.servlet.ServletException;

public class DepositDAO {
	public int k = 0;

	public int deposit(BankBean bb) {
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("update BankAccount set accbalance=accbalance+? where accno=?");

			ps.setFloat(1, bb.getAccBalance());
			ps.setString(2, bb.getAccNo());
			k = ps.executeUpdate();

			if (k > 0) {
				StoreTransactionDetails(bb.getAccNo(), generateTransactonId(), getCurrentTimeStamp(),
						bb.getAccBalance());

			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return k;

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
