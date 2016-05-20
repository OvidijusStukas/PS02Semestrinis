package com.cosisolutions.wms.website.validator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegistrationValidator {
  private static final String EMAIL_VALIDATION_QUERY = "FROM AccountEntity WHERE email = :email";

  @Autowired
  private SessionFactory sessionFactory;

  public boolean isEmailAlreadyTaken(String email) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    Query query = session.createQuery(EMAIL_VALIDATION_QUERY);
    query.setParameter("email", email);
    query.setMaxResults(1);
    List list = query.list();
    session.getTransaction().commit();
    session.close();

    return list != null && list.size() > 0;
  }
}
