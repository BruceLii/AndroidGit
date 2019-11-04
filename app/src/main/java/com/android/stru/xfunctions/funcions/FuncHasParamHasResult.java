package com.android.stru.xfunctions.funcions;

import com.android.stru.xfunctions.base.Function;

/**
 * Note: 有参数有方法  方法为单个入参，多入参则封装后作为入参
* Created by liyonglin  at 2019/11/4 16:13
*/
public abstract class FuncHasParamHasResult<T, P> extends Function {
    public FuncHasParamHasResult(String name) {
        super(name);
    }

    public abstract T function(P p);
}
