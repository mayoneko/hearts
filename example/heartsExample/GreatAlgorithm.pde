
class GreatAlgorithm extends Algorithm {
  Card choiceCard(List board, List hand, Player.Status myStatus, List otherPlayerStatus, Integer leadSuit, boolean isHeartBroken) {
    List<Card> playableHand = Utils.getPlayableHand(hand, leadSuit, isHeartBroken);
    return playableHand.get(0);
  }

  List<Card> choiceExchange(List hand) {
    return hand.subList(0, 3);
  }
}
