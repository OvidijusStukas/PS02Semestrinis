package com.cosisolutions.wms.website.helpers;

import com.cosisolutions.wms.website.entity.UserEntity;
import com.cosisolutions.wms.website.models.UserRegisterModel;
import com.cosisolutions.wms.website.repository.BaseRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class AccountHelper {
    // Regex for searching for:
    // At least one upper case letter, at least one digit, at least one special character and at least 8 characters long
    private static String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$";

    @Autowired
    private BaseRepository<UserEntity> userRepository;
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public enum ValidationResult {
        SUCCESS, EMAIL_ERROR, PASSWORD_ERROR, NAME_ERROR
    }

    public ValidationResult validateRegisterUser(UserRegisterModel model){

        if(!model.getEmail().isEmpty() && model.getEmail() != null){

            if(model.getEmail().indexOf('@') == -1)
                return ValidationResult.EMAIL_ERROR;

            String[] splitEmail = model.getEmail().split("@");

            if(splitEmail[1].indexOf('.') == -1)
                return ValidationResult.EMAIL_ERROR;
        }
        else
            return ValidationResult.EMAIL_ERROR;

        if(model.getFirstName().isEmpty() || model.getFirstName() == null)
            return ValidationResult.NAME_ERROR;

        if(model.getLastName().isEmpty() || model.getLastName() == null)
            return ValidationResult.NAME_ERROR;

        /*if(model.getPassword() != null && model.getPassword().equals(model.getPasswordRepeat())){

            if(!model.getPassword().matches(PASSWORD_REGEX))
                return ValidationResult.PASSWORD_ERROR;

        }*/

        return ValidationResult.SUCCESS;
    }

    public boolean registerUser(UserRegisterModel model){
        UserEntity user = new UserEntity();

        user.setFirstName(model.getFirstName());
        user.setLastName(model.getLastName());
        user.setEmail(model.getEmail());
        user.setEnabled(true);
        user.setRole("ROLE_ADMIN");
        user.setPassword(passwordEncoder.encode(model.getPassword()));

        userRepository.insertEntity(user);
        return true;
    }

    public boolean isEmailAvailable(String email) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery(String.format("from UserEntity where email = '%s'", email));
        List list = query.list();
        session.getTransaction().commit();
        session.close();
        return !(list != null && !list.isEmpty());
    }
}