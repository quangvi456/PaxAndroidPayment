package com.rnpaxdemo.payment.services;

import android.os.Message;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.pax.poslink.BatchResponse;
import com.pax.poslink.PaymentResponse;
import com.rnpaxdemo.payment.constants.Constant;
import com.rnpaxdemo.payment.models.AdjustPayment;
import com.rnpaxdemo.payment.models.RefundPayment;
import com.rnpaxdemo.payment.models.SalePayment;
import com.rnpaxdemo.payment.models.VoidPayment;
import com.rnpaxdemo.payment.util.POSLinkThreadPool;
import com.pax.poslink.BatchRequest;
import com.pax.poslink.PaymentRequest;

public class PaymentServices {
    private PaymentRequest mPaymentRequest;
    private Message message = new Message();
    private int lastReportedStatus = -1;
    private BatchRequest mBatchRequest;

    private static PaymentServices instance;

    public static PaymentServices getInstance() {
        if (instance == null) {
            instance = new PaymentServices();
        }

        return instance;
    }

    private PaymentServices() {
        this.mPaymentRequest = new PaymentRequest();
        this.mBatchRequest = new BatchRequest();
    }

    public void cancel(Promise promise) {
        try {
            POSLinkThreadPool.getInstance().runUIThread(new Runnable() {
                @Override
                public void run() {
                    CustomPOSLinkServices.getInstance().getPoslink().CancelTrans();
                    promise.resolve("Cancel successful.");
                }
            });
        } catch (Exception ex) {
            promise.reject("Cancel transaction failed.");
        }
    }

    private WritableMap convertPaymentResponseToWritableMap(PaymentResponse response) {
        WritableMap map = Arguments.createMap();
        if (response == null) {
            return  map;
        }

        map.putString("AuthCode", response.AuthCode);
        map.putString("ApprovedAmount", response.ApprovedAmount);
        map.putString("AvsResponse", response.AvsResponse);
        map.putString("BogusAccountNum", response.BogusAccountNum);
        map.putString("CardType", response.CardType);
        map.putString("CvResponse", response.CvResponse);
        map.putString("HostCode", response.HostCode);
        map.putString("HostResponse", response.HostResponse);
        map.putString("Message", response.Message);
        map.putString("RefNum", response.RefNum);
        map.putString("RawResponse", response.RawResponse);
        map.putString("RemainingBalance", response.RemainingBalance);
        map.putString("ExtraBalance", response.ExtraBalance);
        map.putString("RequestedAmount", response.RequestedAmount);
        map.putString("ResultCode", response.ResultCode);
        map.putString("ResultTxt", response.ResultTxt);
        map.putString("Timestamp", response.Timestamp);
        map.putString("SigFileName", response.SigFileName);
        map.putString("SignData", response.SignData);
        map.putString("TransactionIntegrityClass", response.TransactionIntegrityClass);
        map.putString("ResultTxt", response.ResultTxt);
        map.putString("ExtData", response.ExtData);
        map.putString("GiftCardType", response.GiftCardType);
        map.putString("TransactionRemainingAmount", response.TransactionRemainingAmount);
        map.putString("DebitAccountType", response.DebitAccountType);
        map.putString("HostDetailedMessage", response.HostDetailedMessage);
        map.putString("GatewayTransactionID", response.GatewayTransactionID);
        map.putString("RetrievalReferenceNumber", response.RetrievalReferenceNumber);
        map.putString("MaskedPAN", response.MaskedPAN);
        map.putString("Track1Data", response.Track1Data);
        map.putString("Track2Data", response.Track2Data);
        map.putString("Track3Data", response.Track3Data);
        map.putString("EDCType", response.EDCType);
        map.putString("PaymentService2000", response.PaymentService2000);
        map.putString("AuthorizationResponse", response.AuthorizationResponse);
        map.putString("IssuerResponseCode", response.IssuerResponseCode);
        map.putString("ECRTransID", response.ECRTransID);

        if (response.CardInfo != null) {
            WritableNativeMap cardInfo = new WritableNativeMap();
            cardInfo.putString("CardBin", response.CardInfo.CardBin);
            cardInfo.putString("NewCardBin", response.CardInfo.NewCardBin);
            cardInfo.putString("ProgramType", response.CardInfo.ProgramType);
            map.putMap("CardInfo", cardInfo);
        }

        return map;
    }

