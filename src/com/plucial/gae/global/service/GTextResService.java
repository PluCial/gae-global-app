package com.plucial.gae.global.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.plucial.gae.global.db.dao.GTextResDao;
import com.plucial.gae.global.db.model.GLangUnit;
import com.plucial.gae.global.db.model.GTextRes;
import com.plucial.gae.global.exception.ObjectNotExistException;


public class GTextResService<E extends Enum<?>, T extends GTextRes<E>, L extends GLangUnit> {
    
    /** DAO */
    private GTextResDao<T, L> dao;
    
    private Class<T> modelClass;
    
    /**
     * コンストラクター
     * @param dao
     * @param langUnit
     * @param modelClass
     */
    public GTextResService(GTextResDao<T, L> dao, Class<T> modelClass) {
    	this.dao = dao;
    	this.modelClass = modelClass;
    }

    
    /**
     * リソースの取得
     * @param resourcesKey
     * @return
     * @throws ObjectNotExistException 
     */
    public T get(String resourcesKey) throws ObjectNotExistException {
        T model =  dao.getOrNull(createKey(resourcesKey));
        if(model == null) throw new ObjectNotExistException();
        return model;
    }
    
    /**
     * リストの取得
     * @param langUnit
     * @return
     * @throws ObjectNotExistException
     */
    public List<T> getList(L langUnit) throws ObjectNotExistException { 
        List<T> list = dao.getResList(langUnit);
        if(list == null || list.size() <= 0) throw new ObjectNotExistException();
        
        return list;
    }
    
    /**
     * 追加
     * @param tx
     * @param object
     * @param langUnit
     * @param role
     * @param content
     * @return
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     */
    public T put(
            Transaction tx, 
            L langUnit,
            E role, 
            String content) throws InstantiationException, IllegalAccessException {
        
        T model = modelClass.newInstance();
        model.setKey(createKey(langUnit));
        model.setLang(langUnit.getLang());
        model.setResVersion(langUnit.getTextResVersion());
        model.setRole(role);
        model.setStringToContent(content);
        
        // 関連
        model.getLangUnitRef().setKey(langUnit.getKey());
        
        // 保存
        Datastore.put(tx, model);
        
        return model;
    }
    
    /**
     * リソースの更新
     * @param resourcesKey
     * @param content
     * @return
     * @throws ObjectNotExistException 
     */
    public T update(String resourcesKey, String content) throws ObjectNotExistException {
        T model = get(resourcesKey);

        model.setStringToContent(content);
        dao.put(model);
        
        return model;
    }
    
    /**
     * リソースマップを取得
     * @param resourcesList
     * @return
     * @throws ObjectNotExistException 
     */
    public Map<String, T> getResMap(L langUnit) throws ObjectNotExistException {
        
        List<T> list = getList(langUnit);
        
        return getResMap(list);
    }
    
    /**
     * リストからリソースマップを取得
     * @param TextResList
     * @return
     */
    public Map<String, T> getResMap(List<T> resList) {
        
        Map<String,T> map = new HashMap<String,T>();
        for (T i : resList) map.put(i.getRole().toString(),i);

        return map;
    }
    
    /**
     * リソースの取得
     * @param resourcesMap
     * @param role
     * @return
     */
    public T getResByMap(Map<String, T> resMap, E role) {
        if(resMap == null) return null; 
        return resMap.get(role.toString());
    }
    
    
    
    
    /**
     * キーの作成(リソース変更用)
     * @param keyString
     * @return
     */
    public Key createKey(String keyString) {
        return Datastore.createKey(modelClass, keyString);
    }
    
    /**
     * キーの作成(保存用)
     * @param langUnit
     * @return
     */
    public Key createKey(L langUnit) {
        // キーを乱数にする
        UUID uniqueKey = UUID.randomUUID();
        
        return Datastore.createKey(modelClass, langUnit.getKey().getName() + "_" + uniqueKey.toString());
    }

}
