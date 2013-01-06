package fn.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.jboss.seam.annotations.Name;

@Entity
@Name("member")
@Table(name = "members", catalog = "flyingnotes")
public class Member implements java.io.Serializable {

	private int id;

	private String name;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "name", length = 45)
	@Length(max = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Member() {
	}

	public Member(int id) {
		this.id = id;
	}

	public Member(int id, String name) {
		this.id = id;
		this.name = name;
	}

}
