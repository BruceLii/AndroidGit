package com.android.stru.xfunctions;

import android.text.TextUtils;
import android.util.Log;

import com.android.stru.xfunctions.base.Function;
import com.android.stru.xfunctions.funcions.FuncHasParamHasResult;
import com.android.stru.xfunctions.funcions.FuncHasParamNoResult;
import com.android.stru.xfunctions.funcions.FuncNoParamHasResult;
import com.android.stru.xfunctions.funcions.FuncNoParamNoResult;

import java.util.HashMap;
import java.util.Map;

/**
 * Note: 方法管理中心
 * Created by liyonglin  at 2019/11/4 16:16
 */

public class FunctionManager {
    private static FunctionManager instance;

    private Map<String, FuncNoParamNoResult> funcNoParamNoResultMap;
    private Map<String, FuncNoParamHasResult> funcNoParamHasResultMap;

    private Map<String, FuncHasParamNoResult> funcHasParamNoResultMap;
    private Map<String, FuncHasParamHasResult> funcHasParamHasResultMap;

    private FunctionManager() {
        funcNoParamNoResultMap = new HashMap<>();
        funcNoParamHasResultMap = new HashMap<>();
        funcHasParamNoResultMap = new HashMap<>();
        funcHasParamHasResultMap = new HashMap<>();
    }

    public static FunctionManager getInstance() {
        if (instance == null) {
            instance = new FunctionManager();
        }
        return instance;
    }


    public FunctionManager addFunction(Function function) {
        if (function instanceof FuncNoParamNoResult) {
            funcNoParamNoResultMap.put(function.getName(), (FuncNoParamNoResult) function);
        } else if (function instanceof FuncNoParamHasResult) {
            funcNoParamHasResultMap.put(function.getName(), (FuncNoParamHasResult) function);
        } else if (function instanceof FuncHasParamNoResult) {
            funcHasParamNoResultMap.put(function.getName(), (FuncHasParamNoResult) function);
        } else if (function instanceof FuncHasParamHasResult) {
            funcHasParamHasResultMap.put(function.getName(), (FuncHasParamHasResult) function);
        }
        return getInstance();
    }


    /**
     * no param  no result
     *
     * @param functionName
     */
    public void invokeFuction(String functionName) {
        if (TextUtils.isEmpty(functionName)) return;

        if (funcNoParamNoResultMap != null) {
            FuncNoParamNoResult f = funcNoParamNoResultMap.get(functionName);

            if (f != null) {
                f.function();
            } else {
                Log.e("=====>", "can`t find the method");
            }
        }
    }


    /**
     * no  param has result
     */

    public <T> T invokeFunction(String functionName, Class<T> tClass) {
        if (TextUtils.isEmpty(functionName)) return null;

        if (funcNoParamHasResultMap != null) {
            FuncNoParamHasResult f = funcNoParamHasResultMap.get(functionName);
            if (f != null) {
                return tClass.cast(f.function());
            } else {
                Log.e("=====>", "can`t find the method");
            }
        }
        return null;
    }


    /**
     * has  param no result
     */

    public <P> void invokeFunction(String functionName, P tClass) {
        if (TextUtils.isEmpty(functionName)) return;

        if (funcHasParamNoResultMap != null) {
            FuncHasParamNoResult f = funcHasParamNoResultMap.get(functionName);
            if (f != null) {
                f.function(tClass);
            } else {
                Log.e("=====>", "can`t find the method");
            }
        }
    }

    /**
     * has  param has result
     */

    public <T, P> T invokeFunction(String functionName, Class<T> t, Class<P> p) {
        if (TextUtils.isEmpty(functionName)) return null;

        if (funcHasParamHasResultMap != null) {
            FuncHasParamHasResult f = funcHasParamHasResultMap.get(functionName);
            if (f != null) {
                return t.cast(f.function(p));
            } else {
                Log.e("=====>", "can`t find the method");
            }
        }
        return null;
    }

}
