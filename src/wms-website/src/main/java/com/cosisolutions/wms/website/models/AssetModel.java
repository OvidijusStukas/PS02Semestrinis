package com.cosisolutions.wms.website.models;

import com.cosisolutions.wms.website.entity.AssetEntity;
import com.cosisolutions.wms.website.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CosISolutions on 2016-04-26.
 */
public class AssetModel {

    @Autowired
    private BaseRepository<AssetEntity> AssetRepository;

    private List<AssetEntity> assetList = null;

    public AssetModel() {

        assetList = new ArrayList<>();
        fillAssetList();
    }

    public void fillAssetList() {
        assetList = AssetRepository.getEntities(AssetEntity.class);
    }

    public List<AssetEntity> getList() {
        return assetList;
    }

}
