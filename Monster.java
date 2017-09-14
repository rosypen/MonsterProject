public class Monster {
	private static int totalMonsters = 0;
	
	//MONSTER FIELDS
	private String name;
	private int health;
	private int attack;
	private int movement;
	private int x;
	private int y;
	private boolean alive;
	
	//MONSTER SETTERS AND GETTERS
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		System.out.printf("Monster name set to: %s\n",name);
		
		
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
		if (this.health <= 0) {
			this.alive = false;
		}
	}
	
	
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	
	
	public int getMovement() {
		return movement;
	}
	public void setMovement(int movement) {
		this.movement = movement;
	}
	
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	
	
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public static int getTotalMonsters() {
		return totalMonsters;
	}
	
	
	
	public String toString() {
		return "Monster."+this.name;
	}
	
	public void printStats() {
		String status = this.name+"'s Stats:\n"
				+ "Health: "+this.health+"\n"
				+ "Attack: "+this.attack+"\n";
		System.out.println(status);
	}
	
	public int[] move() {
		int[] newMove = {this.x,this.y};
		
		int right = 0;
		int left = 1;
		int down = 2;
		int up = 3;
		
		int randMovement = (int) (Math.random() * 4);
		int randDistance = (int) (Math.random() * (this.movement+1));
		
		//System.out.println("random movement: "+randMovement);
		//System.out.println("random distance: "+randDistance);
		if (randMovement == right) {
			newMove[0] = this.x + randDistance;
		} else if (randMovement == left) {
			newMove[0] = this.x - randDistance;
		} else if (randMovement == down) {
			newMove[1] = this.y - randDistance;
		} else if (randMovement == up) {
			newMove[1] = this.y + randDistance;
		}
		//System.out.println(Arrays.toString(newMove));
		return newMove;
	}
	
	//CONSTRUCTORS
	public Monster(){
		totalMonsters++;
		this.name = "Monster";
		this.health = ((int) (Math.random()*501)) +1; //+1 just in case the randint is 0
		this.attack = ((int) (Math.random()*101)) +1; //+1 just in case the randint is 0
		this.movement = (int) (Math.random()*4);
		this.x = 0;
		this.y = 0;
		this.alive = true;
		System.out.println("New Monster created. Total number of Monsters: "+totalMonsters);
	}
	
	public Monster(String name) {
		super();
		this.setName(name);
	}
	
}
