package org.main.qr_generator;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Slf4j
public class QRCodeGenerator {
    private final static int width = 300;
    private final static int height = 300;
    private final static String format = "png";
    private final static String path = System.getProperty("user.dir") + File.separator + "qr_codes" + File.separator;

    public static void generateQRCode(String url){
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, width, height);

            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
            bufferedImage.createGraphics();

            for(int x = 0; x < width; x++){
                for(int y = 0; y < height; y++){
                    bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }

            File qrCodeFile = new File(path + generateNameForQRCodeFile(url) + ".png");
            ImageIO.write(bufferedImage, format, qrCodeFile);
            log.info("QR Code was generated for url ===> " + url);
        }catch (WriterException | IOException e) {
            log.error(e.getMessage());
        }
    }

    private static String generateNameForQRCodeFile(String url){
        String firstReplace = url.replaceAll("https://" , "");
        String secondReplace = firstReplace.replaceAll("\\.", "");
        return "QR_" + secondReplace;
    }
}
