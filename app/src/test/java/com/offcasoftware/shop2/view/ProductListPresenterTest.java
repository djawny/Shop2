package com.offcasoftware.shop2.view;

import com.offcasoftware.shop2.model.Product;
import com.offcasoftware.shop2.repository.ProductRepositoryInterface;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class ProductListPresenterTest {

    //    private ProductRepositoryInterface mRepositoryInterface = mock(ProductRepositoryInterface.class);
    @Mock
    ProductRepositoryInterface mRepositoryInterface;

    @Mock
    ProductListView mProductListView;

    private ProductListPresenter mPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mPresenter = new ProductListPresenter(mRepositoryInterface);
        mPresenter.setView(mProductListView);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPresenterWithNullViewThrowException() throws Exception {
        mPresenter.setView(null);
    }

    @Test
    public void testPresenterShowNoDataWhenEmptyList() throws Exception {
        when(mRepositoryInterface.getProducts()).thenReturn(Collections.<Product>emptyList());

        mPresenter.loadProducts();

        verify(mProductListView).showNoDataInfo();
        verify(mProductListView, times(1)).showErrorInfo();
        verify(mProductListView, never()).showProducts(ArgumentMatchers.<Product>anyList());
    }

    @Test
    public void testPresenterShowsDataWhenEmptyList() throws Exception {
        when(mRepositoryInterface.getProducts()).thenReturn(Collections.<Product>emptyList());

        mPresenter.loadProducts();

        verify(mProductListView).showNoDataInfo();
        verify(mProductListView, never()).showErrorInfo();
        verify(mProductListView, never()).showProducts(ArgumentMatchers.<Product>anyList());
    }

    @Test
    public void testPresenterShowsDataWhenEmptyList_2() throws Exception {
        List products = mock(List.class);
        when(products.isEmpty()).thenReturn(false);

        when(mRepositoryInterface.getProducts()).thenReturn(products);

        mPresenter.loadProducts();

        verify(mProductListView).showProducts(products);
        verify(mProductListView, never()).showErrorInfo();
        verify(mProductListView, never()).showProducts(ArgumentMatchers.<Product>anyList());
    }

    @Test
    public void testPresenterShowsErrorInfoWhen() throws Exception {
        when(mRepositoryInterface.getProducts()).thenThrow(Exception.class);

        mPresenter.loadProducts();

        verify(mProductListView).showErrorInfo();
        verify(mProductListView, never()).showErrorInfo();
        verify(mProductListView, never()).showProducts(ArgumentMatchers.<Product>anyList());
    }


}