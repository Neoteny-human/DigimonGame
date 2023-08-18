package action;

public class timerThread extends Thread{
    public static int second;

    public timerThread(int second){

        this.second = second;
    }


    public void run(){
        while(true){
            try{
                Thread.sleep(1000);
            }
            catch(Exception e){
                e.printStackTrace();
            }

            if(second > 0){
                second -= 1;
            } else {
                break;
            }
        }

    }

    public static int getSecond() {
        return second;
    }

    public static void setSecond(int second) {
        timerThread.second = second;
    }
}

