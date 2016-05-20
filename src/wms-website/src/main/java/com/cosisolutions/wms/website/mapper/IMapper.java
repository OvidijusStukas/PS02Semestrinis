package com.cosisolutions.wms.website.mapper;

import java.io.Serializable;

public interface IMapper<Entity extends Serializable, Model> {
  void ToModel(Model model, Entity entity);
  void ToEntity(Entity entity, Model model);
}
