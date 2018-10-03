/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brian.c2b;

import com.brian.db.DBConnector;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
//import java.sql.Date;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.simple.parser.ParseException;
import org.postgresql.util.PSQLException;
import org.json.JSONObject;

/**
 *
 * @author BADEMBA
 */
@WebService
@Path("/c2b")
public class C2B {

    //localhost:8080/C2B/rest/c2b/test
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/test")
    public String test() {

        String resp = "Hello Brian";
        System.out.println(resp);
        return resp;
    }

    //localhost:7140/C2B/rest/c2b/validation
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/validation")
    public Response Validation(C2BUtils c2BUtils) throws PSQLException, SQLException {

        PreparedStatement ps = null;
        Connection conn = null;
        String insertValidationTxn = "INSERT INTO c2b_validation(ArrivalTime, TransactionType,TransID,TransAmount,BusinessShortCode,BillRefNumber,InvoiceNumber,OrgAccountBalance,ThirdPartyTransID,MSISDN,FirstName,MiddleName,LastName,TransTime,uid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        UUID uuid = UUID.randomUUID();
        try {
            conn = DBConnector.getMysqlDBConnection();
            ps = conn.prepareStatement(insertValidationTxn);
            ps.setString(1, APIUtils.currDate());
            ps.setString(2, c2BUtils.getTransactionType());
            ps.setString(3, c2BUtils.getTransID());
            ps.setDouble(4, c2BUtils.getTransAmount());
            ps.setString(5, c2BUtils.getBusinessShortCode());
            ps.setString(6, c2BUtils.getBillRefNumber());
            ps.setString(7, c2BUtils.getInvoiceNumber());
            ps.setDouble(8, c2BUtils.getOrgAccountBalance());
            ps.setString(9, c2BUtils.getThirdPartyTransID());
            ps.setString(10, c2BUtils.getMsisdn());
            ps.setString(11, c2BUtils.getFirstName());
            ps.setString(12, c2BUtils.getMiddleName());
            ps.setString(13, c2BUtils.getLastName());
            ps.setString(14, c2BUtils.getTransTime());
            ps.setString(15, uuid.toString());
            int add_detail = ps.executeUpdate();

            if (add_detail == 1) {
                System.out.println("New Validation Txn " + c2BUtils.getTransID() + " added");
            } else {
                System.out.println("no txn added");
            }

            conn.close();
        } catch (Exception e) {
            Logger lgr = Logger.getLogger(C2B.class.getName());
            lgr.log(Level.SEVERE, e.getMessage(), e);
            System.out.println("Error_Message-->" + e.getMessage());
        }
        //String re = "OK-2000";
        String re = "{\"ResultDesc\":\"Validation Service request accepted succesfully\",\"ResultCode\":\"0\"}";

        return Response.status(200).entity(re).build();
    }

