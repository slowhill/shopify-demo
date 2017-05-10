package com.dustinwoo.shopifydemo.sales;

import com.dustinwoo.shopifydemo.contracts.BasePresenter;
import com.dustinwoo.shopifydemo.contracts.BaseView;

/**
 * Created by dustin on 2017-05-09.
 */

public class ViewSalesContract {

    public interface View extends BaseView {
        void showOrderDetails(double totalUsdRevenue, double totalRevenue, int numKeyboardsSold);
        void showErrorToast(boolean hasFinishedPagination);
        void showErrorScreen(boolean isFirstFetch);
        void showLoadingScreen(boolean showOnlySpinner);
        void enableLoadButton(boolean isEnabled);
    }

    public interface Presenter extends BasePresenter<View> {
        void fetchOrderInfo();
    }
}
