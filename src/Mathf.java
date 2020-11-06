public class Mathf {
    //region Clamp
    public static float Clamp(float input, float min, float max){
        float result = input;

        if(input < min)
            result = min;
        else if(input > max)
            result = max;

        return result;
    }

    public static int Clamp(int input, int min, int max){
        int result = input;

        if(input < min)
            result = min;
        else if(input > max)
            result = max;

        return result;
    }
    //endregion
}
