/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brian.stkcallback;

/**
 *
 * @author BADEMBA
 */
public class StkCallback {
 public String MerchantRequestID;
 public String CheckoutRequestID;
 public int ResultCode;
 public String ResultDesc;
 CallbackMetadata CallbackMetadataObject;

 public StkCallback(){}
 public StkCallback(String MerchantRequestID,String CheckoutRequestID,int ResultCode,String ResultDesc,CallbackMetadata CallbackMetadataObject){
 this.MerchantRequestID=MerchantRequestID;
 this.CheckoutRequestID=CheckoutRequestID;
 this.ResultCode=ResultCode;
 this.ResultDesc=ResultDesc;
 this.CallbackMetadataObject=CallbackMetadataObject;
 }

 // Getter Methods 

 public String getMerchantRequestID() {
  return MerchantRequestID;
 }

 public String getCheckoutRequestID() {
  return CheckoutRequestID;
 }

 public int getResultCode() {
  return ResultCode;
 }

 public String getResultDesc() {
  return ResultDesc;
 }

 public CallbackMetadata getCallbackMetadata() {
  return CallbackMetadataObject;
 }

 // Setter Methods 

 public void setMerchantRequestID(String MerchantRequestID) {
  this.MerchantRequestID = MerchantRequestID;
 }

 public void setCheckoutRequestID(String CheckoutRequestID) {
  this.CheckoutRequestID = CheckoutRequestID;
 }

 public void setResultCode(int ResultCode) {
  this.ResultCode = ResultCode;
 }

 public void setResultDesc(String ResultDesc) {
  this.ResultDesc = ResultDesc;
 }

 public void setCallbackMetadata(CallbackMetadata CallbackMetadataObject) {
  this.CallbackMetadataObject = CallbackMetadataObject;
 }  
}
