/*Stock Queue Implementation 
 *CSE2100 Project 1, Fall 2014
 *Michael Neas
 *9/14/2014
 *Instructor: Chun-Hsi (Vincent) Huang
 */

import java.util.Scanner;

public class StockTran<E> 
{
	private static Scanner kbd = new Scanner(System.in);
	private static StockLinkedQueue<int[]> workingQueue = new StockLinkedQueue<>(); //initiate queue
	private static boolean _run = true; //keeps the calculator running
	private static int _totalShareCount = 0; // quick check counter

	private static int _buyPrice; //buy price, the third input in scanner
	private static int _buyingShares; //share amount when buying, 2nd input in scanner
	private static int _currentSaleShare; //amount of shares to sell
	private static int _currentSalePrice; //price of the share you want to sell

	private static int _capitalGainLoss; //equal to total dequeued price but used for c input
	private static int _dequeuedPrice = 0; //price dequeued from one node
	private static int _totalDequeuedPrice = 0; //total price dequeued 
	private static int _totalDequeuedCount = 0; //total amount of shares taken away
	private static int _newShareAmount; //difference when the bought shares are > sold shares


	public static void main(String[] args)
	{
		System.out.println("Welcome to your capital gain/loss calculator. b/s shares price");
		while(_run)
		{
			capitalCalc(kbd.next()); //run specific cases based off of letter entered
		}
	}


	public static void capitalCalc(String action)
	{
		if(action.equalsIgnoreCase("b")) //buy case
		{
			if(kbd.hasNextInt()) //handle so that the next value is integer
			{
				_buyingShares = kbd.nextInt(); //takes following two ints and sets them to variables
				_buyPrice = kbd.nextInt();
				_totalShareCount = _buyingShares + _totalShareCount;
				int[] amountPriceArray = new int[]{_buyingShares, _buyPrice};
				workingQueue.enqueue(amountPriceArray);
				System.out.println("You've enqueued " + _buyingShares + " shares at $" + _buyPrice + " per share.");

				System.out.println("The front has " + workingQueue.first()[0] + " shares, "); //tells user whats in front
				System.out.println(" at $" + workingQueue.first()[1] + " per share.");

				System.out.println("You have " + _totalShareCount + " total shares."); //iterates total shares (not needed for program)
			}
			else
			{
				System.out.println("incorrect format"); //fail statement
				kbd.nextLine();
			}
		}

		else if(action.equalsIgnoreCase("s")) //sell case
		{
			_currentSaleShare = kbd.nextInt();
			_currentSalePrice = kbd.nextInt(); //sale assignments

			if(workingQueue.isEmpty() || _totalShareCount < _currentSaleShare) //preliminary check
			{
				System.out.println("You do not have " + _currentSaleShare + " shares to sell");
			}
			else
			{
				_totalShareCount = _totalShareCount - _currentSaleShare; //updates counter
				System.out.println("You have " + _totalShareCount + " shares left after your sale");
				sellingShares(); //give a go to the selling methods
			}
		}

		else if(action.equalsIgnoreCase("size")) //size check
		{
			System.out.println("" + workingQueue.size());
		}

		else if(action.equalsIgnoreCase("c")) //captial gain or loss evaluation
		{
			if(_totalDequeuedCount == _currentSaleShare) //capital gain
			{
				_capitalGainLoss =  _totalDequeuedPrice;
				//System.out.println("" + _totalDequeuedPrice);
				_totalDequeuedCount = 0;
				_currentSaleShare = 0;
			}
			else
				System.out.println("Something unfortunate happened and now your broke cause you have no shares");


			if (_capitalGainLoss > 0)
			{
				System.out.println("Your capital gain is in the green at: $" + _capitalGainLoss);
				_totalDequeuedPrice = 0;
			}
			else if(_capitalGainLoss == 0)
			{
				System.out.println("You broke even at with a capital gain of: $" + _capitalGainLoss);
				_totalDequeuedPrice = 0;
			}
			else
			{
				System.out.println("Your captial loss is in the red at: $" + _capitalGainLoss);
				_totalDequeuedPrice = 0;
			}
		}
		else if(action.equalsIgnoreCase("q")) //quit program
		{
			System.out.println("Bye!");
			_run = false;
		}
		else
		{
			System.out.println("Could not understand what you want to do, try entering b, s, c, or q"); //unrecognized input
		}
	}

	public static void sellingShares() //share selling method
	{
		while(workingQueue.isEmpty() != true && _currentSaleShare > _totalDequeuedCount) //checks to make sure the queue isnt empty and
		{																				//checks if the shares equivalent
			if(_currentSaleShare > workingQueue.first()[0] + _totalDequeuedCount) //when current sale is bigger than all the shares so far
			{
				_dequeuedPrice = (_currentSalePrice - workingQueue.first()[1]) * workingQueue.first()[0];
				_totalDequeuedPrice = _dequeuedPrice + _totalDequeuedPrice;
				_totalDequeuedCount = workingQueue.first()[0] + _totalDequeuedCount;
				workingQueue.dequeue();
				System.out.println("Your " + workingQueue.first()[0] + " shares at $" + workingQueue.first()[1] + " go to the front");
			}
			else if(_currentSaleShare < workingQueue.first()[0] + _totalDequeuedCount) //when current sale is smaller than the total buy shares
			{
				_newShareAmount = (workingQueue.first()[0] + _totalDequeuedCount) - _currentSaleShare;
				_totalDequeuedCount = workingQueue.first()[0] + _totalDequeuedCount - _newShareAmount;
				_dequeuedPrice = (workingQueue.first()[0] - _newShareAmount) * (_currentSalePrice - workingQueue.first()[1]);
				_totalDequeuedPrice = _dequeuedPrice + _totalDequeuedPrice;
				workingQueue.first()[0] = _newShareAmount;
				System.out.println("Your " + workingQueue.first()[0] + " shares at $" + workingQueue.first()[1] + " go to the front");
			}
			else if(_currentSaleShare == workingQueue.first()[0]) //when the amount sold = amount in head of queue
			{
				_dequeuedPrice = workingQueue.first()[0] * (_currentSalePrice - workingQueue.first()[1]);
				_totalDequeuedPrice = _dequeuedPrice + _totalDequeuedPrice;
				_totalDequeuedCount = _totalDequeuedCount + workingQueue.first()[0];
				workingQueue.dequeue();
				System.out.println(workingQueue.size() + " Shares!");
			}
			else if(_currentSaleShare == _totalDequeuedCount + workingQueue.first()[0]) //when amount sold = head and all previous
			{
				_dequeuedPrice = workingQueue.first()[0] * (_currentSalePrice - workingQueue.first()[1]);
				_totalDequeuedPrice = _dequeuedPrice + _totalDequeuedPrice;
				_totalDequeuedCount = _totalDequeuedCount + workingQueue.first()[0];
				workingQueue.dequeue();
				if(workingQueue.size() == 0)
					System.out.println("Your queue is now empty, enter 'c' to check your capital difference!");
			}
			else
			{
				System.out.println("Uhh oh"); //while loop fail
				break;
			}

		}
	}
}
