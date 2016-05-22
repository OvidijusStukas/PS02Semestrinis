package com.cosisolutions.wms.website.repository;

import com.cosisolutions.wms.website.entity.ItemEntity;
import com.cosisolutions.wms.website.entity.ItemPictureEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemPictureRepository extends BaseRepository<ItemPictureEntity> {
  @Autowired
  private SessionFactory sessionFactory;

  public ItemPictureEntity getEntity(ItemEntity itemEntity) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();

    Criteria criteria = session.createCriteria(ItemPictureEntity.class);
    Object picture = criteria.add(Restrictions.eq("item", itemEntity)).uniqueResult();
    session.getTransaction().commit();
    session.close();

    if(picture instanceof ItemPictureEntity)
      return (ItemPictureEntity) picture;
    return null;
  }
}
