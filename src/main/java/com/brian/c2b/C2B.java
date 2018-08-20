/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brian.c2b;

import com.brian.db.DBConnector;
import com.brian.stk.BodyX;
import com.brian.stk.CallbackMetadataX;
import com.brian.stk.ItemX;
import com.brian.stk.STKCallbackUtilsX;
import com.brian.stk.StkCallbackX;
import com.brian.stkcallback.Body;
import com.brian.stkcallback.STKUtil;
import com.brian.stkcallback.StkCallback;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.HTTP;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.postgresql.util.PSQLException;

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
        String insertValidationTxn = "INSERT INTO c2b_validation( TransactionType,TransID,TransAmount,BusinessShortCode,BillRefNumber,InvoiceNumber,OrgAccountBalance,ThirdPartyTransID,MSISDN,FirstName,MiddleName,LastName,TransTime,uid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        UUID uuid = UUID.randomUUID();
        try {
            conn = DBConnector.getPostgresSqlDBConnection();
            ps = conn.prepareStatement(insertValidationTxn);
            ps.setString(1, c2BUtils.getTransactionType());
            ps.setString(2, c2BUtils.getTransID());
            ps.setDouble(3, c2BUtils.getTransAmount());
            ps.setString(4, c2BUtils.getBusinessShortCode());
            ps.setString(5, c2BUtils.getBillRefNumber());
            ps.setString(6, c2BUtils.getInvoiceNumber());
            ps.setDouble(7, c2BUtils.getOrgAccountBalance());
            ps.setString(8, c2BUtils.getThirdPartyTransID());
            ps.setString(9, c2BUtils.getMsisdn());
            ps.setString(10, c2BUtils.getFirstName());
            ps.setString(11, c2BUtils.getMiddleName());
            ps.setString(12, c2BUtils.getLastName());
            ps.setString(13, c2BUtils.getTransTime());
            ps.setString(14, uuid.toString());
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
        String insertValidationTxn = "INSERT INTO c2b_confirmation( TransactionType,TransID,TransAmount,BusinessShortCode,BillRefNumber,InvoiceNumber,OrgAccountBalance,ThirdPartyTransID,MSISDN,FirstName,MiddleName,LastName,TransTime,uid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        UUID uuid = UUID.randomUUID();
        try {
            conn = DBConnector.getPostgresSqlDBConnection();
            ps = conn.prepareStatement(insertValidationTxn);
            ps.setString(1, c2BUtils.getTransactionType());
            ps.setString(2, c2BUtils.getTransID());
            ps.setDouble(3, c2BUtils.getTransAmount());
            ps.setString(4, c2BUtils.getBusinessShortCode());
            ps.setString(5, c2BUtils.getBillRefNumber());
            ps.setString(6, c2BUtils.getInvoiceNumber());
            ps.setDouble(7, c2BUtils.getOrgAccountBalance());
            ps.setString(8, c2BUtils.getThirdPartyTransID());
            ps.setString(9, c2BUtils.getMsisdn());
            ps.setString(10, c2BUtils.getFirstName());
            ps.setString(11, c2BUtils.getMiddleName());
            ps.setString(12, c2BUtils.getLastName());
            ps.setString(13, c2BUtils.getTransTime());
            ps.setString(14, uuid.toString());
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

//    //STK Callback
//    //localhost:7140/C2B/rest/c2b/stkcallback
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/stkcallback")
//    public Response STKCallback(BodyX body) throws PSQLException, SQLException {
//        STKCallbackUtilsX stkutils = new STKCallbackUtilsX();
//        ObjectMapper mapper = new ObjectMapper();
//        StkCallbackX stkcallback = new StkCallbackX();
////        String MerchantRequestID=stkcallback.getMerchantRequestID();
////        String CheckoutRequestID=stkcallback.getCheckoutRequestID();
////        int ResultCode =stkcallback.getResultCode();
////        String ResultDesc= stkcallback.getResultDesc();
//        CallbackMetadataX callbackmeta = new CallbackMetadataX();
////        ItemX item = new ItemX();
//        String[][] coupleArray = new String[5][2];
////        coupleArray[0][0] = item.getName();//"Name":"Amount",
////        coupleArray[0][1] = item.getValue();//"Value":5.00
////        // Double d = Double.parseDouble(coupleArray[0][1]);
////        coupleArray[1][0] = item.getName();//"Name":"MpesaReceiptNumber",
////        coupleArray[1][1] = item.getValue();//"Value":"LIR9F1EXBZ"
////        coupleArray[2][0] = item.getName();//"Name":"Balance"
////        //coupleArray[2][1] = item.getValue();
////        coupleArray[3][0] = item.getName();//"Name":"TransactionDate",
////        coupleArray[3][1] = item.getValue();// "Value":20170927161352
////        coupleArray[4][0] = item.getName();//"Name":"PhoneNumber",
////        coupleArray[4][1] = item.getValue();// "Value":254722000000
//
//        PreparedStatement ps = null;
//        Connection conn = null;
//        String insertStkcallback = "INSERT INTO stkcallback(uid,MerchantRequestID,CheckoutRequestID,ResultCode,ResultDesc,Amount,MpesaReceiptNumber,Balance,TransactionDate,PhoneNumber) VALUES (?,?,?,?,?,?,?,?,?,?)";
//        UUID uuid = UUID.randomUUID();
//        try {
//            conn = DBConnector.getPostgresSqlDBConnection();
//            ps = conn.prepareStatement(insertStkcallback);
//            ps.setString(1, uuid.toString());
//            ps.setString(2, stkcallback.getMerchantRequestID());
//            ps.setString(3, stkcallback.getCheckoutRequestID());
//            ps.setInt(4, stkcallback.getResultCode());
//            ps.setString(5, stkcallback.getResultDesc());
//            ps.setString(6, coupleArray[0][1]);
//            ps.setString(7, coupleArray[1][1]);
//            ps.setString(8, coupleArray[2][1]);
//            ps.setString(9, coupleArray[3][1]);
//            ps.setString(10, coupleArray[4][1]);
//            int add_detail = ps.executeUpdate();
//
//            if (add_detail == 1) {
//                System.out.println("CONFIRMED: Received " + coupleArray[1][1] + " UUID:" + uuid.toString() + " MerchantRequestID:" + stkcallback.getMerchantRequestID() + " CheckoutID:" + stkcallback.getCheckoutRequestID() + " ResultCode:" + stkcallback.getResultCode() + " ResultDesc:" + stkcallback.getResultDesc() + " MpesaReference:" + coupleArray[1][1]);
//            } else {
//                System.out.println("No STK Call back Received");
//            }
//
//            conn.close();
//        } catch (Exception e) {
//            Logger logger = Logger.getLogger(C2B.class.getName());
//            logger.log(Level.SEVERE, e.getMessage(), e);
//            System.out.println("Error_Message-->" + e.getMessage());
//        }
//
//        String stkcallBackResponse = "{\"ResultDesc\":\"Confirmation received succesfully\",\"ResultCode\":\"0\"}";
//        System.out.println(stkcallBackResponse);
//        return Response.status(201).entity(stkcallBackResponse).build();
//    }

     //STK Callback
    //localhost:7140/C2B/rest/c2b/STK
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/STK")
    public Response STK(STKUtil stkutil) throws PSQLException, SQLException {
       Body body = stkutil.getBody();
         StkCallback Stkcallback = new StkCallback();
         String merchantRequestID = Stkcallback.getMerchantRequestID();
         String checkoutRequestID =  Stkcallback.getCheckoutRequestID();
         int resultCode = Stkcallback.getResultCode();
         String resultDesc = Stkcallback.getResultDesc();
         
        System.out.println("MerchantRequestID::"+merchantRequestID);
      
        System.out.println("CheckoutRequestID::"+checkoutRequestID);
    
        System.out.println("ResultCode::"+resultCode);
       
        System.out.println("ResultDesc::"+resultDesc);
       
 
        String[][] coupleArray = new String[5][2];
         //coupleArray[4][1] = item.getValue();// "Value":254722000000

        PreparedStatement ps = null;
        Connection conn = null;
        String insertStkcallback = "INSERT INTO stkcallback(uid,MerchantRequestID,CheckoutRequestID,ResultCode,ResultDesc,Amount,MpesaReceiptNumber,Balance,TransactionDate,PhoneNumber) VALUES (?,?,?,?,?,?,?,?,?,?)";
        UUID uuid = UUID.randomUUID();
        System.out.println("UUID::"+uuid);
//        try {
//            conn = DBConnector.getPostgresSqlDBConnection();
//            ps = conn.prepareStatement(insertStkcallback);
//            ps.setString(1, uuid.toString());
//            ps.setString(2, stkcallback.getMerchantRequestID());
//            ps.setString(3, stkcallback.getCheckoutRequestID());
//            ps.setInt(4, stkcallback.getResultCode());
//            ps.setString(5, stkcallback.getResultDesc());
 
//
//            if (add_detail == 1) {
//                System.out.println("CONFIRMED: Received " + coupleArray[1][1] + " UUID:" + uuid.toString() + " MerchantRequestID:" + stkcallback.getMerchantRequestID() + " CheckoutID:" + stkcallback.getCheckoutRequestID() + " ResultCode:" + stkcallback.getResultCode() + " ResultDesc:" + stkcallback.getResultDesc() + " MpesaReference:" + coupleArray[1][1]);
//            } else {
//                System.out.println("No STK Call back Received");
//            }
//
//            conn.close();
//        } catch (Exception e) {
//            Logger logger = Logger.getLogger(C2B.class.getName());
//            logger.log(Level.SEVERE, e.getMessage(), e);
//            System.out.println("Error_Message-->" + e.getMessage());
//        }

        String stkcallBackResponse = "{\"ResultDesc\":\"Confirmation received succesfully\",\"ResultCode\":\"0\"}";
        System.out.println(stkcallBackResponse);
        return Response.status(201).entity(stkcallBackResponse).build();
    }
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/stk3")
    public Response stk3(InputStream in) throws IOException, ParseException {
        Scanner s = new Scanner(in).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";
        System.out.println("======STKCallback======");
        System.out.println(result);
        //looping through the json
        JSONObject outerObject = new JSONObject();

        System.out.println("======END=======");
        String stkcallBackResponse = "{\"ResultDesc\":\"Confirmation received succesfully\",\"ResultCode\":\"0\"}";
        System.out.println(stkcallBackResponse);
        return Response.status(201).entity(stkcallBackResponse).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/b2cresult")
    public Response b2c(InputStream in) throws IOException, ParseException {
        Scanner s = new Scanner(in).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";
        System.out.println("======B2C======");
        System.out.println(result);
        System.out.println("======END=======");
        String stkcallBackResponse = "{\"ResultDesc\":\"Confirmation received succesfully\",\"ResultCode\":\"0\"}";
        System.out.println(stkcallBackResponse);
        return Response.status(201).entity(stkcallBackResponse).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/b2bresult")
    public Response b2b(InputStream in) throws IOException, ParseException {
        Scanner s = new Scanner(in).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";
        System.out.println("======B2B======");
        System.out.println(result);
        System.out.println("======END=======");
        String stkcallBackResponse = "{\"ResultDesc\":\"Confirmation received succesfully\",\"ResultCode\":\"0\"}";
        System.out.println(stkcallBackResponse);
        return Response.status(201).entity(stkcallBackResponse).build();
    }
}
