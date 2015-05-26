package io.bsnet.breeze.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

public class SessionUtil {

    public static HttpSession getSession(HttpServletRequest $req){
        return  $req.getSession( true );
    }

    public static void invalidate(HttpSession $session){
        Enumeration<String> enSession = $session.getAttributeNames();
        while(enSession.hasMoreElements()){
            $session.removeAttribute(enSession.nextElement());
        }
        $session.invalidate();
    }

    public static void invalidate(HttpServletRequest $req){
        invalidate(getSession($req));
    }

}
