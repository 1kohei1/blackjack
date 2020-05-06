import java.util.*;

class BlackJack
{
    public static void main(String[ ] args)
    {
        Scanner sc = new Scanner(System.in);
        
        Card player = new Card();
        Card cpu = new Card();
        
        //プレイヤーとCPUに2枚ずつカードを配ります
        player.giveOut();
        cpu.giveOut();
        player.giveOut();
        cpu.giveOut();
        //プレイヤーの手札と合計を表示します
        player.open();
        System.out.println("合計:" + player.total());
        //カードを引くか、ステイするか、ゲームをやめるかを入力します
        int playerInput;
        do {
            System.out.println("ステイの場合は0を、一枚引く場合は1を入力してください。(もしゲームを終了する場合は9を入力してください)");
            System.out.print("入力待ち...");
            playerInput = sc.nextInt();
            
            if (playerInput == 9) {
                System.out.println("ゲームを終了します。");
                return ;
            } else if (playerInput == 1) {
                player.giveOut();
                player.open();
                System.out.println("合計:" + player.total());
                if (player.total() > 21) {
                    System.out.println("あなたはバーストしました。");
                    break;
                }
            }
        } while (playerInput == 1);
        //CPUの手札の合計が14以上になるまで引き続けます
    while (cpu.total() <= 14) {
            cpu.giveOut();
    }
    //CPUの手札と合計を表示します
    cpu.open();
    System.out.println("CPUの合計:" + cpu.total());
    //CPUの手札が21より大きかった場合の処理です
    if (cpu.total() > 21) {
        System.out.println("CPUはバーストしました。");
    }
    //結果を表示します
    System.out.println("ーーー結果ーーー");
    player.judge(player.total(), cpu.total());
    
    }
}



class Card
{
    
    private static String[ ] deck = {
         "♠︎ 1","♠︎ 2","♠︎ 3","♠︎ 4","♠︎ 5","♠︎ 6","♠︎ 7","♠︎ 8","♠︎ 9","♠︎ 10","♠︎ J10","♠︎ Q10","♠︎ K10",
         "❤︎ 1","❤︎ 2","❤︎ 3","❤︎ 4","❤︎ 5","❤︎ 6","❤︎ 7","❤︎ 8","❤︎ 9","❤︎ 10","❤︎ J10","❤︎ Q10","❤︎K 10",
         "♣︎ 1","♣︎ 2","♣︎ 3","♣︎ 4","♣︎ 5","♣︎ 6","♣︎ 7","♣︎ 8","♣︎ 9","♣︎ 10","♣︎ J10","♣︎ Q10","♣︎ K10",
         "♦︎ 1","♦︎ 2","♦︎ 3","♦︎ 4","♦︎ 5","♦︎ 6","♦︎ 7","♦︎ 8","♦︎ 9","♦︎ 10","♦︎ J10","♦︎ Q10","♦︎ K10"
             };
    private ArrayList<String> hands = new ArrayList<String>();
    
    public void giveOut () //カードを一枚配ります。
    {
        while (true) {
            int n = (int) (Math.random() * 52);
            if (Integer.parseInt(deck[n].replaceAll("[^0-9]", "")) != 0) {
                hands.add(deck[n]);
                deck[n] = "0";
                return;
            }
        }
    }
    public int total () //合計を表示します
    {
        int total = 0;
        int ace = 0;
        int totalAce = 0;
        for (int i = 0; i < hands.size(); i++) {
            total += Integer.parseInt(hands.get(i).replaceAll("[^0-9]", ""));
            if ((Integer.parseInt(hands.get(i).replaceAll("[^0-9]",""))) == 1) {
                ace++;
                }
        }
        //Aが含まれていた時の判定
        if (ace != 0) {
            totalAce = total + 10;
            if (totalAce <= 21) {
                total = totalAce;
            }
        }
        return total;
    }
    public void open () //手持ちを表示します
    {
        for (int i = 0; i < hands.size(); i++) {
            System.out.print("【" + hands.get(i) + "】");
        }
    }
    public void judge (int a, int b) //勝敗を判定します
    {
        if (b == 21) {
            System.out.println("CPUブラックジャック！");
        }
        if (a == 21) {
            System.out.println("あなたブラックジャック！");
        }
    
        if ((a > 21) && (b > 21)) {
            System.out.println("両者バースト：引き分け"); 
        }else if ((a > 21) && (b < 22)) {
            System.out.println("CPUの勝ち");
        }else if ((a < 22) && (b > 21)) {
            System.out.println("あなたの勝ち");
        } else if ((a < 22) && (b < 22)) {
            if (a < b) {
                System.out.println("CPUの勝ち");
            } else if (a > b) {
                System.out.println("あなたの勝ち");
            } else if (a == b) {
                System.out.println("引き分け");
            }
        }
        
    }
}