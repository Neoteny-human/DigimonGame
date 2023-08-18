package action;

public class playtimeThread extends Thread{
    public static int playtime;
    int alert;

    public void run(){
        while(true){
            try{
                Thread.sleep(1000);
            }
            catch (InterruptedException e){
                break;
            }
            playtime += 1;
            alert += 1;
            if(alert == 30){
                alert = 0;
                System.out.println("게임 이용시간이 "+playtime/3600+ "시간 "+playtime/60+"분 " +playtime%60+"초 경과하였습니다.\n" +
                        "지나친 게임이용은 건강을 해칠 수 있으니 유의하여 주시기 바랍니다.");
            }
        }
    }
}





