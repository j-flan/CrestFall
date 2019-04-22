package myGame;

import java.util.Scanner;

public class CfAreas
{
	// minEnc and maxEnc are the boundaries of your chance for enemy encounters
	// encNumber is the number that is set for your chance of enemy encounter
	// range takes minEnc and maxEnc and gives a variable to be randomized
	// rand is the integer chosen my the randomizer
	// merch is the the players decision at the merchant hub
	// city is a count that gives text based on how many times the player has been to the city
	public static final int minEnc = 1, maxEnc = 2, encNumber = 1, range = maxEnc - minEnc + 1;
	public static int rand, hitDMG, merch, city = 0;

	public static void theLake( )
	{
		Scanner input = new Scanner ( System.in );
		Scanner KeyIn = new Scanner ( System.in );
		char lake; // the lake
		System.out.println ( "You find yourself standing at the edge of a large lake" );
		System.out.println ( "There is only one path leading out and it heads northWest" );
		System.out.println ( "Would you like to look around? y/n" );
		do // check character input
		{
			lake = input.next ( ).toLowerCase ( ).charAt ( 0 );
			if ( lake != 'y' && lake != 'n' )
			{
				System.out.println ( "You must enter y or n" );
			}
		} while ( lake != 'y' && lake != 'n' );
		while ( lake == 'y' )
		{
			rand = (int) ( Math.random ( ) * range + minEnc ); // encounter chance
			if ( encNumber == rand )
			{
				int enemy = (int) ( Math.random ( ) * 4 + 1 ); // random enemy
				switch ( enemy )
				{
					case 1:
						System.out.println ( Enemy.wolves ( ).getText ( ) );
						CfSequence.battleSequence ( Enemy.wolves ( ) );
						break;

					case 2:
						if ( Var.getLadyOfLake ( ) > 0 )
						{
							System.out.println ( "You hear someone singing..." ); // Lady of the Lake
							System.out.println ( "Do you want to check it out? y/n" );
							char help;
							do // check character input
							{
								help = input.next ( ).toLowerCase ( ).charAt ( 0 );
								if ( help != 'y' && help != 'n' )
								{
									System.out.println ( "You must enter y or n" );
								}
							} while ( help != 'y' && help != 'n' );

							if ( help == 'n' ) // Don't look
							{
								System.out.println ( "Thats gotta be a trap... Keep wandering aimlessly" );
							}
							else
							{
								System.out.println ( "You found the lady of the lake" );
								System.out.println ( "Her kind and voiceless words heal your wounds. Full HP!" );
								Var.setHp ( Var.getHpMax ( ) );
								System.out.println ( "She hands you a short sword that glows faintly green.\n"
										+ "The translucent lady disappears upon taking the sword." );
								System.out.println ( "You've acquired PhantomBane! DMG 5-8" );
								System.out.println ( "Do you wish to equip the PhantomBane?\ny/n" );
								do // check character input
								{
									help = input.next ( ).toLowerCase ( ).charAt ( 0 );
									if ( help != 'y' && help != 'n' )
									{
										System.out.println ( "You must enter y or n" );
									}
								} while ( help != 'y' && help != 'n' );
								if ( help == 'y' )
								{
									System.out.println ( "PhantomBane Equipped" );
									Var.setMinDMG ( 5 );
									Var.setMaxDMG ( 8 );
									Var.setLadyOfLake ( -1 );
									Var.setvRapier ( 1 );
									if ( Var.getFighter ( ) == 1 )
									{
										Var.setMaxDMG ( 2 );
									}
									if ( Var.getLoser ( ) == 1 )
									{
										Var.setMaxDMG ( -1 );
									}
									if ( Var.getmDagger ( ) == 0 )
									{
										Var.setMinDMG ( 1 );
									}
								}
								else
								{
									System.out.println ( "The sword fades back into obscurity" );
								}
							}
						}
						else
						{
							System.out.println ( "Here is where you met the lady of the lake" );
							System.out.println ( "You can feel her spirit well up inside of you. Regen 10% MaxHP!" );
							Var.setHp ( Var.getHpMax ( ) / 10 );

							System.out.println ( "HP= " + Var.getHp ( ) );
						}
						break;

					case 3:
						System.out.println ( Enemy.pooPoo ( ).getText ( ) );
						CfSequence.battleSequence ( Enemy.pooPoo ( ) );

						break;

					case 4:
						System.out.println ( Enemy.leech ( ).getText ( ) );
						CfSequence.battleSequence ( Enemy.leech ( ) );

				}
			}
			if ( Var.getHp ( ) < 1 )
			{
				lake = 'n';
			}
			else
			{
				System.out.println ( "Keep Searching the Lake?" );
				do // check character input
				{
					lake = input.next ( ).toLowerCase ( ).charAt ( 0 );
					if ( lake != 'y' && lake != 'n' )
					{
						System.out.println ( "You must enter y or n" );
					}
				} while ( lake != 'y' && lake != 'n' );
			}
		}
	}

	public static int merchantHub( int hpPotCost )
	{
		Scanner input = new Scanner ( System.in );
		Scanner KeyIn = new Scanner ( System.in );
		if ( Var.getMerchCount ( ) == 0 )
		{
			if ( Var.getChaosDemonLife ( ) == 0 )
			{
				System.out
						.println ( "You return to the Merchant Camp.\nYou are relieved to be walking amongst others again" );
				System.out.println ( "Recounting the first time making your way to this oasis,\n"
						+ "You you can't help but feel an emptyness as if you lost a part of yourself on that mountain." );
			}
			System.out.println ( "There are a couple of Vendors hanging about..." );
			System.out.println ( "One of them turns to you and asks \"Are you in need?\" " );
			System.out.println ( "He then unrolls his mat to show you his various wares" );
			System.out.println ( "You hear him whisper what sounds like \"its a secret to everybody\" " );
			Var.setMerchCount ( 1 );
		}
		Var.setDirection ( 1 );
		while ( Var.getDirection ( ) == 1 )
		{
			System.out.println ( "What would you like?" );
			int gGameCost = 5;
			System.out.println ( "0 - Guessing Game (5 gold) Double your money and get points!" );
			int sSwordCost = 10;
			System.out.println ( "1 - A reliable steel Short Sword. DMG: 3-6. (10 gold). In Stock: " + Var.getsSword ( ) );
			int lSwordCost = 15;
			System.out
					.println ( "2 - A Heavy Iron Long Sword (-1 dex). DMG 4-9 (15 gold) In Stock: " + Var.getlSword ( ) );
			int lArmorCost = 15;
			System.out.println ( "3 - Leather Armour. + 1 evade. (15 gold). InStock: " + Var.getlArmor ( ) );
			int mBootsCost = 50;
			System.out.println ( "4 - Mercurial Dancing Boots. + 1 evade  (50 gold). In Stock " + Var.getmBoots ( ) );
			int hPotionCost = 8;
			System.out.println ( "5 - Magic EEL Sauce. Heal 50 HP (8 gold)" );
			hpPotCost = 5;
			System.out.println ( "6 - Magic EEL Sauce to go. Heal 25 HP in battle (5 gold)" );
			System.out.println ( "7 - View Stats" );
			System.out.println ( "8 - Exit Merchant Camp" );
			System.out.println ( "You have " + Var.getGold ( ) + " gold" );
			do
			{
				merch = input.nextInt ( ); // input decision
				if ( merch < 0 || merch > 8 )
				{
					System.out.println ( "You must enter a valid number (1-8)" );
				}
			} while ( merch < 0 || merch > 8 );
			switch ( merch )
			{
				case 0:

					CfSequence.guessingGame ( gGameCost );

					break;

				case 1: // buy short sword
					if ( Var.getsSword ( ) > 0 )
					{
						if ( Var.getGold ( ) >= sSwordCost )
						{
							System.out.println ( "The Short Sword is now yours! DMG: 3-6" );
							Var.setMinDMG ( 3 );
							Var.setMaxDMG ( 6 );
							Var.setGold ( -sSwordCost );
							Var.setsSword ( -1 );
							Var.setvRapier ( 1 );
							if ( Var.getFighter ( ) == 1 )
							{
								Var.setMaxDMG ( 2 );
							}
							if ( Var.getLoser ( ) == 1 )
							{
								Var.setMaxDMG ( -1 );
							}
							if ( Var.getmDagger ( ) == 0 )
							{
								Var.setMinDMG ( 1 );
							}
						}
						else
						{
							System.out.println ( "You are too poor for this item. you have " + Var.getGold ( ) + " gold" );
						}
					}
					else
					{
						System.out.println ( "Sorry, This item is out of stock." );
					}
					break;

				case 2: // buy long sword
					if ( Var.getlSword ( ) > 0 )
					{
						if ( Var.getGold ( ) >= lSwordCost )
						{
							System.out.println ( "The Long Sword is now yours! DMG: 4-9. -1 dex" );
							Var.setMinDMG ( 4 );
							Var.setMaxDMG ( 9 );
							Var.setDex ( -1 );
							Var.setGold ( -lSwordCost );
							Var.setlSword ( -1 );
							Var.setvRapier ( 1 );
							if ( Var.getFighter ( ) == 1 )
							{
								Var.setMaxDMG ( 2 );
							}
							if ( Var.getLoser ( ) == 1 )
							{
								Var.setMaxDMG ( -1 );
							}
							if ( Var.getmDagger ( ) == 0 )
							{
								Var.setMinDMG ( 1 );
							}
						}
						else
						{
							System.out.println ( "You are too poor for this item. you have " + Var.getGold ( ) + " gold" );
						}
					}
					else
					{
						System.out.println ( "Sorry, This item is out of stock." );
					}
					break;

				case 3: // buy leather armor
					if ( Var.getlArmor ( ) > 0 )
					{
						if ( Var.getGold ( ) >= lArmorCost )
						{
							System.out.println ( "The Leather Armor fits! +1 Evade" );
							Var.setEvade ( 1 );
							Var.setGold ( -lArmorCost );
							Var.setlArmor ( -1 );
						}
						else
						{
							System.out.println ( "You are too poor for this item. you have " + Var.getGold ( ) + " gold" );
						}
					}
					else
					{
						System.out.println ( "Sorry, This item is out of stock." );
					}
					break;

				case 4:
					if ( Var.getmBoots ( ) >= 0 )
					{
						if ( Var.getGold ( ) >= mBootsCost )
						{
							System.out.println ( "The strange looking boots fit! +1 Evade. You feel lighter." );
							Var.setEvade ( 1 );
							Var.setGold ( -mBootsCost );
							Var.setmBoots ( -1 );
						}
						else
						{
							System.out.println ( "You are too poor for this item. you have " + Var.getGold ( ) + " gold" );
						}
					}
					else
					{
						System.out.println ( "Sorry, This item is out of stock." );
					}
					break;

				case 5:
					if ( Var.getGold ( ) >= hPotionCost )
					{
						Var.setHp ( 50 );
						Var.setGold ( -hPotionCost );
						Var.setPoints ( -50 );

						System.out.println ( "That Sauce is Awesome! +50 HP. You have " + Var.getHp ( ) + " HP" );
					}
					else
					{
						System.out.println ( "Damn it man! I'm a Merchant, not a Barber! You'll get no charity here. " );
						System.out.println ( "You have " + Var.getGold ( ) + " gold. Go find some more" );
					}
					break;

				case 6:
					if ( Var.getHpPot ( ) < 1 )
					{
						if ( Var.getGold ( ) >= hpPotCost )
						{
							System.out.println ( "You get a bottle of Magic EEl Sauce. Use it wisely" );
							Var.setGold ( -hpPotCost );
							Var.setHpPot ( 1 );
						}
						else
						{
							System.out.println ( "Damn it man! I'm a Merchant, not a Barber! You'll get no charity here. " );
							System.out.println ( "You have " + Var.getGold ( ) + " gold. Go find some more" );
						}
					}
					else
					{
						System.out.println ( "You can only hold one bottle at a time." );
					}
					break;

				case 7:
					System.out.println ( "HP: " + Var.getHp ( ) );
					System.out.println ( "DMG: " + Var.getMinDMG ( ) + " - " + Var.getMaxDMG ( ) );
					System.out.println ( "Dex: " + Var.getDex ( ) );
					System.out.println ( "Evade: " + Var.getEvade ( ) );
					break;

				case 8:
					System.out.println ( "There is a road sign at the far end of camp; It reads:" );
					System.out.println ( "Humble Mountain - West (Road closed due to Extreme Terror)" );
					System.out.println ( "City of CrestFall - South" );
					System.out.println ( "SmellyFart Swamp - southEast" );
					System.out.println (
							"Which way? \n2 - CrestFall, \n3 - Smelly Fart Swamp. \n4 - Hike up Humble Mountain... \n1 - stay at Camp" );
					Var.setMerchCount ( 1 );
					int direction = input.nextInt ( );
					Var.setDirection ( direction );
			}
		}
		return Var.getDirection ( );
	}

