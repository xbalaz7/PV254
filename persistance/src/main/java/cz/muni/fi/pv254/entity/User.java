package cz.muni.fi.pv254.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Table(name= "users")
@Entity
public class User implements Serializable {
    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passwordHash;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable=false,unique=true)
    @Pattern(regexp=".+@.+\\....?")
    private String email;

    @Pattern(regexp="\\+?\\d+")
    private String phone;

    @NotNull
    @Column(nullable = false)
    private String address;

    @NotNull
    @Column(nullable = false)
    private Boolean isAdmin;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Recommendation> recommendations = new HashSet<>();

    public User(String passwordHash, String name, String email, String phone, String address, Boolean isAdmin) {
        this.passwordHash = passwordHash;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.isAdmin = isAdmin;
    }

    public User() { }

    public void setId(Long id) { this.id = id; }

    public Long getId() { return id; }

    public String getPasswordHash() { return passwordHash; }

    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public Boolean getIsAdmin() { return isAdmin; }

    public void setIsAdmin(Boolean isAdmin) { this.isAdmin = isAdmin; }

    public Set<Recommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(Set<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof User)) return false;

        User user = (User) o;

        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        if (address != null ? !address.equals(user.address) : user.address != null) return false;
        return (isAdmin != user.isAdmin);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (isAdmin != null ? isAdmin.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", passwordHash='" + passwordHash + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
