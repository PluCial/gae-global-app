package com.plucial.gae.global.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.ServletContextLocator;

import com.plucial.gae.global.exception.IsNotSupportedLangException;
import com.plucial.gae.global.exception.NoContentsException;
import com.plucial.gae.global.exception.NoLangParameterException;
import com.plucial.global.Lang;

/**
 * Base Controller
 * @author takahara
 *
 */
public abstract class AppController extends Controller {
	
	/**
     * Logger
     */
    private static final Logger logger = Logger.getLogger(AppController.class.getName());
    
    /**
     * デバイスがスマートフォンであるか判定
     * @param request
     * @return
     */
    public boolean isSmartPhone() {

        String userAgent = request.getHeader("User-Agent").toLowerCase();

        if(userAgent != null && (userAgent.indexOf("iphone") > 0 || userAgent.indexOf("android") > 0)) {
            return true;
        }

        return false;
    }
    
    /**
     * 開発環境かどうか
     * @return
     */
    public boolean isLocal(){
        return ServletContextLocator.get().getServerInfo().indexOf("Development") >= 0;
    }
    
    /**
     * エラーハンドリング
     */
    @Override
    public Navigation handleError(Throwable error) throws Throwable {
        
        // 404エラー
        if(error instanceof NoContentsException) {
            return forward("/error/error404");
        }
        
        // 開発環境ではなく、404ではないエラーが発生した場合エラーログを出力
        logger.log(Level.WARNING, error.getMessage(), error);
        
        
        // 500エラー画面に遷移する
        return redirect("/error/error500");
    }
    
    /**
     * ページタイトルの設定
     * @return
     */
    public void setPageTitle(String title) {
        requestScope("pageTitle", title);
    }
    
    /**
     * ページDescriptionの設定
     * @return
     */
    public void setPageDescription(String pageDescription) {
        requestScope("pageDescription", pageDescription);
    }
    
    /**
     * パラメーターから言語を取得
     * @return
     * @throws NoLangParameterException 
     */
    private Lang getLocaleLang() throws NoLangParameterException {
        
    	Lang lang = null;
        try {
        	lang =  Lang.valueOf(asString("lang"));
            
        }catch(Exception e) {
        	Lang baseLang = baseLang();
        	if(baseLang == null) throw new NoLangParameterException();
        	lang = baseLang;
        }
         
        return lang;
    }
    
    /**
     * アクセスしているドメインURLを取得
     * <pre>
     * ローカル： localhost:8888
     * 本番： xxxxx.com
     * </pre>
     * @return
     */
    public String getDomein() {
        String serverName = request.getServerName() != null ? request.getServerName() : "";
        String serverPort = request.getServerPort() > 8000 ? ":" + request.getServerPort() : "";
        return serverName + serverPort;
    }
    
    /**
     * アクセスしているドメインURLを取得
     * <pre>
     * ローカル： http://localhost:8888
     * 本番： https://xxxxx.com
     * </pre>
     * @return 
     */
    public String getDomeinUrl() {
        String scheme = request.getScheme() != null ? request.getScheme() + "://" : "";
        return scheme + getDomein();
    }
    
    /**
     * リクエストURIを取得
     * <pre>
     * 例：/xxxx/xxxx
     * </pre>
     * @return
     */
    public String getRequestUri() {
    	return request.getRequestURI();
    }
    
    /**
     * プロパティを取得
     * @return
     * @throws IOException 
     */
    public Properties getAppProp(Lang lang) throws IOException {
        Properties prop = new Properties();
        InputStream in = this.getClass().getResourceAsStream("/application_" + lang.toString() + ".properties");
        try {
            prop.load(in);
        } finally {
            try {
                in.close();
            } catch (Exception e) {}
        }
        
        return prop;
    }
    
    @Override
    protected Navigation run() throws Exception {

    	Lang localeLang = getLocaleLang();

    	requestScope("localeLang", localeLang);
    	Properties localeProp = getAppProp(localeLang);
    	requestScope("localeProp", localeProp);
    	
    	try {
    		return execute(localeLang, localeProp);
    		
    	}catch(IsNotSupportedLangException e) {
    		throw new NoContentsException();
    	}
    }
    
    /**
     * ベース言語の設定
     * @return
     */
    public abstract Lang baseLang();
    
    /**
     * リクエスト処理
     * @param spot
     * @param client
     * @param isClientLogged
     * @return
     * @throws Exception
     */
    public abstract Navigation execute(Lang localeLang, Properties localeProp) throws Exception;

}
