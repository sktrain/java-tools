package appl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import domain.Account;
import jn.util.DBStatus;



public class Application {
	public static void main(String[] args) {
		
		 H2TcpServer h2TcpServer = new H2TcpServer();
	     h2TcpServer.start();
		
		try (final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ApplConfig.class)) {
			
			final DataSource dataSource = ctx.getBean(DataSource.class);
			try (final Connection con = dataSource.getConnection()) {
				DBStatus.showState(con, "Before");
				demo(con);
				DBStatus.showState(con, "After");
			}
			catch (final Exception e) {
				System.out.println(e);
			}
		}
		
		if (h2TcpServer != null) {
            h2TcpServer.stop();
        }
		
	}

	private static void demo(final Connection con) throws SQLException {
		insertAccount(con, new Account(4711));
		insertAccount(con, new Account(4712));
		final Account account1 = findAccount(con, 4711);
		account1.setBalance(account1.getBalance() + 5000);
		updateAccount(con, account1);
		final Account account2 = findAccount(con, 4712);
		account2.setBalance(account2.getBalance() + 6000);
		updateAccount(con, account2);
		for (final Account account : findAllAccounts(con))
			System.out.println(account);
		deleteAccount(con, account1);
	}

	private static void insertAccount(Connection con, Account account) throws SQLException {
		final String sql = "insert into account (number, balance) values (?, ?)";
		try (final PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, account.getNumber());
			ps.setInt(2, account.getBalance());
			if (ps.executeUpdate() != 1)
				throw new RuntimeException("account not inserted: " + account.getNumber());
		}
	}

	private static void updateAccount(Connection con, Account account) throws SQLException {
		final String sql = "update account set balance = ? where number = ?";
		try (final PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, account.getBalance());
			ps.setInt(2, account.getNumber());
			if (ps.executeUpdate() != 1)
				throw new RuntimeException("account not updated: " + account.getNumber());
		}
	}

	private static void deleteAccount(Connection con, Account account) throws SQLException {
		final String sql = "delete from account where number = ?";
		try (final PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, account.getNumber());
			if (ps.executeUpdate() != 1)
				throw new RuntimeException("account not deleted: " + account.getNumber());
		}
	}

	private static Account findAccount(Connection con, int number) throws SQLException {
		final String sql = "select number, balance from account where number = ?";
		try (final PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, number);
			try (final ResultSet rs = ps.executeQuery()) {
				if (!rs.next())
					return null;
				final Account a = new Account();
				a.setNumber(rs.getInt(1));
				a.setBalance(rs.getInt(2));
				return a;
			}
		}
	}

	private static List<Account> findAllAccounts(Connection con) throws SQLException {
		final String sql = "select number, balance from account";
		try (final PreparedStatement ps = con.prepareStatement(sql)) {
			try (final ResultSet rs = ps.executeQuery()) {
				final List<Account> list = new ArrayList<>();
				while (rs.next()) {
					final Account a = new Account();
					a.setNumber(rs.getInt(1));
					a.setBalance(rs.getInt(2));
					list.add(a);
				}
				return list;
			}
		}
	}
	
}
