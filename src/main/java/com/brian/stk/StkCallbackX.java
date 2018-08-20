/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brian.stk;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author BADEMBA
 */
public class StkCallbackX {

    public String MerchantRequestID;
    public String CheckoutRequestID;
    public int ResultCode;
    public String ResultDesc;

    public CallbackMetadataX CallbackMetadata;

    public String getResultDesc() {
        return ResultDesc;
    }

    @JsonProperty("ResultDesc")
    public void setResultDesc(String ResultDesc) {
        this.ResultDesc = ResultDesc;
    }

    public int getResultCode() {
        return ResultCode;
    }

    @JsonProperty("ResultCode")
    public void setResultCode(int ResultCode) {
        this.ResultCode = ResultCode;
    }

    public String getCheckoutRequestID() {
        return CheckoutRequestID;
    }

    @JsonProperty("CheckoutRequestID")
    public void setCheckoutRequestID(String CheckoutRequestID) {
        this.CheckoutRequestID = CheckoutRequestID;
    }

    public String getMerchantRequestID() {
        return MerchantRequestID;
    }

    @JsonProperty("MerchantRequestID")
    public void setMerchantRequestID(String MerchantRequestID) {
        this.MerchantRequestID = MerchantRequestID;
    }

    public CallbackMetadataX getCallbackMetadata() {
        return CallbackMetadata;
    }

    @JsonProperty("CallbackMetadata")
    public void setCallbackMetadata(CallbackMetadataX CallbackMetadata) {
        this.CallbackMetadata = CallbackMetadata;
    }

    @Override
    public String toString() {
        return "ClassPojo [ResultDesc = " + ResultDesc + ", ResultCode = " + ResultCode + ", CheckoutRequestID = " + CheckoutRequestID + ", MerchantRequestID = " + MerchantRequestID + ", CallbackMetadata = " + CallbackMetadata + "]";
    }
}
