/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Utilitaire;
import com.codename1.ui.Display;
import com.nexmo.client.*;
import com.nexmo.client.auth.AuthMethod;
import com.nexmo.client.auth.TokenAuthMethod;
import com.nexmo.client.sms.SmsSubmissionResult;
import com.nexmo.client.sms.messages.TextMessage;
import java.io.IOException;




/**
 *
 * @author skanderbejaoui
 */
public class SMS {
   public static void creer()  {
       try {
           AuthMethod auth = new TokenAuthMethod("077ae98d","PdOnN5znY0r6IURO");
           NexmoClient client = new NexmoClient(auth);
           SmsSubmissionResult[] responses = client.getSmsClient().submitMessage(new TextMessage(
                   "Russia2018",
                   "21655248042",
                   "Félicitations, vous avez gagné un pari!"));
           for (SmsSubmissionResult response : responses) {
               System.out.println(response);
           }      } catch (IOException ex) {
           
       } catch (NexmoClientException ex) {
          
       }
    }
}
   


   