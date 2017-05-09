package com.dustinwoo.shopifydemo.sales.sales;

import com.dustinwoo.shopifydemo.exceptions.EmptyResponseException;
import com.dustinwoo.shopifydemo.network.OrderService;
import com.dustinwoo.shopifydemo.sales.OrdersManager;
import com.dustinwoo.shopifydemo.sales.models.Order;
import com.dustinwoo.shopifydemo.sales.models.OrderResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by dustin on 2017-05-09.
 */

@RunWith(MockitoJUnitRunner.class)
public class OrdersManagerTest {

    private static final int DEFAULT_PAGE_NUM = 1;

    @Mock private OrderService mockOrderService;
    private Scheduler mTrampolineScheduler = Schedulers.trampoline();

    private Observable<OrderResponse> mOrderRespObservable;
    @Mock private OrderResponse mockOrderResponse;
    @Mock private Order mockOrder1;
    @Mock private Order mockOrder2;
    @Mock private Order mockOrder3;


    private OrdersManager mSubject;

    @Before
    public void setup() {
        mSubject = new OrdersManager(mockOrderService, mTrampolineScheduler);
    }

    @Test
    public void initialization_firstCallToFetch_calledWithDefaultPageNum() {
        when(mockOrderService.orderList(anyInt(), anyString())).thenReturn(Observable.just(mock(OrderResponse.class)));
        ArgumentCaptor<Integer> intArgumentCaptor = ArgumentCaptor.forClass(Integer.class);

        mSubject.fetchOrders();
        verify(mockOrderService).orderList(intArgumentCaptor.capture(), any(String.class));
        assertThat(intArgumentCaptor.getValue()).isEqualTo(DEFAULT_PAGE_NUM);
    }

    @Test
    public void fetchOrders_hasMultipleOrders_emitsResponseOrdersIndividiaully() {
        setupResponseWithMultipleOrders();

        TestObserver<Order> orderTestObserver = mSubject.fetchOrders().test();
        orderTestObserver.assertNoErrors();
        orderTestObserver.assertComplete();
        orderTestObserver.assertValueCount(3);
        orderTestObserver.assertValues(mockOrder1, mockOrder2, mockOrder3);
    }

    @Test
    public void fetchOrders_hasNoOrders_throwsEmptyResponseException() {
        setupResponseWithNoOrders();

        TestObserver<Order> orderTestObserver = mSubject.fetchOrders().test();
        orderTestObserver.assertError(EmptyResponseException.class);
        orderTestObserver.assertNotComplete();
    }

    @Test
    public void fetchOrders_initialCallCompleted_secondCall_makesRequestForSecondPage() {
        setupResponseWithMultipleOrders();
        ArgumentCaptor<Integer> intArgumentCaptor = ArgumentCaptor.forClass(Integer.class);

        TestObserver<Order> orderTestObserver = mSubject.fetchOrders().test();
        orderTestObserver.assertComplete();

        mSubject.fetchOrders();

        verify(mockOrderService, times(2)).orderList(intArgumentCaptor.capture(), any(String.class));
        assertThat(intArgumentCaptor.getAllValues().get(0)).isEqualTo(DEFAULT_PAGE_NUM);
        assertThat(intArgumentCaptor.getAllValues().get(1)).isEqualTo(DEFAULT_PAGE_NUM + 1);

    }

    //============================================================
    // Private Methods / Helpers
    //============================================================

    private void setupResponseWithMultipleOrders() {
        mockOrder1 = mock(Order.class);
        mockOrder2 = mock(Order.class);
        mockOrder3 = mock(Order.class);

        when(mockOrderResponse.getOrderList()).thenReturn(Arrays.asList(mockOrder1, mockOrder2, mockOrder3));
        mOrderRespObservable = Observable.just(mockOrderResponse);
        when(mockOrderService.orderList(anyInt(), anyString())).thenReturn(mOrderRespObservable);
    }

    private void setupResponseWithNoOrders() {
        when(mockOrderResponse.getOrderList()).thenReturn(Collections.<Order>emptyList());
        mOrderRespObservable = Observable.just(mockOrderResponse);
        when(mockOrderService.orderList(anyInt(), anyString())).thenReturn(mOrderRespObservable);
    }
}