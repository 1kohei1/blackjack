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
