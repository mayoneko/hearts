package hearts

data class Card(val id: Int, val suit: Int, val number: Int) {
    companion object {
        const val HEARTS = 0
        const val DIAMONDS = 1
        const val CLUBS = 2
        const val SPADES = 3
    }

    constructor(_cardID: Int) : this(_cardID, _cardID / 13, _cardID % 13 + 1) {
        if (_cardID !in (0..51)) {
            throw IllegalArgumentException("cardID argument must be in 0..51")
        }
    }

    constructor(_suit: Int, _number: Int) : this(_suit * 13 + (_number - 1), _suit, _number) {
        if (_suit !in (0..3)) {
            throw IllegalArgumentException("suit argument must be in 0..3")
        }
        if (_number !in (1..13)) {
            throw IllegalArgumentException("number argument must be in 1..13")
        }
    }

    override fun toString(): String {
        val suitStr = when (this.suit) {
            0 -> "♥"
            1 -> "♦"
            2 -> "♣"
            3 -> "♠"
            else -> "?"
        }
        val numStr = this.number.toString().padStart(2, '0')

        return "${suitStr}-${numStr}"
    }

}

