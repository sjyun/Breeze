package io.bsnet.breeze.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

public class BreezeStringUtil {

    protected final static Logger logger = LoggerFactory.getLogger(BreezeStringUtil.class);

    public static String parameterEncoding( String $param, String $encoding ){
        try{
            $param = new String( $param.getBytes("8859_1"), $encoding);
        }catch( UnsupportedEncodingException e){
            logger.error("", e);
        }
        return $param;
    }
}
