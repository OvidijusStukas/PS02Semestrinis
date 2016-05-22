package com.cosisolutions.wms.website.repository;

import com.cosisolutions.wms.website.entity.AssetEntity;
import com.cosisolutions.wms.website.entity.ItemGroupEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemGroupRepository extends BaseRepository<ItemGroupEntity> {
  @Autowired
  private SessionFactory sessionFactory;

  @SuppressWarnings("unchecked")
  public List<ItemGroupEntity> getEntities(AssetEntity assetEntity) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();

    Criteria criteria = session.createCriteria(ItemGroupEntity.class);
    List list = criteria.add(Restrictions.eq("asset", assetEntity)).list();
    session.getTransaction().commit();
    session.close();

    if(list == null || list.size() < 1)
      return new ArrayList<>();

    List<ItemGroupEntity> itemGroups = new ArrayList<>(list.size());
    list.stream().filter(o -> o instanceof ItemGroupEntity).forEach(o -> itemGroups.add((ItemGroupEntity) o));
    return itemGroups;
  }
}
