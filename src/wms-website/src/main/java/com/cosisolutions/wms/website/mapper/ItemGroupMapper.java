package com.cosisolutions.wms.website.mapper;

import com.cosisolutions.wms.website.entity.ItemGroupEntity;
import com.cosisolutions.wms.website.models.ItemGroupModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemGroupMapper implements IMapper<ItemGroupEntity, ItemGroupModel> {
  @Autowired
  private AssetMapper assetMapper;

  @Override
  public void toModel(ItemGroupModel model, ItemGroupEntity entity) {
    if(model == null || entity == null) return;

    model.setId(entity.getId());
    model.setName(entity.getName());

    assetMapper.toModel(model.getAsset(), entity.getAsset());
  }

  @Override
  public void toEntity(ItemGroupEntity entity, ItemGroupModel model) {
    if(entity == null || model == null) return;

    entity.setName(model.getName());

    if(model.getId() > 0)
      entity.setId(model.getId());

    assetMapper.toEntity(entity.getAsset(), model.getAsset());
  }
}
