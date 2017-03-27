package com.bm.sy.filmstudio.util;

/**
 * 创建人: 李俊男
 * 类名称：ObjectUtils
 * 类描述：
 * 创建时间：2017/2/14 0:42
 */
public class ObjectUtils {
    private static final String TAG = "【ObjectUtils】";

    private ObjectUtils() {
        throw new AssertionError();
    }

    public static boolean isEquals(Object actual, Object expected) {
        if (actual != expected) {
            if (actual == null) {
                if (expected != null) {
                    return false;
                }
            } else if (!actual.equals(expected)) {
                return false;
            }
        }

        return true;
    }

    public static String nullStrToEmpty(Object str) {
        return str == null ? "" : (str instanceof String ? (String) str : str.toString());
    }

    public static Long[] transformLongArray(long[] source) {
        Long[] destin = new Long[source.length];

        for (int i = 0; i < source.length; ++i) {
            destin[i] = Long.valueOf(source[i]);
        }

        return destin;
    }

    public static long[] transformLongArray(Long[] source) {
        long[] destin = new long[source.length];

        for (int i = 0; i < source.length; ++i) {
            destin[i] = source[i].longValue();
        }

        return destin;
    }

    public static Integer[] transformIntArray(int[] source) {
        Integer[] destin = new Integer[source.length];

        for (int i = 0; i < source.length; ++i) {
            destin[i] = Integer.valueOf(source[i]);
        }

        return destin;
    }

    public static int[] transformIntArray(Integer[] source) {
        int[] destin = new int[source.length];

        for (int i = 0; i < source.length; ++i) {
            destin[i] = source[i].intValue();
        }

        return destin;
    }

    public static <V> int compare(V v1, V v2) {
        return v1 == null ? (v2 == null ? 0 : -1) : (v2 == null ? 1 : ((Comparable) v1).compareTo(v2));
    }
}

