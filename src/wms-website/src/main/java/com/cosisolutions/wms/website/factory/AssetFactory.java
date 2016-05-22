package com.cosisolutions.wms.website.factory;

import com.cosisolutions.wms.website.entity.AccountEntity;
import com.cosisolutions.wms.website.entity.AssetEntity;
import com.cosisolutions.wms.website.entity.ItemEntity;
import com.cosisolutions.wms.website.mapper.AssetMapper;
import com.cosisolutions.wms.website.models.AssetModel;
import com.cosisolutions.wms.website.repository.AccountRepository;
import com.cosisolutions.wms.website.repository.AssetRepository;
import com.cosisolutions.wms.website.repository.ItemRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AssetFactory {
  @Autowired
  private AssetMapper assetMapper;
  @Autowired
  private ItemRepository itemRepository;
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

  public Workbook createProductExportFile(AssetEntity assetEntity) {
    List<ItemEntity> items = itemRepository.getEntities(assetEntity);

    Workbook workbook = new org.apache.poi.hssf.usermodel.HSSFWorkbook();
    Sheet sheet = workbook.createSheet(assetEntity.getName());

    Map<Integer, Object[]> data = new HashMap<>(items.size() + 1);
    data.put(1, new Object[] {"Code", "Name", "Count", "Group", "Description"});
    for (int i = 0; i < items.size(); i++) {
      ItemEntity item = items.get(i);
      String description = item.getDescription() != null ? item.getDescription() : "";
      data.put(i + 2, new Object[] {item.getCode(), item.getName(), item.getCount(), item.getGroup().getName(), description});
    }

    int rownum = 0;
    for (Integer key : data.keySet()) {
      int cellnum = 0;
      Row row = sheet.createRow(rownum++);
      for (Object obj : data.get(key)) {
        Cell cell = row.createCell(cellnum++);
        if(obj instanceof String)
          cell.setCellValue((String)obj);
        if(obj instanceof Integer)
          cell.setCellValue((Integer)obj);
      }
    }

    return workbook;
  }
}
