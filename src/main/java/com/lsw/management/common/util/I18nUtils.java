package com.lsw.management.common.util;

import com.lsw.management.common.constants.ErrorCode;
import com.lsw.management.common.exception.BusinessException;
import org.springframework.lang.Nullable;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author zhangyh
 * @Date 2023/4/14 11:00
 * @desc
 */
public class I18nUtils {

    public static String getMessage(String code){
        return getMessage(code,null,null);
    }

    public static String getMessage(String code, @Nullable Object[] args, Locale locale) {
        HttpServletRequest request = RequestHolder.getHttpServletRequest();
        ServletContext servletContext = request.getServletContext();
        WebApplicationContext webApplicationContext = (WebApplicationContext) request.getAttribute(RequestContext.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        if (webApplicationContext == null) {
            webApplicationContext = RequestContextUtils.findWebApplicationContext(request, servletContext);
            if (webApplicationContext == null) {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR);
            }
        }
        String requestLocal;
        if(locale==null){
            requestLocal = getRequestLocal();
            locale=Locale.forLanguageTag(requestLocal);
        }
        return webApplicationContext.getMessage(code, args, locale);
    }

    /**
     * 从HttpServletRequest中获取Locale
     */
    public static String getRequestLocal() {
        HttpServletRequest request = RequestHolder.getHttpServletRequest();
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if (localeResolver == null) {
            throw new BusinessException(ErrorCode.NO_LOCALE_RESOLVER_FOUND);
        }
        return localeResolver.resolveLocale(request).toString();
    }

    public static void main(String[] args) {
        // 获取当前时间
        LocalDate currentDate = LocalDate.now();

        // 创建格式化器对象
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/d");

        // 格式化 LocalDate 对象
        String formattedDate = currentDate.format(formatter);
        System.out.println(formattedDate); // 2022/11/10
    }
}