	public static void theCity( )
	{
		Scanner input = new Scanner ( System.in );
		Scanner KeyIn = new Scanner ( System.in );
		System.out.println ( "You find yourself standing at the Gates to the City" );
		System.out.println ( "HP = " + Var.getHp ( ) + " gold = " + Var.getGold ( ) );
		System.out.println ( "Press Enter to... Enter" );
		KeyIn.nextLine ( );

		int commonBoard;
		int armorerItems;
		char armorer;
		city = 9;
		System.out.println ( "The City looks like it has seen better days..." );
		System.out.println (
				"There are only a handful of people walking about and there is rising smoke from multiple dwellings." );
		System.out.println ( "There is a large map of the City with a message board, that could be helpful." );
		System.out.println ( "Press Enter" );
		KeyIn.nextLine ( );

		while ( city != 6 )
		{
			System.out.println ( "City Common Board" );
			System.out.println ( "0 - EEL Sauce Vendor. 1 Bottle (8 gold)" );
			System.out.println ( "1 - Read Messages" );
			System.out.println ( "2 - View Stats" );
			System.out.println ( "3 - Visit the Armorer" );
			int innCost = 15;
			System.out.println ( "4 - Get a room at the Inn. (15 gold)" );
			System.out.println ( "5 - Wander aimlessly about town" );
			System.out.println ( "6 - Leave the City" );
			System.out.println ( "7 - Guessing Game (5) Double your gold and get points!" );
			System.out.println ( "You have " + Var.getGold ( ) + " gold" );
			do
			{
				commonBoard = input.nextInt ( ); // input decision
				if ( commonBoard < 0 || commonBoard > 7 )
				{
					System.out.println ( "You must enter a valid number (0-7)" );
				}
			} while ( commonBoard < 0 || commonBoard > 7 );
			switch ( commonBoard )
			{
				case 0:
					int hpPotCost = 8;
					if ( Var.getHpPot ( ) < 1 )
					{
						if ( Var.getGold ( ) >= hpPotCost )
						{
							System.out.println ( "You get a bottle of Magic EEl Sauce. Use it wisely" );
							Var.setGold ( -hpPotCost );
							Var.setHpPot ( 1 );
						}
						else
						{
							System.out.println ( "Damn it man! I'm a Merchant, not a Barber! You'll get no charity here. " );
							System.out.println ( "You have " + Var.getGold ( ) + " gold. Go find some more" );
						}
					}
					else
					{
						System.out.println ( "You can only hold one bottle at a time." );
					}
					break;

				case 1: // Read Messages
					System.out.println ( "1.) Have you purchased your flame retardant wall dividers yet?" );
					System.out.println ( "2.) An increase in fire raining from the sky has prompted the city treasurer" );
					System.out.println ( "\tto offer a reward of 50 gold to whomever can put an end to it." );
					System.out.println ( "3.) Please attend tomorrows memorial service for the Brave Soldiers" );
					System.out.println ( "\twho went to the mountain and gave their lives to help our fair City " );
					System.out.println ( "Press Enter to  Return to Common Board" );
					KeyIn.nextLine ( );
					break;

				case 2: // View stats
					System.out.println ( "HP: " + Var.getHp ( ) + " / " + Var.getHpMax ( ) );
					System.out.println ( "DMG: " + Var.getMinDMG ( ) + " - " + Var.getMaxDMG ( ) );
					System.out.println ( "Dex: " + Var.getDex ( ) );
					System.out.println ( "Evade: " + Var.getEvade ( ) );
					break;

				case 3: // visit armorer
					System.out.println ( "You enter the Armory and notice it's missing 2 walls." );
					System.out.println ( "There is only an Apprentice Armorer tending the shop." );
					System.out.println ( "You learn that the Master Armorer was killed in the last fallout" );
					System.out.println (
							"Apprentice: \"I've managed to salvage a few items my master created, please have a look\"" );
					System.out.println ( "Buy something? y/n" );
					do // check character input
					{
						armorer = input.next ( ).toLowerCase ( ).charAt ( 0 );
						if ( armorer != 'y' && armorer != 'n' )
						{
							System.out.println ( "You must enter y or n, Buy Something?" );
						}
					} while ( armorer != 'y' && armorer != 'n' );
					while ( armorer != 'n' )
					{
						System.out.println ( "What would you like?" );
						int mDaggerCost = 30;
						System.out.println ( "1 - Jeweled Dagger. Off-hand, +1 Min DMG, + 1 Dex. (30 gold) In stock: "
								+ Var.getmDagger ( ) );
						int mShieldCost = 30;
						System.out.println (
								"2 - Crested Shield. Off-hand, +1 Resistance. (30 gold) In stock: " + Var.getmShield ( ) );
						int bSwordCost = 50;
						System.out
								.println ( "3 - Snowy Bastard Sword. 6 - 12 DMG. (50 gold) In stock: " + Var.getbSword ( ) );
						int vRapierCost = 60;
						System.out.println (
								"4 - Void Rapier. 5 - 11 DMG. Heals 1 HP on Hit. - 10 Max HP in battle" + Var.getvRapier ( ) );
						System.out.println ( "5 - Return to Armorer Entrance" );
						System.out.println ( "(You may only hold one Off-hand item, unless you grow and extra hand...)" );
						armorerItems = input.nextInt ( );
						switch ( armorerItems )
						{
							// armorer case dagger
							case 1:
								if ( Var.getmDagger ( ) > 0 )
								{
									if ( Var.getGold ( ) >= mDaggerCost )
									{
										if ( Var.getmShield ( ) == 0 )
										{
											System.out.println ( "You ditch your shield and it vanishes! -1 Resistance" );
											System.out.println ( "Where you left it now lies a few gold coins... + 10 gold" );
											Var.setHpMax ( -25 );
											Var.setmShield ( 1 );
											Var.setGold ( 10 );
										}
										System.out.println ( "The Dagger's hilt feels like it was made for your hand" );
										System.out.println ( "+1 Min DMG, +1 Dex" );
										Var.setMinDMG ( 1 );
										Var.setDex ( 1 );
										Var.setGold ( -mDaggerCost );
										Var.setmDagger ( -1 );

									}
									else
									{
										System.out.println (
												"You are too poor for this item. you have " + Var.getGold ( ) + " gold" );
									}
								}
								else
								{
									System.out.println ( "Sorry, This item is out of stock." );
								}
								break;

							// armorer case shield
							case 2:
								if ( Var.getmShield ( ) > 0 )
								{
									if ( Var.getGold ( ) >= mShieldCost )
									{
										if ( Var.getmDagger ( ) == 0 )
										{
											System.out.println (
													"You set aside the Jeweled Dagger and it vanishes! -1 MinDMG, -1 Dex" );
											System.out.println ( "A few coins materialize in its place. +10 gold" );
											Var.setMinDMG ( -1 );
											Var.setDex ( -1 );
											Var.setGold ( 10 );
											Var.setmDagger ( 1 );
										}
										System.out.println (
												"You grab your new shield and feel full of Vigor! +1 resistance = +25 Max HP" );
										Var.setHpMax ( 25 );
										Var.setGold ( -mShieldCost );
										Var.setmShield ( -1 );

									}
									else
									{
										System.out.println (
												"You are too poor for this item. you have " + Var.getGold ( ) + " gold" );
									}
								}
								else
								{
									System.out.println ( "Sorry, This item is out of stock." );
								}
								break;

							// Armorer case bastard sword
							case 3:
								if ( Var.getbSword ( ) > 0 )
								{
									if ( Var.getGold ( ) >= bSwordCost )
									{
										System.out.println (
												"Gripping the pommel, a chill flows from your palm to the top of your head." );
										System.out.println ( "It feels as though you pulled this sword from its own grave" );
										System.out.println ( "DMG = 6 - 12" );
										Var.setMinDMG ( 6 );
										Var.setMaxDMG ( 12 );
										Var.setGold ( -bSwordCost );
										Var.setbSword ( -1 );
										Var.setvRapier ( 1 );
										if ( Var.getFighter ( ) == 1 )
										{
											Var.setMaxDMG ( 2 );
										}
										if ( Var.getLoser ( ) == 1 )
										{
											Var.setMaxDMG ( -1 );
										}
										if ( Var.getmDagger ( ) == 0 )
										{
											Var.setMinDMG ( 1 );
										}
									}
									else
									{
										System.out.println (
												"You are too poor for this item. you have " + Var.getGold ( ) + " gold" );
									}
								}
								else
								{
									System.out.println ( "Sorry, This item is out of stock." );
								}
								break;

							// armorer case, void rapier
							case 4:
								if ( Var.getvRapier ( ) > 0 )
								{
									if ( Var.getGold ( ) >= vRapierCost )
									{
										System.out.println ( "Gripping the pommel, you get the feeling of a ghostly tentacle\n"
												+ "weaving its way through the tendons of your forearm." );
										System.out.println ( "DMG = 5 - 11. Max HP -10.\nYou feel more vascular." );
										Var.setMinDMG ( 5 );
										Var.setMaxDMG ( 11 );
										Var.setGold ( -vRapierCost );
										Var.setvRapier ( -1 );
										if ( Var.getFighter ( ) == 1 )
										{
											Var.setMaxDMG ( 2 );
										}
										if ( Var.getLoser ( ) == 1 )
										{
											Var.setMaxDMG ( -1 );
										}
										if ( Var.getmDagger ( ) == 0 )
										{
											Var.setMinDMG ( 1 );
										}
									}
									else
									{
										System.out.println (
												"You are too poor for this item. you have " + Var.getGold ( ) + " gold" );
									}
								}
								else
								{
									System.out.println ( "Sorry, This item is out of stock." );
								}

							case 5:
								System.out.println ( "Return to Common Board? y/n" );
								do // check character input
								{
									armorer = input.next ( ).toLowerCase ( ).charAt ( 0 );
									if ( armorer != 'y' && armorer != 'n' )
									{
										System.out.println ( "You must enter y or n, Return to Common Board?" );
									}
								} while ( armorer != 'y' && armorer != 'n' );
								if ( armorer == 'y' ) // flip decision
								{
									armorer = 'n';
								}
								else if ( armorer == 'n' )
								{
									armorer = 'y';
								}
						} // end armorerItem switch
					} // exit armorer
					break;

				case 4:
					if ( Var.getGold ( ) >= innCost )
					{
						System.out.println ( "You recieve a good nights rest. + 75 HP" );
						Var.setHp ( 75 );
						Var.setPoints ( -75 );
						Var.setGold ( -innCost );
					}
					else
					{
						System.out.println ( "NO FREE-LOADING! Go make some gold, you Dirty BUM!" );
					}
					break;

				case 5:

					// City Search
					cityWalk ( );

					break;

				case 6:
					// leave city
					int carriage = 10;
					System.out.println ( "Are you sure you  want to leave the City?" );
					System.out.println ( "Enter 6 to confirm. Enter 0 to return to Common Board" );
					city = input.nextInt ( );
					if ( city == 6 )
					{
						System.out.println ( "Where to?" );
						System.out.println ( "1 - Take Carriage back to the Merchant Camp fork. (10 gold)" );
						System.out.println ( "2 - Walk back to Merchant Camp fork" );
						if ( Var.getChaosDemonLife ( ) == 0 )
						{ // after killing chaos demon
							System.out.println (
									"3 - A man standing at the City Gates recognizes you\nas the one who ended the rain of fire" );
							System.out.println (
									"Not knowing how he knows this, you aknowledge him\nand he offers you a free ride south with his caravan." );
						}
						int direction = input.nextInt ( );
						Var.setDirection ( direction );
						if ( Var.getDirection ( ) == 1 )
						{
							if ( Var.getGold ( ) >= carriage )
							{
								Var.setGold ( -carriage );
								merch = 0;
								Var.setMerchCount ( -1 );
								System.out.println (
										"The ride back to the Merchant Camp lasts the rest of\n the day and night, and is not very exciting\n" );

							}
							else
							{
								System.out.println ( "It's not that much money, go get some more." );
								city = 9;
							}
						}
						else if ( Var.getDirection ( ) == 3 && Var.getChaosDemonLife ( ) == 0 )
						{
							System.out.println ( "You desire to leave this place as there is nothing left here for you." );
							System.out.println (
									"(this option will end this chapter of the game\nand you may not be able to return)\nContinue? y/n" );
							int citySearch;
							do // check character input
							{
								citySearch = input.next ( ).toLowerCase ( ).charAt ( 0 );
								if ( citySearch != 'y' && citySearch != 'n' )
								{
									System.out.println ( "You must enter y or n" );
								}
							} while ( citySearch != 'y' && citySearch != 'n' );
							if ( citySearch == 'y' )
							{
								System.out.println (
										"The cloaked carriage driver gives you a nod \nand asks of you only to help him load up his shipment" );
								System.out.println (
										"When you ask him exactly where you're going\nhe says \" 21 days South and 6 days East\"\n" );
								System.out.println (
										"Feeling perfectly fine with that answer, you help load the cart.\nPress Enter" );
								KeyIn.nextLine ( );

								Var.setChapter ( 3 );

								break;
							}
							else
							{
								System.out.println (
										"You decide there are some things you need to wrap-up and take another look at the board...\n" );
								city = 9;
							}
						}
						else
						{
							Var.setDirection ( 1 );
						}
					}
					break;

				case 7:

					int gGameCost = 5;
					CfSequence.guessingGame ( gGameCost );

			}// end commonBoard Switch
			if ( Var.getHp ( ) < 1 )
			{
				break;
			}
		} // end city decision
	}

