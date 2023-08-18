import NPC.chief;
import NPC.merchant.fruitMerchant;
import NPC.merchant.hpMerchant;
import NPC.merchant.spMerchant;
import NPC.part;
import NPC.veter;
import action.*;
import character.*;
import item.fruit;
import item.hpPotion;
import item.spPotion;

import java.util.*;

import static action.homeThread.getChoice;
import static action.playtimeThread.playtime;
import static action.shopThread.*;


public class main {
    public static void main(String[] args) {



        List a = Collections.emptyList();
        int count;
        Scanner sc = new Scanner(System.in);
        int shopOption;
        int itemOption;
        int fightOption;
        boolean canFight = false;
        int partnerOption;
        int trainOption;
        boolean fromHome = true;
        int currentX =0;
        int currentY =0;
        boolean reHunting = false; //사냥 후 다시 사냥을 하는 것인지 파악해서 플레이어의 위치를 지정하는데 사용.
        int choice = 0;

        //모든 thread에서 공유하도록 회복실 3개 생성.
        partner[] hosPartners = new partner[3];








//        파트너 생성.........................................................................
//        아구몬 생성.
        ArrayList<coskill>[] agucoskill = new ArrayList[5];
        for (int i = 0; i < 5; i++) {
            agucoskill[i] = new ArrayList<>();
        }
        ArrayList<buffskill> agubuff = new ArrayList();

        agucoskill[0].add(new coskill("깨어나기", 0, 1.2, 1));
        agucoskill[1].add(new coskill("베이비 플레임", 10, 1.2, 1));
        agucoskill[1].add(new coskill("스핏파이어", 10, 0.7, 2));
        agucoskill[2].add(new coskill("메가 플레임", 20, 1.7, 1));
        agucoskill[2].add(new coskill("비트 파이어", 20, 1.0, 2));
        agucoskill[3].add(new coskill("기가 디스트로이어", 30, 2.0, 1));
        agucoskill[3].add(new coskill("트라이던드 암", 30, 1.0, 3));
        agucoskill[4].add(new coskill("가이아 포스", 40, 3.0, 1));
        agucoskill[4].add(new coskill("브레이브 토네이도", 40, 0.7, 6));
        agubuff.add(new buffskill("드라몬 킬러", 10, 0, 30, 0));

        partner agu = new partner("아구몬의 알", "아구몬", "그레이몬", "메탈그레이몬", "워그레이몬",
                agucoskill,
                "Va", 1000, 1000, 0, 100, agubuff);


        //파피몬 생성.
        ArrayList<coskill>[] papicoskill = new ArrayList[5];
        for (int i = 0; i < 5; i++) {
            papicoskill[i] = new ArrayList<>();
        }
        ArrayList<buffskill> papibuff = new ArrayList();

        papicoskill[0].add(new coskill("깨어나기", 0, 1.2, 1));
        papicoskill[1].add(new coskill("드릴 혼", 10, 1.2, 1));
        papicoskill[1].add(new coskill("파피몬 샷", 10, 0.7, 2));
        papicoskill[2].add(new coskill("프리즈 팡", 15, 1.7, 1));
        papicoskill[2].add(new coskill("아이스 캐논", 15, 2.0, 1));
        papicoskill[3].add(new coskill("카이저 네일", 20, 1.5, 2));
        papicoskill[3].add(new coskill("언월차기", 20, 1.0, 3));
        papicoskill[4].add(new coskill("코큐토스 브레스", 25, 4.0, 1));
        papicoskill[4].add(new coskill("메탈 팡", 25, 1, 6));
        papibuff.add(new buffskill("메탈 하울링", 10, 0, 30, 0));

        partner papi = new partner("파피몬의 알", "파피몬", "가루몬", "워가루몬", "메탈가루몬",
                papicoskill,
                "Da", 1000, 1000, 0, 100, papibuff);


        //파닥몬 생성.
        ArrayList<coskill>[] patacoskill = new ArrayList[5];
        for (int i = 0; i < 5; i++) {
            patacoskill[i] = new ArrayList<>();
        }
        ArrayList<buffskill> patabuff = new ArrayList();

        patacoskill[0].add(new coskill("깨어나기", 0, 1.2, 1));
        patacoskill[1].add(new coskill("하네빈타", 10, 2.0, 1));
        patacoskill[1].add(new coskill("공기~팡!", 15, 1.5, 2));
        patacoskill[1].add(new coskill("어쩌고저쩌고", 15, 2.0, 2));
        patacoskill[2].add(new coskill("천사의 손", 20, 3.0, 1));
        patacoskill[2].add(new coskill("홀리 로드", 30, 2.0, 2));
        patacoskill[3].add(new coskill("엑스칼리버", 40, 1.5, 3));
        patacoskill[3].add(new coskill("천국의 문", 60, 4.0, 1));
        patacoskill[4].add(new coskill("세븐 헤븐즈", 80, 5.0, 1));
        patacoskill[4].add(new coskill("테스타멘트", 200, 1, 6));
        patabuff.add(new buffskill("파이널 힐", 10, 1000, 0, 0));

        partner pata = new partner("파닥몬의 알", "파닥몬", "엔젤몬", "홀리엔젤몬", "세라피몬",
                patacoskill,
                "Va", 1000, 1000, 0, 100, patabuff);


        //피요몬 생성.
        ArrayList<coskill>[] piyocoskill = new ArrayList[5];
        for (int i = 0; i < 5; i++) {
            piyocoskill[i] = new ArrayList<>();
        }
        ArrayList<buffskill> piyobuff = new ArrayList();

        piyocoskill[0].add(new coskill("깨어나기", 0, 1.2, 1));
        piyocoskill[1].add(new coskill("매지컬 파이어", 10, 2.0, 1));
        piyocoskill[1].add(new coskill("파이어 플랩!", 15, 1.5, 2));
        piyocoskill[1].add(new coskill("마하 글라이드", 15, 2.0, 2));
        piyocoskill[2].add(new coskill("버-플레임", 20, 3.0, 1));
        piyocoskill[2].add(new coskill("섀도우 윙", 30, 2.0, 2));
        piyocoskill[3].add(new coskill("이글 클로", 40, 1.5, 3));
        piyocoskill[3].add(new coskill("스타라이트 익스플로전", 60, 4.0, 1));
        piyocoskill[4].add(new coskill("크림슨 플레어", 80, 5.0, 1));
        piyocoskill[4].add(new coskill("성운폭발", 200, 1, 6));
        piyobuff.add(new buffskill("흩날리는 별빛", 10, 1000, 0, 0));

        partner piyo = new partner("피요몬의 알", "피요몬", "버드라몬", "가루다몬", "페닉스몬",
                piyocoskill,
                "Va", 1000, 1000, 0, 100, piyobuff);


        //필드몬스터 생성...................................................................................
        //에렉몬 생성.
        ArrayList<coskill>[] eleccoskill = new ArrayList[5];
        for (int i = 0; i < 5; i++) {
            eleccoskill[i] = new ArrayList<>();
        }
        ArrayList<buffskill> elecbuff = new ArrayList();

        eleccoskill[0].add(new coskill("깨어나기", 0, 1.2, 1));
        eleccoskill[1].add(new coskill("스파클링 썬더", 10, 1.2, 1));
        eleccoskill[1].add(new coskill("썬더 나이프", 10, 0.7, 2));
        eleccoskill[2].add(new coskill("판처 너클", 20, 1.7, 1));
        eleccoskill[2].add(new coskill("혼 드라이버", 20, 1.0, 2));
        eleccoskill[3].add(new coskill("트라이 혼 어택", 30, 2.0, 1));
        eleccoskill[3].add(new coskill("노크 버스터", 30, 1.0, 3));
        eleccoskill[4].add(new coskill("창뢰", 40, 3.0, 1));
        eleccoskill[4].add(new coskill("풍사화전", 40, 0.7, 6));
        elecbuff.add(new buffskill("뇌신지무", 0, 0, 30, 0));

        fieldmon elec = new fieldmon(1, "에렉몬의 알", "에렉몬", "태스크몬", "트리케라몬", "청룡몬",
                "Vi", 80, 500, 500, eleccoskill, elecbuff, 100, 1000);

        //팔몬 생성.
        ArrayList<coskill>[] palcoskill = new ArrayList[5];
        for (int i = 0; i < 5; i++) {
            palcoskill[i] = new ArrayList<>();
        }
        ArrayList<buffskill> palbuff = new ArrayList();

        palcoskill[0].add(new coskill("깨어나기", 0, 0, 0));
        palcoskill[1].add(new coskill("포이즌 아이비", 10, 1.1, 1));
        palcoskill[1].add(new coskill("지독한 냄새", 10, 0.6, 2));
        palcoskill[2].add(new coskill("수왕권", 20, 1.3, 1));
        palcoskill[2].add(new coskill("사자왕환", 20, 0.7, 2));
        palcoskill[3].add(new coskill("빙수권", 30, 1.6, 1));
        palcoskill[3].add(new coskill("냉기공파참", 30, 0.6, 3));
        palcoskill[4].add(new coskill("레굴루스 커터", 40, 2.0, 1));
        palcoskill[4].add(new coskill("수왕파", 40, 0.5, 6));
        palbuff.add(new buffskill("냉기공", 0, 30, 0, 0));

        fieldmon pal = new fieldmon(2, "팔몬의 알", "팔몬", "레오몬", "화이트레오몬", "레굴루몬",
                "Da", 80, 500, 500, palcoskill, palbuff, 100, 800);




        //필드몬스터들 생성 후 모두 리스트에 저장.
        ArrayList<fieldmon> fieldmons = new ArrayList<>();
        fieldmons.add(elec);
        fieldmons.add(pal);


        //보스생성.......................................................................................
        //1. 데블몬 생성
        ArrayList<coskill> devilcoskill = new ArrayList();
        ArrayList<buffskill> devilbuff = new ArrayList();
        devilcoskill.add(new coskill("악마의 손길", 20, 1.5, 2));
        devilcoskill.add(new coskill("데들리 네일", 20, 2.0, 1));
        devilbuff.add(new buffskill("데몬즈 레이", 10, 0, 30, 0));

        bossmon devil = new bossmon(1, "데블몬", "Va", 2, 100, 1000, 1000,
                500, 2000, devilcoskill, devilbuff, "선택받은 아이들이여! \n한 명도 남기지 않고 피의 제물로 삼도록 해주마!"
                , "어리석군...정말 어리석기 짝이 없어. \n이런 데서 힘을 다 써버리면 어쩔 거야. \n어둠의 힘은 이미 바다 저편까지 퍼져 있지. \n" +
                "그곳엔 나보다도 훨씬 더 강한 디지몬이 있단 말이야. \n끝장이야..너희들은!");


        //2. 에테몬 생성
        ArrayList<coskill> etecoskill = new ArrayList();
        ArrayList<buffskill> etebuff = new ArrayList();
        etecoskill.add(new coskill("러브 세레나데", 20, 1.5, 2));
        etecoskill.add(new coskill("원숭이 발톱", 20, 2.0, 1));
        etebuff.add(new buffskill("다크 뮤지컬", 10, 0, 0, 100));

        bossmon ete = new bossmon(2, "에테몬", "Vi", 3, 120, 1500, 1500,
                1500, 3000, etecoskill, etebuff, "내가 바로 디지몬의 왕, 에테몬님이시라네! 예이!"
                , "사라지고 싶지 않아! 난 이 세상의 스타라고!! 스타는 영원한 거란 말이야!!!!!");


        //3. 베놈묘티스몬 생성
        ArrayList<coskill> venomcoskill = new ArrayList();
        ArrayList<buffskill> venombuff = new ArrayList();
        venomcoskill.add(new coskill("베놈 인퓨즈", 20, 2.0, 2));
        venomcoskill.add(new coskill("카오스 플레임", 20, 1.4, 3));
        venombuff.add(new buffskill("인페르노", 10, 0, 30, 0));

        bossmon venom = new bossmon(3, "베놈묘티스몬", "Vi", 4, 140, 1800, 1800,
                2000, 5000, venomcoskill, venombuff, "맨 처음 박쥐떼가 하늘을 뒤덮었다.\n" +
                "곧 이어 수많은 사람들이 좀비 디지몬의 왕 이름을 외쳤다.\n" +
                "이윽고 시간이 악마의 숫자를 새긴 순간\n" +
                "좀비 디지몬의 왕은 무시무시한 악마의 정체를 드러냈다."
                , "...");

        //4. 아포카리몬 생성
        ArrayList<coskill> apocoskill = new ArrayList();
        ArrayList<buffskill> apobuff = new ArrayList();
        apocoskill.add(new coskill("다크니스 존", 30, 2.0, 2));
        apocoskill.add(new coskill("그란데스 빅뱅", 30, 1.4, 3));
        apobuff.add(new buffskill("데스 에볼루션", 20, 0, 30, 0));

        bossmon apo = new bossmon(4, "아포카리몬", "Un", 5, 140, 1800, 1800,
                2000, 5000, apocoskill, apobuff, "우리들이 정말 추해보이는가? 그렇군. 어차피 우린 진화의 과정에서 소멸된 원념일 뿐..."
                , "하하하하하... 너희들은 끝났어! (웃기는 소리하지마!) 어차피 곧 소멸한다. 허나 그냥 죽지는 않아. 너희들, 그리고 이 세상을 모조리 길동무로 삼아주마!");

        ArrayList<bossmon> bossmons = new ArrayList();
        bossmons.add(devil);
        bossmons.add(ete);
        bossmons.add(venom);
        bossmons.add(apo);
        //보스몬스터들 생성 후 모두 리스트에 저장.





        //NPC 생성.............................................................................

        //상점 주인들 생성...........................................
        ArrayList<hpPotion>[] hpPotions = new ArrayList[5];
        for (int i = 0; i < 5; i++) {
            hpPotions[i] = new ArrayList<>();
        }
        hpPotions[1].add(hpPotion1); //각 챕터마다 파는 아이템이 하나씩 증가하도록.
        hpPotions[2].add(hpPotion1);
        hpPotions[2].add(hpPotion2);
        hpPotions[3].add(hpPotion1);
        hpPotions[3].add(hpPotion2);
        hpPotions[3].add(hpPotion3);
        hpPotions[4].add(hpPotion1);
        hpPotions[4].add(hpPotion2);
        hpPotions[4].add(hpPotion3);
        hpPotions[4].add(hpPotion4);
        hpMerchant hpMerchant = new hpMerchant("테오", "원하는 포션은 다 있어.", hpPotions);

        ArrayList<spPotion>[] spPotions = new ArrayList[5];
        for (int i = 0; i < 5; i++) {
            spPotions[i] = new ArrayList<spPotion>();
        }
        spPotions[1].add(spPotion1); //각 챕터마다 판매하는 에이드가 증가하도록.
        spPotions[2].add(spPotion1);
        spPotions[2].add(spPotion2);
        spPotions[3].add(spPotion1);
        spPotions[3].add(spPotion2);
        spPotions[3].add(spPotion3);
        spPotions[4].add(spPotion1);
        spPotions[4].add(spPotion2);
        spPotions[4].add(spPotion3);
        spPotions[4].add(spPotion4);
        spMerchant spMerchant = new spMerchant("에단", "필요한 게 있어?", spPotions);

        ArrayList<fruit>[] fruits = new ArrayList[5];
        for (int i = 0; i < 5; i++) {
            fruits[i] = new ArrayList<fruit>();
        }
        fruits[1].add(fruit1); // 각 챕터마다 판매하는 에이드가 증가하도록.
        fruits[2].add(fruit1);
        fruits[2].add(fruit2);
        fruits[3].add(fruit1);
        fruits[3].add(fruit2);
        fruits[3].add(fruit3);
        fruits[4].add(fruit1);
        fruits[4].add(fruit2);
        fruits[4].add(fruit3);
        fruits[4].add(fruit4);
        fruitMerchant fruitMerchant = new fruitMerchant("소피아", "요즘 열매가 핫해", fruits);

        //훈련사들 생성...............................................................

        //소장들 생성........................
        ArrayList<coskill> firstCo = new ArrayList<>();
        ArrayList<buffskill> firstBuff = new ArrayList<>();
        firstCo.add(new coskill("매지컬 파이어", 10, 1.5, 1));
        firstCo.add(new coskill("쁘띠 썬더", 10, 0.8, 2));
        firstBuff.add(new buffskill("웅크리기", 10, 0, 0, 100));
        chief firstChief = new chief("유아기 소장", "어서오세요. 강소장입니다.", 20, 1.2, firstCo, firstBuff);

        ArrayList<coskill> secondCo = new ArrayList<>();
        ArrayList<buffskill> secondBuff = new ArrayList<>();
        secondCo.add(new coskill("메가 블래스터", 20, 2.0, 1));
        secondCo.add(new coskill("파이어 플랩", 20, 1, 3));
        secondBuff.add(new buffskill("마하 글라이드", 20, 0, 30, 0));
        chief secondChief = new chief("성숙기 소장", "어서오세요. 이소장입니다.", 30, 1.4, secondCo, secondBuff);

        ArrayList<coskill> thirdCo = new ArrayList<>();
        ArrayList<buffskill> thirdBuff = new ArrayList<>();
        thirdCo.add(new coskill("섀도우 윙", 30, 3, 1));
        thirdCo.add(new coskill("페어리 바인", 30, 2, 2));
        thirdBuff.add(new buffskill("템프테이션", 30, 300, 0, 0));
        chief thirdChief = new chief("완전체 소장", "어서오세요. 박소장입니다.", 40, 1.6, thirdCo, thirdBuff);

        ArrayList<coskill> lastCo = new ArrayList<>();
        ArrayList<buffskill> lastBuff = new ArrayList<>();
        lastCo.add(new coskill("손 위프", 40, 6, 1));
        lastCo.add(new coskill("아크틱 블리자드", 40, 3, 2));
        lastBuff.add(new buffskill("버서크 하울", 40, 1000, 0, 0));
        chief lastChief = new chief("궁극체 소장", "어서오세요. 김소장입니다.", 50, 1.8, lastCo, lastBuff);

        //알바 생성!
        part part = new part("강 훈련사", "아 힘들어.", 10, 1.2, 50);

        //수의사 생성!
        veter veter = new veter("오박사", "어서 오너라.", 10);







        playtimeThread playtimeThread = new playtimeThread();
        playtimeThread.start();

        //Timing Thread start!!
//        timingThread timingThread = new timingThread();
//        timingThread.start();
//        loop6:
//        while(true){
//            String A = " ";
//            System.out.println("입력 대기요");
//            A = sc.nextLine();
//            System.out.println(A + "입력 받았어유");
//            if(A == ""){
//                timingThread.interrupt();
//                break;
//            }
//        }
//        //Timing Thread complete.
//
//        try {
//            timingThread.join();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }


        //moveThread를 나누지 않고 main에서 구현할 때
//        loop4:
//        while (true) {
//            Scanner scan = new Scanner(System.in);
//            String c = scan.next();
//            if(!fieldMonMoveThread.isAlive()){
//                break loop4;
//            }
//            switch (c) {
//                case "w":
//                    huntingMap.setMapPoint(you.getY(), you.getX(), null);
//                    if(you.getY() == 0 && you.getX() == 2){
//                        fieldMonMoveThread.interrupt();
//                        break loop4;
//                    }
//                    if(you.getY() != 0) {
//                        you.setY(you.getY() - 1);
//                    }
//                    break;
//                case "s":
//                    huntingMap.setMapPoint(you.getY(), you.getX(), null);
//                    if(you.getY() == huntingMap.getMapY()-1 && you.getX() == 3){
//                        fieldMonMoveThread.interrupt();
//                        break loop4;
//                    }
//                    if(you.getY() != huntingMap.getMapY()-1) {
//                        you.setY(you.getY() + 1);
//                    }
//                    break;
//                case "a":
//                    huntingMap.setMapPoint(you.getY(), you.getX(), null);
//                    if(you.getX() != 0) {
//                        you.setX(you.getX() - 1);
//                    }
//                    break;
//                case "d":
//                    huntingMap.setMapPoint(you.getY(), you.getX(), null);
//                    if(you.getX() != huntingMap.getMapX()-1) {
//                        you.setX(you.getX() + 1);
//                    }
//                    break;
//            }
//            huntingMap.setMapPoint(you.getY(), you.getX(), you);
//
//        }

//            while(true) {
//
//                if (!fieldMonMoveThread.isAlive()) {
//                    moveThread.interrupt();
//                    break;
//                }
//                if (!moveThread.isAlive()) {
//                    fieldMonMoveThread.interrupt();
//                    break;
//                }
//            }












        //////겜시작!!!
        ArrayList<partner> yourPartner = new ArrayList<>();
        //비어있는 파트너 리스트를 생성.

        ArrayList<hpPotion>[] userHpPotions = new ArrayList[4];
        for (int i = 0; i < 4; i++) {
            userHpPotions[i] = new ArrayList<>();
        }
        ArrayList<spPotion>[] userSpPotions = new ArrayList[4];
        for (int i = 0; i < 4; i++) {
            userSpPotions[i] = new ArrayList<>();
        }
        ArrayList<fruit>[] userFruits = new ArrayList[4];
        for (int i = 0; i < 4; i++) {
            userFruits[i] = new ArrayList<>();
        }
        //비어있는 내 아이템 리스트 생성.

        System.out.println("안녕하세요. 당신의 이름은?");
        player you = new player(sc.next(), 1000, userHpPotions, userSpPotions, userFruits, yourPartner);
        System.out.println("알을 발견했다. 당신은 선택받은 자! 알의 무늬는?\n" +
                "1. 화염무늬 " +
                "2. 세모무늬 " +
                "3. 가로줄무늬 " +
                "4. 하트무늬");
        int egg = sc.nextInt();
        switch (egg) {
            case 1:
                you.getPartner().add(agu);
                you.getPartner().add(papi);
                you.getPartner().add(pata);
                you.getPartner().add(piyo);
                break;
            case 2:
                you.getPartner().add(papi);
                break;
            case 3:
                you.getPartner().add(pata);
                break;
            case 4 :
                you.getPartner().add(piyo);
        }
        System.out.println("알에서 깨어난다!");
        you.getPartner().get(0).upgrade();
        if(egg == 1){
            you.getPartner().get(1).upgrade();
            you.getPartner().get(2).upgrade();
            you.getPartner().get(3).upgrade();
        }

        character.setChapter(1);




        loop1:
        while (true) {
            huntingMap HomeMap = new huntingMap(5,5, 3);
            if(choice == 0 || choice == 5) {
                you.setX(2);
                you.setY(2);
                huntingMap.setMapPoint(2, 2, you);
            }
            else if(choice == 2){
                you.setX(1);
                you.setY(0);
                huntingMap.setMapPoint(0,1, you);
            }
            else if(choice == 6){
                you.setX(3);
                you.setY(0);
                huntingMap.setMapPoint(0,3, you);
            }
            else if(choice == 1){
                you.setX(4);
                you.setY(1);
                huntingMap.setMapPoint(1,4, you);
            }
            else if(choice == 3){
                you.setX(1);
                you.setY(4);
                huntingMap.setMapPoint(4,1, you);
            }
            else if(choice == 4){
                you.setX(3);
                you.setY(4);
                huntingMap.setMapPoint(4,3, you);
            }
            else if(choice == 7) {
                you.setX(4);
                you.setY(3);
                huntingMap.setMapPoint(3,4, you);
            }
            homeThread homeThread = new homeThread(HomeMap, you);
            homeThread.start();
            try {
                homeThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


            if (getChoice() == 1) {
                huntingMap shopMap = new huntingMap(5, 5, 6);
                you.setX(0);
                you.setY(3);
                huntingMap.setMapPoint(3, 0, you);
                shopThread shopThread = new shopThread(shopMap, you, hpMerchant, spMerchant, fruitMerchant);
                shopThread.start();
                try {
                    shopThread.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                choice = 1;
                continue loop1;
            }
//            {
//                loop2:
//                while (true) {
//                    shopOption = shopAction.enter(sc);
//                    switch (shopOption) {
//                        case 1:
//                            int i = shopAction.hp(sc, hpMerchant);
//                            if (i > character.getChapter()) {
//                                System.out.println("아직 없는 아이템 입니다.");
//                                continue loop2;
//                            } else if (i == 0) {
//                                continue loop2;
//                            } else {
//                                System.out.println("몇 개 구입하시겠습니까?");
//                                count = sc.nextInt();
//                                switch (i) {
//                                    case 1:
//                                        you.BuyItem(hpPotion1, count);
//                                        break;
//                                    case 2:
//                                        you.BuyItem(hpPotion2, count);
//                                        break;
//                                    case 3:
//                                        you.BuyItem(hpPotion3, count);
//                                        break;
//                                    case 4:
//                                        you.BuyItem(hpPotion4, count);
//                                        break;
//                                }
//                                break;
//                            }
//
//                        case 2:
//                            i = shopAction.sp(sc, spMerchant);
//                            if (i > character.getChapter()) {
//                                System.out.println("아직 없는 아이템 입니다.");
//                                continue loop2;
//                            } else if (i == 0) {
//                                continue loop2;
//                            } else {
//                                System.out.println("몇 개 구입하시겠습니까?");
//                                count = sc.nextInt();
//                                switch (i) {
//                                    case 1:
//                                        you.BuyItem(spPotion1, count);
//                                        break;
//                                    case 2:
//                                        you.BuyItem(spPotion2, count);
//                                        break;
//                                    case 3:
//                                        you.BuyItem(spPotion3, count);
//                                        break;
//                                    case 4:
//                                        you.BuyItem(spPotion4, count);
//                                        break;
//                                }
//                                break;
//                            }
//                        case 3:
//                            i = shopAction.fruit(sc, fruitMerchant);
//                            if (i > character.getChapter()) {
//                                System.out.println("아직 없는 아이템 입니다.");
//                                continue loop2;
//                            } else if (i == 0) {
//                                continue loop2;
//                            } else {
//                                System.out.println("몇 개 구입하시겠습니까?");
//                                count = sc.nextInt();
//                                switch (i) {
//                                    case 1:
//                                        you.BuyItem(fruit1, count);
//                                        break;
//                                    case 2:
//                                        you.BuyItem(fruit2, count);
//                                        break;
//                                    case 3:
//                                        you.BuyItem(fruit3, count);
//                                        break;
//                                    case 4:
//                                        you.BuyItem(fruit4, count);
//                                        break;
//                                }
//                                break;
//                            }
//                        case 4:
//                            loop3:
//                            while(true) {
//                                System.out.println("어떤 아이템을 판매하시겠습니까?\n" +
//                                        "1. 포션\n" +
//                                        "2. 에이드\n" +
//                                        "3. 열매\n" +
//                                        "0. 뒤로");
//                                shopOption = sc.nextInt();
//                                if(shopOption == 0){
//                                    continue loop2;
//                                }
//                                switch (shopOption) {
//                                    case 1:
//                                        for (int l = 0; l < 4; l++) {
//                                            if (!you.getHpPotions()[l].isEmpty()) {
//                                                System.out.println((l + 1) + ". " + you.getHpPotions()[l].get(0).getName() +
//                                                        " ("+you.getHpPotions()[l].size()+"개)"+
//                                                        " 판매가격: " + (you.getHpPotions()[l].get(0).getPrice() / 2));
//                                            }
//                                        }
//                                        System.out.println("0. 뒤로");
//                                        shopOption = sc.nextInt();
//                                        if(shopOption == 0){
//                                            continue loop3;
//                                        }
//                                        System.out.println("몇 개를 판매하시겠습니까?");
//                                        count = sc.nextInt();
//                                        if (you.getHpPotions()[shopOption-1].size() >= count) {
//                                            you.setMoney(you.getHpPotions()[shopOption-1].get(0).getPrice() * count / 2 + you.getMoney());
//                                            for (int l = 0; l < count; l++) {
//                                                you.getHpPotions()[shopOption-1].remove(0);
//                                            }
//                                            continue loop2;
//                                        } else {
//                                            System.out.println("보유 개수가 부족합니다!");
//                                            continue loop3;
//                                        }
//                                    case 2:
//                                        for (int l = 0; l < 4; l++) {
//                                            if (!you.getSpPotions()[l].isEmpty()) {
//                                                System.out.println((l + 1) + ". " + you.getSpPotions()[l].get(0).getName() +
//                                                        " ("+you.getSpPotions()[l].size()+"개)"+
//                                                        " 판매가격: " + (you.getSpPotions()[l].get(0).getPrice() / 2));
//                                            }
//                                        }
//                                        System.out.println("0. 뒤로");
//                                        shopOption = sc.nextInt();
//                                        if(shopOption == 0){
//                                            continue loop3;
//                                        }
//                                        System.out.println("몇 개를 판매하시겠습니까?");
//                                        count = sc.nextInt();
//                                        if (you.getSpPotions()[shopOption-1].size() >= count) {
//                                            you.setMoney(you.getSpPotions()[shopOption-1].get(0).getPrice() * count / 2 + you.getMoney());
//                                            for (int l = 0; l < count; l++) {
//                                                you.getSpPotions()[shopOption-1].remove(0);
//                                            }
//                                            continue loop2;
//                                        } else {
//                                            System.out.println("보유 개수가 부족합니다!");
//                                            continue loop3;
//                                        }
//                                    case 3:
//                                        for (int l = 0; l < 4; l++) {
//                                            if (!you.getFruits()[l].isEmpty()) {
//                                                System.out.println((l + 1) + ". " + you.getFruits()[l].get(0).getName() +
//                                                        " ("+you.getFruits()[l].size()+"개)"+
//                                                        " 판매가격: " + (you.getFruits()[l].get(0).getPrice() / 2));
//                                            }
//                                        }
//                                        System.out.println("0. 뒤로");
//                                        shopOption = sc.nextInt();
//                                        if(shopOption == 0){
//                                            continue loop3;
//                                        }
//                                        System.out.println("몇 개를 판매하시겠습니까?");
//                                        count = sc.nextInt();
//                                        if (you.getFruits()[shopOption-1].size() >= count) {
//                                            you.setMoney(you.getFruits()[shopOption-1].get(0).getPrice() * count / 2 + you.getMoney());
//                                            for (int l = 0; l < count; l++) {
//                                                you.getFruits()[shopOption-1].remove(0);
//                                            }
//                                            continue loop2;
//                                        } else {
//                                            System.out.println("보유 개수가 부족합니다!");
//                                            continue loop3;
//                                        }
//                                }
//                            }
//                        case 0:
//                            break loop2;
//
//                    }
//
//                }
//            }
            if (getChoice() == 2) {
                    huntingMap trainingMap = new huntingMap(5, 5, 4);
                    you.setX(3);
                    you.setY(4);
                    huntingMap.setMapPoint(4, 3, you);
                    trainingThread trainingThread = new trainingThread(trainingMap, you);
                    trainingThread.start();
                    try {
                        trainingThread.join();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    choice = 2;
                    continue loop1;
            }
//            {
//                int train;
//                loop4:
//                while (true) {
//                    trainOption = trainingAction.enter(sc);
//                    switch (trainOption) {
//                        case 1:
//                            train = trainingAction.chiefEnter(sc, firstChief);
//                            if (train == 0) {
//                                continue loop4;
//                            } else if (train == 2) {
//                                System.out.println("누구를 훈련시킬까?\n" +
//                                        "0. 뒤로");
//                                for (int i = 0; i < you.getPartner().size(); i++) {
//                                    System.out.println((i + 1) + ". " + you.getPartner().get(i).getName());
//                                }
//                                partnerOption = sc.nextInt();
//                                if (partnerOption == 0) {
//                                    continue loop4;
//                                }
//                                if (you.getPartner().get(partnerOption - 1).getGeneration() == 1) {
//                                    trainingAction.skillUp(firstChief, you.getPartner().get(partnerOption - 1), you);
//                                } else {
//                                    System.out.println("성장기 디지몬이 아니야!");
//                                    continue loop4;
//                                }
//                            } else if (train == 1) {
//                                System.out.println("누구를 훈련시킬까?\n" +
//                                        "0. 뒤로");
//                                for (int i = 0; i < you.getPartner().size(); i++) {
//                                    System.out.println((i + 1) + ". " + you.getPartner().get(i).getName());
//                                }
//                                partnerOption = sc.nextInt();
//                                if (partnerOption == 0) {
//                                    continue loop4;
//                                }
//                                if (you.getPartner().get(partnerOption - 1).getGeneration() == 1) {
//                                    trainingAction.LearnSkill(firstChief, you.getPartner().get(partnerOption - 1), you);
//                                } else {
//                                    System.out.println("성장기 디지몬이 아니야!");
//                                    continue loop4;
//                                }
//                            }
//                            break;
//                        case 2:
//                            train = trainingAction.chiefEnter(sc, secondChief);
//                            if (train == 0) {
//                                continue loop4;
//                            } else if (train == 2) {
//                                System.out.println("누구를 훈련시킬까?\n" +
//                                        "0. 뒤로");
//                                for (int i = 0; i < you.getPartner().size(); i++) {
//                                    System.out.println((i + 1) + ". " + you.getPartner().get(i).getName());
//                                }
//                                partnerOption = sc.nextInt();
//                                if (partnerOption == 0) {
//                                    continue loop4;
//                                }
//                                if (you.getPartner().get(partnerOption - 1).getGeneration() == 2) {
//                                    trainingAction.skillUp(secondChief, you.getPartner().get(partnerOption - 1), you);
//                                } else {
//                                    System.out.println("성숙기 디지몬이 아니야!");
//                                    continue loop4;
//                                }
//                            } else if (train == 1) {
//                                System.out.println("누구를 훈련시킬까?\n" +
//                                        "0. 뒤로");
//                                for (int i = 0; i < you.getPartner().size(); i++) {
//                                    System.out.println((i + 1) + ". " + you.getPartner().get(i).getName());
//                                }
//                                partnerOption = sc.nextInt();
//                                if (partnerOption == 0) {
//                                    continue loop4;
//                                }
//                                if (you.getPartner().get(partnerOption - 1).getGeneration() == 2) {
//                                    trainingAction.LearnSkill(secondChief, you.getPartner().get(partnerOption - 1), you);
//                                } else {
//                                    System.out.println("성숙기 디지몬이 아니야!");
//                                    continue loop4;
//                                }
//                            }
//                            break;
//                        case 3:
//                            train = trainingAction.chiefEnter(sc, thirdChief);
//                            if (train == 0) {
//                                continue loop4;
//                            } else if (train == 2) {
//                                System.out.println("누구를 훈련시킬까?\n" +
//                                        "0. 뒤로");
//                                for (int i = 0; i < you.getPartner().size(); i++) {
//                                    System.out.println((i + 1) + ". " + you.getPartner().get(i).getName());
//                                }
//                                partnerOption = sc.nextInt();
//                                if (partnerOption == 0) {
//                                    continue loop4;
//                                }
//                                if (you.getPartner().get(partnerOption - 1).getGeneration() == 2) {
//                                    trainingAction.skillUp(thirdChief, you.getPartner().get(partnerOption - 1), you);
//                                } else {
//                                    System.out.println("완전체 디지몬이 아니야!");
//                                    continue loop4;
//                                }
//                            } else if (train == 1) {
//                                System.out.println("누구를 훈련시킬까?\n" +
//                                        "0. 뒤로");
//                                for (int i = 0; i < you.getPartner().size(); i++) {
//                                    System.out.println((i + 1) + ". " + you.getPartner().get(i).getName());
//                                }
//                                partnerOption = sc.nextInt();
//                                if (partnerOption == 0) {
//                                    continue loop4;
//                                }
//                                if (you.getPartner().get(partnerOption - 1).getGeneration() == 2) {
//                                    trainingAction.LearnSkill(thirdChief, you.getPartner().get(partnerOption - 1), you);
//                                } else {
//                                    System.out.println("완전체 디지몬이 아니야!");
//                                    continue loop4;
//                                }
//                            }
//                            break;
//                        case 4:
//                            train = trainingAction.chiefEnter(sc, lastChief);
//                            if (train == 0) {
//                                continue loop4;
//                            } else if (train == 2) {
//                                System.out.println("누구를 훈련시킬까?\n" +
//                                        "0. 뒤로");
//                                for (int i = 0; i < you.getPartner().size(); i++) {
//                                    System.out.println((i + 1) + ". " + you.getPartner().get(i).getName());
//                                }
//                                partnerOption = sc.nextInt();
//                                if (partnerOption == 0) {
//                                    continue loop4;
//                                }
//                                if (you.getPartner().get(partnerOption - 1).getGeneration() == 2) {
//                                    trainingAction.skillUp(lastChief, you.getPartner().get(partnerOption - 1), you);
//                                } else {
//                                    System.out.println("궁극체 디지몬이 아니야!");
//                                    continue loop4;
//                                }
//                            } else if (train == 1) {
//                                System.out.println("누구를 훈련시킬까?\n" +
//                                        "0. 뒤로");
//                                for (int i = 0; i < you.getPartner().size(); i++) {
//                                    System.out.println((i + 1) + ". " + you.getPartner().get(i).getName());
//                                }
//                                partnerOption = sc.nextInt();
//                                if (partnerOption == 0) {
//                                    continue loop4;
//                                }
//                                if (you.getPartner().get(partnerOption - 1).getGeneration() == 2) {
//                                    trainingAction.LearnSkill(lastChief, you.getPartner().get(partnerOption - 1), you);
//                                } else {
//                                    System.out.println("궁극체 디지몬이 아니야!");
//                                    continue loop4;
//                                }
//                            }
//                            break;
//                        case 5:
//                            loop3:
//                            while(true) {
//                                System.out.println("누구를 훈련시킬까?\n" +
//                                        "0. 뒤로");
//                                for (int i = 0; i < you.getPartner().size(); i++) {
//                                    System.out.println((i + 1) + ". " + you.getPartner().get(i).getName() +
//                                            " 배고픔: "+you.getPartner().get(i).getHungry());
//                                }
//                                partnerOption = sc.nextInt();
//                                if (partnerOption == 0) {
//                                    continue loop4;
//                                }
//
//                                switch (trainingAction.Train(part, you.getPartner().get(partnerOption - 1), you)) {
//                                    case 0:
//                                        for(int i = 0; i < 5; i++) {
//                                            you.getPartner().get(partnerOption - 1).levelUp();
//                                            you.getPartner().get(partnerOption - 1).upgrade();
//                                        }
//                                        //레벨업, 업그레이드 확인
//                                        continue loop4;
//                                    case 1:
//                                        continue loop3;
//                                    case 2:
//                                        continue loop4;
//                                }
//                                break;
//                            }
//                        case 0:
//                            choice = 1;
//                            continue loop1;
//
//                    }
//                }
//
//            }
            if (getChoice() == 3) {
                loop7:
                while(true) {

                    huntingMap FirstMap = new huntingMap(5, 10, elec, 1);
                    if(fromHome) {
                        if(reHunting == false) {
                            you.setX(1);
                            you.setY(0);
                            huntingMap.setMapPoint(0, 1, you);
                        }
                        else{
                            you.setX(currentX);
                            you.setY(currentY);
                            huntingMap.setMapPoint(currentY, currentX, you);
                            reHunting = false;
                        }
                    }
                    else{
                        you.setX(3);
                        you.setY(9);
                        huntingMap.setMapPoint(9, 3, you);
                        fromHome = true;
                    }
                    FirstMap.ShowMap();


                    MoveThread moveThread = new MoveThread(FirstMap, you);
                    fieldMonMoveThread fieldMonMoveThread = new fieldMonMoveThread(FirstMap, elec);



                    fieldMonMoveThread.start();
                    moveThread.start();
                    //1번 맵...
                    loop:
                    while (true) {

                        if (!fieldMonMoveThread.isAlive()) {
                            moveThread.interrupt();
                            currentX = you.getX();
                            currentY = you.getY();
                            fightOption = huntingAction.meet(elec, you);
                            if (fightOption == 0 || fightOption == 2) {
                                choice = 3;
                                System.out.println("초기화면");
                                break loop7;
                            } else if (fightOption == 1) {
                                huntingMap.setMapClear(5,10);
                                reHunting = true;
                                System.out.println("다시 사냥");
                                continue loop7;
                            }

                        }
                        if (!moveThread.isAlive()) {
                            fieldMonMoveThread.interrupt();
                            if(MoveThread.isGoback()){
                                huntingMap.setMapClear(5,10);
                                System.out.println("다음 맵");
                                loop6:
                                while(true) {
                                    huntingMap SecondMap = new huntingMap(5, 10, pal, 2);
                                    MoveThread moveThread1 = new MoveThread(SecondMap, you);
                                    fieldMonMoveThread fieldMonMoveThread1 = new fieldMonMoveThread(SecondMap, pal);
                                    huntingMap.ShowMap();

                                    fieldMonMoveThread1.start();
                                    moveThread1.start();
                                    if(reHunting == false) {
                                        you.setX(1);
                                        you.setY(0);
                                        huntingMap.setMapPoint(0, 1, you);
                                    }
                                    else{
                                        you.setX(currentX);
                                        you.setY(currentY);
                                        huntingMap.setMapPoint(currentY, currentX, you);
                                        reHunting = false;
                                    }
                                    FirstMap.ShowMap();

                                    loop5:
                                    while (true) {
                                        if (!fieldMonMoveThread1.isAlive()) {
                                            moveThread1.interrupt();
                                            currentX = you.getX();
                                            currentY = you.getY();
                                            fightOption = huntingAction.meet(elec, you);
                                            if (fightOption == 0 || fightOption == 2) {
                                                System.out.println("다 죽거나 도망쳐서 초기 화면");
                                                choice = 3;
                                                break loop7;
                                            } else if (fightOption == 1) {
                                                huntingMap.setMapClear(5, 10);
                                                reHunting = true;
                                                System.out.println("다시 사냥");
                                                continue loop6;
                                            }
                                        }
                                        if (!moveThread1.isAlive()) {
                                            fieldMonMoveThread1.interrupt();
                                            fromHome = false;
                                            System.out.println("이전 맵");
                                            continue loop7;
                                        }
                                    }
                                }

                            }
                            else{
                                System.out.println("초기화면으로 되돌아가기");
                                choice = 3;
                                break loop7;
                            }
                        }
                    }
                }
            }
            if (getChoice() == 5) {
                loop3:
                while (true) {
                    switch (character.getChapter()) {
                        case 1:
                            System.out.println("1챕터: 파일섬 편");
                            break;
                        case 2:
                            System.out.println("2챕터: 서버대륙 편");
                            break;
                        case 3:
                            System.out.println("3챕터: 현실세계 편");
                            break;
                        case 4:
                            System.out.println("4챕터: 어둠의 사천왕 편");
                            break;
                    }
                    System.out.println("이름: " + you.getName() + "\n금화: " + you.getMoney() + "개");
                    System.out.println("인벤토리 :");
                    you.showItems();
                    System.out.println("1. 아이템 사용\n" + "2. 파트너 상태창\n" + "3. 파트너 방생\n" + "0. 뒤로");
                    itemOption = sc.nextInt();
                    if (itemOption == 1) {
                        System.out.println("어떤 아이템을 사용할까요?\n1. 포션\n2. 에이드\n3. 열매\n0. 뒤로");
                        itemOption = sc.nextInt();
                        switch (itemOption) {
                            case 1:
                                System.out.println("어떤 포션을 사용할까요?");
                                System.out.println("1. 포션 " + you.getHpPotions()[0].size() + "개 " +
                                        "(" + "hp " + hpPotion1.getHp() + "회복)");
                                System.out.println("2. 슈퍼포션 " + you.getHpPotions()[1].size() + "개 " +
                                        "(" + "hp " + hpPotion2.getHp() + "회복)");
                                System.out.println("3. 하이퍼포션 " + you.getHpPotions()[2].size() + "개 " +
                                        "(" + "hp " + hpPotion3.getHp() + "회복)");
                                System.out.println("4. 맥스포션 " + you.getHpPotions()[3].size() + "개 " +
                                        "(" + "hp " + hpPotion4.getHp() + "회복)");
                                itemOption = sc.nextInt();
                                switch (itemOption) {
                                    case 1:
                                        if (!you.getHpPotions()[0].isEmpty()) {
                                            System.out.println("어느 디지몬에게 사용할까?");
                                            for (int i = 0; i < you.getPartner().size(); i++) {
                                                if (you.getPartner().get(i).getHp() > 0) {
                                                    System.out.println((i + 1) + ". " + you.getPartner().get(i).getName());
                                                }
                                            }
                                            partnerOption = sc.nextInt();
                                            you.useHpPotion(you.getHpPotions()[0].get(0), you.getPartner().get(partnerOption - 1));
                                        } else {
                                            System.out.println("사용할 포션이 없어!");
                                            continue loop3;
                                        }
                                        break;
                                    case 2:
                                        if (!you.getHpPotions()[1].isEmpty()) {
                                            System.out.println("어느 디지몬에게 사용할까?");
                                            for (int i = 0; i < you.getPartner().size(); i++) {
                                                if (you.getPartner().get(i).getHp() > 0) {
                                                    System.out.println((i + 1) + ". " + you.getPartner().get(i).getName());
                                                }
                                            }
                                            partnerOption = sc.nextInt();
                                            you.useHpPotion(you.getHpPotions()[1].get(0), you.getPartner().get(partnerOption - 1));
                                        } else {
                                            System.out.println("사용할 포션이 없어!");
                                            continue loop3;
                                        }
                                        break;
                                    case 3:
                                        if (!you.getHpPotions()[2].isEmpty()) {
                                            System.out.println("어느 디지몬에게 사용할까?");
                                            for (int i = 0; i < you.getPartner().size(); i++) {
                                                if (you.getPartner().get(i).getHp() > 0) {
                                                    System.out.println((i + 1) + ". " + you.getPartner().get(i).getName());
                                                }
                                            }
                                            partnerOption = sc.nextInt();
                                            you.useHpPotion(you.getHpPotions()[2].get(0), you.getPartner().get(partnerOption - 1));
                                        } else {
                                            System.out.println("사용할 포션이 없어!");
                                            continue loop3;
                                        }
                                        break;
                                    case 4:
                                        if (!you.getHpPotions()[3].isEmpty()) {
                                            System.out.println("어느 디지몬에게 사용할까?");
                                            for (int i = 0; i < you.getPartner().size(); i++) {
                                                if (you.getPartner().get(i).getHp() > 0) {
                                                    System.out.println((i + 1) + ". " + you.getPartner().get(i).getName());
                                                }
                                            }
                                            partnerOption = sc.nextInt();
                                            you.useHpPotion(you.getHpPotions()[3].get(0), you.getPartner().get(partnerOption - 1));
                                        } else {
                                            System.out.println("사용할 포션이 없어!");
                                            continue loop3;
                                        }
                                        break;
                                }
                                break;
                            case 2:
                                System.out.println("어떤 에이드를 사용할까요?");
                                System.out.println("1. sp에이드 " + you.getHpPotions()[0].size() + "개 " +
                                        "(" + "스킬 " + spPotion1.getNumber() + "개의 sp를 회복)");
                                System.out.println("2. sp이더 " + you.getHpPotions()[0].size() + "개 " +
                                        "(" + "스킬 " + spPotion2.getNumber() + "개의 sp를 회복)");
                                System.out.println("3. sp엘릭서 " + you.getHpPotions()[0].size() + "개 " +
                                        "(" + "스킬 " + spPotion3.getNumber() + "개의 sp를 회복)");
                                System.out.println("4. sp맥스 " + you.getHpPotions()[0].size() + "개 " +
                                        "(" + "스킬 " + spPotion4.getNumber() + "개의 sp를 회복)");
                                itemOption = sc.nextInt();

                                switch (itemOption) {
                                    case 1:
                                        if (!you.getSpPotions()[0].isEmpty()) {
                                            System.out.println("어느 스킬에 사용할까? 1. 액티브, 2 버프");
                                            itemOption = sc.nextInt();
                                            System.out.println("어느 디지몬에게 사용할까?");
                                            for (int i = 0; i < you.getPartner().size(); i++) {
                                                if (you.getPartner().get(i).getHp() > 0) {
                                                    System.out.println((i + 1) + ". " + you.getPartner().get(i).getName());
                                                }
                                            }
                                            partnerOption = sc.nextInt();

                                            if (itemOption == 1) {
                                                you.useSpPotion(you.getSpPotions()[0].get(0), "co", you.getPartner().get(partnerOption - 1));
                                            } else {
                                                you.useSpPotion(you.getSpPotions()[0].get(0), "buff", you.getPartner().get(partnerOption - 1));
                                            }
                                        } else {
                                            System.out.println("사용할 포션이 없어!");
                                            continue loop3;
                                        }
                                        break;
                                    case 2:
                                        if (!you.getSpPotions()[1].isEmpty()) {
                                            System.out.println("어느 스킬에 사용할까? 1. 액티브, 2 버프");
                                            itemOption = sc.nextInt();
                                            System.out.println("어느 디지몬에게 사용할까?");
                                            for (int i = 0; i < you.getPartner().size(); i++) {
                                                if (you.getPartner().get(i).getHp() > 0) {
                                                    System.out.println((i + 1) + ". " + you.getPartner().get(i).getName());
                                                }
                                            }
                                            partnerOption = sc.nextInt();

                                            if (itemOption == 1) {
                                                you.useSpPotion(you.getSpPotions()[1].get(0), "co", you.getPartner().get(partnerOption - 1));
                                            } else {
                                                you.useSpPotion(you.getSpPotions()[1].get(0), "buff", you.getPartner().get(partnerOption - 1));
                                            }
                                        } else {
                                            System.out.println("사용할 포션이 없어!");
                                            continue loop3;
                                        }
                                        break;
                                    case 3:
                                        if (!you.getSpPotions()[2].isEmpty()) {
                                            System.out.println("어느 스킬에 사용할까? 1. 액티브, 2 버프");
                                            itemOption = sc.nextInt();
                                            System.out.println("어느 디지몬에게 사용할까?");
                                            for (int i = 0; i < you.getPartner().size(); i++) {
                                                if (you.getPartner().get(i).getHp() > 0) {
                                                    System.out.println((i + 1) + ". " + you.getPartner().get(i).getName());
                                                }
                                            }
                                            partnerOption = sc.nextInt();
                                            if (itemOption == 1) {
                                                you.useSpPotion(you.getSpPotions()[2].get(0), "co", you.getPartner().get(partnerOption - 1));
                                            } else {
                                                you.useSpPotion(you.getSpPotions()[2].get(0), "buff", you.getPartner().get(partnerOption - 1));
                                            }
                                        } else {
                                            System.out.println("사용할 포션이 없어!");
                                            continue loop3;
                                        }
                                        break;
                                    case 4:
                                        if (!you.getSpPotions()[3].isEmpty()) {
                                            System.out.println("어느 스킬에 사용할까? 1. 액티브, 2 버프");
                                            itemOption = sc.nextInt();
                                            System.out.println("어느 디지몬에게 사용할까?");
                                            for (int i = 0; i < you.getPartner().size(); i++) {
                                                if (you.getPartner().get(i).getHp() > 0) {
                                                    System.out.println((i + 1) + ". " + you.getPartner().get(i).getName());
                                                }
                                            }
                                            partnerOption = sc.nextInt();
                                            if (itemOption == 1) {
                                                you.useSpPotion(you.getSpPotions()[3].get(0), "co", you.getPartner().get(partnerOption - 1));
                                            } else {
                                                you.useSpPotion(you.getSpPotions()[3].get(0), "buff", you.getPartner().get(partnerOption - 1));
                                            }
                                        } else {
                                            System.out.println("사용할 포션이 없어!");
                                            continue loop3;
                                        }
                                        break;
                                }
                                break;
                            case 3:
                                System.out.println("어떤 열매를 사용할까요?");
                                System.out.println("1. 나무열매 " + you.getHpPotions()[0].size() + "개 " +
                                        "(" + "배고픔 " + fruit1 + "회복, hp " + fruit1 + "회복");
                                System.out.println("2. 빨간열매 " + you.getHpPotions()[0].size() + "개 " +
                                        "(" + "배고픔 " + fruit2 + "회복, hp " + fruit2 + "회복");
                                System.out.println("3. 과사열매 " + you.getHpPotions()[0].size() + "개 " +
                                        "(" + "배고픔 " + fruit3 + "회복, hp " + fruit3 + "회복");
                                System.out.println("4. 황금열매 " + you.getHpPotions()[0].size() + "개 " +
                                        "(" + "배고픔 " + fruit4 + "회복, hp " + fruit4 + "회복");
                                itemOption = sc.nextInt();
                                switch (itemOption) {
                                    case 1:
                                        if (!you.getFruits()[0].isEmpty()) {
                                            System.out.println("어느 디지몬에게 사용할까?");
                                            for (int i = 0; i < you.getPartner().size(); i++) {
                                                if (you.getPartner().get(i).getHp() > 0) {
                                                    System.out.println((i + 1) + ". " + you.getPartner().get(i).getName());
                                                }
                                            }
                                            partnerOption = sc.nextInt();
                                            you.useFruit(you.getFruits()[0].get(0), you.getPartner().get(partnerOption - 1));
                                        } else {
                                            System.out.println("사용할 열매가 없어!");
                                            continue loop3;
                                        }
                                        break;
                                    case 2:
                                        if (!you.getFruits()[1].isEmpty()) {
                                            System.out.println("어느 디지몬에게 사용할까?");
                                            for (int i = 0; i < you.getPartner().size(); i++) {
                                                if (you.getPartner().get(i).getHp() > 0) {
                                                    System.out.println((i + 1) + ". " + you.getPartner().get(i).getName());
                                                }
                                            }
                                            partnerOption = sc.nextInt();
                                            you.useFruit(you.getFruits()[1].get(0), you.getPartner().get(partnerOption - 1));
                                        } else {
                                            System.out.println("사용할 열매가 없어!");
                                            continue loop3;
                                        }
                                        break;
                                    case 3:
                                        if (!you.getFruits()[2].isEmpty()) {
                                            System.out.println("어느 디지몬에게 사용할까?");
                                            for (int i = 0; i < you.getPartner().size(); i++) {
                                                if (you.getPartner().get(i).getHp() > 0) {
                                                    System.out.println((i + 1) + ". " + you.getPartner().get(i).getName());
                                                }
                                            }
                                            partnerOption = sc.nextInt();
                                            you.useFruit(you.getFruits()[2].get(0), you.getPartner().get(partnerOption - 1));
                                        } else {
                                            System.out.println("사용할 열매가 없어!");
                                            continue loop3;
                                        }
                                        break;
                                    case 4:
                                        if (!you.getFruits()[3].isEmpty()) {
                                            System.out.println("어느 디지몬에게 사용할까?");
                                            for (int i = 0; i < you.getPartner().size(); i++) {
                                                if (you.getPartner().get(i).getHp() > 0) {
                                                    System.out.println((i + 1) + ". " + you.getPartner().get(i).getName());
                                                }
                                            }
                                            partnerOption = sc.nextInt();
                                            you.useFruit(you.getFruits()[3].get(0), you.getPartner().get(partnerOption - 1));
                                        } else {
                                            System.out.println("사용할 열매가 없어!");
                                            continue loop3;
                                        }
                                        break;
                                }
                                break;
                            default:
                                continue loop3;
                        }

                    } else if (itemOption == 2) {
                        for (int i = 0; i < you.getPartner().size(); i++) {
                            if (you.getPartner().get(i).getHp() > 0) {
                                System.out.println((i + 1) + ". " + you.getPartner().get(i).getName());
                            }
                        }
                        System.out.println("0. 뒤로");
                        partnerOption = sc.nextInt();
                        if(partnerOption <=0 || partnerOption > you.getPartner().size()){
                            continue loop3;
                        }
                        else {
                            int i;
                            System.out.println(you.getPartner().get(partnerOption - 1).getName() + "\n체력: " + (int) you.getPartner().get(partnerOption - 1).getHp() +
                                    "/" + (int) you.getPartner().get(partnerOption - 1).getMax_hp() +
                                    "\n파워: " + (int) you.getPartner().get(partnerOption - 1).getPower() + "\n배고픔: " +
                                    you.getPartner().get(partnerOption - 1).getHungry() + "\n스킬: ");
                            for (i = 0; i < you.getPartner().get(partnerOption - 1).getCoskill().size(); i++) {
                                System.out.println((i + 1) + ". " + you.getPartner().get(partnerOption - 1).getCoskill().get(i).getName());
                            }
                            System.out.println("버프: ");
                            for (int j = i; j < you.getPartner().get(partnerOption - 1).getBuff().size() + i; j++) {
                                System.out.println((i + 1) + ". " + you.getPartner().get(partnerOption - 1).getBuff().get(j - i).getName());
                            }
                            System.out.print("세대: ");
                            switch (you.getPartner().get(partnerOption - 1).getGeneration()) {
                                case 1:
                                    System.out.println("성장기");
                                    break;
                                case 2:
                                    System.out.println("성숙기");
                                    break;
                                case 3:
                                    System.out.println("완전체");
                                    break;
                                case 4:
                                    System.out.println("궁극체");
                                    break;
                            }
                            System.out.println("타입: " + you.getPartner().get(partnerOption - 1).getType());
                            System.out.println("0.뒤로");
                            itemOption = sc.nextInt();
                            if (itemOption == 0) {
                                continue loop3;
                            } else if (itemOption <= i) {
                                System.out.println("스킬이름: " + you.getPartner().get(partnerOption - 1).getCoskill().get(itemOption - 1).getName()
                                        + " 스킬계수: " + you.getPartner().get(partnerOption - 1).getCoskill().get(itemOption - 1).getCoefficient() +
                                        " 공격횟수: " + you.getPartner().get(partnerOption - 1).getCoskill().get(itemOption - 1).getTimes() + "번");
                            } else
                                System.out.println("버프이름: " + you.getPartner().get(partnerOption - 1).getBuff().get(itemOption - i - 1).getName() +
                                        " 힐: " + you.getPartner().get(partnerOption - 1).getBuff().get(itemOption - i - 1).getHeal() +
                                        " 파워업: " + (int) you.getPartner().get(partnerOption - 1).getBuff().get(itemOption - i - 1).getPower_up() +
                                        " 쉴드: " + you.getPartner().get(partnerOption - 1).getBuff().get(itemOption - i - 1).getShield());
                        }

                    } else if (itemOption == 3) {
                        if (you.getPartner().size() > 1) {
                            System.out.println("방생할 디지몬을 선택하세요.");
                            for (int i = 0; i < you.getPartner().size(); i++) {
                                if (you.getPartner().get(i).getHp() > 0) {
                                    System.out.println((i + 1) + ". " + you.getPartner().get(i).getName());
                                }
                            }
                            itemOption = sc.nextInt();
                            you.getPartner().remove(itemOption - 1);
                        } else {
                            System.out.println("파트너가 하나밖에 없어!");
                        }
                    } else {
                        break;
                    }
                }
            }
            if (getChoice() == 4) {

                bossmon target = new bossmon();
                target = bossmons.get(character.getChapter() - 1);

                System.out.println(target.getLine());
                System.out.println("이름: " + target.getName() + "\n체력: " + target.getHp() + "\n파워: " + (int) target.getPower() +
                        "\n스킬: ");
                for (int i = 0; i < target.getCoskill().size(); i++) {
                    System.out.println((i + 1) + ". " + target.getCoskill().get(i).getName() +
                            " (" + target.getCoskill().get(i).getCoefficient() + ")" + "X" + target.getCoskill().get(i).getTimes() +
                            "번 공격");
                }
                System.out.println("버프스킬: ");
                for (int i = 0; i < target.getBuffskill().size(); i++) {
                    System.out.println((i + 1) + ". " + target.getBuffskill().get(i).getName() +
                            " (힐: " + target.getBuffskill().get(i).getHeal() +
                            ", 파워업: " + target.getBuffskill().get(i).getPower_up() + ", 쉴드: "
                            + target.getBuffskill().get(i).getShield() + ") ");
                }
                System.out.print("세대: ");
                switch (target.getGeneration()) {
                    case 2:
                        System.out.println("성장기");
                        break;
                    case 3:
                        System.out.println("성숙기");
                        break;
                    case 4:
                        System.out.println("완전체");
                        break;
                    case 5:
                        System.out.println("궁극체");
                        break;
                }
                System.out.println("타입: " + target.getType() + "\n@@@\n@@@");
                loop2:
                while (true) {
                    for (int i = 0; i < you.getPartner().size(); i++) {
                        if (you.getPartner().get(i).getHp() > 0 && you.getPartner().get(i).getHungry() <= 90) {
                            canFight = true;
                        }
                    }
                    if (canFight == true) {
                        System.out.println("내보낼 디지몬을 선택하세요!");
                        for (int i = 0; i < you.getPartner().size(); i++) {
                            if (you.getPartner().get(i).getHp() > 0 && you.getPartner().get(i).getHungry() <= 90) {
                                System.out.println((i + 1) + ". " + you.getPartner().get(i).getName() +
                                        " (배고픔 수치: " + you.getPartner().get(i).getHungry() + ")");
                            }
                        }
                    } else {
                        System.out.println("더 이상 싸울 수 있는 디지몬이 없어!");
                        choice = 3;
                        for(int i = 5; i > 0; i--){
                            try {
                                System.out.println(i+"초 후 돌아갑니다.");
                                Thread.sleep(1000);
                            }
                            catch (Exception e){}
                        }
                        continue loop1;
                    }
                    fightOption = sc.nextInt();

                    // 전투에 돌입한 디지몬은 배고픔수치가 10증가.
                    you.getPartner().get(fightOption - 1).setHungry(you.getPartner().get(fightOption - 1).getHungry() + 10);
                    canFight = false;


                    int hp = storyAction.bossFight(you, you.getPartner().get(fightOption - 1), target);
                    if (hp == 0) {
                        System.out.println("패배하였습니다!");
                        continue loop2;
                    } else {
                        if (hp != 2) {
                            System.out.println(target.getWill());
                            System.out.println("이겼다!");
                            if (character.getChapter() == 4) {
                                System.out.println("\n\n\n\n\n선택받은 아이들은 평화를 되찾았다!\n엔딩!");
                                System.out.println("엔딩 플레이타임: "+ playtime/3600+ "시간 "+ playtime/60+"분 " +playtime%60+"초");
                                break loop1;
                            } else {
                                you.setMoney(you.getMoney() + target.getMoney());
                                you.getPartner().get(fightOption - 1).setExp(you.getPartner().get(fightOption - 1).getExp() +
                                        target.getExp());
                                //경험치와 돈을 얻는다.
                                for (int i = 0; i <= 20; i++) {
                                    you.getPartner().get(fightOption - 1).levelUp();
                                    you.getPartner().get(fightOption - 1).upgrade();
                                }
                                //경험치 얻을 때마다 진화조건에 도달했는지 체크.
                                System.out.println("원하는 파트너 선택!\n" +
                                        "1.아구몬의 알\n" +
                                        "2.파피몬의 알\n" +
                                        "3.파닥몬의 알\n" +
                                        "4.피요몬의 알\n");
                                loop3:
                                while(true){
                                int fr = sc.nextInt();
                                boolean same = false;
                                switch(fr) {
                                    case 1:
                                        for (int i = 0; i < you.getPartner().size(); i++) {
                                            if (you.getPartner().get(i).getGen_name()[0] == "아구몬의 알") {
                                                same = true;
                                            }
                                        }
                                        if (same == false) {
                                            System.out.println("아구몬의 알 획득!");
                                            you.getPartner().add(agu);
                                            you.getPartner().get(you.getPartner().size()-1).upgrade();
                                        } else {
                                            same = true;
                                            System.out.println("이미 있는 파트너야!");
                                            continue loop3;
                                        }
                                        break;
                                    case 2:
                                        for (int i = 0; i < you.getPartner().size(); i++) {
                                            if (you.getPartner().get(i).getGen_name()[0] == "파피몬의 알") {
                                                same = true;
                                            }
                                        }
                                        if (same == false) {
                                            System.out.println("파피몬의 알 획득!");
                                            you.getPartner().add(papi);
                                            you.getPartner().get(you.getPartner().size()-1).upgrade();
                                        } else {
                                            same = true;
                                            System.out.println("이미 있는 파트너야!");
                                            continue loop3;
                                        }
                                        break;
                                    case 3:
                                        for (int i = 0; i < you.getPartner().size(); i++) {
                                            if (you.getPartner().get(i).getGen_name()[0] == "파닥몬의 알") {
                                                same = true;
                                            }
                                        }
                                        if (same == false) {
                                            System.out.println("파닥몬의 알 획득!");
                                            you.getPartner().add(pata);
                                            you.getPartner().get(you.getPartner().size()-1).upgrade();
                                        } else {
                                            same = true;
                                            System.out.println("이미 있는 파트너야!");
                                            continue loop3;
                                        }
                                        break;
                                    case 4:
                                        for (int i = 0; i < you.getPartner().size(); i++) {
                                            if (you.getPartner().get(i).getGen_name()[0] == "피요몬의 알") {
                                                same = true;
                                            }
                                        }
                                        if (same == false) {
                                            System.out.println("피요몬의 알 획득!");
                                            you.getPartner().add(piyo);
                                            you.getPartner().get(you.getPartner().size()-1).upgrade();
                                        } else {
                                            same = true;
                                            System.out.println("이미 있는 파트너야!");
                                            continue loop3;
                                        }
                                        break;
                                }
                                break;

                                }


                                // 다음 챕터의 아이템 두 개씩 획득한다.(챕터마다 드랍 아이템이 발전)
                                switch (character.getChapter()) {
                                    case 1:
                                        you.GetItem(hpPotion2, 2);
                                        you.GetItem(spPotion2, 2);
                                        you.GetItem(fruit2, 2);
                                        break;
                                    case 2:
                                        you.GetItem(hpPotion3, 2);
                                        you.GetItem(spPotion3, 2);
                                        you.GetItem(fruit3, 2);
                                        break;
                                    case 3:
                                        you.GetItem(hpPotion4, 2);
                                        you.GetItem(spPotion4, 2);
                                        you.GetItem(fruit4, 2);
                                        break;
                                }
                                switch (character.getChapter()) {
                                    case 1:
                                        System.out.println("챕터1: 파일섬 편 완료!");
                                        System.out.println("챕터2: 서버대륙 편 시작!");
                                        break;
                                    case 2:
                                        System.out.println("챕터2: 서버대륙 편 완료!");
                                        System.out.println("챕터3: 현실세계 편 시작!");
                                        break;
                                    case 3:
                                        System.out.println("챕터3 : 현실세계 편 완료!");
                                        System.out.println("챕터4: 어둠의 사천왕 편 시작!");
                                        break;
                                }
                                character.setChapter(character.getChapter() + 1);
                            }
                        }
                        choice = 4;
                        for(int i = 5; i > 0; i--){
                            try {
                                System.out.println(i+"초 후 돌아갑니다.");
                                Thread.sleep(1000);
                            }
                            catch (Exception e){}
                        }
                        continue loop1;
                    }
                }

            }
            if (getChoice() == 6) {
                huntingMap trainingMap = new huntingMap(5, 5, 5);
                you.setX(1);
                you.setY(4);
                huntingMap.setMapPoint(4, 1, you);
                hospitalThread hospitalThread = new hospitalThread(you, veter, hosPartners);
                hospitalThread.start();
                try {
                    hospitalThread.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                choice = 6;
                continue loop1;
            }
            if (getChoice() == 7){
                choice = 7;
                huntingMap mineMap = new huntingMap(5,5,7);
                you.setX(0);
                you.setY(1);
                huntingMap.setMapPoint(1, 0, you);
                mineThread mineThread = new mineThread(mineMap, you);
                Thread MineThread;
                MineThread = new Thread(mineThread);
                MineThread.start();
                try {
                    MineThread.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                continue loop1;
            }

//            {
//                System.out.println(veter.getName() + ": " + veter.getLine());
//                if (you.getMoney() >= veter.getMoney()) {
//                    System.out.println("누구를 회복시킬까?\n" +
//                            "0. 뒤로");
//                    for (int i = 0; i < you.getPartner().size(); i++) {
//                        System.out.println((i + 1) + ". " + you.getPartner().get(i).getName() +
//                                " (HP: " + (int)you.getPartner().get(i).getHp()+"/"+(int)you.getPartner().get(i).getMax_hp()+
//                                ", 배고픔: "+you.getPartner().get(i).getHungry()+")");
//                    }
//                    partnerOption = sc.nextInt();
//                    if (partnerOption == 0) {
//                        choice = 5;
//                        continue loop1;
//                    } else {
//                        you.getPartner().get(partnerOption - 1).setHp(you.getPartner().get(partnerOption - 1).getMax_hp());
//                        //풀피로
//                        you.getPartner().get(partnerOption - 1).setHungry(0);
//                        //배고픔 0으로
//                        for (int i = 0; i < you.getPartner().get(partnerOption - 1).getCoskill().size(); i++) {
//                            you.getPartner().get(partnerOption - 1).getCoskill().get(i).setSp(you.getPartner().get(partnerOption - 1).getCoskill().get(i).getMax_sp());
//                        }
//                        //공격스킬 SP회복
//                        for (int i = 0; i < you.getPartner().get(partnerOption - 1).getBuff().size(); i++) {
//                            you.getPartner().get(partnerOption - 1).getBuff().get(i).setSp(you.getPartner().get(partnerOption - 1).getBuff().get(i).getMax_sp());
//                        }
//                        //패시브스킬 SP회복
//                        System.out.println("회복이 완료되었어!");
//                    }
//                } else {
//                    System.out.println("돈이 부족해!");
//                }
//            }
            if (getChoice() == 0) {
                int end;
                System.out.println("정말 게임을 종료하시겠습니까?\n" +
                        "1. 예\n2. 아니요");
                end = sc.nextInt();
                if (end == 1) {
                    System.out.println("플레이타임: "+playtime/3600+ "시간 "+playtime/60+"분 " +playtime%60+"초");
                    System.out.println("진행 챕터: 챕터 "+character.getChapter());
                    break loop1;
                } else {
                    continue loop1;
                }
            }
        }
            playtimeThread.interrupt();

    }
}
