package io.git.quantum.pqc;

import io.git.quantum.pqc.security.DilithiumSignature;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        DilithiumSignature.getInstance().run();
    }
}