    public void sale(SalePayment input, Promise promise) {
        if (CustomPOSLinkServices.getInstance().getPoslink() == null || input == null) {
            promise.reject("Sale failed. Input is invalid.");
            return;
        }

        mPaymentRequest = new PaymentRequest();
        mPaymentRequest.Amount = input.getAmount();
        mPaymentRequest.TipAmt = input.getTip();
        mPaymentRequest.TransType = mPaymentRequest.ParseTransType(input.getTransType());
        mPaymentRequest.TenderType = mPaymentRequest.ParseTenderType(input.getTenderType());
        mPaymentRequest.ECRRefNum = input.getEcrRefNum();
        mPaymentRequest.OrigECRRefNum = "true";

        CustomPOSLinkServices.getInstance().getPoslink().PaymentRequest = mPaymentRequest;

        POSLinkThreadPool.getInstance().runInSingleThread(() -> {
            try {
                Thread.sleep(500);
                // ProcessTrans is Blocking call, will return when the transaction is complete.
                CustomPOSLinkServices.getInstance().getPoslink().ProcessTrans();
                PaymentResponse response = CustomPOSLinkServices.getInstance().getPoslink().PaymentResponse;
                promise.resolve(convertPaymentResponseToWritableMap(response));
                CustomPOSLinkServices.getInstance().getPoslink().PaymentRequest = null;
                lastReportedStatus = -1;
                return;
            } catch (InterruptedException e) {
                promise.reject("Sale failed. Thread interrupted.");
                Thread.currentThread().interrupt();
                return;
            }
        });
    }

    public void voidPayment(VoidPayment input, Promise promise) {
        if (CustomPOSLinkServices.getInstance().getPoslink() == null || input == null) {
            promise.reject("Void failed. Input is invalid.");
            return;
        }

        mPaymentRequest = new PaymentRequest();
        mPaymentRequest.TransType = mPaymentRequest.ParseTransType(input.getTransType());
        mPaymentRequest.TenderType = mPaymentRequest.ParseTenderType(input.getTenderType());
        mPaymentRequest.ECRRefNum = input.getEcrRefNum();
        mPaymentRequest.OrigRefNum = input.getOrgRefNum();

        CustomPOSLinkServices.getInstance().getPoslink().PaymentRequest = mPaymentRequest;

        POSLinkThreadPool.getInstance().runInSingleThread(() -> {
            try {
                Thread.sleep(500);
                // ProcessTrans is Blocking call, will return when the transaction is complete.
                CustomPOSLinkServices.getInstance().getPoslink().ProcessTrans();
                CustomPOSLinkServices.getInstance().getPoslink().setReportStatusListener(null);
                lastReportedStatus = -1;
                PaymentResponse response = CustomPOSLinkServices.getInstance().getPoslink().PaymentResponse;
                promise.resolve(convertPaymentResponseToWritableMap(response));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                promise.reject("Void failed. Thread interrupted.");
                return;
            }
        });
    }

