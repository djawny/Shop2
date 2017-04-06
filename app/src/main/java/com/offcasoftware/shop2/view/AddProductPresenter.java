package com.offcasoftware.shop2.view;

import com.offcasoftware.shop2.base.BasePresenter;
import com.offcasoftware.shop2.model.Product;
import com.offcasoftware.shop2.repository.ProductRepositoryInterface;
import com.offcasoftware.shop2.util.Precondition;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class AddProductPresenter extends BasePresenter<AddProductView> {

    private ProductRepositoryInterface mProductRepositoryInterface;

    private CompositeDisposable mCompositeDisposable;

    public AddProductPresenter(ProductRepositoryInterface productRepositoryInterface) {
        mProductRepositoryInterface = Precondition.checkNotNull(productRepositoryInterface);
        mCompositeDisposable = new CompositeDisposable();
    }

    public void addProduct(String name, String price) {
        int priceInt = Integer.parseInt(price);
        Product product = new Product(name, priceInt);

//        try {
//            mProductRepositoryInterface.addProduct(product);
//            getView().closeScreen();
//        } catch (Exception e) {
//            getView().showError();
//        }

        mCompositeDisposable.add(mProductRepositoryInterface
                .AddProductStream(product)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Void>() {
                    @Override
                    public void onNext(Void value) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError();

                    }

                    @Override
                    public void onComplete() {
                        getView().closeScreen();

                    }
                }));
    }

    public void clearCompositeDisposable(){
        mCompositeDisposable.clear();
    }
}
