package org.photoeditor;

import org.opencv.core.Core;
import org.opencv.core.Mat;
class FlipEffect extends ImageProcessor {
    public FlipEffect(Mat mImage) {
        //Abstract sınıfta yapılan constructor işlemleri burada super() metoduyla tekrar yapılır.
        super(mImage);
    }
    
    //Efektin uygulandığı metot
    @Override
    public void applyEffect() {
        Core.flip(mImage, mImage, 1);
    }
}