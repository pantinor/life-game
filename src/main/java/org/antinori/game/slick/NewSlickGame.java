package org.antinori.game.slick;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import org.antinori.game.GameInterface;
import org.antinori.game.LifeMain;
import org.antinori.game.Location;
import org.antinori.game.Player;
import org.antinori.game.SoundEffect;
import org.antinori.game.cards.Card;
import org.antinori.game.cards.CareerCard;
import org.antinori.game.cards.HouseCard;
import org.antinori.game.cards.LifeTile;
import org.antinori.game.cards.ShareTheWealthCard;

public class NewSlickGame implements GameInterface {

	public boolean gameOver = false;
	
	LifeWorld world;

	private ArrayList<Player> players = new ArrayList<Player>(6);
	private Player currentPlayer = null;
	
	private ArrayList<Card> STW_CARDS = new ArrayList<Card>(21);
	private ArrayList<Card> CAREER_CARDS = new ArrayList<Card>(6);
	private ArrayList<Card> COLLEGE_CAREER_CARDS = new ArrayList<Card>(6);
	private ArrayList<LifeTile> LIFE_TILES = new ArrayList<LifeTile>(25);
	
	public static String[] NUMBER_OPTIONS = {"1","2","3","4","5","6","7","8","9","10" };
	
	String[] STARTER_HOMES_OPTIONS = null;
	String[] BETTER_HOMES_OPTIONS = null;
	ArrayList<HouseCard> STARTER_HOMES = new ArrayList<HouseCard>(6);
	ArrayList<HouseCard> BETTER_HOMES = new ArrayList<HouseCard>(6);



