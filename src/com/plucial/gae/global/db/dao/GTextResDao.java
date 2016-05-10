package com.plucial.gae.global.db.dao;

import java.util.List;

import org.slim3.datastore.DaoBase;

import com.plucial.gae.global.db.model.GLangUnit;
import com.plucial.gae.global.db.model.GTextRes;

public abstract class GTextResDao<T extends GTextRes<?>, L extends GLangUnit> extends DaoBase<T> {
    
    
    /**
     * テキストリソースのリストを取得
     * @param langUnit
     * @return
     */
    public abstract List<T> getResList(L langUnit);

}
