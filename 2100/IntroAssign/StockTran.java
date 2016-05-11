// This is a solution for CSE 2100 assignment 1.
// This solution is only a basic one and outstanding works should 
// be better than this.

import java.util.*;

public class StockTran
{
	public static void main(String[] args)
	{
		CircularLinkedList<int[]> Queue = new 					CircularLinkedList<int[]>();
		Scanner sc = new Scanner(System.in);
		int gain=0;
		int totalamount=0;
		int amount=0;
		int price=0;

		while (true)
		{
			String string;
			System.out.println("Please input a transaction");
			string=sc.nextLine();
			String[] stringarr = string.split(" ");
			char[] arr;

			if (stringarr[0].equals("c"))
			{
				System.out.println(gain);
			}

			if (stringarr[0].equals("b"))
			{
				if (Queue.length>30)
				{
					System.out.println("The queue is full!");
					continue;
				}
				amount=Integer.parseInt(stringarr[1]);
				price=Integer.parseInt(stringarr[2]);

				int[] newTrans = new int[2];
				newTrans[0]=amount;
				newTrans[1]=price;
				totalamount=totalamount+amount;
				Queue.add(newTrans);
			}

			if (stringarr[0].equals("s"))
			{
				if (Queue.length<1)
				{
					System.out.println("Nothing to sell!");
					continue;
				}

				amount=Integer.parseInt(stringarr[1]);
				price=Integer.parseInt(stringarr[2]);

				if (amount>totalamount)
				{
					System.out.println("Not enough items");
					continue;
				}

				while(amount-(Queue.firstData())[0]>0)
				{
					amount=amount-(Queue.firstData()[0]);
					Queue.removeFirst();
					gain+=Queue.firstData()[0]*(price-(Queue.firstData())[1]);
				}
				Queue.firstData()[0]=Queue.firstData()[0]-amount;
				gain+=(amount)*(price-(Queue.firstData())[1]);
				totalamount-=amount;

			}
			if (stringarr[0].equals("q"))
			{
				break;
			}
		}
	}
}

   