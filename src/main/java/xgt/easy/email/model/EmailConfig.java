/**
 * 
 */
package xgt.easy.email.model;

/**
 * @author Gavin
 *
 */
public class EmailConfig {
	public static final String DEFAULT_KEY = "default";
	private boolean isDefault;
	private String host;
	private int port;
	private String userName;
	private String password;
	private boolean auth;
	private boolean startTls;
	private boolean log;
	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}
	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the auth
	 */
	public boolean isAuth() {
		return auth;
	}
	/**
	 * @param auth the auth to set
	 */
	public void setAuth(boolean auth) {
		this.auth = auth;
	}
	/**
	 * @return the startTls
	 */
	public boolean isStartTls() {
		return startTls;
	}
	/**
	 * @param startTls the startTls to set
	 */
	public void setStartTls(boolean startTls) {
		this.startTls = startTls;
	}
	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}
	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}
	/**
	 * @return the log
	 */
	public boolean isLog() {
		return log;
	}
	/**
	 * @param log the log to set
	 */
	public void setLog(boolean log) {
		this.log = log;
	}
	/**
	 * @return the isDefault
	 */
	public boolean isDefault() {
		return isDefault;
	}
	/**
	 * @param isDefault the isDefault to set
	 */
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
	
}
