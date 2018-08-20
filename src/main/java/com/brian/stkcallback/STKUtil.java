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
public class STKUtil {
    Body body;

    public STKUtil(){}

    public STKUtil(Body body){this.body=body;}
 // Getter Methods 

 public Body getBody() {
  return body;
 }

 // Setter Methods 

 public void setBody(Body body) {
  this.body = body;
 }
}