    //localhost:7140/C2B/rest/c2b/confirmation
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/confirmation")
    public Response Confirmation(C2BUtils c2BUtils) throws PSQLException, SQLException {
        ObjectMapper mapper = new ObjectMapper();
        PreparedStatement ps = null;
        Connection conn = null;
        String insertValidationTxn = "INSERT INTO c2b_confirmation(ArrivalTime, TransactionType,TransID,TransAmount,BusinessShortCode,BillRefNumber,InvoiceNumber,OrgAccountBalance,ThirdPartyTransID,MSISDN,FirstName,MiddleName,LastName,TransTime,uid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
        UUID uuid = UUID.randomUUID();
        try {
            conn = DBConnector.getMysqlDBConnection();
            ps = conn.prepareStatement(insertValidationTxn);
            ps.setString(1, APIUtils.currDate());
            ps.setString(2, c2BUtils.getTransactionType());
            ps.setString(3, c2BUtils.getTransID());
            ps.setDouble(4, c2BUtils.getTransAmount());
            ps.setString(5, c2BUtils.getBusinessShortCode());
            ps.setString(6, c2BUtils.getBillRefNumber());
            ps.setString(7, c2BUtils.getInvoiceNumber());
            ps.setDouble(8, c2BUtils.getOrgAccountBalance());
            ps.setString(9, c2BUtils.getThirdPartyTransID());
            ps.setString(10, c2BUtils.getMsisdn());
            ps.setString(11, c2BUtils.getFirstName());
            ps.setString(12, c2BUtils.getMiddleName());
            ps.setString(13, c2BUtils.getLastName());
            ps.setString(14, c2BUtils.getTransTime());
            ps.setString(15, uuid.toString());
            int add_detail = ps.executeUpdate();

            if (add_detail == 1) {
                System.out.println("CONFIRMED: Received " + c2BUtils.getTransID() + "|" + " TransTime:" + c2BUtils.getTransTime() + "|" + " Amount:" + "KES " + c2BUtils.getTransAmount() + "|" + " OrgAccountBalance:" + c2BUtils.getOrgAccountBalance() + "|" + " BusinessShortCode:" + c2BUtils.getBusinessShortCode() + "|" + " BillRefNumber:" + c2BUtils.getBillRefNumber() + "|" + " InvoiceNumber:" + c2BUtils.getInvoiceNumber() + "|" + " MSISDN:" + c2BUtils.getMsisdn() + "|" + " FirstName:" + c2BUtils.getFirstName() + " MiddleName:" + c2BUtils.getMiddleName() + " LastName:" + c2BUtils.getLastName());
            } else {
                System.out.println("no txn Confirmed");
            }

            conn.close();
        } catch (Exception exception) {
            Logger logger = Logger.getLogger(C2B.class.getName());
            logger.log(Level.SEVERE, exception.getMessage(), exception);
            System.out.println("Error_Message-->" + exception.getMessage());
        }

        String confirmationResponse = "{\"ResultDesc\":\"Confirmation received succesfully\",\"ResultCode\":\"0\"}";

        return Response.status(201).entity(confirmationResponse).build();
    }

    //localhost:7140/C2B/rest/c2b/stkcallback
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/stkcallback")
    public Response stkcallback(InputStream in) throws IOException, ParseException, java.text.ParseException {
        Scanner s = new Scanner(in).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";

        ObjectMapper mapper = new ObjectMapper();

        JSONObject jsobject = new JSONObject(result);
        JSONObject bodyobject = jsobject.getJSONObject("Body");

        JSONObject stkCallbackobject = bodyobject.getJSONObject("stkCallback");

        String merchantRequestID = stkCallbackobject.getString("MerchantRequestID");

        String checkoutRequestID = stkCallbackobject.getString("CheckoutRequestID");

        int resultCode = stkCallbackobject.getInt("ResultCode");

        String resultDesc = stkCallbackobject.getString("ResultDesc");

        JSONObject callbackMetadataobject = stkCallbackobject.getJSONObject("CallbackMetadata");

        JSONArray itemarray = callbackMetadataobject.getJSONArray("Item");

        //Extracting Amount
        JSONObject itemobjectloopAmount = itemarray.getJSONObject(0);
        String amountName = itemobjectloopAmount.getString("Name");
        double amountValue = itemobjectloopAmount.getDouble("Value");

        //Extracting MpesaReceipt
        JSONObject itemobjectloopReceipt = itemarray.getJSONObject(1);
        String receiptName = itemobjectloopReceipt.getString("Name");
        String receiptValue = itemobjectloopReceipt.getString("Value");

        //Extracting Balance
        JSONObject itemobjectloopBalance = itemarray.getJSONObject(2);
        String balanceName = itemobjectloopBalance.getString("Name");

        //Extracting TransactionDate
        JSONObject itemobjectloopTransactionDate = itemarray.getJSONObject(3);
        String transactionDateName = itemobjectloopTransactionDate.getString("Name");
        long transactionDateValue = itemobjectloopTransactionDate.getLong("Value");

        //Extracting PhoneNumber
        JSONObject itemobjectloopPhoneNumber = itemarray.getJSONObject(4);
        String phoneNumberName = itemobjectloopPhoneNumber.getString("Name");
        long phoneNumberValue = itemobjectloopPhoneNumber.getLong("Value");

        PreparedStatement ps = null;
        Connection conn = null;
        String insertStkcallback = "INSERT INTO stkcallback(uid,MerchantRequestID,CheckoutRequestID,ResultCode,ResultDesc,Amount,MpesaReceiptNumber,Balance,TransactionDate,PhoneNumber) VALUES (?,?,?,?,?,?,?,?,?,?)";
        UUID uuid = UUID.randomUUID();
        try {
            conn = DBConnector.getMysqlDBConnection();
            ps = conn.prepareStatement(insertStkcallback);
            ps.setString(1, uuid.toString());
            ps.setString(2, merchantRequestID);
            ps.setString(3, checkoutRequestID);
            ps.setInt(4, resultCode);
            ps.setString(5, resultDesc);
            ps.setDouble(6, amountValue);
            ps.setString(7, receiptValue);
            ps.setString(8, balanceName);
            ps.setLong(9, transactionDateValue);
            ps.setLong(10, phoneNumberValue);
            int add_detail = ps.executeUpdate();

            if (add_detail == 1) {
                System.out.println("======STKCallback======");
                System.out.println("STK: TransID " + receiptValue + "| Amount:" + amountValue + "| TransactionDate:" + transactionDateValue + "| UUID:" + uuid.toString() + "| MerchantRequestID:" + merchantRequestID + "| CheckoutID:" + checkoutRequestID + "| ResultCode:" + resultCode + "| ResultDesc:" + resultDesc + "| PhoneNumber:" + phoneNumberValue);
                System.out.println("======END=======");
            } else {
                System.out.println("No STK Call back Received");
            }

            conn.close();
        } catch (Exception e) {
            Logger logger = Logger.getLogger(C2B.class.getName());
            logger.log(Level.SEVERE, e.getMessage(), e);
            System.out.println("Error_Message-->" + e.getMessage());
        }
        String stkcallBackResponse = "{\"ResultDesc\":\"Confirmation received succesfully\",\"ResultCode\":\"0\"}";
        System.out.println(stkcallBackResponse);
        return Response.status(201).entity(stkcallBackResponse).build();
    }

