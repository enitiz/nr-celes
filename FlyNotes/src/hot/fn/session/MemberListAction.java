package fn.session;

import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;

import fn.entity.Member;

@Stateful
@Name("memberList")
@Scope(ScopeType.SESSION)
public class MemberListAction implements MemberList {

	@PersistenceContext
	private EntityManager em;

	private String searchString;
	private int page, pageSize = 10;

	@DataModel
	private List<Member> members;

	@Override
	public void find() {
		page = 0;
		queryMembers();
	}

	@Override
	public void nextPage() {
		page++;
		queryMembers();
	}

	private void queryMembers() {
		String searchQuery = "select m from Member m where lower(m.name) like #{pattern} or lower(m.email) like #{pattern} or lower(m.position) like #{pattern} or lower(m.department) like #{pattern}";

		members = em.createQuery(searchQuery).setMaxResults(pageSize)
				.setFirstResult(page * pageSize).getResultList();
	}

	@Override
	public boolean isNextPageAvaliable() {
		return members != null && members.size() == pageSize;
	}

	@Override
	public int getPageSize() {
		return pageSize;
	}

	@Override
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	@Factory(value = "pattern", scope = ScopeType.EVENT)
	public String getSearchPattern() {
		return searchString == null ? "%" : '%' + searchString.toLowerCase()
				.replace('*', '%') + '%';
	}

	@Override
	public String getSearchString() {
		return searchString;
	}

	@Override
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	@Override
	@Remove
	public void destroy() {
	}

	@Override
	public int memberRank(String position) {
		if (position.equals("Administrator"))
			return 0;
		else if (position.equals("Finance Officer"))
			return 1;
		else if (position.equals("Director"))
			return 2;
		else if (position.equals("Registrar"))
			return 3;
		else if (position.equals("Head of Department"))
			return 4;
		else if (position.equals("Professor"))
			return 5;
		else if (position.equals("Lecturer"))
			return 6;
		else if (position.equals("Staff or Assistant"))
			return 7;
		else
			return 99;
	}
}
