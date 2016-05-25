package io.github.lingnanlu.dianfei;

/**
 * Created by Administrator on 2016/5/24.
 */
public interface CallBack<T> {
    void onPre();
    void onResult(T result);
    void onError(Exception e);
}