    //localhost:7140/C2B/rest/c2b/b2cresult
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/b2cresult")
    public Response b2c(InputStream in) throws IOException, ParseException {
        Scanner s = new Scanner(in).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";
        System.out.println("======B2C======");
        //System.out.println(result);
        JSONObject jsobject = new JSONObject(result);

        JSONObject bodyobject = jsobject.getJSONObject("Result");

        int resultType = bodyobject.getInt("ResultType");

        int resultCode = bodyobject.getInt("ResultCode");

        String resultDesc = bodyobject.getString("ResultDesc");

        String originatorConversationID = bodyobject.getString("OriginatorConversationID");

        String conversationID = bodyobject.getString("ConversationID");

        String transactionID = bodyobject.getString("TransactionID");

        JSONObject resultParametersObject = bodyobject.getJSONObject("ResultParameters");
        JSONArray resultParameterArray = resultParametersObject.getJSONArray("ResultParameter");

        JSONObject transactionAmountLoop = resultParameterArray.getJSONObject(0);
        double transactionAmount = transactionAmountLoop.getDouble("Value");

        JSONObject transactionReceiptLoop = resultParameterArray.getJSONObject(1);
        String transactionReceipt = transactionReceiptLoop.getString("Value");

        JSONObject b2CRecipientIsRegisteredCustomerLoop = resultParameterArray.getJSONObject(2);
        String b2CRecipientIsRegisteredCustomer = b2CRecipientIsRegisteredCustomerLoop.getString("Value");

        JSONObject b2CChargesPaidAccountAvailableFundsLoop = resultParameterArray.getJSONObject(3);
        Double b2CChargesPaidAccountAvailableFunds = b2CChargesPaidAccountAvailableFundsLoop.getDouble("Value");

        JSONObject receiverPartyPublicNameLoop = resultParameterArray.getJSONObject(4);
        String receiverPartyPublicName = receiverPartyPublicNameLoop.getString("Value");

        JSONObject transactionCompletedDateTimeLoop = resultParameterArray.getJSONObject(5);
        String transactionCompletedDateTime = transactionCompletedDateTimeLoop.getString("Value");

        JSONObject b2CUtilityAccountAvailableFundsLoop = resultParameterArray.getJSONObject(6);
        Double b2CUtilityAccountAvailableFunds = b2CUtilityAccountAvailableFundsLoop.getDouble("Value");

        JSONObject b2CWorkingAccountAvailableFundsLoop = resultParameterArray.getJSONObject(7);
        Double b2CWorkingAccountAvailableFunds = b2CWorkingAccountAvailableFundsLoop.getDouble("Value");

        JSONObject referenceData = bodyobject.getJSONObject("ReferenceData");
        JSONObject referenceItem = referenceData.getJSONObject("ReferenceItem");
        String queueTimeoutURL = referenceItem.getString("Value");
        // DB Connection
        PreparedStatement ps = null;
        Connection conn = null;

        String insertB2c = "INSERT INTO b2c(uid,Txdate,ResultType,ResultCode,OriginatorConversationID,ConversationID,TransactionID,TransactionAmount,TransactionReceipt,B2CRecipientIsRegisteredCustomer,B2CChargesPaidAccountAvailableFunds,BeneficiaryName,TxCompletedDateTime,B2CUtilityAccountAvailableFunds,B2CWorkingAccountAvailableFunds,QueueTimeoutURL) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        UUID uuid = UUID.randomUUID();
        try {
            conn = DBConnector.getMysqlDBConnection();
            ps = conn.prepareStatement(insertB2c);
            ps.setString(1, uuid.toString());
            ps.setString(2, APIUtils.currDate());
            ps.setInt(3, resultType);
            ps.setInt(4, resultCode);
            //ps.setString(5, resultDesc);
            ps.setString(5, originatorConversationID);
            ps.setString(6, conversationID);
            ps.setString(7, transactionID);
            ps.setDouble(8, transactionAmount);
            ps.setString(9, transactionReceipt);
            ps.setString(10, b2CRecipientIsRegisteredCustomer);
            ps.setDouble(11, b2CChargesPaidAccountAvailableFunds);
            ps.setString(12, receiverPartyPublicName);
            ps.setString(13, transactionCompletedDateTime);
            ps.setDouble(14, b2CUtilityAccountAvailableFunds);
            ps.setDouble(15, b2CWorkingAccountAvailableFunds);
            ps.setString(16, queueTimeoutURL);

            int add_detail = ps.executeUpdate();

            if (add_detail == 1) {
                System.out.println("B2C: TransID " + transactionID + "| Amount:" + transactionAmount + "| TransactionDate:" + transactionCompletedDateTime + "| UUID:" + uuid.toString());
            } else {
                System.out.println("No STK Call back Received");
            }

            conn.close();
        } catch (Exception e) {
            Logger logger = Logger.getLogger(C2B.class.getName());
            logger.log(Level.SEVERE, e.getMessage(), e);
            System.out.println("Error_Message-->" + e.getMessage());
        }
        //
        System.out.println("B2C" + bodyobject);
        System.out.println("======END=======");
        String stkcallBackResponse = "{\"ResultDesc\":\"Confirmation received succesfully\",\"ResultCode\":\"0\"}";
        System.out.println(stkcallBackResponse);
        return Response.status(201).entity(stkcallBackResponse).build();
    }

