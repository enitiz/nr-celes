package fn.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.hibernate.validator.Pattern;
import org.jboss.seam.annotations.Name;

@Entity
@Name("member")
@Table(name = "members", catalog = "flyingnotes")
public class Member implements java.io.Serializable {

	private int id;

	private String name;
	private String password;
	private String email;
	private String position;
	private String department;

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
	@Length(max = 100, min=4, message="At least 4 characters required")
	@Pattern(regex = "^[a-zA-Z.-]+ [a-zA-Z.-]+", message = "Enter both first and last name")
	@NotNull(message="Name is required")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "password", length = 45)
	@Length(max = 100, min = 6, message = "At least 6 characters required")
	@Pattern(regex="^(?=.*[0-9])(?=.*[@#$%^&+=])(?=\\S+$)$", message="At least 1 digit and 1 special character required" )
	@NotNull(message="Password is required")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "email", length = 45)
	@Length(max = 254)
	@Pattern(regex="^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,4}$", message="Invalid email address")
	@NotNull(message="Email address is required")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "position", length = 45)
	@Length(max = 100)
	@NotNull(message="Position is required")
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "department", length = 45)
	@Length(max = 100)
	@NotNull(message="Department is required")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Member() {
	}

	public Member(int id) {
		this.id = id;
	}

	public Member(int id, String name, String email, String password,
			String position, String department) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.position = position;
		this.department = department;
	}

}
