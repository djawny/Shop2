package com.offcasoftware.shop2.view;

import com.offcasoftware.shop2.model.Product;
import com.offcasoftware.shop2.repository.ProductRepositoryInterface;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.stubbing.answers.ThrowsException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
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
        mPresenter = new AddProductPresenter(mProductRepositoryInterface);
        mPresenter.setView(mAddProductView);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPresenterWithNullViewThrowException() throws Exception {
        mPresenter.setView(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPresenterWithNullRepoThrowException() throws Exception {
        new AddProductPresenter(null);
    }

    @Test
    public void testAddProductRepositoryExceptionShowsError() throws Exception {
        doThrow(new Exception()).when(mProductRepositoryInterface).addProduct(any(Product.class));

        mPresenter.addProduct("product", "1");

        verify(mAddProductView).showError();
        verify(mAddProductView, never()).closeScreen();
    }

    @Test
    public void testAddProductCloseScreen(){
        mPresenter.addProduct("product", "1");

        verify(mAddProductView, never()).showError();
        verify(mAddProductView).closeScreen();
    }
}