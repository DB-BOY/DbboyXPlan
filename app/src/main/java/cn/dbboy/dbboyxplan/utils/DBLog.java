package cn.dbboy.dbboyxplan.utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by db.boy on 2019/1/11.
 */
public class DBLog {
    public static final int LINE_MAX = 1024 * 3;
    private static final int DIVIDER_TOP = 1;
    private static final int DIVIDER_BOTTOM = 2;
    private static final int DIVIDER_CENTER = 4;
    private static final int DIVIDER_NORMAL = 3;
    private static final int METHOD_COUNT = 2; // defaultShow method count in trace
    private static final int MIN_STACK_OFFSET = 3;// starts at this class after two native calls
    private static String TAG = "--db.boy--";

    public static void i(Object msg) {
        i(TAG, msg);
    }

    public static void i(String tag, Object msg) {
        print(2, tag, msg);
//        Log.i(tag,msg.toString());
    }

    public static void d(Object msg) {
        d(TAG, msg);
    }

    public static void d(String tag, Object msg) {
        print(1, tag, msg);
    }

    public static void e(Object msg) {
        e(TAG, msg);
    }

    public static void e(String tag, Object msg) {
        print(4, tag, msg);
    }

    /**
     * 长字符串转化为List
     *
     * @param msg
     *
     * @return
     */
    private static List<String> largeStringToList(String msg) {
        List<String> stringList = new ArrayList<>();
        int index = 0;
        int maxLength = LINE_MAX;
        int countOfSub = msg.length() / maxLength;
        if (countOfSub > 0) {
            for (int i = 0; i < countOfSub; i++) {
                String sub = msg.substring(index, index + maxLength);
                stringList.add(sub);
                index += maxLength;
            }
            stringList.add(msg.substring(index, msg.length()));
        } else {
            stringList.add(msg);
        }
        return stringList;
    }

    private static void logString(int level, String tag, String msg) {
        if (msg.length() > LINE_MAX) {
            for (String subMsg : largeStringToList(msg)) {
                logString(level, tag, subMsg);
            }
        } else {
            printLog(level, tag, getDivider(DIVIDER_NORMAL) + "\t" + msg);
        }
    }

    private static void print(int level, String tag, Object msg) {
        String str = msg.toString();
        printLog(level, tag, getDivider(DIVIDER_TOP));
        printLog(level, tag, getDivider(DIVIDER_NORMAL) + getTraceElement());
        printLog(level, tag, getDivider(DIVIDER_CENTER));//
        for (String subMsg : largeStringToList(str)) {
            logString(level, tag, subMsg);
        }

        printLog(level, tag, getDivider(DIVIDER_BOTTOM));
    }

    private static void printLog(int type, String tag, String msg) {
        switch (type) {
            case 0:
                Log.v(tag, msg);
                break;
            case 1:
                Log.d(tag, msg);
                break;
            case 2:
                Log.i(tag, msg);
                break;
            case 3:
                Log.w(tag, msg);
                break;
            case 4:
                Log.e(tag, msg);
                break;
            case 5:
                Log.wtf(tag, msg);
                break;
            default:
                break;
        }
    }

    private static String getDivider(int dir) {
        switch (dir) {
            case DIVIDER_TOP:
                return "╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════";
            case DIVIDER_BOTTOM:
                return "╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════";
            case DIVIDER_NORMAL:
                return "║ ";
            case DIVIDER_CENTER:
                return "╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────";
            default:
                break;
        }
        return "";
    }

    /**
     * 获取当前activity栈信息
     *
     * @return
     */
    private static StackTraceElement getCurrentStackTrace() {
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        StackTraceElement caller = trace[1];
        return caller;
    }
    

    /**
     * 可显示调用方法所在的文件行号，在AndroidStudio的logcat处可点击定位。
     * 此方法参考：https://github.com/orhanobut/logger
     */
    private static String getTraceElement() {
        try {
            int methodCount = METHOD_COUNT;
            StackTraceElement[] trace = Thread.currentThread().getStackTrace();
            int stackOffset = _getStackOffset(trace);

            //corresponding method count with the current stack may exceeds the stack trace. Trims the count
            if (methodCount + stackOffset > trace.length) {
                methodCount = trace.length - stackOffset - 1;
            }

            String level = "    ";
            StringBuilder builder = new StringBuilder();
            int stackIndex = stackOffset;
            for (int i = methodCount; i > 0; i--) {
                stackIndex = i + stackOffset;
                if (stackIndex >= trace.length) {
                    continue;
                }
                //                levjel += "    ";
            }
            builder.append(level).append(_getSimpleClassName(trace[stackIndex].getClassName())).append(".").append(trace[stackIndex].getMethodName()).append(" ").append("(").append(trace[stackIndex].getFileName()).append(":").append(trace[stackIndex].getLineNumber()).append(")");
            return builder.toString();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Determines the starting index of the stack trace, after method calls made by this class.
     *
     * @param trace
     *         the stack trace
     *
     * @return the stack offset
     */
    private static int _getStackOffset(StackTraceElement[] trace) {
        for (int i = MIN_STACK_OFFSET; i < trace.length; i++) {
            StackTraceElement e = trace[i];
            String name = e.getClassName();
            if (!name.equals(DBLog.class.getName())) {
                return --i;
            }
        }
        return -1;
    }

    private static String _getSimpleClassName(String name) {
        int lastIndex = name.lastIndexOf(".");
        return name.substring(lastIndex + 1);
    }

}
