package com.amber.common.handler;

import com.amber.common.entity.SerializeType;
import com.amber.common.util.ReflectionKit;
//import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.*;

@Slf4j
public abstract class AbstractHandler<T extends Annotation> {


    private final Class<T> annotationClass;

    public AbstractHandler(Class<T> annotationClass) {
        this.annotationClass = annotationClass;
    }

    abstract Map<Class<?>, Set<Field>> getFieldsMap();

    abstract Set<Class<?>> getClassSet();


    /**
     * 是否解析过该class
     *
     * @param oClass
     * @return
     */
    public boolean isHandleClass(Class<?> oClass) {
        return getClassSet().contains(oClass);
    }


    /**
     * 将该class标识为解析过
     *
     * @param oClass
     */
    public void addHandleClass(Class<?> oClass) {
        getClassSet().add(oClass);
    }

    /**
     * 获取字段列表
     *
     * @param oClass
     * @return
     */
    public Set<Field> getFields(Class<?> oClass) {
        return getFieldsMap().get(oClass);
    }

    /**
     * 添加字段缓存
     *
     * @param oClass
     * @param field
     */
    public void addField(Class<?> oClass, Field field) {
        getFieldsMap().computeIfAbsent(oClass, k -> new HashSet<>());
        getFields(oClass).add(field);
    }

    /**
     * 聚合父类属性
     *
     * @param oClass
     * @return
     */
    public void parse(Class<?> oClass) {
        parseClass(oClass, new HashSet<>());
    }

    private boolean parseClass(Class<?> oClass, Set<Class<?>> foreachClassList) {
        // 已处理的类无需再次处理
        if (isHandleClass(oClass)) {
            Set<Field> fields = getFields(oClass);
            return fields != null && !fields.isEmpty();
        }

        foreachClassList.add(oClass);

        List<Field> fieldList = ReflectionKit.getFieldList(oClass);

        boolean haveAnnotationField = false;

        for (Field declaredField : fieldList) {

            Class<?> declaringClass = declaredField.getDeclaringClass();
            Class<?> declaredFieldType = declaredField.getType();

            // 如果字符类型
            if (CharSequence.class.isAssignableFrom(declaredFieldType)) {
                T annotation = declaredField.getAnnotation(this.annotationClass);
                if (annotation != null) {
                    addField(declaringClass, declaredField);
                    haveAnnotationField = true;
                }
                // 如果该字段为数组类型
            } else if (Collection.class.isAssignableFrom(declaredFieldType)) {
                Class<?> actualTypeArgumentClass;
                try {
                    actualTypeArgumentClass = Class.forName(((ParameterizedType) declaredField.getGenericType()).getActualTypeArguments()[0].getTypeName());

                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

                if (foreachClassList.contains(actualTypeArgumentClass)) {
                    haveAnnotationField = true;
                    addField(declaringClass, declaredField);
                    continue;
                }

                boolean childHaveAnnotationField = parseClass(actualTypeArgumentClass, foreachClassList);
                if (childHaveAnnotationField) {
                    addField(declaringClass, declaredField);
                    haveAnnotationField = true;
                }
                // 如果该字段为对象类型
            } else if (Object.class.isAssignableFrom(declaredFieldType)) {

                if (foreachClassList.contains(declaredFieldType)) {
                    haveAnnotationField = true;
                    addField(declaringClass, declaredField);
                    continue;
                }
                boolean childHaveAnnotationField = parseClass(declaredFieldType, foreachClassList);
                if (childHaveAnnotationField) {
                    addField(declaringClass, declaredField);
                    haveAnnotationField = true;
                }
            }

        }
        // 标记为已处理
        addHandleClass(oClass);

        return haveAnnotationField;
    }

    abstract String getAfterValue(T annotation, String originalValue, SerializeType serializeType);

    /**
     * 处理Object
     *
     * @param obj
     * @param oClass
     */
    protected void handleObject(Object obj, Class<?> oClass, SerializeType serializeType) {


        Set<Field> fields = getFields(oClass);
        if (fields == null || fields.isEmpty()) {
            return;
        }
        for (Field declaredField : fields) {

            //静态属性直接跳过
            if (Modifier.isStatic(declaredField.getModifiers())) {
                continue;
            }

            boolean accessible = declaredField.isAccessible();
            declaredField.setAccessible(true);
            Object value;
            try {
                value = declaredField.get(obj);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            declaredField.setAccessible(accessible);
            if (value == null || value instanceof Number) {
                continue;
            }

            if (value instanceof CharSequence) {
                T annotation = declaredField.getAnnotation(annotationClass);
                if (annotation != null) {
                    try {
                        setValue(declaredField, obj, annotation, serializeType);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }

            } else if (value instanceof Collection) {
                Collection coll = (Collection) value;
                for (Object o : coll) {
                    handleObject(o, o.getClass(), serializeType);
                }
            } else {
                handleObject(value, value.getClass(), serializeType);
            }


        }

    }

    /**
     * 处理字符
     *
     * @param field  字段
     * @param object 字段原值
     * @throws IllegalAccessException
     */
    public void setValue(Field field, Object object, T annotation, SerializeType serializeType) throws IllegalAccessException {

        boolean accessible = field.isAccessible();
        field.setAccessible(true);
        String value = (String) field.get(object);
        if (annotation != null) {
            String afterValue = getAfterValue(annotation, value, serializeType);
            log.debug("原值：" + value);
            log.debug("处理后：" + afterValue);
            field.set(object, afterValue);
            field.setAccessible(accessible);
        }
    }


}
