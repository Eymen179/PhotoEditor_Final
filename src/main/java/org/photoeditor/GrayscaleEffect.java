package org.photoeditor;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
class GrayscaleEffect extends ImageProcessor {
    public GrayscaleEffect(Mat mImage) {
        //Abstract sınıfta yapılan constructor işlemleri burada super() metoduyla tekrar yapılır.
        super(mImage);
    }
    
    //Efektin uygulandığı metot
    @Override
    public void applyEffect() {
        Imgproc.cvtColor(mImage, mImage, Imgproc.COLOR_BGR2GRAY);
    }
}