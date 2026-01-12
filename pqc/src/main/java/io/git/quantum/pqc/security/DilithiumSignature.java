package io.git.quantum.pqc.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Provider;
import java.security.Security;
import java.security.Signature;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DilithiumSignature {
    private static final Logger log = LogManager.getLogger(DilithiumSignature.class);
    private static DilithiumSignature INSTANCE;

    private DilithiumSignature() {/*SINGLETON*/}

    public static DilithiumSignature getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DilithiumSignature();
        }
        return INSTANCE;
    }

    public void run() {

        try {
            
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("ML-DSA");
            KeyPair kp = kpg.generateKeyPair();
            Signature sig = Signature.getInstance("ML-DSA");
            String message = "Post-Quantum Java is here!";
            byte[] messageBytes = message.getBytes();
            
            // Signing
            sig.initSign(kp.getPrivate());
            sig.update(messageBytes);
            byte[] signature = sig.sign();
            
            // Verification
            sig.initVerify(kp.getPublic());
            sig.update(messageBytes);
            boolean isVerified = sig.verify(signature);
            System.out.println("Signature Verified: " + isVerified);


            for (Provider provider : Security.getProviders()) {
                log.info("Provider: " + provider.getName());
                for (Provider.Service service : provider.getServices()) {
                    if ("KeyAgreement".equals(service.getType())) {
                        log.info("  KeyAgreement Algorithm: " + service.getAlgorithm());
                    }
                }
            }

        } catch (Exception e) {
            log.error("Error occurred: ", e);
        }

    }

}
