package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User findUserByCar(String model, int series) {
      Query query = sessionFactory.getCurrentSession()
              .createQuery("SELECT u FROM User u INNER JOIN Car " +
                      "on u.car.series=:paramSeries and u.car.model=:paramModel");
      query.setParameter("paramSeries", series);
      query.setParameter("paramModel", model);
      return (User) query.getSingleResult();
   }

}
