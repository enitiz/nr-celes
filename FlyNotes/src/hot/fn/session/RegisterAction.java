package fn.session;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import fn.entity.Member;

@Stateful
@Name("register")
public class RegisterAction implements Register {
	
	@In
	private Member member;
	
	@PersistenceContext
	private EntityManager em;

	public void addMember() {
		em.persist(member);
	}
	
	@Remove
	public void destroy() {
		
	}
}