	public static void cityWalk( )
	{
		Scanner input = new Scanner ( System.in );
		Scanner KeyIn = new Scanner ( System.in );
		char citySearch;
		System.out.println ( "Lets walk about town, shall we? y/n" );
		do // check character input
		{
			citySearch = input.next ( ).toLowerCase ( ).charAt ( 0 );
			if ( citySearch != 'y' && citySearch != 'n' )
			{
				System.out.println ( "You must enter y or n, press 'n' to return to Common Board." );
			}
		} while ( citySearch != 'y' && citySearch != 'n' );
		while ( citySearch == 'y' )
		{
			rand = (int) ( Math.random ( ) * range + minEnc ); // encounter chance
			if ( encNumber == rand )
			{
				int enemy = (int) ( Math.random ( ) * 4 + 1 ); // random enemy
				switch ( enemy )
				{
					case 1:
						System.out.println ( Enemy.drunkass ( ).getText ( ) );
						CfSequence.battleSequence ( Enemy.drunkass ( ) );

						break;

					case 2: // you find the girl
						if ( Var.getVictim ( ) > 0 )
						{
							System.out.println ( "You hear a whisper. As you listen more closely it sounds \n "
									+ "more like a hushed scream from down the alley" ); // rescue
							System.out.println ( "Do you want to check it out? y/n" );
							char help;
							do // check character input
							{
								help = input.next ( ).toLowerCase ( ).charAt ( 0 );
								if ( help != 'y' && help != 'n' )
								{
									System.out.println ( "You must enter y or n" );
								}
							} while ( help != 'y' && help != 'n' );

							if ( help == 'n' ) // Don't look
							{
								System.out.println ( "That's way too Creepy. You walk the other way" );
							}
							else
							{
								System.out.println ( Enemy.gVandal ( ).getText ( ) );
								CfSequence.battleSequence ( Enemy.gVandal ( ) );
								if ( Var.getHp ( ) > 0 )
								{
									Var.setHpMax ( 25 ); // increase max hp by 25
									Var.setVictim ( -1 ); // girl saved
								}
							}
						}
						else
						{
							System.out.println ( "You wander back to the spot where you saved that young lady" );
							System.out.println ( "Feeling Nostalgic?\n ...Oh a coin! + 1 gold" );
							Var.setGold ( 1 );
						}
						break;

					case 3:

						System.out.println ( Enemy.sRat ( ).getText ( ) );
						CfSequence.battleSequence ( Enemy.sRat ( ) );

						break;

					case 4:

						System.out.println ( Enemy.cultist ( ).getText ( ) );
						CfSequence.battleSequence ( Enemy.cultist ( ) );

				}
			}
			if ( Var.getHp ( ) > 0 )
			{
				System.out.println ( "Keep walking about aimlessly? y/n" );
				do // check character input
				{
					citySearch = input.next ( ).toLowerCase ( ).charAt ( 0 );
					if ( citySearch != 'y' && citySearch != 'n' )
					{
						System.out.println ( "You must enter y or n" );
					}
				} while ( citySearch != 'y' && citySearch != 'n' );
			}
			else
			{
				citySearch = 'n';
			}
		}
	}

