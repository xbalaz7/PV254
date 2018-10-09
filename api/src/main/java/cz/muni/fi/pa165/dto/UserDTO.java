package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.enums.LegalStatusEnum;

import java.util.HashSet;
import java.util.Set;

public class UserDTO {
    private Long id;
    private String passwordHash;
    private String name;
    private String email;
    private String phone;
    private String address;
    private Boolean isAdmin;
    private LegalStatusEnum legalStatus;

    public UserDTO() { }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

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

    public LegalStatusEnum getLegalStatus() { return legalStatus; }

    public void setLegalStatus(LegalStatusEnum legalStatus) { this.legalStatus = legalStatus; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof UserDTO)) return false;

        UserDTO userDto = (UserDTO) o;

        if (name != null ? !name.equals(userDto.name) : userDto.name != null) return false;
        if (email != null ? !email.equals(userDto.email) : userDto.email != null) return false;
        if (phone != null ? !phone.equals(userDto.phone) : userDto.phone != null) return false;
        return address != null ? address.equals(userDto.address) : userDto.address == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", passwordHash='" + passwordHash + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", isAdmin=" + isAdmin +
                ", legalStatus=" + legalStatus +
                '}';
    }
}
