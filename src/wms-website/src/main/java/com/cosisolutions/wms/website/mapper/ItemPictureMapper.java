package com.cosisolutions.wms.website.mapper;

import com.cosisolutions.wms.website.entity.ItemPictureEntity;
import com.cosisolutions.wms.website.models.ItemPictureModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemPictureMapper implements IMapper<ItemPictureEntity, ItemPictureModel> {
  @Autowired
  private ItemMapper itemMapper;

  @Override
  public void toModel(ItemPictureModel itemPictureModel, ItemPictureEntity itemPictureEntity) {
    itemPictureModel.setId(itemPictureEntity.getId());
    itemPictureModel.setData(itemPictureEntity.getData());

    itemMapper.toModel(itemPictureModel.getItem(), itemPictureEntity.getItem());
  }

  @Override
  public void toEntity(ItemPictureEntity itemPictureEntity, ItemPictureModel itemPictureModel) {
    itemPictureEntity.setData(itemPictureModel.getData());

    if(itemPictureModel.getId() > 0)
      itemPictureEntity.setId(itemPictureModel.getId());

    itemMapper.toEntity(itemPictureEntity.getItem(), itemPictureModel.getItem());
  }
}
