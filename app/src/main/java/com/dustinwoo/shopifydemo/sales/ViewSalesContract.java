package com.dustinwoo.shopifydemo.sales;

import com.dustinwoo.shopifydemo.contracts.BasePresenter;
import com.dustinwoo.shopifydemo.contracts.BaseView;

/**
 * Created by dustin on 2017-05-09.
 */

public class ViewSalesContract {

    public interface View extends BaseView {
        void showRevenueEarned(double totalRevenue);
        void showKeyboardsSold(int numSold);
        void showErrorScreen();
        void showLoadingScreen(boolean showOnlySpinner);
    }

    public interface Presenter extends BasePresenter<View> {
        void fetchOrderInfo();
    }
}