    private WritableMap convertBatchResponseToWritableMap(BatchResponse response) {
        WritableMap map = Arguments.createMap();
        if (response == null) {
            return  map;
        }

        map.putString("ResultCode", response.ResultCode);
        map.putString("ResultTxt", response.ResultTxt);
        map.putString("CreditCount", response.CreditCount);
        map.putString("CreditAmount", response.CreditAmount);
        map.putString("DebitCount", response.DebitCount);
        map.putString("DebitAmount", response.DebitAmount);
        map.putString("EBTCount", response.EBTCount);
        map.putString("EBTAmount", response.EBTAmount);
        map.putString("GiftCount", response.GiftCount);
        map.putString("GiftAmount", response.GiftAmount);
        map.putString("LoyaltyCount", response.LoyaltyCount);
        map.putString("LoyaltyAmount", response.LoyaltyAmount);
        map.putString("CashCount", response.CashCount);
        map.putString("CashAmount", response.CashAmount);
        map.putString("CHECKCount", response.CHECKCount);
        map.putString("CHECKAmount", response.CHECKAmount);
        map.putString("Timestamp", response.Timestamp);
        map.putString("TID", response.TID);
        map.putString("MID", response.MID);
        map.putString("HostTraceNum", response.HostTraceNum);
        map.putString("BatchNum", response.BatchNum);
        map.putString("AuthCode", response.AuthCode);
        map.putString("HostCode", response.HostCode);
        map.putString("HostResponse", response.HostResponse);
        map.putString("Message", response.Message);
        map.putString("ExtData", response.ExtData);
        map.putString("SAFTotalCount", response.SAFTotalCount);
        map.putString("SAFTotalAmount", response.SAFTotalAmount);
        map.putString("SAFUploadedCount", response.SAFUploadedCount);
        map.putString("SAFUploadedAmount", response.SAFUploadedAmount);
        map.putString("SAFFailedCount", response.SAFFailedCount);
        map.putString("SAFFailedTotal", response.SAFFailedTotal);
        map.putString("SAFDeletedCount", response.SAFDeletedCount);
        map.putString("BatchFailedRefNum", response.BatchFailedRefNum);
        map.putString("BatchFailedCount", response.BatchFailedCount);
        map.putString("SAFTotalAmount", response.SAFTotalAmount);

        if (response.TORResponseInfo != null) {
            WritableNativeMap torResponseInfo = new WritableNativeMap();
            torResponseInfo.putString("RecordType", response.TORResponseInfo.RecordType);
            torResponseInfo.putString("ReversalTimeStamp", response.TORResponseInfo.ReversalTimeStamp);
            torResponseInfo.putString("HostResponseCode", response.TORResponseInfo.HostResponseCode);
            torResponseInfo.putString("HostResponseMessage", response.TORResponseInfo.HostResponseMessage);
            torResponseInfo.putString("HostReferenceNumber", response.TORResponseInfo.HostReferenceNumber);
            torResponseInfo.putString("GatewayTransactionID", response.TORResponseInfo.GatewayTransactionID);
            torResponseInfo.putString("OrigAmount", response.TORResponseInfo.OrigAmount);
            torResponseInfo.putString("MaskedPAN", response.TORResponseInfo.MaskedPAN);
            torResponseInfo.putString("BatchNo", response.TORResponseInfo.BatchNo);
            torResponseInfo.putString("ReversalAuthCode", response.TORResponseInfo.ReversalAuthCode);
            torResponseInfo.putString("OrigTransType", response.TORResponseInfo.OrigTransType);
            torResponseInfo.putString("OrigTransDateTime", response.TORResponseInfo.OrigTransDateTime);
            torResponseInfo.putString("OrigTransAuthCode", response.TORResponseInfo.OrigTransAuthCode);
            map.putMap("TORResponseInfo", torResponseInfo);
        }

        if (response.MultiMerchant != null) {
            WritableNativeMap multiMerchant = new WritableNativeMap();
            multiMerchant.putString("Id", response.MultiMerchant.Id);
            multiMerchant.putString("Name", response.MultiMerchant.Name);
            map.putMap("MultiMerchant", multiMerchant);
        }

        return map;
    }

    public void closeBatch(Promise promise) {
        if (CustomPOSLinkServices.getInstance().getPoslink() == null) {
            return;
        }

        mBatchRequest = new BatchRequest();
        mBatchRequest.TransType = mBatchRequest.ParseTransType("BATCHCLOSE");
        CustomPOSLinkServices.getInstance().getPoslink().BatchRequest = mBatchRequest;
        CustomPOSLinkServices.getInstance().getPoslink().setReportStatusListener(status -> {
            if (status != lastReportedStatus) {
                switch (status) {
                case 0:
                    Message msg0 = new Message();
                    msg0.what = Constant.TRANSACTION_STATUS;
                    msg0.obj = "Ready for CARD INPUT";
                    message = msg0;
                    break;
                case 1:
                    Message msg1 = new Message();
                    msg1.what = Constant.TRANSACTION_STATUS;
                    msg1.obj = "Ready for PIN ENTRY";
                    message = msg1;
                    break;
                case 2:
                    Message msg2 = new Message();
                    msg2.what = Constant.TRANSACTION_STATUS;
                    msg2.obj = "Ready for SIGNATURE";
                    message = msg2;
                    break;
                case 3:
                    Message msg3 = new Message();
                    msg3.what = Constant.TRANSACTION_STATUS;
                    msg3.obj = "Ready for ONLINE PROCESSING";
                    message = msg3;
                    break;
                case 4:
                    Message msg4 = new Message();
                    msg4.what = Constant.TRANSACTION_STATUS;
                    msg4.obj = "Ready for NEW CARD INPUT";
                    message = msg4;
                    break;
                case 5:
                    Message msg5 = new Message();
                    msg5.what = Constant.TRANSACTION_STATUS;
                    msg5.obj = "Ready for Signature retry by pressing CLEAR key";
                    message = msg5;
                    break;
                case 6:
                    Message msg6 = new Message();
                    msg6.what = Constant.TRANSACTION_STATUS;
                    msg6.obj = " Ready for PIN retry by inputting wrong offline PIN for EMV.";
                    message = msg6;
                    break;
                case 9020002:
                    Message msg7 = new Message();
                    msg7.what = Constant.TRANSACTION_STATUS;
                    msg7.obj = "Ready for Entering Cashback.";
                    message = msg7;
                    break;
                default:
                    // do nothing
                    message = new Message();
                    break;
                }

                lastReportedStatus = status;
            }
        });

        POSLinkThreadPool.getInstance().runInSingleThread(() -> {
            try {
                Thread.sleep(500);
                // ProcessTrans is Blocking call, will return when the transaction is
                // complete.
                CustomPOSLinkServices.getInstance().getPoslink().ProcessTrans();
                BatchResponse response = CustomPOSLinkServices.getInstance().getPoslink().BatchResponse;
                promise.resolve(convertBatchResponseToWritableMap(response));
                CustomPOSLinkServices.getInstance().getPoslink().setReportStatusListener(null);
                lastReportedStatus = -1;
                CustomPOSLinkServices.getInstance().getPoslink().BatchRequest = null;

                // mHandler.removeCallbacks(MyRunnable);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                promise.resolve("Error");
            }
        });
    }

