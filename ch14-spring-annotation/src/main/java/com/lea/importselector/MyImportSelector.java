package com.lea.importselector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author lzc
 * @create 2020-10-21 11:30
 */
public class MyImportSelector implements ImportSelector {
    /**
     * @param importingClassMetadata  当前标注@Import注解的类 所有的注解信息
     * @return: java.lang.String[]
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        String[] str = new String[]{"com.lea.bean.OtherBlack", "com.lea.bean.OtherWhite"};
        return str;
    }
}
