
class GreatAlgorithm extends Algorithm {
  Card choiceCard(List<Card> board, List<Card> hand, Player.Status myStatus, List<Player.Status> otherPlayerStatus, Integer leadSuit, boolean isHeartBroken) {
    List<Card> playableHand = Utils.getPlayableHand(hand, leadSuit, isHeartBroken);
    return playableHand.get(0);
  }

  List<Card> choiceExchange(List<Card> hand) {
    return hand.subList(0, 3);
  }
}
