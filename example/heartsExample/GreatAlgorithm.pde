
class GreatAlgorithm extends Algorithm {
  Card choiceCard(List<Card> board, List<Card> hand, Player.Status myStatus, List<Player.Status> otherPlayerStatus, Integer leadSuit, boolean isHeartBroken) {
    List<Card> playableHand = Utils.getPlayableHand(hand, leadSuit, isHeartBroken);
    return playableHand.get(0);
  }

  List<Card> choiceExchange(List<Card> hand) {
    Card[] _hand = hand.toArray(new Card [hand.size()]);
    Card[] returnHand = new Card[3];
    for(int i=0;i<3;i++){
      returnHand[i] = _hand[i];
    }
    return Arrays.asList(returnHand);
  }
}
