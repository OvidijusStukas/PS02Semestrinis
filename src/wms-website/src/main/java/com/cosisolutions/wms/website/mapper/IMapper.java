package com.cosisolutions.wms.website.mapper;

import java.io.Serializable;

public interface IMapper<Entity extends Serializable, Model> {
  void toModel(Model model, Entity entity);
  void toEntity(Entity entity, Model model);
}
