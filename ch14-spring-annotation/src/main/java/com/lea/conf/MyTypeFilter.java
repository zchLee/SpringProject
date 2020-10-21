package com.lea.conf;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * @author lzc
 * @create 2020-10-21 10:18
 */
public class MyTypeFilter implements TypeFilter {
    
    /**
     * @param metadataReader                读取当前正在扫描的类信息
     * @param metadataReaderFactory         可以获取其他任何类型信息
     * @return: boolean
     */
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        // 获取当前类的注解
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        ClassMetadata metadata = metadataReader.getClassMetadata();// 当前正在扫描的类的类信息
        Resource resource = metadataReader.getResource();// 获取当前类资源（类路径）
        System.out.println("-------------->" + resource + "\t" + metadata + "\t" + annotationMetadata);
        if (metadata.getClassName().contains("Service"))
            return true;
        return false;
    }
}