	public static void mountainTop( )
	{
		Scanner input = new Scanner ( System.in );
		Scanner KeyIn = new Scanner ( System.in );
		System.out
				.println ( "The top of the mountain is a meandering trenchwork of jagged rock and snow covered boulders." );
		System.out.println ( "You will need to find another way down..." );
		System.out.println ( "Would you like to look around first? y/n" );
		char mountain = 'a';
		do // check character input
		{
			mountain = input.next ( ).toLowerCase ( ).charAt ( 0 );
			if ( mountain != 'y' && mountain != 'n' )
			{
				System.out.println ( "You must enter y or n" );
			}
		} while ( mountain != 'y' && mountain != 'n' );

		while ( mountain == 'y' )
		{
			rand = (int) ( Math.random ( ) * range + minEnc ); // encounter chance
			if ( encNumber == rand )
			{
				int enemy = (int) ( Math.random ( ) * 4 + 1 ); // random enemy
				switch ( enemy )
				{
					case 1:

						System.out.println ( Enemy.fSprite ( ).getText ( ) );
						CfSequence.battleSequence ( Enemy.fSprite ( ) );

						break;

					case 2: // Chaos Demon
						if ( Var.getChaosDemonLife ( ) > 0 )
						{
							System.out.println ( "A voice that is much different than your own begins to tug on your mind" ); // rescue
							System.out.println ( "Give in to the foreign voice? y/n" );
							char help;
							do // check character input
							{
								help = input.next ( ).toLowerCase ( ).charAt ( 0 );
								if ( help != 'y' && help != 'n' )
								{
									System.out.println ( "You must enter y or n" );
								}
							} while ( help != 'y' && help != 'n' );

							if ( help == 'n' ) // Don't look
							{
								System.out.println ( "That's way too Creepy. You shut the voice out of your mind." );
							}
							else
							{
								System.out.println ( Enemy.chaosDemon ( ).getText ( ) );

								System.out.println ( "Press Enter" );
								KeyIn.nextLine ( );

								// Boss battle

								CfSequence.battleSequence ( Enemy.chaosDemon ( ) );

								if ( Var.getHp ( ) > 0 )
								{
									Var.setHpMax ( 25 ); // hp Max + 25
									Var.setHp ( Var.getHpMax ( ) ); // full hp
									Var.setChaosDemonLife ( -1 ); // chaos demon dies
								}
							}
						}
						else
						{
							System.out.println ( "You wander back to the spot where you fought the Chaos Demon" );
							System.out.println ( "Feeling Nostalgic?\n ...Oh a coin! + 1 gold" );
							Var.setGold ( 1 );
						}
						break;

					case 3:
						System.out.println ( Enemy.mTroll ( ).getText ( ) );
						CfSequence.battleSequence ( Enemy.mTroll ( ) );
						break;

					case 4:
						System.out.println ( Enemy.eBat ( ).getText ( ) );
						CfSequence.battleSequence ( Enemy.eBat ( ) );

				}
			}
			if ( Var.getHp ( ) > 0 )
			{
				System.out.println ( "Keep Searching the Mountain?" );
				do // check character input
				{
					mountain = input.next ( ).toLowerCase ( ).charAt ( 0 );
					if ( mountain != 'y' && mountain != 'n' )
					{
						System.out.println ( "You must enter y or n" );
					}
				} while ( mountain != 'y' && mountain != 'n' );
			}
			else
			{
				mountain = 'n';
			}
		}
		if ( Var.getHp ( ) > 0 )
		{
			System.out.println ( "You find an old merchant trail marker and head Down the Mountain" ); // other side of
																																		// mountain
			System.out.println ( "Enter to continue" );
			KeyIn.nextLine ( );
		}
	}

	public static void homeHub( )
	{
		Scanner input = new Scanner ( System.in );
		System.out.println ( "\"Welcome friend, what would you like?\"\n" );
		int gGameCost = 5;
		System.out.println ( "0 - Play Guessing Game. (5 gold)" );
		int hpPotCost = 20;
		System.out.println ( "1 - Snake Oil. Heal 40 HP. (20 gold)" );
		int dynamiteCost = 25;
		System.out.println ( "2 - Dynamite. Deals 30 DMG to the enemy and 5 damage to you. (25 gold)" );
		System.out.println ( "3 - Go Home and Rest. Full HP" );
		System.out.println ( "4 - Leave Village" );
		System.out.println ( "5 - Get Advice" );
		int slArmorCost = 220;
		int pDaggerCost = 150;
		int sKatanaCost = 200;
		// if marsh OR cliffs are complete
		if ( Var.getHordeLife ( ) == 0 || Var.getGryphonLife ( ) == 0 )
		{
			System.out.println ( "6 - Parrying Dagger. dex + 2, + 1 minDMG off-hand (150 gold)" );
		}
		// if marsh AND cliffs are complete
		if ( Var.getHordeLife ( ) == 0 && Var.getGryphonLife ( ) == 0 )
		{
			System.out.println ( "7 - Studded Leather Armor. + 2 Evade (220 gold)" );
			System.out.println ( "8 - Shadow Katana. 7 - 13 DMG. Heals 1 HP on Hit. - 10 Max HP in battle. (200 gold) " );
		}
		System.out.println ( "\nYou Have " + Var.getGold ( ) + "gold" );
		do
		{
			merch = input.nextInt ( ); // input decision
			if ( merch < 0 || merch > 8 )
			{
				System.out.println ( "You must enter a valid number" );
			}
		} while ( merch < 0 || merch > 8 );
		switch ( merch )
		{

			case 0:

				CfSequence.guessingGame ( gGameCost );

				break;

			case 1:
				if ( Var.getHpPot ( ) < 1 )
				{
					if ( Var.getGold ( ) >= hpPotCost )
					{
						System.out.println ( "You get a bottle of Snake Oil. Use it wisely" );
						Var.setGold ( -hpPotCost );
						Var.setHpPot ( 1 );
					}
					else
					{
						System.out.println ( "I'm your friend but I still require payment. " );
						System.out.println ( "You only have " + Var.getGold ( ) + " gold..." );
					}
				}
				else
				{
					System.out.println ( "You can only hold one bottle at a time." );
				}
				break;

			case 2:
				if ( Var.getDynamite ( ) < 1 )
				{
					if ( Var.getGold ( ) >= dynamiteCost )
					{
						System.out.println ( "You get a Stick of Dynamite. Use it recklessly" );
						Var.setGold ( -dynamiteCost );
						Var.setDynamite ( 1 );
					}
					else
					{
						System.out.println ( "I'm your friend but I still require payment. " );
						System.out.println ( "You only have " + Var.getGold ( ) + " gold..." );
					}
				}
				else
				{
					System.out.println ( "You can only hold one explosive at a time." );
				}
				break;

			case 3:
				Var.setHp ( Var.getHpMax ( ) );
				System.out.println ( "Your bed is so comfy... HP Refiled: " + Var.getHp ( ) + " / " + Var.getHpMax ( ) );
				break;

			case 4:
				System.out.println ( "Where to?" );
				System.out.println (
						"1 - Stay Home\n2 - Shimmering Valley\n3 - Arid Plains\n4 - Frozen Desert\n5 - Sleeping Forest" );
				int direction = input.nextInt ( );
				Var.setDirection ( direction );
				break;

			case 5:

				if ( Var.getLitchKing ( ) == 0 )
				{
					System.out.println ( "The Smoke from the volcano has darkened a bit recently...\n" );
				}
				else if ( Var.getHordeLife ( ) == 0 || Var.getGryphonLife ( ) == 0 )
				{
					System.out.println (
							"I heard there is an Ancient Tomb past the sleeping forest that is impossible to open.\n "
									+ "There is also rumor that the the crystalized desert was a cursed\n"
									+ "during the Gryphon Riders Crusade that ended mysteriously 1200 years ago.\n" );
				}
				else
				{
					System.out.println ( "I hear travelers lose whole days of travel in the sleeping forest\n"
							+ "and the plains are teaming with horrible creatures."
							+ "\nThe Volcano has been recently erupting beyond the plains as well. "
							+ "I don't recommend going in those directions\n"
							+ "If you're looking for adventure, The shimmering Valley is beautiful...\n"
							+ "And that's about all I know right now, I hope that helps.\n" );
				}
				break;

			case 6:
				if ( Var.getHordeLife ( ) == 0 || Var.getGryphonLife ( ) == 0 )
				{
					if ( Var.getpDagger ( ) > 0 )
					{
						if ( Var.getGold ( ) >= pDaggerCost )
						{
							if ( Var.getmShield ( ) == 0 )
							{
								System.out.println ( "You ditch your shield and it vanishes! -1 Resistance" );
								System.out.println ( "Where you left it now lies a few gold coins... + 10 gold" );
								Var.setHpMax ( 25 );
								Var.setmShield ( 1 );
								Var.setGold ( 10 );
							}
							else if ( Var.getmDagger ( ) == 0 )
							{
								System.out.println ( "You set aside the Jeweled Dagger and it vanishes! -1 MinDMG, -1 Dex" );
								System.out.println ( "A few coins materialize in its place. +10 gold" );
								Var.setMinDMG ( -1 );
								Var.setDex ( -1 );
								Var.setGold ( 10 );
								Var.setmDagger ( 1 );
							}
							System.out.println ( "The Parrying Dagger's hilt feels like it was made for your hand" );
							System.out.println ( "+1 Min DMG, +2 Dex" );
							Var.setMinDMG ( 1 );
							Var.setDex ( 2 );
							Var.setGold ( -pDaggerCost );
							Var.setpDagger ( -1 );

						}
						else
						{
							System.out.println ( "You are too poor for this item. you have " + Var.getGold ( ) + " gold" );
						}
					}
					else
					{
						System.out.println ( "Sorry, This item is out of stock." );
					}
				}
				else
				{
					System.out.println ( "No Cheating!" );
				}
				break;

			case 7:
				if ( Var.getHordeLife ( ) == 0 && Var.getGryphonLife ( ) == 0 )
				{
					if ( Var.getSlArmor ( ) > 0 )
					{
						if ( Var.getGold ( ) >= slArmorCost )
						{
							if ( Var.getlArmor ( ) == 0 )
							{
								System.out.println (
										"You're glad to get some new armor and remove your old leather armor. -1 Evade" );
								System.out.println ( "It would be best if it was burned...\n" );
								Var.setEvade ( -1 );
							}
							System.out.println ( "The Studded Leather Armor fits like a glove. +2 Evade" );
							Var.setEvade ( 2 );
							Var.setGold ( -slArmorCost );
							Var.setSlArmor ( -1 );
						}
						else
						{
							System.out.println ( "You are too poor for this item. you have " + Var.getGold ( ) + " gold" );
						}
					}
					else
					{
						System.out.println ( "Sorry, This item is out of stock." );
					}
				}
				else
				{
					System.out.println ( "No Cheating!" );
				}
				break;

			case 8:
				if ( Var.getHordeLife ( ) == 0 && Var.getGryphonLife ( ) == 0 )
				{
					if ( Var.getsKatana ( ) > 0 )
					{
						if ( Var.getGold ( ) >= sKatanaCost )
						{
							System.out.println ( "Gripping the pommel, you get the feeling of a ghostly tentacle\n"
									+ "weaving its way through the tendons of your forearm." );
							System.out.println ( "DMG = 7 - 13. Max HP -10 in Battle.\nYou feel more vascular." );
							Var.setMinDMG ( 7 );
							Var.setMaxDMG ( 13 );
							Var.setGold ( -sKatanaCost );
							Var.setsKatana ( -1 );
							Var.setvRapier ( 1 );
							Var.setcKukri ( 1 );
							Var.setlAxe ( 1 );
							if ( Var.getFighter ( ) == 1 )
							{
								Var.setMaxDMG ( 2 );
							}
							if ( Var.getLoser ( ) == 1 )
							{
								Var.setMaxDMG ( -1 );
							}
							if ( Var.getmDagger ( ) == 0 || Var.getpDagger ( ) == 0 )
							{
								Var.setMinDMG ( 1 );
							}

						}
						else
						{
							System.out.println ( "You are too poor for this item. you have " + Var.getGold ( ) + " gold" );
						}
					}
					else
					{
						System.out.println ( "Sorry, This item is out of stock." );
					}
				}
				else
				{
					System.out.println ( "No Cheating!" );
				}
		}
	}

