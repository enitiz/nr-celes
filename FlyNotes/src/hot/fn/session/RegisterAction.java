package fn.session;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import fn.entity.Member;

@Stateless
@Name("register")
public class RegisterAction implements Register {

	@In
	private Member member;

	@PersistenceContext
	private EntityManager em;

	public String addMember() {
		em.persist(member);
		return "/members/members.xhtml";
	}

	@Remove
	public void destroy() {

	}
}
