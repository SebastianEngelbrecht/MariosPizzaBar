public class Mathf {
    public static float Clamp(float input, float min, float max){
        float result = input;

        if(input < min)
            result = min;
        else if(input > max)
            result = max;

        return result;
    }
}
