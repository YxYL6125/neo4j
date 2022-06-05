package com.liyao.neo4j.common;

/**
 * @program: neo4j
 * @description: 结果返回类
 * @author: YxYL
 * @create: 2022-06-05 10:53
 **/

public class ResponseResult<T> {
    //返回信息(错误返回)
    private String msg;
    //返回结果
    private T result;

    @Override
    public String toString() {
        return "ResponseResult{" +
                "msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