	public NewSlickGame(LifeWorld world) {
		this.world = world;
	}


	
	public void createDecks() {

		ArrayList<Card> deck = new ArrayList<Card>(21);
		for (int i = 0; i < 4; i++)
			deck.add(new ShareTheWealthCard(Card.Type.PAY_BANK));
		for (int i = 0; i < 4; i++)
			deck.add(new ShareTheWealthCard(Card.Type.PAY_YOU));
		for (int i = 0; i < 4; i++)
			deck.add(new ShareTheWealthCard(Card.Type.SPIN2));
		for (int i = 0; i < 4; i++)
			deck.add(new ShareTheWealthCard(Card.Type.SPIN4));
		for (int i = 0; i < 5; i++)
			deck.add(new ShareTheWealthCard(Card.Type.EXEMPT));
		STW_CARDS = shuffleDeck(deck, 21);

		deck = new ArrayList<Card>(6);
		deck.add(new CareerCard(Card.Type.CAREER, CareerCard.CareerType.TYPE_SALESPERSON, 20000, 50000, 5000));
		deck.add(new CareerCard(Card.Type.CAREER, CareerCard.CareerType.TYPE_MECHANIC, 30000, 60000, 10000));
		deck.add(new CareerCard(Card.Type.CAREER, CareerCard.CareerType.TYPE_POLICE_OFFICER, 40000, 70000, 15000));
		deck.add(new CareerCard(Card.Type.CAREER, CareerCard.CareerType.TYPE_ENTERTAINER, 50000, 1000000, 20000));
		deck.add(new CareerCard(Card.Type.CAREER, CareerCard.CareerType.TYPE_ATHLETE, 60000, 1000000, 25000));
		deck.add(new CareerCard(Card.Type.CAREER, CareerCard.CareerType.TYPE_HAIR_STYLIST, 30000, 60000, 10000));
		CAREER_CARDS = shuffleDeck(deck, 6);

		deck = new ArrayList<Card>(6);
		deck.add(new CareerCard(Card.Type.COLLEGE_CAREER, CareerCard.CareerType.TYPE_ACCOUNTANT, 70000, 110000, 30000));
		deck.add(new CareerCard(Card.Type.COLLEGE_CAREER, CareerCard.CareerType.TYPE_COMPUTER_DESIGNER, 50000, 80000, 20000));
		deck.add(new CareerCard(Card.Type.COLLEGE_CAREER, CareerCard.CareerType.TYPE_DOCTOR, 100000, 1000000, 45000));
		deck.add(new CareerCard(Card.Type.COLLEGE_CAREER, CareerCard.CareerType.TYPE_LAWYER, 90000, 1000000, 40000));
		deck.add(new CareerCard(Card.Type.COLLEGE_CAREER, CareerCard.CareerType.TYPE_VETERINARIAN, 80000, 120000, 35000));
		deck.add(new CareerCard(Card.Type.COLLEGE_CAREER, CareerCard.CareerType.TYPE_TEACHER, 40000, 70000, 15000));
		COLLEGE_CAREER_CARDS = shuffleDeck(deck, 6);

		HouseCard shc1 = new HouseCard(Card.Type.STARTER_HOME, HouseCard.HomeType.TS, 180000, 200000);
		HouseCard shc2 = new HouseCard(Card.Type.STARTER_HOME, HouseCard.HomeType.MH, 80000, 80000);
		HouseCard shc3 = new HouseCard(Card.Type.STARTER_HOME, HouseCard.HomeType.LC, 120000, 140000);
		HouseCard shc4 = new HouseCard(Card.Type.STARTER_HOME, HouseCard.HomeType.RS, 140000, 160000);
		HouseCard shc5 = new HouseCard(Card.Type.STARTER_HOME, HouseCard.HomeType.C, 100000, 105000);
		HouseCard shc6 = new HouseCard(Card.Type.STARTER_HOME, HouseCard.HomeType.SC, 160000, 180000);
		String sh1 = shc1.getDescription();
		String sh2 = shc2.getDescription();
		String sh3 = shc3.getDescription();
		String sh4 = shc4.getDescription();
		String sh5 = shc5.getDescription();
		String sh6 = shc6.getDescription();
		
		HouseCard bhc1 = new HouseCard(Card.Type.HOUSE, HouseCard.HomeType.EC, 400000, 400000);
		HouseCard bhc2 = new HouseCard(Card.Type.HOUSE, HouseCard.HomeType.PS, 700000, 700000);
		HouseCard bhc3 = new HouseCard(Card.Type.HOUSE, HouseCard.HomeType.LMR, 600000, 600000);
		HouseCard bhc4 = new HouseCard(Card.Type.HOUSE, HouseCard.HomeType.M, 800000, 800000);
		HouseCard bhc5 = new HouseCard(Card.Type.HOUSE, HouseCard.HomeType.MV, 500000, 500000);
		HouseCard bhc6 = new HouseCard(Card.Type.HOUSE, HouseCard.HomeType.DWRV, 300000, 300000);
		String bh1 = bhc1.getDescription();
		String bh2 = bhc2.getDescription();
		String bh3 = bhc3.getDescription();
		String bh4 = bhc4.getDescription();
		String bh5 = bhc5.getDescription();
		String bh6 = bhc6.getDescription();
		
		String[] sh = {sh1,sh2,sh3,sh4,sh5,sh6};
		STARTER_HOMES_OPTIONS = sh;
		String[] bh = {bh1,bh2,bh3,bh4,bh5,bh6};
		BETTER_HOMES_OPTIONS = bh;
		
		STARTER_HOMES.add(shc1);
		STARTER_HOMES.add(shc2);
		STARTER_HOMES.add(shc3);
		STARTER_HOMES.add(shc4);
		STARTER_HOMES.add(shc5);
		STARTER_HOMES.add(shc6);

		BETTER_HOMES.add(bhc1);
		BETTER_HOMES.add(bhc2);
		BETTER_HOMES.add(bhc3);
		BETTER_HOMES.add(bhc4);
		BETTER_HOMES.add(bhc5);
		BETTER_HOMES.add(bhc6);
		
		
		ArrayList<LifeTile> tiles = new ArrayList<LifeTile>(25);
		for (int i = 0; i < 7; i++)	tiles.add(new LifeTile(10000));
		for (int i = 0; i < 6; i++)	tiles.add(new LifeTile(20000));
		for (int i = 0; i < 5; i++)	tiles.add(new LifeTile(30000));
		for (int i = 0; i < 4; i++)	tiles.add(new LifeTile(40000));
		for (int i = 0; i < 3; i++)	tiles.add(new LifeTile(50000));
		LIFE_TILES = shuffleLifeTiles(tiles,25);

	}