	public static void sValley( )
	{
		Scanner input = new Scanner ( System.in );
		Scanner KeyIn = new Scanner ( System.in );
		char valley; // Shimmering Valley
		System.out.println ( "The trail widens and then branches into many directions" );
		System.out.println ( "From here you can either head back home, or continue East to the White Marsh" );
		System.out.println ( "First, Would you like to look around the meandering trails? y/n" );
		do // check character input
		{
			valley = input.next ( ).toLowerCase ( ).charAt ( 0 );
			if ( valley != 'y' && valley != 'n' )
			{
				System.out.println ( "You must enter y or n" );
			}
		} while ( valley != 'y' && valley != 'n' );
		while ( valley == 'y' )
		{
			rand = (int) ( Math.random ( ) * range + minEnc ); // encounter chance
			if ( encNumber == rand )
			{
				int enemy = (int) ( Math.random ( ) * 4 + 1 ); // random enemy
				switch ( enemy )
				{
					case 1:

						System.out.println ( Enemy.fern ( ).getText ( ) );
						CfSequence.battleSequence ( Enemy.fern ( ) );

						break;

					case 2:
						System.out.println ( "The sound of a half-dozen horses comes from up ahead" ); // traveling merchant
						System.out.println ( "Do you want to check it out? y/n" );
						char help;
						do // check character input
						{
							help = input.next ( ).toLowerCase ( ).charAt ( 0 );
							if ( help != 'y' && help != 'n' )
							{
								System.out.println ( "You must enter y or n" );
							}
						} while ( help != 'y' && help != 'n' );

						if ( help == 'n' ) // Don't look
						{
							System.out.println (
									"Frightened, you run off into the brush and hide until the sound\nof the horses fade into the distance..." );
						}
						else
						{
							System.out.println (
									"You meet a merchant and his small caravan on the trail\nand he gladly shows you his wares" );
							int valleyMerch = 0;
							while ( valleyMerch != 4 )
							{
								System.out.println ( "\"What would you like?\"\n" );
								int gGameCost = 5;
								System.out.println ( "0 - Play Guessing Game. (5 gold)" );
								int hpPotCost = 35;
								System.out.println ( "1 - Snake Oil. Heal 40 HP. (35 gold)" );
								int dynamiteCost = 40;
								System.out.println ( "2 - Dynamite. Deals 30 DMG to the enemy and 5 damage to you. (40 gold)" );
								int cKukriCost = 75;
								System.out.println ( "3 - Coral Kukri. 6 - 11 DMG, 33% Chance to Poison (75 gold)" );
								System.out.println ( "4 - Take your Leave" );
								System.out.println ( "\nYou Have " + Var.getGold ( ) + "gold" );
								do
								{
									valleyMerch = input.nextInt ( ); // input decision
									if ( valleyMerch < 0 || valleyMerch > 4 )
									{
										System.out.println ( "You must enter a valid number (0 - 4)" );
									}
								} while ( valleyMerch < 0 || valleyMerch > 4 );
								switch ( valleyMerch )
								{
									case 0:

										CfSequence.guessingGame ( gGameCost );

										break;

									case 1:
										if ( Var.getHpPot ( ) < 1 )
										{
											if ( Var.getGold ( ) >= hpPotCost )
											{
												System.out.println ( "You get a bottle of Snake Oil. Use it wisely" );
												Var.setGold ( -hpPotCost );
												Var.setHpPot ( 1 );
											}
											else
											{
												System.out.println ( "I'm your friend but I still require payment. " );
												System.out.println ( "You only have " + Var.getGold ( ) + " gold..." );
											}
										}
										else
										{
											System.out.println ( "You can only hold one bottle at a time." );
										}
										break;

									case 2:
										if ( Var.getDynamite ( ) < 1 )
										{
											if ( Var.getGold ( ) >= dynamiteCost )
											{
												System.out.println ( "You get a Stick of Dynamite. Use it recklessly" );
												Var.setGold ( -dynamiteCost );
												Var.setDynamite ( 1 );
											}
											else
											{
												System.out.println ( "I'm your friend but I still require payment. " );
												System.out.println ( "You only have " + Var.getGold ( ) + " gold..." );
											}
										}
										else
										{
											System.out.println ( "You can only hold one explosive at a time." );
										}
										break;

									case 3:
										if ( Var.getcKukri ( ) > 0 )
										{
											if ( Var.getGold ( ) >= cKukriCost )
											{
												System.out.println (
														"The Coral Kukri is now yours! DMG: 6-11. careful not to poison yourself..." );
												Var.setMinDMG ( 6 );
												Var.setMaxDMG ( 11 );
												Var.setGold ( -cKukriCost );
												Var.setcKukri ( -1 );
												Var.setvRapier ( 1 );
												Var.setlAxe ( 1 );
												Var.setsKatana ( 1 );
												if ( Var.getFighter ( ) == 1 )
												{
													Var.setMaxDMG ( 2 );
												}
												if ( Var.getLoser ( ) == 1 )
												{
													Var.setMaxDMG ( -1 );
												}
												if ( Var.getmDagger ( ) == 0 || Var.getpDagger ( ) == 0 )
												{
													Var.setMinDMG ( 1 );
												}
											}
											else
											{
												System.out.println (
														"You are too poor for this item. you have " + Var.getGold ( ) + " gold" );
											}
										}
										else
										{
											System.out.println ( "Sorry, This item is out of stock." );
										}

										break;

									case 4:

								}// end switch
							} // end merchant
						}
						break;

					case 3:

						System.out.println ( Enemy.viper ( ).getText ( ) );
						CfSequence.battleSequence ( Enemy.viper ( ) );

						break;

					case 4:
						System.out.println ( Enemy.malboro ( ).getText ( ) );
						CfSequence.battleSequence ( Enemy.malboro ( ) );

				}
			}
			if ( Var.getHp ( ) < 1 )
			{
				valley = 'n';
			}
			else
			{
				System.out.println ( "Keep looking around?" );
				do // check character input
				{
					valley = input.next ( ).toLowerCase ( ).charAt ( 0 );
					if ( valley != 'y' && valley != 'n' )
					{
						System.out.println ( "You must enter y or n" );
					}
				} while ( valley != 'y' && valley != 'n' );
			}
		}
	}

	public static void whiteMarsh( )
	{
		Scanner input = new Scanner ( System.in );
		Scanner KeyIn = new Scanner ( System.in );
		System.out.println ( "The White Marsh is Humid, Moist, Creepy, and blindingly white with low visibility." );
		System.out.println ( "Would you like to look around? y/n" );
		char mountain = 'a';
		do // check character input
		{
			mountain = input.next ( ).toLowerCase ( ).charAt ( 0 );
			if ( mountain != 'y' && mountain != 'n' )
			{
				System.out.println ( "You must enter y or n" );
			}
		} while ( mountain != 'y' && mountain != 'n' );

		while ( mountain == 'y' )
		{
			rand = (int) ( Math.random ( ) * range + minEnc ); // encounter chance
			if ( encNumber == rand )
			{
				int enemy = (int) ( Math.random ( ) * 4 + 1 ); // random enemy
				switch ( enemy )
				{
					case 1:
						System.out.println ( Enemy.litchling ( ).getText ( ) );
						CfSequence.battleSequence ( Enemy.litchling ( ) );

						break;

					case 2: // Horde
						if ( Var.getHordeLife ( ) > 0 )
						{
							System.out.println ( "You come to a large circle of long dead trees.\n"
									+ "As you approach, you notice a glow eminating from the mist." ); // boss
							System.out.println ( "Move closer? y/n" );
							char help;
							do // check character input
							{
								help = input.next ( ).toLowerCase ( ).charAt ( 0 );
								if ( help != 'y' && help != 'n' )
								{
									System.out.println ( "You must enter y or n" );
								}
							} while ( help != 'y' && help != 'n' );

							if ( help == 'n' ) // Don't look
							{
								System.out.println ( "No fucking way..." );
							}
							else
							{
								System.out.println ( Enemy.horde ( ).getText ( ) );
								Var.setHp ( Var.getHpMax ( ) / 2 );

								System.out.println ( "Press Enter" );
								KeyIn.nextLine ( );

								// Boss battle

								CfSequence.battleSequence ( Enemy.horde ( ) );

								if ( Var.getHp ( ) > 0 )
								{
									Var.setHpMax ( 10 ); // hp Max + 10
									Var.setHp ( Var.getHpMax ( ) / 2 ); // full hp

									Var.setHordeLife ( -1 ); // horde dies

								}
							}
						}
						else
						{
							System.out.println ( "You wander back to the spot where you fought the Necro Horde" );
							System.out.println ( "Feeling Nostalgic?\n ...Oh a coin! + 1 gold" );
							Var.setGold ( 1 );
						}
						break;

					case 3:
						System.out.println ( "An Enormous tree stands alone in a small clearing.\n"
								+ "As you pass, you notice the branches swaying though there is no breeze." ); // tree
						System.out.println ( "Investigate the tree? y/n" );
						char help;
						do // check character input
						{
							help = input.next ( ).toLowerCase ( ).charAt ( 0 );
							if ( help != 'y' && help != 'n' )
							{
								System.out.println ( "You must enter y or n" );
							}
						} while ( help != 'y' && help != 'n' );

						if ( help == 'n' ) // Don't look
						{
							System.out.println ( "Thats one freaky tree bruh. Exit stage right." );
						}
						else
						{
							int ruse = (int) ( Math.random ( ) * 10 );
							if ( ruse >= 5 )
							{
								System.out.println ( Enemy.mCrows ( ).getText ( ) );
								CfSequence.battleSequence ( Enemy.mCrows ( ) );
							}
							else
							{
								System.out.println ( "You can hear a creaky sigh let out from the tree as you approach.\n"
										+ "It gives itself a decent shake and drops 25 gold coins along with a few dead animals\n"
										+ "and a bottle of Snake Oil!" + " + 25 gold!" );
								Var.setGold ( 25 );
							}
							if ( Var.getHpPot ( ) > 0 )
							{
								System.out.println ( "\nDelicious! +40 HP" );
								Var.setHp ( 40 );
							}
							else
							{
								System.out.println ( "You stowe the snake oil for later." );
								Var.setHpPot ( 1 );
							}
						}
						break;

					case 4:
						System.out.println ( Enemy.aElemental ( ).getText ( ) );
						CfSequence.battleSequence ( Enemy.aElemental ( ) );

				}
			}
			if ( Var.getHp ( ) > 0 )
			{
				System.out.println ( "Keep Searching the Marsh?" );
				do // check character input
				{
					mountain = input.next ( ).toLowerCase ( ).charAt ( 0 );
					if ( mountain != 'y' && mountain != 'n' )
					{
						System.out.println ( "You must enter y or n" );
					}
				} while ( mountain != 'y' && mountain != 'n' );
			}
			else
			{
				mountain = 'n';
			}
		}
	}

