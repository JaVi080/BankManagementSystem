import java.io.Serializable;
import java.util.Objects;

public class Person implements Serializable {
    private static final long serialVersionUID=1L;
    private String name;
    private String CNIC;
    private String type;
    private String Password;

    public Person(String name, String CNIC, String type, String password) {
        this.name = name;
        this.CNIC = CNIC;
        this.type = type;
        this.Password=password;
    }

    public String getName() {
        return name;
    }
    public String getCNIC() {
        return CNIC;
    }

    public String getType() {
        return type;
    }

    public String getPassword() {
        return Password;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", CNIC='" + CNIC + '\'' +
                ", type='" + type + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(type, person.type) && Objects.equals(Password, person.Password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, Password);
    }
}
