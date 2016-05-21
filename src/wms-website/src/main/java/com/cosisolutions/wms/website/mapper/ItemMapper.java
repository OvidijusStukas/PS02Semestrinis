package com.cosisolutions.wms.website.mapper;

import com.cosisolutions.wms.website.entity.AssetEntity;
import com.cosisolutions.wms.website.entity.ItemEntity;
import com.cosisolutions.wms.website.entity.ItemGroupEntity;
import com.cosisolutions.wms.website.models.AssetModel;
import com.cosisolutions.wms.website.models.ItemGroupModel;
import com.cosisolutions.wms.website.models.ItemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper implements IMapper<ItemEntity, ItemModel> {
  @Autowired
  private AssetMapper assetMapper;
  @Autowired
  private ItemGroupMapper itemGroupMapper;

  @Override
  public void toModel(ItemModel model, ItemEntity entity) {
    if(model == null || entity == null) return;

    model.setId(entity.getId());
    model.setCode(entity.getCode());
    model.setName(entity.getName());
    model.setCount(entity.getCount());
    model.setDescription(entity.getDescription());

    if(model.getAsset() == null && entity.getAsset() != null)
      model.setAsset(new AssetModel());
    assetMapper.toModel(model.getAsset(), entity.getAsset());
    if(model.getGroup() == null && entity.getGroup() != null)
      model.setGroup(new ItemGroupModel());
    itemGroupMapper.toModel(model.getGroup(), entity.getGroup());
  }

  @Override
  public void toEntity(ItemEntity entity, ItemModel model) {
    if(entity == null || model == null) return;

    entity.setCode(model.getCode());
    entity.setName(model.getName());
    entity.setCount(model.getCount());
    entity.setDescription(model.getDescription());

    if(model.getId() > 0)
      entity.setId(model.getId());

    if(entity.getAsset() == null && model.getAsset() != null)
      entity.setAsset(new AssetEntity());
    assetMapper.toEntity(entity.getAsset(), model.getAsset());
    if(entity.getGroup() == null && model.getGroup() != null)
      entity.setGroup(new ItemGroupEntity());
    itemGroupMapper.toEntity(entity.getGroup(), model.getGroup());
  }
}