	public ArrayList<Card> shuffleDeck(ArrayList<Card> deck, int total) {
		ArrayList<Card> shuffled = new ArrayList<Card>(total);
		Random rand = new Random();
		for (int i = 0; i < total; i++) {
			int r = rand.nextInt(deck.size());
			Card c = deck.remove(r);
			shuffled.add(c);
		}
		return shuffled;
	}
	
	public ArrayList<LifeTile> shuffleLifeTiles(ArrayList<LifeTile> deck, int total) {
		ArrayList<LifeTile> shuffled = new ArrayList<LifeTile>(total);
		Random rand = new Random();
		for (int i = 0; i < total; i++) {
			int r = rand.nextInt(deck.size());
			LifeTile c = deck.remove(r);
			shuffled.add(c);
		}
		return shuffled;
	}
	
	/*
	 * Returns the first player to take a turn
	 */
	public Actor initPlayers() throws Exception {
		
		this.world.setMessageDisplayed("Spin the wheel to see who goes first.", true);
		try {
			while(this.world.getMessageDisplayed() != null) {
				Thread.sleep(500);
			}
		} catch(Exception e) {
		}
		
		//spin to see who goes first
		HashMap<Integer,Player> map = new HashMap<Integer,Player>();
		for (Player player : players) {
			int roll = 0;
			while (true) {
				roll = spinWheel(player,false);
				//cannot contain duplicates
				if (!map.containsKey(roll)) {
					map.put(roll, player);
					break;
				} else {
					messageDialog(player,player.getPlayerName() + " will spin again since someone already rolled a " + roll);
				}
			}
		}
		ArrayList<Integer> sortedKeys = new ArrayList<Integer>(map.keySet());
		Collections.sort(sortedKeys);
		Collections.reverse(sortedKeys);
		
		ArrayList<Player> tempPlayers = new ArrayList<Player>();
		for (Integer roll : sortedKeys) {
			Player player = map.get(roll);
			tempPlayers.add(player);
		}
		players = tempPlayers;
		
		

		if (STW_CARDS == null)
			throw new Exception("Shuffled Deck is null.");

		if (players == null || players.size() < 2)
			throw new Exception("Not enough players.");

		// deal the stw cards, each player gets 3 at start
		for (Player player : players) {
			player.addCard((ShareTheWealthCard)STW_CARDS.remove(0));
			player.addCard((ShareTheWealthCard)STW_CARDS.remove(0));
			player.addCard((ShareTheWealthCard)STW_CARDS.remove(0));
		}

		for (Player player : players) {
			player.setMoney(10000);
		}


		return players.get(0).getActor();
	}

