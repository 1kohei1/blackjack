import java.util.*;

class KoheiBlackjack {
  public static void main(String[] args) {
    BlackjackGame game = new BlackjackGame();
    while (!game.isOver()) {
      game.start();
    }
  }
}

class BlackjackGame {
  List<Card> playerHands;
  List<Card> dealerHands;
  Deck deck;
  boolean isOver;
  Scanner in;

  public BlackjackGame() {
    playerHands = new ArrayList<Card>();
    dealerHands = new ArrayList<Card>();
    deck = new Deck();
    deck.shuffle();
    in = new Scanner(System.in);
    isOver = false;
  }

  public boolean isOver() {
    return isOver;
  }

  public void start() {
    initialDraw();
    playerTurn();
    dealerTurn();
    result();
    askContinueOrNot();
  }

  private void initialDraw() {
    playerHands.clear();
    dealerHands.clear();

    // CPUとPlayerのカードが配られる。
    playerHands.add(deck.draw());
    dealerHands.add(deck.draw());
    playerHands.add(deck.draw());
    dealerHands.add(deck.draw());

    System.out.println("ディーラーの1枚目:");
    System.out.println(dealerHands.get(0));
  }

  private void playerTurn() {
    while (computeScore(playerHands) < 22) {
      System.out.println("あなたのカード:");
      printCards(playerHands);
      
      int userInput = 2; // 1 = hit, 0 = stand
      while (!(userInput == 0 || userInput == 1)) {
        System.out.printf("ヒットかスタンドか決めてください(1 = hit, 0 = stand): ");
        userInput = in.nextInt();
      }

      if (userInput == 1) {
        playerHands.add(deck.draw());
      } else {
        break;
      }
    }
  }

  private void dealerTurn() {
    while (computeScore(dealerHands) < 17) {
      dealerHands.add(deck.draw());
    }
    System.out.println("ディーラーのカード:");
    printCards(dealerHands);
  }

  private void result() {
    int playerScore = computeScore(playerHands);
    int dealerScore = computeScore(dealerHands);

    if (playerScore > 21) {
      System.out.println("あなたはバーストしました。負けました。");
    } else if (dealerScore > 21) {
      System.out.println("ディーラーがバーストしました。勝ちました。");
    } else if (dealerScore > playerScore) {
      System.out.println("負けました。");
    } else {
      System.out.println("勝ちました。");
    }
  }

  // 1は最初11で計算。もし、それで21より大きくなったら1として計算。
  private int computeScore(List<Card> cards) {
    int num1 = 0;
    int score = 0;

    for (Card c : cards) {
      if (c.getNum() == 1) {
        score += 11;
        num1++;
      } else if (c.getNum() > 10) {
        score += 10;
      } else {
        score += c.getNum();
      }
    }

    for (int i = 0; i < num1; i++) {
        if (score > 21) {
            score -= 10;
        }
    }

    return score;
  }

  public void askContinueOrNot() {
    int userInput = 2;
    while (!(userInput == 0 || userInput == 1)) {
      System.out.printf("ゲームを続けますか？(1 = 続ける, 0 = 続けない): ");
      userInput = in.nextInt();
    }
    
    if (userInput == 0) {
      isOver = true;
      System.out.println("ゲームを終了します");
    }
  }

  private void printCards(List<Card> cards) {
    for (Card c : cards) {
      System.out.println(c);
    }
  }
}

class Deck {
  Card[] cards;
  int currIndex;

  public Deck() {
    cards = new Card[52];
    currIndex = 0;
    
    int index = 0;
    // 52枚のカードのデックを作る。
    for (int i = 1; i <= 13; i++) {
      for (int j = 0; j < 4; j++) {
        cards[index] = new Card(i, j);
        index++;
      }
    }
  }

  public void shuffle() {
    List<Card> list = Arrays.asList(cards);
    Collections.shuffle(list);
    list.toArray(cards);
  }

  public Card draw() {
    if (currIndex == 52) {
      return null; // 本当はここでExceptionを返すべきだけど今回はしません。
    }
    Card card = cards[currIndex];
    currIndex++;
    return card;
  }
}

class Card {
  int num;
  int suit; // 0 => spade, 1 => heart, 2 => club, 3 => diamond

  public Card(int num, int suit) {
    this.num = num;
    this.suit = suit;
  }

  public int getNum() {
    return this.num;
  }

  public String toString() {
    return getSuitString() + " " + getNum();
  }

  public String getSuitString() {
    if (suit == 0) {
      return "Spade";
    } else if (suit == 1) {
      return "Heart";
    } else if (suit == 2) {
      return "Club";
    } else if (suit == 3) {
      return "Diamond";
    } else {
      return "";
    }
  }
}
