package com.property.colpencil.colpencilandroidlibrary.Function.Tools;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.XmlResourceParser;
import android.text.TextUtils;

import com.property.colpencil.colpencilandroidlibrary.Function.ColpencilLogger.FileLog;

import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import dalvik.system.PathClassLoader;

/**
 * @Description:类的反射工具
 * @author 汪 亮
 * @Email  DramaScript@outlook.com
 * @date 16/6/23
 */
public class ClassTypeReflectTool {

    private static final String TAG = "ReflectionUtil";
    private static final String ID = "$id";
    private static final String LAYOUT = "$layout";
    private static final String STYLE = "$style";
    private static final String STRING = "$string";
    private static final String DRAWABLE = "$drawable";
    private static final String ARRAY = "$array";
    private static final String COLOR = "color";
    private static final String ANIM = "anim";

    public static Type getModelClazz(Class<?> subclass) {
        return getGenericType(0, subclass);
    }

    private static Type getGenericType(int index, Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (!(superclass instanceof ParameterizedType)) {
            return Object.class;
        }
        Type[] params = ((ParameterizedType) superclass).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            throw new RuntimeException("Index outof bounds");
        }

        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return params[index];
    }

    /**
     * 从SDcard读取layout
     *
     * @return
     */
    public static XmlPullParser getLayoutXmlPullParser(Context context, String filePath, String fileName) {
        XmlResourceParser paser = null;
        AssetManager asset = context.getResources().getAssets();
        try {
            Method method = asset.getClass().getMethod("addAssetPath", String.class);
            int cookie = (Integer) method.invoke(asset, filePath);
            if (cookie == 0) {
                FileLog.e(TAG, "加载路径失败");
            }
            paser = asset.openXmlResourceParser(cookie, fileName + ".xml");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return paser;
    }

    /**
     * 获取类里面的所在字段
     */
    public static Field[] getFields(Class clazz) {
        Field[] fields = null;
        fields = clazz.getDeclaredFields();
        if (fields == null || fields.length == 0) {
            Class superClazz = clazz.getSuperclass();
            if (superClazz != null) {
                fields = getFields(superClazz);
            }
        }
        return fields;
    }

    /**
     * 获取类里面的指定对象，如果该类没有则从父类查询
     */
    public static Field getField(Class clazz, String name) {
        Field field = null;
        try {
            field = clazz.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            try {
                field = clazz.getField(name);
            } catch (NoSuchFieldException e1) {
                if (clazz.getSuperclass() == null) {
                    return field;
                } else {
                    field = getField(clazz.getSuperclass(), name);
                }
            }
        }
        if (field != null) {
            field.setAccessible(true);
        }
        return field;
    }

    /**
     * 利用递归找一个类的指定方法，如果找不到，去父亲里面找直到最上层Object对象为止。
     *
     * @param clazz      目标类
     * @param methodName 方法名
     * @param params     方法参数类型数组
     * @return 方法对象
     */
    public static Method getMethod(Class clazz, String methodName, final Class<?>... params) {
        Method method = null;
        try {
            method = clazz.getDeclaredMethod(methodName, params);
        } catch (NoSuchMethodException e) {
            try {
                method = clazz.getMethod(methodName, params);
            } catch (NoSuchMethodException ex) {
                if (clazz.getSuperclass() == null) {
                    return method;
                } else {
                    method = getMethod(clazz.getSuperclass(), methodName, params);
                }
            }
        }
        if (method != null) {
            method.setAccessible(true);
        }
        return method;
    }

    /**
     * 加载指定的反射类
     */
    public static Class<?> loadClass(Context context, String ClassName) {
        String packageName = AndroidUtils.getPackageName(context);
        String sourcePath = AndroidUtils.getSourcePath(context, packageName);
        if (!TextUtils.isEmpty(sourcePath)) {
            PathClassLoader cl = new PathClassLoader(sourcePath, "/data/app/", ClassLoader.getSystemClassLoader());
            try {
                return cl.loadClass(ClassName);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            FileLog.e(TAG, "没有【" + sourcePath + "】目录");
        }
        return null;
    }

}