	public void startGame() throws Exception {

		while (!gameOver) {

			for (Player player : players) {

				if (player.isMiss_turn()) {
					player.setMiss_turn(false);
					continue;
				}
				
				currentPlayer = player;player.setPlayerIsCurrent();

				//LifeMain.playerInfoPanel.setPlayer(player);
				//LifeMain.statusPanel.setText(players);

				if (player.getPlayerLocation() == null) {
					
					boolean college = false;
					if (!player.isComputerPlayer()) {
						String[] options = { "Go to COLLEGE", "Start a CAREER" };
						int n = optionDialog(player.getPlayerName() + ", where will you start on the path of life?",options);
						college = (n==0?true:false);
					} else {
						college = new Random().nextBoolean();
					}
					
					player.setPlayerLocation(college? LifeMap.START_COLLEGE_LOCATION : LifeMap.START_CAREER_LOCATION);
					
					if (college) {
						player.addMoney(100000);
						player.setLoans(player.getLoans() + 5);
						player.setHasCollegeDegree(true);
					} else {
						CareerCard career = pullCareerCard(false);
						messageDialog(player,player.getPlayerName() + " picked a career " + career.getDescription());
						player.setCareer(career);
					}
				}

				if (!player.isComputerPlayer() && player.getLti_card_value() == -1) {
					OptionPopup popup = new OptionPopup(NUMBER_OPTIONS,player.getPlayerName() + ", do you want to buy a Long Term investment?", true);
					popup.showDialog(this.world.getRootPane());
					if (popup.selection != -1) player.setLti_card_value(popup.selection+1);
				} else if (player.getLti_card_value() == -1) {
					int pick = new Random().nextInt(9);
					player.setLti_card_value(pick + 1);
				}
				
				if (player.isRetired()) {
					
					spinWheel(player, true);
					
					LifeTile tile = pullLifeTile(player);
					if (tile != null) player.addLifeTile(tile);
					
					if (allPlayersRetired()) {
						gameOver = true;
						break;
					}
					continue;
				}
				
				int roll = spinWheel(player, true);
				
				boolean spin_again = false;
				
				for (int i=0;i<roll;i++) {
				
					Location loc = player.getPlayerLocation();
					Location next = loc.getNextLocation();

					//check for ORANGE spaces or PAYDAY spaces
					switch(loc.getTile().getType()) {
						case PAYDAY:{
							int payraise = loc.getTile().getAmount();
							int salary = player.getSalary();
							if (payraise > 0) player.addPayRaise(payraise);
							SoundEffect.CASH.play();
							player.addMoney(salary);
							if (!player.isComputerPlayer())
								messageDialog(player,player.getPlayerName() + " got paid $" + (salary + payraise) + " (payraise $" + payraise + ")");
							break;
						}
						case COLLEGE_CAREER_CHOICE:{
							CareerCard career = pullCareerCard(true);
							messageDialog(player,player.getPlayerName() + " picked a career " + career.getDescription());
							player.setCareer(career);
							spin_again = true;
							break;
						}
						case GET_MARRIED: {
							getMarried(player);
							spin_again = true;
							break;
						}
						case CHANGE_CAREER:{
							changeCareer(player);
							spin_again = true;
							break;
						}
						case BUY_STARTER_HOME: {
							selectStarterHome(player);
							spin_again = true;
							break;
						}
						case BUY_BETTER_HOME: {
							selectBetterHome(player);
							spin_again = true;
							break;
						}
						case RETURN_TO_SCHOOL:{
							String[] options = { "Return to School", "Continue on the path of life" };
							boolean choice = choosePath(player,player.getPlayerName() + ", which path will you take?",options);
							messageDialog(player,player.getPlayerName() + " chose " + (choice?options[0]:options[1]) + ".");
							next = (choice?LifeMap.BACK_TO_SCHOOL_SELECTED:LifeMap.BACK_TO_SCHOOL_NOT_SELECTED);
							if (choice) player.subtractMoney(50000);
							spin_again = true;
							break;
						}
						case TAKE_FAMILY_PATH:{
							String[] options = { "Take the Family Path", "Continue on the path of life" };
							boolean choice = choosePath(player,player.getPlayerName() + ", which path will you take?",options);
							messageDialog(player,player.getPlayerName() + " chose " + (choice?options[0]:options[1]) + ".");
							next = (choice?LifeMap.FAMILY_PATH_SELECTED:LifeMap.FAMILY_PATH_NOT_SELECTED);
							spin_again = true;
							break;
						}
						case TAKE_RISKY_PATH:{
							String[] options = { "Take the Risky Path", "Continue on the path of life" };
							boolean choice = choosePath(player,player.getPlayerName() + ", which path will you take?",options);
							messageDialog(player,player.getPlayerName() + " chose " + (choice?options[0]:options[1]) + ".");
							next = (choice?LifeMap.RISKY_PATH_SELECTED:LifeMap.RISKY_PATH_NOT_SELECTED);
							spin_again = true;
							break;
						}
						case RETIRE: {
							//repay loans
							player.subtractMoney(player.getLoans()*20000);
							player.setLoans(0);
							//sell house
							if (player.getHouse() != null) {
								int sale = player.getHouse().getSellFor();
								player.addMoney(sale);
							}
							player.setHouse(null);
							//set null career
							player.setCareer(null);
							//set retired boolean on player
							player.setRetired(true);
							// collect LTI each spin after this
							//still play STW cards
							//collect 10000 times children
							player.addMoney(10000*player.getChild_count());
							//choose ME or CA
							String[] options = {"Millionaire Estates","Countryside Acres"};
							boolean choice = choosePath(player,player.getPlayerName() + ", which retirement home will you choose?",options);
							messageDialog(player,player.getPlayerName() + " chose " + (choice?options[0]:options[1]) + ".");
							player.setRetireeHome((choice?options[0]:options[1]));
							break;
						}
					}
					
					if (spin_again) {
						i = 0;
						roll = spinWheel(player, true);
						spin_again = false;
					}
					
					if (player.isRetired()) {
						break;
					}
										
					player.setPlayerLocation(next);
										
				}
				
				Location finalLocation = player.getPlayerLocation();
				messageDialog(player,finalLocation.getTile().getType() + " - " + finalLocation.getTile().getDescription());

				switch(finalLocation.getTile().getType()) {
					case COLLECT: {
						int amount = finalLocation.getTile().getAmount();
						for (Player p : players) {
							if (p == currentPlayer) continue;
							boolean choice = false;
							if (p.hasPayYouCard()) {
								String[] options = {"Yes","No"};
								choice = choosePath(player,player.getPlayerName() + ", do you want to use your COLLECT card to collect half of $"+amount+"?",options);
							} else {
								continue;
							}
							if (choice) {
								amount = amount/2;
								messageDialog(p,p.getPlayerName() + " used a PAY YOU card and took half.");
								p.addMoney(amount);
								replaceSTWCard(p.usePayCard(Card.Type.PAY_YOU));
								break;
							}
						}
						player.addMoney(amount);
						break;
					}
					case LAWSUIT: {
						sueAnotherPlayer(player);
						break;
					}
					case TAXES_DUE: {
						int amount = player.getCareer().getTaxes_due();
						player.subtractMoney(amount);
						break;
					}
					case TAX_REFUND: {
						int amount = player.getCareer().getTaxes_due();
						player.addMoney(amount);
						break;
					}
					case PAY: {
						int amount = finalLocation.getTile().getAmount();
						if (player.hasPayBankCard()) tryPayBankCard(player,amount);
						break;
					}
					case LIFE: {
						LifeTile tile = pullLifeTile(player);
						if (tile != null) player.addLifeTile(tile);
						break;
					}
					case MOVE_TO_MARRIED: {
						player.setPlayerLocation(LifeMap.MARRIED_LOCATION);
						break;
					}
					case MISS_TURN: {
						player.setMiss_turn(true);
						break;
					}
					case SPIN_AGAIN: {
						for (Player p : players) {
							if (p == currentPlayer) continue;
							p.setMiss_turn(true);
						}
						break;
					}
					case TAKE_STW_CARD: {
						int pick = new Random().nextInt(STW_CARDS.size()-1);
						ShareTheWealthCard card = (ShareTheWealthCard)STW_CARDS.get(pick);
						player.addCard(card);
						break;
					}
					case LOSE_JOB: {
						CareerCard career = pullCareerCard(false);
						messageDialog(player,player.getPlayerName() + " picked a career " + career.getDescription());
						player.setCareer(career);
						player.setHasCollegeDegree(false);
						break;
					}
					case SPIN_TO_WIN: {
						spinToWin();
						break;
					}
					case LIFE_ADD_CHILDREN: {
						int kids = finalLocation.getTile().getAmount();
						player.addChild(kids);
						LifeTile tile = pullLifeTile(player);
						if (tile != null) player.addLifeTile(tile);
						for (Player p : players) {
							if (p == currentPlayer) continue;
							if (p.getMoney()<5000) p.getLoanFromBank();
							p.subtractMoney(5000);
							player.addMoney(5000);
							messageDialog(player,player.getPlayerName() + " received a $5000 gift from " + p.getPlayerName());
						}
						break;
					}
					case COLLECT_FOR_EACH_CHILD_FROM_OTHERS: {
						for (Player p : players) {
							if (p == currentPlayer) continue;
							int count = p.getChild_count();
							if (count == 0) continue;
							int amount = 5000*count;
							if (p.getMoney()<amount) p.getLoanFromBank();
							p.subtractMoney(amount);
							player.addMoney(amount);
							messageDialog(player,player.getPlayerName() + " received $"+amount+" from " + p.getPlayerName());
						}
						break;
					}
					case PAY_PER_CHILD: {
						if (player.getChild_count() > 0) {
							if (player.getMoney()<5000*player.getChild_count()) player.getLoanFromBank();
							player.subtractMoney(5000*player.getChild_count());
						}
						break;
					}
					case PENSION: {
						int spin = spinWheel(player, false);
						player.addMoney(spin*10000);
						messageDialog(player,player.getPlayerName() + " received a pension of $" +(spin*10000) );
						break;
					}
				}
				

			}
			
		}
		
		//show stats and winner
		this.world.gameOver = true;
	}
	
