package UserInterface;

import java.util.HashMap;
import java.util.Map;

public class AccountCred {
	private Map<AccountType, String> usernames;
	private Map<AccountType, String> passwords;

	public AccountCred() {
		this.usernames = new HashMap<>();
		this.passwords = new HashMap<>();
	}

	public void setUsername(AccountType accountType, String username) {
		this.usernames.put(accountType, username);
	}

	public void setPassword(AccountType accountType, String password) {
		this.passwords.put(accountType, password);
	}

	public String getUsername(AccountType accountType) {
		return this.usernames.get(accountType);
	}

	public String getPassword(AccountType accountType) {
		return this.passwords.get(accountType);
	}
}