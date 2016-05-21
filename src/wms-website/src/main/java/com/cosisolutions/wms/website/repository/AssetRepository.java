package com.cosisolutions.wms.website.repository;

import com.cosisolutions.wms.website.entity.AccountEntity;
import com.cosisolutions.wms.website.entity.AssetEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AssetRepository extends BaseRepository<AssetEntity> {
  @Autowired
  private SessionFactory sessionFactory;

  @SuppressWarnings("unchecked")
  public List<AssetEntity> getAssetsByAccount(AccountEntity account) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();

    Criteria criteria = session.createCriteria(AssetEntity.class);
    List list = criteria.add(Restrictions.eq("account", account)).list();
    session.getTransaction().commit();
    session.close();

    if(list == null || list.size() < 1)
      return new ArrayList<>();

    List<AssetEntity> assets = new ArrayList<>(list.size());
    list.stream().filter(o -> o instanceof AssetEntity).forEach(o -> assets.add((AssetEntity) o));
    return assets;
  }
}
