package org.photoeditor;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
class BlurEffect extends ImageProcessor {
    public BlurEffect(Mat mImage) {
        //Abstract sınıfta yapılan constructor işlemleri burada super() metoduyla tekrar yapılır.
        super(mImage);
    }
    
    //Efektin uygulandığı metot
    @Override
    public void applyEffect() {
        Imgproc.GaussianBlur(mImage, mImage, new Size(15, 15), 0);
    }
}