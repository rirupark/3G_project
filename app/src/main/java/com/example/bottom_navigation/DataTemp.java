package com.example.bottom_navigation;

public class DataTemp {
        private static final DataTemp dataTemp = new DataTemp();
        public static DataTemp getInstance(){
            return dataTemp;
        }
        String tempString;

    public String getTempString() {
        return tempString;
    }

    public void setTempString(String tempString) {
        this.tempString = tempString;
    }
}
