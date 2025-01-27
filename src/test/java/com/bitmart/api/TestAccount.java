package com.bitmart.api;

import com.bitmart.api.common.CloudException;
import com.bitmart.api.request.account.prv.*;
import com.bitmart.api.request.account.pub.AccountCurrenciesRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public final class TestAccount extends TestData {


    // -------------- pub

    @Test
    @DisplayName("Test. GET /account/v1/currencies")
    void currencies() throws CloudException {
        System.out.println(
                call.callCloud(new AccountCurrenciesRequest()).getResponseContent()
        );
    }


    // -------------- prv

    @Test
    @DisplayName("Test. GET /account/v1/wallet")
    void wallet() throws CloudException {
        System.out.println(
                call.callCloud(new AccountWalletRequest()
                        .setCurrency("BTC")
                )
        );
        System.out.println(
                call.callCloud(new AccountWalletRequest()
                )
        );
    }


    @Test
    @DisplayName("Test. GET /account/v1/deposit/address")
    void depositAddress() throws CloudException {
        System.out.println(
                call.callCloud(new AccountDepositAddressRequest()
                        .setCurrency("USDT")
                )
        );

        System.out.println(
                call.callCloud(new AccountDepositAddressRequest()
                        .setCurrency("USDT-TRC20")
                )
        );

        System.out.println(
                call.callCloud(new AccountDepositAddressRequest()
                        .setCurrency("USDT-ERC20")
                )
        );
    }

    @Test
    @DisplayName("Test. GET /account/v1/withdraw/charge")
    void withdrawCharge() throws CloudException {
        System.out.println(
                call.callCloud(new AccountWithdrawChargeRequest("BMX"))
        );
    }


    @Test
    @DisplayName("Test. GET /account/v1/withdraw/apply")
    void withdrawApply() throws CloudException {
        System.out.println(
                call.callCloud(new AccountWithdrawApplyRequest()
                        .setCurrency("USDT-ERC20")
                        .setAmount("50.000")
                        .setDestination("2:BitMart")
                        .setAddress("0xe57b69a8776b37860407965B73cdFFBDFe668Bb5")
                        .setAddress_memo("")
                )
        );
    }

    @Test
    @DisplayName("Test. GET /account/v2/deposit-withdraw/history")
    void depositWithdrawHistoryV2() throws CloudException {
        System.out.println(
                call.callCloud(new AccountDepositWithdrawHistoryV2Request()
                        // .setCurrency("BMX")
                        .setOperationType("withdraw")
                        .setN(10)
                )
        );
    }

    @Test
    @DisplayName("Test. GET /account/v1/deposit-withdraw/detail")
    void depositWithdrawDetail() throws CloudException {
        System.out.println(
                call.callCloud(new AccountDepositWithdrawDetailRequest()
                        .setId(1679947L)
                )
        );
    }

}
