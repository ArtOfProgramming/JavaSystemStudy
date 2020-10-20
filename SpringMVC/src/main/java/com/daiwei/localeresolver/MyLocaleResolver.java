package com.daiwei.localeresolver;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.LocaleResolver;

public class MyLocaleResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        Locale locale = null;

        String localeStr = request.getParameter("locale");
        if (localeStr!=null && !"".equals(localeStr)){
            locale = new Locale(localeStr.split("_")[0],localeStr.split("_")[1]);
        }else{
            locale = request.getLocale();
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
