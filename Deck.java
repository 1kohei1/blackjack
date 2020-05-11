import java.util.*;

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

