package web.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        TypedQuery<User> query = entityManager.createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    public void saveUser(User user) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.persist(user);
    }

    @Override
    public User getUserById(int id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        return entityManager.find(User.class, id);
    }

    @Override
    public void deleteUserById(int id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }
}