    //localhost:7140/C2B/rest/c2b/b2bresult
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/b2bresult")
    public Response b2b(InputStream in) throws IOException, ParseException {
        Scanner s = new Scanner(in).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";
        System.out.println("======B2B======");
        JSONObject jsobject = new JSONObject(result);

        JSONObject bodyobject = jsobject.getJSONObject("Result");

        int resultType = bodyobject.getInt("ResultType");

        int resultCode = bodyobject.getInt("ResultCode");

        String resultDesc = bodyobject.getString("ResultDesc");

        String originatorConversationID = bodyobject.getString("OriginatorConversationID");;

        String conversationID = bodyobject.getString("ConversationID");

        String transactionID = bodyobject.getString("TransactionID");

        JSONObject resultParametersObject = bodyobject.getJSONObject("ResultParameters");
        JSONArray resultParameterArray = resultParametersObject.getJSONArray("ResultParameter");

        JSONObject initiatorAccountCurrentBalanceLoop = resultParameterArray.getJSONObject(0);
        String initiatorAccountCurrentBalance = initiatorAccountCurrentBalanceLoop.getString("Value");

        JSONObject debitAccountCurrentBalanceLoop = resultParameterArray.getJSONObject(1);
        String debitAccountCurrentBalance = debitAccountCurrentBalanceLoop.getString("Value");

        JSONObject amountLoop = resultParameterArray.getJSONObject(2);
        Double amount = amountLoop.getDouble("Value");

        JSONObject debitPartyAffectedAccountBalanceLoop = resultParameterArray.getJSONObject(3);
        String debitPartyAffectedAccountBalance = debitPartyAffectedAccountBalanceLoop.getString("Value");

        JSONObject transactionCompletedDateTimeLoop = resultParameterArray.getJSONObject(4);
        long transactionCompletedDateTime = transactionCompletedDateTimeLoop.getLong("Value");

        JSONObject debitPartyChargesNameLoop = resultParameterArray.getJSONObject(5);
        String debitPartyChargesName = debitPartyChargesNameLoop.getString("Value");

        JSONObject receiverPartyPublicNameLoop = resultParameterArray.getJSONObject(6);
        String receiverPartyPublicName = receiverPartyPublicNameLoop.getString("Value");

        JSONObject currencyLoop = resultParameterArray.getJSONObject(7);
        String currency = currencyLoop.getString("Value");

        JSONObject referenceDataObject = bodyobject.getJSONObject("ReferenceData");
        JSONArray referenceItemArray = referenceDataObject.getJSONArray("ReferenceItem");

        JSONObject billReferenceNumberLoop = referenceItemArray.getJSONObject(0);
        String billReferenceNumber = billReferenceNumberLoop.getString("Value");

        JSONObject queueTimeoutURLLoop = referenceItemArray.getJSONObject(1);
        String queueTimeoutURL = queueTimeoutURLLoop.getString("Value");

        // DB Connection
        PreparedStatement ps = null;
        Connection conn = null;

        String insertB2c = "INSERT INTO b2b(uid,Txdate,ResultType,ResultCode,OriginatorConversationID,ConversationID,TransactionID,InitiatorAccountCurrentBalance,DebitAccountCurrentBalance,Amount,DebitPartyAffectedAccountBalance,TransCompletedTime,DebitPartyCharges,ReceiverPartyPublicName,Currency,BillReferenceNumber,QueueTimeoutURL) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        UUID uuid = UUID.randomUUID();
        try {
            conn = DBConnector.getMysqlDBConnection();
            ps = conn.prepareStatement(insertB2c);
            ps.setString(1, uuid.toString());
            ps.setString(2, APIUtils.currDate());
            ps.setInt(3, resultType);
            ps.setInt(4, resultCode);
            ps.setString(5, originatorConversationID);
            ps.setString(6, conversationID);
            ps.setString(7, transactionID);
            ps.setString(8, initiatorAccountCurrentBalance);
            ps.setString(9, debitAccountCurrentBalance);
            ps.setDouble(10, amount);
            ps.setString(11, debitPartyAffectedAccountBalance);
            ps.setLong(12, transactionCompletedDateTime);
            ps.setString(13, debitPartyChargesName);
            ps.setString(14, receiverPartyPublicName);
            ps.setString(15, currency);
            ps.setString(16, billReferenceNumber);
            ps.setString(17, queueTimeoutURL);

            int add_detail = ps.executeUpdate();

            if (add_detail == 1) {
                System.out.println("B2B: TransID: " + transactionID + "| Amount:" + amount + "| TransactionDate:" + transactionCompletedDateTime + "| UUID:" + uuid.toString());
            } else {
                System.out.println("No B2B Call back Received");
            }

            conn.close();
        } catch (Exception e) {
            Logger logger = Logger.getLogger(C2B.class.getName());
            logger.log(Level.SEVERE, e.getMessage(), e);
            System.out.println("Error_Message-->" + e.getMessage());
        }
        //

        System.out.println("======END=======");
        String stkcallBackResponse = "{\"ResultDesc\":\"Confirmation received succesfully\",\"ResultCode\":\"0\"}";
        System.out.println(stkcallBackResponse);
        return Response.status(201).entity(stkcallBackResponse).build();
    }

}
