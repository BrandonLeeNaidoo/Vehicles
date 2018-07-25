public class Main
{
    public static void main(String [] args)
    {
        Vehicles_Processing vpObj = new Vehicles_Processing();

        //TODO: Schalk pass the values through parameter for each vehicle
        String jsonStr = vpObj.buildJsonString("", "", "", "", "");
        vpObj.postVehicle(jsonStr);
    }
}
