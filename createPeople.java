import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class createPeople{

	//search for a Member based on their name --returns the Member (object)   -->also, uses memberIndex() method
	public static Member findMember(String name, Member[]GROUP){
		int foundMember = memberIndex(name,GROUP);
		
		if (foundMember==-1){
			System.out.println("Could not find the person named " + name);
			return null;
		} else {
			System.out.println("Found the person named " + name);
			return GROUP[foundMember];
		}	
	}
	
	//search for a Member based on their name -- returns the index
	public static int memberIndex(String name, Member[]GROUP){
	
		//get count from the Member class (static) = number of Members that've been created
		int count = Member.getCount();
		if (count != 1) System.out.println("There are currently " + count+ " people in the group");
		else System.out.println("There is currentlly  a single person in the group");
		int index = -1;
		System.out.println("Searching for person with that name....");
		for (int i  = 0; i < count; i++){
			if (GROUP[i] == null) {
				index  = -1;
				break;
			}
			System.out.println("....found " + GROUP[i].getName());
			if (GROUP[i].getName() .equals(name)){
				index = i;
				break;
			} 
		}
		return index;
	}
	
	
	public static void ageCategory(Member person){
		int age = person.getAge();
		String name = person.getName();
		
		//infant = ages 0 - 1
		if (age > -1 && age < 2){
			System.out.println(name + " is a teeny weeny infant.");
		//toddler = ages 2 - 4
		} else if (age >= 2 && age < 5){
			System.out.println(name + " is a cute toddler.");
		//child = ages 5 - 12
		} else if (age >=5 && age < 13){
			System.out.println(name + " is a child.");
		//teenager = ages 13 - 17
		} else if (age >=13 && age < 18){
			System.out.println(name + " is an adolescent.");
		//adult = ages 18-64
		} else if (age >=18 && age < 65){
			System.out.println(name + " is an adult.");
		//elder = ages 65-90
		} else if (age >=65 && age < 91){
			System.out.println(name + " is a wise elder.");
		//great elder = ages 90+
		} else {
			System.out.println(name + " is a great elder. Very old indeed.");
		}
	
	}

	
	public static void tester(Member[] GROUP, Scanner s, boolean adding){
		String name;
		char gender = 'f';
		int age;
		Member NewPerson;
		
		while (adding){
			System.out.println("Please enter the name of the new person: ");
			name = s.next();
			
			boolean genderIncorrect = true;
			System.out.println("Is " + name + " female or male? Please enter a single character:  f / m");
			while (genderIncorrect){
				gender = s.next().charAt(0);;
				if (gender == 'f' || gender == 'm') genderIncorrect = false;
				else System.out.println("We don't understand your answer. Please enter < f > or < m >");
			}
			
			NewPerson = new Member (name,0,gender);
			if (gender== 'f')	System.out.println("How old is she?");
			if (gender=='m') System.out.println("How old is he?");
			age = (int)s.nextDouble();
			if (age >= 0) NewPerson.setAge(age);
			else {
				System.out.println("We don't understand your answer. Setting age to 0.");
			}
			
			System.out.println("Would you like to add parents? y / n");
			if (s.next().charAt(0) == 'y'){
				System.out.println("Alright! Who is " + name + "'s mother? Please make sure she already exists!!!");
				String mother = s.next();
				if (memberIndex(mother,GROUP) != -1){
					NewPerson.setMother(findMember(mother,GROUP));
				} else {
					System.out.println("Couldn't find her. Sorry!");
				}
				System.out.println("Ok. And their father?");
				String father = s.next();
				if (memberIndex(father,GROUP) != -1){
					NewPerson.setFather(findMember(father,GROUP));
				} else {
					System.out.println("Couldn't find him. Sorry!");
				}
			}
			
			GROUP[Member.counter- 1] = NewPerson;
			System.out.println("--PERSON ADDED--\nWould you like to add a new person? y / n");
			if (s.next().charAt(0) =='n') adding = false;
		}
		
		System.out.println("----------\nPlease enter a name to search for: ");
		name = s.next();
		Member Two = findMember(name,GROUP);
		if (Two != null) {
			Two.printStats();
			ageCategory(Two);
		}	
		
	}
	
	//save to text file savedata.txt
	public static void save(Member[] GROUP){
	/* 
	counter
	name1,age,year born,gender,number of children,number of siblings,mate(name of mate, or "none"),mother,father,children(lists them, or "none"),siblings(lists them, or "none"),alive/dead
	name2,age,year born,gender,number of children,number of siblings,mate(name of mate, or "none"),mother,father,children(lists them, or "none"),siblings(lists them, or "none"),alive/dead
	etc
	*/
		int number = 0;
		int count = Member.counter;
		try{
			File file = new File("savedata.txt");
			PrintWriter writer = new PrintWriter(file,"UTF-8");
			writer.println(count);
			for (int i = 0; i < count; i++){
				//----name----
				writer.print(GROUP[i].getName());
				//---general----
				writer.print("," + GROUP[i].getAge() + "," + GROUP[i].getYearBorn() + "," + GROUP[i].getGender() + "," + GROUP[i].howManyChildren() + "," +  GROUP[i].howManySiblings());
				//----mate-----
				if (GROUP[i].hasMate()) writer.print("," + GROUP[i].getMate().getName());
				else writer.print("," + "none");
				//----mother----
				if (GROUP[i].hasMother()) writer.print("," + GROUP[i].getMother().getName());
				else writer.print("," + "none");
				//----father-----
				if (GROUP[i].hasFather()) writer.print("," + GROUP[i].getFather().getName());
				else writer.print("," + "none");
				//------children------seperated by #'s
				if (GROUP[i].hasChildren()){
					number = GROUP[i].howManyChildren();
					for (int k = 0; k < number; k++){
						writer.print("#" + GROUP[i].getChildren()[k].getName());
					}
				} else writer.print("," + "none");
				//-----siblilngs-----seperated by  #'s
				if (GROUP[i].hasSiblings()){
					number = GROUP[i].howManySiblings();
					for (int k = 0; k < number; k++){
						writer.print("#" + GROUP[i].getSiblings()[k].getName());
					}
				} else writer.print("," + "none");	
				//-----dead?------
				if(GROUP[i].isDead()) writer.print(",dead");
				else writer.print(",alive");
						
				writer.println("");				
			}
			
			writer.close();
		} catch (IOException e){
			System.out.println(e);
		}
	}
	
	//load information back from text file: savedata.txt
	public static int load (Member [] GROUP){
	
		File file = new File("savedata.txt");
	
		if (file.exists() ){
			try{
				FileReader reader = new FileReader("savedata.txt");
				BufferedReader br = new BufferedReader(reader);
				
				//check if text file is empty
				String tester = br.readLine();
				if (tester == null){
					System.out.println("--No save data--\nStarting a new group......");
					return -1;
				}
				
				//first, set the counter (number of members)
				int count = Integer.parseInt(tester);
				//set everything else:
				int newcount = 0;
				String[]items;
				String[]innerlist;
				String name, mateName;
				int born, age;
				char gender;
				Member mate = null;
				Member child = null;
				Member sibling = null;
				Member mother = null;
				Member father = null;
				Member NewMember;
				boolean found = false;
				String line = br.readLine();
				
				//increment until cursor is on a free space in the array....
				while(GROUP[newcount] != null){
					newcount++;
				}
				
				//each line is a Member:    (newcount starts at 0 and goes to the last line, ie count-1)
				while (newcount != count){		
					//items is an array: each item in items is some element of the Member
					items = line.split(",");
					//---NAME---
					name = items[0];
					//---AGE---
					age = Integer.parseInt(items[1]);
					//---YEAR BORN--
					born = Integer.parseInt(items[2]);
					//--GENDER---
					gender = items[3].charAt(0);
					//---MATE's NAME--
					mateName = items[6];
					
					//see if mate is already in the GROUP (ie that member is already read in):
					if (!mateName.equals("none")){
						//newcount is like a cursor 
						for (int j = 0; j < newcount; j++){
							if (GROUP[j].getName() .equals(mateName)){
								mate = GROUP[j];
								found = true;
							}
						}
						//mate is not already listed in the GROUP, so new (temporary) Member is created***
						if  (!found){
							int a = 0;
							//go through the array until an empty space is found
							while (true){
								a++;
								if (GROUP[newcount + a] == null){
									break;
								}
							}
							mate = new Member(mateName);			
							GROUP[newcount+a] = mate;
							Member.decrementCount();
						}
					}
							
					//finally, check to see if a temporary version of this member has already been created, and replace it; otherwise create a newmember at the cursor(newcount)
					NewMember = new Member(name,born,gender,age,mate);	
					found = false;
					//not sure if this for loop is needed....
					for (int j = 0; j < newcount; j++){
							if (GROUP[j].getName() .equals(name)){
								GROUP[j] = NewMember;
								found = true;
								System.out.println("TEST");
								break;
							} 
					}		
					if (!found) {
						GROUP[newcount] = NewMember;	
					}
					
					
					//--DEAD or ALIVE---
					if(items[11].equals("alive")){
						//not deceased
						NewMember.setDeceased(false);
					} else  {
						NewMember.setDeceased(true);
					}
					
					//--NUMBER of CHILDREN (4)--
					NewMember.setChildCount(Integer.parseInt(items[4]));
					
					//--NUMBER of SIBLINGS (5)--
					NewMember.setSiblingCount(Integer.parseInt(items[5]));
					
					//--set all children (9)---
					if (!items[9].equals("none")){
						//innerlist = list of children
						innerlist = items[7].split("#");
						
						int a = 0;
							//go through the array until an empty space is found
							while (true){
								a++;
								if (GROUP[newcount + a] == null){
									break;
								}
							}
							//newcount + a = next empty space
							//for each child...
							for (int k = 0; k < innerlist.length; k++){
								//for each child (innerlist[k]), see if they already exist...
								for (int m = 0; m < newcount; m++){
									if (GROUP[m].getName() .equals(innerlist[k])){
										child = GROUP[m];
										found = true;
									}
								}
								//if no child found already in GROUP...
								if (!found){
									child = new Member(innerlist[k]);			
									GROUP[newcount+a] = child;								
								}
								a++;	
								//set the child (it'll only be set if it exists)
								NewMember.setChild(child);
							}
							
					}
					
					
					//--set all siblings (10)---
					if(!items[10].equals("none")){
						//innerlist = list of  siblings
						innerlist = items[8].split("#");
						
						int a = 0;
							//go through the array until an empty space is found
							while (true){
								a++;
								if (GROUP[newcount + a] == null){
									break;
								}
							}
							//newcount + a = next empty space
							//for each sibling...
							for (int k = 0; k < innerlist.length; k++){
								//for each sibling (innerlist[k]), see if they already exist...
								for (int m = 0; m < newcount; m++){
									if (GROUP[m].getName() .equals(innerlist[k])){
										sibling = GROUP[m];
										found = true;
									}
								}
								//if no child found already in GROUP...
								if (!found){
									sibling = new Member(innerlist[k]);			
									GROUP[newcount+a] = sibling;								
								}
								a++;
								//set the sibling (will only set if it exists)
								NewMember.setSibling(sibling);
							}					
					}
					
					//--set mother (7)
					String motherName = items[7];
					if(!motherName.equals("none")){
					
						//search through GROUP to see if mother already exists as a Member
						for (int j = 0; j < newcount; j++){
							if (GROUP[j].getName() .equals(motherName)){
								mother = GROUP[j];
								found = true;
							}
						}
						//mother is not already listed in the GROUP, so new (temporary) Member is created
						if  (!found){
							int a = 0;
							//go through the array until an empty space is found
							while (true){
								a++;
								if (GROUP[newcount + a] == null){
									break;
								}
							}
							mother = new Member(motherName);			
							GROUP[newcount+a] = mother;
						}
						NewMember.setMother(mother);
					}
					//-set father (8)
					String fatherName = items[8];
					if(!fatherName.equals("none")){
					
						//search through GROUP to see if father already exists as a Member
						for (int j = 0; j < newcount; j++){
							if (GROUP[j].getName() .equals(fatherName)){
								father = GROUP[j];
								found = true;
							}
						}
						//father is not already listed in the GROUP, so new (temporary) Member is created
						if  (!found){
							int a = 0;
							//go through the array until an empty space is found
							while (true){
								a++;
								if (GROUP[newcount + a] == null){
									break;
								}
							}
							father = new Member(fatherName);			
							GROUP[newcount+a] = father;
						}
						NewMember.setFather(father);
					}
					
									
					newcount++;
					line = br.readLine();
				}
				reader.close();
				return 0;
			} catch (IOException e){
				System.out.println(e);
				return -1;
			}
		}else{
			System.out.println("--No save data--");
		}
		return 0;
	}
	
	
	public static void marriage(Member[]GROUP, Scanner s){
		
		System.out.println("What is the name of the first person?");
		String name1 = s.next();
		int first = memberIndex(name1,GROUP);
		System.out.println("And the other?");
		String name2 = s.next();
		int second = memberIndex(name2,GROUP);
		System.out.println("Thank you! " + name1 + " and " + name2  + " are falling in love...");
		GROUP[first].setMate(GROUP[second]);
		GROUP[second].setMate(GROUP[first]);
		System.out.println("They're now mates! Hurrah!");		
		System.out.println(name1 + "'s mate is: " + GROUP[first].getMate().getName());
		System.out.println(name2 + "'s mate is: " + GROUP[second].getMate().getName());
		
	}
	
	
	
	public static void main(String[] args){
	
		Member [] GROUP = new Member[1000];
		load(GROUP);
		
		Scanner s = new Scanner(System.in);		
		boolean play = true;
		boolean adding = true;
		while (play){
			System.out.println("You currently have [" + Member.getCount() + "] members!");
			tester(GROUP,s,adding);
			adding = true;
			System.out.println("Would you like add some more people? y / n");
			if (s.next().charAt(0) =='n'){
				adding = false;
				System.out.println("Redirecting to 'marriage'...");
				marriage(GROUP,s);
				System.out.println("Would you like to quit? y / n");
				if (s.next().charAt(0) =='y')  {
					save(GROUP);
					System.exit(0);
				}
			}	
		}		
	}
	
	
}
