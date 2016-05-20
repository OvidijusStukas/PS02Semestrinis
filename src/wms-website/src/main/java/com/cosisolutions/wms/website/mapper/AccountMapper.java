package com.cosisolutions.wms.website.mapper;

import com.cosisolutions.wms.website.entity.AccountEntity;
import com.cosisolutions.wms.website.models.AccountModel;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper implements IMapper<AccountEntity, AccountModel> {
  @Override
  public void ToModel(AccountModel model, AccountEntity accountEntity) {
    model.setId(accountEntity.getId());
    model.setEmail(accountEntity.getEmail());
    model.setLastName(accountEntity.getLastName());
    model.setPassword(accountEntity.getPassword());
    model.setFirstName(accountEntity.getFirstName());
    model.setPasswordRepeat(accountEntity.getPassword());
  }

  @Override
  public void ToEntity(AccountEntity entity, AccountModel accountModel) {
    entity.setEmail(accountModel.getEmail());
    entity.setPassword(accountModel.getPassword());
    entity.setFirstName(accountModel.getFirstName());
    entity.setLastName(accountModel.getLastName());
    if(accountModel.getId() > 0)
      entity.setId(accountModel.getId());
  }
}
