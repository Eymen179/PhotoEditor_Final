package org.photoeditor;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class TakePicture {
    public String capturePhoto(JLabel lblTakePhotoProgress) {
        //OpenCV kütüphanelerinin kullanılabilmesi için kod içine yükleme yapılır.
        // Kaynak dosyayı geçici bir dosyaya kopyalar.
        File dllTemp = null;
        try {
            InputStream dllStream = Main.class.getResourceAsStream("/opencv_java470.dll");
            dllTemp = File.createTempFile("opencv_java470", ".dll");
            dllTemp.deleteOnExit();
            Files.copy(dllStream, dllTemp.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Fotoğraf çekilirken bir hata oluştu","Hata", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        
        // Kaynak dosyayı yükler.
        try {
            System.load(dllTemp.getAbsolutePath());
        } catch (UnsatisfiedLinkError e) {
            JOptionPane.showMessageDialog(null,"Fotoğraf çekilirken bir hata oluştu","Hata", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        //Bu değişkenle seçilen kameraya bağlanılır. 0 değeri varsayılan kamera için geçerlidir.
        VideoCapture camera = new VideoCapture(0);

        //Kamera açılmazsa yazılacak uyarı mesajları
        if (!camera.isOpened()) {
            System.out.println("Kamera açılamadı.");
            JOptionPane.showMessageDialog(null, "Kameraya erişim sağlanamadı. Lütfen tekrar deneyin!", "Hata", JOptionPane.ERROR_MESSAGE);
            lblTakePhotoProgress.setText("Kameraya erişim başarısız!");
        }
        
        //Bir matris değişkeni tanımlanır ve kamerayla çekilen fotoğraf matrise atanır.
        Mat frame = new Mat();
        camera.read(frame);
        camera.release();//Kamera ve kameraya giden bağlantı kapatılır.

        //Dosya yolu seçtirici değişken
        JFileChooser fileChooser = new JFileChooser();

        //Dosya yolu seçme ekranı için bir başlık
        fileChooser.setDialogTitle("Kayıt Dizini Seç");
        lblTakePhotoProgress.setText("Kullanıcıdan dosya yolu seçmesi bekleniyor...");
        int userSelection = fileChooser.showSaveDialog(null);

        //Dosya yolu seçimi düzgün yapılırsa dosya yolu kaydedilir ve fotoğraf bu dosya yoluna yazdırılır.
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();
            filePath = filePath + ".png";
            
            Imgcodecs.imwrite(filePath, frame);
            lblTakePhotoProgress.setText("Fotoğraf kaydedildi.");
            System.out.println("Fotoğraf kaydedildi: " + filePath);
            
            // Uygulama sonunda geçici dosya silinir.
            dllTemp.delete();
            return filePath;
        }else{
            //Olası bir hatada gözükecek mesajlar
            JOptionPane.showMessageDialog(null, "Dosyaya yazdırma işlemi başarısızlıkla sonuçlandı. Lütfen tekrar deneyin!", "Hata", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}