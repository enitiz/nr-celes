package fn.session;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

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
		try {
			member.setHash(hash(member.getPassword()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		em.persist(member);
		return "/members/members.xhtml";
	}

	public byte[] hash(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		byte[] salt = new byte[20];
		new Random().nextBytes(salt);
		MessageDigest digest;
		digest = MessageDigest.getInstance("SHA-256");
		digest.reset();
		digest.update(salt);
		return digest.digest(password.getBytes("UTF-8"));
	}

	@Remove
	public void destroy() {

	}
}
