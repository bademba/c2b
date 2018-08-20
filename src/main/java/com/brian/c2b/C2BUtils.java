/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brian.c2b;

import java.sql.Date;
//import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 *
 * @author BADEMBA
 */
//@JsonPropertyOrder({})
public class C2BUtils {

    public String TransactionType;
    public String TransID;
    public String TransTime;
    public double TransAmount;
    public String BusinessShortCode;
    public String BillRefNumber;
    public String InvoiceNumber;
    public double OrgAccountBalance;
    public String ThirdPartyTransID;
    public String Msisdn;
    public String FirstName;
    public String MiddleName;
    public String LastName;

    public C2BUtils() {

    }

    public C2BUtils(String TransactionType, String TransID, String TransTime, double TransAmount, String BusinessShortCode, String BillRefNumber, String InvoiceNumber, double OrgAccountBalance, String ThirdPartyTransID, String Msisdn, String FirstName, String MiddleName, String LastName) {
        this.TransactionType = TransactionType;
        this.TransID = TransID;
        this.TransTime = TransTime;
        this.TransAmount = TransAmount;
        this.BusinessShortCode = BusinessShortCode;
        this.BillRefNumber = BillRefNumber;
        this.OrgAccountBalance = OrgAccountBalance;
        this.ThirdPartyTransID = ThirdPartyTransID;
        this.Msisdn = Msisdn;
        this.FirstName = FirstName;
        this.MiddleName = MiddleName;
        this.LastName = LastName;
        this.TransTime = TransTime;
    }

    public String getTransactionType() {
        return TransactionType;
    }

    public void setTransactionType(String TransactionType) {
        this.TransactionType = TransactionType;
    }

    public String getTransID() {
        return TransID;
    }

    public void setTransID(String TransID) {
        this.TransID = TransID;
    }

    public String getTransTime() {
        return TransTime;
    }

    public void setTransTime(String TransTime) {
        this.TransTime = TransTime;
    }

    public double getTransAmount() {
        return TransAmount;
    }

    public void setTransAmount(double TransAmount) {
        this.TransAmount = TransAmount;
    }

    public String getBusinessShortCode() {
        return BusinessShortCode;
    }

    public void setBusinessShortCode(String BusinessShortCode) {
        this.BusinessShortCode = BusinessShortCode;
    }

    public String getBillRefNumber() {
        return BillRefNumber;
    }

    public void setBillRefNumber(String BillRefNumber) {
        this.BillRefNumber = BillRefNumber;
    }

    public String getInvoiceNumber() {
        return InvoiceNumber;
    }

    public void setInvoiceNumber(String InvoiceNumber) {
        this.InvoiceNumber = InvoiceNumber;
    }

    public double getOrgAccountBalance() {
        return OrgAccountBalance;
    }

    public void setOrgAccountBalance(double OrgAccountBalance) {
        this.OrgAccountBalance = OrgAccountBalance;
    }

    public String getThirdPartyTransID() {
        return ThirdPartyTransID;
    }

    public void setThirdPartyTransID(String ThirdPartyTransID) {
        this.ThirdPartyTransID = ThirdPartyTransID;
    }

    public String getMsisdn() {
        return Msisdn;
    }

    public void setMsisdn(String Msisdn) {
        this.Msisdn = Msisdn;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String MiddleName) {
        this.MiddleName = MiddleName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

}