	public static void frozenDesert( )
	{
		Scanner input = new Scanner ( System.in );
		Scanner KeyIn = new Scanner ( System.in );
		System.out.println (
				"The bleakness of the Frozen desert is staggering, you can only guess as to what cursed this wasteland." );
		System.out.println ( "Would you like to look around? y/n" );
		char mountain = 'a';
		do // check character input
		{
			mountain = input.next ( ).toLowerCase ( ).charAt ( 0 );
			if ( mountain != 'y' && mountain != 'n' )
			{
				System.out.println ( "You must enter y or n" );
			}
		} while ( mountain != 'y' && mountain != 'n' );

		while ( mountain == 'y' )
		{
			rand = (int) ( Math.random ( ) * range + minEnc ); // encounter chance
			if ( encNumber == rand )
			{
				int enemy = (int) ( Math.random ( ) * 4 + 1 ); // random enemy
				switch ( enemy )
				{
					case 1:
						System.out.println ( Enemy.cScorpion ( ).getText ( ) );
						CfSequence.battleSequence ( Enemy.cScorpion ( ) );

						break;

					case 2:
						System.out.println ( "The sound of a half-dozen horses comes from up ahead" ); // traveling merchant
						System.out.println ( "Do you want to check it out? y/n" );
						char help;
						do // check character input
						{
							help = input.next ( ).toLowerCase ( ).charAt ( 0 );
							if ( help != 'y' && help != 'n' )
							{
								System.out.println ( "You must enter y or n" );
							}
						} while ( help != 'y' && help != 'n' );

						if ( help == 'n' ) // Don't look
						{
							System.out.println (
									"Frightened, you run off behind a dune and hide until the sound\nof the horses fade into the distance..." );
						}
						else
						{
							System.out.println (
									"You meet a merchant and his small caravan on the trail\nand he gladly shows you his wares" );
							int valleyMerch = 0;
							while ( valleyMerch != 4 )
							{
								System.out.println ( "\"What would you like?\"\n" );
								int gGameCost = 5;
								System.out.println ( "0 - Play Guessing Game. (5 gold)" );
								int hpPotCost = 35;
								System.out.println ( "1 - Snake Oil. Heal 40 HP. (35 gold)" );
								int dynamiteCost = 40;
								System.out.println ( "2 - Dynamite. Deals 30 DMG to the enemy and 5 damage to you. (40 gold)" );
								int cKukriCost = 75;
								System.out.println ( "3 - Coral Kukri. 6 - 11 DMG, 33% Chance to Poison (75 gold)" );
								System.out.println ( "4 - Take your Leave" );
								System.out.println ( "\nYou Have " + Var.getGold ( ) + "gold" );
								do
								{
									valleyMerch = input.nextInt ( ); // input decision
									if ( valleyMerch < 0 || valleyMerch > 4 )
									{
										System.out.println ( "You must enter a valid number (0 - 4)" );
									}
								} while ( valleyMerch < 0 || valleyMerch > 4 );
								switch ( valleyMerch )
								{
									case 0:

										CfSequence.guessingGame ( gGameCost );

										break;

									case 1:
										if ( Var.getHpPot ( ) < 1 )
										{
											if ( Var.getGold ( ) >= hpPotCost )
											{
												System.out.println ( "You get a bottle of Snake Oil. Use it wisely" );
												Var.setGold ( -hpPotCost );
												Var.setHpPot ( 1 );
											}
											else
											{
												System.out.println ( "I'm your friend but I still require payment. " );
												System.out.println ( "You only have " + Var.getGold ( ) + " gold..." );
											}
										}
										else
										{
											System.out.println ( "You can only hold one bottle at a time." );
										}
										break;

									case 2:
										if ( Var.getDynamite ( ) < 1 )
										{
											if ( Var.getGold ( ) >= dynamiteCost )
											{
												System.out.println ( "You get a Stick of Dynamite. Use it recklessly" );
												Var.setGold ( -dynamiteCost );
												Var.setDynamite ( 1 );
											}
											else
											{
												System.out.println ( "I'm your friend but I still require payment. " );
												System.out.println ( "You only have " + Var.getGold ( ) + " gold..." );
											}
										}
										else
										{
											System.out.println ( "You can only hold one explosive at a time." );
										}
										break;

									case 3:
										if ( Var.getcKukri ( ) > 0 )
										{
											if ( Var.getGold ( ) >= cKukriCost )
											{
												System.out.println (
														"The Coral Kukri is now yours! DMG: 6-11. careful not to poison yourself..." );
												Var.setMinDMG ( 6 );
												Var.setMaxDMG ( 11 );
												Var.setGold ( -cKukriCost );
												Var.setcKukri ( -1 );
												Var.setvRapier ( 1 );
												Var.setlAxe ( 1 );
												Var.setsKatana ( 0 );
												if ( Var.getFighter ( ) == 1 )
												{
													Var.setMaxDMG ( 2 );
												}
												if ( Var.getLoser ( ) == 1 )
												{
													Var.setMaxDMG ( -1 );
												}
												if ( Var.getmDagger ( ) == 0 || Var.getpDagger ( ) == 0 )
												{
													Var.setMinDMG ( 1 );
												}
											}
											else
											{
												System.out.println (
														"You are too poor for this item. you have " + Var.getGold ( ) + " gold" );
											}
										}
										else
										{
											System.out.println ( "Sorry, This item is out of stock." );
										}

										break;

									case 4:

								}// end switch
							} // end merchant
						}
						break;

					case 3:
						System.out.println ( Enemy.wWisp ( ).getText ( ) );
						CfSequence.battleSequence ( Enemy.wWisp ( ) );

						break;

					case 4:
						System.out.println ( Enemy.taranTroll ( ).getText ( ) );
						CfSequence.battleSequence ( Enemy.taranTroll ( ) );

				}
			}
			if ( Var.getHp ( ) > 0 )
			{
				System.out.println ( "Keep Searching the Desert?" );
				do // check character input
				{
					mountain = input.next ( ).toLowerCase ( ).charAt ( 0 );
					if ( mountain != 'y' && mountain != 'n' )
					{
						System.out.println ( "You must enter y or n" );
					}
				} while ( mountain != 'y' && mountain != 'n' );
			}
			else
			{
				mountain = 'n';
			}
		}
	}