	public void messageDialog(Player player, String text) {
		try {
			this.world.setMessageDisplayed(text,false);
			Thread.sleep(2000);
			this.world.setMessageDisplayed(null,false);
		} catch(Exception e) {
		}
	}
	
	public int optionDialog(String text, String[] options) {
		this.world.optionMessageDisplayed = text;
		this.world.options = options;
		this.world.selectedOption = -1;
		try {
			while(this.world.selectedOption == -1) {
				Thread.sleep(500);
			}
		} catch(Exception e) {
		}
		this.world.optionMessageDisplayed = null;
		synchronized(this.world.selectables) {
			this.world.selectables.clear();
		}

		System.out.println("selected = " + this.world.selectedOption);
		return this.world.selectedOption;
	}
	
	public int spinWheel(Player player, boolean check_lti) {

		int seed = LifeMain.dice.roll();
		int roll = seed;
		
		if (!player.isComputerPlayer()) {
			SoundEffect.SPIN.play();
			roll = this.world.spinWheel(seed);
		}
		
		messageDialog(player,player.getPlayerName() + " spinned a " + roll);
		
		if (check_lti) {
			for (Player p : players) {
				if (p.getLti_card_value() == roll) {
					p.addMoney(5000);
					messageDialog(p,p.getPlayerName() + " received a $5000 payout from Long Term Investment.");
				}
			}
		}
		
		return roll;

	}
	
