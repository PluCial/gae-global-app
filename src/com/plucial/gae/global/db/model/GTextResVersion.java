package com.plucial.gae.global.db.model;

import java.io.Serializable;
import java.util.Date;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.CreationDate;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModificationDate;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;

@Model(schemaVersion = 1)
public class GTextResVersion implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * キー
     */
    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;
    
    /**
	 * 適用バージョン
	 */
    @Attribute(unindexed = true)
	private int resVersion = 0;
	
	/**
	 * 最終バージョン
	 */
    @Attribute(unindexed = true)
	private int lastResVersion = 0;
	
	/**
     * コンテンツ
     */
    @Attribute(unindexed = true)
    private Text memo;
    
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
    

    // ----------------------------------------------------------------------
    // ゲッター & セッター
    // ----------------------------------------------------------------------

    /**
     * Returns the key.
     *
     * @return the key
     */
    public Key getKey() {
        return key;
    }

    /**
     * Sets the key.
     *
     * @param key
     *            the key
     */
    public void setKey(Key key) {
        this.key = key;
    }

    /**
     * Returns the version.
     *
     * @return the version
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Sets the version.
     *
     * @param version
     *            the version
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        GTextResVersion other = (GTextResVersion) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }

	public int getResVersion() {
		return resVersion;
	}

	public void setResVersion(int resVersion) {
		this.resVersion = resVersion;
	}

	public int getLastResVersion() {
		return lastResVersion;
	}

	public void setLastResVersion(int lastResVersion) {
		this.lastResVersion = lastResVersion;
	}

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
}
