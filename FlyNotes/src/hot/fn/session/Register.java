package fn.session;

import javax.ejb.Local;

@Local
public interface Register {

	public void addMember();
	
	public void destroy();
}
