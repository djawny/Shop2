package com.offcasoftware.shop2.view;

import com.offcasoftware.shop2.model.Product;
import com.offcasoftware.shop2.repository.ProductRepositoryInterface;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddProductPresenterTest {

    @Mock
    ProductRepositoryInterface mProductRepositoryInterface;

    @Mock
    AddProductView mAddProductView;

    private AddProductPresenter mPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mPresenter = new AddProductPresenter(mProductRepositoryInterface,
                Schedulers.trampoline(),
                Schedulers.trampoline());
        mPresenter.setView(mAddProductView);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPresenterWithNullViewThrowException() throws Exception {
        mPresenter.setView(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPresenterWithNullRepoThrowException() throws Exception {
        new AddProductPresenter(null, null, null);
    }

    @Test
    public void testAddProductRepositoryExceptionShowsError() throws Exception {
//        doThrow(new Exception()).when(mProductRepositoryInterface).addProduct(any(Product.class));

        when(mProductRepositoryInterface.addProductStream(any(Product.class)))
                .thenReturn(Observable.<Void>error(new Throwable()));

        mPresenter.addProduct("product", "1");

        verify(mAddProductView).showError();
        verify(mAddProductView, never()).closeScreen();
    }

    @Test
    public void testAddProductCloseScreen() {
        when(mProductRepositoryInterface.addProductStream(any(Product.class)))
                .thenReturn(Observable.<Void>empty());

        mPresenter.addProduct("product", "1");

        verify(mAddProductView, never()).showError();
        verify(mAddProductView).closeScreen();
    }
}