    public void adjust(AdjustPayment input, Promise promise) {
        if (CustomPOSLinkServices.getInstance().getPoslink() == null || input == null) {
            promise.reject("Adjust failed. Input is invalid.");
            return;
        }

        mPaymentRequest = new PaymentRequest();
        mPaymentRequest.TransType = mPaymentRequest.ParseTransType(input.getTransType());
        mPaymentRequest.TenderType = mPaymentRequest.ParseTenderType(input.getTenderType());
        mPaymentRequest.ECRRefNum = input.getEcrRefNum();
        mPaymentRequest.OrigRefNum = input.getOrgRefNum();
        mPaymentRequest.Amount = input.getAmount();

        CustomPOSLinkServices.getInstance().getPoslink().PaymentRequest = mPaymentRequest;

        POSLinkThreadPool.getInstance().runInSingleThread(() -> {
            try {
                Thread.sleep(500);
                // ProcessTrans is Blocking call, will return when the transaction is complete.
                CustomPOSLinkServices.getInstance().getPoslink().ProcessTrans();
                CustomPOSLinkServices.getInstance().getPoslink().setReportStatusListener(null);
                lastReportedStatus = -1;
                PaymentResponse response = CustomPOSLinkServices.getInstance().getPoslink().PaymentResponse;
                promise.resolve(convertPaymentResponseToWritableMap(response));
                CustomPOSLinkServices.getInstance().getPoslink().PaymentRequest = null;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                promise.resolve("Adjust failed. Thread interrupted.");
            }
        });
    }

    public void refund(RefundPayment input, Promise promise) {
        if (CustomPOSLinkServices.getInstance().getPoslink() == null || input == null) {
            promise.reject("Refund failed. Input is invalid.");
            return;
        }

        mPaymentRequest = new PaymentRequest();
        mPaymentRequest.TransType = mPaymentRequest.ParseTransType(input.getTransType());
        mPaymentRequest.TenderType = mPaymentRequest.ParseTenderType(input.getTenderType());
        mPaymentRequest.ECRRefNum = input.getEcrRefNum();
        mPaymentRequest.Amount = input.getAmount();

        CustomPOSLinkServices.getInstance().getPoslink().PaymentRequest = mPaymentRequest;

        POSLinkThreadPool.getInstance().runInSingleThread(() -> {
            try {
                Thread.sleep(500);
                // ProcessTrans is Blocking call, will return when the transaction is complete.
                CustomPOSLinkServices.getInstance().getPoslink().ProcessTrans();
                CustomPOSLinkServices.getInstance().getPoslink().setReportStatusListener(null);
                lastReportedStatus = -1;
                PaymentResponse response = CustomPOSLinkServices.getInstance().getPoslink().PaymentResponse;
                promise.resolve(convertPaymentResponseToWritableMap(response));
                CustomPOSLinkServices.getInstance().getPoslink().PaymentRequest = null;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                promise.resolve("Refund failed. Thread interrupted.");
            }
        });
    }
}