	public static void sandstoneCliffs( )
	{
		System.out.println ( "The SandStone Cliffs are flat and rocky with patches of vegetation.\n"
				+ "The salty air is invigorationg and the sound of the waves calm your nerves... " );
		Scanner input = new Scanner ( System.in );
		Scanner KeyIn = new Scanner ( System.in );
		System.out.println ( "" );
		System.out.println ( "Would you like to look around? y/n" );
		char mountain = 'a';
		do // check character input
		{
			mountain = input.next ( ).toLowerCase ( ).charAt ( 0 );
			if ( mountain != 'y' && mountain != 'n' )
			{
				System.out.println ( "You must enter y or n" );
			}
		} while ( mountain != 'y' && mountain != 'n' );

		while ( mountain == 'y' )
		{
			rand = (int) ( Math.random ( ) * range + minEnc ); // encounter chance
			if ( encNumber == rand )
			{
				int enemy = (int) ( Math.random ( ) * 4 + 1 ); // random enemy
				switch ( enemy )
				{
					case 1:
						System.out.println ( Enemy.ants ( ).getText ( ) );
						CfSequence.battleSequence ( Enemy.ants ( ) );
						break;

					case 2: // Horde
						if ( Var.getGryphonLife ( ) > 0 )
						{
							System.out.println ( "Your gaze moves to the cliffs edge and curiosity fills your mind" ); // boss
							System.out.println ( "Move closer? y/n" );
							char help;
							do // check character input
							{
								help = input.next ( ).toLowerCase ( ).charAt ( 0 );
								if ( help != 'y' && help != 'n' )
								{
									System.out.println ( "You must enter y or n" );
								}
							} while ( help != 'y' && help != 'n' );

							if ( help == 'n' ) // Don't look
							{
								System.out.println ( "Heights are not my thing bruh..." );
							}
							else
							{
								System.out.println ( Enemy.gryphon ( ).getText ( ) );
								Var.setHp ( Var.getHpMax ( ) / 2 );

								System.out.println ( "Press Enter" );
								KeyIn.nextLine ( );

								// Boss battle
								CfSequence.battleSequence ( Enemy.gryphon ( ) );

								if ( Var.getHp ( ) > 0 )
								{
									Var.setHpMax ( 10 ); // hp Max + 10
									Var.setHp ( Var.getHpMax ( ) ); // full hp

									Var.setGryphonLife ( -1 ); // Gryphon dies

								}
							}
						}
						else
						{
							System.out.println ( "You wander back to the spot where you fought the Gryphon" );
							System.out.println ( "Feeling Nostalgic?\n ...Oh a coin! + 1 gold" );
							Var.setGold ( 1 );
						}
						break;

					case 3:
						System.out.println ( "An Enormous tree stands alone in a small clearing.\n"
								+ "As you pass, you notice the branches swaying though there is no breeze." ); // tree
						System.out.println ( "Investigate the tree? y/n" );
						char help;
						do // check character input
						{
							help = input.next ( ).toLowerCase ( ).charAt ( 0 );
							if ( help != 'y' && help != 'n' )
							{
								System.out.println ( "You must enter y or n" );
							}
						} while ( help != 'y' && help != 'n' );

						if ( help == 'n' ) // Don't look
						{
							System.out.println ( "Thats one freaky tree bruh. Exit stage right." );
						}
						else
						{
							int ruse = (int) ( Math.random ( ) * 10 );
							if ( ruse >= 5 )
							{
								System.out.println ( Enemy.mCrows ( ).getText ( ) );
								CfSequence.battleSequence ( Enemy.mCrows ( ) );
							}
							else
							{
								System.out.println ( "You can hear a creaky sigh let out from the tree as you approach.\n"
										+ "It gives itself a decent shake and drops 25 gold coins along with a few dead animals\n"
										+ "and a bottle of Snake Oil!" + " + 25 gold!" );
								Var.setGold ( 25 );
							}
							if ( Var.getHpPot ( ) > 0 )
							{
								System.out.println ( "\nDelicious! +40 HP" );
								Var.setHp ( 40 );

							}
							else
							{
								System.out.println ( "You stowe the snake oil for later." );
								Var.setHpPot ( 1 );
							}
						}
						break;

					case 4:
						System.out.println ( Enemy.miniBears ( ).getText ( ) );
						CfSequence.battleSequence ( Enemy.miniBears ( ) );

				}
			}
			if ( Var.getHp ( ) > 0 )
			{
				System.out.println ( "Keep Searching the Cliffs?" );
				do // check character input
				{
					mountain = input.next ( ).toLowerCase ( ).charAt ( 0 );
					if ( mountain != 'y' && mountain != 'n' )
					{
						System.out.println ( "You must enter y or n" );
					}
				} while ( mountain != 'y' && mountain != 'n' );
			}
			else
			{
				mountain = 'n';
			}
		}
	}

	public static void tombEntrance( )
	{
		Scanner input = new Scanner ( System.in );
		int valleyMerch = 0;
		while ( valleyMerch != 5 )
		{
			System.out.println ( "\"What would you like?\"\n" );
			int gGameCost = 5;
			System.out.println ( "0 - Play Guessing Game. (5 gold)" );
			int hpPotCost = 35;
			System.out.println ( "1 - Snake Oil. Heal 40 HP. (35 gold)" );
			int dynamiteCost = 40;
			System.out.println ( "2 - Dynamite. Deals 30 DMG to the enemy and 5 damage to you. (40 gold)" );
			int lAxeCost = 240;
			System.out.println ( "3 - Lightning Axe. 8 - 13 DMG, 14% Chance to Stun (240 gold)" );
			System.out.println ( "4 - Examine Stone Door." );
			System.out.println ( "5 - Go Back Home" );
			System.out.println ( "\nYou Have " + Var.getGold ( ) + "gold" );
			do
			{
				valleyMerch = input.nextInt ( ); // input decision
				if ( valleyMerch < 0 || valleyMerch > 5 )
				{
					System.out.println ( "You must enter a valid number (0 - 5)" );
				}
			} while ( valleyMerch < 0 || valleyMerch > 5 );
			switch ( valleyMerch )
			{

				case 0:

					CfSequence.guessingGame ( gGameCost );

					break;

				case 1:
					if ( Var.getHpPot ( ) < 1 )
					{
						if ( Var.getGold ( ) >= hpPotCost )
						{
							System.out.println ( "You get a bottle of Snake Oil. Use it wisely" );
							Var.setGold ( -hpPotCost );
							Var.setHpPot ( 1 );
						}
						else
						{
							System.out.println ( "I'm your friend but I still require payment. " );
							System.out.println ( "You only have " + Var.getGold ( ) + " gold..." );
						}
					}
					else
					{
						System.out.println ( "You can only hold one bottle at a time." );
					}
					break;

				case 2:
					if ( Var.getDynamite ( ) < 1 )
					{
						if ( Var.getGold ( ) >= dynamiteCost )
						{
							System.out.println ( "You get a Stick of Dynamite. Use it recklessly" );
							Var.setGold ( -dynamiteCost );
							Var.setDynamite ( 1 );
						}
						else
						{
							System.out.println ( "You are too poor. " );
							System.out.println ( "You only have " + Var.getGold ( ) + " gold..." );
						}
					}
					else
					{
						System.out.println ( "You can only hold one explosive at a time." );
					}
					break;

				case 3:
					if ( Var.getlAxe ( ) > 0 )
					{
						if ( Var.getGold ( ) >= lAxeCost )
						{
							System.out.println (
									"A surge of static energy flows through your whole body and settles\n 8 - 13 DMG" );
							Var.setMinDMG ( 8 );
							Var.setMaxDMG ( 13 );
							Var.setGold ( -lAxeCost );
							Var.setlAxe ( -1 );
							Var.setcKukri ( 1 );
							Var.setvRapier ( 1 );
							Var.setsKatana ( 1 );
							if ( Var.getFighter ( ) == 1 )
							{
								Var.setMaxDMG ( 2 );
							}
							if ( Var.getLoser ( ) == 1 )
							{
								Var.setMaxDMG ( -1 );
							}
							if ( Var.getmDagger ( ) == 0 || Var.getpDagger ( ) == 0 )
							{
								Var.setMinDMG ( 1 );
							}

						}
						else
						{
							System.out.println ( "You are too poor for this item. you have " + Var.getGold ( ) + " gold" );
						}
					}
					else
					{
						System.out.println ( "Sorry, This item is out of stock." );
					}

					break;

				case 4:
					System.out.println ( "An Enormous set of double doors is carved into a massive wall of stone\n"
							+ "It is weathered and rough with no markings besides two small holes.\n"
							+ "Above the holes you faintly make out an etching of a Warrior riding a Gryphon" );
					if ( Var.getHordeLife ( ) == 0 && Var.getGryphonLife ( ) == 0 )
					{
						System.out.println ( "Insert the Two Keys?\ny/n" );
						int mountain;
						do // check character input
						{
							mountain = input.next ( ).toLowerCase ( ).charAt ( 0 );
							if ( mountain != 'y' && mountain != 'n' )
							{
								System.out.println ( "You must enter y or n" );
							}
						} while ( mountain != 'y' && mountain != 'n' );
						if ( mountain == 'y' )
						{
							valleyMerch = 5;
							ancientTomb ( );
						}
					}
					break;

				case 5:
			}// end switch
		} // end merchant
	}

	public static void ancientTomb( )
	{
		System.out.println ( "The Tomb is dark with patches of light seeping though the weathered cracks in the walls.\n"
				+ "You can hear the emptyness of the space around you and the faint shuffling of insects." );
		Scanner input = new Scanner ( System.in );
		Scanner KeyIn = new Scanner ( System.in );
		System.out.println ( "" );
		System.out.println ( "Would you like to look around? y/n" );
		char mountain = 'a';
		do // check character input
		{
			mountain = input.next ( ).toLowerCase ( ).charAt ( 0 );
			if ( mountain != 'y' && mountain != 'n' )
			{
				System.out.println ( "You must enter y or n" );
			}
		} while ( mountain != 'y' && mountain != 'n' );

		while ( mountain == 'y' )
		{
			rand = (int) ( Math.random ( ) * range + minEnc ); // encounter chance
			if ( encNumber == rand )
			{
				int enemy = (int) ( Math.random ( ) * 4 + 1 ); // random enemy
				switch ( enemy )
				{
					case 1:
						System.out.println ( Enemy.scarabs ( ).getText ( ) );
						CfSequence.battleSequence ( Enemy.scarabs ( ) );

						break;

					case 2: // Horde
						if ( Var.getLitchKing ( ) > 0 )
						{
							System.out.println (
									"There is something large shining in the darkness which resmbles a Monstrous Casket\n"
											+ "under the rays of light peering though the cracks in the gloomy walls of the Anchient Tomb." ); // boss
							System.out.println ( "Move closer? y/n" );
							char help;
							do // check character input
							{
								help = input.next ( ).toLowerCase ( ).charAt ( 0 );
								if ( help != 'y' && help != 'n' )
								{
									System.out.println ( "You must enter y or n" );
								}
							} while ( help != 'y' && help != 'n' );

							if ( help == 'n' ) // Don't look
							{
								System.out.println ( "That's some Nightmare fuel, No Thanks..." );
							}
							else
							{
								System.out.println ( Enemy.litchKing ( ).getText ( ) );
								Var.setHp ( Var.getHpMax ( ) / 2 );

								System.out.println ( "Press Enter" );
								KeyIn.nextLine ( );

								// Boss battle
								CfSequence.battleSequence ( Enemy.litchKing ( ) );

								if ( Var.getHp ( ) > 0 )
								{
									Var.setHpMax ( 10 ); // hp Max + 10
									Var.setHp ( Var.getHpMax ( ) ); // full hp
									Var.setLitchKing ( -1 ); // Litch King dies
									Var.setvBangle ( -1 ); // vBangle
								}
							}
						}
						else
						{
							System.out.println ( "You wander back to the spot where you fought the Litch King" );
							System.out.println ( "Feeling Nostalgic?\n ...Oh a coin! + 1 gold" );
							Var.setGold ( 1 );
						}
						break;

					case 3:
						System.out.println (
								"You see a very large hole at the base of a nearby wall that opens into an even darker space." ); // tree
						System.out.println ( "Investigate the Hole? y/n" );
						char help;
						do // check character input
						{
							help = input.next ( ).toLowerCase ( ).charAt ( 0 );
							if ( help != 'y' && help != 'n' )
							{
								System.out.println ( "You must enter y or n" );
							}
						} while ( help != 'y' && help != 'n' );

						if ( help == 'n' ) // Don't look
						{
							System.out.println ( "I don't noodle for snakes..." );
						}
						else
						{
							int ruse = (int) ( Math.random ( ) * 10 );
							if ( ruse >= 5 )
							{
								System.out.println ( Enemy.pViper ( ).getText ( ) );
								CfSequence.battleSequence ( Enemy.pViper ( ) );
							}
							else
							{
								System.out.println (
										"You reach into the hole and find 25 gold\nanlong with a bottle of Snake Oil!" );
								Var.setGold ( 25 );
							}
							if ( Var.getHpPot ( ) > 0 )
							{
								System.out.println ( "\nDelicious! +40 HP" );
								Var.setHp ( 40 );

							}
							else
							{
								System.out.println ( "You stowe the snake oil for later." );
								Var.setHpPot ( 1 );
							}
						}
						break;

					case 4:
						System.out.println ( Enemy.spectre ( ).getText ( ) );
						CfSequence.battleSequence ( Enemy.spectre ( ) );

				}
			}
			if ( Var.getHp ( ) > 0 )
			{
				System.out.println ( "Keep Searching the Tomb?" );
				do // check character input
				{
					mountain = input.next ( ).toLowerCase ( ).charAt ( 0 );
					if ( mountain != 'y' && mountain != 'n' )
					{
						System.out.println ( "You must enter y or n" );
					}
				} while ( mountain != 'y' && mountain != 'n' );
			}
			else
			{
				mountain = 'n';
			}
			System.out.println ( "You step out of the Tomb and back into the warmth of the Sun.\n"
					+ "You're glad to see that merchant still in his place as\n"
					+ "you had expected him to vanish like so many other things had\n" + "on this insane adventure." );
		}
	}

