package org.main;

import org.main.qr_generator.QRCodeGenerator;

public class Main {
    public static void main(String[] args) {
        QRCodeGenerator.generateQRCode("https://iticket.md");
    }
}