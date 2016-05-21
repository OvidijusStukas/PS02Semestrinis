package com.cosisolutions.wms.website.factory;

import com.cosisolutions.wms.website.entity.AccountEntity;
import com.cosisolutions.wms.website.entity.AssetEntity;
import com.cosisolutions.wms.website.mapper.AssetMapper;
import com.cosisolutions.wms.website.models.AssetModel;
import com.cosisolutions.wms.website.repository.AccountRepository;
import com.cosisolutions.wms.website.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AssetFactory {
  @Autowired
  private AssetMapper assetMapper;
  @Autowired
  private AssetRepository assetRepository;
  @Autowired
  private AccountRepository accountRepository;

  public List<AssetModel> createAssetModelsForUser() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    AccountEntity accountEntity = accountRepository.findUserByEmail(auth.getName());

    List<AssetEntity> assetEntities = assetRepository.getAssetsByAccount(accountEntity);
    List<AssetModel> assetModels = new ArrayList<>(assetEntities.size());

    assetEntities.stream().forEach(entity -> {
      AssetModel model = new AssetModel();
      assetMapper.toModel(model, entity);
      assetModels.add(model);
    });

    return assetModels;
  }
}
