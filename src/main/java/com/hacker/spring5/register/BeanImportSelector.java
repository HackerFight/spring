package com.hacker.spring5.register;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Set;

/**
 * @author Hacker
 * @date：2018/12/21
 * @project spring
 * @describe  实现这个接口，也可以像容器注册组件，然后通过 @Import() 将这个类导入容器
 *
 */
public class BeanImportSelector implements ImportSelector{

    /**
     * 返回值就是向容器注册的bean，注意是全类名
     * @param importingClassMetadata
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Set<String> annotationTypes = importingClassMetadata.getAnnotationTypes();
        System.out.println("测试结果：" + annotationTypes); //可以拿到 当前标注@Import注解的类的 所有注解信息
        return new String[]{"com.hacker.spring5.otherbean.Toys","com.hacker.spring5.otherbean.Sun"};
    }
}