	public boolean choosePath(Player player, String text, String[] options) {
		boolean choice = false;
		if (!player.isComputerPlayer()) {
			int n = optionDialog(text,options);
			choice = (n==0?true:false);
		} else {
			choice = new Random().nextBoolean();
		}
		return choice;
	}
	
	public void changeCareer(Player player) {
		boolean change = false;
		if (!player.isComputerPlayer()) {
			String[] options = {"Change Career","Take a $20,000 Pay Raise"};
			int n = optionDialog(player.getPlayerName() + ", which will choose?",options);
			change = (n==0?true:false);
		} else {
			change = new Random().nextBoolean();
		}
		
		if (change) {
			boolean college = new Random().nextBoolean();
			CareerCard career = pullCareerCard(college);
			messageDialog(player,player.getPlayerName() + " picked a career " + career.getDescription());
			player.setCareer(career);
			player.setHasCollegeDegree(college);
		} else {
			player.addPayRaise(20000);
		}
	}

	public void addPlayer(String name, Actor actor, Color color, boolean isComputer) {
		Player player = new Player(name, actor, color, isComputer);
		players.add(player);
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public LifeTile pullLifeTile(Player puller) {
		
		if (LIFE_TILES.size() == 0) {
			
			ArrayList<Player> others = new ArrayList<Player>();
			for (Player player : players) {
				if (player == currentPlayer || (player.isRetired() && player.getRetireeHome() == "Countryside Acres")) continue;
				if (player.getLife_card_count() < 1) continue;
				others.add(player);
			}
			
			if (others.size() == 0) return null;
			
			if (!puller.isComputerPlayer()) {
				
				String[] choices = new String[others.size()];
				int i=0;
				for (Player player : others) {
					choices[i] = player.getPlayerName();i++;
				}
				int n = optionDialog("There are no more LIFE tiles in the bank. Select another player to take a LIFE tile from.",choices);
				Player choice = null;
				for (Player player : others) {
					if (player.getPlayerName() == choices[n]) choice = player;
				}
				
				messageDialog(puller,puller.getPlayerName() + " took a LIFE Card from " + choice.getPlayerName() + ".");
				
				return choice.removeLifeTile();

			} else {
				//pick the player with the most life cards
				Player choice = others.get(0);
				for (Player player : others) {
					if (player.getLife_card_count() > choice.getLife_card_count()) choice = player;
				}
				messageDialog(puller,puller.getPlayerName() + " took a LIFE Card from " + choice.getPlayerName() + ".");
				
				return choice.removeLifeTile();
			}

		}
		return LIFE_TILES.remove(0);
	}
	
	public int sueAnotherPlayer(Player player) {
		
		int amount = 100000;
		Player[] others = new Player[players.size()-1];
		int i = 0;
		for (Player p : players) {
			if (p == currentPlayer) continue;
			others[i] = p;i++;
		}
		
		Player choice = null;
		if (!player.isComputerPlayer()) {
			OptionPopup popup = new OptionPopup(others, "LAWSUIT - Select another player to sue.", false);
			popup.showDialog(this.world.getRootPane());
			choice = others[popup.selection];
		} else {
			//computer sues the player with most money
			for (Player p : players) {
				if (p == currentPlayer) continue;
				if (choice == null || choice.getMoney() > p.getMoney()) choice = p;
			}
		}
		
		ShareTheWealthCard exemption = choice.hasExemptionCard();
		if (exemption != null) {
			messageDialog(choice,choice.getPlayerName() + " was sued but had an exemption card and used it.");
			replaceSTWCard(exemption);
			amount = 0;
		} else {
			messageDialog(choice, choice.getPlayerName() + " paid $100,000 in a lawsuit to " + player.getPlayerName());
		}
		
		player.addMoney(amount);
		choice.subtractMoney(amount);
		
		return amount;
	}
	
	public void tryPayBankCard(Player player, int amount) {
		
		String[] options = {"Yes","No"};
		boolean choosePay = choosePath(player,player.getPlayerName() + ", do you want to use your Share The Wealth card so someone else pays half of $"+amount+"?",options);
		if (choosePay) {
			
			Player[] others = new Player[players.size()-1];
			int i = 0;
			for (Player p : players) {
				if (p == currentPlayer) continue;
				others[i] = p;i++;
			}
		
			Player choice = null;
			if (!player.isComputerPlayer()) {
				OptionPopup popup = new OptionPopup(others, "Pick which player you want to pay half of the $"+amount+".", false);
				popup.showDialog(this.world.getRootPane());
				choice = others[popup.selection];
			} else {
				//computer chooses the player with most money
				for (Player p : players) {
					if (p == currentPlayer) continue;
					if (choice == null || choice.getMoney() > p.getMoney()) choice = p;
				}
			}
			
			replaceSTWCard(player.usePayCard(Card.Type.PAY_BANK));
					
			ShareTheWealthCard exemption = choice.hasExemptionCard();
			if (exemption != null) {
				messageDialog(choice,choice.getPlayerName() + " had an exemption card and used it.");
				replaceSTWCard(exemption);
			} else {
				amount = amount/2;
				messageDialog(choice, choice.getPlayerName() + " paid half to the bank!");
				choice.subtractMoney(amount);
			}
		}
		
		player.subtractMoney(amount);

	}
	
	public void replaceSTWCard(ShareTheWealthCard card) {
		STW_CARDS.add(card);
	}
	
	public void selectStarterHome(Player player) {
		String choice = null;
		if (!player.isComputerPlayer()) {
			OptionPopup popup = new OptionPopup(STARTER_HOMES_OPTIONS, "Pick your STARTER HOME", false);
			popup.showDialog(this.world.getRootPane());
			choice = STARTER_HOMES_OPTIONS[popup.selection];
		} else {
			choice = STARTER_HOMES_OPTIONS[new Random().nextInt(5)];
		}
		messageDialog(player,player.getPlayerName() + " chose " + choice + ".");
		if (choice == null) {
			player.setHouse(STARTER_HOMES.get(0));
			player.subtractMoney(STARTER_HOMES.get(0).getValue());
		} else {
			for (HouseCard card : STARTER_HOMES) {
				boolean eq = card.getDescription().equals(choice);
				if (eq) {
					player.setHouse(card);
					player.subtractMoney(card.getValue());
				}
			}
		}
	}
	
	public void selectBetterHome(Player player) {
		String choice = null;
		if (!player.isComputerPlayer()) {
			OptionPopup popup = new OptionPopup(BETTER_HOMES_OPTIONS, "Select a BETTER HOME or else CANCEL.", true);
			popup.showDialog(this.world.getRootPane());
			if (popup.selection == -1) return;
			choice = BETTER_HOMES_OPTIONS[popup.selection];
		} else {
			choice = BETTER_HOMES_OPTIONS[new Random().nextInt(5)];
		}
		messageDialog(player,player.getPlayerName() + " chose " + choice + ".");
		for (HouseCard card : BETTER_HOMES) {
			boolean eq = card.getDescription().equals(choice);
			if (eq) {
				if (player.getHouse() != null) player.addMoney(player.getHouse().getSellFor());
				player.setHouse(card);
				player.subtractMoney(card.getValue());
			}
		}
	}
	
	public void spinToWin() {
		for (Player p : players) {
			ArrayList<Integer> select = null;
			if (!p.isComputerPlayer()) {
				SpinToWinPopup popup = new SpinToWinPopup(p, (GameInterface)this);
				popup.showDialog(this.world.getRootPane());
				select = popup.selection;
			} else {
				boolean play = new Random().nextBoolean();
				if (!play) continue;
				select = new ArrayList<Integer>();
				if (p.hasSPIN4Card()) {
					while (select.size() < 5) {
						int pick = LifeMain.dice.roll();
						if (!select.contains(pick)) select.add(pick);
					}
					ShareTheWealthCard card = p.useSPINCard(Card.Type.SPIN4);
					replaceSTWCard(card);
				} else if (p.hasSPIN2Card()) {
					while (select.size() < 3) {
						int pick = LifeMain.dice.roll();
						if (!select.contains(pick)) select.add(pick);
					}
					ShareTheWealthCard card = p.useSPINCard(Card.Type.SPIN2);
					replaceSTWCard(card);
				} else {
					select.add(LifeMain.dice.roll());
				}
				boolean big_wager = new Random().nextBoolean();
				p.setSpintowin_wager(big_wager?20000:5000);
			}
			
			if (select == null) continue; //did not play
			
			int spin = spinWheel(p,false);
			if (select.contains(spin)) {
				p.addMoney(p.getSpintowin_wager()*10);
				messageDialog(p, p.getPlayerName() + " won $" + (p.getSpintowin_wager()*10));
			} else {
				p.subtractMoney(p.getSpintowin_wager());
				messageDialog(p,p.getPlayerName() + " lost the wager.");
			}
		}
	}
	
	public CareerCard pullCareerCard(boolean requireCollege) {
		CareerCard card = null;
		int index = new Random().nextInt(6);
		if (requireCollege) {
			card = (CareerCard)COLLEGE_CAREER_CARDS.get(index);
		} else {
			card = (CareerCard)CAREER_CARDS.get(index);
		}
		return card;
	}
	
	public void getMarried(Player player) {
		player.setMarried(true);
		LifeTile tile = pullLifeTile(player);
		if (tile != null) player.addLifeTile(tile);
		int spin = spinWheel(player, false);
		int amount = 0;
		if (spin>7) {
			amount = 10000;
		} else if (spin>4) {
			amount = 5000;
		}
		String text = player.getPlayerName() + " spinned a "+ spin;
		if (amount > 0) text = text + ". Each player will give you $"+amount+" as a wedding gift."; 
		else text = text + " and does not receive any wedding gifts.";
		messageDialog(player, text);
		for (Player p : players) {
			if (p == player) continue;
			p.subtractMoney(amount);
			player.addMoney(amount);
		}
	}
	
	public boolean allPlayersRetired() {
		boolean all_retired = true;
		for (Player player : players) {
			if (!player.isRetired()) all_retired = false;
		}
		return all_retired;
	}
	
	//debug
	public void setDebugStartPositions(Location loc) {
		
		for (Actor actor : this.world.actors) {
			actor.setCurrentTile(loc.getX(), loc.getY());
		}
		for (Player player : players) {
			player.setPlayerLocation(loc);
			player.setCareer((CareerCard)CAREER_CARDS.get(0));
		}
		
	}

}
