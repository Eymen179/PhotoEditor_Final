package org.photoeditor;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
class DarkenEffect extends ImageProcessor {
    public DarkenEffect(Mat mImage) {
        //Abstract sınıfta yapılan constructor işlemleri burada super() metoduyla tekrar yapılır.
        super(mImage);
    }
    
    //Efektin uygulandığı metot
    @Override
    public void applyEffect() {
        Core.add(mImage, new Scalar(-50, -50, -50), mImage);
    }
}