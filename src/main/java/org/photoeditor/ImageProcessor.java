package org.photoeditor;

import org.opencv.core.Mat;
abstract class ImageProcessor {
    //Efekt uygulanan sınıflarda kullanılması için bir matris tanımı
    protected Mat mImage;
    //Efekt uygulanmış matris, constructor üzerinden güncellenir.
    public ImageProcessor(Mat mImage) {
        this.mImage = mImage;
    }
    //Diğer efekt sınıflarında içi doldurulacak olan abstract metot
    public abstract void applyEffect();
}