package com.songcw.basecore.http;

/**
 * Create by Sprout at 2017/8/15
 */
public interface IInterceptor {
    void runOnStart();

    void runOnComplete();
}
