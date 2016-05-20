package com.cosisolutions.wms.website.repository;

import com.cosisolutions.wms.website.entity.AccountEntity;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountRepository extends BaseRepository<AccountEntity> {

  public AccountEntity findUserByEmail(String email) {
    List<?> list = hibernateTemplate.findByCriteria(DetachedCriteria.forClass(AccountEntity.class).add(Restrictions.eq("email", email)));
    if(list == null || list.size() < 1)
      return null;

    if(list.get(0) instanceof AccountEntity) {
      return (AccountEntity) list.get(0);
    }
    return null;
  }
}
