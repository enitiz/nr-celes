package fn.session;

import javax.ejb.Remove;

import org.hibernate.search.annotations.Factory;

public interface MemberList {

	public abstract void find();

	public abstract boolean isNextPageAvaliable();

	public abstract int getPageSize();

	public abstract void setPageSize(int pageSize);

	@Factory
	public abstract String getSearchPattern();

	public abstract String getSearchString();

	public abstract void setSearchString(String searchString);

	@Remove
	public abstract void destroy();

	void nextPage();

	int memberRank(String position);

}