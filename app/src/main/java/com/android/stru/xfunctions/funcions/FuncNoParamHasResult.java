package com.android.stru.xfunctions.funcions;

import com.android.stru.xfunctions.base.Function;

/**
 * Note: 无参数，有返回值方法
* Created by liyonglin  at 2019/11/4 16:16
*/
public abstract class FuncNoParamHasResult<T> extends Function {
    public FuncNoParamHasResult(String name) {
        super(name);
    }

    public abstract T function();
}
