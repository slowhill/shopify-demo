package com.dustinwoo.shopifydemo.sales;

import com.dustinwoo.shopifydemo.exceptions.EmptyResponseException;
import com.dustinwoo.shopifydemo.sales.models.LineOrder;
import com.dustinwoo.shopifydemo.sales.models.Order;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by dustin on 2017-05-09.
 */
@RunWith(MockitoJUnitRunner.class)
public class ViewSalesPresenterTest {

    private static final int INITIAL_REQ_PAGE_NUM = 1;
    private static final long KEYBOARD_PRODUCT_ID = 2759162243L;

    @Mock private OrdersManager mockOrdersManager;
    private Scheduler trampolineScheduler = Schedulers.trampoline();

    private Observable<Order> mOrderFetchObservable;
    @Mock private ViewSalesContract.View mockView;

    private ViewSalesPresenter mSubject;

    @Before
    public void setup() {
        mSubject = new ViewSalesPresenter(trampolineScheduler, mockOrdersManager);
        mSubject.attachView(mockView);
    }

    @Test
    public void fetchOrdersInfo_requestsNextPage_onSuccessiveCall() {
        setupEmptyObservable();
        ArgumentCaptor<Integer> integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);

        mSubject.fetchOrderInfo();
        mSubject.fetchOrderInfo();

        verify(mockOrdersManager, times(2)).fetchOrders(integerArgumentCaptor.capture());
        assertThat(integerArgumentCaptor.getAllValues().get(0)).isEqualTo(INITIAL_REQ_PAGE_NUM);
        assertThat(integerArgumentCaptor.getAllValues().get(1)).isEqualTo(INITIAL_REQ_PAGE_NUM + 1);
    }

    @Test
    public void fetchOrdersInfo_onRequestStarted_notifiesView_Loading() {
        setupEmptyObservable();

        mSubject.fetchOrderInfo();
        verify(mockView).showLoadingScreen(anyBoolean());
    }

    @Test
    public void fetchOrdersInfo_emptyResponse_notifiesView_ErrorScreen() {
        setupErrorObservable();

        mSubject.fetchOrderInfo();
        verify(mockView, times(1)).showErrorScreen();
    }

    @Test
    public void fetchOrdersInfo_validResponse_updatesRevenue_KeyboardSold_notifiesView() {
        setupValidObservable();

        mSubject.fetchOrderInfo();

        verify(mockView, times(1)).showOrderDetails(100D, 12);
    }

    //============================================================
    // Private Methods / Helpers
    //============================================================

    private void setupEmptyObservable() {
        mOrderFetchObservable = Observable.empty();
        when(mockOrdersManager.fetchOrders(anyInt())).thenReturn(mOrderFetchObservable);
    }

    private void setupErrorObservable() {
        mOrderFetchObservable = Observable.error(new EmptyResponseException());
        when(mockOrdersManager.fetchOrders(anyInt())).thenReturn(mOrderFetchObservable);
    }

    private void setupValidObservable() {
        Order order = mock(Order.class);
        LineOrder keyboardLineOrder = mock(LineOrder.class);
        LineOrder randomLineOrder = mock(LineOrder.class);

        when(keyboardLineOrder.getProductId()).thenReturn(KEYBOARD_PRODUCT_ID);
        when(keyboardLineOrder.getQuantity()).thenReturn(12);
        when(randomLineOrder.getProductId()).thenReturn(1234L);

        when(order.getTotalPrice()).thenReturn(100D);
        when(order.getLineItems()).thenReturn(Arrays.asList(keyboardLineOrder, randomLineOrder));

        mOrderFetchObservable = Observable.just(order);
        when(mockOrdersManager.fetchOrders(anyInt())).thenReturn(mOrderFetchObservable);
    }
}