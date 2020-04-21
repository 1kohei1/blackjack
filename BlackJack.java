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
             String[] phand = new String[11];//プレイヤーの手持ちのカード
             // phandは最初みたとき、わからなかったから、playerHandsのほうが変数の名前としていいと思う。
             /*
              * 配列でもいいけど、java.util.ArrayListのほうがいいと思う。
              * ArrayListはLinkedListというデータ構造なんだけど、配列のサイズを決めないで、要素を加えることができる。
              * 使い方はこんな感じ。
              * ArrayList<String> phand = new ArrayList<String>();
              * phand.add("abc");
              * System.out.println(phand.size());
              * System.out.println(phand.get(0));
              * phand.add("ijk");
              * phand.remove("abc");
              */
             
             //最初に配るカードを決定
             do {
                 int n1 = (int) (Math.random() * 52);
                 int n2 = (int) (Math.random() * 52);
                 
                 //deckから一度配られたカードを排除する
                 phand[0] = deck[n1];
                 deck[n1] = "0";
                 phand[1] = deck[n2];
                 deck[n2] = "0";
             } while ((phand[0].equals("0")) || (phand[1].equals("0")));
              
             int ptotal = 0; //プレイヤーの今の手の合計(player total)
             // ここも一緒でわざわざコメントで変数の説明をするくらいなら、変数の名前だけで何を表してるかを伝えられる名前にするべき。playerTotalとか。
             for(int i = 0; i < 2; i++) {       
                  ptotal += Integer.parseInt(phand[i].replaceAll("[^0-9]",""));
             }
                
             //プレイヤーの手札の枚数を表示
             System.out.println("【今のプレイヤーの手持ち】【1枚目:" + phand[0] + " " + "2枚目:" + phand[1] + "】【合計:" + ptotal + "】");
             
             //ーーーーーーーーーーーーーーーCPUの最初の二枚を決めるーーーーーーーーーーーー
             
             //CPUの手札を決める
             String[] chand = new String[11];//CPUの手持ちのカード
             // ここもcpuHandsのほうがいいと思う。
          
          
             //最初に配るカードを決定
             do {
                 int n1 = (int) (Math.random() * 52);
                 int n2 = (int) (Math.random() * 52);
                 
                 //deckから一度配られたカードを排除する
                 chand[0] = deck[n1];
                 deck[n1] = "0";
                 chand[1] = deck[n2];
                 deck[n2] = "0";
             } while ((chand[0].equals("0")) || (chand[1].equals("0")));
             
             int ctotal = 0; //CPUの今の手の合計(CPU total)
             // ptotalのときと同じコメント。
             for (int i = 0; i < 2; i++) {       
                  ctotal += Integer.parseInt(chand[i].replaceAll("[^0-9]",""));
             }
                //chandの中の値が入っている要素数を取得
                int ci = 0;
                for(int i=0 ; i<11 ; i++){
                    if(chand[i] == null){
                        break;
                    }
                    ci++;
                }
                
                //最初に配られた2枚のカードとその合計を表示
                System.out.println("【CPUの枚数:"+ci+"】");
                
                
                //ーーーーーーーーーーーーーーープレイヤーが何回引くかを決めるーーーーーーーーーー
                
                
                //引くorステイor9
                
                int input; //プレイヤーの入力
                int pcount=2; //なんこ目の配列に入れるか
                int ccount=2;
                do {
                    System.out.println("ステイの場合は0を、一枚引く場合は1を入力してください。(もしゲームを終了する場合は9を入力してください)");
                    //プレイヤーの入力待ち
                    System.out.print("入力待ち...");
                    input = sc.nextInt();
                    
                    if(input==9){
                        System.out.println("あなたは勝負から逃げました。\nゲームを終了します。");
                        return;
                    }else if(input==1){
                        //一枚ランダムに引く
                        int n3 = 0;
                        do {
                            n3 = (int)(Math.random()*52);
                            phand[pcount] = deck[n3];
                        }while(deck[n3].equals("0"));
                        
                        
                        System.out.print("【プレイヤーの今の手持ち】");
                        for(int i=0 ; i<=pcount ; i++){
                            System.out.print("【"+(i+1)+"枚目:"+phand[i]+"】");
                        }
                        System.out.print("【合計】:");
                        ptotal += Integer.parseInt(phand[pcount].replaceAll("[^0-9]",""));//今引いた数字をptotalに足す
                        
                        System.out.println("【合計】:"+ptotal);
                         pcount++;
                         
                        if(ptotal>21){
                            System.out.println("あなたはバーストしました。");
                            break;
                        }
                        
                        
                    }else if(input==2){
                      // これはなんのため？
                        System.out.println("ーーー結果ーーー");
                    }
                }while(input==1);
                
                //ーーーーーーーーCPUが追加で引くーーーーーーーーーー
                while(ctotal<=14){
                    int n4 = 0;
                        do {
                            n4 = (int)(Math.random()*52);
                            chand[ccount] = deck[n4];
                        }while(deck[n4].equals("0"));
                        
                        ctotal += Integer.parseInt(chand[ccount].replaceAll("[^0-9]",""));
                        ccount++;
                        ci++;
                }
                
                System.out.println("【CPUの枚数:"+ci+"】");
                
                
                //ーーーーーーーーーープレイヤーとCPUの合計ーーーーーーーーーーーー
                 //プレイヤーの今の手の合計
                
                            
                //プレイヤーの手札の枚数を表示
                System.out.println("【あなたの合計】"+ptotal);
                System.out.println("【CPUの合計】"+ctotal);
                
                
                //ーーーーーーーー1or11ーーーーーーーーーーーー
                int ptotal2 = ptotal;
                int a = 0;
                for(int i=0 ; i<pcount ; i++){
                    if( (Integer.parseInt(phand[i].replaceAll("[^0-9]",""))) == 1 ){
                        a++;
                    }
                }
                if(a!=0){
                    ptotal2 += 10;
                    if(ptotal2<=21){
                        ptotal = ptotal2;
                    }
                }
                
                
                //ーーーーーーーーー勝敗判断ーーーーーーーーーーーーーー
                
                if( (21>ptotal)&&(21>ctotal) ){
                    if(ptotal>ctotal){
                        System.out.println("プレイヤーの勝ち");
                    }else if(ptotal<ctotal){
                        System.out.println("CPUの勝ち");
                    }else{
                        System.out.println("引き分け");
                    }
                }
                    
                    if(ptotal==21){
                        if(ctotal==21){
                            System.out.println("引き分け:両者ブラックジャック");
                        }else{
                            System.out.println("プレイヤー:ブラックジャック!!!");
                        }
                    }else if(21<ptotal){
                        if(ctotal==21){
                            System.out.println("CPU:ブラックジャック");
                        }else if(ctotal<21){
                            System.out.println("CPUの勝ち:シンギュラリティ");
                    }
                    }
                    
                    if((21<ctotal){
                        if(21<ptotal){
                        System.out.println("引き分け:両者バースト");
                        }else if(21>ptotal){
                            System.out.println("プレイヤーの勝ち");
                    }
                    }
        }
}       
