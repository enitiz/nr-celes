package fn.session;

import javax.ejb.Local;

@Local
public interface Register {

	public String addMember();
	
	public void destroy();
}
