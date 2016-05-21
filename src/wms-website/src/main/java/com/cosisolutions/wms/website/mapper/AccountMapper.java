package com.cosisolutions.wms.website.mapper;

import com.cosisolutions.wms.website.entity.AccountEntity;
import com.cosisolutions.wms.website.models.AccountModel;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper implements IMapper<AccountEntity, AccountModel> {
  @Override
  public void toModel(AccountModel model, AccountEntity entity) {
    if(model == null || entity == null) return;

    model.setId(entity.getId());
    model.setEmail(entity.getEmail());
    model.setLastName(entity.getLastName());
    model.setPassword(entity.getPassword());
    model.setFirstName(entity.getFirstName());
    model.setPasswordRepeat(entity.getPassword());
  }

  @Override
  public void toEntity(AccountEntity entity, AccountModel accountModel) {
    if(entity == null || accountModel == null) return;

    entity.setEmail(accountModel.getEmail());
    entity.setPassword(accountModel.getPassword());
    entity.setFirstName(accountModel.getFirstName());
    entity.setLastName(accountModel.getLastName());
    if(accountModel.getId() > 0)
      entity.setId(accountModel.getId());
  }
}
