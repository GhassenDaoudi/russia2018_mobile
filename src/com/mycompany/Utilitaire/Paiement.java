/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Utilitaire;
import com.paydunya.neptune.*;
/**
 *
 * @author skanderbejaoui
 */
public class Paiement {
      public static String payer(float qte) {
        PaydunyaSetup setup = new PaydunyaSetup();
        PaydunyaCheckoutStore store = new PaydunyaCheckoutStore();
        setup.setMasterKey("GRPkpNLh-ekmy-LBGP-8Oin-qINqVjofG2dY");
        setup.setPrivateKey("test_private_eH8PPZlIKJx8LIczQbUeRJGywYy");
        setup.setPublicKey("test_public_njs3rf9kLkrm8G1mlwGHoHuiKau");
        setup.setToken("E44uqdyTo49rw0Fkx86Q");
        setup.setMode("test");
        store.setName("Russie_2018"); // Seul le nom est requis
        store.setTagline("Application coupe du monde");
        store.setPhoneNumber("+21628428425");
        store.setPostalAddress("Tunisie");
      store.setReturnUrl("http://localhost/RS2018/web");
      store.setCallbackUrl("http://localhost/RS2018/web");
  
        PaydunyaCheckoutInvoice invoice = new PaydunyaCheckoutInvoice(setup, store);
            invoice.setCallbackUrl("http://localhost/RS2018/web");
        invoice.setReturnUrl("http://localhost/RS2018/web");
        
        
        invoice.setDescription("Description Optionnelle");
        invoice.setTotalAmount(qte);
        if (invoice.create()) {
            String invoiceToken =invoice.token;
            System.out.println(invoice.getStatus());
            System.out.println(invoice.getResponseText());
            System.out.println(invoice.getInvoiceUrl());
            return invoice.getInvoiceUrl();
           /* new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        if (invoice.confirm(invoiceToken)) {
                            // Récupérer le statut du paiement
                            // Le statut du paiement peut être soit completed, pending, cancelled
                           /* System.out.println(invoice.getStatus());
                            System.out.println(invoice.getResponseText());

                            // Vous pouvez récupérer le nom, l'adresse email et le
                            // numéro de téléphone du client en utilisant
                            // les méthodes suivantes
                            System.out.println(invoice.getCustomerInfo("name"));
                            System.out.println(invoice.getCustomerInfo("email"));
                            System.out.println(invoice.getCustomerInfo("phone"));

                            // Les méthodes qui suivent seront disponibles si et
                            // seulement si le statut du paiement est égal à "completed".
                            // Récupérer l'URL du reçu PDF électronique pour téléchargement
                            System.out.println(invoice.getReceiptUrl());

                            // Récupérer n'importe laquelle des données personnalisées que
                            // vous avez eu à rajouter précédemment à la facture.
                            // Merci de vous assurer à utiliser les mêmes clés que celles utilisées
                            // lors de la configuration.
                            System.out.println(invoice.getCustomData("Catégorie"));
                            System.out.println(invoice.getCustomData("Période"));
                            System.out.println(invoice.getCustomData("Gagnant N°"));
                            System.out.println(invoice.getCustomData("Prix"));

                            // Vous pouvez aussi récupérer le montant total spécifié précédemment
                            System.out.println(invoice.getTotalAmount());
                            break;

                        } else {
                            System.out.println(".");
                            /*System.out.println(invoice.getStatus());
                            System.out.println(invoice.getResponseText());
                            System.out.println(invoice.getResponseCode());

                        }
                    }
                }
            }).start();*/

        } else {
            System.out.println(invoice.getResponseText());
            System.out.println(invoice.getResponseCode());
            return "erreur";
        }
        
    }
}
