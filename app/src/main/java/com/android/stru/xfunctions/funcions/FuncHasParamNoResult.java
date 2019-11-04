package com.android.stru.xfunctions.funcions;

import com.android.stru.xfunctions.base.Function;

/**
 * Note: 有参数，无返回值方法
* Created by liyonglin  at 2019/11/4 16:16
*/
public abstract class FuncHasParamNoResult<P> extends Function {
    public FuncHasParamNoResult(String name) {
        super(name);
    }

    public abstract void function(P p);
}
