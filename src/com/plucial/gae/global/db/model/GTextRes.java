package com.plucial.gae.global.db.model;

import java.io.Serializable;
import java.util.Date;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.CreationDate;
import org.slim3.datastore.ModelRef;
import org.slim3.datastore.ModificationDate;
import org.slim3.util.StringUtil;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;
import com.plucial.global.Lang;

public abstract class GTextRes<E extends Enum<?>> implements Serializable {
	
	private static final long serialVersionUID = 1L;
    
    /**
     * 言語
     */
    private Lang lang;
    
    /**
     * バージョン
     */
    private int resVersion = 0;
    
    /**
     * コンテンツ
     */
    @Attribute(unindexed = true)
    private Text content;

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
    
    
    public String getContentString() {
        return content == null ? null : content.getValue();
    }
    
    /**
     * 文字列を適切に変換してコンテンツにセットする
     * @param str
     */
    public void setStringToContent(String content) {
        if(StringUtil.isEmpty(content.trim())) throw new NullPointerException();
        
        // 前後の空白を削除
        content = content.trim();

        // 改行をすべて統一
        this.content = new Text(com.plucial.gae.global.utils.StringUtil.unityIndention(content));
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

    public Lang getLang() {
        return lang;
    }

    public void setLang(Lang lang) {
        this.lang = lang;
    }

    public Text getContent() {
        return content;
    }

    public void setContent(Text content) {
        this.content = content;
    }
    

	public abstract E getRole();

	public abstract void setRole(E role);
	
	/** LangUnit との関連 */
    public abstract ModelRef<? extends GLangUnit> getLangUnitRef();
    
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

	public int getResVersion() {
		return resVersion;
	}

	public void setResVersion(int resVersion) {
		this.resVersion = resVersion;
	}
}
