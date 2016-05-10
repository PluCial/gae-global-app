package com.plucial.gae.global.db.model;

import java.io.Serializable;
import java.util.Date;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.CreationDate;
import org.slim3.datastore.ModificationDate;

import com.google.appengine.api.datastore.Key;
import com.plucial.global.Lang;

public abstract class GLangUnit implements Serializable {
	
	private static final long serialVersionUID = 1L;
    
    /**
     * 言語
     */
    private Lang lang;
    
    /**
     * ベース言語
     */
    private Lang baseLang;
    
    /**
     * バージョン
     */
    @Attribute(unindexed = true)
    private int textResVersion = 0;
    
    /**
     * 無効フラグ
     */
    private boolean invalid = false;
    
    /**
     * 作成日時
     */
    @Attribute(listener = CreationDate.class)
    private Date createDate;
    
    /**
     * 更新日時
     */
    @Attribute(listener = ModificationDate.class)
    private Date updateDate;


    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Lang getLang() {
        return lang;
    }

    public void setLang(Lang lang) {
        this.lang = lang;
    }

    public boolean isInvalid() {
        return invalid;
    }

    public void setInvalid(boolean invalid) {
        this.invalid = invalid;
    }

    public Lang getBaseLang() {
        return baseLang;
    }

    public void setBaseLang(Lang baseLang) {
        this.baseLang = baseLang;
    }

    public boolean isBaseLang() {
        return baseLang == lang;
    }
    
    /**
     * Returns the key.
     *
     * @return the key
     */
    public abstract Key getKey();

    /**
     * Sets the key.
     *
     * @param key
     *            the key
     */
    public abstract void setKey(Key key);

	public int getTextResVersion() {
		return textResVersion;
	}

	public void setTextResVersion(int textResVersion) {
		this.textResVersion = textResVersion;
	}
}
