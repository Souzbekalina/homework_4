import java.util.Random;

public class Main {

    public static int bossHealth=700;
    public static int bossDamage=50;
    public static String bossDefenceType;
    public static int [] heroesHealth={250,270,280,300,};
    public static int [] heroesDamage={25,20,15,20};
    public static String []  heroesAttacType={"Physical","Magical", "Kinetic","Medic",};

    public static int raundnumber=0;



    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinish()){playRaund();}

    }
    public static void  playRaund(){
        raundnumber++;
        choosBossDefence();
        bossHits();
        heroesHit();
        medic();
        printStatistics();
    }



    public static void printStatistics(){
        System.out.println("ROUND "+raundnumber+"-----------------");
      /* String defence;
        if (bossDefenceType==null){
            defence="No defence";
        } else {
            defence=bossDefenceType;
        }*/
        System.out.println("Boss health:"+bossHealth+";damage:"
                + bossDamage+"defence:"+(bossDefenceType== null ?"No defence" :bossDefenceType));
        for (int i =0; i<heroesHealth.length; i++){
            System.out.println(heroesAttacType[i]+" health:"+heroesHealth[i]+";damage:"+heroesDamage[i]);


        }
    }
    public static void   choosBossDefence(){
        Random random=new Random();
        int randomIndex= random.nextInt( heroesAttacType.length);//0,1,2
        bossDefenceType=heroesAttacType[randomIndex];
    }






    public static boolean isGameFinish(){
        if (bossHealth<=0){
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0]<=0 && heroesHealth[1]<=0 && heroesHealth[2]<=0){
            System.out.println("Boss won!!!");
            return true;
        }
        return  false;*/
        boolean allHeroesDead=true;
        for (int i=0;i<heroesDamage.length;i++){
            if (heroesHealth[i]>0){
                allHeroesDead=false;
                break;
            }
        }
        if (allHeroesDead){
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void bossHits(){
        for (int i=0;i<heroesHealth.length;i++){
            if (heroesHealth[i]>0) {
                if(heroesHealth[i] - bossDamage<0) {
                    heroesHealth[i] = 0;
                }else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }


            }




    public static void heroesHit(){
        for (int i = 0; i<heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int hit= heroesDamage[i];
                if (heroesAttacType[i]==bossDefenceType) {
                    Random random=new Random();
                    int coeff=random.nextInt(6)+2;
                    hit=heroesDamage[i]*coeff;
                    System.out.println("Critical damage:" + hit);
                }
                if (bossHealth - hit<0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - hit;
                }

            }

        }

    }

    public static void medic() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] == 3 && heroesHealth[3] > 0) {
                continue;
            } else if (heroesHealth[i] <= 100 && heroesHealth[3] > 0) {
                heroesHealth[i] = heroesHealth[i] + 10;
                System.out.println("The medic helped:" + heroesAttacType[i]);
                break;
            }
        }
    }



}
