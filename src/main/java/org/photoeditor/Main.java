//Eymen Arapoğlu - 22120205070
//Efe Furkan Arkan - 22120205034
//Muhammet Fatih Özçevik - 22120205063

package org.photoeditor;

import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

class Main extends JFrame {

    //Bileşenlerin komutlarını ve konumlarını ayarlayan initComponents() metodu, bu metodu çalıştıracak constructor,
    //GUI görünümü ve bileşenlerin tanımlamaları NetBeans Form Editor üzerinden yapılmıştır.
    public Main() {
        initComponents();
    }

    private void initComponents() {
        //Bileşenlerin initComponents() metodu içinde yeniden tanımlanması
        btnGroupFilterList = new ButtonGroup();
        btnPhoto = new JButton();
        btnSocialMedia = new JButton();
        lblFilterTitle = new JLabel();
        lblSocialMediaTitle = new JLabel();
        imageIcon = new ImageIcon(getClass().getClassLoader().getResource("default.png"));
        lblImage = new JLabel(imageIcon);
        rbnGrayScaleEffect = new JRadioButton();
        rbnFlipEffect = new JRadioButton();
        rbnBrightenEffect = new JRadioButton();
        rbnDarkenEffect = new JRadioButton();
        rbnBlurEffect = new JRadioButton();
        btnFilteredImageSave = new JButton();
        rbnNonFilterMode = new JRadioButton();
        lblFilteredImageSave = new JLabel();
        lblTakePhotoProgress = new JLabel();
        lblSharePhotoProgress = new JLabel();
        lblFilteredImageSaveProgress = new JLabel();
        lblWarning = new JLabel();
        lblWarning2 = new JLabel();
        txtEmail = new JTextField();
        txtPassword = new JTextField();

        //RadioButtonların sadece biri seçilecek şekilde olması için ButtonGroup'a göre yazılan kodlar
        btnGroupFilterList.add(rbnGrayScaleEffect);
        btnGroupFilterList.add(rbnFlipEffect);
        btnGroupFilterList.add(rbnBrightenEffect);
        btnGroupFilterList.add(rbnDarkenEffect);
        btnGroupFilterList.add(rbnBlurEffect);
        btnGroupFilterList.add(rbnNonFilterMode);

        //Sırasıyla kapatma düğmesi, uygulama bağlığı ve cursor ayarı
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Photo Editor");
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

        //Standart Buttonlar için yapılan görünen yazı, yardımcı yazı ve actionListener sınıfı tanımları
        btnPhoto.setText("Fotoğraf Çek");
        btnPhoto.setToolTipText("Fotoğraf çekmek ve kaydetmek için tıklayın.");
        btnPhoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhotoActionPerformed(evt);
            }
        });
        btnSocialMedia.setText("Paylaş");
        btnSocialMedia.setToolTipText("Bağlanan Facebook hesabında fotoğraf paylaşmak için tıklayın.");
        btnSocialMedia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSocialMediaActionPerformed(evt);
            }
        });
        btnFilteredImageSave.setText("Kaydet");
        btnFilteredImageSave.setToolTipText("Filtre uygulanmış fotoğrafı kaydetmek için tıklayın.");
        btnFilteredImageSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilteredImageSaveActionPerformed(evt);
            }
        });

        //Labellar için yapılan görünen yazı tanımları
        lblFilterTitle.setText("Filtre Seçenekleri");
        lblSocialMediaTitle.setText("Facebook'ta Paylaş");
        lblFilteredImageSave.setText("Filtreli Fotoğrafı Kaydet");
        lblWarning.setText("UYARI: Bu program, Türkçe karakter içermeyen bir dizinde çalıştırılmalıdır!");
        lblWarning2.setText("UYARI: Programın düzgün çalışması için Chrome tarayıcınızın güncel olması gerekir!");
        lblTakePhotoProgress.setText("");
        lblSharePhotoProgress.setText("");
        lblFilteredImageSaveProgress.setText("");

        //RadioButtonlar için yapılan görünen yazı ve actionListener sınıfı tanımları
        rbnGrayScaleEffect.setText("Siyah - Beyaz");
        rbnGrayScaleEffect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnGrayScaleEffectActionPerformed(evt);
            }
        });
        rbnFlipEffect.setText("Döndür");
        rbnFlipEffect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnFlipEffectActionPerformed(evt);
            }
        });
        rbnBrightenEffect.setText("Parlaklık Artır");
        rbnBrightenEffect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnBrightenEffectActionPerformed(evt);
            }
        });
        rbnDarkenEffect.setText("Parlaklık Azalt");
        rbnDarkenEffect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnDarkenEffectActionPerformed(evt);
            }
        });
        rbnBlurEffect.setText("Bulanıklaştır");
        rbnBlurEffect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnBlurEffectActionPerformed(evt);
            }
        });
        rbnNonFilterMode.setText("Filtresiz Mod");
        rbnNonFilterMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnNonFilterModeActionPerformed(evt);
            }
        });

        txtEmail.setText("Email");
        txtPassword.setText("Şifre");

        //Layout tanımlaması ve bileşenlerin layout üzerindeki konumlarının ayarlanması
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(lblWarning, GroupLayout.PREFERRED_SIZE, 407, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(lblWarning2, GroupLayout.PREFERRED_SIZE, 455, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(49, 49, 49)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(lblImage, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                                                        .addComponent(btnPhoto, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                                                        .addComponent(lblTakePhotoProgress, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(80, 80, 80)
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(lblSocialMediaTitle, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                                                                        .addComponent(lblSharePhotoProgress, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addGap(84, 84, 84))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(40, 40, 40)
                                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                                                .addComponent(lblFilterTitle, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(48, 48, 48))
                                                                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                                        .addComponent(rbnBrightenEffect, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                                                                                        .addComponent(rbnGrayScaleEffect, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                                                                                        .addComponent(rbnBlurEffect, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(18, 18, 18)
                                                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                                        .addComponent(rbnNonFilterMode, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                                                                                        .addComponent(rbnFlipEffect, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                                                                                        .addComponent(rbnDarkenEffect, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)))
                                                                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                                .addComponent(lblFilteredImageSaveProgress, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
                                                                                                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                                                                                .addComponent(lblFilteredImageSave, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                                .addComponent(btnFilteredImageSave, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
                                                                                                        .addGap(49, 49, 49)))))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(107, 107, 107)
                                                                                .addComponent(btnSocialMedia))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(22, 22, 22)
                                                                                .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(0, 0, Short.MAX_VALUE)))))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblImage, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblFilterTitle, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(rbnGrayScaleEffect)
                                                        .addComponent(rbnFlipEffect))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(rbnDarkenEffect)
                                                        .addComponent(rbnBrightenEffect))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(rbnNonFilterMode)
                                                        .addComponent(rbnBlurEffect))
                                                .addGap(18, 18, 18)
                                                .addComponent(lblFilteredImageSave)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnFilteredImageSave, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(btnPhoto, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblFilteredImageSaveProgress)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lblSocialMediaTitle)
                                                .addGap(9, 9, 9)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblTakePhotoProgress)
                                        .addComponent(btnSocialMedia))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                                .addComponent(lblSharePhotoProgress)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                                .addComponent(lblWarning)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblWarning2)
                                .addContainerGap())
        );
        pack();
    }

    //Standart Buttonlar için tanımlanan actionListener sınıfından ActionPerformed metodu tanımları
    private void btnPhotoActionPerformed(java.awt.event.ActionEvent evt) {
        lblTakePhotoProgress.setText("Fotoğraf çekme işlemi başlatıldı.");
        TakePicture takePicture = new TakePicture();
        baseFilePath = takePicture.capturePhoto(lblTakePhotoProgress);
        imageIcon2 = resizeImageIcon(new ImageIcon(baseFilePath), 200, 200);
        lblImage.setIcon(imageIcon2);
    }
    private void btnFilteredImageSaveActionPerformed(java.awt.event.ActionEvent evt) {
        ImageEffects imageEffects = new ImageEffects();
        try{
            filePath = imageEffects.chooseFile(filePath, lblFilteredImageSaveProgress);
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane,"Filtre uygulanırken bir hata oluştu","Hata", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void btnSocialMediaActionPerformed(java.awt.event.ActionEvent evt) {
        lblSharePhotoProgress.setText("Fotoğraf Paylaşma işlemi başlatıldı.");
        FacebookPhotoUploader facebookPhotoUploader = new FacebookPhotoUploader();
        String emailFromUser = txtEmail.getText();
        String passwordFromUser = txtPassword.getText();
        try {
            facebookPhotoUploader.uploadPhotoToFacebook(filePath, lblSharePhotoProgress, emailFromUser, passwordFromUser);
        } catch (InterruptedException e) {
            lblSharePhotoProgress.setText("Fotoğraf Paylaşma işlemi başarısız.");
            JOptionPane.showMessageDialog(rootPane, "Sosyal medyada fotoğraf paylaşılamadı. Lütfen tekrar deneyin!", "Hata", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    //RadioButtonlar için tanımlanan actionListener sınıfından ActionPerformed metodu tanımları
    private void rbnGrayScaleEffectActionPerformed(java.awt.event.ActionEvent evt) {
        ImageEffects imageEffects = new ImageEffects();
        filePath = imageEffects.effectImage(baseFilePath, tmpFilePath, lblImage, 1);
    }
    private void rbnFlipEffectActionPerformed(java.awt.event.ActionEvent evt) {
        ImageEffects imageEffects = new ImageEffects();
        filePath = imageEffects.effectImage(baseFilePath, tmpFilePath, lblImage, 2);
    }
    private void rbnBrightenEffectActionPerformed(java.awt.event.ActionEvent evt) {
        ImageEffects imageEffects = new ImageEffects();
        filePath = imageEffects.effectImage(baseFilePath, tmpFilePath, lblImage, 3);
    }
    private void rbnDarkenEffectActionPerformed(java.awt.event.ActionEvent evt) {
        ImageEffects imageEffects = new ImageEffects();
        filePath = imageEffects.effectImage(baseFilePath, tmpFilePath, lblImage, 4);
    }
    private void rbnBlurEffectActionPerformed(java.awt.event.ActionEvent evt) {
        ImageEffects imageEffects = new ImageEffects();
        filePath = imageEffects.effectImage(baseFilePath, tmpFilePath, lblImage, 5);
    }
    private void rbnNonFilterModeActionPerformed(java.awt.event.ActionEvent evt) {
        ImageEffects imageEffects = new ImageEffects();
        filePath = imageEffects.effectImage(baseFilePath, tmpFilePath, lblImage, 6);
    }
    
    //Resmi yeniden boyutlandırmaya yarayan metot
    public static ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    public static void main(String[] args) {
        //Geçici işlemlerin yürütülmesi için temp klasörü oluşturulur.
        //temp klasörünün yolu
        String tmpFilePath = System.getProperty("user.dir") + File.separator + "temp";
        
        //Klasörü oluştur
        File tmpFile = new File(tmpFilePath);
        if (!tmpFile.exists()) {
            if (tmpFile.mkdir()) {
                System.out.println("temp klasörü oluşturuldu.");
            } else {
                System.out.println("temp klasörü oluşturulamadı.");
            }
        }
        
        //Kapatma kancası (Shutdown Hook) oluşturma
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (tmpFile.exists()) {
                try {
                    //Çalışma sonrası geçici klasör silme
                    FileUtils.deleteDirectory(tmpFile);
                    System.out.println("temp klasörü silindi.");
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null,"Program sonlandırılırken bir hata oluştu","Hata", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(e);
                }
            }
        }));
        
        //Arayüz görünümü ayarlaması
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        
        //Arayüzü oluştur ve görünümü aktif et
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    //Bileşenlerin başlangıç tanımları - lütfen değiştirmeyin
    private JButton btnFilteredImageSave;
    private ButtonGroup btnGroupFilterList;
    private JButton btnPhoto;
    private JButton btnSocialMedia;
    private JLabel lblFilterTitle;
    private JLabel lblFilteredImageSave;
    public JLabel lblFilteredImageSaveProgress;
    public JLabel lblImage;
    public JLabel lblSharePhotoProgress;
    private JLabel lblSocialMediaTitle;
    public JLabel lblTakePhotoProgress;
    private JLabel lblWarning;
    private JLabel lblWarning2;
    private JRadioButton rbnBlurEffect;
    private JRadioButton rbnBrightenEffect;
    private JRadioButton rbnDarkenEffect;
    private JRadioButton rbnFlipEffect;
    private JRadioButton rbnGrayScaleEffect;
    private JRadioButton rbnNonFilterMode;
    private JTextField txtPassword;
    private JTextField txtEmail;
    private ImageIcon imageIcon;
    private ImageIcon imageIcon2;
    private String baseFilePath = "";
    private String filePath = "";
    private final String tmpFilePath = System.getProperty("user.dir") + File.separator + "temp";
}