	public static void aridPlains( )
	{
		System.out.println ( "Your eyes are dry and the sulpher stings your nose." );
		Scanner input = new Scanner ( System.in );
		Scanner KeyIn = new Scanner ( System.in );
		System.out.println ( "" );
		System.out.println ( "Would you like to look around? y/n" );
		char mountain = 'a';
		do // check character input
		{
			mountain = input.next ( ).toLowerCase ( ).charAt ( 0 );
			if ( mountain != 'y' && mountain != 'n' )
			{
				System.out.println ( "You must enter y or n" );
			}
		} while ( mountain != 'y' && mountain != 'n' );

		while ( mountain == 'y' )
		{
			rand = (int) ( Math.random ( ) * range + minEnc ); // encounter chance
			if ( encNumber == rand )
			{
				int enemy = (int) ( Math.random ( ) * 4 + 1 ); // random enemy
				switch ( enemy )
				{
					case 1:
						System.out.println ( Enemy.chimera ( ).getText ( ) );
						CfSequence.battleSequence ( Enemy.chimera ( ) );
						break;

					case 2:
						System.out.println ( Enemy.sandMan ( ).getText ( ) );
						CfSequence.battleSequence ( Enemy.sandMan ( ) );
						break;

					case 3:
						System.out.println ( "An Enormous leafless tree stands alone in a small clearing.\n"
								+ "As you pass, you notice the branches swaying though there is no breeze." ); // tree
						System.out.println ( "Investigate the tree? y/n" );
						char help;
						do // check character input
						{
							help = input.next ( ).toLowerCase ( ).charAt ( 0 );
							if ( help != 'y' && help != 'n' )
							{
								System.out.println ( "You must enter y or n" );
							}
						} while ( help != 'y' && help != 'n' );

						if ( help == 'n' ) // Don't look
						{
							System.out.println ( "Thats one freaky tree bruh. Exit stage right." );
						}
						else
						{
							int ruse = (int) ( Math.random ( ) * 10 );
							if ( ruse >= 5 )
							{
								System.out.println ( Enemy.mCrows ( ).getText ( ) );
								CfSequence.battleSequence ( Enemy.mCrows ( ) );
							}
							else
							{
								System.out.println ( "You can hear a creaky sigh let out from the tree as you approach.\n"
										+ "It gives itself a decent shake and drops 25 gold coins along with a few dead animals\n"
										+ "and a bottle of Snake Oil!" + " + 25 gold!" );
								Var.setGold ( 25 );
							}
							if ( Var.getHpPot ( ) > 0 )
							{
								System.out.println ( "\nDelicious! +40 HP" );
								Var.setHp ( 40 );

							}
							else
							{
								System.out.println ( "You stowe the snake oil for later." );
								Var.setHpPot ( 1 );
							}
						}
						break;

					case 4:
						System.out.println ( Enemy.stoneGolem ( ).getText ( ) );
						CfSequence.battleSequence ( Enemy.stoneGolem ( ) );

				}
			}
			if ( Var.getHp ( ) > 0 )
			{
				System.out.println ( "Keep Searching the Plains?" );
				do // check character input
				{
					mountain = input.next ( ).toLowerCase ( ).charAt ( 0 );
					if ( mountain != 'y' && mountain != 'n' )
					{
						System.out.println ( "You must enter y or n" );
					}
				} while ( mountain != 'y' && mountain != 'n' );
			}
			else
			{
				mountain = 'n';
			}
		}
	}

	public static void volcano( )
	{
		Scanner input = new Scanner ( System.in );
		Scanner KeyIn = new Scanner ( System.in );
		System.out.println ( "The Top of the volcano is sweltering. You take a deep breath and calm your nerves.\n"
				+ "Your eternal fate rests on the coming hours" );
		System.out.println ( "Would you like to look around first? y/n" );
		char mountain = 'a';
		do // check character input
		{
			mountain = input.next ( ).toLowerCase ( ).charAt ( 0 );
			if ( mountain != 'y' && mountain != 'n' )
			{
				System.out.println ( "You must enter y or n" );
			}
		} while ( mountain != 'y' && mountain != 'n' );

		while ( mountain == 'y' )
		{
			rand = (int) ( Math.random ( ) * range + minEnc ); // encounter chance
			if ( encNumber == rand )
			{
				int enemy = (int) ( Math.random ( ) * 4 + 1 ); // random enemy
				switch ( enemy )
				{
					case 1:

						System.out.println ( Enemy.chaosElemental ( ).getText ( ) );
						CfSequence.battleSequence ( Enemy.chaosElemental ( ) );

						break;

					case 2: // Chaos Demon
						if ( Var.getChaosLife ( ) > 0 && Var.getLitchKing ( ) < 1 )
						{
							System.out.println ( "Your spectacles vibrate and you put them on.." );
							System.out.println (
									"You see an Orange blur and a distanly familiar voice begins to tug on your mind" ); // rescue
							System.out.println ( "Give in to the voice? y/n" );
							char help;
							do // check character input
							{
								help = input.next ( ).toLowerCase ( ).charAt ( 0 );
								if ( help != 'y' && help != 'n' )
								{
									System.out.println ( "You must enter y or n" );
								}
							} while ( help != 'y' && help != 'n' );

							if ( help == 'n' ) // Don't look
							{
								System.out.println ( "That's way too Creepy. You shut the voice out of your mind." );
							}
							else
							{
								System.out.println ( Enemy.chaos ( ).getText ( ) );

								System.out.println ( "Press Enter" );
								KeyIn.nextLine ( );

								// Boss battle

								CfSequence.battleSequence ( Enemy.chaos ( ) );

								if ( Var.getHp ( ) > 0 )
								{
									Var.setHpMax ( 25 ); // hp Max + 25
									Var.setHp ( Var.getHpMax ( ) ); // full hp
									Var.setChaosLife ( -1 ); // chaos dies
								}
							}
						}
						else
						{
							System.out.println ( "You wander back to the spot where you fought Chaos" );
							System.out.println ( "Feeling Nostalgic?\n ...Oh a coin! + 1 gold" );
							Var.setGold ( 1 );
						}
						break;

					case 3:
						System.out.println ( Enemy.omegaTroll ( ).getText ( ) );
						CfSequence.battleSequence ( Enemy.omegaTroll ( ) );
						break;

					case 4:
						System.out.println ( "You see a a large cave..." ); // tree
						System.out.println ( "Investigate the cave? y/n" );
						char help;
						do // check character input
						{
							help = input.next ( ).toLowerCase ( ).charAt ( 0 );
							if ( help != 'y' && help != 'n' )
							{
								System.out.println ( "You must enter y or n" );
							}
						} while ( help != 'y' && help != 'n' );

						if ( help == 'n' ) // Don't look
						{
							System.out.println ( "I'm not going anywhere near that forsaken dungeon..." );
						}
						else
						{
							int ruse = (int) ( Math.random ( ) * 10 );
							if ( ruse >= 5 )
							{
								System.out.println ( Enemy.fBat ( ).getText ( ) );
								CfSequence.battleSequence ( Enemy.fBat ( ) );
							}
							else
							{
								System.out.println (
										"Thankfully the cave's occupants are away and amongst their droppings you find\n"
												+ "25 gold coins along with a few dead animals\nand a bottle of Snake Oil!" );
								Var.setGold ( 25 );
							}
							if ( Var.getHpPot ( ) > 0 )
							{
								System.out.println ( "\nDelicious! +40 HP" );
								Var.setHp ( 40 );
							}
							else
							{
								System.out.println ( "You stowe the snake oil for later." );
								Var.setHpPot ( 1 );
							}
						}
						break;

				}
			}
			if ( Var.getHp ( ) > 0 )
			{
				System.out.println ( "Keep Searching the Volcano?" );
				do // check character input
				{
					mountain = input.next ( ).toLowerCase ( ).charAt ( 0 );
					if ( mountain != 'y' && mountain != 'n' )
					{
						System.out.println ( "You must enter y or n" );
					}
				} while ( mountain != 'y' && mountain != 'n' );
			}
			else
			{
				mountain = 'n';
			}
		}
		if ( Var.getHp ( ) > 0 && Var.getChaosLife ( ) < 1 )
		{
			System.out.println ( "A Rift Opens..." ); // return home
			System.out.println ( "Enter to continue" );
			KeyIn.nextLine ( );
		}
	}

}
