package EffectiveJava.item9;

import java.io.*;

/**
 * 숨겨진 에러를 만들 수 있는 suppressed를 사용하여 CustomMultipleException을 만들어보자
 * 두 가지 이상 자원에 try-catch-with-resources를 사용할 때 숨겨진 에러를 찾을 수 있다.
 */
public class ThrowableGetSuppressedExample {

    public static void main(String[] args) {
        try {
            raiseCustomMultiException();
        } catch (Exception e) {
            e.printStackTrace();
            Throwable[] suppExe = e.getSuppressed(); // getSuppressed로 숨겨진 예외를 가져온다.

            // print element of suppExe
            for (int i = 0; i < suppExe.length; i++) {

                System.out.println("Suppressed Exceptions:");
                System.out.println(suppExe[i]);
            }
        }
    }


    /**
     * @throws Exception
     * IOException 속의 숨겨진(suppressed) NumberFormatException이 존재하는 형태
     */
    public static void raiseCustomMultiException() throws Exception {

        // creating a suppressed Exception
        Exception suppressed = new NumberFormatException();

        // creating a IOException object
        final IOException ioe = new IOException();

        // adding suppressed Exception
        ioe.addSuppressed(suppressed); // 숨겨진 형태로 예외 추가

        // throwing IOException
        throw ioe;
    }
}
