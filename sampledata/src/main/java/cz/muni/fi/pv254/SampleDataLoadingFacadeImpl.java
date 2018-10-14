package cz.muni.fi.pv254;

import cz.muni.fi.pv254.entity.User;
import cz.muni.fi.pv254.enums.LegalStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {


    @Autowired
    private UserService userService;

    @Override
    public void loadData() throws IOException {
        User admin = user("password", "Admin", "admin@google.com", "766766766", "Praha", LegalStatusEnum.PERSON, Boolean.TRUE);
        User user = user("password", "User", "user@google.com", "755755755", "Olomouc", LegalStatusEnum.PERSON, Boolean.FALSE);
    }

    private User user(String password, String name, String email, String phone, String address, LegalStatusEnum ls, Boolean isAdmin) {
        User u = new User();
        u.setName(name);
        u.setEmail(email);
        u.setPhone(phone);
        u.setAddress(address);
        u.setLegalStatus(ls);
        u.setIsAdmin(isAdmin);
        User existing = userService.findByEmail(email);
        if (existing == null)
            userService.registerUser(u, password);
        return u;
    }
}