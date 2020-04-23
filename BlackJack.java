import java.util.*;
class BlackJack
{
        public static void main(String[] args)
        {
            Scanner sc = new Scanner(System.in);
            
            //全52種類のトランプのデッキを作成
            String[] deck = {
         "♠︎1","♠︎2","♠︎3","♠︎4","♠︎5","♠︎6","♠︎7","♠︎8","♠︎9","♠︎10","♠︎10","♠︎10","♠︎10",
         "❤︎ 1","❤︎ 2","❤︎ 3","❤︎ 4","❤︎ 5","❤︎ 6","❤︎ 7","❤︎ 8","❤︎ 9","❤︎ 10","❤︎ 10","❤︎ 10","❤︎ 10",
         "♣︎1","♣︎2","♣︎3","♣︎4","♣︎5","♣︎6","♣︎7","♣︎8","♣︎9","♣︎10","♣︎10","♣︎10","♣︎10",
         "♦︎1","♦︎2","♦︎3","♦︎4","♦︎5","♦︎6","♦︎7","♦︎8","♦︎9","♦︎10","♦︎10","♦︎10","♦︎10"
             };
             //ーーーーーーーーーーープレイヤーの手札を決めるーーーーーーーーーーーー
             ArrayList<String> playerHands = new ArrayList<String>();
          
          
             //最初に配るカードを決定
             int n1 = 0;
             int n2 = 0;
             int playerShuffleCount = 0;
             do {
                 if (playerShuffleCount == 0) {
                    n1 = (int) (Math.random() * 52);
                    n2 = (int) (Math.random() * 52);
                 
                    //deckから一度配られたカードを排除する
                    playerHands.add(deck[n1]);
                    deck[n1] = "0";
                    playerHands.add(deck[n2]);
                    deck[n2] = "0";
                } else {
                    n1 = (int) (Math.random() * 52);
                    n2 = (int) (Math.random() * 52);
                    
                    playerHands.set(0, deck[n1]);
                    deck[n1] = "0";
                    playerHands.set(1, deck[n2]);
                    deck[n2] = "0";
                }
                 playerShuffleCount++;
              } while ((playerHands.get(0).equals("0")) || (playerHands.get(0).equals("0")));
              
                 
                 int playerTotal = 0; 
                 for (int i = 0; i < 2; i++) {
                     playerTotal += Integer.parseInt(playerHands.get(i).replaceAll("[^0-9]",""));
                  }
                
                //プレイヤーの手札の枚数を表示
                System.out.println("【今のプレイヤーの手持ち】【1枚目:"+playerHands.get(0)+ " " +"2枚目:" + playerHands.get(1) + "】【合計:" + playerTotal + "】");
                
                //ーーーーーーーーーーーーーーーCPUの最初の二枚を決めるーーーーーーーーーーーー
                
                //CPUの手札を決める
             ArrayList<String> cpuHands = new ArrayList<String>();//プレイヤーの手持ちのカード
          
          
             //最初に配るカードを決定
             int n11 = 0;
             int n22 = 0;
             int cpuShuffleCount = 0;
             do {
                 if (cpuShuffleCount == 0) {
                    n11 = (int) (Math.random() * 52);
                    n22 = (int) (Math.random() * 52);
                 
                    //deckから一度配られたカードを排除する
                    cpuHands.add(deck[n11]);
                    deck[n11] = "0";
                    cpuHands.add(deck[n2]);
                    deck[n22] = "0";
                } else {
                    n11 = (int) (Math.random() * 52);
                    n22 = (int) (Math.random() * 52);
                    
                    cpuHands.set(0, deck[n11]);
                    deck[n1] = "0";
                    cpuHands.set(1, deck[n22]);
                    deck[n2] = "0";
                }
                 cpuShuffleCount++;
              } while ((cpuHands.get(0).equals("0")) || (cpuHands.get(0).equals("0")));
                
                //CPUの手持ちの枚数を表示&合計を計算
                System.out.println("【CPUの枚数:" + cpuHands.size() + "】");
                 int cpuTotal = 0; 
                 for (int i = 0; i < 2; i++) {
                     cpuTotal += Integer.parseInt(cpuHands.get(i).replaceAll("[^0-9]",""));
                  }
                
                //ーーーーーーーーーーーーーーープレイヤーが何回引くかを決めるーーーーーーーーーー
                
                
                //引くorステイor9
                
                int playerInput; //プレイヤーの入力
                int playerCount = 2; //なんこ目の配列に入れるか
                int cpuCount = 2;
                do {
                    System.out.println("ステイの場合は0を、一枚引く場合は1を入力してください。(もしゲームを終了する場合は9を入力してください)");
                    //プレイヤーの入力待ち
                    System.out.print("入力待ち...");
                    playerInput = sc.nextInt();
                    
                    if (playerInput == 9) {
                        System.out.println("あなたは勝負から逃げました。\nゲームを終了します。");
                        return;
                    } else if (playerInput == 1) {
                        //一枚ランダムに引く
                        int n3 = 0;
                        int playerShuffleCount1 = 0;
                        do {
                            if (playerShuffleCount1 == 0) {
                                n3 = (int) (Math.random() * 52);
                                playerHands.add(deck[n3]);
                                playerShuffleCount++;
                            } else {
                                n3 = (int) (Math.random() * 52);
                                playerHands.set(playerCount, deck[n3]);
                            }
                        } while (deck[n3].equals("0"));
                        
                        
                        System.out.print("【プレイヤーの今の手持ち】");
                        for (int i = 0; i <= playerCount; i++) {
                            System.out.print("【"+(i+1)+"枚目:"+playerHands.get(i)+"】");
                        }
                        System.out.print("【合計】:");
                        playerTotal += Integer.parseInt(playerHands.get(playerCount).replaceAll("[^0-9]",""));//今引いた数字をplayerTotalに足す
                        
                        System.out.println("【合計】:" + playerTotal);
                         playerCount++;
                         
                        if (playerTotal > 21) {
                            System.out.println("あなたはバーストしました。");
                            break;
                        }
                        
                        
                    } else if (playerInput == 2) {
                        System.out.println("ーーー結果ーーー");
                    }
                } while (playerInput == 1);
                
                //ーーーーーーーーCPUが追加で引くーーーーーーーーーー
                int cpuShuffleCount2 = 0;
                while (cpuTotal <= 14) {
                    int n4 = 0;
                    cpuShuffleCount2 = 0;
                        do {
                            if (cpuShuffleCount == 0) {
                                n4 = (int) (Math.random() * 52);
                                cpuHands.add(deck[n4]);
                                cpuShuffleCount++;
                            } else {
                                n4 = (int) (Math.random() * 52);
                                cpuHands.add(cpuCount, deck[n4]);
                            }
                        } while (deck[n4].equals("0"));
                        
                        cpuTotal += Integer.parseInt(cpuHands.get(cpuCount).replaceAll("[^0-9]",""));
                        cpuCount++;
                }
                
                System.out.println("【CPUの枚数:" + cpuHands.size() + "】");
                
                
                //ーーーーーーーーーープレイヤーとCPUの合計ーーーーーーーーーーーー
                 //プレイヤーの今の手の合計
                
                            
                //プレイヤーの手札の枚数を表示
                System.out.println("【あなたの合計】" + playerTotal);
                System.out.println("【CPUの合計】" + cpuTotal);
                
                
                //ーーーーーーーー1or11ーーーーーーーーーーーー
                int playerTotal2 = playerTotal;
                int a = 0;
                for (int i = 0; i < playerCount; i++) {
                    if ((Integer.parseInt(playerHands.get(i).replaceAll("[^0-9]",""))) == 1) {
                        a++;
                    }
                }
                if (a != 0) {
                    playerTotal2 += 10;
                    if (playerTotal2 <= 21) {
                        playerTotal = playerTotal2;
                    }
                }
                
                
                //ーーーーーーーーー勝敗判断ーーーーーーーーーーーーーー
                
                if ((21 > playerTotal) && (21 > cpuTotal)) {
                    if (playerTotal > cpuTotal) {
                        System.out.println("プレイヤーの勝ち");
                    } else if (playerTotal < cpuTotal) {
                        System.out.println("CPUの勝ち");
                    } else {
                        System.out.println("引き分け");
                    }
                }
                    
                    if (playerTotal == 21) {
                        if (cpuTotal == 21) {
                            System.out.println("引き分け:両者ブラックジャック");
                        } else {
                            System.out.println("プレイヤー:ブラックジャック!!!");
                        }
                    } else if (21 < playerTotal) {
                        if (cpuTotal == 21) {
                            System.out.println("CPU:ブラックジャック");
                        } else if (cpuTotal < 21) {
                            System.out.println("CPUの勝ち:シンギュラリティ");
                        }
                    }
                    
                    if (21 < cpuTotal) {
                        if (21 < playerTotal) {
                        System.out.println("引き分け:両者バースト");
                        } else if (21 > playerTotal) {
                            System.out.println("プレイヤーの勝ち");
                        }
                    }
        }
}       