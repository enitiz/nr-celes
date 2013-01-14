package fn.session;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;

import fn.entity.Member;

@Stateless
@Name("authenticator")
public class AuthenticatorAction implements Authenticator {
	@Logger
	Log log;

	@In
	Identity identity;
	@In
	Credentials credentials;

	@PersistenceContext
	EntityManager em;

	@Out(required = false, scope = ScopeType.SESSION)
	private Member member;

	public byte[] hash(String email, String password) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		MessageDigest digest;
		email = email.toLowerCase();
		digest = MessageDigest.getInstance("SHA-256");
		digest.reset();
		digest.update(email.getBytes("UTF-8"));
		return digest.digest(password.getBytes("UTF-8"));
	}

	@Override
	public boolean authenticate() {
		log.info("authenticating {0}", credentials.getUsername());

		byte[] testHash = null;

		try {
			testHash = hash(credentials.getUsername(), credentials.getPassword());
		} catch (NoSuchAlgorithmException e) {
			log.info("No Such Algorithm Exception");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			log.info("Unsupported Encoding Exception");
			e.printStackTrace();
		}

		String query = "select m from Member m where m.email = #{credentials.username} and m.hash = :testHash";

		List results = em.createQuery(query).setParameter("testHash", testHash)
				.getResultList();

		if (results.size() == 0)
			return false;
		else {
			member = (Member) results.get(0);
			identity.addRole(member.getPosition());
			return true;
		}
	}
}
