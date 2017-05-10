package com.dustinwoo.shopifydemo.sales;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dustinwoo.shopifydemo.R;
import com.dustinwoo.shopifydemo.activities.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dustin on 2017-05-08.
 */

public class ViewSalesActivity extends BaseActivity implements ViewSalesContract.View {

    @BindView(R.id.shopify_demo_usd_revenue_textview)
    TextView mUsdRevenueTextView;
    @BindView(R.id.shopify_demo_cad_revenue_textview)
    TextView mCadRevenueTextView;
    @BindView(R.id.shopify_demo_keyboardsold_textview)
    TextView mKeyboardsSoldTextView;
    @BindView(R.id.progressbar_container)
    View mProgressBarContainer;
    @BindView(R.id.shopify_demo_view_container)
    View mContentViewContainer;
    @BindView(R.id.shopify_demo_load_more_button)
    Button mLoadMoreButton;

    @Inject
    ViewSalesContract.Presenter mPresenter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_view_sales;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresenterComponent().inject(this);
        ButterKnife.bind(this);
        mPresenter.attachView(this);

        mPresenter.fetchOrderInfo();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    //============================================================
    // Interface Methods
    //============================================================


    @Override
    public void showOrderDetails(double totalUsdRevenue, double totalRevenue, int numKeyboardsSold) {
        mUsdRevenueTextView.setText(getString(R.string.revenue_in_currency, getString(R.string.curr_code_usd), totalUsdRevenue));
        mCadRevenueTextView.setText(getString(R.string.revenue_in_currency, getString(R.string.curr_code_cad), totalRevenue));
        mKeyboardsSoldTextView.setText(getResources().getString(R.string.keyboards_sold, numKeyboardsSold));

        mProgressBarContainer.setVisibility(View.GONE);
        mContentViewContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorScreen(boolean isEmptyResponse) {
        mProgressBarContainer.setVisibility(View.GONE);
        Toast.makeText(this,
                isEmptyResponse ? R.string.fetch_error_empty_message : R.string.fetch_error_generic_message,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingScreen(boolean showOnlySpinner) {
        mProgressBarContainer.setVisibility(View.VISIBLE);
        mContentViewContainer.setVisibility(showOnlySpinner ? View.GONE : View.VISIBLE);
    }

    @Override
    public void enableLoadButton(boolean isEnabled) {
        mLoadMoreButton.setEnabled(isEnabled);
    }

    //============================================================
    // Click Events
    //============================================================

    @OnClick(R.id.shopify_demo_load_more_button)
    protected void onLoadMoreButtonClicked() {
        mPresenter.fetchOrderInfo();
    }
}
