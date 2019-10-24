//Matt Lynn
package co.grandcircus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ShoppingList
{

	public static void main(String[] args)
	{
		Map<String, Double> items = new HashMap<>();
		Map<Integer, String> itemNumber = new HashMap<>();
		ArrayList<String> product = new ArrayList<>();
		ArrayList<Double> price = new ArrayList<>();
		ArrayList<Integer> quantity = new ArrayList<>();
		
		int quantityInput;
		String cont ="yes";
		String userChoice ="";
		items.put("sugar",8.95);
		items.put("spice",11.95);
		items.put("everything nice",3.50);
		items.put("chemical x",24.24);
		items.put("blossom",24.95);
		items.put("bubbles",35.99);
		items.put("buttercup",14.92);
		items.put("steak",999.99);
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to Store! tm");
		System.out.println();
		int counter = 0;
		do {
			//Store printout and number connection
		for(String list: items.keySet())
		{
			itemNumber.put(++counter,list);
			System.out.printf("%s. %-25s $%-7.2f", counter, list, items.get(list) );
			System.out.println();
		}
			System.out.println(counter+1 + ". " + "Exit");
			counter = 0;
			System.out.println("Please enter the name or number of the product you would like to purchase");
			if (scan.hasNextInt())
			{
				quantityInput = scan.nextInt();
				//number input check
				if(quantityInput>0 && quantityInput<9)
				{
					userChoice = itemNumber.get(quantityInput);
				}
				//exit option
				else if(quantityInput==9)
					{
					System.out.println("Goodbye!");
					System.exit(0);
					}

			}
			else
			{
				userChoice = scan.nextLine().toLowerCase();
			}
			
			if (userChoice.equalsIgnoreCase("exit"))
			{
					System.out.println("Goodbye!");
					System.exit(0);
			}
			if (items.containsKey(userChoice)) 
			{
				System.out.println("You have added " + userChoice);
				
				//add new products
				if (!product.contains(userChoice)) 
				{
					product.add(userChoice);
					price.add(items.get(userChoice));
					quantity.add(0);
				}
				//adds current value to old value to set quantity
				quantityInput = Validator.getInt(scan, "Please enter how many you would like to purchase: ");
				quantity.set(product.indexOf(userChoice),
				quantity.get(product.indexOf(userChoice)) + quantityInput);
			}
			else
			{
				System.out.println("Sorry, that item doesn't exist, please try again!");
			}
			System.out.print("Do you want to order anything else? (y/n): ");
			cont=scan.nextLine();
		} while (cont.equalsIgnoreCase("Y") || cont.equalsIgnoreCase("Yes")) ;
		//output
		System.out.println("Receipt");
		System.out.println("Product                 Cost              Quantity");
		System.out.println("##################################################\n");
		
		for(int i=0;i<product.size();i++)
		{
			System.out.printf("%-22s $%-20.2f %-10d",product.get(i),price.get(i),quantity.get(i));
			System.out.println();
		}
		double avg = findTheAverage(price,quantity);
		System.out.printf("Your average item costed: $%-6.2f\n", avg);
		System.out.printf("Your cheapest item was %s at $%-6.2f\n",
		product.get(findTheLowest(price)), price.get(findTheLowest(price)));
		System.out.printf("Your most expensive item was %s at $%-6.2f\n",
		product.get(findTheHighest(price)), price.get(findTheHighest(price)));
		scan.close();

	}
		public static double findTheAverage(ArrayList<Double> price, ArrayList<Integer> quantity)
		{
			int dollas=0, stuff=0;
			for(int i = 0; i<price.size(); i++)
			{
				dollas += price.get(i)*quantity.get(i);
				stuff  +=quantity.get(i);
			}
			
			return dollas/stuff;
		}
		
		public static int findTheLowest(ArrayList<Double> price)
		{
			int lowestIndex=0;
			double lowest = 0;
			for(int i =0;i<price.size();i++)
			{
				if(lowest<price.get(i) || lowest == 0)
					{
					lowest=price.get(i);
					lowestIndex=i;
					}
			}
			
			return lowestIndex;
		}
		public static int findTheHighest(ArrayList<Double> price)
		{
			int highestIndex=0;
			double highest = 0;
			for(int i =0;i<price.size();i++)
			{
				if(highest>price.get(i))
					{
					highest=price.get(i);
					highestIndex=i;
					}
			}
			
			return highestIndex;
		}
}
