package fn.entity;

import java.io.Serializable;

import org.hibernate.annotations.Entity;

@Entity
public class Member implements Serializable {

	private static final long serialVersionUID = 1097864154657408038L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
