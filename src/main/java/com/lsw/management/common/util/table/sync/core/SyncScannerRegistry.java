package com.lsw.management.common.util.table.sync.core;

import com.lsw.management.common.util.table.sync.annotation.EnableTableSync;
import com.lsw.management.common.util.table.sync.annotation.TableSync;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lsw
 * @Date 2023/4/10 9:31
 * @desc
 */
public class SyncScannerRegistry implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {

    public static final Logger LOGGER = LoggerFactory.getLogger(SyncScannerRegistry.class);

    private ResourceLoader resourceLoader;


    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        LOGGER.info("开始扫描注解----------------->>>>>>>");
        AnnotationAttributes annoAttrs = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(EnableTableSync.class.getName()));
        ClassPathSyncScanner classPathSyncScanner = new ClassPathSyncScanner(registry, TableSync.class);
        if (resourceLoader != null) {
            classPathSyncScanner.setResourceLoader(resourceLoader);
        }
        Class<? extends Annotation> annotationClass = annoAttrs.getClass("annotationClass");
        if (!Annotation.class.equals(annotationClass)) {
            classPathSyncScanner.setAnnotationClass(annotationClass);
        }
        // 扫描指定的类和包
        List<String> basePackages = new ArrayList<>();
        for (String pkg : annoAttrs.getStringArray("value")) {
            if (StringUtils.hasText(pkg)) {
                basePackages.add(pkg);
            }
        }
        for (String pkg : annoAttrs.getStringArray("basePackages")) {
            if (StringUtils.hasText(pkg)) {
                basePackages.add(pkg);
            }
        }
        for (Class<?> clazz : annoAttrs.getClassArray("basePackageClasses")) {
            basePackages.add(ClassUtils.getPackageName(clazz));
        }
        //添加过滤器
        classPathSyncScanner.registerFilters();
        classPathSyncScanner.doScan(StringUtils.toStringArray(basePackages));
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
