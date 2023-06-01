package org.photoeditor;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

class ImageEffects {
    public String effectImage(String baseFilePath, String tmpFilePath, JLabel lblImage, int x) {
        //OpenCV kütüphanelerinin kullanılabilmesi için kod içine yükleme yapılır.
        // Kaynak dosyayı geçici bir dosyaya kopyalar.
        File dllTemp = null;
        try {
            InputStream dllStream = Main.class.getResourceAsStream("/opencv_java470.dll");
            dllTemp = File.createTempFile("opencv_java470", ".dll");
            dllTemp.deleteOnExit();
            Files.copy(dllStream, dllTemp.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Filtre uygulanırken bir hata oluştu","Hata", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        
        // Kaynak dosyayı yükler.
        try {
            System.load(dllTemp.getAbsolutePath());
        } catch (UnsatisfiedLinkError e) {
            JOptionPane.showMessageDialog(null,"Filtre uygulanırken bir hata oluştu","Hata", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        // Fotoğrafı oku
        Mat mImage = Imgcodecs.imread(baseFilePath);
        ImageProcessor effect = null;

        //Arayüzden seçilen RadioButton'la gelen efekt numarasına göre efekt seçimi
        switch (x) {
            case 1 -> effect = new GrayscaleEffect(mImage);
            case 2 -> effect = new FlipEffect(mImage);
            case 3 -> effect = new BrightenEffect(mImage);
            case 4 -> effect = new DarkenEffect(mImage);
            case 5 -> effect = new BlurEffect(mImage);
            case 6 ->{
                //Orijinal fotoğrafın kendisi yeniden boyutlandırma metoduna gönderilip ön izlemede gösterilir.
                ImageIcon imageIcon = Main.resizeImageIcon(new ImageIcon(baseFilePath), 200, 200);
                lblImage.setIcon(imageIcon);
                return baseFilePath;
            }
            default -> {
                //Olası bir hata durumunda gözükecek mesajlar
                JOptionPane.showMessageDialog(null,"Filtre uygulanırken bir hata oluştu","Hata", JOptionPane.ERROR_MESSAGE);
                System.out.println("Geçersiz seçim!");
                System.exit(0);
            }
        }
        //Seçilen efekt fotoğrafa uygulanır.
        effect.applyEffect();

        //Filtrelenmiş fotoğrafın ön izlemede anında gözükebilmesi için fotoğraf geçici olarak bir dosyaya kaydedilir
        //ve arayüzde düzgün gözükmesi için yeniden boyutlandırma metoduna gönderilir.
        tmpFilePath = tmpFilePath + File.separator + "tmp.png";
        Imgcodecs.imwrite(tmpFilePath, mImage);
        ImageIcon imageIcon = Main.resizeImageIcon(new ImageIcon(tmpFilePath), 200, 200);
        lblImage.setIcon(imageIcon);
        
        // Uygulama sonunda temp dosyasını silmeyi unutmayın
        dllTemp.delete();
        //Geçici dosya yolu döndürülür.
        return tmpFilePath;
    }
    public String chooseFile(String tmpFilePath, JLabel lblFilteredImageSaveProgress){
        //Geçici dosya yolundaki filtrelenmiş fotoğraf bir matrise kaydedilir.
        Mat mImageFiltered = Imgcodecs.imread(tmpFilePath);

        //Dosya yolu seçtirici değişken
        JFileChooser fileChooser = new JFileChooser();
        lblFilteredImageSaveProgress.setText("Kullanıcıdan dosya yolu seçmesi bekleniyor...");

        //Dosya yolu seçme ekranı için bir başlık
        fileChooser.setDialogTitle("Kayıt Dizini Seç");
        int userSelection = fileChooser.showSaveDialog(null);

        //Dosya yolu seçimi düzgün yapılırsa dosya yolu kaydedilir ve fotoğraf bu dosya yoluna yazdırılır.
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath2 = fileToSave.getAbsolutePath();
            filePath2 = filePath2 + ".png";
            Imgcodecs.imwrite(filePath2, mImageFiltered);

            lblFilteredImageSaveProgress.setText("Filtreli fotoğraf kaydedildi.");
            System.out.println("Filtreli yeni fotoğraf kaydedildi: " + filePath2);
            
            //Yeni dosya yolu döndürülür
            return filePath2;
        }else{
            //Olası bir hatada gözükecek mesajlar
            JOptionPane.showMessageDialog(null, "Filtre uygulanamadı.");
            return null;
        }
    }
}