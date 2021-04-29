package life.pascal.guestPassword;

import io.nayuki.qrcodegen.QrCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@RestController
public class PasswordController {

    @Autowired
    private ServiceConfiguration configuration;

    @PostMapping("/password")
    public void setNewPassword(@RequestParam(value = "key", defaultValue = "") String key, @RequestParam(value = "password", defaultValue = "") String password) {
        System.out.println(key + "key, " + password);
        if (configuration.getAuthKey() != null && !configuration.getAuthKey().isBlank()
                && !configuration.getAuthKey().equals(key))
            return;

        String wifiQrText = "WIFI:S:" + configuration.getSsid() + ";T:WPA;P:" + password + ";;";
        QrCode qr0 = QrCode.encodeText(wifiQrText, QrCode.Ecc.MEDIUM);
        BufferedImage img = qr0.toImage(4, 10);
        try {

            ImageIO.write(img, "png", new File("images/qr-code.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
