package ex2;

import java.util.*;

public class Flight {
    private String code;
    private Plane plane;
    private int[][] seatChart;
    private int resNum = 1;
    private ArrayList<String> reservations = new ArrayList<>();

    public Flight(String code, Plane plane){
        this.code = code;
        this.plane = plane;
        int rows = Math.max(plane.getSeatDimensionsE()[0], plane.getSeatDimensionsT()[0]);
        int cols = (plane.getSeatDimensionsE()[1]+ plane.getSeatDimensionsT()[1]);
        this.seatChart = new int[rows][cols];
    }

    public int getResNum(){
        return resNum;
    }

    public boolean makeReservation(int seatNum, boolean isExec) {
        if (isExec) {
            // Verificar se existem lugares suficientes
            if (getFreeSeatsE() < seatNum) return false;
            // Procura por filas seguidas
            boolean clearRow = false;
            int i;
            for (i = 0; i < plane.getSeatDimensionsE()[1]; i++) {
                clearRow = true;
                for (int j = 0; j < plane.getSeatDimensionsE()[0]; j++) {
                    if (!(seatChart[j][i] == 0)) clearRow = false;
                }
                if(clearRow) {
                    for (int j = 0; j < Math.min(plane.getSeatDimensionsE()[0],seatNum); j++) {
                        seatChart[j][i] = resNum;
                    }
                    break;
                }
            }
            if(clearRow) {
                int left = seatNum-plane.getSeatDimensionsE()[0];
                for(int k = 0; k < left;) {
                    i = ((++i)%plane.getSeatDimensionsE()[1]);
                    for (int j = 0; j < plane.getSeatDimensionsE()[0]; j++) {
                        if (seatChart[j][i] == 0) {
                            seatChart[j][i] = resNum;
                            k++;
                            if(k == left) break;
                        }
                    }
                }
            }
            else { // Dispor "como der"
                for(int k = 0; k < seatNum;) {
                    for (i = 0; i < plane.getSeatDimensionsE()[1]; i++) {
                        for (int j = 0; j < plane.getSeatDimensionsE()[0]; j++) {
                            if (seatChart[j][i] == 0) {
                                seatChart[j][i] = resNum;
                                k++;
                                if(k == seatNum) break;
                            }
                        }
                        if(k == seatNum) break;
                    }
                }
            }
        }
        else {
            // Verificar se existem lugares suficientes
            if (getFreeSeatsT() < seatNum) return false;
            // Procura por filas seguidas
            boolean clearRow = false;
            int i;
            for(i = plane.getSeatDimensionsE()[1]; i < plane.getSeatDimensionsT()[1]+plane.getSeatDimensionsE()[1]; i++) {
                clearRow = true;
                for (int j = 0; j < plane.getSeatDimensionsT()[0]; j++) {
                    if (!(seatChart[j][i] == 0)) clearRow = false;
                }
                if(clearRow) {
                    for (int j = 0; j < Math.min(plane.getSeatDimensionsT()[0],seatNum); j++) {
                        seatChart[j][i] = resNum;
                    }
                    break;
                }
            }
            if(clearRow) {
                int l = i - plane.getSeatDimensionsE()[1];
                int left = seatNum-plane.getSeatDimensionsT()[0];
                for(int k = 0; k < left;) {
                    i = ((l++)%plane.getSeatDimensionsT()[1]);
                    for (int j = 0; j < plane.getSeatDimensionsT()[0]; j++) {
                        if (seatChart[j][i] == 0) {
                            seatChart[j][i] = resNum;
                            k++;
                            if(k == left) break;
                        }
                    }
                }
            }
            else { // Dispor "como der"
                for(int k = 0; k < seatNum;) {
                    for (i = plane.getSeatDimensionsE()[1]; i < plane.getSeatDimensionsT()[1] + plane.getSeatDimensionsE()[1]; i++) {
                        for (int j = 0; j < plane.getSeatDimensionsT()[0]; j++) {
                            if (seatChart[j][i] == 0) {
                                seatChart[j][i] = resNum;
                                k++;
                                if(k == seatNum) break;
                            }
                        }
                        if(k == seatNum) break;
                    }
                }
            }
        }
        String tmp=this.code + ":" + resNum;
        this.reservations.add(tmp);
        resNum++;
        return true;
    }

    public void deleteReservation(int resNum){
        boolean toRemove=false;
        for(String res : this.reservations){
            String[] tmp = res.split(":");
            if (Integer.parseInt(tmp[1])==resNum){
                 toRemove=true;
            }
        }
        for(int[] row : seatChart){
            for (int seat : row) {
                if (seat == resNum) {
                    seat = 0;
                }
            }
        }
        if (toRemove)
        this.reservations.remove(resNum);
    }

    public ArrayList<String> getReservations(){
        return reservations;
    }

    public int getFreeSeatsE() {
        int ret = 0;
        for(int i = 0; i < plane.getSeatDimensionsE()[1]; i++) {
            for(int j = 0; j < plane.getSeatDimensionsE()[0]; j++) {
                if(seatChart[j][i] == 0) ret++;
            }
        }
        return ret;
    }

    public int getFreeSeatsT() {
        int ret = 0;
        for(int i = plane.getSeatDimensionsE()[1]; i < plane.getSeatDimensionsT()[1]+plane.getSeatDimensionsE()[1]; i++) {
            for(int j = 0; j < plane.getSeatDimensionsT()[0]; j++) {
                if(seatChart[j][i] == 0) ret++;
            }
        }
        return ret;
    }

    public String toString(){
        return String.format("\b\bCode: %10s Executive Seats: %5s Tourist Seats: %5s \n",code,plane.getNumSeatsE(),plane.getNumSeatsT());
    }

    public void printChart() {
        System.out.print("   ");
        for( int i=1;i<seatChart[0].length+1;i++){
            System.out.print(i+"  ");
        }
        System.out.println();
        char toPrint='A';
        for (int i = 0; i < seatChart.length; i++) {
            for (int j = 0; j < seatChart[i].length; j++) {
                if (j == 0){
                    System.out.print((char)(toPrint+i )+ "  ");  // To print the letters
                }
                int execCols=this.plane.getSeatDimensionsE()[1];
                int execLine=this.plane.getSeatDimensionsE()[0];
                int travCols=this.plane.getSeatDimensionsT()[1];
                int travLine=this.plane.getSeatDimensionsT()[0];

                //  System.out.println("Debug: "+execCols+" "+ execLine+" "+travCols+" "+travLine );
                if ( ( (j<this.plane.getSeatDimensionsE()[1]) && (i+1>this.plane.getSeatDimensionsE()[0]))  ||
                        ( (j+this.plane.getSeatDimensionsE()[1] < this.plane.getSeatDimensionsT()[1]+this.plane.getSeatDimensionsE()[1]) &&
                                (  i+1 > this.plane.getSeatDimensionsT()[0]))){
                    System.out.print("   ");             // Executive row > Tourist row and needs padding
                } else {
                    if(j<9) System.out.print(seatChart[i][j] + "  ");
                    else if(j<99) System.out.print(seatChart[i][j] + "   ");
                    else System.out.print(seatChart[i][j] + "    ");
                }
            }
            System.out.print("\n");
        }
    }

    public String getFlightCode() {
        return code;
    }

    public Plane getPlane() {
        return plane;
    }

}
