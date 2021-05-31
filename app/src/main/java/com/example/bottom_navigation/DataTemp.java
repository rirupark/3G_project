package com.example.bottom_navigation;

public class DataTemp {
        private static final DataTemp dataTemp = new DataTemp();
        public static DataTemp getInstance(){
            return dataTemp;
        }
        String tempString;
        int tempInt;
    public String getTempString() {
        return tempString;
    }

    public void setTempString(String tempString) {
        this.tempString = tempString;
    }

    public void setTempInt(int tempInt) {
        this.tempInt = tempInt;
    }

    public int getTempInt() {
        return tempInt;
    }
}
