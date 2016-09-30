//==================================    Global Variables    ======================================================================================================================

//Players
	var player1;
	var player2;
	var player3;
	var player4;
	
//Hands of Players
	var p1Hand = [];
	var p2Hand = [];
	var p3Hand = [];
	var p4Hand = [];
	
//==================================    Global Variables    ======================================================================================================================


//==================================    Objects and Methods ======================================================================================================================

// Object to create a card
function card(suit, rank, cardId) {	
	
	this.suit = suit;
	this.rank = rank;
	this.cardId = cardId;
	//document.writeln(cardId);
};


// Object to create a player
function player(playerId, name, hand, bid) {	
	
	var cardsInHand = 13;
	
	this.playerId = playerId;
	this.name = name;
	this.hand = hand;
	this.bid = bid;
	
	//document.writeln(playerId+" , "+name+" , "+bid);
	//for (z = 0; z < hand.length; z++) {
	//	document.writeln(hand[z]);
	//}

};


//creating deck of cards
function deck() {

	this.rank = ['2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14'];
	this.suits = ['1','2','3','4'];
	var cards = [];
    
    for( var s = 0; s < this.suits.length; s++ ) {
        for( var n = 0; n < this.rank.length; n++ ) {
			var id;
			if(Number(this.rank[n]) < 10)
				 id = this.suits[s]+'0'+this.rank[n];
			 else
				 id = this.suits[s]+this.rank[n];
			 
            cards.push( new card( this.rank[n], this.suits[s], id ) );
			//document.writeln(n+"="+id);
        }
    }

    return cards;
};


//shuffle the deck of cards
function shuffle(deckOfCards) {

    var i, j, k = 0, n = 13; 
	var temp;
    for (i = 0; i < n; i++) {
        for (j = 0; j < deckOfCards.length; j++) {
            k = Math.floor(Math.random() * deckOfCards.length);
            temp = deckOfCards[j];
            deckOfCards[j] = deckOfCards[k];
            deckOfCards[k] = temp;
        }
    }
	
	//for (z = 0; z < deckOfCards.length; z++) {
	//	document.writeln(deckOfCards[z].cardId);
	//}
	
	return deckOfCards;

};

	
//deal the shuffled deck of cards
function dealCards(sDeck) {
	
	var i = 0, j = 0; 
    while(i < sDeck.length) {
        
		//Assigning the hands
		p1Hand[j] = sDeck[i].cardId;
		i++;
		p2Hand[j] = sDeck[i].cardId;
		i++
		p3Hand[j] = sDeck[i].cardId;
		i++;
		p4Hand[j] = sDeck[i].cardId;
		i++;
		
		j++;
    }
	
	//Sorting the hands
	p1Hand.sort();
	p2Hand.sort();
	p3Hand.sort();
	p4Hand.sort();
};


//bidding 
function performBidding() {
	
	var p1Bid, p2Bid = 0, p3Bid = 0, p4Bid = 0;
	var i, j, k;
	do {
		p1Bid = prompt("Please enter your BID", "0");
	}while(!(p1Bid>0 && p1Bid<14));
	
	// Bidding strategy for Simulated players(2,3,4)----------------------------------------------------------------------------------------------
	
	//Bidding strategy for Player 2
	for (i = 0; i < p2Hand.length; i++) {
		//alert(p2Hand[i]);
		if(p2Hand[i]>401 || p2Hand[i] == 113 || p2Hand[i] == 114 || p2Hand[i] == 213 || p2Hand[i] == 214 || p2Hand[i] == 313 || p2Hand[i] == 314) {
			p2Bid++;
			//alert(p2Bid);
		}
	}
	
	//Bidding strategy for Player 3
	for (i = 0; i < p3Hand.length; i++) {
		if(p3Hand[i]>401 || p3Hand[i] == 113 || p3Hand[i] == 114 || p3Hand[i] == 213 || p3Hand[i] == 214 || p3Hand[i] == 313 || p3Hand[i] == 314)
			p3Bid++;
	}
	
	//Bidding strategy for Player 4
	for (i = 0; i < p4Hand.length; i++) {
		if(p4Hand[i]>401 || p4Hand[i] == 113 || p4Hand[i] == 114 || p4Hand[i] == 213 || p4Hand[i] == 214 || p4Hand[i] == 313 || p4Hand[i] == 314)
			p4Bid++;
	}
	// Bidding strategy for Simulated players(2,3,4)----------------------------------------------------------------------------------------------
	
	//Setting the bid value in GUI
	player1.bid = p1Bid;
	player2.bid = p2Bid;
	player3.bid = p3Bid;
	player4.bid = p4Bid;
	assignPlayerCredentials();
};


//Create the players and deal cards to them
function deal() {
	
	var deckOfCards = deck();
	var shuffledDeck = shuffle(deckOfCards);
	dealCards(shuffledDeck);
	
	//Creating 4 players
	player1 = new player(1, "Nishant", p1Hand, 0);
	player2 = new player(2, "Aziz", p2Hand, 0);
	player3 = new player(3, "Sanvida", p3Hand, 0);
	player4 = new player(4, "Noopur", p4Hand, 0);
	
	//document.writeln(player1.playerId+" , "+player1.name+" , "+player1.bid);
	//document.writeln(player1.hand[2]);
	
	
	assignImagetoCards(player1.hand);
	assignPlayerCredentials();
	performBidding();
	
};


//Assign cards, player and Bid value in the GUI
function assignPlayerCredentials() {
	
	// Setting name
	document.getElementById('name1').innerHTML = "<strong> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+
													"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+
													"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+
													"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+
													"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+
													"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+
													"&nbsp&nbsp"+player1.name+"</strong>";
	document.getElementById('name2').innerHTML = "<strong> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+
													"&nbsp&nbsp"+player2.name+"</strong>";
	document.getElementById('name3').innerHTML = "<strong> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+
													"&nbsp&nbsp"+player3.name+"</strong>";
	document.getElementById('name4').innerHTML = "<strong> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+
													"&nbsp&nbsp"+player4.name+"</strong>";
													
	// Setting Bid values and showing them
	document.getElementById('bid1').innerHTML = "<strong> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+
													"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+
													"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+
													"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+
													"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+
													"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+
													"&nbsp&nbsp BID : "+player1.bid+"</strong>";
	document.getElementById('bid2').innerHTML = "<strong> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+
													"&nbsp&nbsp BID : "+player2.bid+"</strong>";
	document.getElementById('bid3').innerHTML = "<strong> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+
													"&nbsp&nbsp BID : "+player3.bid+"</strong>";
	document.getElementById('bid4').innerHTML = "<strong> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+
													"&nbsp&nbsp BID : "+player4.bid+"</strong>";
													
	document.getElementById('bid1').style.display = "block";
	document.getElementById('bid2').style.display = "block";
	document.getElementById('bid3').style.display = "block";
	document.getElementById('bid4').style.display = "block";
	
	//Setting the label of the button to reset after it is clicked
	document.getElementById('btnDeal').value = "RESET";
};


// Object to maintain the score card
function scoreboard() {
	
	var successfulBid;
	var failedBid;
	var currentRoundPoints;
	var previousRoundPoints;
	var totalPoints;
	
};


// Object to maintain the statistics of whole game
function statistics() {
	
	var tricksWon;
	var extraTricksBagged;
};

//==================================    Objects and Methods ======================================================================================================================