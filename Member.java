//MEMBERS ---- each is a person
public class Member {

    private String name;
    Member mother;
    Member father;
	Member mate;
    private char gender;
	private int childCount;
    private Member[] children = new Member[10];
	private int siblingCount;
	private Member[] siblings = new Member[10];
    private boolean deceased;
    private int age,born;
    
    //keeps track of number of members (probably includes deceased ones, too):   see getCount() below
    public static int counter = 0;
    
	//a temporary Member....
	public Member(String name){
		deceased = false;
        this.name = name;
        this.born = 0;
        this.gender = 'm';
        this.mate = null;
        this.age = 0;
		this.mother = null;
		this.father = null;
		this.childCount = 0;
		this.siblingCount = 0;
        counter++;
	}
	
	//a new Member is born!
    public Member(String name, int born, char gender){
        deceased = false;
        this.name = name;
        this.born = born;
        this.gender = gender;
        this.mate = null;
        this.age = 0;
		this.mother = null;
		this.father = null;
		this.childCount = 0;
		this.siblingCount = 0;
        counter++;
    }
	
	//a new Member arrives! He or she is a Stranger.
	public Member(String name, int born, char gender, int age, Member mate){
        deceased = false;
        this.name = name;
        this.born = born;
        this.gender = gender;
        this.mate = mate;
        this.age = age;
		this.mother = null;
		this.father = null;
		this.childCount = 0;
		this.siblingCount = 0;
        counter++;
    }
	
    //System.out.println's the stats
    public void printStats(){
        System.out.println(name + "'s information: ");
		System.out.println("Age: " + age +  "\nBorn in year " + born);
		if (gender == 'f'){
			System.out.println("Gender: female");
		} else {
			System.out.println("Gender: male");
		}
		if (mate != null){
			System.out.println("Mate: " + mate.getName());
		} else {
			System.out.println("Mate: none");
		}
		
		if (mother != null) System.out.println("Mother: " + mother.getName());
		if (father != null) System.out.println("Father: " + father.getName());
		
		if (childCount > 0){
			System.out.println("Children: ");
			for(int i = 0; i <childCount; i++){
				System.out.println(children[i]);
			}
		}
        
        if (deceased){
            System.out.println("--deceased--");
        }
    }
    
    
	//--------SETS----------//
    //sets this Member's mother (who should already exist!)
    public void setMother(Member mother){
       this.mother = mother;       
    }
    //sets this Member's father (who should also already exist)
    public void setFather(Member father){
       this.father = father;
    }
    
	public void setAge(int age){
		this.age = age;
	}
		
	public void setDeceased(boolean deceased){
		this.deceased = deceased;
	}
		
	//link child to this Member (who is its parent) (child should already exist)
	public void setChild(Member child){
		this.childCount++;
		this.children[this.childCount-1] = child;
	}
		
	//link sibling to this Member (who is its brother or sister) (sibling should already exist)
	public void setSibling(Member sibling){
		this.siblingCount++;
		this.siblings[this.siblingCount-1] = sibling;	
	}
	//link Member's mate (mate should already exist)
	public void setMate(Member mate){
		this.mate = mate;
	}
			
	public void setChildCount(int childCount){
		this.childCount = childCount;
	}
			
	public void setSiblingCount(int siblingCount){
		this.siblingCount = siblingCount;
	}
	
	//careful with this one! Use only with load.
	public static void decrementCount(){
		counter--;
	}
			
		
  //----------GETS-------------//
  
  public static int getCount(){
	return counter;
  }
  
    public int getAge(){
        return this.age;
    }
    
	public int getYearBorn(){
		return this.born;
	}
	
	public char getGender(){
		return this.gender;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int howManyChildren(){
		return childCount;
	}
	
	public Member[] getChildren(){
		return this.children;
	}
	
	public Member getMother(){
		return this.mother;
	}
	
	public Member getFather(){
		return this.father;
	}
	
	public int howManySiblings(){
		return siblingCount;
	}
	
	public Member[] getSiblings(){
		return this.siblings;
	}

	public Member getMate(){
		return this.mate;
	}
	
	
	//------------Booleans------------//
	public boolean hasChildren(){
		if (this.childCount > 0) return true;
		else return false;
	}
	
	public boolean isDead(){
		return this.deceased;
	}
	
	public boolean hasMate(){
		if (mate != null) return true;
		else return false;
	}

	public boolean hasMother(){
		if (mother != null) return true;
		else return false;
	}

	public boolean hasFather(){
		if (father != null) return true;
		else return false;
	}

	public boolean hasSiblings(){
		if (siblingCount > 0) return true;
		else return false;
	}
	
	
	
	
}
