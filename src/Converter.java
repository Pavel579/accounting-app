public class Converter {

    public double convertStepsToDistance(int steps){
        return steps*0.75/100000;
    }

    public double convertStepsToKkal(int steps){
        return steps*50/1000d;
    }


}
