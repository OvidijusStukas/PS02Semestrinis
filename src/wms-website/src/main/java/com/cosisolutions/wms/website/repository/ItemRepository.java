package com.cosisolutions.wms.website.repository;

import com.cosisolutions.wms.website.entity.AssetEntity;
import com.cosisolutions.wms.website.entity.ItemEntity;
import com.cosisolutions.wms.website.entity.ItemGroupEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemRepository extends BaseRepository<ItemEntity> {
  @Autowired
  private SessionFactory sessionFactory;

  @SuppressWarnings("unchecked")
  public List<ItemEntity> getEntities(AssetEntity assetEntity) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();

    Criteria criteria = session.createCriteria(ItemEntity.class);
    List list = criteria.add(Restrictions.eq("asset", assetEntity)).list();
    session.getTransaction().commit();
    session.close();

    if(list == null || list.size() < 1)
      return new ArrayList<>();

    List<ItemEntity> items = new ArrayList<>(list.size());
    list.stream().filter(o -> o instanceof ItemEntity).forEach(o -> items.add((ItemEntity) o));
    return items;
  }

  @SuppressWarnings("unchecked")
  public List<ItemEntity> search(AssetEntity assetEntity, String search) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();

    Criteria criteria = session.createCriteria(ItemEntity.class);
    Criterion criterion1 = Restrictions
      .and(Restrictions.eq("asset", assetEntity),
           Restrictions.like("name", search, MatchMode.ANYWHERE));
    Criterion criterion2 = Restrictions
      .and(Restrictions.eq("asset", assetEntity),
        Restrictions.like("description", search, MatchMode.ANYWHERE));
    Criterion criterion3 = Restrictions
      .and(Restrictions.eq("asset", assetEntity),
        Restrictions.like("code", search, MatchMode.ANYWHERE));
    List list = criteria.add(Restrictions.or(criterion1, criterion2, criterion3)).list();
    session.getTransaction().commit();
    session.close();

    if(list == null || list.size() < 1)
      return new ArrayList<>();

    List<ItemEntity> items = new ArrayList<>(list.size());
    list.stream().filter(o -> o instanceof ItemEntity).forEach(o -> items.add((ItemEntity) o));
    return items;
  }

  @SuppressWarnings("unchecked")
  public List<ItemEntity> searchGroup(AssetEntity assetEntity, ItemGroupEntity groupEntity) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();

    Criteria criteria = session.createCriteria(ItemEntity.class);
    Criterion criterion1 = Restrictions.eq("asset", assetEntity);
    Criterion criterion2 = Restrictions.eq("group", groupEntity);
    List list = criteria.add(Restrictions.and(criterion1, criterion2)).list();
    session.getTransaction().commit();
    session.close();

    if (list == null || list.size() < 1)
      return new ArrayList<>();

    List<ItemEntity> items = new ArrayList<>(list.size());
    list.stream().filter(o -> o instanceof ItemEntity).forEach(o -> items.add((ItemEntity) o));
    return items;
  }
}
