/**
 * 
 */
package xgt.easy.sys.model;

/**
 * @author Gavin
 *
 */
public enum UserStatus {
	DELETED(-1),NOT_VALID(0),NORMAL(1),FORBID(2);
	private UserStatus(int status){
		this.status = status;
	};
	private int status;
	public int getStatus(){
		return status;
	}
}
