package com.offcasoftware.shop2.base;

public abstract class BasePresenter<V extends BaseView> {

    private V mView;

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
