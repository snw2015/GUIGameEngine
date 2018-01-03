package snw.engine.animation;

import java.awt.geom.AffineTransform;

public class AnimationData {
    public AffineTransform getTransformation() {
        return transformation;
    }

    public void setTransformation(AffineTransform transformation) {
        this.transformation = transformation;
    }

    public int getAlphaInt() {
        return alphaInt;
    }

    public void setAlphaInt(int alphaInt) {
        if (alphaInt < 0) {
            alphaInt = 0;
        }
        this.alphaInt = alphaInt;
        this.alphaFloat = (float) alphaInt / 255;
    }

    public float getAlphaFloat() {
        return alphaFloat;
    }

    public void setAlphaFloat(float alphaFloat) {
        if (alphaFloat < 0) {
            alphaFloat = 0;
        }
        this.alphaFloat = alphaFloat;
        this.alphaInt = (int) (alphaFloat * 255 + 0.5f);
    }

    private AffineTransform transformation;
    private int alphaInt;
    private float alphaFloat;

    public AnimationData(String rawData) {
        String[] matrix = rawData.split(",");

        transformation = new AffineTransform(Double.valueOf(matrix[0]),
                Double.valueOf(matrix[1]), Double.valueOf(matrix[2]),
                Double.valueOf(matrix[3]), Double.valueOf(matrix[4]),
                Double.valueOf(matrix[5]));
        setAlphaInt(Integer.valueOf(matrix[6]));
    }

    public AnimationData(AffineTransform transformation, int alpha) {
        this.transformation = transformation;
        setAlphaInt(alpha);
    }

    public AnimationData(AffineTransform transformation) {
        this.transformation = transformation;
        setAlphaInt(255);
    }

    public AnimationData add(AnimationData other) {
        AffineTransform newTransformation = new AffineTransform(this.transformation);
        newTransformation.concatenate(other.transformation);

        AnimationData newData = new AnimationData(newTransformation);
        newData.setAlphaFloat(this.alphaFloat * other.alphaFloat);

        return newData;
    }

    public AnimationData preAdd(AnimationData other) {
        AffineTransform newTransformation = new AffineTransform(this.transformation);
        newTransformation.preConcatenate(other.transformation);

        AnimationData newData = new AnimationData(newTransformation);
        newData.setAlphaFloat(this.alphaFloat * other.alphaFloat);

        return newData;
    }

    public void transform(AnimationData other) {
        this.transformation.concatenate(other.transformation);
        setAlphaFloat(alphaFloat * other.alphaFloat);
    }

    public void preTransform(AnimationData other) {
        this.transformation.preConcatenate(other.transformation);
        setAlphaFloat(alphaFloat * other.alphaFloat);
    }


    public String toString() {
        return (transformation.toString() + "," + alphaInt);
    }
}
