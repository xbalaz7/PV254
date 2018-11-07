package cz.muni.fi.pv254;

import cz.muni.fi.pv254.dao.UserDao;
import cz.muni.fi.pv254.entity.User;

import cz.muni.fi.pv254.spring.PersistenceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;

@ContextConfiguration(classes= PersistenceConfig.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class UserDaoTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private UserDao userDao;

    private User employee;
    private User customerPerson;
    private User customerCompany;

    @BeforeMethod
    public void init() {
        employee = new User();
        employee.setName("employee");
        employee.setAddress("Masarykova 1, Brno");
        employee.setPasswordHash("abc123");
        employee.setEmail("employee@cmr.cz");
        employee.setIsAdmin(Boolean.FALSE);
        
        customerPerson = new User();
        customerPerson.setName("customerPerson");
        customerPerson.setAddress("Masarykova 2, Praha");
        customerPerson.setPasswordHash("def000");
        customerPerson.setEmail("customerPerson@cmr.cz");
        customerPerson.setIsAdmin(Boolean.FALSE);

        customerCompany = new User();
        customerCompany.setName("customer");
        customerCompany.setAddress("Bocni 7, Brno");
        customerCompany.setPasswordHash("aaa777");
        customerCompany.setName("customer");
        customerCompany.setEmail("customer@cmr.cz");
        customerCompany.setIsAdmin(Boolean.FALSE);
    }

//    @Test
//    public void add() {
//        Assert.assertTrue(userDao.findAll().isEmpty());
//        userDao.add(employee);
//        Assert.assertNotNull(employee.getId());
//        Assert.assertEquals(userDao.findAll().size(), 1);
//        userDao.add(customerPerson);
//        Assert.assertEquals(userDao.findAll().size(), 2);
//        userDao.add(customerCompany);
//        Assert.assertEquals(userDao.findAll().size(), 3);
//    }
//
//    @Test
//    public void findAll() {
//        userDao.add(employee);
//        userDao.add(customerCompany);
//        Assert.assertTrue(userDao.findAll().contains(employee));
//        Assert.assertTrue(!userDao.findAll().contains(customerPerson));
//        Assert.assertTrue(userDao.findAll().contains(customerCompany));
//    }
//
//    @Test
//    public void findById() {
//        userDao.add(employee);
//        Assert.assertTrue(userDao.findById(employee.getId()).equals(employee));
//        Assert.assertTrue(userDao.findById(2L) == null);
//    }
//
//    @Test
//    public void findByEmail() {
//        userDao.add(employee);
//        Assert.assertTrue(userDao.findByEmail(employee.getEmail()).equals(employee));
//        Assert.assertTrue(userDao.findByEmail("invalidEmail") == null);
//    }
//
//    @Test
//    public void update() {
//        userDao.add(employee);
//        Assert.assertEquals(employee.getName(), "employee");
//        employee.setName("changedName");
//        userDao.update(employee);
//        Assert.assertEquals(employee.getName(), "changedName");
//    }
//
//    @Test
//    public void remove() {
//        userDao.add(employee);
//        userDao.add(customerPerson);
//        userDao.add(customerCompany);
//        Assert.assertEquals(userDao.findAll().size(), 3);
//        userDao.remove(employee);
//        Assert.assertEquals(userDao.findAll().size(), 2);
//        userDao.remove(customerPerson);
//        Assert.assertEquals(userDao.findAll().size(), 1);
//        userDao.remove(customerCompany);
//        Assert.assertTrue(userDao.findAll().isEmpty());
//    }
//
//    @Test(expectedExceptions = ValidationException.class)
//    public void createUserWithNullName() {
//        employee.setName(null);
//        userDao.add(employee);
//    }
//
//    @Test(expectedExceptions = ValidationException.class)
//    public void createUserWithNullEmail() {
//        employee.setEmail(null);
//        userDao.add(employee);
//    }
//
//    @Test(expectedExceptions = ValidationException.class)
//    public void createUserWithWrongEmail() {
//        employee.setEmail("email");
//        userDao.add(employee);
//    }
}
