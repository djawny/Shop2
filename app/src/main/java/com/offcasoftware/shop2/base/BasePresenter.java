package com.offcasoftware.shop2.base;

import io.reactivex.Scheduler;

public abstract class BasePresenter<V extends BaseView> {

    private V mView;
    protected Scheduler mSubscribeScheduler;
    protected Scheduler mObserveScheduler;

    public BasePresenter(Scheduler scheduler, Scheduler observe) {
        mSubscribeScheduler = scheduler;
        mObserveScheduler = observe;
    }

    public V getView() {
        return mView;
    }

    public void setView(V view) {
        if (view == null) {
            throw new IllegalArgumentException("No null View in Presenter");
        }
        mView = view;
    }
}
